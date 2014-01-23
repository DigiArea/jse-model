package com.digiarea.jse.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.AssignExpr;
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

public abstract class ModelBuilder {

	public static enum BuilderType {
		ANNOTATION, CLASS, INTERFACE, ENUMERATION
	}

	public static class FieldItem {

		private Type fieldType;
		private String fieldName;
		private Expression defaultValue;
		private ModelBuilder builder;
		private boolean withGetter = true;
		private boolean withSetter = true;

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

		public Type getFieldType() {
			return fieldType;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Expression getDefaultValue() {
			return defaultValue;
		}

		public ModelBuilder getBuilder() {
			return builder;
		}

		public boolean isWithGetter() {
			return withGetter;
		}

		public boolean isWithSetter() {
			return withSetter;
		}

	}

	protected NameExpr qualifiedName = null;
	protected ClassOrInterfaceType type = null;
	protected JavadocComment javaDoc = null;
	protected List<AnnotationExpr> annotations = null;
	protected List<BodyDeclaration> members = new ArrayList<BodyDeclaration>();
	protected List<TypeParameter> typeParameters = null;
	protected List<ClassOrInterfaceType> implementsList = null;
	protected ClassOrInterfaceType extendsType = null;
	protected List<ClassOrInterfaceType> extendsList = null;
	protected List<EnumConstantDeclaration> entries = new ArrayList<EnumConstantDeclaration>();
	protected boolean isAbstract = false;
	protected boolean isPrivate = false;
	protected boolean isFinal = false;
	protected boolean isStatic = false;
	protected boolean makeFullConstructor = true;
	protected boolean makeDefaultConstructor = true;
	protected boolean makeConstructor = true;
	protected int modifiers = Modifiers.PUBLIC;

	protected List<FieldItem> fieldItems = new ArrayList<FieldItem>();

	public ModelBuilder(String qualifiedName) {
		super();
		this.qualifiedName = NodeFacade.NameExpr(qualifiedName);
		this.type = NodeFacade.ClassOrInterfaceType(this.qualifiedName);
	}

	public void setMakeDefaultConstructor(boolean makeDefaultConstructor) {
		this.makeDefaultConstructor = makeDefaultConstructor;
	}

	public void setMakeConstructor(boolean makeConstructor) {
		this.makeConstructor = makeConstructor;
	}

	public void setJavaDoc(JavadocComment javaDoc) {
		this.javaDoc = javaDoc;
	}

	public void setAnnotations(List<AnnotationExpr> annotations) {
		this.annotations = annotations;
	}

	public void setTypeParameters(List<TypeParameter> typeParameters) {
		this.typeParameters = typeParameters;
	}

	public void setImplementsList(List<ClassOrInterfaceType> implementsList) {
		this.implementsList = implementsList;
	}

	public void setExtendsType(ClassOrInterfaceType extendsList) {
		this.extendsType = extendsList;
	}

