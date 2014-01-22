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

import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.AnnotationMemberDeclaration;
import com.digiarea.jse.Type;
import com.digiarea.jse.Expression;
import com.digiarea.jse.ArrayAccessExpr;
import com.digiarea.jse.ArrayCreationExpr;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.ArrayInitializerExpr;
import com.digiarea.jse.AssertStmt;
import com.digiarea.jse.AssignExpr;
import com.digiarea.jse.BinaryExpr;
import com.digiarea.jse.BlockComment;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.Statement;
import com.digiarea.jse.BooleanLiteralExpr;
import com.digiarea.jse.BreakStmt;
import com.digiarea.jse.CastExpr;
import com.digiarea.jse.CatchClause;
import com.digiarea.jse.CharLiteralExpr;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.ClassExpr;
import com.digiarea.jse.Comment;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.ConditionalExpr;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.ContinueStmt;
import com.digiarea.jse.DoStmt;
import com.digiarea.jse.DoubleLiteralExpr;
import com.digiarea.jse.Ellipsis;
import com.digiarea.jse.EmptyMemberDeclaration;
import com.digiarea.jse.EmptyStmt;
import com.digiarea.jse.EmptyTypeDeclaration;
import com.digiarea.jse.EnclosedExpr;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.ExplicitConstructorInvocationStmt;
import com.digiarea.jse.ExpressionStmt;
import com.digiarea.jse.FieldAccessExpr;
import com.digiarea.jse.FieldDeclaration;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.ForeachStmt;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.ForStmt;
import com.digiarea.jse.IfStmt;
import com.digiarea.jse.InitializerDeclaration;
import com.digiarea.jse.InstanceOfExpr;
import com.digiarea.jse.IntegerLiteralExpr;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.LabeledStmt;
import com.digiarea.jse.Lambda;
import com.digiarea.jse.LambdaBlock;
import com.digiarea.jse.LambdaExpr;
import com.digiarea.jse.LineComment;
import com.digiarea.jse.LiteralExpr;
import com.digiarea.jse.LongLiteralExpr;
import com.digiarea.jse.MarkerAnnotationExpr;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.MethodCallExpr;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.MethodExprRef;
import com.digiarea.jse.MethodRef;
import com.digiarea.jse.MethodTypeRef;
import com.digiarea.jse.Node;
import java.util.List;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.NullLiteralExpr;
import com.digiarea.jse.ObjectCreationExpr;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.Project;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.ReturnStmt;
import com.digiarea.jse.SingleMemberAnnotationExpr;
import com.digiarea.jse.StringLiteralExpr;
import com.digiarea.jse.SuperExpr;
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.SwitchStmt;
import com.digiarea.jse.SynchronizedStmt;
import com.digiarea.jse.ThisExpr;
import com.digiarea.jse.ThrowStmt;
import com.digiarea.jse.TryStmt;
import com.digiarea.jse.TypeDeclarationStmt;
import com.digiarea.jse.UnaryExpr;
import com.digiarea.jse.VoidType;
import com.digiarea.jse.WhileStmt;
import com.digiarea.jse.WildcardType;

public class NodeFactory {

    public static AnnotationDeclaration AnnotationDeclaration() {
        return new AnnotationDeclaration();
    }

    public static AnnotationDeclaration AnnotationDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AnnotationDeclaration(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    public static AnnotationExpr AnnotationExpr() {
        return new AnnotationExpr();
    }

    public static AnnotationExpr AnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AnnotationExpr(name, annotations, posBegin, posEnd);
    }

    public static AnnotationMemberDeclaration AnnotationMemberDeclaration() {
        return new AnnotationMemberDeclaration();
    }

    public static AnnotationMemberDeclaration AnnotationMemberDeclaration(Modifiers modifiers, Type type, String name, Expression defaultValue, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AnnotationMemberDeclaration(modifiers, type, name, defaultValue, javaDoc, annotations, posBegin, posEnd);
    }

