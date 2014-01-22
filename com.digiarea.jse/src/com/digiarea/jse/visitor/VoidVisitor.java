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
package com.digiarea.jse.visitor;

import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.AnnotationMemberDeclaration;
import com.digiarea.jse.ArrayAccessExpr;
import com.digiarea.jse.ArrayCreationExpr;
import com.digiarea.jse.ArrayInitializerExpr;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.AssertStmt;
import com.digiarea.jse.AssignExpr;
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.BlockComment;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.BooleanLiteralExpr;
import com.digiarea.jse.BreakStmt;
import com.digiarea.jse.CastExpr;
import com.digiarea.jse.CatchClause;
import com.digiarea.jse.CharLiteralExpr;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassExpr;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Comment;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ConditionalExpr;
import com.digiarea.jse.ConstructorDeclaration;
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
import com.digiarea.jse.Expression;
import com.digiarea.jse.ExpressionStmt;
import com.digiarea.jse.FieldAccessExpr;
import com.digiarea.jse.FieldDeclaration;
import com.digiarea.jse.ForeachStmt;
import com.digiarea.jse.ForStmt;
import com.digiarea.jse.IfStmt;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.InitializerDeclaration;
import com.digiarea.jse.InstanceOfExpr;
import com.digiarea.jse.IntegerLiteralExpr;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.JavadocComment;
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
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.NullLiteralExpr;
import com.digiarea.jse.ObjectCreationExpr;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.PrimitiveType.Primitive;
import com.digiarea.jse.Project;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.ReturnStmt;
import com.digiarea.jse.SingleMemberAnnotationExpr;
import com.digiarea.jse.Statement;
import com.digiarea.jse.StringLiteralExpr;
import com.digiarea.jse.SuperExpr;
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.SwitchStmt;
import com.digiarea.jse.SynchronizedStmt;
import com.digiarea.jse.ThisExpr;
import com.digiarea.jse.ThrowStmt;
import com.digiarea.jse.TryStmt;
import com.digiarea.jse.Type;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.TypeDeclarationStmt;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.UnaryExpr;
import com.digiarea.jse.UnaryExpr.UnaryOperator;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.VoidType;
import com.digiarea.jse.WhileStmt;
import com.digiarea.jse.WildcardType;

public interface VoidVisitor<C> {

    public void visit(AnnotationDeclaration n, C ctx) throws Exception;

    public void visit(AnnotationExpr n, C ctx) throws Exception;

    public void visit(AnnotationMemberDeclaration n, C ctx) throws Exception;

    public void visit(ArrayAccessExpr n, C ctx) throws Exception;

    public void visit(ArrayCreationExpr n, C ctx) throws Exception;

    public void visit(ArrayInitializerExpr n, C ctx) throws Exception;

    public void visit(ArraySlot n, C ctx) throws Exception;

    public void visit(AssertStmt n, C ctx) throws Exception;

    public void visit(AssignExpr n, C ctx) throws Exception;

    public void visit(AssignOperator n, C ctx) throws Exception;

    public void visit(BinaryExpr n, C ctx) throws Exception;

    public void visit(BinaryOperator n, C ctx) throws Exception;

    public void visit(BlockComment n, C ctx) throws Exception;

    public void visit(BlockStmt n, C ctx) throws Exception;

    public void visit(BodyDeclaration n, C ctx) throws Exception;

    public void visit(BooleanLiteralExpr n, C ctx) throws Exception;

    public void visit(BreakStmt n, C ctx) throws Exception;

    public void visit(CastExpr n, C ctx) throws Exception;

    public void visit(CatchClause n, C ctx) throws Exception;

    public void visit(CharLiteralExpr n, C ctx) throws Exception;

    public void visit(ClassDeclaration n, C ctx) throws Exception;

    public void visit(ClassExpr n, C ctx) throws Exception;

    public void visit(ClassOrInterfaceType n, C ctx) throws Exception;

    public void visit(Comment n, C ctx) throws Exception;

    public void visit(CompilationUnit n, C ctx) throws Exception;

    public void visit(ConditionalExpr n, C ctx) throws Exception;

    public void visit(ConstructorDeclaration n, C ctx) throws Exception;

    public void visit(ContinueStmt n, C ctx) throws Exception;

    public void visit(DoStmt n, C ctx) throws Exception;

    public void visit(DoubleLiteralExpr n, C ctx) throws Exception;

    public void visit(Ellipsis n, C ctx) throws Exception;

    public void visit(EmptyMemberDeclaration n, C ctx) throws Exception;

    public void visit(EmptyStmt n, C ctx) throws Exception;

    public void visit(EmptyTypeDeclaration n, C ctx) throws Exception;

