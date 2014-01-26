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
package com.digiarea.jse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.PrimitiveType.Primitive;
import com.digiarea.jse.UnaryExpr.UnaryOperator;

/**
 * The Class NodeFacade.
 */
public class NodeFacade extends NodeFactory {

	/** The Constant BYTE_TYPE. */
	public static final PrimitiveType BYTE_TYPE = PrimitiveType(Primitive.Byte);

	/** The Constant SHORT_TYPE. */
	public static final PrimitiveType SHORT_TYPE = PrimitiveType(Primitive.Short);

	/** The Constant INT_TYPE. */
	public static final PrimitiveType INT_TYPE = PrimitiveType(Primitive.Int);

	/** The Constant LONG_TYPE. */
	public static final PrimitiveType LONG_TYPE = PrimitiveType(Primitive.Long);

	/** The Constant FLOAT_TYPE. */
	public static final PrimitiveType FLOAT_TYPE = PrimitiveType(Primitive.Float);

	/** The Constant DOUBLE_TYPE. */
	public static final PrimitiveType DOUBLE_TYPE = PrimitiveType(Primitive.Double);

	/** The Constant BOOLEAN_TYPE. */
	public static final PrimitiveType BOOLEAN_TYPE = PrimitiveType(Primitive.Boolean);

	/** The Constant CHAR_TYPE. */
	public static final PrimitiveType CHAR_TYPE = PrimitiveType(Primitive.Char);

	/** The Constant VOID_TYPE. */
	public static final VoidType VOID_TYPE = VoidType();

	/**
	 * Array initializer expr.
	 * 
	 * @param values
	 *            the values
	 * @param annotations
	 *            the annotations
	 * @return the array initializer expr
	 */
	public static ArrayInitializerExpr ArrayInitializerExpr(
			List<Expression> values, List<AnnotationExpr> annotations) {
		return ArrayInitializerExpr(NodeList(values, null, 0, 0),
				NodeList(annotations, null, 0, 0), 0, 0);
	}

	/**
	 * Object creation expr.
	 * 
	 * @param name
	 *            the name
	 * @param arguments
	 *            the arguments
	 * @return the object creation expr
	 */
	public static ObjectCreationExpr ObjectCreationExpr(String name,
			List<Expression> arguments) {
		return ObjectCreationExpr(null, ClassOrInterfaceType(name), null,
				NodeList(arguments), null, null, 0, 0);
	}

	/**
	 * Object creation expr.
	 * 
	 * @param name
	 *            the name
	 * @param arguments
	 *            the arguments
	 * @param withDiamond
	 *            the with diamond
	 * @return the object creation expr
	 */
	public static ObjectCreationExpr ObjectCreationExpr(String name,
			List<Expression> arguments, boolean withDiamond) {
		if (withDiamond) {
			return ObjectCreationExpr(
					null,
					ClassOrInterfaceType(null, QualifiedNameExpr(name),
							new ArrayList<Type>()), null, NodeList(arguments),
					null, null, 0, 0);
		} else {
			return ObjectCreationExpr(null, ClassOrInterfaceType(name), null,
					NodeList(arguments), null, null, 0, 0);
		}
	}

	/**
	 * Cast expr.
	 * 
	 * @param qName
	 *            the q name
	 * @param img
	 *            the img
	 * @return the cast expr
	 */
	public static CastExpr CastExpr(String qName, Expression img) {
		return CastExpr(NodeList((Type) ClassOrInterfaceType(qName)), img,
				null, 0, 0);
	}

	/**
	 * Field access expr.
	 * 
	 * @param qName
	 *            the q name
	 * @param field
	 *            the field
	 * @return the field access expr
	 */
	public static FieldAccessExpr FieldAccessExpr(String qName, String field) {
		return FieldAccessExpr(QualifiedNameExpr(qName), null, field);
	}

	/**
	 * Boolean literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the boolean literal expr
	 */
	public static BooleanLiteralExpr BooleanLiteralExpr(boolean value) {
		return BooleanLiteralExpr(value, null, 0, 0);
	}

	/**
	 * Integer literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the integer literal expr
	 */
	public static IntegerLiteralExpr IntegerLiteralExpr(int value) {
		return IntegerLiteralExpr(String.valueOf(value));
	}

	/**
	 * Method call expr.
	 * 
	 * @param nodes
	 *            the nodes
	 * @return the method call expr
	 */
	public static MethodCallExpr FacadeMethodCallExpr(List<Expression> nodes) {
		return MethodCallExpr(
				QualifiedNameExpr(QualifiedNameExpr(NameExpr("java"), "util"),
						"Arrays"), null, "asList", nodes);
	}

	/**
	 * Class expr.
	 * 
	 * @param predicate
	 *            the predicate
	 * @return the class expr
	 */
	public static ClassExpr ClassExpr(Class<?> predicate) {
		return ClassExpr(ClassOrInterfaceType(predicate.getName()));
	}

	/**
	 * Javadoc comment.
	 * 
	 * @param context
	 *            the context
	 * @return the javadoc comment
	 */
	public static JavadocComment JavadocComment(String context) {
		return JavadocComment(context, null, 0, 0);
	}

	/**
	 * Line comment.
	 * 
	 * @param context
	 *            the context
	 * @return the line comment
	 */
	public static LineComment LineComment(String context) {
		return LineComment(context, null, 0, 0);
	}

	/**
	 * Block comment.
	 * 
	 * @param context
	 *            the context
	 * @return the block comment
	 */
	public static BlockComment BlockComment(String context) {
		return BlockComment(context, null, 0, 0);
	}

	/**
	 * Class or interface type.
	 * 
	 * @param nameExpr
	 *            the name expr
	 * @return the class or interface type
	 */
	public static ClassOrInterfaceType ClassOrInterfaceType(NameExpr nameExpr) {
		return ClassOrInterfaceType(null, nameExpr);
	}

	/**
	 * Class or interface type.
	 * 
	 * @param qName
	 *            the q name
	 * @return the class or interface type
	 */
	public static ClassOrInterfaceType ClassOrInterfaceType(String qName) {
		return ClassOrInterfaceType(QualifiedNameExpr(qName));
	}