    public static ArrayAccessExpr ArrayAccessExpr() {
        return new ArrayAccessExpr();
    }

    public static ArrayAccessExpr ArrayAccessExpr(Expression name, Expression index, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArrayAccessExpr(name, index, annotations, posBegin, posEnd);
    }

    public static ArrayCreationExpr ArrayCreationExpr() {
        return new ArrayCreationExpr();
    }

    public static ArrayCreationExpr ArrayCreationExpr(Type type, NodeList<ArraySlot> slots, ArrayInitializerExpr initializer, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArrayCreationExpr(type, slots, initializer, annotations, posBegin, posEnd);
    }

    public static ArrayInitializerExpr ArrayInitializerExpr() {
        return new ArrayInitializerExpr();
    }

    public static ArrayInitializerExpr ArrayInitializerExpr(NodeList<Expression> values, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArrayInitializerExpr(values, annotations, posBegin, posEnd);
    }

    public static ArraySlot ArraySlot() {
        return new ArraySlot();
    }

    public static ArraySlot ArraySlot(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArraySlot(expression, annotations, posBegin, posEnd);
    }

    public static AssertStmt AssertStmt() {
        return new AssertStmt();
    }

    public static AssertStmt AssertStmt(Expression check, Expression message, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AssertStmt(check, message, annotations, posBegin, posEnd);
    }

    public static AssignExpr AssignExpr() {
        return new AssignExpr();
    }

    public static AssignExpr AssignExpr(Expression target, Expression value, AssignExpr.AssignOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AssignExpr(target, value, operator, annotations, posBegin, posEnd);
    }

    public static BinaryExpr BinaryExpr() {
        return new BinaryExpr();
    }

    public static BinaryExpr BinaryExpr(Expression left, Expression right, BinaryExpr.BinaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BinaryExpr(left, right, operator, annotations, posBegin, posEnd);
    }

    public static BlockComment BlockComment() {
        return new BlockComment();
    }

