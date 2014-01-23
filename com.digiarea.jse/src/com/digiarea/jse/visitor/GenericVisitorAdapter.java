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

import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationDeclaration;
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
import com.digiarea.jse.BooleanLiteralExpr;
import com.digiarea.jse.BreakStmt;
import com.digiarea.jse.CastExpr;
import com.digiarea.jse.CatchClause;
import com.digiarea.jse.CharLiteralExpr;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassExpr;
import com.digiarea.jse.ClassOrInterfaceType;
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
import com.digiarea.jse.StringLiteralExpr;
import com.digiarea.jse.SuperExpr;
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.SwitchStmt;
import com.digiarea.jse.SynchronizedStmt;
import com.digiarea.jse.ThisExpr;
import com.digiarea.jse.ThrowStmt;
import com.digiarea.jse.TryStmt;
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

public class GenericVisitorAdapter<R, C> implements GenericVisitor<R, C> {

    @Override
    public R visit(AnnotationDeclaration n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getMembers() != null) {
            n.getMembers().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(AnnotationMemberDeclaration n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getDefaultValue() != null) {
            n.getDefaultValue().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ArrayAccessExpr n, C ctx) throws Exception {
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getIndex() != null) {
            n.getIndex().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ArrayCreationExpr n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getSlots() != null) {
            n.getSlots().accept(this, ctx);
        }
        if (n.getInitializer() != null) {
            n.getInitializer().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ArrayInitializerExpr n, C ctx) throws Exception {
        if (n.getValues() != null) {
            n.getValues().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ArraySlot n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(AssertStmt n, C ctx) throws Exception {
        if (n.getCheck() != null) {
            n.getCheck().accept(this, ctx);
        }
        if (n.getMessage() != null) {
            n.getMessage().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(AssignExpr n, C ctx) throws Exception {
        if (n.getTarget() != null) {
            n.getTarget().accept(this, ctx);
        }
        if (n.getValue() != null) {
            n.getValue().accept(this, ctx);
        }
        if (n.getOperator() != null) {
            n.getOperator().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(AssignOperator n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(BinaryExpr n, C ctx) throws Exception {
        if (n.getLeft() != null) {
            n.getLeft().accept(this, ctx);
        }
        if (n.getRight() != null) {
            n.getRight().accept(this, ctx);
        }
        if (n.getOperator() != null) {
            n.getOperator().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(BinaryOperator n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(BlockComment n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(BlockStmt n, C ctx) throws Exception {
        if (n.getStatements() != null) {
            n.getStatements().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(BooleanLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(BreakStmt n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(CastExpr n, C ctx) throws Exception {
        if (n.getTypes() != null) {
            n.getTypes().accept(this, ctx);
        }
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(CatchClause n, C ctx) throws Exception {
        if (n.getTypes() != null) {
            n.getTypes().accept(this, ctx);
        }
        if (n.getCatchBlock() != null) {
            n.getCatchBlock().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(CharLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ClassDeclaration n, C ctx) throws Exception {
        if (n.getTypeParameters() != null) {
            n.getTypeParameters().accept(this, ctx);
        }
        if (n.getExtendsType() != null) {
            n.getExtendsType().accept(this, ctx);
        }
        if (n.getImplementsList() != null) {
            n.getImplementsList().accept(this, ctx);
        }
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getMembers() != null) {
            n.getMembers().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ClassExpr n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ClassOrInterfaceType n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(CompilationUnit n, C ctx) throws Exception {
        if (n.getPackageDeclaration() != null) {
            n.getPackageDeclaration().accept(this, ctx);
        }
        if (n.getImports() != null) {
            n.getImports().accept(this, ctx);
        }
        if (n.getTypes() != null) {
            n.getTypes().accept(this, ctx);
        }
        if (n.getComments() != null) {
            n.getComments().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ConditionalExpr n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getThenExpression() != null) {
            n.getThenExpression().accept(this, ctx);
        }
        if (n.getElseExpression() != null) {
            n.getElseExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ConstructorDeclaration n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getTypeParameters() != null) {
            n.getTypeParameters().accept(this, ctx);
        }
        if (n.getParameters() != null) {
            n.getParameters().accept(this, ctx);
        }
        if (n.getThrowsList() != null) {
            n.getThrowsList().accept(this, ctx);
        }
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ContinueStmt n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(DoStmt n, C ctx) throws Exception {
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(DoubleLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Ellipsis n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(EmptyMemberDeclaration n, C ctx) throws Exception {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(EmptyStmt n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(EmptyTypeDeclaration n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getMembers() != null) {
            n.getMembers().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(EnclosedExpr n, C ctx) throws Exception {
        if (n.getInner() != null) {
            n.getInner().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(EnumConstantDeclaration n, C ctx) throws Exception {
        if (n.getArgs() != null) {
            n.getArgs().accept(this, ctx);
        }
        if (n.getClassBody() != null) {
            n.getClassBody().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(EnumDeclaration n, C ctx) throws Exception {
        if (n.getImplementsList() != null) {
            n.getImplementsList().accept(this, ctx);
        }
        if (n.getEntries() != null) {
            n.getEntries().accept(this, ctx);
        }
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getMembers() != null) {
            n.getMembers().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ExplicitConstructorInvocationStmt n, C ctx) throws Exception {
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getArgs() != null) {
            n.getArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ExpressionStmt n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(FieldAccessExpr n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(FieldDeclaration n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getVariables() != null) {
            n.getVariables().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ForeachStmt n, C ctx) throws Exception {
        if (n.getVariable() != null) {
            n.getVariable().accept(this, ctx);
        }
        if (n.getIterable() != null) {
            n.getIterable().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ForStmt n, C ctx) throws Exception {
        if (n.getInit() != null) {
            n.getInit().accept(this, ctx);
        }
        if (n.getCompare() != null) {
            n.getCompare().accept(this, ctx);
        }
        if (n.getUpdate() != null) {
            n.getUpdate().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(IfStmt n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getThenStmt() != null) {
            n.getThenStmt().accept(this, ctx);
        }
        if (n.getElseStmt() != null) {
            n.getElseStmt().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ImportDeclaration n, C ctx) throws Exception {
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(InitializerDeclaration n, C ctx) throws Exception {
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(InstanceOfExpr n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(IntegerLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(InterfaceDeclaration n, C ctx) throws Exception {
        if (n.getTypeParameters() != null) {
            n.getTypeParameters().accept(this, ctx);
        }
        if (n.getExtendsList() != null) {
            n.getExtendsList().accept(this, ctx);
        }
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getMembers() != null) {
            n.getMembers().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(JavadocComment n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(LabeledStmt n, C ctx) throws Exception {
        if (n.getStmt() != null) {
            n.getStmt().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(LambdaBlock n, C ctx) throws Exception {
        if (n.getBlockStmt() != null) {
            n.getBlockStmt().accept(this, ctx);
        }
        if (n.getParameters() != null) {
            n.getParameters().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(LambdaExpr n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getParameters() != null) {
            n.getParameters().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(LineComment n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(LongLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MarkerAnnotationExpr n, C ctx) throws Exception {
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MemberValuePair n, C ctx) throws Exception {
        if (n.getValue() != null) {
            n.getValue().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MethodCallExpr n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getArgs() != null) {
            n.getArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MethodDeclaration n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getTypeParameters() != null) {
            n.getTypeParameters().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getParameters() != null) {
            n.getParameters().accept(this, ctx);
        }
        if (n.getSlots() != null) {
            n.getSlots().accept(this, ctx);
        }
        if (n.getThrowsList() != null) {
            n.getThrowsList().accept(this, ctx);
        }
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MethodExprRef n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MethodRef n, C ctx) throws Exception {
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(MethodTypeRef n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Modifiers n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(NameExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public <E extends Node> R visit(NodeList<E> n, C ctx) throws Exception {
        if (n.getNodes() != null) {
            for (E item : n.getNodes()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(NormalAnnotationExpr n, C ctx) throws Exception {
        if (n.getPairs() != null) {
            n.getPairs().accept(this, ctx);
        }
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(NullLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ObjectCreationExpr n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getTypeArgs() != null) {
            n.getTypeArgs().accept(this, ctx);
        }
        if (n.getArgs() != null) {
            n.getArgs().accept(this, ctx);
        }
        if (n.getAnonymousClassBody() != null) {
            n.getAnonymousClassBody().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(PackageDeclaration n, C ctx) throws Exception {
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Parameter n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getEllipsis() != null) {
            n.getEllipsis().accept(this, ctx);
        }
        if (n.getId() != null) {
            n.getId().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(PrimitiveType n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Primitive n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Project n, C ctx) throws Exception {
        if (n.getCompilationUnits() != null) {
            n.getCompilationUnits().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(QualifiedNameExpr n, C ctx) throws Exception {
        if (n.getQualifier() != null) {
            n.getQualifier().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ReferenceType n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getSlots() != null) {
            n.getSlots().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ReturnStmt n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(SingleMemberAnnotationExpr n, C ctx) throws Exception {
        if (n.getMemberValue() != null) {
            n.getMemberValue().accept(this, ctx);
        }
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(StringLiteralExpr n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(SuperExpr n, C ctx) throws Exception {
        if (n.getClassExpression() != null) {
            n.getClassExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(SwitchEntryStmt n, C ctx) throws Exception {
        if (n.getLabel() != null) {
            n.getLabel().accept(this, ctx);
        }
        if (n.getStmts() != null) {
            n.getStmts().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(SwitchStmt n, C ctx) throws Exception {
        if (n.getSelector() != null) {
            n.getSelector().accept(this, ctx);
        }
        if (n.getEntries() != null) {
            n.getEntries().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(SynchronizedStmt n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ThisExpr n, C ctx) throws Exception {
        if (n.getClassExpression() != null) {
            n.getClassExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ThrowStmt n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(TryStmt n, C ctx) throws Exception {
        if (n.getResources() != null) {
            n.getResources().accept(this, ctx);
        }
        if (n.getTryBlock() != null) {
            n.getTryBlock().accept(this, ctx);
        }
        if (n.getCatchClauses() != null) {
            n.getCatchClauses().accept(this, ctx);
        }
        if (n.getFinallyBlock() != null) {
            n.getFinallyBlock().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(TypeDeclarationStmt n, C ctx) throws Exception {
        if (n.getTypeDeclaration() != null) {
            n.getTypeDeclaration().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(TypeParameter n, C ctx) throws Exception {
        if (n.getTypeBound() != null) {
            n.getTypeBound().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(UnaryExpr n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getOperator() != null) {
            n.getOperator().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(UnaryOperator n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(VariableDeclarationExpr n, C ctx) throws Exception {
        if (n.getModifiers() != null) {
            n.getModifiers().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        if (n.getVars() != null) {
            n.getVars().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(VariableDeclarator n, C ctx) throws Exception {
        if (n.getId() != null) {
            n.getId().accept(this, ctx);
        }
        if (n.getInit() != null) {
            n.getInit().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(VariableDeclaratorId n, C ctx) throws Exception {
        if (n.getSlots() != null) {
            n.getSlots().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(VoidType n, C ctx) throws Exception {
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(WhileStmt n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(WildcardType n, C ctx) throws Exception {
        if (n.getExtendsType() != null) {
            n.getExtendsType().accept(this, ctx);
        }
        if (n.getSuperType() != null) {
            n.getSuperType().accept(this, ctx);
        }
        if (n.getAnnotations() != null) {
            n.getAnnotations().accept(this, ctx);
        }
        return null;
    }

    public GenericVisitorAdapter() {
        super();
    }

}
