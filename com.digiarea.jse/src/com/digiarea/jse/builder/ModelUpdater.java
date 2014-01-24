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

/**
 * The Class ModelUpdater.
 */
public class ModelUpdater {

	/**
	 * The qualified name.
	 */
	private NameExpr qualifiedName;
	
	/**
	 * The type declaration.
	 */
	private TypeDeclaration typeDeclaration;
	
	/**
	 * The is interface.
	 */
	private boolean isInterface = false;
	
	/**
	 * The is class.
	 */
	private boolean isClass = false;
	
	/**
	 * The is abstract.
	 */
	private boolean isAbstract = false;
	
	/**
	 * The is annotation.
	 */
	private boolean isAnnotation = false;
	
	/**
	 * The is enumeration.
	 */
	private boolean isEnumeration = false;
	
	/**
	 * The is generic.
	 */
	private boolean isGeneric = false;
	
	/**
	 * The has extends.
	 */
	private boolean hasExtends = false;
	
	/**
	 * The has public constructor.
	 */
	private boolean hasPublicConstructor = false;
	
	/**
	 * The has default constructor.
	 */
	private boolean hasDefaultConstructor = false;
	
	/**
	 * The hierarchy.
	 */
	private ModelHierarchy hierarchy;
	
	/**
	 * The fields.
	 */
	private List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();
	
	/**
	 * The entries.
	 */
	private List<EnumConstantDeclaration> entries = new ArrayList<EnumConstantDeclaration>();
	
	/**
	 * The max constructor parameters.
	 */
	private List<Parameter> maxConstructorParameters = new ArrayList<Parameter>();
	
	/**
	 * The extends type.
	 */
	private ClassOrInterfaceType extendsType = null;
	
	/**
	 * The type parameters.
	 */
	private List<TypeParameter> typeParameters = null;
	
	/**
	 * The extends list.
	 */
	private List<ClassOrInterfaceType> extendsList = null;
	
	/**
	 * The implements list.
	 */
	private List<ClassOrInterfaceType> implementsList = null;
	
	/**
	 * The annotations.
	 */
	private List<AnnotationExpr> annotations = null;
	
	/**
	 * The annotation members.
	 */
	private List<AnnotationMemberDeclaration> annotationMembers = null;

	/**
	 * Instantiates a new model updater.
	 *
	 * @param unit the unit
	 * @param hierarchy the hierarchy
	 */
	public ModelUpdater(CompilationUnit unit, ModelHierarchy hierarchy) {
		this(NodeFacade.NameExpr(unit.getName()), NodeUtils.getMainType(unit),
				hierarchy);
	}

	/**
	 * Instantiates a new model updater.
	 *
	 * @param qualifiedName the qualified name
	 * @param typeDeclaration the type declaration
	 * @param hierarchy the hierarchy
	 */
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

	/**
	 * Gets the max constructor parameters.
	 *
	 * @param clazz the clazz
	 * @return the max constructor parameters
	 */
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

	/**
	 * Gets the fields.
	 *
	 * @param clazz the clazz
	 * @param withSupers the with supers
	 * @param extendsType the extends type
	 * @return the fields
	 */
	private List<FieldDeclaration> getFields(ClassDeclaration clazz,
			boolean withSupers, ClassOrInterfaceType extendsType) {
		List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();
		getFields(fields, clazz, withSupers);
		return fields;
	}

	/**
	 * Gets the fields.
	 *
	 * @param clazz the clazz
	 * @param withSupers the with supers
	 * @return the fields
	 */
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

	/**
	 * Gets the fields.
	 *
	 * @param fields the fields
	 * @param clazz the clazz
	 * @param withSupers the with supers
	 * @return the fields
	 */
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

	/**
	 * Gets the fields.
	 *
	 * @param fields the fields
	 * @param extType the ext type
	 * @return the fields
	 */
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

	/**
	 * Checks if is interface.
	 *
	 * @return true, if is interface
	 */
	public boolean isInterface() {
		return isInterface;
	}

	/**
	 * Checks if is class.
	 *
	 * @return true, if is class
	 */
	public boolean isClass() {
		return isClass;
	}

	/**
	 * Checks if is abstract.
	 *
	 * @return true, if is abstract
	 */
	public boolean isAbstract() {
		return isAbstract;
	}

	/**
	 * Checks if is annotation.
	 *
	 * @return true, if is annotation
	 */
	public boolean isAnnotation() {
		return isAnnotation;
	}

	/**
	 * Checks if is enumeration.
	 *
	 * @return true, if is enumeration
	 */
	public boolean isEnumeration() {
		return isEnumeration;
	}

	/**
	 * Adds the member.
	 *
	 * @param decl the decl
	 */
	public void addMember(BodyDeclaration decl) {
		NodeUtils.addMember(typeDeclaration, decl);
	}

	/**
	 * Gets the qualified name.
	 *
	 * @return the qualified name
	 */
	public NameExpr getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * Gets the type declaration.
	 *
	 * @return the type declaration
	 */
	public TypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	/**
	 * Checks for extends.
	 *
	 * @return true, if successful
	 */
	public boolean hasExtends() {
		return hasExtends;
	}

	/**
	 * Sets the checks for public constructor.
	 *
	 * @param hasPublicConstructor the new checks for public constructor
	 */
	public void setHasPublicConstructor(boolean hasPublicConstructor) {
		this.hasPublicConstructor = hasPublicConstructor;
	}

	/**
	 * Checks for public constructor.
	 *
	 * @return true, if successful
	 */
	public boolean hasPublicConstructor() {
		return hasPublicConstructor;
	}

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	public List<FieldDeclaration> getFields() {
		return fields;
	}

	/**
	 * Gets the max constructor parameters.
	 *
	 * @return the max constructor parameters
	 */
	public List<Parameter> getMaxConstructorParameters() {
		return maxConstructorParameters;
	}

	/**
	 * Gets the extends type.
	 *
	 * @return the extends type
	 */
	public ClassOrInterfaceType getExtendsType() {
		return extendsType;
	}

	/**
	 * Gets the entries.
	 *
	 * @return the entries
	 */
	public List<EnumConstantDeclaration> getEntries() {
		return entries;
	}

	/**
	 * Gets the extends list.
	 *
	 * @return the extends list
	 */
	public List<ClassOrInterfaceType> getExtendsList() {
		return extendsList;
	}

	/**
	 * Gets the implements list.
	 *
	 * @return the implements list
	 */
	public List<ClassOrInterfaceType> getImplementsList() {
		return implementsList;
	}

	/**
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	/**
	 * Gets the annotation members.
	 *
	 * @return the annotation members
	 */
	public List<AnnotationMemberDeclaration> getAnnotationMembers() {
		return annotationMembers;
	}

	/**
	 * Checks for default constructor.
	 *
	 * @return true, if successful
	 */
	public boolean hasDefaultConstructor() {
		return hasDefaultConstructor;
	}

	/**
	 * Sets the checks for default constructor.
	 *
	 * @param hasDefaultConstructor the new checks for default constructor
	 */
	public void setHasDefaultConstructor(boolean hasDefaultConstructor) {
		this.hasDefaultConstructor = hasDefaultConstructor;
	}

	/**
	 * Gets the type parameters.
	 *
	 * @return the type parameters
	 */
	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	/**
	 * Checks if is generic.
	 *
	 * @return true, if is generic
	 */
	public boolean isGeneric() {
		return isGeneric;
	}

}