	/**
	 * Class or interface type.
	 * 
	 * @param classOrInterfaceType
	 *            the class or interface type
	 * @param nameExpr
	 *            the name expr
	 * @return the class or interface type
	 */
	public static ClassOrInterfaceType ClassOrInterfaceType(
			ClassOrInterfaceType classOrInterfaceType, NameExpr nameExpr) {
		return ClassOrInterfaceType(classOrInterfaceType, nameExpr, null);
	}

	/**
	 * Qualified name expr.
	 * 
	 * @param name
	 *            the name
	 * @return the name expr
	 */
	public static NameExpr QualifiedNameExpr(String name) {
		String[] split = name.split("\\.");
		NameExpr ret = NameExpr(split[0]);
		for (int i = 1; i < split.length; i++) {
			ret = QualifiedNameExpr(ret, split[i]);
		}
		return ret;
	}

	/**
	 * Name expr.
	 * 
	 * @param name
	 *            the name
	 * @return the name expr
	 */
	public static NameExpr NameExpr(String name) {
		return NameExpr(name, null, 0, 0);
	}

	/**
	 * Compilation unit.
	 * 
	 * @param pakage
	 *            the pakage
	 * @param imports
	 *            the imports
	 * @param types
	 *            the types
	 * @param comments
	 *            the comments
	 * @param name
	 *            the name
	 * @return the compilation unit
	 */
	public static CompilationUnit CompilationUnit(PackageDeclaration pakage,
			List<ImportDeclaration> imports, List<TypeDeclaration> types,
			List<Comment> comments, String name) {
		return CompilationUnit(pakage, NodeList(imports), NodeList(types),
				NodeList(comments), name, null, 0, 0);
	}

	/**
	 * Package declaration.
	 * 
	 * @param name
	 *            the name
	 * @param annotations
	 *            the annotations
	 * @return the package declaration
	 */
	public static PackageDeclaration PackageDeclaration(NameExpr name,
			List<AnnotationExpr> annotations) {
		return PackageDeclaration(name, NodeList(annotations), 0, 0);
	}

	/**
	 * Import declaration.
	 * 
	 * @param name
	 *            the name
	 * @param isStatic
	 *            the is static
	 * @param isAsterisk
	 *            the is asterisk
	 * @return the import declaration
	 */
	public static ImportDeclaration ImportDeclaration(NameExpr name,
			boolean isStatic, boolean isAsterisk) {
		return ImportDeclaration(name, isStatic, isAsterisk, null, 0, 0);
	}

	/**
	 * Empty type declaration.
	 * 
	 * @param javaDoc
	 *            the java doc
	 * @return the empty type declaration
	 */
	public static EmptyTypeDeclaration EmptyTypeDeclaration(
			JavadocComment javaDoc) {
		return EmptyTypeDeclaration(null, null, null, javaDoc, null, 0, 0);
	}

