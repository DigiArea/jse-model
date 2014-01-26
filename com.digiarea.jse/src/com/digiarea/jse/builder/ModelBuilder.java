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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.Expression;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.utils.NodeUtils;

/**
 * The Class ModelBuilder.
 */
public abstract class ModelBuilder {

	/**
	 * The Enum BuilderType.
	 */
	public static enum BuilderType {
		
		/**
		 * The annotation.
		 */
		ANNOTATION, 
 /**
  * The class.
  */
 CLASS, 
 /**
  * The interface.
  */
 INTERFACE, 
 /**
  * The enumeration.
  */
 ENUMERATION
	}

	/**
	 * The Class FieldItem.
	 */
	public static class FieldItem {

		/**
		 * The field type.
		 */
		private Type fieldType;
		
		/**
		 * The field name.
		 */
		private String fieldName;
		
		/**
		 * The default value.
		 */
		private Expression defaultValue;
		
		/**
		 * The builder.
		 */
		private ModelBuilder builder;
		
		/**
		 * The with getter.
		 */
		private boolean withGetter = true;
		
		/**
		 * The with setter.
		 */
		private boolean withSetter = true;

		/**
		 * Instantiates a new field item.
		 *
		 * @param fieldType the field type
		 * @param fieldName the field name
		 * @param defaultValue the default value
		 * @param builder the builder
		 * @param withGetter the with getter
		 * @param withSetter the with setter
		 */
		public FieldItem(Type fieldType, String fieldName,
				Expression defaultValue, ModelBuilder builder,
				boolean withGetter, boolean withSetter) {
			super();
			this.fieldType = fieldType;
			this.fieldName = fieldName;
			this.defaultValue = defaultValue;
			this.builder = builder;
			this.withGetter = withGetter;
			this.withSetter = withSetter;
		}

		/**
		 * Gets the field type.
		 *
		 * @return the field type
		 */
		public Type getFieldType() {
			return fieldType;
		}

		/**
		 * Gets the field name.
		 *
		 * @return the field name
		 */
		public String getFieldName() {
			return fieldName;
		}

		/**
		 * Gets the default value.
		 *
		 * @return the default value
		 */
		public Expression getDefaultValue() {
			return defaultValue;
		}

		/**
		 * Gets the builder.
		 *
		 * @return the builder
		 */
		public ModelBuilder getBuilder() {
			return builder;
		}

		/**
		 * Checks if is with getter.
		 *
		 * @return true, if is with getter
		 */
		public boolean isWithGetter() {
			return withGetter;
		}

		/**
		 * Checks if is with setter.
		 *
		 * @return true, if is with setter
		 */
		public boolean isWithSetter() {
			return withSetter;
		}

	}

	/**
	 * The qualified name.
	 */
	protected NameExpr qualifiedName = null;
	
	/**
	 * The type.
	 */
	protected ClassOrInterfaceType type = null;
	
	/**
	 * The java doc.
	 */
	protected JavadocComment javaDoc = null;
	
	/**
	 * The annotations.
	 */
	protected List<AnnotationExpr> annotations = null;
	
	/**
	 * The members.
	 */
	protected List<BodyDeclaration> members = new ArrayList<BodyDeclaration>();
	
	/**
	 * The type parameters.
	 */
	protected List<TypeParameter> typeParameters = null;
	
	/**
	 * The implements list.
	 */
	protected List<ClassOrInterfaceType> implementsList = null;
	
	/**
	 * The extends type.
	 */
	protected ClassOrInterfaceType extendsType = null;
	
	/**
	 * The extends list.
	 */
	protected List<ClassOrInterfaceType> extendsList = null;
	
	/**
	 * The entries.
	 */
	protected List<EnumConstantDeclaration> entries = new ArrayList<EnumConstantDeclaration>();
	
	/**
	 * The is abstract.
	 */
	protected boolean isAbstract = false;
	
	/**
	 * The is private.
	 */
	protected boolean isPrivate = false;
	
	/**
	 * The is final.
	 */
	protected boolean isFinal = false;
	
	/**
	 * The is static.
	 */
	protected boolean isStatic = false;
	
	/**
	 * The make full constructor.
	 */
	protected boolean makeFullConstructor = true;
	
	/**
	 * The make default constructor.
	 */
	protected boolean makeDefaultConstructor = true;
	
	/**
	 * The make constructor.
	 */
	protected boolean makeConstructor = true;
	
	/**
	 * The modifiers.
	 */
	protected int modifiers = Modifiers.PUBLIC;