    public void visit(EnclosedExpr n, C ctx) throws Exception;

    public void visit(EnumConstantDeclaration n, C ctx) throws Exception;

    public void visit(EnumDeclaration n, C ctx) throws Exception;

    public void visit(ExplicitConstructorInvocationStmt n, C ctx) throws Exception;

    public void visit(Expression n, C ctx) throws Exception;

    public void visit(ExpressionStmt n, C ctx) throws Exception;

    public void visit(FieldAccessExpr n, C ctx) throws Exception;

    public void visit(FieldDeclaration n, C ctx) throws Exception;

    public void visit(ForeachStmt n, C ctx) throws Exception;

    public void visit(ForStmt n, C ctx) throws Exception;

    public void visit(IfStmt n, C ctx) throws Exception;

    public void visit(ImportDeclaration n, C ctx) throws Exception;

    public void visit(InitializerDeclaration n, C ctx) throws Exception;

    public void visit(InstanceOfExpr n, C ctx) throws Exception;

    public void visit(IntegerLiteralExpr n, C ctx) throws Exception;

    public void visit(InterfaceDeclaration n, C ctx) throws Exception;

    public void visit(JavadocComment n, C ctx) throws Exception;

    public void visit(LabeledStmt n, C ctx) throws Exception;

    public void visit(Lambda n, C ctx) throws Exception;

    public void visit(LambdaBlock n, C ctx) throws Exception;

    public void visit(LambdaExpr n, C ctx) throws Exception;

    public void visit(LineComment n, C ctx) throws Exception;

    public void visit(LiteralExpr n, C ctx) throws Exception;

    public void visit(LongLiteralExpr n, C ctx) throws Exception;

    public void visit(MarkerAnnotationExpr n, C ctx) throws Exception;

    public void visit(MemberValuePair n, C ctx) throws Exception;

    public void visit(MethodCallExpr n, C ctx) throws Exception;

    public void visit(MethodDeclaration n, C ctx) throws Exception;

    public void visit(MethodExprRef n, C ctx) throws Exception;

    public void visit(MethodRef n, C ctx) throws Exception;

    public void visit(MethodTypeRef n, C ctx) throws Exception;

    public void visit(Modifiers n, C ctx) throws Exception;

    public void visit(NameExpr n, C ctx) throws Exception;

    public <E extends Node> void visit(NodeList<E> n, C ctx) throws Exception;

    public void visit(NormalAnnotationExpr n, C ctx) throws Exception;

    public void visit(NullLiteralExpr n, C ctx) throws Exception;

    public void visit(ObjectCreationExpr n, C ctx) throws Exception;

    public void visit(PackageDeclaration n, C ctx) throws Exception;

    public void visit(Parameter n, C ctx) throws Exception;

    public void visit(PrimitiveType n, C ctx) throws Exception;

    public void visit(Primitive n, C ctx) throws Exception;

    public void visit(Project n, C ctx) throws Exception;

    public void visit(QualifiedNameExpr n, C ctx) throws Exception;

    public void visit(ReferenceType n, C ctx) throws Exception;

    public void visit(ReturnStmt n, C ctx) throws Exception;

    public void visit(SingleMemberAnnotationExpr n, C ctx) throws Exception;

    public void visit(Statement n, C ctx) throws Exception;

    public void visit(StringLiteralExpr n, C ctx) throws Exception;

    public void visit(SuperExpr n, C ctx) throws Exception;

    public void visit(SwitchEntryStmt n, C ctx) throws Exception;

    public void visit(SwitchStmt n, C ctx) throws Exception;

    public void visit(SynchronizedStmt n, C ctx) throws Exception;

    public void visit(ThisExpr n, C ctx) throws Exception;

    public void visit(ThrowStmt n, C ctx) throws Exception;

    public void visit(TryStmt n, C ctx) throws Exception;

    public void visit(Type n, C ctx) throws Exception;

    public void visit(TypeDeclaration n, C ctx) throws Exception;

    public void visit(TypeDeclarationStmt n, C ctx) throws Exception;

    public void visit(TypeParameter n, C ctx) throws Exception;

    public void visit(UnaryExpr n, C ctx) throws Exception;

    public void visit(UnaryOperator n, C ctx) throws Exception;

    public void visit(VariableDeclarationExpr n, C ctx) throws Exception;

    public void visit(VariableDeclarator n, C ctx) throws Exception;

    public void visit(VariableDeclaratorId n, C ctx) throws Exception;

    public void visit(VoidType n, C ctx) throws Exception;

    public void visit(WhileStmt n, C ctx) throws Exception;

    public void visit(WildcardType n, C ctx) throws Exception;

}