	/**
	 * Class declaration.
	 * 
	 * @param typePar
	 *            the type par
	 * @param extendsType
	 *            the extends type
	 * @param impList
	 *            the imp list
	 * @param modifiers
	 *            the modifiers
	 * @param name
	 *            the name
	 * @param members
	 *            the members
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the class declaration
	 */
	public static ClassDeclaration ClassDeclaration(
			List<TypeParameter> typePar, ClassOrInterfaceType extendsType,
			List<ClassOrInterfaceType> impList, int modifiers, String name,
			List<BodyDeclaration> members, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return ClassDeclaration(NodeList(typePar), extendsType,
				NodeList(impList), Modifiers(modifiers), name,
				NodeList(members), javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * Modifiers.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @return the modifiers
	 */
	public static Modifiers Modifiers(int modifiers) {
		return Modifiers(modifiers, null, 0, 0);
	}

	/**
	 * Interface declaration.
	 * 
	 * @param typePar
	 *            the type par
	 * @param extList
	 *            the ext list
	 * @param modifiers
	 *            the modifiers
	 * @param name
	 *            the name
	 * @param members
	 *            the members
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the interface declaration
	 */
	public static InterfaceDeclaration InterfaceDeclaration(
			List<TypeParameter> typePar, List<ClassOrInterfaceType> extList,
			int modifiers, String name, List<BodyDeclaration> members,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return InterfaceDeclaration(NodeList(typePar), NodeList(extList),
				Modifiers(modifiers), name, NodeList(members), javaDoc,
				NodeList(annotations), 0, 0);
	}

	/**
	 * Enum declaration.
	 * 
	 * @param impList
	 *            the imp list
	 * @param entries
	 *            the entries
	 * @param modifiers
	 *            the modifiers
	 * @param name
	 *            the name
	 * @param members
	 *            the members
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the enum declaration
	 */
	public static EnumDeclaration EnumDeclaration(
			List<ClassOrInterfaceType> impList,
			List<EnumConstantDeclaration> entries, int modifiers, String name,
			List<BodyDeclaration> members, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return EnumDeclaration(NodeList(impList), NodeList(entries),
				Modifiers(modifiers), name, NodeList(members), javaDoc,
				NodeList(annotations), 0, 0);
	}

	/**
	 * Enum constant declaration.
	 * 
	 * @param name
	 *            the name
	 * @param args
	 *            the args
	 * @param classBody
	 *            the class body
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the enum constant declaration
	 */
	public static EnumConstantDeclaration EnumConstantDeclaration(String name,
			List<Expression> args, List<BodyDeclaration> classBody,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return EnumConstantDeclaration(name, NodeList(args),
				NodeList(classBody), javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * Type parameter.
	 * 
	 * @param name
	 *            the name
	 * @param typeBound
	 *            the type bound
	 * @return the type parameter
	 */
	public static TypeParameter TypeParameter(String name,
			List<ClassOrInterfaceType> typeBound) {
		return TypeParameter(name, NodeList(typeBound), null, 0, 0);
	}

	/**
	 * Empty member declaration.
	 * 
	 * @param javaDoc
	 *            the java doc
	 * @return the body declaration
	 */
	public static BodyDeclaration EmptyMemberDeclaration(JavadocComment javaDoc) {
		return EmptyMemberDeclaration(javaDoc, null, 0, 0);
	}

	/**
	 * Field declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param type
	 *            the type
	 * @param variables
	 *            the variables
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the field declaration
	 */
	public static FieldDeclaration FieldDeclaration(int modifiers, Type type,
			List<VariableDeclarator> variables, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return FieldDeclaration(Modifiers(modifiers), type,
				NodeList(variables), javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * Variable declarator.
	 * 
	 * @param id
	 *            the id
	 * @param init
	 *            the init
	 * @return the variable declarator
	 */
	public static VariableDeclarator VariableDeclarator(
			VariableDeclaratorId id, Expression init) {
		return VariableDeclarator(id, init, null, 0, 0);
	}

	/**
	 * Array initializer expr.
	 * 
	 * @param values
	 *            the values
	 * @return the array initializer expr
	 */
	public static ArrayInitializerExpr ArrayInitializerExpr(
			List<Expression> values) {
		return ArrayInitializerExpr(NodeList(values), null, 0, 0);
	}

	/**
	 * Method declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param typeParameters
	 *            the type parameters
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param parameters
	 *            the parameters
	 * @param slots
	 *            the slots
	 * @param throwsList
	 *            the throws list
	 * @param body
	 *            the body
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the method declaration
	 */
	public static MethodDeclaration MethodDeclaration(int modifiers,
			List<TypeParameter> typeParameters, Type type, String name,
			List<Parameter> parameters, List<ArraySlot> slots,
			List<ClassOrInterfaceType> throwsList, BlockStmt body,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return MethodDeclaration(Modifiers(modifiers),
				NodeList(typeParameters), type, name, null, null,
				NodeList(parameters), NodeList(slots), NodeList(throwsList),
				body, javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * Method declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param typeParameters
	 *            the type parameters
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param receiverType
	 *            the receiver type
	 * @param receiverQualifier
	 *            the receiver qualifier
	 * @param parameters
	 *            the parameters
	 * @param slots
	 *            the slots
	 * @param throwsList
	 *            the throws list
	 * @param body
	 *            the body
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @param posBegin
	 *            the pos begin
	 * @param posEnd
	 *            the pos end
	 * @return the method declaration
	 */
	public static MethodDeclaration MethodDeclaration(int modifiers,
			List<TypeParameter> typeParameters, Type type, String name,
			Type receiverType, NameExpr receiverQualifier,
			List<Parameter> parameters, List<ArraySlot> slots,
			List<ClassOrInterfaceType> throwsList, BlockStmt body,
			JavadocComment javaDoc, List<AnnotationExpr> annotations,
			int posBegin, int posEnd) {
		return MethodDeclaration(Modifiers(modifiers),
				NodeList(typeParameters), type, name, receiverType,
				receiverQualifier, NodeList(parameters), NodeList(slots),
				NodeList(throwsList), body, javaDoc, NodeList(annotations),
				posBegin, posEnd);
	}

	/**
	 * Block stmt.
	 * 
	 * @param stmts
	 *            the stmts
	 * @return the block stmt
	 */
	public static BlockStmt BlockStmt(List<Statement> stmts) {
		return BlockStmt(NodeList(stmts), null, 0, 0);
	}

	/**
	 * Constructor declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param typeParameters
	 *            the type parameters
	 * @param name
	 *            the name
	 * @param parameters
	 *            the parameters
	 * @param throwsList
	 *            the throws list
	 * @param blockStmt
	 *            the block stmt
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the constructor declaration
	 */
	public static ConstructorDeclaration ConstructorDeclaration(int modifiers,
			List<TypeParameter> typeParameters, String name,
			List<Parameter> parameters, List<ClassOrInterfaceType> throwsList,
			BlockStmt blockStmt, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return ConstructorDeclaration(Modifiers(modifiers),
				NodeList(typeParameters), name, null, null,
				NodeList(parameters), NodeList(throwsList), blockStmt, javaDoc,
				NodeList(annotations), 0, 0);
	}

	/**
	 * Constructor declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param typeParameters
	 *            the type parameters
	 * @param name
	 *            the name
	 * @param receiverType
	 *            the receiver type
	 * @param receiverQualifier
	 *            the receiver qualifier
	 * @param parameters
	 *            the parameters
	 * @param throwsList
	 *            the throws list
	 * @param blockStmt
	 *            the block stmt
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @param posBegin
	 *            the pos begin
	 * @param posEnd
	 *            the pos end
	 * @return the constructor declaration
	 */
	public static ConstructorDeclaration ConstructorDeclaration(int modifiers,
			List<TypeParameter> typeParameters, String name, Type receiverType,
			NameExpr receiverQualifier, List<Parameter> parameters,
			List<ClassOrInterfaceType> throwsList, BlockStmt blockStmt,
			JavadocComment javaDoc, List<AnnotationExpr> annotations,
			int posBegin, int posEnd) {
		return ConstructorDeclaration(Modifiers(modifiers),
				NodeList(typeParameters), name, receiverType,
				receiverQualifier, NodeList(parameters), NodeList(throwsList),
				blockStmt, javaDoc, NodeList(annotations), posBegin, posEnd);
	}

	/**
	 * Explicit constructor invocation stmt.
	 * 
	 * @param typeArgs
	 *            the type args
	 * @param isThis
	 *            the is this
	 * @param expr
	 *            the expr
	 * @param args
	 *            the args
	 * @return the explicit constructor invocation stmt
	 */
	public static ExplicitConstructorInvocationStmt ExplicitConstructorInvocationStmt(
			List<Type> typeArgs, boolean isThis, Expression expr,
			List<Expression> args) {
		return ExplicitConstructorInvocationStmt(NodeList(typeArgs), isThis,
				null, NodeList(args), null, 0, 0);
	}

	/**
	 * Initializer declaration.
	 * 
	 * @param isStatic
	 *            the is static
	 * @param block
	 *            the block
	 * @param javaDoc
	 *            the java doc
	 * @return the initializer declaration
	 */
	public static InitializerDeclaration InitializerDeclaration(
			boolean isStatic, BlockStmt block, JavadocComment javaDoc) {
		return InitializerDeclaration(isStatic, block, javaDoc, null, 0, 0);
	}

	/**
	 * Class or interface type.
	 * 
	 * @param scope
	 *            the scope
	 * @param name
	 *            the name
	 * @param typeArgs
	 *            the type args
	 * @return the class or interface type
	 */
	public static ClassOrInterfaceType ClassOrInterfaceType(
			ClassOrInterfaceType scope, NameExpr name, List<Type> typeArgs) {
		return ClassOrInterfaceType(scope, name, NodeList(typeArgs), null);
	}

	/**
	 * Wildcard type.
	 * 
	 * @param extendsType
	 *            the extends type
	 * @param superType
	 *            the super type
	 * @return the wildcard type
	 */
	public static WildcardType WildcardType(ReferenceType extendsType,
			ReferenceType superType) {
		return WildcardType(extendsType, superType, null, 0, 0);
	}

	/**
	 * Primitive type.
	 * 
	 * @param type
	 *            the type
	 * @return the primitive type
	 */
	public static PrimitiveType PrimitiveType(Primitive type) {
		return PrimitiveType(type, null, 0, 0);
	}

	/**
	 * Qualified name expr.
	 * 
	 * @param qualifier
	 *            the qualifier
	 * @param name
	 *            the name
	 * @return the qualified name expr
	 */
	public static QualifiedNameExpr QualifiedNameExpr(NameExpr qualifier,
			String name) {
		return QualifiedNameExpr(qualifier, name, null, 0, 0);
	}

	/**
	 * Assign expr.
	 * 
	 * @param target
	 *            the target
	 * @param value
	 *            the value
	 * @param operator
	 *            the operator
	 * @return the assign expr
	 */
	public static AssignExpr AssignExpr(Expression target, Expression value,
			AssignOperator operator) {
		return AssignExpr(target, value, operator, null, 0, 0);
	}

	/**
	 * Conditional expr.
	 * 
	 * @param condition
	 *            the condition
	 * @param thenExpression
	 *            the then expression
	 * @param elseExpression
	 *            the else expression
	 * @return the conditional expr
	 */
	public static ConditionalExpr ConditionalExpr(Expression condition,
			Expression thenExpression, Expression elseExpression) {
		return ConditionalExpr(condition, thenExpression, elseExpression, null,
				0, 0);
	}

	/**
	 * Binary expr.
	 * 
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 * @param operator
	 *            the operator
	 * @return the binary expr
	 */
	public static BinaryExpr BinaryExpr(Expression left, Expression right,
			BinaryOperator operator) {
		return BinaryExpr(left, right, operator, null, 0, 0);
	}

	/**
	 * Instance of expr.
	 * 
	 * @param expression
	 *            the expression
	 * @param type
	 *            the type
	 * @return the instance of expr
	 */
	public static InstanceOfExpr InstanceOfExpr(Expression expression, Type type) {
		return InstanceOfExpr(expression, type, null, 0, 0);
	}

	/**
	 * Unary expr.
	 * 
	 * @param expression
	 *            the expression
	 * @param operator
	 *            the operator
	 * @return the unary expr
	 */
	public static UnaryExpr UnaryExpr(Expression expression,
			UnaryOperator operator) {
		return UnaryExpr(expression, operator, null, 0, 0);
	}

	/**
	 * Cast expr.
	 * 
	 * @param type
	 *            the type
	 * @param expression
	 *            the expression
	 * @return the cast expr
	 */
	public static CastExpr CastExpr(Type type, Expression expression) {
		return CastExpr(NodeList(type), expression, null, 0, 0);
	}

	/**
	 * Field access expr.
	 * 
	 * @param scope
	 *            the scope
	 * @param typeArgs
	 *            the type args
	 * @param field
	 *            the field
	 * @return the field access expr
	 */
	public static FieldAccessExpr FieldAccessExpr(Expression scope,
			List<Type> typeArgs, String field) {
		return FieldAccessExpr(scope, NodeList(typeArgs), field, null, 0, 0);
	}

	/**
	 * Method call expr.
	 * 
	 * @param scope
	 *            the scope
	 * @param typeArgs
	 *            the type args
	 * @param name
	 *            the name
	 * @param args
	 *            the args
	 * @return the method call expr
	 */
	public static MethodCallExpr MethodCallExpr(Expression scope,
			List<Type> typeArgs, String name, List<Expression> args) {
		return MethodCallExpr(scope, NodeList(typeArgs), name, NodeList(args),
				null, 0, 0);
	}

	/**
	 * Method call expr.
	 * 
	 * @param name
	 *            the name
	 * @param args
	 *            the args
	 * @return the method call expr
	 */
	public static MethodCallExpr MethodCallExpr(String name,
			List<Expression> args) {
		return MethodCallExpr(null, null, name, NodeList(args), null, 0, 0);
	}

	/**
	 * Facade method call expr.
	 *
	 * @param name the name
	 * @param args the args
	 * @return the method call expr
	 */
	public static MethodCallExpr FacadeMethodCallExpr(String name,
			List<Expression> args) {
		QualifiedNameExpr qName = QualifiedNameExpr(
				QualifiedNameExpr(
						QualifiedNameExpr(NameExpr("com"), "digiarea"), "jse"),
				"NodeFacade");
		return MethodCallExpr(qName, null, name, NodeList(args), null, 0, 0);
	}

	/**
	 * Enclosed expr.
	 * 
	 * @param inner
	 *            the inner
	 * @return the enclosed expr
	 */
	public static EnclosedExpr EnclosedExpr(Expression inner) {
		return EnclosedExpr(inner, null, 0, 0);
	}

	/**
	 * Class expr.
	 * 
	 * @param type
	 *            the type
	 * @return the class expr
	 */
	public static ClassExpr ClassExpr(Type type) {
		return ClassExpr(type, null, 0, 0);
	}

	/**
	 * Super expr.
	 * 
	 * @param classExpression
	 *            the class expression
	 * @return the super expr
	 */
	public static SuperExpr SuperExpr(Expression classExpression) {
		return SuperExpr(classExpression, null, 0, 0);
	}

	/**
	 * This expr.
	 * 
	 * @param classExpression
	 *            the class expression
	 * @return the this expr
	 */
	public static ThisExpr ThisExpr(Expression classExpression) {
		return ThisExpr(classExpression, null, 0, 0);
	}

	/**
	 * Array access expr.
	 * 
	 * @param name
	 *            the name
	 * @param index
	 *            the index
	 * @return the array access expr
	 */
	public static ArrayAccessExpr ArrayAccessExpr(Expression name,
			Expression index) {
		return ArrayAccessExpr(name, index, null, 0, 0);
	}

	/**
	 * Integer literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the integer literal expr
	 */
	public static IntegerLiteralExpr IntegerLiteralExpr(String value) {
		return IntegerLiteralExpr(value, null, 0, 0);
	}

	/**
	 * Long literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the long literal expr
	 */
	public static LongLiteralExpr LongLiteralExpr(String value) {
		return LongLiteralExpr(value, null, 0, 0);
	}

	/**
	 * Double literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the double literal expr
	 */
	public static DoubleLiteralExpr DoubleLiteralExpr(String value) {
		return DoubleLiteralExpr(value, null, 0, 0);
	}

	/**
	 * Char literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the char literal expr
	 */
	public static CharLiteralExpr CharLiteralExpr(String value) {
		return CharLiteralExpr(value, null, 0, 0);
	}

	/**
	 * Object creation expr.
	 * 
	 * @param scope
	 *            the scope
	 * @param type
	 *            the type
	 * @param typeArgs
	 *            the type args
	 * @param args
	 *            the args
	 * @param anonymousClassBody
	 *            the anonymous class body
	 * @return the object creation expr
	 */
	public static ObjectCreationExpr ObjectCreationExpr(Expression scope,
			ClassOrInterfaceType type, List<Type> typeArgs,
			List<Expression> args, List<BodyDeclaration> anonymousClassBody) {
		return ObjectCreationExpr(scope, type, NodeList(typeArgs),
				NodeList(args), NodeList(anonymousClassBody), null, 0, 0);
	}

	/**
	 * Assert stmt.
	 * 
	 * @param check
	 *            the check
	 * @param message
	 *            the message
	 * @return the assert stmt
	 */
	public static AssertStmt AssertStmt(Expression check, Expression message) {
		return AssertStmt(check, message, null, 0, 0);
	}

	/**
	 * Labeled stmt.
	 * 
	 * @param label
	 *            the label
	 * @param stmt
	 *            the stmt
	 * @return the labeled stmt
	 */
	public static LabeledStmt LabeledStmt(String label, Statement stmt) {
		return LabeledStmt(label, stmt, null, 0, 0);
	}

	/**
	 * Type declaration stmt.
	 * 
	 * @param typeDeclaration
	 *            the type declaration
	 * @return the type declaration stmt
	 */
	public static TypeDeclarationStmt TypeDeclarationStmt(
			TypeDeclaration typeDeclaration) {
		return TypeDeclarationStmt(typeDeclaration, null, 0, 0);
	}

	/**
	 * Expression stmt.
	 * 
	 * @param expression
	 *            the expression
	 * @return the expression stmt
	 */
	public static ExpressionStmt ExpressionStmt(Expression expression) {
		return ExpressionStmt(expression, null, 0, 0);
	}

	/**
	 * Variable declaration expr.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param type
	 *            the type
	 * @param vars
	 *            the vars
	 * @param annotations
	 *            the annotations
	 * @return the variable declaration expr
	 */
	public static VariableDeclarationExpr VariableDeclarationExpr(
			int modifiers, Type type, List<VariableDeclarator> vars,
			List<AnnotationExpr> annotations) {
		return VariableDeclarationExpr(Modifiers(modifiers), type,
				NodeList(vars), NodeList(annotations), 0, 0);
	}

	/**
	 * Switch stmt.
	 * 
	 * @param selector
	 *            the selector
	 * @param entries
	 *            the entries
	 * @return the switch stmt
	 */
	public static SwitchStmt SwitchStmt(Expression selector,
			List<SwitchEntryStmt> entries) {
		return SwitchStmt(selector, NodeList(entries), null, 0, 0);
	}

	/**
	 * Switch entry stmt.
	 * 
	 * @param label
	 *            the label
	 * @param stmts
	 *            the stmts
	 * @return the switch entry stmt
	 */
	public static SwitchEntryStmt SwitchEntryStmt(Expression label,
			List<Statement> stmts) {
		return SwitchEntryStmt(label, NodeList(stmts), null, 0, 0);
	}

	/**
	 * If stmt.
	 * 
	 * @param condition
	 *            the condition
	 * @param thenStmt
	 *            the then stmt
	 * @param elseStmt
	 *            the else stmt
	 * @return the if stmt
	 */
	public static IfStmt IfStmt(Expression condition, Statement thenStmt,
			Statement elseStmt) {
		return IfStmt(condition, thenStmt, elseStmt, null, 0, 0);
	}

	/**
	 * While stmt.
	 * 
	 * @param condition
	 *            the condition
	 * @param body
	 *            the body
	 * @return the while stmt
	 */
	public static WhileStmt WhileStmt(Expression condition, Statement body) {
		return WhileStmt(condition, body, null, 0, 0);
	}

	/**
	 * Do stmt.
	 * 
	 * @param body
	 *            the body
	 * @param condition
	 *            the condition
	 * @return the do stmt
	 */
	public static DoStmt DoStmt(Statement body, Expression condition) {
		return DoStmt(body, condition, null, 0, 0);
	}

	/**
	 * Foreach stmt.
	 * 
	 * @param variable
	 *            the variable
	 * @param iterable
	 *            the iterable
	 * @param body
	 *            the body
	 * @return the statement
	 */
	public static ForeachStmt ForeachStmt(VariableDeclarationExpr variable,
			Expression iterable, Statement body) {
		return ForeachStmt(variable, iterable, body, null, 0, 0);
	}

	/**
	 * For stmt.
	 * 
	 * @param init
	 *            the init
	 * @param compare
	 *            the compare
	 * @param update
	 *            the update
	 * @param body
	 *            the body
	 * @return the statement
	 */
	public static Statement ForStmt(List<Expression> init, Expression compare,
			List<Expression> update, Statement body) {
		return ForStmt(NodeList(init), compare, NodeList(update), body, null,
				0, 0);
	}

	/**
	 * Break stmt.
	 * 
	 * @param id
	 *            the id
	 * @return the break stmt
	 */
	public static BreakStmt BreakStmt(String id) {
		return BreakStmt(id, null, 0, 0);
	}

	/**
	 * Continue stmt.
	 * 
	 * @param id
	 *            the id
	 * @return the continue stmt
	 */
	public static ContinueStmt ContinueStmt(String id) {
		return ContinueStmt(id, null, 0, 0);
	}

	/**
	 * Return stmt.
	 * 
	 * @param expression
	 *            the expression
	 * @return the return stmt
	 */
	public static ReturnStmt ReturnStmt(Expression expression) {
		return ReturnStmt(expression, null, 0, 0);
	}

	/**
	 * Throw stmt.
	 * 
	 * @param expression
	 *            the expression
	 * @return the throw stmt
	 */
	public static ThrowStmt ThrowStmt(Expression expression) {
		return ThrowStmt(expression, null, 0, 0);
	}

	/**
	 * Synchronized stmt.
	 * 
	 * @param expression
	 *            the expression
	 * @param block
	 *            the block
	 * @return the synchronized stmt
	 */
	public static SynchronizedStmt SynchronizedStmt(Expression expression,
			BlockStmt block) {
		return SynchronizedStmt(expression, block, null, 0, 0);
	}

	/**
	 * Catch clause.
	 * 
	 * @param isFinal
	 *            the is final
	 * @param types
	 *            the types
	 * @param name
	 *            the name
	 * @param catchBlock
	 *            the catch block
	 * @return the catch clause
	 */
	public static CatchClause CatchClause(boolean isFinal, List<Type> types,
			String name, BlockStmt catchBlock) {
		return CatchClause(isFinal, NodeList(types), name, catchBlock, null, 0,
				0);
	}

	/**
	 * Try stmt.
	 * 
	 * @param resources
	 *            the resources
	 * @param tryBlock
	 *            the try block
	 * @param catchClauses
	 *            the catch clauses
	 * @param finallyBlock
	 *            the finally block
	 * @return the try stmt
	 */
	public static TryStmt TryStmt(List<VariableDeclarationExpr> resources,
			BlockStmt tryBlock, List<CatchClause> catchClauses,
			BlockStmt finallyBlock) {
		return TryStmt(NodeList(resources), tryBlock, NodeList(catchClauses),
				finallyBlock, null, 0, 0);
	}

	/**
	 * Normal annotation expr.
	 * 
	 * @param pairs
	 *            the pairs
	 * @param name
	 *            the name
	 * @return the normal annotation expr
	 */
	public static NormalAnnotationExpr NormalAnnotationExpr(
			List<MemberValuePair> pairs, NameExpr name) {
		return NormalAnnotationExpr(NodeList(pairs), name, null, 0, 0);
	}

	/**
	 * Marker annotation expr.
	 * 
	 * @param name
	 *            the name
	 * @return the marker annotation expr
	 */
	public static MarkerAnnotationExpr MarkerAnnotationExpr(NameExpr name) {
		return MarkerAnnotationExpr(name, null, 0, 0);
	}

	/**
	 * Single member annotation expr.
	 * 
	 * @param memberValue
	 *            the member value
	 * @param name
	 *            the name
	 * @return the single member annotation expr
	 */
	public static SingleMemberAnnotationExpr SingleMemberAnnotationExpr(
			Expression memberValue, NameExpr name) {
		return SingleMemberAnnotationExpr(memberValue, name, null, 0, 0);
	}

	/**
	 * Member value pair.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @return the member value pair
	 */
	public static MemberValuePair MemberValuePair(String name, Expression value) {
		return MemberValuePair(name, value, null, 0, 0);
	}

	/**
	 * Annotation declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param name
	 *            the name
	 * @param members
	 *            the members
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the annotation declaration
	 */
	public static AnnotationDeclaration AnnotationDeclaration(int modifiers,
			String name, List<BodyDeclaration> members, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return AnnotationDeclaration(Modifiers(modifiers), name,
				NodeList(members), javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * Annotation member declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param defaultValue
	 *            the default value
	 * @param javaDoc
	 *            the java doc
	 * @param annotations
	 *            the annotations
	 * @return the annotation member declaration
	 */
	public static AnnotationMemberDeclaration AnnotationMemberDeclaration(
			int modifiers, Type type, String name, Expression defaultValue,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return AnnotationMemberDeclaration(Modifiers(modifiers), type, name,
				defaultValue, javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * This method return null when nodes equals null.
	 * 
	 * @param <E>
	 *            the element type
	 * @param nodes
	 *            the nodes
	 * @return the node list
	 */
	public static <E extends Node> NodeList<E> NodeList(List<E> nodes) {
		if (nodes != null) {
			return NodeList(nodes, null, 0, 0);
		}
		return null;
	}

	/**
	 * This method return null when node equals null.
	 * 
	 * @param <E>
	 *            the element type
	 * @param node
	 *            the node
	 * @return the node list
	 */
	public static <E extends Node> NodeList<E> NodeList(E node) {
		if (node != null) {
			return NodeList(Arrays.asList(node), null, 0, 0);
		}
		return null;
	}

	/**
	 * This method return null when node equals null.
	 * 
	 * @param <E>
	 *            the element type
	 * @param node
	 *            the node
	 * @return the node list
	 */
	public static <E extends Node> NodeList<E> NodeList(E[] node) {
		if (node != null) {
			return NodeList(Arrays.asList(node), null, 0, 0);
		}
		return null;
	}

	/**
	 * Cast expr.
	 * 
	 * @param types
	 *            the types
	 * @param expression
	 *            the expression
	 * @return the expression
	 */
	public static Expression CastExpr(List<Type> types, Expression expression) {
		return CastExpr(NodeList(types), expression, null, 0, 0);
	}

	/**
	 * Type method reference.
	 * 
	 * @param type
	 *            the type
	 * @param typeArgs
	 *            the type args
	 * @param methodName
	 *            the method name
	 * @return the type method reference
	 */
	public static TypeMethodReference TypeMethodReference(Type type,
			List<Type> typeArgs, String methodName) {
		return TypeMethodReference(type, methodName, NodeList(typeArgs), null,
				0, 0);
	}

	/**
	 * Expression method reference.
	 * 
	 * @param scope
	 *            the scope
	 * @param typeArgs
	 *            the type args
	 * @param methodName
	 *            the method name
	 * @return the expression method reference
	 */
	public static ExpressionMethodReference ExpressionMethodReference(
			Expression scope, List<Type> typeArgs, String methodName) {
		return ExpressionMethodReference(scope, methodName, NodeList(typeArgs),
				null, 0, 0);
	}

	/**
	 * Super method reference.
	 * 
	 * @param scope
	 *            the scope
	 * @param typeArgs
	 *            the type args
	 * @param methodName
	 *            the method name
	 * @return the super method reference
	 */
	public static SuperMethodReference SuperMethodReference(Expression scope,
			List<Type> typeArgs, String methodName) {
		return SuperMethodReference((NameExpr) scope, methodName,
				NodeList(typeArgs), null, 0, 0);
	}

	/**
	 * Creation reference.
	 * 
	 * @param scope
	 *            the scope
	 * @param typeArgs
	 *            the type args
	 * @return the creation reference
	 */
	public static CreationReference CreationReference(Expression scope,
			List<Type> typeArgs) {
		return CreationReference(ClassOrInterfaceType((NameExpr) scope),
				NodeList(typeArgs), null, 0, 0);
	}

	/**
	 * Creation reference.
	 * 
	 * @param type
	 *            the type
	 * @param typeArgs
	 *            the type args
	 * @return the creation reference
	 */
	public static CreationReference CreationReference(Type type,
			List<Type> typeArgs) {
		return CreationReference(type, NodeList(typeArgs), null, 0, 0);
	}

	/**
	 * Type parameter.
	 * 
	 * @param name
	 *            the name
	 * @param typeBound
	 *            the type bound
	 * @param annotations
	 *            the annotations
	 * @return the type parameter
	 */
	public static TypeParameter TypeParameter(String name,
			List<ClassOrInterfaceType> typeBound,
			List<AnnotationExpr> annotations) {
		return TypeParameter(name, NodeList(typeBound), NodeList(annotations),
				0, 0);
	}

	/**
	 * Class or interface type.
	 * 
	 * @param scope
	 *            the scope
	 * @param name
	 *            the name
	 * @param typeArgs
	 *            the type args
	 * @param annotations
	 *            the annotations
	 * @return the class or interface type
	 */
	public static ClassOrInterfaceType ClassOrInterfaceType(
			ClassOrInterfaceType scope, NameExpr name, List<Type> typeArgs,
			List<AnnotationExpr> annotations) {
		return ClassOrInterfaceType(scope, name, NodeList(typeArgs),
				NodeList(annotations), 0, 0);
	}

	/**
	 * Array slot.
	 * 
	 * @param annotations
	 *            the annotations
	 * @return the array slot
	 */
	public static ArraySlot ArraySlot(List<AnnotationExpr> annotations) {
		return ArraySlot(null, NodeList(annotations), 0, 0);
	}

	/**
	 * Reference type.
	 * 
	 * @param type
	 *            the type
	 * @param slots
	 *            the slots
	 * @return the reference type
	 */
	public static ReferenceType ReferenceType(Type type, List<ArraySlot> slots) {
		return ReferenceType(type, NodeList(slots), null, 0, 0);
	}

	/**
	 * Ellipsis.
	 * 
	 * @param annotations
	 *            the annotations
	 * @return the ellipsis
	 */
	public static Ellipsis Ellipsis(List<AnnotationExpr> annotations) {
		return Ellipsis(NodeList(annotations), 0, 0);
	}

	/**
	 * Parameter.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param type
	 *            the type
	 * @param ellipsis
	 *            the ellipsis
	 * @param id
	 *            the id
	 * @param annotations
	 *            the annotations
	 * @return the parameter
	 */
	public static Parameter Parameter(int modifiers, Type type,
			Ellipsis ellipsis, VariableDeclaratorId id,
			List<AnnotationExpr> annotations) {
		return Parameter(Modifiers(modifiers), type, ellipsis, id,
				NodeList(annotations), 0, 0);
	}

	/**
	 * Parameter.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @return the parameter
	 */
	public static Parameter Parameter(Type type, String name) {
		return Parameter(0, type, null, VariableDeclaratorId(name, null), null);
	}

	/**
	 * Array creation expr.
	 * 
	 * @param type
	 *            the type
	 * @param slots
	 *            the slots
	 * @param initializer
	 *            the initializer
	 * @return the array creation expr
	 */
	public static ArrayCreationExpr ArrayCreationExpr(Type type,
			List<ArraySlot> slots, ArrayInitializerExpr initializer) {
		return ArrayCreationExpr(type, NodeList(slots), initializer, null, 0, 0);
	}

	/**
	 * Array slot.
	 * 
	 * @param expression
	 *            the expression
	 * @return the array slot
	 */
	public static ArraySlot ArraySlot(Expression expression) {
		return ArraySlot(expression, null, 0, 0);
	}

	/**
	 * Array slot.
	 * 
	 * @param expression
	 *            the expression
	 * @param annotations
	 *            the annotations
	 * @return the array slot
	 */
	public static ArraySlot ArraySlot(Expression expression,
			List<AnnotationExpr> annotations) {
		return ArraySlot(expression, NodeList(annotations), 0, 0);
	}

	/**
	 * Variable declarator id.
	 * 
	 * @param name
	 *            the name
	 * @param slots
	 *            the slots
	 * @return the variable declarator id
	 */
	public static VariableDeclaratorId VariableDeclaratorId(String name,
			List<ArraySlot> slots) {
		return VariableDeclaratorId(name, NodeList(slots), null, 0, 0);
	}

	/**
	 * String literal expr.
	 * 
	 * @param value
	 *            the value
	 * @return the string literal expr
	 */
	public static StringLiteralExpr StringLiteralExpr(String value) {
		return StringLiteralExpr(value, null, 0, 0);
	}

	/**
	 * Lambda block.
	 * 
	 * @param blockStmt
	 *            the block stmt
	 * @param parameters
	 *            the parameters
	 * @return the lambda block
	 */
	public static LambdaBlock LambdaBlock(BlockStmt blockStmt,
			NodeList<Parameter> parameters) {
		return new LambdaBlock(blockStmt, parameters, null, 0, 0);
	}

	/**
	 * Lambda expr.
	 * 
	 * @param expression
	 *            the expression
	 * @param parameters
	 *            the parameters
	 * @return the lambda expr
	 */
	public static LambdaExpr LambdaExpr(Expression expression,
			NodeList<Parameter> parameters) {
		return new LambdaExpr(expression, parameters, null, 0, 0);
	}

	/**
	 * Name expr.
	 * 
	 * @param base
	 *            the base
	 * @param values
	 *            the values
	 * @return the name expr
	 */
	public static NameExpr NameExpr(NameExpr base, String... values) {
		NameExpr name = base != null ? QualifiedNameExpr(base, values[0])
				: NameExpr(values[0]);
		for (int i = 1; i < values.length; i++) {
			name = QualifiedNameExpr(name, values[i]);
		}
		return name;
	}

	/**
	 * Variable declarator id.
	 * 
	 * @param name
	 *            the name
	 * @return the variable declarator id
	 */
	public static VariableDeclaratorId VariableDeclaratorId(String name) {
		return VariableDeclaratorId(name, null, null, 0, 0);
	}

	/**
	 * Variable declaration expr.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param init
	 *            the init
	 * @return the variable declaration expr
	 */
	public static VariableDeclarationExpr VariableDeclarationExpr(Type type,
			String name, Expression init) {
		return new VariableDeclarationExpr(null, type,
				NodeList(VariableDeclarator(VariableDeclaratorId(name), init)),
				null, 0, 0);
	}

	/**
	 * Reference type.
	 * 
	 * @param type
	 *            the type
	 * @param slotsNumber
	 *            the slots number
	 * @return the reference type
	 */
	public static ReferenceType ReferenceType(Type type, int slotsNumber) {
		if (slotsNumber != 0) {
			List<ArraySlot> slots = new ArrayList<>();
			for (int i = 0; i < slotsNumber; i++) {
				slots.add(new ArraySlot());
			}
			return ReferenceType(type, slots);
		} else {
			return ReferenceType(type, null);
		}
	}

	/**
	 * Method call expr.
	 * 
	 * @param scope
	 *            the scope
	 * @param name
	 *            the name
	 * @return the method call expr
	 */
	public static MethodCallExpr MethodCallExpr(Expression scope, String name) {
		return MethodCallExpr(scope, null, name, null);
	}

	/**
	 * Constructor declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param name
	 *            the name
	 * @return the constructor declaration
	 */
	public static ConstructorDeclaration ConstructorDeclaration(int modifiers,
			String name) {
		return ConstructorDeclaration(Modifiers(modifiers), null, name, null,
				null, null, null, null, null, null, 0, 0);
	}

	/**
	 * Project.
	 * 
	 * @param units
	 *            the units
	 * @return the project
	 */
	public static Project Project(List<CompilationUnit> units) {
		return Project(NodeList(units), null, 0, 0);
	}

	/**
	 * Method declaration.
	 * 
	 * @param returnType
	 *            the return type
	 * @param methodName
	 *            the method name
	 * @return the method declaration
	 */
	public static MethodDeclaration MethodDeclaration(Type returnType,
			String methodName) {
		return MethodDeclaration(Modifiers.PUBLIC, null, returnType,
				methodName, null, null, null, null, null, null);
	}

	/**
	 * Enum constant declaration.
	 * 
	 * @param name
	 *            the name
	 * @return the enum constant declaration
	 */
	public static EnumConstantDeclaration EnumConstantDeclaration(String name) {
		return EnumConstantDeclaration(name, null, null, null, null);
	}

	/**
	 * Field declaration.
	 * 
	 * @param modifiers
	 *            the modifiers
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param init
	 *            the init
	 * @return the field declaration
	 */
	public static FieldDeclaration FieldDeclaration(int modifiers, Type type,
			String name, Expression init) {
		VariableDeclarator var = VariableDeclarator(VariableDeclaratorId(name),
				init);
		return FieldDeclaration(modifiers, type, Arrays.asList(var), null, null);
	}

	/**
	 * Method declaration.
	 *
	 * @param modifiers the modifiers
	 * @param type the type
	 * @param name the name
	 * @return the method declaration
	 */
	public static MethodDeclaration MethodDeclaration(int modifiers, Type type,
			String name) {
		return MethodDeclaration(modifiers, null, type, name, null, null, null,
				null, null, null);
	}

	/**
	 * Object creation expr.
	 *
	 * @param type the type
	 * @param arguments the arguments
	 * @return the object creation expr
	 */
	public static ObjectCreationExpr ObjectCreationExpr(
			ClassOrInterfaceType type, List<Expression> arguments) {
		return ObjectCreationExpr(null, type, null, NodeList(arguments), null,
				null, 0, 0);
	}

	/**
	 * Object creation expr.
	 *
	 * @param type the type
	 * @return the object creation expr
	 */
	public static ObjectCreationExpr ObjectCreationExpr(
			ClassOrInterfaceType type) {
		return ObjectCreationExpr(null, type, null, null, null, null, 0, 0);
	}

	/**
	 * Constructor declaration.
	 *
	 * @param modifiers the modifiers
	 * @param name the name
	 * @param params the params
	 * @return the constructor declaration
	 */
	public static ConstructorDeclaration ConstructorDeclaration(int modifiers,
			String name, List<Parameter> params) {
		return ConstructorDeclaration(Modifiers(modifiers), null, name, null,
				null, NodeList(params), null, null, null, null, 0, 0);
	}

	/**
	 * Method declaration.
	 *
	 * @param modifiers the modifiers
	 * @param type the type
	 * @param name the name
	 * @param params the params
	 * @return the method declaration
	 */
	public static MethodDeclaration MethodDeclaration(int modifiers, Type type,
			String name, List<Parameter> params) {
		return MethodDeclaration(Modifiers(modifiers), null, type, name, null,
				null, NodeList(params), null, null, null, null, null, 0, 0);
	}

}