	/**
	 * The field items.
	 */
	protected List<FieldItem> fieldItems = new ArrayList<FieldItem>();

	/**
	 * Instantiates a new model builder.
	 *
	 * @param qualifiedName the qualified name
	 */
	public ModelBuilder(String qualifiedName) {
		super();
		this.qualifiedName = NodeFacade.QualifiedNameExpr(qualifiedName);
		this.type = NodeFacade.ClassOrInterfaceType(this.qualifiedName);
	}

	/**
	 * Sets the make default constructor.
	 *
	 * @param makeDefaultConstructor the new make default constructor
	 */
	public void setMakeDefaultConstructor(boolean makeDefaultConstructor) {
		this.makeDefaultConstructor = makeDefaultConstructor;
	}

	/**
	 * Sets the make constructor.
	 *
	 * @param makeConstructor the new make constructor
	 */
	public void setMakeConstructor(boolean makeConstructor) {
		this.makeConstructor = makeConstructor;
	}

	/**
	 * Sets the java doc.
	 *
	 * @param javaDoc the new java doc
	 */
	public void setJavaDoc(JavadocComment javaDoc) {
		this.javaDoc = javaDoc;
	}

	/**
	 * Sets the annotations.
	 *
	 * @param annotations the new annotations
	 */
	public void setAnnotations(List<AnnotationExpr> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Sets the type parameters.
	 *
	 * @param typeParameters the new type parameters
	 */
	public void setTypeParameters(List<TypeParameter> typeParameters) {
		this.typeParameters = typeParameters;
	}

	/**
	 * Sets the implements list.
	 *
	 * @param implementsList the new implements list
	 */
	public void setImplementsList(List<ClassOrInterfaceType> implementsList) {
		this.implementsList = implementsList;
	}

	/**
	 * Sets the extends type.
	 *
	 * @param extendsList the new extends type
	 */
	public void setExtendsType(ClassOrInterfaceType extendsList) {
		this.extendsType = extendsList;
	}

	/**
	 * Sets the extends list.
	 *
	 * @param extendsList the new extends list
	 */
	public void setExtendsList(List<ClassOrInterfaceType> extendsList) {
		this.extendsList = extendsList;
	}

	/**
	 * Gets the type parameters.
	 *
	 * @return the type parameters
	 */
	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dagxp.lmm.jse.builder.ModelBuilder#getQualifiedName()
	 */
	/**
	 * Gets the qualified name.
	 *
	 * @return the qualified name
	 */
	public NameExpr getQualifiedName() {
		return qualifiedName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dagxp.lmm.jse.builder.ModelBuilder#getType()
	 */
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ClassOrInterfaceType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dagxp.lmm.jse.builder.ModelBuilder#build()
	 */
	/**
	 * Builds the.
	 *
	 * @return the compilation unit
	 */
	public final CompilationUnit build() {
		buildBody();
		TypeDeclaration typeDeclaration = getTypeDeclaration();
		PackageDeclaration pkg = null;
		if (qualifiedName instanceof QualifiedNameExpr) {
			pkg = NodeFacade.PackageDeclaration(
					((QualifiedNameExpr) qualifiedName).getQualifier(), null);
		}
		return NodeFacade.CompilationUnit(pkg, null,
				Arrays.asList(typeDeclaration), null,
				NodeUtils.toString(qualifiedName));
	}

	/**
	 * Builds the body.
	 */
	protected void buildBody() {
		String cuName = getQualifiedName().getName();
		// parameters for full constructor
		List<Parameter> parameters = new ArrayList<Parameter>();
		// statements for full constructor
		List<Statement> fullStmts = new ArrayList<Statement>();
		// make fields, getters, setters, parameters, statements
		for (FieldItem item : fieldItems) {
			Type type = item.getFieldType();
			String name = item.getFieldName();
			Expression init = item.getDefaultValue();
			// full constructor parameters
			parameters.add(NodeFacade.Parameter(type, name));
			// field
			addMember(NodeFacade.FieldDeclaration(Modifiers.PRIVATE, type,
					name, init));
			if (item.isWithGetter()) {
				// getter
				addMember(NodeUtils.createGetterDeclaration(type, name));
			}
			if (item.isWithSetter()) {
				// setter
				addMember(NodeUtils.createSetterDeclaration(type, name));
			}
			// full constructor statements
			fullStmts.add(NodeFacade.ExpressionStmt(NodeFacade.AssignExpr(
					NodeFacade.FieldAccessExpr(NodeFacade.ThisExpr(), null,
							name), NodeFacade.NameExpr(name),
					AssignOperator.assign)));
		}
		// make constructors
		makeConstructors(cuName, parameters, fullStmts);
	}

	/**
	 * Gets the type declaration.
	 *
	 * @return the type declaration
	 */
	public abstract TypeDeclaration getTypeDeclaration();

	/**
	 * Checks if is interface.
	 *
	 * @return true, if is interface
	 */
	public boolean isInterface() {
		return false;
	}

	/**
	 * Checks if is enumeration.
	 *
	 * @return true, if is enumeration
	 */
	public boolean isEnumeration() {
		return false;
	}

	/**
	 * Checks if is annotation.
	 *
	 * @return true, if is annotation
	 */
	public boolean isAnnotation() {
		return false;
	}

	/**
	 * Checks if is abstract.
	 *
	 * @return true, if is abstract
	 */
	public boolean isAbstract() {
		return Modifiers.hasModifier(modifiers, Modifiers.ABSTRACT);
	}

	/**
	 * Checks if is private.
	 *
	 * @return true, if is private
	 */
	public boolean isPrivate() {
		return Modifiers.hasModifier(modifiers, Modifiers.PRIVATE);
	}

	/**
	 * Checks if is final.
	 *
	 * @return true, if is final
	 */
	public boolean isFinal() {
		return Modifiers.hasModifier(modifiers, Modifiers.FINAL);
	}

	/**
	 * Checks if is static.
	 *
	 * @return true, if is static
	 */
	public boolean isStatic() {
		return Modifiers.hasModifier(modifiers, Modifiers.STATIC);
	}

	/**
	 * Gets the modifiers.
	 *
	 * @return the modifiers
	 */
	public int getModifiers() {
		return modifiers;
	}

	/**
	 * Gets the modifiers expression.
	 *
	 * @return the modifiers expression
	 */
	public Expression getModifiersExpression() {
		return NodeUtils.modifiersToExpression(modifiers);
	}

	/**
	 * Sets the modifiers.
	 *
	 * @param modifiers the new modifiers
	 */
	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	/**
	 * Sets the modifiers.
	 *
	 * @param modifiers the new modifiers
	 */
	public void setModifiers(Expression modifiers) {
		this.modifiers = NodeUtils.getModifiers(modifiers);
	}

	/**
	 * Adds the field item.
	 *
	 * @param fieldType the field type
	 * @param fieldName the field name
	 * @param defaultValue the default value
	 */
	public final void addFieldItem(Type fieldType, String fieldName,
			Expression defaultValue) {
		addFieldItem(fieldType, fieldName, defaultValue, true, true);
	}

	/**
	 * Adds the field item.
	 *
	 * @param fieldType the field type
	 * @param fieldName the field name
	 * @param defaultValue the default value
	 * @param withGetter the with getter
	 * @param withSetter the with setter
	 */
	public final void addFieldItem(Type fieldType, String fieldName,
			Expression defaultValue, boolean withGetter, boolean withSetter) {
		fieldItems.add(new FieldItem(fieldType, fieldName, defaultValue, this,
				withGetter, withSetter));
	}

	/**
	 * Adds the field item.
	 *
	 * @param item the item
	 */
	public final void addFieldItem(FieldItem item) {
		fieldItems.add(item);
	}

	/**
	 * Adds the member.
	 *
	 * @param member the member
	 */
	public void addMember(BodyDeclaration member) {
		members.add(member);
	}

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
	public List<BodyDeclaration> getMembers() {
		return members;
	}

	/**
	 * Adds the annotation.
	 *
	 * @param annotation the annotation
	 */
	public void addAnnotation(AnnotationExpr annotation) {
		if (annotations == null) {
			annotations = new ArrayList<AnnotationExpr>();
		}
		annotations.add(annotation);
	}

	/**
	 * Adds the enum entry.
	 *
	 * @param entry the entry
	 */
	public void addEnumEntry(EnumConstantDeclaration entry) {
		entries.add(entry);
	}

	/**
	 * Sets the enum entries.
	 *
	 * @param names the new enum entries
	 */
	public void setEnumEntries(List<String> names) {
		for (Iterator<String> iterator = names.iterator(); iterator.hasNext();) {
			entries.add(NodeFacade.EnumConstantDeclaration(iterator.next()));
		}
	}

	/**
	 * Gets the builder for class.
	 *
	 * @param qualifiedName the qualified name
	 * @return the builder for class
	 */
	private static final ModelBuilder getBuilderForClass(String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public TypeDeclaration getTypeDeclaration() {
				return NodeFacade.ClassDeclaration(typeParameters, extendsType,
						implementsList, modifiers, qualifiedName.getName(),
						members, javaDoc, annotations);
			}
		};
	}

	/**
	 * Gets the builder for interface.
	 *
	 * @param qualifiedName the qualified name
	 * @return the builder for interface
	 */
	private static final ModelBuilder getBuilderForInterface(
			String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public boolean isInterface() {
				return true;
			}

			@Override
			public TypeDeclaration getTypeDeclaration() {
				return NodeFacade.InterfaceDeclaration(typeParameters,
						extendsList, modifiers, qualifiedName.getName(),
						members, javaDoc, annotations);
			}

			@Override
			protected void buildBody() {
				for (FieldItem item : fieldItems) {
					// field is turned out to method declaration
					BodyDeclaration methodDeclaration = NodeFacade
							.MethodDeclaration(item.getFieldType(),
									item.getFieldName());
					methodDeclaration.setJavaDoc(javaDoc);
					addMember(methodDeclaration);
				}
			}
		};
	}

