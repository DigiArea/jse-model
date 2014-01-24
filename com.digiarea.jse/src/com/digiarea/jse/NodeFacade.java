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

	public static ArrayInitializerExpr ArrayInitializerExpr(
			List<Expression> values, List<AnnotationExpr> annotations) {
		return ArrayInitializerExpr(NodeList(values, null, 0, 0),
				NodeList(annotations, null, 0, 0), 0, 0);
	}

	public static ObjectCreationExpr ObjectCreationExpr(String name,
			List<Expression> arguments) {
		return ObjectCreationExpr(null, ClassOrInterfaceType(name), null,
				NodeList(arguments), null, null, 0, 0);
	}

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

	public static CastExpr CastExpr(String qName, Expression img) {
		return CastExpr(NodeList((Type) ClassOrInterfaceType(qName)), img,
				null, 0, 0);
	}

	public static FieldAccessExpr FieldAccessExpr(String qName, String field) {
		return FieldAccessExpr(QualifiedNameExpr(qName), null, field);
	}

	public static BooleanLiteralExpr BooleanLiteralExpr(boolean value) {
		return BooleanLiteralExpr(value, null, 0, 0);
	}

	public static IntegerLiteralExpr IntegerLiteralExpr(int value) {
		return IntegerLiteralExpr(String.valueOf(value));
	}

	public static MethodCallExpr MethodCallExpr(List<Expression> nodes) {
		return MethodCallExpr(
				QualifiedNameExpr(QualifiedNameExpr(NameExpr("java"), "util"),
						"Arrays"), null, "asList", nodes);
	}

	public static ClassExpr ClassExpr(Class<?> predicate) {
		return ClassExpr(ClassOrInterfaceType(predicate.getName()));
	}

	public static JavadocComment JavadocComment(String context) {
		return JavadocComment(context, null, 0, 0);
	}

	public static LineComment LineComment(String context) {
		return LineComment(context, null, 0, 0);
	}

	public static BlockComment BlockComment(String context) {
		return BlockComment(context, null, 0, 0);
	}

	public static ClassOrInterfaceType ClassOrInterfaceType(NameExpr nameExpr) {
		return ClassOrInterfaceType(null, nameExpr);
	}

	public static ClassOrInterfaceType ClassOrInterfaceType(String qName) {
		return ClassOrInterfaceType(QualifiedNameExpr(qName));
	}

	public static ClassOrInterfaceType ClassOrInterfaceType(
			ClassOrInterfaceType classOrInterfaceType, NameExpr nameExpr) {
		return ClassOrInterfaceType(classOrInterfaceType, nameExpr, null);
	}

	public static NameExpr QualifiedNameExpr(String name) {
		String[] split = name.split("\\.");
		NameExpr ret = NameExpr(split[0]);
		for (int i = 1; i < split.length; i++) {
			ret = QualifiedNameExpr(ret, split[i]);
		}
		return ret;
	}

	public static NameExpr NameExpr(String name) {
		return NameExpr(name, null, 0, 0);
	}

	public static CompilationUnit CompilationUnit(PackageDeclaration pakage,
			List<ImportDeclaration> imports, List<TypeDeclaration> types,
			List<Comment> comments, String name) {
		return CompilationUnit(pakage, NodeList(imports), NodeList(types),
				NodeList(comments), name, null, 0, 0);
	}

	public static PackageDeclaration PackageDeclaration(NameExpr name,
			List<AnnotationExpr> annotations) {
		return PackageDeclaration(name, NodeList(annotations), 0, 0);
	}

	public static ImportDeclaration ImportDeclaration(NameExpr name,
			boolean isStatic, boolean isAsterisk) {
		return ImportDeclaration(name, isStatic, isAsterisk, null, 0, 0);
	}

	public static EmptyTypeDeclaration EmptyTypeDeclaration(
			JavadocComment javaDoc) {
		return EmptyTypeDeclaration(null, null, null, javaDoc, null, 0, 0);
	}

	public static ClassDeclaration ClassDeclaration(
			List<TypeParameter> typePar, ClassOrInterfaceType extendsType,
			List<ClassOrInterfaceType> impList, int modifiers, String name,
			List<BodyDeclaration> members, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return ClassDeclaration(NodeList(typePar), extendsType,
				NodeList(impList), Modifiers(modifiers), name,
				NodeList(members), javaDoc, NodeList(annotations), 0, 0);
	}

	public static Modifiers Modifiers(int modifiers) {
		return Modifiers(modifiers, null, 0, 0);
	}

	public static InterfaceDeclaration InterfaceDeclaration(
			List<TypeParameter> typePar, List<ClassOrInterfaceType> extList,
			int modifiers, String name, List<BodyDeclaration> members,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return InterfaceDeclaration(NodeList(typePar), NodeList(extList),
				Modifiers(modifiers), name, NodeList(members), javaDoc,
				NodeList(annotations), 0, 0);
	}

	public static EnumDeclaration EnumDeclaration(
			List<ClassOrInterfaceType> impList,
			List<EnumConstantDeclaration> entries, int modifiers, String name,
			List<BodyDeclaration> members, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return EnumDeclaration(NodeList(impList), NodeList(entries),
				Modifiers(modifiers), name, NodeList(members), javaDoc,
				NodeList(annotations), 0, 0);
	}

	public static EnumConstantDeclaration EnumConstantDeclaration(String name,
			List<Expression> args, List<BodyDeclaration> classBody,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return EnumConstantDeclaration(name, NodeList(args),
				NodeList(classBody), javaDoc, NodeList(annotations), 0, 0);
	}

	public static TypeParameter TypeParameter(String name,
			List<ClassOrInterfaceType> typeBound) {
		return TypeParameter(name, NodeList(typeBound), null, 0, 0);
	}

	public static BodyDeclaration EmptyMemberDeclaration(JavadocComment javaDoc) {
		return EmptyMemberDeclaration(javaDoc, null, 0, 0);
	}

	public static FieldDeclaration FieldDeclaration(int modifiers, Type type,
			List<VariableDeclarator> variables, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return FieldDeclaration(Modifiers(modifiers), type,
				NodeList(variables), javaDoc, NodeList(annotations), 0, 0);
	}

	public static VariableDeclarator VariableDeclarator(
			VariableDeclaratorId id, Expression init) {
		return VariableDeclarator(id, init, null, 0, 0);
	}

	public static ArrayInitializerExpr ArrayInitializerExpr(
			List<Expression> values) {
		return ArrayInitializerExpr(NodeList(values), null, 0, 0);
	}

	public static MethodDeclaration MethodDeclaration(int modifiers,
			List<TypeParameter> typeParameters, Type type, String name,
			List<Parameter> parameters, List<ArraySlot> slots,
			List<ClassOrInterfaceType> throwsList, BlockStmt body,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return MethodDeclaration(Modifiers(modifiers),
				NodeList(typeParameters), type, name, NodeList(parameters),
				NodeList(slots), NodeList(throwsList), body, javaDoc,
				NodeList(annotations), 0, 0);
	}

	public static BlockStmt BlockStmt(List<Statement> stmts) {
		return BlockStmt(NodeList(stmts), null, 0, 0);
	}

	public static ConstructorDeclaration ConstructorDeclaration(int modifiers,
			List<TypeParameter> typeParameters, String name,
			List<Parameter> parameters, List<ClassOrInterfaceType> throwsList,
			BlockStmt blockStmt, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return ConstructorDeclaration(Modifiers(modifiers),
				NodeList(typeParameters), name, NodeList(parameters),
				NodeList(throwsList), blockStmt, javaDoc,
				NodeList(annotations), 0, 0);
	}

	public static ExplicitConstructorInvocationStmt ExplicitConstructorInvocationStmt(
			List<Type> typeArgs, boolean isThis, Expression expr,
			List<Expression> args) {
		return ExplicitConstructorInvocationStmt(NodeList(typeArgs), isThis,
				null, NodeList(args), null, 0, 0);
	}

	public static InitializerDeclaration InitializerDeclaration(
			boolean isStatic, BlockStmt block, JavadocComment javaDoc) {
		return InitializerDeclaration(isStatic, block, javaDoc, null, 0, 0);
	}

	public static ClassOrInterfaceType ClassOrInterfaceType(
			ClassOrInterfaceType scope, NameExpr name, List<Type> typeArgs) {
		return ClassOrInterfaceType(scope, name, NodeList(typeArgs), null);
	}

	public static WildcardType WildcardType(ReferenceType extendsType,
			ReferenceType superType) {
		return WildcardType(extendsType, superType, null, 0, 0);
	}

	public static PrimitiveType PrimitiveType(Primitive type) {
		return PrimitiveType(type, null, 0, 0);
	}

	public static QualifiedNameExpr QualifiedNameExpr(NameExpr qualifier,
			String name) {
		return QualifiedNameExpr(qualifier, name, null, 0, 0);
	}

	public static AssignExpr AssignExpr(Expression target, Expression value,
			AssignOperator operator) {
		return AssignExpr(target, value, operator, null, 0, 0);
	}

	public static ConditionalExpr ConditionalExpr(Expression condition,
			Expression thenExpression, Expression elseExpression) {
		return ConditionalExpr(condition, thenExpression, elseExpression, null,
				0, 0);
	}

	public static BinaryExpr BinaryExpr(Expression left, Expression right,
			BinaryOperator operator) {
		return BinaryExpr(left, right, operator, null, 0, 0);
	}

	public static InstanceOfExpr InstanceOfExpr(Expression expression, Type type) {
		return InstanceOfExpr(expression, type, null, 0, 0);
	}

	public static UnaryExpr UnaryExpr(Expression expression,
			UnaryOperator operator) {
		return UnaryExpr(expression, operator, null, 0, 0);
	}

	public static CastExpr CastExpr(Type type, Expression expression) {
		return CastExpr(NodeList(type), expression, null, 0, 0);
	}

	public static FieldAccessExpr FieldAccessExpr(Expression scope,
			List<Type> typeArgs, String field) {
		return FieldAccessExpr(scope, NodeList(typeArgs), field, null, 0, 0);
	}

	public static MethodCallExpr MethodCallExpr(Expression scope,
			List<Type> typeArgs, String name, List<Expression> args) {
		return MethodCallExpr(scope, NodeList(typeArgs), name, NodeList(args),
				null, 0, 0);
	}

	public static MethodCallExpr MethodCallExpr(String name,
			List<Expression> args) {
		QualifiedNameExpr qName = QualifiedNameExpr(
				QualifiedNameExpr(
						QualifiedNameExpr(NameExpr("com"), "digiarea"), "jse"),
				"NodeFacade");
		return MethodCallExpr(qName, null, name, NodeList(args), null, 0, 0);
	}

	public static EnclosedExpr EnclosedExpr(Expression inner) {
		return EnclosedExpr(inner, null, 0, 0);
	}

	public static ClassExpr ClassExpr(Type type) {
		return ClassExpr(type, null, 0, 0);
	}

	public static SuperExpr SuperExpr(Expression classExpression) {
		return SuperExpr(classExpression, null, 0, 0);
	}

	public static ThisExpr ThisExpr(Expression classExpression) {
		return ThisExpr(classExpression, null, 0, 0);
	}

	public static ArrayAccessExpr ArrayAccessExpr(Expression name,
			Expression index) {
		return ArrayAccessExpr(name, index, null, 0, 0);
	}

	public static IntegerLiteralExpr IntegerLiteralExpr(String value) {
		return IntegerLiteralExpr(value, null, 0, 0);
	}

	public static LongLiteralExpr LongLiteralExpr(String value) {
		return LongLiteralExpr(value, null, 0, 0);
	}

	public static DoubleLiteralExpr DoubleLiteralExpr(String value) {
		return DoubleLiteralExpr(value, null, 0, 0);
	}

	public static CharLiteralExpr CharLiteralExpr(String value) {
		return CharLiteralExpr(value, null, 0, 0);
	}

	public static ObjectCreationExpr ObjectCreationExpr(Expression scope,
			ClassOrInterfaceType type, List<Type> typeArgs,
			List<Expression> args, List<BodyDeclaration> anonymousClassBody) {
		return ObjectCreationExpr(scope, type, NodeList(typeArgs),
				NodeList(args), NodeList(anonymousClassBody), null, 0, 0);
	}

	public static AssertStmt AssertStmt(Expression check, Expression message) {
		return AssertStmt(check, message, null, 0, 0);
	}

	public static LabeledStmt LabeledStmt(String label, Statement stmt) {
		return LabeledStmt(label, stmt, null, 0, 0);
	}

	public static TypeDeclarationStmt TypeDeclarationStmt(
			TypeDeclaration typeDeclaration) {
		return TypeDeclarationStmt(typeDeclaration, null, 0, 0);
	}

	public static ExpressionStmt ExpressionStmt(Expression expression) {
		return ExpressionStmt(expression, null, 0, 0);
	}

	public static VariableDeclarationExpr VariableDeclarationExpr(
			int modifiers, Type type, List<VariableDeclarator> vars,
			List<AnnotationExpr> annotations) {
		return VariableDeclarationExpr(Modifiers(modifiers), type,
				NodeList(vars), NodeList(annotations), 0, 0);
	}

	public static SwitchStmt SwitchStmt(Expression selector,
			List<SwitchEntryStmt> entries) {
		return SwitchStmt(selector, NodeList(entries), null, 0, 0);
	}

	public static SwitchEntryStmt SwitchEntryStmt(Expression label,
			List<Statement> stmts) {
		return SwitchEntryStmt(label, NodeList(stmts), null, 0, 0);
	}

	public static IfStmt IfStmt(Expression condition, Statement thenStmt,
			Statement elseStmt) {
		return IfStmt(condition, thenStmt, elseStmt, null, 0, 0);
	}

	public static WhileStmt WhileStmt(Expression condition, Statement body) {
		return WhileStmt(condition, body, null, 0, 0);
	}

	public static DoStmt DoStmt(Statement body, Expression condition) {
		return DoStmt(body, condition, null, 0, 0);
	}

	public static Statement ForeachStmt(VariableDeclarationExpr variable,
			Expression iterable, Statement body) {
		return ForeachStmt(variable, iterable, body, null, 0, 0);
	}

	public static Statement ForStmt(List<Expression> init, Expression compare,
			List<Expression> update, Statement body) {
		return ForStmt(NodeList(init), compare, NodeList(update), body, null,
				0, 0);
	}

	public static BreakStmt BreakStmt(String id) {
		return BreakStmt(id, null, 0, 0);
	}

	public static ContinueStmt ContinueStmt(String id) {
		return ContinueStmt(id, null, 0, 0);
	}

	public static ReturnStmt ReturnStmt(Expression expression) {
		return ReturnStmt(expression, null, 0, 0);
	}

	public static ThrowStmt ThrowStmt(Expression expression) {
		return ThrowStmt(expression, null, 0, 0);
	}

	public static SynchronizedStmt SynchronizedStmt(Expression expression,
			BlockStmt block) {
		return SynchronizedStmt(expression, block, null, 0, 0);
	}

	public static CatchClause CatchClause(boolean isFinal, List<Type> types,
			String name, BlockStmt catchBlock) {
		return CatchClause(isFinal, NodeList(types), name, catchBlock, null, 0,
				0);
	}

	public static TryStmt TryStmt(List<VariableDeclarationExpr> resources,
			BlockStmt tryBlock, List<CatchClause> catchClauses,
			BlockStmt finallyBlock) {
		return TryStmt(NodeList(resources), tryBlock, NodeList(catchClauses),
				finallyBlock, null, 0, 0);
	}

	public static NormalAnnotationExpr NormalAnnotationExpr(
			List<MemberValuePair> pairs, NameExpr name) {
		return NormalAnnotationExpr(NodeList(pairs), name, null, 0, 0);
	}

	public static MarkerAnnotationExpr MarkerAnnotationExpr(NameExpr name) {
		return MarkerAnnotationExpr(name, null, 0, 0);
	}

	public static SingleMemberAnnotationExpr SingleMemberAnnotationExpr(
			Expression memberValue, NameExpr name) {
		return SingleMemberAnnotationExpr(memberValue, name, null, 0, 0);
	}

	public static MemberValuePair MemberValuePair(String name, Expression value) {
		return MemberValuePair(name, value, null, 0, 0);
	}

	public static AnnotationDeclaration AnnotationDeclaration(int modifiers,
			String name, List<BodyDeclaration> members, JavadocComment javaDoc,
			List<AnnotationExpr> annotations) {
		return AnnotationDeclaration(Modifiers(modifiers), name,
				NodeList(members), javaDoc, NodeList(annotations), 0, 0);
	}

	public static AnnotationMemberDeclaration AnnotationMemberDeclaration(
			int modifiers, Type type, String name, Expression defaultValue,
			JavadocComment javaDoc, List<AnnotationExpr> annotations) {
		return AnnotationMemberDeclaration(Modifiers(modifiers), type, name,
				defaultValue, javaDoc, NodeList(annotations), 0, 0);
	}

	/**
	 * This method return null when nodes equals null
	 * 
	 * @param nodes
	 * @return
	 */
	public static <E extends Node> NodeList<E> NodeList(List<E> nodes) {
		if (nodes != null) {
			return NodeList(nodes, null, 0, 0);
		}
		return null;
	}

	/**
	 * This method return null when node equals null
	 * 
	 * @param node
	 * @return
	 */
	public static <E extends Node> NodeList<E> NodeList(E node) {
		if (node != null) {
			return NodeList(Arrays.asList(node), null, 0, 0);
		}
		return null;
	}

	/**
	 * This method return null when node equals null
	 * 
	 * @param node
	 * @return
	 */
	public static <E extends Node> NodeList<E> NodeList(E[] node) {
		if (node != null) {
			return NodeList(Arrays.asList(node), null, 0, 0);
		}
		return null;
	}

	public static Expression CastExpr(List<Type> types, Expression expression) {
		return CastExpr(NodeList(types), expression, null, 0, 0);
	}

	public static Expression MethodTypeRef(Type type, List<Type> typeArgs,
			String image) {
		return MethodTypeRef(type, NodeList(typeArgs), image, null, 0, 0);
	}

	public static Expression MethodExprRef(Expression scope,
			List<Type> typeArgs, String name) {
		return MethodExprRef(scope, NodeList(typeArgs), name, null, 0, 0);
	}

	public static TypeParameter TypeParameter(String name,
			List<ClassOrInterfaceType> typeBound,
			List<AnnotationExpr> annotations) {
		return TypeParameter(name, NodeList(typeBound), NodeList(annotations),
				0, 0);
	}

	public static ClassOrInterfaceType ClassOrInterfaceType(
			ClassOrInterfaceType scope, NameExpr name, List<Type> typeArgs,
			List<AnnotationExpr> annotations) {
		return ClassOrInterfaceType(scope, name, NodeList(typeArgs),
				NodeList(annotations), 0, 0);
	}

	public static ArraySlot ArraySlot(List<AnnotationExpr> annotations) {
		return ArraySlot(null, NodeList(annotations), 0, 0);
	}

	public static ReferenceType ReferenceType(Type type, List<ArraySlot> slots) {
		return ReferenceType(type, NodeList(slots), null, 0, 0);
	}

	public static Ellipsis Ellipsis(List<AnnotationExpr> annotations) {
		return Ellipsis(NodeList(annotations), 0, 0);
	}

	public static Parameter Parameter(int modifiers, Type type,
			Ellipsis ellipsis, VariableDeclaratorId id,
			List<AnnotationExpr> annotations) {
		return Parameter(Modifiers(modifiers), type, ellipsis, id,
				NodeList(annotations), 0, 0);
	}

	public static Parameter Parameter(Type type, String name) {
		return Parameter(0, type, null, VariableDeclaratorId(name, null), null);
	}

	public static ArrayCreationExpr ArrayCreationExpr(Type type,
			List<ArraySlot> slots, ArrayInitializerExpr initializer) {
		return ArrayCreationExpr(type, NodeList(slots), initializer, null, 0, 0);
	}

	public static ArraySlot ArraySlot(Expression expression) {
		return ArraySlot(expression, null, 0, 0);
	}

	public static ArraySlot ArraySlot(Expression expression,
			List<AnnotationExpr> annotations) {
		return ArraySlot(expression, NodeList(annotations), 0, 0);
	}

	public static VariableDeclaratorId VariableDeclaratorId(String name,
			List<ArraySlot> slots) {
		return VariableDeclaratorId(name, NodeList(slots), null, 0, 0);
	}

	public static StringLiteralExpr StringLiteralExpr(String value) {
		return StringLiteralExpr(value, null, 0, 0);
	}

	public static LambdaBlock LambdaBlock(BlockStmt blockStmt,
			NodeList<Parameter> parameters) {
		return new LambdaBlock(blockStmt, parameters, null, 0, 0);
	}

	public static LambdaExpr LambdaExpr(Expression expression,
			NodeList<Parameter> parameters) {
		return new LambdaExpr(expression, parameters, null, 0, 0);
	}

	public static NameExpr NameExpr(NameExpr base, String... values) {
		NameExpr name = base != null ? QualifiedNameExpr(base, values[0])
				: NameExpr(values[0]);
		for (int i = 1; i < values.length; i++) {
			name = QualifiedNameExpr(name, values[i]);
		}
		return name;
	}

	public static VariableDeclaratorId VariableDeclaratorId(String name) {
		return VariableDeclaratorId(name, null, null, 0, 0);
	}

	public static VariableDeclarationExpr VariableDeclarationExpr(Type type,
			String name, Expression init) {
		return new VariableDeclarationExpr(null, type,
				NodeList(VariableDeclarator(VariableDeclaratorId(name), init)),
				null, 0, 0);
	}

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

	public static MethodCallExpr MethodCallExpr(Expression scope, String name) {
		return MethodCallExpr(scope, null, name, null);
	}

	public static ConstructorDeclaration ConstructorDeclaration(int modifiers,
			String name) {
		return ConstructorDeclaration(Modifiers(modifiers), null, name, null,
				null, null, null, null, 0, 0);
	}

	public static Project Project(List<CompilationUnit> units) {
		return Project(NodeList(units), null, 0, 0);
	}

	public static MethodDeclaration MethodDeclaration(Type returnType,
			String methodName) {
		return MethodDeclaration(0, null, returnType, methodName, null, null,
				null, null, null, null);
	}

	public static EnumConstantDeclaration EnumConstantDeclaration(String name) {
		return EnumConstantDeclaration(name, null, null, null, null);
	}

	public static FieldDeclaration FieldDeclaration(int modifiers, Type type,
			String name, Expression init) {
		VariableDeclarator var = VariableDeclarator(VariableDeclaratorId(name),
				init);
		return FieldDeclaration(modifiers, type, Arrays.asList(var), null, null);
	}
}
