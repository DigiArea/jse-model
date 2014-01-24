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

import com.digiarea.jse.Node;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.NodeFacade;
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
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
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
import java.util.List;
import java.util.ArrayList;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.NullLiteralExpr;
import com.digiarea.jse.ObjectCreationExpr;
import com.digiarea.jse.VariableDeclaratorId;
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
import com.digiarea.jse.UnaryExpr;
import com.digiarea.jse.UnaryExpr.UnaryOperator;
import com.digiarea.jse.VoidType;
import com.digiarea.jse.WhileStmt;
import com.digiarea.jse.WildcardType;

/**
 * The Class CloneVisitor.
 *
 * @param <C> the generic type
 */
@SuppressWarnings("unchecked")
public class CloneVisitor<C> implements GenericVisitor<Node, C> {

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AnnotationDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(AnnotationDeclaration n, C ctx) throws Exception {
        AnnotationDeclaration img = NodeFacade.AnnotationDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AnnotationMemberDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(AnnotationMemberDeclaration n, C ctx) throws Exception {
        AnnotationMemberDeclaration img = NodeFacade.AnnotationMemberDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getDefaultValue() != null) {
            img.setDefaultValue((Expression) n.getDefaultValue().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArrayAccessExpr, java.lang.Object)
     */
    @Override
    public Node visit(ArrayAccessExpr n, C ctx) throws Exception {
        ArrayAccessExpr img = NodeFacade.ArrayAccessExpr();
        if (n.getName() != null) {
            img.setName((Expression) n.getName().accept(this, ctx));
        }
        if (n.getIndex() != null) {
            img.setIndex((Expression) n.getIndex().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArrayCreationExpr, java.lang.Object)
     */
    @Override
    public Node visit(ArrayCreationExpr n, C ctx) throws Exception {
        ArrayCreationExpr img = NodeFacade.ArrayCreationExpr();
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getSlots() != null) {
            img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
        }
        if (n.getInitializer() != null) {
            img.setInitializer((ArrayInitializerExpr) n.getInitializer().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArrayInitializerExpr, java.lang.Object)
     */
    @Override
    public Node visit(ArrayInitializerExpr n, C ctx) throws Exception {
        ArrayInitializerExpr img = NodeFacade.ArrayInitializerExpr();
        if (n.getValues() != null) {
            img.setValues((NodeList<Expression>) n.getValues().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArraySlot, java.lang.Object)
     */
    @Override
    public Node visit(ArraySlot n, C ctx) throws Exception {
        ArraySlot img = NodeFacade.ArraySlot();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssertStmt, java.lang.Object)
     */
    @Override
    public Node visit(AssertStmt n, C ctx) throws Exception {
        AssertStmt img = NodeFacade.AssertStmt();
        if (n.getCheck() != null) {
            img.setCheck((Expression) n.getCheck().accept(this, ctx));
        }
        if (n.getMessage() != null) {
            img.setMessage((Expression) n.getMessage().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssignExpr, java.lang.Object)
     */
    @Override
    public Node visit(AssignExpr n, C ctx) throws Exception {
        AssignExpr img = NodeFacade.AssignExpr();
        if (n.getTarget() != null) {
            img.setTarget((Expression) n.getTarget().accept(this, ctx));
        }
        if (n.getValue() != null) {
            img.setValue((Expression) n.getValue().accept(this, ctx));
        }
        img.setOperator(n.getOperator());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssignExpr.AssignOperator, java.lang.Object)
     */
    @Override
    public Node visit(AssignOperator n, C ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BinaryExpr, java.lang.Object)
     */
    @Override
    public Node visit(BinaryExpr n, C ctx) throws Exception {
        BinaryExpr img = NodeFacade.BinaryExpr();
        if (n.getLeft() != null) {
            img.setLeft((Expression) n.getLeft().accept(this, ctx));
        }
        if (n.getRight() != null) {
            img.setRight((Expression) n.getRight().accept(this, ctx));
        }
        img.setOperator(n.getOperator());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BinaryExpr.BinaryOperator, java.lang.Object)
     */
    @Override
    public Node visit(BinaryOperator n, C ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BlockComment, java.lang.Object)
     */
    @Override
    public Node visit(BlockComment n, C ctx) throws Exception {
        BlockComment img = NodeFacade.BlockComment();
        img.setContent(n.getContent());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BlockStmt, java.lang.Object)
     */
    @Override
    public Node visit(BlockStmt n, C ctx) throws Exception {
        BlockStmt img = NodeFacade.BlockStmt();
        if (n.getStatements() != null) {
            img.setStatements((NodeList<Statement>) n.getStatements().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BooleanLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(BooleanLiteralExpr n, C ctx) throws Exception {
        BooleanLiteralExpr img = NodeFacade.BooleanLiteralExpr();
        img.setValue(n.isValue());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BreakStmt, java.lang.Object)
     */
    @Override
    public Node visit(BreakStmt n, C ctx) throws Exception {
        BreakStmt img = NodeFacade.BreakStmt();
        img.setId(n.getId());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CastExpr, java.lang.Object)
     */
    @Override
    public Node visit(CastExpr n, C ctx) throws Exception {
        CastExpr img = NodeFacade.CastExpr();
        if (n.getTypes() != null) {
            img.setTypes((NodeList<Type>) n.getTypes().accept(this, ctx));
        }
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CatchClause, java.lang.Object)
     */
    @Override
    public Node visit(CatchClause n, C ctx) throws Exception {
        CatchClause img = NodeFacade.CatchClause();
        img.setFinal(n.isFinal());
        if (n.getTypes() != null) {
            img.setTypes((NodeList<Type>) n.getTypes().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getCatchBlock() != null) {
            img.setCatchBlock((BlockStmt) n.getCatchBlock().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CharLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(CharLiteralExpr n, C ctx) throws Exception {
        CharLiteralExpr img = NodeFacade.CharLiteralExpr();
        img.setValue(n.getValue());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(ClassDeclaration n, C ctx) throws Exception {
        ClassDeclaration img = NodeFacade.ClassDeclaration();
        if (n.getTypeParameters() != null) {
            img.setTypeParameters((NodeList<TypeParameter>) n.getTypeParameters().accept(this, ctx));
        }
        if (n.getExtendsType() != null) {
            img.setExtendsType((ClassOrInterfaceType) n.getExtendsType().accept(this, ctx));
        }
        if (n.getImplementsList() != null) {
            img.setImplementsList((NodeList<ClassOrInterfaceType>) n.getImplementsList().accept(this, ctx));
        }
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassExpr, java.lang.Object)
     */
    @Override
    public Node visit(ClassExpr n, C ctx) throws Exception {
        ClassExpr img = NodeFacade.ClassExpr();
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassOrInterfaceType, java.lang.Object)
     */
    @Override
    public Node visit(ClassOrInterfaceType n, C ctx) throws Exception {
        ClassOrInterfaceType img = NodeFacade.ClassOrInterfaceType();
        if (n.getScope() != null) {
            img.setScope((ClassOrInterfaceType) n.getScope().accept(this, ctx));
        }
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CompilationUnit, java.lang.Object)
     */
    @Override
    public Node visit(CompilationUnit n, C ctx) throws Exception {
        CompilationUnit img = NodeFacade.CompilationUnit();
        if (n.getPackageDeclaration() != null) {
            img.setPackageDeclaration((PackageDeclaration) n.getPackageDeclaration().accept(this, ctx));
        }
        if (n.getImports() != null) {
            img.setImports((NodeList<ImportDeclaration>) n.getImports().accept(this, ctx));
        }
        if (n.getTypes() != null) {
            img.setTypes((NodeList<TypeDeclaration>) n.getTypes().accept(this, ctx));
        }
        if (n.getComments() != null) {
            img.setComments((NodeList<Comment>) n.getComments().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ConditionalExpr, java.lang.Object)
     */
    @Override
    public Node visit(ConditionalExpr n, C ctx) throws Exception {
        ConditionalExpr img = NodeFacade.ConditionalExpr();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getThenExpression() != null) {
            img.setThenExpression((Expression) n.getThenExpression().accept(this, ctx));
        }
        if (n.getElseExpression() != null) {
            img.setElseExpression((Expression) n.getElseExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ConstructorDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(ConstructorDeclaration n, C ctx) throws Exception {
        ConstructorDeclaration img = NodeFacade.ConstructorDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getTypeParameters() != null) {
            img.setTypeParameters((NodeList<TypeParameter>) n.getTypeParameters().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getReceiverType() != null) {
            img.setReceiverType((Type) n.getReceiverType().accept(this, ctx));
        }
        if (n.getReceiverQualifier() != null) {
            img.setReceiverQualifier((NameExpr) n.getReceiverQualifier().accept(this, ctx));
        }
        if (n.getParameters() != null) {
            img.setParameters((NodeList<Parameter>) n.getParameters().accept(this, ctx));
        }
        if (n.getThrowsList() != null) {
            img.setThrowsList((NodeList<ClassOrInterfaceType>) n.getThrowsList().accept(this, ctx));
        }
        if (n.getBlock() != null) {
            img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ContinueStmt, java.lang.Object)
     */
    @Override
    public Node visit(ContinueStmt n, C ctx) throws Exception {
        ContinueStmt img = NodeFacade.ContinueStmt();
        img.setId(n.getId());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.DoStmt, java.lang.Object)
     */
    @Override
    public Node visit(DoStmt n, C ctx) throws Exception {
        DoStmt img = NodeFacade.DoStmt();
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.DoubleLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(DoubleLiteralExpr n, C ctx) throws Exception {
        DoubleLiteralExpr img = NodeFacade.DoubleLiteralExpr();
        img.setValue(n.getValue());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Ellipsis, java.lang.Object)
     */
    @Override
    public Node visit(Ellipsis n, C ctx) throws Exception {
        Ellipsis img = NodeFacade.Ellipsis();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyMemberDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(EmptyMemberDeclaration n, C ctx) throws Exception {
        EmptyMemberDeclaration img = NodeFacade.EmptyMemberDeclaration();
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyStmt, java.lang.Object)
     */
    @Override
    public Node visit(EmptyStmt n, C ctx) throws Exception {
        EmptyStmt img = NodeFacade.EmptyStmt();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyTypeDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(EmptyTypeDeclaration n, C ctx) throws Exception {
        EmptyTypeDeclaration img = NodeFacade.EmptyTypeDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnclosedExpr, java.lang.Object)
     */
    @Override
    public Node visit(EnclosedExpr n, C ctx) throws Exception {
        EnclosedExpr img = NodeFacade.EnclosedExpr();
        if (n.getInner() != null) {
            img.setInner((Expression) n.getInner().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnumConstantDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(EnumConstantDeclaration n, C ctx) throws Exception {
        EnumConstantDeclaration img = NodeFacade.EnumConstantDeclaration();
        img.setName(n.getName());
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getClassBody() != null) {
            img.setClassBody((NodeList<BodyDeclaration>) n.getClassBody().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnumDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(EnumDeclaration n, C ctx) throws Exception {
        EnumDeclaration img = NodeFacade.EnumDeclaration();
        if (n.getImplementsList() != null) {
            img.setImplementsList((NodeList<ClassOrInterfaceType>) n.getImplementsList().accept(this, ctx));
        }
        if (n.getEntries() != null) {
            img.setEntries((NodeList<EnumConstantDeclaration>) n.getEntries().accept(this, ctx));
        }
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ExplicitConstructorInvocationStmt, java.lang.Object)
     */
    @Override
    public Node visit(ExplicitConstructorInvocationStmt n, C ctx) throws Exception {
        ExplicitConstructorInvocationStmt img = NodeFacade.ExplicitConstructorInvocationStmt();
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        img.setThis(n.isThis());
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ExpressionStmt, java.lang.Object)
     */
    @Override
    public Node visit(ExpressionStmt n, C ctx) throws Exception {
        ExpressionStmt img = NodeFacade.ExpressionStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.FieldAccessExpr, java.lang.Object)
     */
    @Override
    public Node visit(FieldAccessExpr n, C ctx) throws Exception {
        FieldAccessExpr img = NodeFacade.FieldAccessExpr();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        img.setField(n.getField());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.FieldDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(FieldDeclaration n, C ctx) throws Exception {
        FieldDeclaration img = NodeFacade.FieldDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getVariables() != null) {
            img.setVariables((NodeList<VariableDeclarator>) n.getVariables().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ForeachStmt, java.lang.Object)
     */
    @Override
    public Node visit(ForeachStmt n, C ctx) throws Exception {
        ForeachStmt img = NodeFacade.ForeachStmt();
        if (n.getVariable() != null) {
            img.setVariable((VariableDeclarationExpr) n.getVariable().accept(this, ctx));
        }
        if (n.getIterable() != null) {
            img.setIterable((Expression) n.getIterable().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ForStmt, java.lang.Object)
     */
    @Override
    public Node visit(ForStmt n, C ctx) throws Exception {
        ForStmt img = NodeFacade.ForStmt();
        if (n.getInit() != null) {
            img.setInit((NodeList<Expression>) n.getInit().accept(this, ctx));
        }
        if (n.getCompare() != null) {
            img.setCompare((Expression) n.getCompare().accept(this, ctx));
        }
        if (n.getUpdate() != null) {
            img.setUpdate((NodeList<Expression>) n.getUpdate().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.IfStmt, java.lang.Object)
     */
    @Override
    public Node visit(IfStmt n, C ctx) throws Exception {
        IfStmt img = NodeFacade.IfStmt();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getThenStmt() != null) {
            img.setThenStmt((Statement) n.getThenStmt().accept(this, ctx));
        }
        if (n.getElseStmt() != null) {
            img.setElseStmt((Statement) n.getElseStmt().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ImportDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(ImportDeclaration n, C ctx) throws Exception {
        ImportDeclaration img = NodeFacade.ImportDeclaration();
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        img.setStatic(n.isStatic());
        img.setAsterisk(n.isAsterisk());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InitializerDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(InitializerDeclaration n, C ctx) throws Exception {
        InitializerDeclaration img = NodeFacade.InitializerDeclaration();
        img.setStatic(n.isStatic());
        if (n.getBlock() != null) {
            img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InstanceOfExpr, java.lang.Object)
     */
    @Override
    public Node visit(InstanceOfExpr n, C ctx) throws Exception {
        InstanceOfExpr img = NodeFacade.InstanceOfExpr();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.IntegerLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(IntegerLiteralExpr n, C ctx) throws Exception {
        IntegerLiteralExpr img = NodeFacade.IntegerLiteralExpr();
        img.setValue(n.getValue());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InterfaceDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(InterfaceDeclaration n, C ctx) throws Exception {
        InterfaceDeclaration img = NodeFacade.InterfaceDeclaration();
        if (n.getTypeParameters() != null) {
            img.setTypeParameters((NodeList<TypeParameter>) n.getTypeParameters().accept(this, ctx));
        }
        if (n.getExtendsList() != null) {
            img.setExtendsList((NodeList<ClassOrInterfaceType>) n.getExtendsList().accept(this, ctx));
        }
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.JavadocComment, java.lang.Object)
     */
    @Override
    public Node visit(JavadocComment n, C ctx) throws Exception {
        JavadocComment img = NodeFacade.JavadocComment();
        img.setContent(n.getContent());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LabeledStmt, java.lang.Object)
     */
    @Override
    public Node visit(LabeledStmt n, C ctx) throws Exception {
        LabeledStmt img = NodeFacade.LabeledStmt();
        img.setLabel(n.getLabel());
        if (n.getStmt() != null) {
            img.setStmt((Statement) n.getStmt().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LambdaBlock, java.lang.Object)
     */
    @Override
    public Node visit(LambdaBlock n, C ctx) throws Exception {
        LambdaBlock img = NodeFacade.LambdaBlock();
        if (n.getBlockStmt() != null) {
            img.setBlockStmt((BlockStmt) n.getBlockStmt().accept(this, ctx));
        }
        if (n.getParameters() != null) {
            img.setParameters((NodeList<Parameter>) n.getParameters().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LambdaExpr, java.lang.Object)
     */
    @Override
    public Node visit(LambdaExpr n, C ctx) throws Exception {
        LambdaExpr img = NodeFacade.LambdaExpr();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getParameters() != null) {
            img.setParameters((NodeList<Parameter>) n.getParameters().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LineComment, java.lang.Object)
     */
    @Override
    public Node visit(LineComment n, C ctx) throws Exception {
        LineComment img = NodeFacade.LineComment();
        img.setContent(n.getContent());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LongLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(LongLiteralExpr n, C ctx) throws Exception {
        LongLiteralExpr img = NodeFacade.LongLiteralExpr();
        img.setValue(n.getValue());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MarkerAnnotationExpr, java.lang.Object)
     */
    @Override
    public Node visit(MarkerAnnotationExpr n, C ctx) throws Exception {
        MarkerAnnotationExpr img = NodeFacade.MarkerAnnotationExpr();
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MemberValuePair, java.lang.Object)
     */
    @Override
    public Node visit(MemberValuePair n, C ctx) throws Exception {
        MemberValuePair img = NodeFacade.MemberValuePair();
        img.setName(n.getName());
        if (n.getValue() != null) {
            img.setValue((Expression) n.getValue().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodCallExpr, java.lang.Object)
     */
    @Override
    public Node visit(MethodCallExpr n, C ctx) throws Exception {
        MethodCallExpr img = NodeFacade.MethodCallExpr();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(MethodDeclaration n, C ctx) throws Exception {
        MethodDeclaration img = NodeFacade.MethodDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getTypeParameters() != null) {
            img.setTypeParameters((NodeList<TypeParameter>) n.getTypeParameters().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getReceiverType() != null) {
            img.setReceiverType((Type) n.getReceiverType().accept(this, ctx));
        }
        if (n.getReceiverQualifier() != null) {
            img.setReceiverQualifier((NameExpr) n.getReceiverQualifier().accept(this, ctx));
        }
        if (n.getParameters() != null) {
            img.setParameters((NodeList<Parameter>) n.getParameters().accept(this, ctx));
        }
        if (n.getSlots() != null) {
            img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
        }
        if (n.getThrowsList() != null) {
            img.setThrowsList((NodeList<ClassOrInterfaceType>) n.getThrowsList().accept(this, ctx));
        }
        if (n.getBlock() != null) {
            img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodExprRef, java.lang.Object)
     */
    @Override
    public Node visit(MethodExprRef n, C ctx) throws Exception {
        MethodExprRef img = NodeFacade.MethodExprRef();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodRef, java.lang.Object)
     */
    @Override
    public Node visit(MethodRef n, C ctx) throws Exception {
        MethodRef img = NodeFacade.MethodRef();
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodTypeRef, java.lang.Object)
     */
    @Override
    public Node visit(MethodTypeRef n, C ctx) throws Exception {
        MethodTypeRef img = NodeFacade.MethodTypeRef();
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Modifiers, java.lang.Object)
     */
    @Override
    public Node visit(Modifiers n, C ctx) throws Exception {
        Modifiers img = NodeFacade.Modifiers();
        img.setModifiers(n.getModifiers());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NameExpr, java.lang.Object)
     */
    @Override
    public Node visit(NameExpr n, C ctx) throws Exception {
        NameExpr img = NodeFacade.NameExpr();
        img.setName(n.getName());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NodeList, java.lang.Object)
     */
    @Override
    public <E extends Node> Node visit(NodeList<E> n, C ctx) throws Exception {
        NodeList<E> img = NodeFacade.NodeList();
        if (n.getNodes() != null) {
            List<E> nodes = new ArrayList<E>();
            for (E item : n.getNodes()) {
                if (item != null) {
                    nodes.add((E) item.accept(this, ctx));
                }
            }
            img.setNodes(nodes);
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NormalAnnotationExpr, java.lang.Object)
     */
    @Override
    public Node visit(NormalAnnotationExpr n, C ctx) throws Exception {
        NormalAnnotationExpr img = NodeFacade.NormalAnnotationExpr();
        if (n.getPairs() != null) {
            img.setPairs((NodeList<MemberValuePair>) n.getPairs().accept(this, ctx));
        }
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NullLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(NullLiteralExpr n, C ctx) throws Exception {
        NullLiteralExpr img = NodeFacade.NullLiteralExpr();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ObjectCreationExpr, java.lang.Object)
     */
    @Override
    public Node visit(ObjectCreationExpr n, C ctx) throws Exception {
        ObjectCreationExpr img = NodeFacade.ObjectCreationExpr();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((ClassOrInterfaceType) n.getType().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getAnonymousClassBody() != null) {
            img.setAnonymousClassBody((NodeList<BodyDeclaration>) n.getAnonymousClassBody().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PackageDeclaration, java.lang.Object)
     */
    @Override
    public Node visit(PackageDeclaration n, C ctx) throws Exception {
        PackageDeclaration img = NodeFacade.PackageDeclaration();
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Parameter, java.lang.Object)
     */
    @Override
    public Node visit(Parameter n, C ctx) throws Exception {
        Parameter img = NodeFacade.Parameter();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getEllipsis() != null) {
            img.setEllipsis((Ellipsis) n.getEllipsis().accept(this, ctx));
        }
        if (n.getId() != null) {
            img.setId((VariableDeclaratorId) n.getId().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PrimitiveType, java.lang.Object)
     */
    @Override
    public Node visit(PrimitiveType n, C ctx) throws Exception {
        PrimitiveType img = NodeFacade.PrimitiveType();
        img.setType(n.getType());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PrimitiveType.Primitive, java.lang.Object)
     */
    @Override
    public Node visit(Primitive n, C ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Project, java.lang.Object)
     */
    @Override
    public Node visit(Project n, C ctx) throws Exception {
        Project img = NodeFacade.Project();
        if (n.getCompilationUnits() != null) {
            img.setCompilationUnits((NodeList<CompilationUnit>) n.getCompilationUnits().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.QualifiedNameExpr, java.lang.Object)
     */
    @Override
    public Node visit(QualifiedNameExpr n, C ctx) throws Exception {
        QualifiedNameExpr img = NodeFacade.QualifiedNameExpr();
        if (n.getQualifier() != null) {
            img.setQualifier((NameExpr) n.getQualifier().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ReferenceType, java.lang.Object)
     */
    @Override
    public Node visit(ReferenceType n, C ctx) throws Exception {
        ReferenceType img = NodeFacade.ReferenceType();
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getSlots() != null) {
            img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ReturnStmt, java.lang.Object)
     */
    @Override
    public Node visit(ReturnStmt n, C ctx) throws Exception {
        ReturnStmt img = NodeFacade.ReturnStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SingleMemberAnnotationExpr, java.lang.Object)
     */
    @Override
    public Node visit(SingleMemberAnnotationExpr n, C ctx) throws Exception {
        SingleMemberAnnotationExpr img = NodeFacade.SingleMemberAnnotationExpr();
        if (n.getMemberValue() != null) {
            img.setMemberValue((Expression) n.getMemberValue().accept(this, ctx));
        }
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.StringLiteralExpr, java.lang.Object)
     */
    @Override
    public Node visit(StringLiteralExpr n, C ctx) throws Exception {
        StringLiteralExpr img = NodeFacade.StringLiteralExpr();
        img.setValue(n.getValue());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SuperExpr, java.lang.Object)
     */
    @Override
    public Node visit(SuperExpr n, C ctx) throws Exception {
        SuperExpr img = NodeFacade.SuperExpr();
        if (n.getClassExpression() != null) {
            img.setClassExpression((Expression) n.getClassExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SwitchEntryStmt, java.lang.Object)
     */
    @Override
    public Node visit(SwitchEntryStmt n, C ctx) throws Exception {
        SwitchEntryStmt img = NodeFacade.SwitchEntryStmt();
        if (n.getLabel() != null) {
            img.setLabel((Expression) n.getLabel().accept(this, ctx));
        }
        if (n.getStmts() != null) {
            img.setStmts((NodeList<Statement>) n.getStmts().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SwitchStmt, java.lang.Object)
     */
    @Override
    public Node visit(SwitchStmt n, C ctx) throws Exception {
        SwitchStmt img = NodeFacade.SwitchStmt();
        if (n.getSelector() != null) {
            img.setSelector((Expression) n.getSelector().accept(this, ctx));
        }
        if (n.getEntries() != null) {
            img.setEntries((NodeList<SwitchEntryStmt>) n.getEntries().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SynchronizedStmt, java.lang.Object)
     */
    @Override
    public Node visit(SynchronizedStmt n, C ctx) throws Exception {
        SynchronizedStmt img = NodeFacade.SynchronizedStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getBlock() != null) {
            img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ThisExpr, java.lang.Object)
     */
    @Override
    public Node visit(ThisExpr n, C ctx) throws Exception {
        ThisExpr img = NodeFacade.ThisExpr();
        if (n.getClassExpression() != null) {
            img.setClassExpression((Expression) n.getClassExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ThrowStmt, java.lang.Object)
     */
    @Override
    public Node visit(ThrowStmt n, C ctx) throws Exception {
        ThrowStmt img = NodeFacade.ThrowStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TryStmt, java.lang.Object)
     */
    @Override
    public Node visit(TryStmt n, C ctx) throws Exception {
        TryStmt img = NodeFacade.TryStmt();
        if (n.getResources() != null) {
            img.setResources((NodeList<VariableDeclarationExpr>) n.getResources().accept(this, ctx));
        }
        if (n.getTryBlock() != null) {
            img.setTryBlock((BlockStmt) n.getTryBlock().accept(this, ctx));
        }
        if (n.getCatchClauses() != null) {
            img.setCatchClauses((NodeList<CatchClause>) n.getCatchClauses().accept(this, ctx));
        }
        if (n.getFinallyBlock() != null) {
            img.setFinallyBlock((BlockStmt) n.getFinallyBlock().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TypeDeclarationStmt, java.lang.Object)
     */
    @Override
    public Node visit(TypeDeclarationStmt n, C ctx) throws Exception {
        TypeDeclarationStmt img = NodeFacade.TypeDeclarationStmt();
        if (n.getTypeDeclaration() != null) {
            img.setTypeDeclaration((TypeDeclaration) n.getTypeDeclaration().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TypeParameter, java.lang.Object)
     */
    @Override
    public Node visit(TypeParameter n, C ctx) throws Exception {
        TypeParameter img = NodeFacade.TypeParameter();
        img.setName(n.getName());
        if (n.getTypeBound() != null) {
            img.setTypeBound((NodeList<ClassOrInterfaceType>) n.getTypeBound().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.UnaryExpr, java.lang.Object)
     */
    @Override
    public Node visit(UnaryExpr n, C ctx) throws Exception {
        UnaryExpr img = NodeFacade.UnaryExpr();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        img.setOperator(n.getOperator());
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.UnaryExpr.UnaryOperator, java.lang.Object)
     */
    @Override
    public Node visit(UnaryOperator n, C ctx) throws Exception {
        return null;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VariableDeclarationExpr, java.lang.Object)
     */
    @Override
    public Node visit(VariableDeclarationExpr n, C ctx) throws Exception {
        VariableDeclarationExpr img = NodeFacade.VariableDeclarationExpr();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getVars() != null) {
            img.setVars((NodeList<VariableDeclarator>) n.getVars().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VariableDeclarator, java.lang.Object)
     */
    @Override
    public Node visit(VariableDeclarator n, C ctx) throws Exception {
        VariableDeclarator img = NodeFacade.VariableDeclarator();
        if (n.getId() != null) {
            img.setId((VariableDeclaratorId) n.getId().accept(this, ctx));
        }
        if (n.getInit() != null) {
            img.setInit((Expression) n.getInit().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VariableDeclaratorId, java.lang.Object)
     */
    @Override
    public Node visit(VariableDeclaratorId n, C ctx) throws Exception {
        VariableDeclaratorId img = NodeFacade.VariableDeclaratorId();
        img.setName(n.getName());
        if (n.getSlots() != null) {
            img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VoidType, java.lang.Object)
     */
    @Override
    public Node visit(VoidType n, C ctx) throws Exception {
        VoidType img = NodeFacade.VoidType();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.WhileStmt, java.lang.Object)
     */
    @Override
    public Node visit(WhileStmt n, C ctx) throws Exception {
        WhileStmt img = NodeFacade.WhileStmt();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.WildcardType, java.lang.Object)
     */
    @Override
    public Node visit(WildcardType n, C ctx) throws Exception {
        WildcardType img = NodeFacade.WildcardType();
        if (n.getExtendsType() != null) {
            img.setExtendsType((ReferenceType) n.getExtendsType().accept(this, ctx));
        }
        if (n.getSuperType() != null) {
            img.setSuperType((ReferenceType) n.getSuperType().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    /**
     * Instantiates a new clone visitor.
     */
    public CloneVisitor() {
        super();
    }

}
