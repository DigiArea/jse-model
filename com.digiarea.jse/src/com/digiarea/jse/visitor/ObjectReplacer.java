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
import java.util.Map;
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

@SuppressWarnings("unchecked")
public class ObjectReplacer implements GenericVisitor<Node, Map<Object, Object>> {

    @Override
    public Node visit(AnnotationDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        AnnotationDeclaration img = NodeFacade.AnnotationDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(AnnotationMemberDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        AnnotationMemberDeclaration img = NodeFacade.AnnotationMemberDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getDefaultValue() != null) {
            img.setDefaultValue((Expression) n.getDefaultValue().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ArrayAccessExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ArrayCreationExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ArrayInitializerExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ArrayInitializerExpr img = NodeFacade.ArrayInitializerExpr();
        if (n.getValues() != null) {
            img.setValues((NodeList<Expression>) n.getValues().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ArraySlot n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ArraySlot img = NodeFacade.ArraySlot();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(AssertStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(AssignExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        AssignExpr img = NodeFacade.AssignExpr();
        if (n.getTarget() != null) {
            img.setTarget((Expression) n.getTarget().accept(this, ctx));
        }
        if (n.getValue() != null) {
            img.setValue((Expression) n.getValue().accept(this, ctx));
        }
        if (ctx.containsKey(n.getOperator())) {
            img.setOperator((AssignExpr.AssignOperator) ctx.get(n.getOperator()));
        } else {
            img.setOperator(n.getOperator());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(AssignOperator n, Map<Object, Object> ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(BinaryExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        BinaryExpr img = NodeFacade.BinaryExpr();
        if (n.getLeft() != null) {
            img.setLeft((Expression) n.getLeft().accept(this, ctx));
        }
        if (n.getRight() != null) {
            img.setRight((Expression) n.getRight().accept(this, ctx));
        }
        if (ctx.containsKey(n.getOperator())) {
            img.setOperator((BinaryExpr.BinaryOperator) ctx.get(n.getOperator()));
        } else {
            img.setOperator(n.getOperator());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(BinaryOperator n, Map<Object, Object> ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(BlockComment n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        BlockComment img = NodeFacade.BlockComment();
        if (ctx.containsKey(n.getContent())) {
            img.setContent((String) ctx.get(n.getContent()));
        } else {
            img.setContent(n.getContent());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(BlockStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        BlockStmt img = NodeFacade.BlockStmt();
        if (n.getStatements() != null) {
            img.setStatements((NodeList<Statement>) n.getStatements().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(BooleanLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        BooleanLiteralExpr img = NodeFacade.BooleanLiteralExpr();
        if (ctx.containsKey(n.isValue())) {
            img.setValue((boolean) ctx.get(n.isValue()));
        } else {
            img.setValue(n.isValue());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(BreakStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        BreakStmt img = NodeFacade.BreakStmt();
        if (ctx.containsKey(n.getId())) {
            img.setId((String) ctx.get(n.getId()));
        } else {
            img.setId(n.getId());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(CastExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(CatchClause n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        CatchClause img = NodeFacade.CatchClause();
        if (ctx.containsKey(n.isFinal())) {
            img.setFinal((boolean) ctx.get(n.isFinal()));
        } else {
            img.setFinal(n.isFinal());
        }
        if (n.getTypes() != null) {
            img.setTypes((NodeList<Type>) n.getTypes().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getCatchBlock() != null) {
            img.setCatchBlock((BlockStmt) n.getCatchBlock().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(CharLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        CharLiteralExpr img = NodeFacade.CharLiteralExpr();
        if (ctx.containsKey(n.getValue())) {
            img.setValue((String) ctx.get(n.getValue()));
        } else {
            img.setValue(n.getValue());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ClassDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ClassExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ClassExpr img = NodeFacade.ClassExpr();
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ClassOrInterfaceType n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(CompilationUnit n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ConditionalExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ConstructorDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ConstructorDeclaration img = NodeFacade.ConstructorDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (n.getTypeParameters() != null) {
            img.setTypeParameters((NodeList<TypeParameter>) n.getTypeParameters().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ContinueStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ContinueStmt img = NodeFacade.ContinueStmt();
        if (ctx.containsKey(n.getId())) {
            img.setId((String) ctx.get(n.getId()));
        } else {
            img.setId(n.getId());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(DoStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(DoubleLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        DoubleLiteralExpr img = NodeFacade.DoubleLiteralExpr();
        if (ctx.containsKey(n.getValue())) {
            img.setValue((String) ctx.get(n.getValue()));
        } else {
            img.setValue(n.getValue());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(Ellipsis n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        Ellipsis img = NodeFacade.Ellipsis();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(EmptyMemberDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        EmptyMemberDeclaration img = NodeFacade.EmptyMemberDeclaration();
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(EmptyStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        EmptyStmt img = NodeFacade.EmptyStmt();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(EmptyTypeDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        EmptyTypeDeclaration img = NodeFacade.EmptyTypeDeclaration();
        if (n.getModifiers() != null) {
            img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(EnclosedExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        EnclosedExpr img = NodeFacade.EnclosedExpr();
        if (n.getInner() != null) {
            img.setInner((Expression) n.getInner().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(EnumConstantDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        EnumConstantDeclaration img = NodeFacade.EnumConstantDeclaration();
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(EnumDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ExplicitConstructorInvocationStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ExplicitConstructorInvocationStmt img = NodeFacade.ExplicitConstructorInvocationStmt();
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (ctx.containsKey(n.isThis())) {
            img.setThis((boolean) ctx.get(n.isThis()));
        } else {
            img.setThis(n.isThis());
        }
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ExpressionStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ExpressionStmt img = NodeFacade.ExpressionStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(FieldAccessExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        FieldAccessExpr img = NodeFacade.FieldAccessExpr();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (ctx.containsKey(n.getField())) {
            img.setField((String) ctx.get(n.getField()));
        } else {
            img.setField(n.getField());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(FieldDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ForeachStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ForStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(IfStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ImportDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ImportDeclaration img = NodeFacade.ImportDeclaration();
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (ctx.containsKey(n.isStatic())) {
            img.setStatic((boolean) ctx.get(n.isStatic()));
        } else {
            img.setStatic(n.isStatic());
        }
        if (ctx.containsKey(n.isAsterisk())) {
            img.setAsterisk((boolean) ctx.get(n.isAsterisk()));
        } else {
            img.setAsterisk(n.isAsterisk());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(InitializerDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        InitializerDeclaration img = NodeFacade.InitializerDeclaration();
        if (ctx.containsKey(n.isStatic())) {
            img.setStatic((boolean) ctx.get(n.isStatic()));
        } else {
            img.setStatic(n.isStatic());
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(InstanceOfExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(IntegerLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        IntegerLiteralExpr img = NodeFacade.IntegerLiteralExpr();
        if (ctx.containsKey(n.getValue())) {
            img.setValue((String) ctx.get(n.getValue()));
        } else {
            img.setValue(n.getValue());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(InterfaceDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getMembers() != null) {
            img.setMembers((NodeList<BodyDeclaration>) n.getMembers().accept(this, ctx));
        }
        if (n.getJavaDoc() != null) {
            img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(JavadocComment n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        JavadocComment img = NodeFacade.JavadocComment();
        if (ctx.containsKey(n.getContent())) {
            img.setContent((String) ctx.get(n.getContent()));
        } else {
            img.setContent(n.getContent());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(LabeledStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        LabeledStmt img = NodeFacade.LabeledStmt();
        if (ctx.containsKey(n.getLabel())) {
            img.setLabel((String) ctx.get(n.getLabel()));
        } else {
            img.setLabel(n.getLabel());
        }
        if (n.getStmt() != null) {
            img.setStmt((Statement) n.getStmt().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(LambdaBlock n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(LambdaExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(LineComment n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        LineComment img = NodeFacade.LineComment();
        if (ctx.containsKey(n.getContent())) {
            img.setContent((String) ctx.get(n.getContent()));
        } else {
            img.setContent(n.getContent());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(LongLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        LongLiteralExpr img = NodeFacade.LongLiteralExpr();
        if (ctx.containsKey(n.getValue())) {
            img.setValue((String) ctx.get(n.getValue()));
        } else {
            img.setValue(n.getValue());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MarkerAnnotationExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        MarkerAnnotationExpr img = NodeFacade.MarkerAnnotationExpr();
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MemberValuePair n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        MemberValuePair img = NodeFacade.MemberValuePair();
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getValue() != null) {
            img.setValue((Expression) n.getValue().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MethodCallExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        MethodCallExpr img = NodeFacade.MethodCallExpr();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MethodDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MethodExprRef n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        MethodExprRef img = NodeFacade.MethodExprRef();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MethodRef n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        MethodRef img = NodeFacade.MethodRef();
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(MethodTypeRef n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        MethodTypeRef img = NodeFacade.MethodTypeRef();
        if (n.getType() != null) {
            img.setType((Type) n.getType().accept(this, ctx));
        }
        if (n.getTypeArgs() != null) {
            img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(Modifiers n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        Modifiers img = NodeFacade.Modifiers();
        if (ctx.containsKey(n.getModifiers())) {
            img.setModifiers((int) ctx.get(n.getModifiers()));
        } else {
            img.setModifiers(n.getModifiers());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(NameExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        NameExpr img = NodeFacade.NameExpr();
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public <E extends Node> Node visit(NodeList<E> n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        NodeList<E> img = NodeFacade.NodeList();
        if (n.getNodes() != null) {
            List<E> nodes = new ArrayList<E>();
            for (E item : n.getNodes()) {
                if (item != null) {
                    E node = (E) item.accept(this, ctx);
                    if (node != null) {
                        nodes.add(node);
                    }
                }
            }
            img.setNodes(nodes);
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(NormalAnnotationExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(NullLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        NullLiteralExpr img = NodeFacade.NullLiteralExpr();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ObjectCreationExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(PackageDeclaration n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        PackageDeclaration img = NodeFacade.PackageDeclaration();
        if (n.getName() != null) {
            img.setName((NameExpr) n.getName().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(Parameter n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(PrimitiveType n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        PrimitiveType img = NodeFacade.PrimitiveType();
        if (ctx.containsKey(n.getType())) {
            img.setType((PrimitiveType.Primitive) ctx.get(n.getType()));
        } else {
            img.setType(n.getType());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(Primitive n, Map<Object, Object> ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Project n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        Project img = NodeFacade.Project();
        if (n.getCompilationUnits() != null) {
            img.setCompilationUnits((NodeList<CompilationUnit>) n.getCompilationUnits().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(QualifiedNameExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        QualifiedNameExpr img = NodeFacade.QualifiedNameExpr();
        if (n.getQualifier() != null) {
            img.setQualifier((NameExpr) n.getQualifier().accept(this, ctx));
        }
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ReferenceType n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ReturnStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ReturnStmt img = NodeFacade.ReturnStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(SingleMemberAnnotationExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(StringLiteralExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        StringLiteralExpr img = NodeFacade.StringLiteralExpr();
        if (ctx.containsKey(n.getValue())) {
            img.setValue((String) ctx.get(n.getValue()));
        } else {
            img.setValue(n.getValue());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(SuperExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        SuperExpr img = NodeFacade.SuperExpr();
        if (n.getClassExpression() != null) {
            img.setClassExpression((Expression) n.getClassExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(SwitchEntryStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(SwitchStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(SynchronizedStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ThisExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ThisExpr img = NodeFacade.ThisExpr();
        if (n.getClassExpression() != null) {
            img.setClassExpression((Expression) n.getClassExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(ThrowStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        ThrowStmt img = NodeFacade.ThrowStmt();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(TryStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(TypeDeclarationStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        TypeDeclarationStmt img = NodeFacade.TypeDeclarationStmt();
        if (n.getTypeDeclaration() != null) {
            img.setTypeDeclaration((TypeDeclaration) n.getTypeDeclaration().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(TypeParameter n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        TypeParameter img = NodeFacade.TypeParameter();
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getTypeBound() != null) {
            img.setTypeBound((NodeList<ClassOrInterfaceType>) n.getTypeBound().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(UnaryExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        UnaryExpr img = NodeFacade.UnaryExpr();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (ctx.containsKey(n.getOperator())) {
            img.setOperator((UnaryExpr.UnaryOperator) ctx.get(n.getOperator()));
        } else {
            img.setOperator(n.getOperator());
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(UnaryOperator n, Map<Object, Object> ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(VariableDeclarationExpr n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(VariableDeclarator n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(VariableDeclaratorId n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        VariableDeclaratorId img = NodeFacade.VariableDeclaratorId();
        if (ctx.containsKey(n.getName())) {
            img.setName((String) ctx.get(n.getName()));
        } else {
            img.setName(n.getName());
        }
        if (n.getSlots() != null) {
            img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
        }
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(VoidType n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
        VoidType img = NodeFacade.VoidType();
        if (n.getAnnotations() != null) {
            img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations().accept(this, ctx));
        }
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(WhileStmt n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    @Override
    public Node visit(WildcardType n, Map<Object, Object> ctx) throws Exception {
        if (ctx.containsKey(n)) {
            return (Node) ctx.get(n);
        }
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
        if (ctx.containsKey(n.getPosBegin())) {
            img.setPosBegin((int) ctx.get(n.getPosBegin()));
        } else {
            img.setPosBegin(n.getPosBegin());
        }
        if (ctx.containsKey(n.getPosEnd())) {
            img.setPosEnd((int) ctx.get(n.getPosEnd()));
        } else {
            img.setPosEnd(n.getPosEnd());
        }
        return img;
    }

    public ObjectReplacer() {
        super();
    }

}
