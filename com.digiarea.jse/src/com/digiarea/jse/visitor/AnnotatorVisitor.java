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
import com.digiarea.jse.ForStmt;
import com.digiarea.jse.ForeachStmt;
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
import com.digiarea.jse.NodeFacade;
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
import com.digiarea.jse.utils.Enclosure;

/**
 * The Class AnnotatorVisitor.
 *
 * @param <C> the generic type
 */
public class AnnotatorVisitor<C> implements VoidVisitor<C> {

	/**
	 * The enclosure.
	 */
	protected Enclosure enclosure = new Enclosure(
			NodeFacade.QualifiedNameExpr(""));

	/**
	 * Process annotations.
	 *
	 * @param node the node
	 * @param ctx the ctx
	 * @throws Exception the exception
	 */
	protected void processAnnotations(Node node, C ctx) throws Exception {
		List<AnnotationExpr> annotations = node.getAnnotations();
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				a.accept(this, ctx);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * AnnotationDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(AnnotationDeclaration n, C ctx) throws Exception {
		enclosure.add(n.getName());
		processAnnotations(n, ctx);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, ctx);
		}
		if (n.getMembers() != null) {
			n.getMembers().accept(this, ctx);
		}
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, ctx);
		}
		enclosure.cut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * AnnotationMemberDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(AnnotationMemberDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ArrayAccessExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ArrayAccessExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
		if (n.getIndex() != null) {
			n.getIndex().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ArrayCreationExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ArrayCreationExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
		if (n.getSlots() != null) {
			n.getSlots().accept(this, ctx);
		}
		if (n.getInitializer() != null) {
			n.getInitializer().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ArrayInitializerExpr, java.lang.Object)
	 */
	@Override
	public void visit(ArrayInitializerExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getValues() != null) {
			n.getValues().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ArraySlot,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ArraySlot n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.AssertStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(AssertStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getCheck() != null) {
			n.getCheck().accept(this, ctx);
		}
		if (n.getMessage() != null) {
			n.getMessage().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.AssignExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(AssignExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getTarget() != null) {
			n.getTarget().accept(this, ctx);
		}
		if (n.getValue() != null) {
			n.getValue().accept(this, ctx);
		}
		if (n.getOperator() != null) {
			n.getOperator().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.AssignExpr
	 * .AssignOperator, java.lang.Object)
	 */
	@Override
	public void visit(AssignOperator n, C ctx) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.BinaryExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(BinaryExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getLeft() != null) {
			n.getLeft().accept(this, ctx);
		}
		if (n.getRight() != null) {
			n.getRight().accept(this, ctx);
		}
		if (n.getOperator() != null) {
			n.getOperator().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.BinaryExpr
	 * .BinaryOperator, java.lang.Object)
	 */
	@Override
	public void visit(BinaryOperator n, C ctx) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.BlockComment,
	 * java.lang.Object)
	 */
	@Override
	public void visit(BlockComment n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.BlockStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(BlockStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getStatements() != null) {
			n.getStatements().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * BooleanLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(BooleanLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.BreakStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(BreakStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.CastExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(CastExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getTypes() != null) {
			n.getTypes().accept(this, ctx);
		}
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.CatchClause,
	 * java.lang.Object)
	 */
	@Override
	public void visit(CatchClause n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getTypes() != null) {
			n.getTypes().accept(this, ctx);
		}
		if (n.getCatchBlock() != null) {
			n.getCatchBlock().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.CharLiteralExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(CharLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ClassDeclaration
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ClassDeclaration n, C ctx) throws Exception {
		enclosure.add(n.getName());
		processAnnotations(n, ctx);
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
		enclosure.cut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ClassExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ClassExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ClassOrInterfaceType, java.lang.Object)
	 */
	@Override
	public void visit(ClassOrInterfaceType n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getScope() != null) {
			n.getScope().accept(this, ctx);
		}
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.CompilationUnit
	 * , java.lang.Object)
	 */
	@Override
	public void visit(CompilationUnit n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ConditionalExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ConditionalExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getCondition() != null) {
			n.getCondition().accept(this, ctx);
		}
		if (n.getThenExpression() != null) {
			n.getThenExpression().accept(this, ctx);
		}
		if (n.getElseExpression() != null) {
			n.getElseExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ConstructorDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(ConstructorDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, ctx);
		}
		if (n.getTypeParameters() != null) {
			n.getTypeParameters().accept(this, ctx);
		}
		if (n.getReceiverType() != null) {
			n.getReceiverType().accept(this, ctx);
		}
		if (n.getReceiverQualifier() != null) {
			n.getReceiverQualifier().accept(this, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ContinueStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ContinueStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.CreationReference
	 * , java.lang.Object)
	 */
	@Override
	public void visit(CreationReference n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.DoStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(DoStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getBody() != null) {
			n.getBody().accept(this, ctx);
		}
		if (n.getCondition() != null) {
			n.getCondition().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.DoubleLiteralExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(DoubleLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.Ellipsis,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Ellipsis n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * EmptyMemberDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EmptyMemberDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.EmptyStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(EmptyStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * EmptyTypeDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EmptyTypeDeclaration n, C ctx) throws Exception {
		enclosure.add(n.getName());
		processAnnotations(n, ctx);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, ctx);
		}
		if (n.getMembers() != null) {
			n.getMembers().accept(this, ctx);
		}
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, ctx);
		}
		enclosure.cut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.EnclosedExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(EnclosedExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getInner() != null) {
			n.getInner().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * EnumConstantDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EnumConstantDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getArgs() != null) {
			n.getArgs().accept(this, ctx);
		}
		if (n.getClassBody() != null) {
			n.getClassBody().accept(this, ctx);
		}
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.EnumDeclaration
	 * , java.lang.Object)
	 */
	@Override
	public void visit(EnumDeclaration n, C ctx) throws Exception {
		enclosure.add(n.getName());
		processAnnotations(n, ctx);
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
		enclosure.cut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ExplicitConstructorInvocationStmt, java.lang.Object)
	 */
	@Override
	public void visit(ExplicitConstructorInvocationStmt n, C ctx)
			throws Exception {
		processAnnotations(n, ctx);
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
		if (n.getArgs() != null) {
			n.getArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ExpressionMethodReference, java.lang.Object)
	 */
	@Override
	public void visit(ExpressionMethodReference n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ExpressionStmt
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ExpressionStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.FieldAccessExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(FieldAccessExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getScope() != null) {
			n.getScope().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.FieldDeclaration
	 * , java.lang.Object)
	 */
	@Override
	public void visit(FieldDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ForeachStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ForeachStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getVariable() != null) {
			n.getVariable().accept(this, ctx);
		}
		if (n.getIterable() != null) {
			n.getIterable().accept(this, ctx);
		}
		if (n.getBody() != null) {
			n.getBody().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ForStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ForStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.IfStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(IfStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getCondition() != null) {
			n.getCondition().accept(this, ctx);
		}
		if (n.getThenStmt() != null) {
			n.getThenStmt().accept(this, ctx);
		}
		if (n.getElseStmt() != null) {
			n.getElseStmt().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ImportDeclaration
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ImportDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * InitializerDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(InitializerDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getBlock() != null) {
			n.getBlock().accept(this, ctx);
		}
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.InstanceOfExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(InstanceOfExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * IntegerLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(IntegerLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * InterfaceDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(InterfaceDeclaration n, C ctx) throws Exception {
		enclosure.add(n.getName());
		processAnnotations(n, ctx);
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
		enclosure.cut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.JavadocComment
	 * , java.lang.Object)
	 */
	@Override
	public void visit(JavadocComment n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LabeledStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LabeledStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getStmt() != null) {
			n.getStmt().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LambdaBlock,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LambdaBlock n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getBlockStmt() != null) {
			n.getBlockStmt().accept(this, ctx);
		}
		if (n.getParameters() != null) {
			n.getParameters().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LambdaExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LambdaExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
		if (n.getParameters() != null) {
			n.getParameters().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LineComment,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LineComment n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LongLiteralExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(LongLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * MarkerAnnotationExpr, java.lang.Object)
	 */
	@Override
	public void visit(MarkerAnnotationExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.MemberValuePair
	 * , java.lang.Object)
	 */
	@Override
	public void visit(MemberValuePair n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getValue() != null) {
			n.getValue().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.MethodCallExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(MethodCallExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getScope() != null) {
			n.getScope().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
		if (n.getArgs() != null) {
			n.getArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.MethodDeclaration
	 * , java.lang.Object)
	 */
	@Override
	public void visit(MethodDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, ctx);
		}
		if (n.getTypeParameters() != null) {
			n.getTypeParameters().accept(this, ctx);
		}
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
		if (n.getReceiverType() != null) {
			n.getReceiverType().accept(this, ctx);
		}
		if (n.getReceiverQualifier() != null) {
			n.getReceiverQualifier().accept(this, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.Modifiers,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Modifiers n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.NameExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(NameExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.NodeList,
	 * java.lang.Object)
	 */
	@Override
	public <E extends Node> void visit(NodeList<E> n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getNodes() != null) {
			for (E item : n.getNodes()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * NormalAnnotationExpr, java.lang.Object)
	 */
	@Override
	public void visit(NormalAnnotationExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getPairs() != null) {
			n.getPairs().accept(this, ctx);
		}
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.NullLiteralExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(NullLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ObjectCreationExpr, java.lang.Object)
	 */
	@Override
	public void visit(ObjectCreationExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * PackageDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(PackageDeclaration n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.Parameter,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Parameter n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.PrimitiveType
	 * , java.lang.Object)
	 */
	@Override
	public void visit(PrimitiveType n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.PrimitiveType
	 * .Primitive, java.lang.Object)
	 */
	@Override
	public void visit(Primitive n, C ctx) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.Project,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Project n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getCompilationUnits() != null) {
			n.getCompilationUnits().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.QualifiedNameExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(QualifiedNameExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getQualifier() != null) {
			n.getQualifier().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ReferenceType
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ReferenceType n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
		if (n.getSlots() != null) {
			n.getSlots().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ReturnStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ReturnStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * SingleMemberAnnotationExpr, java.lang.Object)
	 */
	@Override
	public void visit(SingleMemberAnnotationExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getMemberValue() != null) {
			n.getMemberValue().accept(this, ctx);
		}
		if (n.getName() != null) {
			n.getName().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.StringLiteralExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(StringLiteralExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.SuperExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(SuperExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getClassExpression() != null) {
			n.getClassExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * SuperMethodReference, java.lang.Object)
	 */
	@Override
	public void visit(SuperMethodReference n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getQualifier() != null) {
			n.getQualifier().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.SwitchEntryStmt
	 * , java.lang.Object)
	 */
	@Override
	public void visit(SwitchEntryStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getLabel() != null) {
			n.getLabel().accept(this, ctx);
		}
		if (n.getStmts() != null) {
			n.getStmts().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.SwitchStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(SwitchStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getSelector() != null) {
			n.getSelector().accept(this, ctx);
		}
		if (n.getEntries() != null) {
			n.getEntries().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.SynchronizedStmt
	 * , java.lang.Object)
	 */
	@Override
	public void visit(SynchronizedStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
		if (n.getBlock() != null) {
			n.getBlock().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ThisExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ThisExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getClassExpression() != null) {
			n.getClassExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ThrowStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ThrowStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.TryStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(TryStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * TypeDeclarationStmt, java.lang.Object)
	 */
	@Override
	public void visit(TypeDeclarationStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getTypeDeclaration() != null) {
			n.getTypeDeclaration().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * TypeMethodReference, java.lang.Object)
	 */
	@Override
	public void visit(TypeMethodReference n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
		if (n.getTypeArgs() != null) {
			n.getTypeArgs().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.TypeParameter
	 * , java.lang.Object)
	 */
	@Override
	public void visit(TypeParameter n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getTypeBound() != null) {
			n.getTypeBound().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.UnaryExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(UnaryExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, ctx);
		}
		if (n.getOperator() != null) {
			n.getOperator().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.UnaryExpr
	 * .UnaryOperator, java.lang.Object)
	 */
	@Override
	public void visit(UnaryOperator n, C ctx) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * VariableDeclarationExpr, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclarationExpr n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, ctx);
		}
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
		if (n.getVars() != null) {
			n.getVars().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * VariableDeclarator, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclarator n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getId() != null) {
			n.getId().accept(this, ctx);
		}
		if (n.getInit() != null) {
			n.getInit().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * VariableDeclaratorId, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclaratorId n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getSlots() != null) {
			n.getSlots().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.VoidType,
	 * java.lang.Object)
	 */
	@Override
	public void visit(VoidType n, C ctx) throws Exception {
		processAnnotations(n, ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.WhileStmt,
	 * java.lang.Object)
	 */
	@Override
	public void visit(WhileStmt n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getCondition() != null) {
			n.getCondition().accept(this, ctx);
		}
		if (n.getBody() != null) {
			n.getBody().accept(this, ctx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.WildcardType,
	 * java.lang.Object)
	 */
	@Override
	public void visit(WildcardType n, C ctx) throws Exception {
		processAnnotations(n, ctx);
		if (n.getExtendsType() != null) {
			n.getExtendsType().accept(this, ctx);
		}
		if (n.getSuperType() != null) {
			n.getSuperType().accept(this, ctx);
		}
	}

}
