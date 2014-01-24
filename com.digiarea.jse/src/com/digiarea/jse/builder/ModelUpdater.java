/*******************************************************************************
 * Copyright (c) 2011 - 2014 DigiArea, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     DigiArea, Inc. - initial API and implementation
 *******************************************************************************/
package com.digiarea.jse.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.AnnotationMemberDeclaration;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.FieldDeclaration;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.Type;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.utils.NodeUtils;

public class ModelUpdater {

	private NameExpr qualifiedName;
	private TypeDeclaration typeDeclaration;
	private boolean isInterface = false;
	private boolean isClass = false;
	private boolean isAbstract = false;
	private boolean isAnnotation = false;
	private boolean isEnumeration = false;
	private boolean isGeneric = false;
	private boolean hasExtends = false;
	private boolean hasPublicConstructor = false;
	private boolean hasDefaultConstructor = false;
	private ModelHierarchy hierarchy;
	private List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();
	private List<EnumConstantDeclaration> entries = new ArrayList<EnumConstantDeclaration>();
	private List<Parameter> maxConstructorParameters = new ArrayList<Parameter>();
	private ClassOrInterfaceType extendsType = null;
	private List<TypeParameter> typeParameters = null;
	private List<ClassOrInterfaceType> extendsList = null;
	private List<ClassOrInterfaceType> implementsList = null;
	private List<AnnotationExpr> annotations = null;
	private List<AnnotationMemberDeclaration> annotationMembers = null;

	public ModelUpdater(CompilationUnit unit, ModelHierarchy hierarchy) {
		this(NodeFacade.NameExpr(unit.getName()), NodeUtils.getMainType(unit),
				hierarchy);
	}

	public ModelUpdater(NameExpr qualifiedName,
			TypeDeclaration typeDeclaration, ModelHierarchy hierarchy) {
		super();
		this.qualifiedName = qualifiedName;
		this.typeDeclaration = typeDeclaration;
		this.hierarchy = hierarchy;
		this.annotations = typeDeclaration.getAnnotations();
		// cache it
		if (typeDeclaration instanceof AnnotationDeclaration) {
			isAnnotation = true;
			AnnotationDeclaration annotation = (AnnotationDeclaration) typeDeclaration;
			annotationMembers = new ArrayList<AnnotationMemberDeclaration>();
			if (annotation.getMembers() != null) {
				for (BodyDeclaration member : annotation.getMembers()) {
					if (member instanceof AnnotationMemberDeclaration) {
						annotationMembers
								.add((AnnotationMemberDeclaration) member);
					}
				}
			}
		} else if (typeDeclaration instanceof ClassDeclaration) {
			ClassDeclaration clazz = (ClassDeclaration) typeDeclaration;
			typeParameters = clazz.getTypeParameters();
			isGeneric = typeParameters != null;
			isInterface = false;
			isClass = !isInterface;
			hasExtends = clazz.getExtendsType() != null;
			if (hasExtends) {
				extendsType = clazz.getExtendsType();
			}
			if (clazz.getImplementsList() != null) {
				implementsList = clazz.getImplementsList();
			}
			fields = getFields(clazz, true, extendsType);
			maxConstructorParameters = getMaxConstructorParameters(clazz);
			if (maxConstructorParameters == null
					|| maxConstructorParameters.size() == 0) {
				hasPublicConstructor = true;
			}
			isAbstract = clazz.getModifiers().isAbstract();
		} else if (typeDeclaration instanceof InterfaceDeclaration) {
			InterfaceDeclaration clazz = (InterfaceDeclaration) typeDeclaration;
			isInterface = true;
			isClass = !isInterface;
			hasExtends = clazz.getExtendsList() != null
					&& !clazz.getExtendsList().isEmpty();
			if (hasExtends) {
				extendsList = clazz.getExtendsList();
			}
		} else if (typeDeclaration instanceof EnumDeclaration) {
			EnumDeclaration clazz = (EnumDeclaration) typeDeclaration;
			isEnumeration = true;
			fields = getFields(clazz, true);
			entries = clazz.getEntries();
		}
	}