    public static BlockComment BlockComment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BlockComment(content, annotations, posBegin, posEnd);
    }

    public static BlockStmt BlockStmt() {
        return new BlockStmt();
    }

    public static BlockStmt BlockStmt(NodeList<Statement> statements, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BlockStmt(statements, annotations, posBegin, posEnd);
    }

    public static BodyDeclaration BodyDeclaration() {
        return new BodyDeclaration();
    }

    public static BodyDeclaration BodyDeclaration(JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BodyDeclaration(javaDoc, annotations, posBegin, posEnd);
    }

    public static BooleanLiteralExpr BooleanLiteralExpr() {
        return new BooleanLiteralExpr();
    }

    public static BooleanLiteralExpr BooleanLiteralExpr(boolean value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BooleanLiteralExpr(value, annotations, posBegin, posEnd);
    }

    public static BreakStmt BreakStmt() {
        return new BreakStmt();
    }

    public static BreakStmt BreakStmt(String id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BreakStmt(id, annotations, posBegin, posEnd);
    }

    public static CastExpr CastExpr() {
        return new CastExpr();
    }

    public static CastExpr CastExpr(NodeList<Type> types, Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CastExpr(types, expression, annotations, posBegin, posEnd);
    }

    public static CatchClause CatchClause() {
        return new CatchClause();
    }

    public static CatchClause CatchClause(boolean isFinal, NodeList<Type> types, String name, BlockStmt catchBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CatchClause(isFinal, types, name, catchBlock, annotations, posBegin, posEnd);
    }

    public static CharLiteralExpr CharLiteralExpr() {
        return new CharLiteralExpr();
    }

    public static CharLiteralExpr CharLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CharLiteralExpr(value, annotations, posBegin, posEnd);
    }

    public static ClassDeclaration ClassDeclaration() {
        return new ClassDeclaration();
    }

    public static ClassDeclaration ClassDeclaration(NodeList<TypeParameter> typeParameters, ClassOrInterfaceType extendsType, NodeList<ClassOrInterfaceType> implementsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ClassDeclaration(typeParameters, extendsType, implementsList, modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    public static ClassExpr ClassExpr() {
        return new ClassExpr();
    }

    public static ClassExpr ClassExpr(Type type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ClassExpr(type, annotations, posBegin, posEnd);
    }

    public static ClassOrInterfaceType ClassOrInterfaceType() {
        return new ClassOrInterfaceType();
    }

    public static ClassOrInterfaceType ClassOrInterfaceType(ClassOrInterfaceType scope, NameExpr name, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ClassOrInterfaceType(scope, name, typeArgs, annotations, posBegin, posEnd);
    }

    public static Comment Comment() {
        return new Comment();
    }

    public static Comment Comment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Comment(content, annotations, posBegin, posEnd);
    }

    public static CompilationUnit CompilationUnit() {
        return new CompilationUnit();
    }

    public static CompilationUnit CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration> types, NodeList<Comment> comments, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CompilationUnit(packageDeclaration, imports, types, comments, name, annotations, posBegin, posEnd);
    }

    public static ConditionalExpr ConditionalExpr() {
        return new ConditionalExpr();
    }

    public static ConditionalExpr ConditionalExpr(Expression condition, Expression thenExpression, Expression elseExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ConditionalExpr(condition, thenExpression, elseExpression, annotations, posBegin, posEnd);
    }

    public static ConstructorDeclaration ConstructorDeclaration() {
        return new ConstructorDeclaration();
    }

    public static ConstructorDeclaration ConstructorDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, String name, NodeList<Parameter> parameters, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ConstructorDeclaration(modifiers, typeParameters, name, parameters, throwsList, block, javaDoc, annotations, posBegin, posEnd);
    }

    public static ContinueStmt ContinueStmt() {
        return new ContinueStmt();
    }

    public static ContinueStmt ContinueStmt(String id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ContinueStmt(id, annotations, posBegin, posEnd);
    }

    public static DoStmt DoStmt() {
        return new DoStmt();
    }

    public static DoStmt DoStmt(Statement body, Expression condition, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new DoStmt(body, condition, annotations, posBegin, posEnd);
    }

    public static DoubleLiteralExpr DoubleLiteralExpr() {
        return new DoubleLiteralExpr();
    }

    public static DoubleLiteralExpr DoubleLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new DoubleLiteralExpr(value, annotations, posBegin, posEnd);
    }

    public static Ellipsis Ellipsis() {
        return new Ellipsis();
    }

    public static Ellipsis Ellipsis(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Ellipsis(annotations, posBegin, posEnd);
    }

    public static EmptyMemberDeclaration EmptyMemberDeclaration() {
        return new EmptyMemberDeclaration();
    }

    public static EmptyMemberDeclaration EmptyMemberDeclaration(JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EmptyMemberDeclaration(javaDoc, annotations, posBegin, posEnd);
    }

    public static EmptyStmt EmptyStmt() {
        return new EmptyStmt();
    }

    public static EmptyStmt EmptyStmt(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EmptyStmt(annotations, posBegin, posEnd);
    }

    public static EmptyTypeDeclaration EmptyTypeDeclaration() {
        return new EmptyTypeDeclaration();
    }

    public static EmptyTypeDeclaration EmptyTypeDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EmptyTypeDeclaration(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    public static EnclosedExpr EnclosedExpr() {
        return new EnclosedExpr();
    }

    public static EnclosedExpr EnclosedExpr(Expression inner, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EnclosedExpr(inner, annotations, posBegin, posEnd);
    }

    public static EnumConstantDeclaration EnumConstantDeclaration() {
        return new EnumConstantDeclaration();
    }

    public static EnumConstantDeclaration EnumConstantDeclaration(String name, NodeList<Expression> args, NodeList<BodyDeclaration> classBody, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EnumConstantDeclaration(name, args, classBody, javaDoc, annotations, posBegin, posEnd);
    }

    public static EnumDeclaration EnumDeclaration() {
        return new EnumDeclaration();
    }

    public static EnumDeclaration EnumDeclaration(NodeList<ClassOrInterfaceType> implementsList, NodeList<EnumConstantDeclaration> entries, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EnumDeclaration(implementsList, entries, modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    public static ExplicitConstructorInvocationStmt ExplicitConstructorInvocationStmt() {
        return new ExplicitConstructorInvocationStmt();
    }

    public static ExplicitConstructorInvocationStmt ExplicitConstructorInvocationStmt(NodeList<Type> typeArgs, boolean isThis, Expression expression, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ExplicitConstructorInvocationStmt(typeArgs, isThis, expression, args, annotations, posBegin, posEnd);
    }

    public static Expression Expression() {
        return new Expression();
    }

    public static Expression Expression(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Expression(annotations, posBegin, posEnd);
    }

    public static ExpressionStmt ExpressionStmt() {
        return new ExpressionStmt();
    }

    public static ExpressionStmt ExpressionStmt(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ExpressionStmt(expression, annotations, posBegin, posEnd);
    }

    public static FieldAccessExpr FieldAccessExpr() {
        return new FieldAccessExpr();
    }

    public static FieldAccessExpr FieldAccessExpr(Expression scope, NodeList<Type> typeArgs, String field, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new FieldAccessExpr(scope, typeArgs, field, annotations, posBegin, posEnd);
    }

    public static FieldDeclaration FieldDeclaration() {
        return new FieldDeclaration();
    }

    public static FieldDeclaration FieldDeclaration(Modifiers modifiers, Type type, NodeList<VariableDeclarator> variables, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new FieldDeclaration(modifiers, type, variables, javaDoc, annotations, posBegin, posEnd);
    }

    public static ForeachStmt ForeachStmt() {
        return new ForeachStmt();
    }

    public static ForeachStmt ForeachStmt(VariableDeclarationExpr variable, Expression iterable, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ForeachStmt(variable, iterable, body, annotations, posBegin, posEnd);
    }

    public static ForStmt ForStmt() {
        return new ForStmt();
    }

    public static ForStmt ForStmt(NodeList<Expression> init, Expression compare, NodeList<Expression> update, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ForStmt(init, compare, update, body, annotations, posBegin, posEnd);
    }

    public static IfStmt IfStmt() {
        return new IfStmt();
    }

    public static IfStmt IfStmt(Expression condition, Statement thenStmt, Statement elseStmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new IfStmt(condition, thenStmt, elseStmt, annotations, posBegin, posEnd);
    }

    public static ImportDeclaration ImportDeclaration() {
        return new ImportDeclaration();
    }

    public static ImportDeclaration ImportDeclaration(NameExpr name, boolean isStatic, boolean isAsterisk, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ImportDeclaration(name, isStatic, isAsterisk, annotations, posBegin, posEnd);
    }

    public static InitializerDeclaration InitializerDeclaration() {
        return new InitializerDeclaration();
    }

    public static InitializerDeclaration InitializerDeclaration(boolean isStatic, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new InitializerDeclaration(isStatic, block, javaDoc, annotations, posBegin, posEnd);
    }

    public static InstanceOfExpr InstanceOfExpr() {
        return new InstanceOfExpr();
    }

    public static InstanceOfExpr InstanceOfExpr(Expression expression, Type type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new InstanceOfExpr(expression, type, annotations, posBegin, posEnd);
    }

    public static IntegerLiteralExpr IntegerLiteralExpr() {
        return new IntegerLiteralExpr();
    }

    public static IntegerLiteralExpr IntegerLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new IntegerLiteralExpr(value, annotations, posBegin, posEnd);
    }

    public static InterfaceDeclaration InterfaceDeclaration() {
        return new InterfaceDeclaration();
    }

    public static InterfaceDeclaration InterfaceDeclaration(NodeList<TypeParameter> typeParameters, NodeList<ClassOrInterfaceType> extendsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new InterfaceDeclaration(typeParameters, extendsList, modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    public static JavadocComment JavadocComment() {
        return new JavadocComment();
    }

    public static JavadocComment JavadocComment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new JavadocComment(content, annotations, posBegin, posEnd);
    }

    public static LabeledStmt LabeledStmt() {
        return new LabeledStmt();
    }

    public static LabeledStmt LabeledStmt(String label, Statement stmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LabeledStmt(label, stmt, annotations, posBegin, posEnd);
    }

    public static Lambda Lambda() {
        return new Lambda();
    }

    public static Lambda Lambda(NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Lambda(parameters, annotations, posBegin, posEnd);
    }

    public static LambdaBlock LambdaBlock() {
        return new LambdaBlock();
    }

    public static LambdaBlock LambdaBlock(BlockStmt blockStmt, NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LambdaBlock(blockStmt, parameters, annotations, posBegin, posEnd);
    }

    public static LambdaExpr LambdaExpr() {
        return new LambdaExpr();
    }

    public static LambdaExpr LambdaExpr(Expression expression, NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LambdaExpr(expression, parameters, annotations, posBegin, posEnd);
    }

    public static LineComment LineComment() {
        return new LineComment();
    }

    public static LineComment LineComment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LineComment(content, annotations, posBegin, posEnd);
    }

    public static LiteralExpr LiteralExpr() {
        return new LiteralExpr();
    }

    public static LiteralExpr LiteralExpr(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LiteralExpr(annotations, posBegin, posEnd);
    }

    public static LongLiteralExpr LongLiteralExpr() {
        return new LongLiteralExpr();
    }

    public static LongLiteralExpr LongLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LongLiteralExpr(value, annotations, posBegin, posEnd);
    }

    public static MarkerAnnotationExpr MarkerAnnotationExpr() {
        return new MarkerAnnotationExpr();
    }

    public static MarkerAnnotationExpr MarkerAnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MarkerAnnotationExpr(name, annotations, posBegin, posEnd);
    }

    public static MemberValuePair MemberValuePair() {
        return new MemberValuePair();
    }

    public static MemberValuePair MemberValuePair(String name, Expression value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MemberValuePair(name, value, annotations, posBegin, posEnd);
    }

    public static MethodCallExpr MethodCallExpr() {
        return new MethodCallExpr();
    }

    public static MethodCallExpr MethodCallExpr(Expression scope, NodeList<Type> typeArgs, String name, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodCallExpr(scope, typeArgs, name, args, annotations, posBegin, posEnd);
    }

    public static MethodDeclaration MethodDeclaration() {
        return new MethodDeclaration();
    }

    public static MethodDeclaration MethodDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, Type type, String name, NodeList<Parameter> parameters, NodeList<ArraySlot> slots, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodDeclaration(modifiers, typeParameters, type, name, parameters, slots, throwsList, block, javaDoc, annotations, posBegin, posEnd);
    }

    public static MethodExprRef MethodExprRef() {
        return new MethodExprRef();
    }

    public static MethodExprRef MethodExprRef(Expression scope, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodExprRef(scope, typeArgs, name, annotations, posBegin, posEnd);
    }

    public static MethodRef MethodRef() {
        return new MethodRef();
    }

    public static MethodRef MethodRef(NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodRef(typeArgs, name, annotations, posBegin, posEnd);
    }

    public static MethodTypeRef MethodTypeRef() {
        return new MethodTypeRef();
    }

    public static MethodTypeRef MethodTypeRef(Type type, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodTypeRef(type, typeArgs, name, annotations, posBegin, posEnd);
    }

    public static Modifiers Modifiers() {
        return new Modifiers();
    }

    public static Modifiers Modifiers(int modifiers, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Modifiers(modifiers, annotations, posBegin, posEnd);
    }

    public static NameExpr NameExpr() {
        return new NameExpr();
    }

    public static NameExpr NameExpr(String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NameExpr(name, annotations, posBegin, posEnd);
    }

    public static <E extends Node> NodeList<E> NodeList() {
        return new NodeList<E>();
    }

    public static <E extends Node> NodeList<E> NodeList(List<E> nodes, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NodeList<E>(nodes, annotations, posBegin, posEnd);
    }

    public static NormalAnnotationExpr NormalAnnotationExpr() {
        return new NormalAnnotationExpr();
    }

    public static NormalAnnotationExpr NormalAnnotationExpr(NodeList<MemberValuePair> pairs, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NormalAnnotationExpr(pairs, name, annotations, posBegin, posEnd);
    }

    public static NullLiteralExpr NullLiteralExpr() {
        return new NullLiteralExpr();
    }

    public static NullLiteralExpr NullLiteralExpr(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NullLiteralExpr(annotations, posBegin, posEnd);
    }

    public static ObjectCreationExpr ObjectCreationExpr() {
        return new ObjectCreationExpr();
    }

    public static ObjectCreationExpr ObjectCreationExpr(Expression scope, ClassOrInterfaceType type, NodeList<Type> typeArgs, NodeList<Expression> args, NodeList<BodyDeclaration> anonymousClassBody, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ObjectCreationExpr(scope, type, typeArgs, args, anonymousClassBody, annotations, posBegin, posEnd);
    }

    public static PackageDeclaration PackageDeclaration() {
        return new PackageDeclaration();
    }

    public static PackageDeclaration PackageDeclaration(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new PackageDeclaration(name, annotations, posBegin, posEnd);
    }

    public static Parameter Parameter() {
        return new Parameter();
    }

    public static Parameter Parameter(Modifiers modifiers, Type type, Ellipsis ellipsis, VariableDeclaratorId id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Parameter(modifiers, type, ellipsis, id, annotations, posBegin, posEnd);
    }

    public static PrimitiveType PrimitiveType() {
        return new PrimitiveType();
    }

    public static PrimitiveType PrimitiveType(PrimitiveType.Primitive type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new PrimitiveType(type, annotations, posBegin, posEnd);
    }

    public static Project Project() {
        return new Project();
    }

    public static Project Project(NodeList<CompilationUnit> compilationUnits, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Project(compilationUnits, annotations, posBegin, posEnd);
    }

    public static QualifiedNameExpr QualifiedNameExpr() {
        return new QualifiedNameExpr();
    }

    public static QualifiedNameExpr QualifiedNameExpr(NameExpr qualifier, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new QualifiedNameExpr(qualifier, name, annotations, posBegin, posEnd);
    }

    public static ReferenceType ReferenceType() {
        return new ReferenceType();
    }

    public static ReferenceType ReferenceType(Type type, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ReferenceType(type, slots, annotations, posBegin, posEnd);
    }

    public static ReturnStmt ReturnStmt() {
        return new ReturnStmt();
    }

    public static ReturnStmt ReturnStmt(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ReturnStmt(expression, annotations, posBegin, posEnd);
    }

    public static SingleMemberAnnotationExpr SingleMemberAnnotationExpr() {
        return new SingleMemberAnnotationExpr();
    }

    public static SingleMemberAnnotationExpr SingleMemberAnnotationExpr(Expression memberValue, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SingleMemberAnnotationExpr(memberValue, name, annotations, posBegin, posEnd);
    }

    public static Statement Statement() {
        return new Statement();
    }

    public static Statement Statement(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Statement(annotations, posBegin, posEnd);
    }

    public static StringLiteralExpr StringLiteralExpr() {
        return new StringLiteralExpr();
    }

    public static StringLiteralExpr StringLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new StringLiteralExpr(value, annotations, posBegin, posEnd);
    }

    public static SuperExpr SuperExpr() {
        return new SuperExpr();
    }

    public static SuperExpr SuperExpr(Expression classExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SuperExpr(classExpression, annotations, posBegin, posEnd);
    }

    public static SwitchEntryStmt SwitchEntryStmt() {
        return new SwitchEntryStmt();
    }

    public static SwitchEntryStmt SwitchEntryStmt(Expression label, NodeList<Statement> stmts, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SwitchEntryStmt(label, stmts, annotations, posBegin, posEnd);
    }

    public static SwitchStmt SwitchStmt() {
        return new SwitchStmt();
    }

    public static SwitchStmt SwitchStmt(Expression selector, NodeList<SwitchEntryStmt> entries, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SwitchStmt(selector, entries, annotations, posBegin, posEnd);
    }

    public static SynchronizedStmt SynchronizedStmt() {
        return new SynchronizedStmt();
    }

    public static SynchronizedStmt SynchronizedStmt(Expression expression, BlockStmt block, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SynchronizedStmt(expression, block, annotations, posBegin, posEnd);
    }

    public static ThisExpr ThisExpr() {
        return new ThisExpr();
    }

    public static ThisExpr ThisExpr(Expression classExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ThisExpr(classExpression, annotations, posBegin, posEnd);
    }

    public static ThrowStmt ThrowStmt() {
        return new ThrowStmt();
    }

    public static ThrowStmt ThrowStmt(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ThrowStmt(expression, annotations, posBegin, posEnd);
    }

    public static TryStmt TryStmt() {
        return new TryStmt();
    }

    public static TryStmt TryStmt(NodeList<VariableDeclarationExpr> resources, BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TryStmt(resources, tryBlock, catchClauses, finallyBlock, annotations, posBegin, posEnd);
    }

    public static Type Type() {
        return new Type();
    }

    public static Type Type(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Type(annotations, posBegin, posEnd);
    }

    public static TypeDeclaration TypeDeclaration() {
        return new TypeDeclaration();
    }

    public static TypeDeclaration TypeDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TypeDeclaration(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    public static TypeDeclarationStmt TypeDeclarationStmt() {
        return new TypeDeclarationStmt();
    }

    public static TypeDeclarationStmt TypeDeclarationStmt(TypeDeclaration typeDeclaration, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TypeDeclarationStmt(typeDeclaration, annotations, posBegin, posEnd);
    }

    public static TypeParameter TypeParameter() {
        return new TypeParameter();
    }

    public static TypeParameter TypeParameter(String name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TypeParameter(name, typeBound, annotations, posBegin, posEnd);
    }

    public static UnaryExpr UnaryExpr() {
        return new UnaryExpr();
    }

    public static UnaryExpr UnaryExpr(Expression expression, UnaryExpr.UnaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new UnaryExpr(expression, operator, annotations, posBegin, posEnd);
    }

    public static VariableDeclarationExpr VariableDeclarationExpr() {
        return new VariableDeclarationExpr();
    }

    public static VariableDeclarationExpr VariableDeclarationExpr(Modifiers modifiers, Type type, NodeList<VariableDeclarator> vars, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VariableDeclarationExpr(modifiers, type, vars, annotations, posBegin, posEnd);
    }

    public static VariableDeclarator VariableDeclarator() {
        return new VariableDeclarator();
    }

    public static VariableDeclarator VariableDeclarator(VariableDeclaratorId id, Expression init, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VariableDeclarator(id, init, annotations, posBegin, posEnd);
    }

    public static VariableDeclaratorId VariableDeclaratorId() {
        return new VariableDeclaratorId();
    }

    public static VariableDeclaratorId VariableDeclaratorId(String name, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VariableDeclaratorId(name, slots, annotations, posBegin, posEnd);
    }

    public static VoidType VoidType() {
        return new VoidType();
    }

    public static VoidType VoidType(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VoidType(annotations, posBegin, posEnd);
    }

    public static WhileStmt WhileStmt() {
        return new WhileStmt();
    }

    public static WhileStmt WhileStmt(Expression condition, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new WhileStmt(condition, body, annotations, posBegin, posEnd);
    }

    public static WildcardType WildcardType() {
        return new WildcardType();
    }

    public static WildcardType WildcardType(ReferenceType extendsType, ReferenceType superType, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new WildcardType(extendsType, superType, annotations, posBegin, posEnd);
    }

    public NodeFactory() {
        super();
    }

}