	/**
	 * Gets the builder for enumeration.
	 *
	 * @param qualifiedName the qualified name
	 * @return the builder for enumeration
	 */
	private static final ModelBuilder getBuilderForEnumeration(
			String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public boolean isEnumeration() {
				return true;
			}

			@Override
			public TypeDeclaration getTypeDeclaration() {
				Collections.sort(entries,
						new Comparator<EnumConstantDeclaration>() {
							@Override
							public int compare(EnumConstantDeclaration arg0,
									EnumConstantDeclaration arg1) {
								return arg0.getName().compareTo(arg1.getName());
							}
						});
				return NodeFacade.EnumDeclaration(implementsList, entries,
						modifiers, qualifiedName.getName(), members, javaDoc,
						annotations);
			}
		};
	}

	/**
	 * Gets the builder for annotation.
	 *
	 * @param qualifiedName the qualified name
	 * @return the builder for annotation
	 */
	private static final ModelBuilder getBuilderForAnnotation(
			String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public boolean isAnnotation() {
				return true;
			}

			@Override
			public TypeDeclaration getTypeDeclaration() {
				return NodeFacade.AnnotationDeclaration(modifiers,
						qualifiedName.getName(), members, javaDoc, annotations);
			}

			@Override
			protected void buildBody() {
				for (FieldItem item : fieldItems) {
					// annotation member
					addMember(NodeFacade.AnnotationMemberDeclaration(
							Modifiers.PUBLIC, item.getFieldType(),
							item.getFieldName(), item.getDefaultValue(), null,
							null));
				}
			}
		};
	}

	/**
	 * Gets the builder.
	 *
	 * @param qualifiedName the qualified name
	 * @param builderType the builder type
	 * @return the builder
	 */
	public static final ModelBuilder getBuilder(String qualifiedName,
			BuilderType builderType) {
		switch (builderType) {
		case ANNOTATION:
			return getBuilderForAnnotation(qualifiedName);
		case CLASS:
			return getBuilderForClass(qualifiedName);
		case INTERFACE:
			return getBuilderForInterface(qualifiedName);
		case ENUMERATION:
			return getBuilderForEnumeration(qualifiedName);
		default:
			return null;
		}
	}

	/**
	 * Make constructors.
	 *
	 * @param cuName the cu name
	 * @param parameters the parameters
	 * @param fullStmts the full stmts
	 */
	protected void makeConstructors(String cuName, List<Parameter> parameters,
			List<Statement> fullStmts) {
		if (makeConstructor && !parameters.isEmpty()) {
			ConstructorDeclaration fullConstructor = NodeFacade
					.ConstructorDeclaration(isEnumeration() ? Modifiers.PRIVATE
							: Modifiers.PUBLIC, cuName);
			fullConstructor.setParameters(NodeFacade.NodeList(parameters));
			fullConstructor.setBlock(NodeFacade.BlockStmt(fullStmts));
			// fullStmts.add(new ExpressionStmt(new MethodCallExpr(null,
			// "this")));
			addMember(fullConstructor);
		}
		if (makeDefaultConstructor && !isEnumeration()) {
			// make default constructor
			ConstructorDeclaration defaultConstructor = NodeFacade
					.ConstructorDeclaration(Modifiers.PUBLIC, cuName);
			List<Statement> defaultStmts = new ArrayList<Statement>();
			defaultConstructor.setBlock(NodeFacade.BlockStmt(defaultStmts));
			defaultStmts.add(NodeFacade.ExpressionStmt(NodeFacade
					.MethodCallExpr(null, "super")));
			addMember(defaultConstructor);
		}
	}

}