	private List<Parameter> getMaxConstructorParameters(ClassDeclaration clazz) {
		List<Parameter> parameters = null;
		int max = 0;
		List<BodyDeclaration> body = clazz.getMembers();
		if (body != null) {
			for (Iterator<BodyDeclaration> iterator = body.iterator(); iterator
					.hasNext();) {
				BodyDeclaration bodyDeclaration = iterator.next();
				if (bodyDeclaration instanceof ConstructorDeclaration) {
					ConstructorDeclaration m = (ConstructorDeclaration) bodyDeclaration;
					boolean hasPublicModifier = m.getModifiers().isPublic();
					if (!hasPublicConstructor && hasPublicModifier) {
						hasPublicConstructor = true;
					}
					List<Parameter> p = m.getParameters();
					if (p != null) {
						int size = p.size();
						if (size == 0 && !hasDefaultConstructor
								&& hasPublicModifier) {
							hasDefaultConstructor = true;
						}
						if (size > max) {
							parameters = p;
							max = size;
						}
					} else if (!hasDefaultConstructor && hasPublicModifier) {
						hasDefaultConstructor = true;
					}
				}
			}
		}
		if (parameters == null) {
			return new ArrayList<Parameter>();
		} else {
			return parameters;
		}
	}

	private List<FieldDeclaration> getFields(ClassDeclaration clazz,
			boolean withSupers, ClassOrInterfaceType extendsType) {
		List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();
		getFields(fields, clazz, withSupers);
		return fields;
	}

	private List<FieldDeclaration> getFields(EnumDeclaration clazz,
			boolean withSupers) {
		List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();
		if (clazz.getMembers() != null) {
			for (BodyDeclaration decl : clazz.getMembers()) {
				if (decl instanceof FieldDeclaration) {
					fields.add((FieldDeclaration) decl);
				}
			}
		}
		return fields;
	}

	private void getFields(List<FieldDeclaration> fields,
			ClassDeclaration clazz, boolean withSupers) {
		// get fields from the declaration
		if (clazz.getMembers() != null) {
			for (BodyDeclaration decl : clazz.getMembers()) {
				if (decl instanceof FieldDeclaration) {
					fields.add((FieldDeclaration) decl);
				}
			}
		}
		// get fields from super class
		if (withSupers && clazz.getExtendsType() != null) {
			ClassOrInterfaceType extType = clazz.getExtendsType();
			getFields(fields, extType);
		}
	}

	private void getFields(List<FieldDeclaration> fields, Type extType) {
		for (CompilationUnit unit : hierarchy.getProject()
				.getCompilationUnits()) {
			String uqn = NodeUtils.getQualifiedName(unit).toString();
			if (uqn.equals(extType.toString())) {
				TypeDeclaration decl = NodeUtils.getMainType(unit);
				if (decl instanceof ClassDeclaration) {
					getFields(fields, (ClassDeclaration) decl, true);
				}
			}
		}
	}

	public boolean isInterface() {
		return isInterface;
	}

	public boolean isClass() {
		return isClass;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public boolean isAnnotation() {
		return isAnnotation;
	}

	public boolean isEnumeration() {
		return isEnumeration;
	}

	public void addMember(BodyDeclaration decl) {
		NodeUtils.addMember(typeDeclaration, decl);
	}

	public NameExpr getQualifiedName() {
		return qualifiedName;
	}

	public TypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	public boolean hasExtends() {
		return hasExtends;
	}

	public void setHasPublicConstructor(boolean hasPublicConstructor) {
		this.hasPublicConstructor = hasPublicConstructor;
	}

	public boolean hasPublicConstructor() {
		return hasPublicConstructor;
	}

	public List<FieldDeclaration> getFields() {
		return fields;
	}

	public List<Parameter> getMaxConstructorParameters() {
		return maxConstructorParameters;
	}

	public ClassOrInterfaceType getExtendsType() {
		return extendsType;
	}

	public List<EnumConstantDeclaration> getEntries() {
		return entries;
	}

	public List<ClassOrInterfaceType> getExtendsList() {
		return extendsList;
	}

	public List<ClassOrInterfaceType> getImplementsList() {
		return implementsList;
	}

	public List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	public List<AnnotationMemberDeclaration> getAnnotationMembers() {
		return annotationMembers;
	}

	public boolean hasDefaultConstructor() {
		return hasDefaultConstructor;
	}

	public void setHasDefaultConstructor(boolean hasDefaultConstructor) {
		this.hasDefaultConstructor = hasDefaultConstructor;
	}

	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	public boolean isGeneric() {
		return isGeneric;
	}

}
