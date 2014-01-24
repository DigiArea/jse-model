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
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.Comment;
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
import com.digiarea.jse.LambdaBlock;
import com.digiarea.jse.LambdaExpr;
import com.digiarea.jse.LineComment;
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

/**
 * A factory for creating Node objects.
 */
class NodeFactory {

    /**
     * Annotation declaration.
     *
     * @return the annotation declaration
     */
    public static AnnotationDeclaration AnnotationDeclaration() {
        return new AnnotationDeclaration();
    }

    /**
     * Annotation declaration.
     *
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the annotation declaration
     */
    public static AnnotationDeclaration AnnotationDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AnnotationDeclaration(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Annotation member declaration.
     *
     * @return the annotation member declaration
     */
    public static AnnotationMemberDeclaration AnnotationMemberDeclaration() {
        return new AnnotationMemberDeclaration();
    }

    /**
     * Annotation member declaration.
     *
     * @param modifiers the modifiers
     * @param type the type
     * @param name the name
     * @param defaultValue the default value
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the annotation member declaration
     */
    public static AnnotationMemberDeclaration AnnotationMemberDeclaration(Modifiers modifiers, Type type, String name, Expression defaultValue, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AnnotationMemberDeclaration(modifiers, type, name, defaultValue, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Array access expr.
     *
     * @return the array access expr
     */
    public static ArrayAccessExpr ArrayAccessExpr() {
        return new ArrayAccessExpr();
    }

    /**
     * Array access expr.
     *
     * @param name the name
     * @param index the index
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the array access expr
     */
    public static ArrayAccessExpr ArrayAccessExpr(Expression name, Expression index, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArrayAccessExpr(name, index, annotations, posBegin, posEnd);
    }

    /**
     * Array creation expr.
     *
     * @return the array creation expr
     */
    public static ArrayCreationExpr ArrayCreationExpr() {
        return new ArrayCreationExpr();
    }

    /**
     * Array creation expr.
     *
     * @param type the type
     * @param slots the slots
     * @param initializer the initializer
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the array creation expr
     */
    public static ArrayCreationExpr ArrayCreationExpr(Type type, NodeList<ArraySlot> slots, ArrayInitializerExpr initializer, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArrayCreationExpr(type, slots, initializer, annotations, posBegin, posEnd);
    }

    /**
     * Array initializer expr.
     *
     * @return the array initializer expr
     */
    public static ArrayInitializerExpr ArrayInitializerExpr() {
        return new ArrayInitializerExpr();
    }

    /**
     * Array initializer expr.
     *
     * @param values the values
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the array initializer expr
     */
    public static ArrayInitializerExpr ArrayInitializerExpr(NodeList<Expression> values, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArrayInitializerExpr(values, annotations, posBegin, posEnd);
    }

    /**
     * Array slot.
     *
     * @return the array slot
     */
    public static ArraySlot ArraySlot() {
        return new ArraySlot();
    }

    /**
     * Array slot.
     *
     * @param expression the expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the array slot
     */
    public static ArraySlot ArraySlot(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ArraySlot(expression, annotations, posBegin, posEnd);
    }

    /**
     * Assert stmt.
     *
     * @return the assert stmt
     */
    public static AssertStmt AssertStmt() {
        return new AssertStmt();
    }

    /**
     * Assert stmt.
     *
     * @param check the check
     * @param message the message
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the assert stmt
     */
    public static AssertStmt AssertStmt(Expression check, Expression message, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AssertStmt(check, message, annotations, posBegin, posEnd);
    }

    /**
     * Assign expr.
     *
     * @return the assign expr
     */
    public static AssignExpr AssignExpr() {
        return new AssignExpr();
    }

    /**
     * Assign expr.
     *
     * @param target the target
     * @param value the value
     * @param operator the operator
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the assign expr
     */
    public static AssignExpr AssignExpr(Expression target, Expression value, AssignExpr.AssignOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new AssignExpr(target, value, operator, annotations, posBegin, posEnd);
    }

    /**
     * Binary expr.
     *
     * @return the binary expr
     */
    public static BinaryExpr BinaryExpr() {
        return new BinaryExpr();
    }

    /**
     * Binary expr.
     *
     * @param left the left
     * @param right the right
     * @param operator the operator
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the binary expr
     */
    public static BinaryExpr BinaryExpr(Expression left, Expression right, BinaryExpr.BinaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BinaryExpr(left, right, operator, annotations, posBegin, posEnd);
    }

    /**
     * Block comment.
     *
     * @return the block comment
     */
    public static BlockComment BlockComment() {
        return new BlockComment();
    }

    /**
     * Block comment.
     *
     * @param content the content
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the block comment
     */
    public static BlockComment BlockComment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BlockComment(content, annotations, posBegin, posEnd);
    }

    /**
     * Block stmt.
     *
     * @return the block stmt
     */
    public static BlockStmt BlockStmt() {
        return new BlockStmt();
    }

    /**
     * Block stmt.
     *
     * @param statements the statements
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the block stmt
     */
    public static BlockStmt BlockStmt(NodeList<Statement> statements, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BlockStmt(statements, annotations, posBegin, posEnd);
    }

    /**
     * Boolean literal expr.
     *
     * @return the boolean literal expr
     */
    public static BooleanLiteralExpr BooleanLiteralExpr() {
        return new BooleanLiteralExpr();
    }

    /**
     * Boolean literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the boolean literal expr
     */
    public static BooleanLiteralExpr BooleanLiteralExpr(boolean value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BooleanLiteralExpr(value, annotations, posBegin, posEnd);
    }

    /**
     * Break stmt.
     *
     * @return the break stmt
     */
    public static BreakStmt BreakStmt() {
        return new BreakStmt();
    }

    /**
     * Break stmt.
     *
     * @param id the id
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the break stmt
     */
    public static BreakStmt BreakStmt(String id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new BreakStmt(id, annotations, posBegin, posEnd);
    }

    /**
     * Cast expr.
     *
     * @return the cast expr
     */
    public static CastExpr CastExpr() {
        return new CastExpr();
    }

    /**
     * Cast expr.
     *
     * @param types the types
     * @param expression the expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the cast expr
     */
    public static CastExpr CastExpr(NodeList<Type> types, Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CastExpr(types, expression, annotations, posBegin, posEnd);
    }

    /**
     * Catch clause.
     *
     * @return the catch clause
     */
    public static CatchClause CatchClause() {
        return new CatchClause();
    }

    /**
     * Catch clause.
     *
     * @param isFinal the is final
     * @param types the types
     * @param name the name
     * @param catchBlock the catch block
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the catch clause
     */
    public static CatchClause CatchClause(boolean isFinal, NodeList<Type> types, String name, BlockStmt catchBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CatchClause(isFinal, types, name, catchBlock, annotations, posBegin, posEnd);
    }

    /**
     * Char literal expr.
     *
     * @return the char literal expr
     */
    public static CharLiteralExpr CharLiteralExpr() {
        return new CharLiteralExpr();
    }

    /**
     * Char literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the char literal expr
     */
    public static CharLiteralExpr CharLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CharLiteralExpr(value, annotations, posBegin, posEnd);
    }

    /**
     * Class declaration.
     *
     * @return the class declaration
     */
    public static ClassDeclaration ClassDeclaration() {
        return new ClassDeclaration();
    }

    /**
     * Class declaration.
     *
     * @param typeParameters the type parameters
     * @param extendsType the extends type
     * @param implementsList the implements list
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the class declaration
     */
    public static ClassDeclaration ClassDeclaration(NodeList<TypeParameter> typeParameters, ClassOrInterfaceType extendsType, NodeList<ClassOrInterfaceType> implementsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ClassDeclaration(typeParameters, extendsType, implementsList, modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Class expr.
     *
     * @return the class expr
     */
    public static ClassExpr ClassExpr() {
        return new ClassExpr();
    }

    /**
     * Class expr.
     *
     * @param type the type
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the class expr
     */
    public static ClassExpr ClassExpr(Type type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ClassExpr(type, annotations, posBegin, posEnd);
    }

    /**
     * Class or interface type.
     *
     * @return the class or interface type
     */
    public static ClassOrInterfaceType ClassOrInterfaceType() {
        return new ClassOrInterfaceType();
    }

    /**
     * Class or interface type.
     *
     * @param scope the scope
     * @param name the name
     * @param typeArgs the type args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the class or interface type
     */
    public static ClassOrInterfaceType ClassOrInterfaceType(ClassOrInterfaceType scope, NameExpr name, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ClassOrInterfaceType(scope, name, typeArgs, annotations, posBegin, posEnd);
    }

    /**
     * Compilation unit.
     *
     * @return the compilation unit
     */
    public static CompilationUnit CompilationUnit() {
        return new CompilationUnit();
    }

    /**
     * Compilation unit.
     *
     * @param packageDeclaration the package declaration
     * @param imports the imports
     * @param types the types
     * @param comments the comments
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the compilation unit
     */
    public static CompilationUnit CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration> types, NodeList<Comment> comments, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new CompilationUnit(packageDeclaration, imports, types, comments, name, annotations, posBegin, posEnd);
    }

    /**
     * Conditional expr.
     *
     * @return the conditional expr
     */
    public static ConditionalExpr ConditionalExpr() {
        return new ConditionalExpr();
    }

    /**
     * Conditional expr.
     *
     * @param condition the condition
     * @param thenExpression the then expression
     * @param elseExpression the else expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the conditional expr
     */
    public static ConditionalExpr ConditionalExpr(Expression condition, Expression thenExpression, Expression elseExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ConditionalExpr(condition, thenExpression, elseExpression, annotations, posBegin, posEnd);
    }

    /**
     * Constructor declaration.
     *
     * @return the constructor declaration
     */
    public static ConstructorDeclaration ConstructorDeclaration() {
        return new ConstructorDeclaration();
    }

    /**
     * Constructor declaration.
     *
     * @param modifiers the modifiers
     * @param typeParameters the type parameters
     * @param name the name
     * @param receiverType the receiver type
     * @param receiverQualifier the receiver qualifier
     * @param parameters the parameters
     * @param throwsList the throws list
     * @param block the block
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the constructor declaration
     */
    public static ConstructorDeclaration ConstructorDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, String name, Type receiverType, NameExpr receiverQualifier, NodeList<Parameter> parameters, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ConstructorDeclaration(modifiers, typeParameters, name, receiverType, receiverQualifier, parameters, throwsList, block, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Continue stmt.
     *
     * @return the continue stmt
     */
    public static ContinueStmt ContinueStmt() {
        return new ContinueStmt();
    }

    /**
     * Continue stmt.
     *
     * @param id the id
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the continue stmt
     */
    public static ContinueStmt ContinueStmt(String id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ContinueStmt(id, annotations, posBegin, posEnd);
    }

    /**
     * Do stmt.
     *
     * @return the do stmt
     */
    public static DoStmt DoStmt() {
        return new DoStmt();
    }

    /**
     * Do stmt.
     *
     * @param body the body
     * @param condition the condition
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the do stmt
     */
    public static DoStmt DoStmt(Statement body, Expression condition, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new DoStmt(body, condition, annotations, posBegin, posEnd);
    }

    /**
     * Double literal expr.
     *
     * @return the double literal expr
     */
    public static DoubleLiteralExpr DoubleLiteralExpr() {
        return new DoubleLiteralExpr();
    }

    /**
     * Double literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the double literal expr
     */
    public static DoubleLiteralExpr DoubleLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new DoubleLiteralExpr(value, annotations, posBegin, posEnd);
    }

    /**
     * Ellipsis.
     *
     * @return the ellipsis
     */
    public static Ellipsis Ellipsis() {
        return new Ellipsis();
    }

    /**
     * Ellipsis.
     *
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the ellipsis
     */
    public static Ellipsis Ellipsis(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Ellipsis(annotations, posBegin, posEnd);
    }

    /**
     * Empty member declaration.
     *
     * @return the empty member declaration
     */
    public static EmptyMemberDeclaration EmptyMemberDeclaration() {
        return new EmptyMemberDeclaration();
    }

    /**
     * Empty member declaration.
     *
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the empty member declaration
     */
    public static EmptyMemberDeclaration EmptyMemberDeclaration(JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EmptyMemberDeclaration(javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Empty stmt.
     *
     * @return the empty stmt
     */
    public static EmptyStmt EmptyStmt() {
        return new EmptyStmt();
    }

    /**
     * Empty stmt.
     *
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the empty stmt
     */
    public static EmptyStmt EmptyStmt(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EmptyStmt(annotations, posBegin, posEnd);
    }

    /**
     * Empty type declaration.
     *
     * @return the empty type declaration
     */
    public static EmptyTypeDeclaration EmptyTypeDeclaration() {
        return new EmptyTypeDeclaration();
    }

    /**
     * Empty type declaration.
     *
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the empty type declaration
     */
    public static EmptyTypeDeclaration EmptyTypeDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EmptyTypeDeclaration(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Enclosed expr.
     *
     * @return the enclosed expr
     */
    public static EnclosedExpr EnclosedExpr() {
        return new EnclosedExpr();
    }

    /**
     * Enclosed expr.
     *
     * @param inner the inner
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the enclosed expr
     */
    public static EnclosedExpr EnclosedExpr(Expression inner, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EnclosedExpr(inner, annotations, posBegin, posEnd);
    }

    /**
     * Enum constant declaration.
     *
     * @return the enum constant declaration
     */
    public static EnumConstantDeclaration EnumConstantDeclaration() {
        return new EnumConstantDeclaration();
    }

    /**
     * Enum constant declaration.
     *
     * @param name the name
     * @param args the args
     * @param classBody the class body
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the enum constant declaration
     */
    public static EnumConstantDeclaration EnumConstantDeclaration(String name, NodeList<Expression> args, NodeList<BodyDeclaration> classBody, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EnumConstantDeclaration(name, args, classBody, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Enum declaration.
     *
     * @return the enum declaration
     */
    public static EnumDeclaration EnumDeclaration() {
        return new EnumDeclaration();
    }

    /**
     * Enum declaration.
     *
     * @param implementsList the implements list
     * @param entries the entries
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the enum declaration
     */
    public static EnumDeclaration EnumDeclaration(NodeList<ClassOrInterfaceType> implementsList, NodeList<EnumConstantDeclaration> entries, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new EnumDeclaration(implementsList, entries, modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Explicit constructor invocation stmt.
     *
     * @return the explicit constructor invocation stmt
     */
    public static ExplicitConstructorInvocationStmt ExplicitConstructorInvocationStmt() {
        return new ExplicitConstructorInvocationStmt();
    }

    /**
     * Explicit constructor invocation stmt.
     *
     * @param typeArgs the type args
     * @param isThis the is this
     * @param expression the expression
     * @param args the args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the explicit constructor invocation stmt
     */
    public static ExplicitConstructorInvocationStmt ExplicitConstructorInvocationStmt(NodeList<Type> typeArgs, boolean isThis, Expression expression, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ExplicitConstructorInvocationStmt(typeArgs, isThis, expression, args, annotations, posBegin, posEnd);
    }

    /**
     * Expression stmt.
     *
     * @return the expression stmt
     */
    public static ExpressionStmt ExpressionStmt() {
        return new ExpressionStmt();
    }

    /**
     * Expression stmt.
     *
     * @param expression the expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the expression stmt
     */
    public static ExpressionStmt ExpressionStmt(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ExpressionStmt(expression, annotations, posBegin, posEnd);
    }

    /**
     * Field access expr.
     *
     * @return the field access expr
     */
    public static FieldAccessExpr FieldAccessExpr() {
        return new FieldAccessExpr();
    }

    /**
     * Field access expr.
     *
     * @param scope the scope
     * @param typeArgs the type args
     * @param field the field
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the field access expr
     */
    public static FieldAccessExpr FieldAccessExpr(Expression scope, NodeList<Type> typeArgs, String field, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new FieldAccessExpr(scope, typeArgs, field, annotations, posBegin, posEnd);
    }

    /**
     * Field declaration.
     *
     * @return the field declaration
     */
    public static FieldDeclaration FieldDeclaration() {
        return new FieldDeclaration();
    }

    /**
     * Field declaration.
     *
     * @param modifiers the modifiers
     * @param type the type
     * @param variables the variables
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the field declaration
     */
    public static FieldDeclaration FieldDeclaration(Modifiers modifiers, Type type, NodeList<VariableDeclarator> variables, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new FieldDeclaration(modifiers, type, variables, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Foreach stmt.
     *
     * @return the foreach stmt
     */
    public static ForeachStmt ForeachStmt() {
        return new ForeachStmt();
    }

    /**
     * Foreach stmt.
     *
     * @param variable the variable
     * @param iterable the iterable
     * @param body the body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the foreach stmt
     */
    public static ForeachStmt ForeachStmt(VariableDeclarationExpr variable, Expression iterable, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ForeachStmt(variable, iterable, body, annotations, posBegin, posEnd);
    }

    /**
     * For stmt.
     *
     * @return the for stmt
     */
    public static ForStmt ForStmt() {
        return new ForStmt();
    }

    /**
     * For stmt.
     *
     * @param init the init
     * @param compare the compare
     * @param update the update
     * @param body the body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the for stmt
     */
    public static ForStmt ForStmt(NodeList<Expression> init, Expression compare, NodeList<Expression> update, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ForStmt(init, compare, update, body, annotations, posBegin, posEnd);
    }

    /**
     * If stmt.
     *
     * @return the if stmt
     */
    public static IfStmt IfStmt() {
        return new IfStmt();
    }

    /**
     * If stmt.
     *
     * @param condition the condition
     * @param thenStmt the then stmt
     * @param elseStmt the else stmt
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the if stmt
     */
    public static IfStmt IfStmt(Expression condition, Statement thenStmt, Statement elseStmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new IfStmt(condition, thenStmt, elseStmt, annotations, posBegin, posEnd);
    }

    /**
     * Import declaration.
     *
     * @return the import declaration
     */
    public static ImportDeclaration ImportDeclaration() {
        return new ImportDeclaration();
    }

    /**
     * Import declaration.
     *
     * @param name the name
     * @param isStatic the is static
     * @param isAsterisk the is asterisk
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the import declaration
     */
    public static ImportDeclaration ImportDeclaration(NameExpr name, boolean isStatic, boolean isAsterisk, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ImportDeclaration(name, isStatic, isAsterisk, annotations, posBegin, posEnd);
    }

    /**
     * Initializer declaration.
     *
     * @return the initializer declaration
     */
    public static InitializerDeclaration InitializerDeclaration() {
        return new InitializerDeclaration();
    }

    /**
     * Initializer declaration.
     *
     * @param isStatic the is static
     * @param block the block
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the initializer declaration
     */
    public static InitializerDeclaration InitializerDeclaration(boolean isStatic, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new InitializerDeclaration(isStatic, block, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Instance of expr.
     *
     * @return the instance of expr
     */
    public static InstanceOfExpr InstanceOfExpr() {
        return new InstanceOfExpr();
    }

    /**
     * Instance of expr.
     *
     * @param expression the expression
     * @param type the type
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the instance of expr
     */
    public static InstanceOfExpr InstanceOfExpr(Expression expression, Type type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new InstanceOfExpr(expression, type, annotations, posBegin, posEnd);
    }

    /**
     * Integer literal expr.
     *
     * @return the integer literal expr
     */
    public static IntegerLiteralExpr IntegerLiteralExpr() {
        return new IntegerLiteralExpr();
    }

    /**
     * Integer literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the integer literal expr
     */
    public static IntegerLiteralExpr IntegerLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new IntegerLiteralExpr(value, annotations, posBegin, posEnd);
    }

    /**
     * Interface declaration.
     *
     * @return the interface declaration
     */
    public static InterfaceDeclaration InterfaceDeclaration() {
        return new InterfaceDeclaration();
    }

    /**
     * Interface declaration.
     *
     * @param typeParameters the type parameters
     * @param extendsList the extends list
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the interface declaration
     */
    public static InterfaceDeclaration InterfaceDeclaration(NodeList<TypeParameter> typeParameters, NodeList<ClassOrInterfaceType> extendsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new InterfaceDeclaration(typeParameters, extendsList, modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Javadoc comment.
     *
     * @return the javadoc comment
     */
    public static JavadocComment JavadocComment() {
        return new JavadocComment();
    }

    /**
     * Javadoc comment.
     *
     * @param content the content
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the javadoc comment
     */
    public static JavadocComment JavadocComment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new JavadocComment(content, annotations, posBegin, posEnd);
    }

    /**
     * Labeled stmt.
     *
     * @return the labeled stmt
     */
    public static LabeledStmt LabeledStmt() {
        return new LabeledStmt();
    }

    /**
     * Labeled stmt.
     *
     * @param label the label
     * @param stmt the stmt
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the labeled stmt
     */
    public static LabeledStmt LabeledStmt(String label, Statement stmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LabeledStmt(label, stmt, annotations, posBegin, posEnd);
    }

    /**
     * Lambda block.
     *
     * @return the lambda block
     */
    public static LambdaBlock LambdaBlock() {
        return new LambdaBlock();
    }

    /**
     * Lambda block.
     *
     * @param blockStmt the block stmt
     * @param parameters the parameters
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the lambda block
     */
    public static LambdaBlock LambdaBlock(BlockStmt blockStmt, NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LambdaBlock(blockStmt, parameters, annotations, posBegin, posEnd);
    }

    /**
     * Lambda expr.
     *
     * @return the lambda expr
     */
    public static LambdaExpr LambdaExpr() {
        return new LambdaExpr();
    }

    /**
     * Lambda expr.
     *
     * @param expression the expression
     * @param parameters the parameters
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the lambda expr
     */
    public static LambdaExpr LambdaExpr(Expression expression, NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LambdaExpr(expression, parameters, annotations, posBegin, posEnd);
    }

    /**
     * Line comment.
     *
     * @return the line comment
     */
    public static LineComment LineComment() {
        return new LineComment();
    }

    /**
     * Line comment.
     *
     * @param content the content
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the line comment
     */
    public static LineComment LineComment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LineComment(content, annotations, posBegin, posEnd);
    }

    /**
     * Long literal expr.
     *
     * @return the long literal expr
     */
    public static LongLiteralExpr LongLiteralExpr() {
        return new LongLiteralExpr();
    }

    /**
     * Long literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the long literal expr
     */
    public static LongLiteralExpr LongLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new LongLiteralExpr(value, annotations, posBegin, posEnd);
    }

    /**
     * Marker annotation expr.
     *
     * @return the marker annotation expr
     */
    public static MarkerAnnotationExpr MarkerAnnotationExpr() {
        return new MarkerAnnotationExpr();
    }

    /**
     * Marker annotation expr.
     *
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the marker annotation expr
     */
    public static MarkerAnnotationExpr MarkerAnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MarkerAnnotationExpr(name, annotations, posBegin, posEnd);
    }

    /**
     * Member value pair.
     *
     * @return the member value pair
     */
    public static MemberValuePair MemberValuePair() {
        return new MemberValuePair();
    }

    /**
     * Member value pair.
     *
     * @param name the name
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the member value pair
     */
    public static MemberValuePair MemberValuePair(String name, Expression value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MemberValuePair(name, value, annotations, posBegin, posEnd);
    }

    /**
     * Method call expr.
     *
     * @return the method call expr
     */
    public static MethodCallExpr MethodCallExpr() {
        return new MethodCallExpr();
    }

    /**
     * Method call expr.
     *
     * @param scope the scope
     * @param typeArgs the type args
     * @param name the name
     * @param args the args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the method call expr
     */
    public static MethodCallExpr MethodCallExpr(Expression scope, NodeList<Type> typeArgs, String name, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodCallExpr(scope, typeArgs, name, args, annotations, posBegin, posEnd);
    }

    /**
     * Method declaration.
     *
     * @return the method declaration
     */
    public static MethodDeclaration MethodDeclaration() {
        return new MethodDeclaration();
    }

    /**
     * Method declaration.
     *
     * @param modifiers the modifiers
     * @param typeParameters the type parameters
     * @param type the type
     * @param name the name
     * @param receiverType the receiver type
     * @param receiverQualifier the receiver qualifier
     * @param parameters the parameters
     * @param slots the slots
     * @param throwsList the throws list
     * @param block the block
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the method declaration
     */
    public static MethodDeclaration MethodDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, Type type, String name, Type receiverType, NameExpr receiverQualifier, NodeList<Parameter> parameters, NodeList<ArraySlot> slots, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodDeclaration(modifiers, typeParameters, type, name, receiverType, receiverQualifier, parameters, slots, throwsList, block, javaDoc, annotations, posBegin, posEnd);
    }

    /**
     * Method expr ref.
     *
     * @return the method expr ref
     */
    public static MethodExprRef MethodExprRef() {
        return new MethodExprRef();
    }

    /**
     * Method expr ref.
     *
     * @param scope the scope
     * @param typeArgs the type args
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the method expr ref
     */
    public static MethodExprRef MethodExprRef(Expression scope, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodExprRef(scope, typeArgs, name, annotations, posBegin, posEnd);
    }

    /**
     * Method ref.
     *
     * @return the method ref
     */
    public static MethodRef MethodRef() {
        return new MethodRef();
    }

    /**
     * Method ref.
     *
     * @param typeArgs the type args
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the method ref
     */
    public static MethodRef MethodRef(NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodRef(typeArgs, name, annotations, posBegin, posEnd);
    }

    /**
     * Method type ref.
     *
     * @return the method type ref
     */
    public static MethodTypeRef MethodTypeRef() {
        return new MethodTypeRef();
    }

    /**
     * Method type ref.
     *
     * @param type the type
     * @param typeArgs the type args
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the method type ref
     */
    public static MethodTypeRef MethodTypeRef(Type type, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new MethodTypeRef(type, typeArgs, name, annotations, posBegin, posEnd);
    }

    /**
     * Modifiers.
     *
     * @return the modifiers
     */
    public static Modifiers Modifiers() {
        return new Modifiers();
    }

    /**
     * Modifiers.
     *
     * @param modifiers the modifiers
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the modifiers
     */
    public static Modifiers Modifiers(int modifiers, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Modifiers(modifiers, annotations, posBegin, posEnd);
    }

    /**
     * Name expr.
     *
     * @return the name expr
     */
    public static NameExpr NameExpr() {
        return new NameExpr();
    }

    /**
     * Name expr.
     *
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the name expr
     */
    public static NameExpr NameExpr(String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NameExpr(name, annotations, posBegin, posEnd);
    }

    /**
     * Node list.
     *
     * @param <E> the element type
     * @return the node list
     */
    public static <E extends Node> NodeList<E> NodeList() {
        return new NodeList<E>();
    }

    /**
     * Node list.
     *
     * @param <E> the element type
     * @param nodes the nodes
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the node list
     */
    public static <E extends Node> NodeList<E> NodeList(List<E> nodes, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NodeList<E>(nodes, annotations, posBegin, posEnd);
    }

    /**
     * Normal annotation expr.
     *
     * @return the normal annotation expr
     */
    public static NormalAnnotationExpr NormalAnnotationExpr() {
        return new NormalAnnotationExpr();
    }

    /**
     * Normal annotation expr.
     *
     * @param pairs the pairs
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the normal annotation expr
     */
    public static NormalAnnotationExpr NormalAnnotationExpr(NodeList<MemberValuePair> pairs, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NormalAnnotationExpr(pairs, name, annotations, posBegin, posEnd);
    }

    /**
     * Null literal expr.
     *
     * @return the null literal expr
     */
    public static NullLiteralExpr NullLiteralExpr() {
        return new NullLiteralExpr();
    }

    /**
     * Null literal expr.
     *
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the null literal expr
     */
    public static NullLiteralExpr NullLiteralExpr(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new NullLiteralExpr(annotations, posBegin, posEnd);
    }

    /**
     * Object creation expr.
     *
     * @return the object creation expr
     */
    public static ObjectCreationExpr ObjectCreationExpr() {
        return new ObjectCreationExpr();
    }

    /**
     * Object creation expr.
     *
     * @param scope the scope
     * @param type the type
     * @param typeArgs the type args
     * @param args the args
     * @param anonymousClassBody the anonymous class body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the object creation expr
     */
    public static ObjectCreationExpr ObjectCreationExpr(Expression scope, ClassOrInterfaceType type, NodeList<Type> typeArgs, NodeList<Expression> args, NodeList<BodyDeclaration> anonymousClassBody, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ObjectCreationExpr(scope, type, typeArgs, args, anonymousClassBody, annotations, posBegin, posEnd);
    }

    /**
     * Package declaration.
     *
     * @return the package declaration
     */
    public static PackageDeclaration PackageDeclaration() {
        return new PackageDeclaration();
    }

    /**
     * Package declaration.
     *
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the package declaration
     */
    public static PackageDeclaration PackageDeclaration(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new PackageDeclaration(name, annotations, posBegin, posEnd);
    }

    /**
     * Parameter.
     *
     * @return the parameter
     */
    public static Parameter Parameter() {
        return new Parameter();
    }

    /**
     * Parameter.
     *
     * @param modifiers the modifiers
     * @param type the type
     * @param ellipsis the ellipsis
     * @param id the id
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the parameter
     */
    public static Parameter Parameter(Modifiers modifiers, Type type, Ellipsis ellipsis, VariableDeclaratorId id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Parameter(modifiers, type, ellipsis, id, annotations, posBegin, posEnd);
    }

    /**
     * Primitive type.
     *
     * @return the primitive type
     */
    public static PrimitiveType PrimitiveType() {
        return new PrimitiveType();
    }

    /**
     * Primitive type.
     *
     * @param type the type
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the primitive type
     */
    public static PrimitiveType PrimitiveType(PrimitiveType.Primitive type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new PrimitiveType(type, annotations, posBegin, posEnd);
    }

    /**
     * Project.
     *
     * @return the project
     */
    public static Project Project() {
        return new Project();
    }

    /**
     * Project.
     *
     * @param compilationUnits the compilation units
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the project
     */
    public static Project Project(NodeList<CompilationUnit> compilationUnits, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new Project(compilationUnits, annotations, posBegin, posEnd);
    }

    /**
     * Qualified name expr.
     *
     * @return the qualified name expr
     */
    public static QualifiedNameExpr QualifiedNameExpr() {
        return new QualifiedNameExpr();
    }

    /**
     * Qualified name expr.
     *
     * @param qualifier the qualifier
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the qualified name expr
     */
    public static QualifiedNameExpr QualifiedNameExpr(NameExpr qualifier, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new QualifiedNameExpr(qualifier, name, annotations, posBegin, posEnd);
    }

    /**
     * Reference type.
     *
     * @return the reference type
     */
    public static ReferenceType ReferenceType() {
        return new ReferenceType();
    }

    /**
     * Reference type.
     *
     * @param type the type
     * @param slots the slots
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the reference type
     */
    public static ReferenceType ReferenceType(Type type, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ReferenceType(type, slots, annotations, posBegin, posEnd);
    }

    /**
     * Return stmt.
     *
     * @return the return stmt
     */
    public static ReturnStmt ReturnStmt() {
        return new ReturnStmt();
    }

    /**
     * Return stmt.
     *
     * @param expression the expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the return stmt
     */
    public static ReturnStmt ReturnStmt(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ReturnStmt(expression, annotations, posBegin, posEnd);
    }

    /**
     * Single member annotation expr.
     *
     * @return the single member annotation expr
     */
    public static SingleMemberAnnotationExpr SingleMemberAnnotationExpr() {
        return new SingleMemberAnnotationExpr();
    }

    /**
     * Single member annotation expr.
     *
     * @param memberValue the member value
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the single member annotation expr
     */
    public static SingleMemberAnnotationExpr SingleMemberAnnotationExpr(Expression memberValue, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SingleMemberAnnotationExpr(memberValue, name, annotations, posBegin, posEnd);
    }

    /**
     * String literal expr.
     *
     * @return the string literal expr
     */
    public static StringLiteralExpr StringLiteralExpr() {
        return new StringLiteralExpr();
    }

    /**
     * String literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the string literal expr
     */
    public static StringLiteralExpr StringLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new StringLiteralExpr(value, annotations, posBegin, posEnd);
    }

    /**
     * Super expr.
     *
     * @return the super expr
     */
    public static SuperExpr SuperExpr() {
        return new SuperExpr();
    }

    /**
     * Super expr.
     *
     * @param classExpression the class expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the super expr
     */
    public static SuperExpr SuperExpr(Expression classExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SuperExpr(classExpression, annotations, posBegin, posEnd);
    }

    /**
     * Switch entry stmt.
     *
     * @return the switch entry stmt
     */
    public static SwitchEntryStmt SwitchEntryStmt() {
        return new SwitchEntryStmt();
    }

    /**
     * Switch entry stmt.
     *
     * @param label the label
     * @param stmts the stmts
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the switch entry stmt
     */
    public static SwitchEntryStmt SwitchEntryStmt(Expression label, NodeList<Statement> stmts, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SwitchEntryStmt(label, stmts, annotations, posBegin, posEnd);
    }

    /**
     * Switch stmt.
     *
     * @return the switch stmt
     */
    public static SwitchStmt SwitchStmt() {
        return new SwitchStmt();
    }

    /**
     * Switch stmt.
     *
     * @param selector the selector
     * @param entries the entries
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the switch stmt
     */
    public static SwitchStmt SwitchStmt(Expression selector, NodeList<SwitchEntryStmt> entries, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SwitchStmt(selector, entries, annotations, posBegin, posEnd);
    }

    /**
     * Synchronized stmt.
     *
     * @return the synchronized stmt
     */
    public static SynchronizedStmt SynchronizedStmt() {
        return new SynchronizedStmt();
    }

    /**
     * Synchronized stmt.
     *
     * @param expression the expression
     * @param block the block
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the synchronized stmt
     */
    public static SynchronizedStmt SynchronizedStmt(Expression expression, BlockStmt block, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new SynchronizedStmt(expression, block, annotations, posBegin, posEnd);
    }

    /**
     * This expr.
     *
     * @return the this expr
     */
    public static ThisExpr ThisExpr() {
        return new ThisExpr();
    }

    /**
     * This expr.
     *
     * @param classExpression the class expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the this expr
     */
    public static ThisExpr ThisExpr(Expression classExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ThisExpr(classExpression, annotations, posBegin, posEnd);
    }

    /**
     * Throw stmt.
     *
     * @return the throw stmt
     */
    public static ThrowStmt ThrowStmt() {
        return new ThrowStmt();
    }

    /**
     * Throw stmt.
     *
     * @param expression the expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the throw stmt
     */
    public static ThrowStmt ThrowStmt(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new ThrowStmt(expression, annotations, posBegin, posEnd);
    }

    /**
     * Try stmt.
     *
     * @return the try stmt
     */
    public static TryStmt TryStmt() {
        return new TryStmt();
    }

    /**
     * Try stmt.
     *
     * @param resources the resources
     * @param tryBlock the try block
     * @param catchClauses the catch clauses
     * @param finallyBlock the finally block
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the try stmt
     */
    public static TryStmt TryStmt(NodeList<VariableDeclarationExpr> resources, BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TryStmt(resources, tryBlock, catchClauses, finallyBlock, annotations, posBegin, posEnd);
    }

    /**
     * Type declaration stmt.
     *
     * @return the type declaration stmt
     */
    public static TypeDeclarationStmt TypeDeclarationStmt() {
        return new TypeDeclarationStmt();
    }

    /**
     * Type declaration stmt.
     *
     * @param typeDeclaration the type declaration
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the type declaration stmt
     */
    public static TypeDeclarationStmt TypeDeclarationStmt(TypeDeclaration typeDeclaration, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TypeDeclarationStmt(typeDeclaration, annotations, posBegin, posEnd);
    }

    /**
     * Type parameter.
     *
     * @return the type parameter
     */
    public static TypeParameter TypeParameter() {
        return new TypeParameter();
    }

    /**
     * Type parameter.
     *
     * @param name the name
     * @param typeBound the type bound
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the type parameter
     */
    public static TypeParameter TypeParameter(String name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new TypeParameter(name, typeBound, annotations, posBegin, posEnd);
    }

    /**
     * Unary expr.
     *
     * @return the unary expr
     */
    public static UnaryExpr UnaryExpr() {
        return new UnaryExpr();
    }

    /**
     * Unary expr.
     *
     * @param expression the expression
     * @param operator the operator
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the unary expr
     */
    public static UnaryExpr UnaryExpr(Expression expression, UnaryExpr.UnaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new UnaryExpr(expression, operator, annotations, posBegin, posEnd);
    }

    /**
     * Variable declaration expr.
     *
     * @return the variable declaration expr
     */
    public static VariableDeclarationExpr VariableDeclarationExpr() {
        return new VariableDeclarationExpr();
    }

    /**
     * Variable declaration expr.
     *
     * @param modifiers the modifiers
     * @param type the type
     * @param vars the vars
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the variable declaration expr
     */
    public static VariableDeclarationExpr VariableDeclarationExpr(Modifiers modifiers, Type type, NodeList<VariableDeclarator> vars, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VariableDeclarationExpr(modifiers, type, vars, annotations, posBegin, posEnd);
    }

    /**
     * Variable declarator.
     *
     * @return the variable declarator
     */
    public static VariableDeclarator VariableDeclarator() {
        return new VariableDeclarator();
    }

    /**
     * Variable declarator.
     *
     * @param id the id
     * @param init the init
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the variable declarator
     */
    public static VariableDeclarator VariableDeclarator(VariableDeclaratorId id, Expression init, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VariableDeclarator(id, init, annotations, posBegin, posEnd);
    }

    /**
     * Variable declarator id.
     *
     * @return the variable declarator id
     */
    public static VariableDeclaratorId VariableDeclaratorId() {
        return new VariableDeclaratorId();
    }

    /**
     * Variable declarator id.
     *
     * @param name the name
     * @param slots the slots
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the variable declarator id
     */
    public static VariableDeclaratorId VariableDeclaratorId(String name, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VariableDeclaratorId(name, slots, annotations, posBegin, posEnd);
    }

    /**
     * Void type.
     *
     * @return the void type
     */
    public static VoidType VoidType() {
        return new VoidType();
    }

    /**
     * Void type.
     *
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the void type
     */
    public static VoidType VoidType(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new VoidType(annotations, posBegin, posEnd);
    }

    /**
     * While stmt.
     *
     * @return the while stmt
     */
    public static WhileStmt WhileStmt() {
        return new WhileStmt();
    }

    /**
     * While stmt.
     *
     * @param condition the condition
     * @param body the body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the while stmt
     */
    public static WhileStmt WhileStmt(Expression condition, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new WhileStmt(condition, body, annotations, posBegin, posEnd);
    }

    /**
     * Wildcard type.
     *
     * @return the wildcard type
     */
    public static WildcardType WildcardType() {
        return new WildcardType();
    }

    /**
     * Wildcard type.
     *
     * @param extendsType the extends type
     * @param superType the super type
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     * @return the wildcard type
     */
    public static WildcardType WildcardType(ReferenceType extendsType, ReferenceType superType, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        return new WildcardType(extendsType, superType, annotations, posBegin, posEnd);
    }

}