	public void setExtendsList(List<ClassOrInterfaceType> extendsList) {
		this.extendsList = extendsList;
	}

	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dagxp.lmm.jse.builder.ModelBuilder#getQualifiedName()
	 */
	public NameExpr getQualifiedName() {
		return qualifiedName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dagxp.lmm.jse.builder.ModelBuilder#getType()
	 */
	public ClassOrInterfaceType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dagxp.lmm.jse.builder.ModelBuilder#build()
	 */
	public final CompilationUnit build() {
		buildBody();
		TypeDeclaration typeDeclaration = getTypeDeclaration();
		PackageDeclaration pkg = null;
		if (qualifiedName instanceof QualifiedNameExpr) {
			pkg = NodeFacade.PackageDeclaration(
					((QualifiedNameExpr) qualifiedName).getQualifier(), null);
		}
		return NodeFacade.CompilationUnit(pkg, null, Arrays.asList(typeDeclaration),
				null, NodeUtils.toString(qualifiedName), null);
	}

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
				addMember(NodeFacade.createGetterDeclaration(type, name));
			}
			if (item.isWithSetter()) {
				// setter
				addMember(NodeFacade.createSetterDeclaration(type, name));
			}
			// full constructor statements
			fullStmts.add(NodeFacade.ExpressionStmt(
					new AssignExpr(NodeFacade.FieldAccessExpr(
							NodeFacade.ThisExpr(), null, name, null),
							NodeFacade.NameExpr(name), AssignOperator.assign,
							null), null));
		}
		// make constructors
		makeConstructors(cuName, parameters, fullStmts);
	}

	public abstract TypeDeclaration getTypeDeclaration();

	public boolean isInterface() {
		return false;
	}

	public boolean isEnumeration() {
		return false;
	}

	public boolean isAnnotation() {
		return false;
	}

	public boolean isAbstract() {
		return Modifiers.hasModifier(modifiers, Modifiers.ABSTRACT);
	}

	public boolean isPrivate() {
		return Modifiers.hasModifier(modifiers, Modifiers.PRIVATE);
	}

	public boolean isFinal() {
		return Modifiers.hasModifier(modifiers, Modifiers.FINAL);
	}

	public boolean isStatic() {
		return Modifiers.hasModifier(modifiers, Modifiers.STATIC);
	}

	public int getModifiers() {
		return modifiers;
	}

	public Expression getModifiersExpression() {
		return NodeFacade.modifiersToExpression(modifiers);
	}

	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	public void setModifiers(Expression modifiers) {
		this.modifiers = NodeFacade.getModifiers(modifiers);
	}

	public final void addFieldItem(Type fieldType, String fieldName,
			Expression defaultValue) {
		addFieldItem(fieldType, fieldName, defaultValue, true, true);
	}

	public final void addFieldItem(Type fieldType, String fieldName,
			Expression defaultValue, boolean withGetter, boolean withSetter) {
		fieldItems.add(new FieldItem(fieldType, fieldName, defaultValue, this,
				withGetter, withSetter));
	}

	public final void addFieldItem(FieldItem item) {
		fieldItems.add(item);
	}

	public void addMember(BodyDeclaration member) {
		members.add(member);
	}

	public List<BodyDeclaration> getMembers() {
		return members;
	}

	public void addAnnotation(AnnotationExpr annotation) {
		if (annotations == null) {
			annotations = new ArrayList<AnnotationExpr>();
		}
		annotations.add(annotation);
	}

	public void addEnumEntry(EnumConstantDeclaration entry) {
		entries.add(entry);
	}

	public void setEnumEntries(List<String> names) {
		for (Iterator<String> iterator = names.iterator(); iterator.hasNext();) {
			entries.add(NodeFacade.EnumConstantDeclaration(iterator.next()));
		}
	}

	private static final ModelBuilder getBuilderForClass(String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public TypeDeclaration getTypeDeclaration() {
				return NodeFacade.ClassDeclaration(javaDoc, modifiers,
						annotations, qualifiedName.getName(), typeParameters,
						extendsType, implementsList, members);
			}
		};
	}

	private static final ModelBuilder getBuilderForInterface(
			String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public boolean isInterface() {
				return true;
			}

			@Override
			public TypeDeclaration getTypeDeclaration() {
				return NodeFacade.InterfaceDeclaration(javaDoc, modifiers,
						annotations, qualifiedName.getName(), typeParameters,
						extendsList, members);
			}

			@Override
			protected void buildBody() {
				for (FieldItem item : fieldItems) {
					// field is turned out to method declaration
					addMember(NodeFacade.MethodDeclaration(javaDoc, 0, null,
							null, item.getFieldType(), item.getFieldName(),
							null, 0, null, null));
				}
			}
		};
	}

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
				return NodeFacade.EnumDeclaration(javaDoc, modifiers,
						annotations, qualifiedName.getName(), implementsList,
						entries, members);
			}
		};
	}

	private static final ModelBuilder getBuilderForAnnotation(
			String qualifiedName) {
		return new ModelBuilder(qualifiedName) {

			@Override
			public boolean isAnnotation() {
				return true;
			}

			@Override
			public TypeDeclaration getTypeDeclaration() {
				return NodeFacade.AnnotationDeclaration(javaDoc, modifiers,
						annotations, qualifiedName.getName(), members);
			}

			@Override
			protected void buildBody() {
				for (FieldItem item : fieldItems) {
					// annotation member
					addMember(NodeFacade.AnnotationMemberDeclaration(
							Modifiers.PUBLIC, item.getFieldType(),
							item.getFieldName(), item.getDefaultValue()));
				}
			}
		};
	}

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
