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

import java.util.List;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationDeclaration;
import java.util.ArrayList;
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
import com.digiarea.jse.CreationReference;
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
import com.digiarea.jse.ExpressionMethodReference;
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
import com.digiarea.jse.SuperMethodReference;
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.SwitchStmt;
import com.digiarea.jse.SynchronizedStmt;
import com.digiarea.jse.ThisExpr;
import com.digiarea.jse.ThrowStmt;
import com.digiarea.jse.TryStmt;
import com.digiarea.jse.TypeDeclarationStmt;
import com.digiarea.jse.TypeMethodReference;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.UnaryExpr;
import com.digiarea.jse.UnaryExpr.UnaryOperator;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.VoidType;
import com.digiarea.jse.WhileStmt;
import com.digiarea.jse.WildcardType;

/**
 * The Class ChildrenProvider.
 *
 * @param <C> the generic type
 */
public class ChildrenProvider<C> implements GenericVisitor<List<Object>, Void> {

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AnnotationDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(AnnotationDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        img.add(n.getName());
        if (n.getMembers() != null) {
            img.add(n.getMembers());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AnnotationMemberDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(AnnotationMemberDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        img.add(n.getName());
        if (n.getDefaultValue() != null) {
            img.add(n.getDefaultValue());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArrayAccessExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ArrayAccessExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getIndex() != null) {
            img.add(n.getIndex());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArrayCreationExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ArrayCreationExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getSlots() != null) {
            img.add(n.getSlots());
        }
        if (n.getInitializer() != null) {
            img.add(n.getInitializer());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArrayInitializerExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ArrayInitializerExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getValues() != null) {
            img.add(n.getValues());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArraySlot, java.lang.Object)
     */
    @Override
    public List<Object> visit(ArraySlot n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssertStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(AssertStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCheck() != null) {
            img.add(n.getCheck());
        }
        if (n.getMessage() != null) {
            img.add(n.getMessage());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssignExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(AssignExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTarget() != null) {
            img.add(n.getTarget());
        }
        if (n.getValue() != null) {
            img.add(n.getValue());
        }
        img.add(n.getOperator());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssignExpr.AssignOperator, java.lang.Object)
     */
    @Override
    public List<Object> visit(AssignOperator n, Void ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BinaryExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(BinaryExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getLeft() != null) {
            img.add(n.getLeft());
        }
        if (n.getRight() != null) {
            img.add(n.getRight());
        }
        img.add(n.getOperator());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BinaryExpr.BinaryOperator, java.lang.Object)
     */
    @Override
    public List<Object> visit(BinaryOperator n, Void ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BlockComment, java.lang.Object)
     */
    @Override
    public List<Object> visit(BlockComment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getContent());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BlockStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(BlockStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getStatements() != null) {
            img.add(n.getStatements());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BooleanLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(BooleanLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.isValue());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BreakStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(BreakStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getId());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CastExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(CastExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTypes() != null) {
            img.add(n.getTypes());
        }
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CatchClause, java.lang.Object)
     */
    @Override
    public List<Object> visit(CatchClause n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.isFinal());
        if (n.getTypes() != null) {
            img.add(n.getTypes());
        }
        img.add(n.getName());
        if (n.getCatchBlock() != null) {
            img.add(n.getCatchBlock());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CharLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(CharLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(ClassDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTypeParameters() != null) {
            img.add(n.getTypeParameters());
        }
        if (n.getExtendsType() != null) {
            img.add(n.getExtendsType());
        }
        if (n.getImplementsList() != null) {
            img.add(n.getImplementsList());
        }
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        img.add(n.getName());
        if (n.getMembers() != null) {
            img.add(n.getMembers());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ClassExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassOrInterfaceType, java.lang.Object)
     */
    @Override
    public List<Object> visit(ClassOrInterfaceType n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getScope() != null) {
            img.add(n.getScope());
        }
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CompilationUnit, java.lang.Object)
     */
    @Override
    public List<Object> visit(CompilationUnit n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getPackageDeclaration() != null) {
            img.add(n.getPackageDeclaration());
        }
        if (n.getImports() != null) {
            img.add(n.getImports());
        }
        if (n.getTypes() != null) {
            img.add(n.getTypes());
        }
        if (n.getComments() != null) {
            img.add(n.getComments());
        }
        img.add(n.getName());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ConditionalExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ConditionalExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getThenExpression() != null) {
            img.add(n.getThenExpression());
        }
        if (n.getElseExpression() != null) {
            img.add(n.getElseExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ConstructorDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(ConstructorDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        if (n.getTypeParameters() != null) {
            img.add(n.getTypeParameters());
        }
        img.add(n.getName());
        if (n.getReceiverType() != null) {
            img.add(n.getReceiverType());
        }
        if (n.getReceiverQualifier() != null) {
            img.add(n.getReceiverQualifier());
        }
        if (n.getParameters() != null) {
            img.add(n.getParameters());
        }
        if (n.getThrowsList() != null) {
            img.add(n.getThrowsList());
        }
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ContinueStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ContinueStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getId());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CreationReference, java.lang.Object)
     */
    @Override
    public List<Object> visit(CreationReference n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.DoStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(DoStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.DoubleLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(DoubleLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Ellipsis, java.lang.Object)
     */
    @Override
    public List<Object> visit(Ellipsis n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyMemberDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(EmptyMemberDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(EmptyStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyTypeDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(EmptyTypeDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        img.add(n.getName());
        if (n.getMembers() != null) {
            img.add(n.getMembers());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnclosedExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(EnclosedExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getInner() != null) {
            img.add(n.getInner());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnumConstantDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(EnumConstantDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getArgs() != null) {
            img.add(n.getArgs());
        }
        if (n.getClassBody() != null) {
            img.add(n.getClassBody());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnumDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(EnumDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getImplementsList() != null) {
            img.add(n.getImplementsList());
        }
        if (n.getEntries() != null) {
            img.add(n.getEntries());
        }
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        img.add(n.getName());
        if (n.getMembers() != null) {
            img.add(n.getMembers());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ExplicitConstructorInvocationStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ExplicitConstructorInvocationStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        img.add(n.isThis());
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getArgs() != null) {
            img.add(n.getArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ExpressionMethodReference, java.lang.Object)
     */
    @Override
    public List<Object> visit(ExpressionMethodReference n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        img.add(n.getMethodName());
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ExpressionStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ExpressionStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.FieldAccessExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(FieldAccessExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getScope() != null) {
            img.add(n.getScope());
        }
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        img.add(n.getField());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.FieldDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(FieldDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getVariables() != null) {
            img.add(n.getVariables());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ForeachStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ForeachStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariable() != null) {
            img.add(n.getVariable());
        }
        if (n.getIterable() != null) {
            img.add(n.getIterable());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ForStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ForStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getInit() != null) {
            img.add(n.getInit());
        }
        if (n.getCompare() != null) {
            img.add(n.getCompare());
        }
        if (n.getUpdate() != null) {
            img.add(n.getUpdate());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.IfStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(IfStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getThenStmt() != null) {
            img.add(n.getThenStmt());
        }
        if (n.getElseStmt() != null) {
            img.add(n.getElseStmt());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ImportDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(ImportDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getName() != null) {
            img.add(n.getName());
        }
        img.add(n.isStatic());
        img.add(n.isAsterisk());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InitializerDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(InitializerDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.isStatic());
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InstanceOfExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(InstanceOfExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.IntegerLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(IntegerLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InterfaceDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(InterfaceDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTypeParameters() != null) {
            img.add(n.getTypeParameters());
        }
        if (n.getExtendsList() != null) {
            img.add(n.getExtendsList());
        }
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        img.add(n.getName());
        if (n.getMembers() != null) {
            img.add(n.getMembers());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.JavadocComment, java.lang.Object)
     */
    @Override
    public List<Object> visit(JavadocComment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getContent());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LabeledStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(LabeledStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getLabel());
        if (n.getStmt() != null) {
            img.add(n.getStmt());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LambdaBlock, java.lang.Object)
     */
    @Override
    public List<Object> visit(LambdaBlock n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getBlockStmt() != null) {
            img.add(n.getBlockStmt());
        }
        if (n.getParameters() != null) {
            img.add(n.getParameters());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LambdaExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(LambdaExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getParameters() != null) {
            img.add(n.getParameters());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LineComment, java.lang.Object)
     */
    @Override
    public List<Object> visit(LineComment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getContent());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LongLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(LongLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MarkerAnnotationExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(MarkerAnnotationExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MemberValuePair, java.lang.Object)
     */
    @Override
    public List<Object> visit(MemberValuePair n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getValue() != null) {
            img.add(n.getValue());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodCallExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(MethodCallExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getScope() != null) {
            img.add(n.getScope());
        }
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        img.add(n.getName());
        if (n.getArgs() != null) {
            img.add(n.getArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(MethodDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        if (n.getTypeParameters() != null) {
            img.add(n.getTypeParameters());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        img.add(n.getName());
        if (n.getReceiverType() != null) {
            img.add(n.getReceiverType());
        }
        if (n.getReceiverQualifier() != null) {
            img.add(n.getReceiverQualifier());
        }
        if (n.getParameters() != null) {
            img.add(n.getParameters());
        }
        if (n.getSlots() != null) {
            img.add(n.getSlots());
        }
        if (n.getThrowsList() != null) {
            img.add(n.getThrowsList());
        }
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getJavaDoc() != null) {
            img.add(n.getJavaDoc());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Modifiers, java.lang.Object)
     */
    @Override
    public List<Object> visit(Modifiers n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getModifiers());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NameExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(NameExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NodeList, java.lang.Object)
     */
    @Override
    public <E extends Node> List<Object> visit(NodeList<E> n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getNodes() != null) {
            img.add(n.getNodes());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NormalAnnotationExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(NormalAnnotationExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getPairs() != null) {
            img.add(n.getPairs());
        }
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NullLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(NullLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ObjectCreationExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ObjectCreationExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getScope() != null) {
            img.add(n.getScope());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        if (n.getArgs() != null) {
            img.add(n.getArgs());
        }
        if (n.getAnonymousClassBody() != null) {
            img.add(n.getAnonymousClassBody());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PackageDeclaration, java.lang.Object)
     */
    @Override
    public List<Object> visit(PackageDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Parameter, java.lang.Object)
     */
    @Override
    public List<Object> visit(Parameter n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getEllipsis() != null) {
            img.add(n.getEllipsis());
        }
        if (n.getId() != null) {
            img.add(n.getId());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PrimitiveType, java.lang.Object)
     */
    @Override
    public List<Object> visit(PrimitiveType n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getType());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PrimitiveType.Primitive, java.lang.Object)
     */
    @Override
    public List<Object> visit(Primitive n, Void ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Project, java.lang.Object)
     */
    @Override
    public List<Object> visit(Project n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCompilationUnits() != null) {
            img.add(n.getCompilationUnits());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.QualifiedNameExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(QualifiedNameExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getQualifier() != null) {
            img.add(n.getQualifier());
        }
        img.add(n.getName());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ReferenceType, java.lang.Object)
     */
    @Override
    public List<Object> visit(ReferenceType n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getSlots() != null) {
            img.add(n.getSlots());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ReturnStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ReturnStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SingleMemberAnnotationExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(SingleMemberAnnotationExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getMemberValue() != null) {
            img.add(n.getMemberValue());
        }
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.StringLiteralExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(StringLiteralExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SuperExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(SuperExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getClassExpression() != null) {
            img.add(n.getClassExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SuperMethodReference, java.lang.Object)
     */
    @Override
    public List<Object> visit(SuperMethodReference n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getQualifier() != null) {
            img.add(n.getQualifier());
        }
        img.add(n.getMethodName());
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SwitchEntryStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(SwitchEntryStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getLabel() != null) {
            img.add(n.getLabel());
        }
        if (n.getStmts() != null) {
            img.add(n.getStmts());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SwitchStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(SwitchStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getSelector() != null) {
            img.add(n.getSelector());
        }
        if (n.getEntries() != null) {
            img.add(n.getEntries());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SynchronizedStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(SynchronizedStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ThisExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(ThisExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getClassExpression() != null) {
            img.add(n.getClassExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ThrowStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(ThrowStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TryStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(TryStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getResources() != null) {
            img.add(n.getResources());
        }
        if (n.getTryBlock() != null) {
            img.add(n.getTryBlock());
        }
        if (n.getCatchClauses() != null) {
            img.add(n.getCatchClauses());
        }
        if (n.getFinallyBlock() != null) {
            img.add(n.getFinallyBlock());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TypeDeclarationStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(TypeDeclarationStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTypeDeclaration() != null) {
            img.add(n.getTypeDeclaration());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TypeMethodReference, java.lang.Object)
     */
    @Override
    public List<Object> visit(TypeMethodReference n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getType() != null) {
            img.add(n.getType());
        }
        img.add(n.getMethodName());
        if (n.getTypeArgs() != null) {
            img.add(n.getTypeArgs());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TypeParameter, java.lang.Object)
     */
    @Override
    public List<Object> visit(TypeParameter n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getTypeBound() != null) {
            img.add(n.getTypeBound());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.UnaryExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(UnaryExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        img.add(n.getOperator());
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.UnaryExpr.UnaryOperator, java.lang.Object)
     */
    @Override
    public List<Object> visit(UnaryOperator n, Void ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VariableDeclarationExpr, java.lang.Object)
     */
    @Override
    public List<Object> visit(VariableDeclarationExpr n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getModifiers() != null) {
            img.add(n.getModifiers());
        }
        if (n.getType() != null) {
            img.add(n.getType());
        }
        if (n.getVars() != null) {
            img.add(n.getVars());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VariableDeclarator, java.lang.Object)
     */
    @Override
    public List<Object> visit(VariableDeclarator n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getId() != null) {
            img.add(n.getId());
        }
        if (n.getInit() != null) {
            img.add(n.getInit());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VariableDeclaratorId, java.lang.Object)
     */
    @Override
    public List<Object> visit(VariableDeclaratorId n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getSlots() != null) {
            img.add(n.getSlots());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VoidType, java.lang.Object)
     */
    @Override
    public List<Object> visit(VoidType n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.WhileStmt, java.lang.Object)
     */
    @Override
    public List<Object> visit(WhileStmt n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.WildcardType, java.lang.Object)
     */
    @Override
    public List<Object> visit(WildcardType n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExtendsType() != null) {
            img.add(n.getExtendsType());
        }
        if (n.getSuperType() != null) {
            img.add(n.getSuperType());
        }
        if (n.getAnnotations() != null) {
            img.add(n.getAnnotations());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    /**
     * Instantiates a new children provider.
     */
    public ChildrenProvider() {
        super();
    }

}
