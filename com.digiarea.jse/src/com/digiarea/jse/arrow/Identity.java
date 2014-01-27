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
package com.digiarea.jse.arrow;

import java.util.ArrayList;
import java.util.List;

import com.digiarea.common.Arrow;
import com.digiarea.jse.*;
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.PrimitiveType.Primitive;
import com.digiarea.jse.UnaryExpr.UnaryOperator;
import com.digiarea.jse.visitor.GenericVisitor;

/**
 * The Class Identity.
 */
@SuppressWarnings("unchecked")
public class Identity implements GenericVisitor<Node, Context>,
		Arrow<Project, Project> {

	/**
	 * Copy annotations.
	 * 
	 * @param exprs
	 *            the exprs
	 * @param annotatable
	 *            the annotatable
	 * @param ctx
	 *            the ctx
	 * @return the node list
	 * @throws Exception
	 *             the exception
	 */
	protected NodeList<AnnotationExpr> copyAnnotations(
			List<AnnotationExpr> exprs, Node annotatable, Context ctx)
			throws Exception {
		if (exprs != null) {
			List<AnnotationExpr> annotations = new ArrayList<AnnotationExpr>();
			for (AnnotationExpr annotation : exprs) {
				AnnotationExpr ex = (AnnotationExpr) annotation.accept(this,
						ctx);
				if (ex != null) {
					annotations.add(ex);
				}
			}
			return NodeFacade.NodeList(annotations);
		}
		return null;
	}

	/**
	 * Copy members.
	 * 
	 * @param decls
	 *            the decls
	 * @param ctx
	 *            the ctx
	 * @return the node list
	 * @throws Exception
	 *             the exception
	 */
	protected NodeList<BodyDeclaration> copyMembers(
			List<BodyDeclaration> decls, Context ctx) throws Exception {
		if (decls != null) {
			List<BodyDeclaration> members = new ArrayList<BodyDeclaration>();
			for (BodyDeclaration member : decls) {
				BodyDeclaration bd = (BodyDeclaration) member.accept(this, ctx);
				if (bd != null) {
					members.add(bd);
				}
			}
			return NodeFacade.NodeList(members);
		}
		return null;
	}

	/**
	 * Copy class or interface types.
	 * 
	 * @param types
	 *            the types
	 * @param ctx
	 *            the ctx
	 * @return the node list
	 * @throws Exception
	 *             the exception
	 */
	protected NodeList<ClassOrInterfaceType> copyClassOrInterfaceTypes(
			List<ClassOrInterfaceType> types, Context ctx) throws Exception {
		if (types != null) {
			List<ClassOrInterfaceType> result = new ArrayList<ClassOrInterfaceType>();
			for (ClassOrInterfaceType type : types) {
				ClassOrInterfaceType tp = (ClassOrInterfaceType) type.accept(
						this, ctx);
				if (tp != null) {
					result.add(tp);
				}
			}
			return NodeFacade.NodeList(result);
		}
		return null;
	}

	/**
	 * Cope type parameters.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param ctx
	 *            the ctx
	 * @return the node list
	 * @throws Exception
	 *             the exception
	 */
	protected NodeList<TypeParameter> copeTypeParameters(
			List<TypeParameter> parameters, Context ctx) throws Exception {
		if (parameters != null) {
			List<TypeParameter> result = new ArrayList<TypeParameter>();
			for (TypeParameter parameter : parameters) {
				TypeParameter p = (TypeParameter) parameter.accept(this, ctx);
				if (p != null) {
					result.add(p);
				}
			}
			return NodeFacade.NodeList(result);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.common.Arrow#arrow(java.lang.Object)
	 */
	@Override
	public Project arrow(Project input) throws Exception {
		return (Project) input.accept(this, new Context(NodeFacade.Project()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * AnnotationDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(AnnotationDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		AnnotationDeclaration img = NodeFacade.AnnotationDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setMembers(copyMembers(n.getMembers(), ctx));
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.cutEnclosure();
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * AnnotationMemberDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(AnnotationMemberDeclaration n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		AnnotationMemberDeclaration img = NodeFacade
				.AnnotationMemberDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setName(n.getName());
		if (n.getDefaultValue() != null) {
			img.setDefaultValue((Expression) n.getDefaultValue().accept(this,
					ctx));
		}
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ArrayAccessExpr, java.lang.Object)
	 */
	@Override
	public Node visit(ArrayAccessExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArrayAccessExpr img = NodeFacade.ArrayAccessExpr();
		ctx.setNode(img);
		if (n.getName() != null) {
			img.setName((Expression) n.getName().accept(this, ctx));
		}
		if (n.getIndex() != null) {
			img.setIndex((Expression) n.getIndex().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ArrayCreationExpr, java.lang.Object)
	 */
	@Override
	public Node visit(ArrayCreationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArrayCreationExpr img = NodeFacade.ArrayCreationExpr();
		ctx.setNode(img);
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		if (n.getInitializer() != null) {
			img.setInitializer((ArrayInitializerExpr) n.getInitializer()
					.accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ArrayInitializerExpr, java.lang.Object)
	 */
	@Override
	public Node visit(ArrayInitializerExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArrayInitializerExpr img = NodeFacade.ArrayInitializerExpr();
		ctx.setNode(img);
		if (n.getValues() != null) {
			img.setValues((NodeList<Expression>) n.getValues()
					.accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ArraySlot,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(ArraySlot n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArraySlot img = NodeFacade.ArraySlot();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssertStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(AssertStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		AssertStmt img = NodeFacade.AssertStmt();
		ctx.setNode(img);
		if (n.getCheck() != null) {
			img.setCheck((Expression) n.getCheck().accept(this, ctx));
		}
		if (n.getMessage() != null) {
			img.setMessage((Expression) n.getMessage().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssignExpr
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(AssignExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		AssignExpr img = NodeFacade.AssignExpr();
		ctx.setNode(img);
		if (n.getTarget() != null) {
			img.setTarget((Expression) n.getTarget().accept(this, ctx));
		}
		if (n.getValue() != null) {
			img.setValue((Expression) n.getValue().accept(this, ctx));
		}
		img.setOperator(n.getOperator());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.AssignExpr
	 * .AssignOperator, java.lang.Object)
	 */
	@Override
	public Node visit(AssignOperator n, Context ctx) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BinaryExpr
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(BinaryExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BinaryExpr img = NodeFacade.BinaryExpr();
		ctx.setNode(img);
		if (n.getLeft() != null) {
			img.setLeft((Expression) n.getLeft().accept(this, ctx));
		}
		if (n.getRight() != null) {
			img.setRight((Expression) n.getRight().accept(this, ctx));
		}
		img.setOperator(n.getOperator());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BinaryExpr
	 * .BinaryOperator, java.lang.Object)
	 */
	@Override
	public Node visit(BinaryOperator n, Context ctx) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BlockComment
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(BlockComment n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BlockComment img = NodeFacade.BlockComment();
		ctx.setNode(img);
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BlockStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(BlockStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BlockStmt img = NodeFacade.BlockStmt();
		ctx.setNode(img);
		if (n.getStatements() != null) {
			img.setStatements((NodeList<Statement>) n.getStatements().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * BooleanLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(BooleanLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BooleanLiteralExpr img = NodeFacade.BooleanLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.isValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.BreakStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(BreakStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BreakStmt img = NodeFacade.BreakStmt();
		ctx.setNode(img);
		img.setId(n.getId());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CastExpr,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(CastExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CastExpr img = NodeFacade.CastExpr();
		ctx.setNode(img);
		if (n.getTypes() != null) {
			img.setTypes((NodeList<Type>) n.getTypes().accept(this, ctx));
		}
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.CatchClause
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(CatchClause n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CatchClause img = NodeFacade.CatchClause();
		ctx.setNode(img);
		img.setFinal(n.isFinal());
		if (n.getTypes() != null) {
			img.setTypes((NodeList<Type>) n.getTypes().accept(this, ctx));
		}
		img.setName(n.getName());
		if (n.getCatchBlock() != null) {
			img.setCatchBlock((BlockStmt) n.getCatchBlock().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * CharLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(CharLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CharLiteralExpr img = NodeFacade.CharLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ClassDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(ClassDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		ClassDeclaration img = NodeFacade.ClassDeclaration();
		ctx.setNode(img);
		img.setTypeParameters(copeTypeParameters(n.getTypeParameters(), ctx));
		if (n.getExtendsType() != null) {
			img.setExtendsType((ClassOrInterfaceType) n.getExtendsType()
					.accept(this, ctx));
		}
		img.setImplementsList(copyClassOrInterfaceTypes(n.getImplementsList(),
				ctx));
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setMembers(copyMembers(n.getMembers(), ctx));
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.cutEnclosure();
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ClassExpr,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(ClassExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ClassExpr img = NodeFacade.ClassExpr();
		ctx.setNode(img);
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ClassOrInterfaceType, java.lang.Object)
	 */
	@Override
	public Node visit(ClassOrInterfaceType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ClassOrInterfaceType img = NodeFacade.ClassOrInterfaceType();
		ctx.setNode(img);
		if (n.getScope() != null) {
			img.setScope((ClassOrInterfaceType) n.getScope().accept(this, ctx));
		}
		if (n.getName() != null) {
			ctx.setName(true);
			img.setName((NameExpr) n.getName().accept(this, ctx));
			ctx.setName(false);
		}
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * CompilationUnit, java.lang.Object)
	 */
	@Override
	public Node visit(CompilationUnit n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CompilationUnit img = NodeFacade.CompilationUnit();
		ctx.setNode(img);
		ctx.setUnit(img);
		img.setName(n.getName());
		if (n.getPackageDeclaration() != null) {
			img.setPackageDeclaration((PackageDeclaration) n
					.getPackageDeclaration().accept(this, ctx));
		}
		// as package might be set we can set the NodeFacade.enclosure
		if (img.getPackageDeclaration() != null) {
			ctx.setEnclosure(img.getPackageDeclaration().getName());
		} else {
			ctx.setEnclosure(NodeFacade.NameExpr());
		}
		if (n.getImports() != null) {
			img.setImports((NodeList<ImportDeclaration>) n.getImports().accept(
					this, ctx));
		}
		if (n.getTypes() != null) {
			img.setTypes((NodeList<TypeDeclaration>) n.getTypes().accept(this,
					ctx));
		}
		if (n.getComments() != null) {
			img.setComments((NodeList<Comment>) n.getComments().accept(this,
					ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setUnit(null);
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ConditionalExpr, java.lang.Object)
	 */
	@Override
	public Node visit(ConditionalExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ConditionalExpr img = NodeFacade.ConditionalExpr();
		ctx.setNode(img);
		if (n.getCondition() != null) {
			img.setCondition((Expression) n.getCondition().accept(this, ctx));
		}
		if (n.getThenExpression() != null) {
			img.setThenExpression((Expression) n.getThenExpression().accept(
					this, ctx));
		}
		if (n.getElseExpression() != null) {
			img.setElseExpression((Expression) n.getElseExpression().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ConstructorDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(ConstructorDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ConstructorDeclaration img = NodeFacade.ConstructorDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setTypeParameters(copeTypeParameters(n.getTypeParameters(), ctx));
		img.setName(n.getName());
		if (n.getReceiverType() != null) {
			img.setReceiverType((Type) n.getReceiverType().accept(this, ctx));
		}
		if (n.getReceiverQualifier() != null) {
			img.setReceiverQualifier((NameExpr) n.getReceiverQualifier()
					.accept(this, ctx));
		}
		if (n.getParameters() != null) {
			img.setParameters((NodeList<Parameter>) n.getParameters().accept(
					this, ctx));
		}
		if (n.getThrowsList() != null) {
			ctx.setName(true);
			img.setThrowsList((NodeList<ClassOrInterfaceType>) n
					.getThrowsList().accept(this, ctx));
			ctx.setName(false);
		}
		if (n.getBlock() != null) {
			img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
		}
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ContinueStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(ContinueStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ContinueStmt img = NodeFacade.ContinueStmt();
		ctx.setNode(img);
		img.setId(n.getId());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * CreationReference, java.lang.Object)
	 */
	@Override
	public Node visit(CreationReference n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CreationReference img = NodeFacade.CreationReference();
		ctx.setNode(img);
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		if (n.getAnnotations() != null) {
			img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations()
					.accept(this, ctx));
		}
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.DoStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(DoStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		DoStmt img = NodeFacade.DoStmt();
		ctx.setNode(img);
		if (n.getBody() != null) {
			img.setBody((Statement) n.getBody().accept(this, ctx));
		}
		if (n.getCondition() != null) {
			img.setCondition((Expression) n.getCondition().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * DoubleLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(DoubleLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		DoubleLiteralExpr img = NodeFacade.DoubleLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Ellipsis,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(Ellipsis n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Ellipsis img = NodeFacade.Ellipsis();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * EmptyMemberDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(EmptyMemberDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EmptyMemberDeclaration img = NodeFacade.EmptyMemberDeclaration();
		ctx.setNode(img);
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EmptyStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(EmptyStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EmptyStmt img = NodeFacade.EmptyStmt();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * EmptyTypeDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(EmptyTypeDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		EmptyTypeDeclaration img = NodeFacade.EmptyTypeDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setMembers(copyMembers(n.getMembers(), ctx));
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.cutEnclosure();
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.EnclosedExpr
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(EnclosedExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EnclosedExpr img = NodeFacade.EnclosedExpr();
		ctx.setNode(img);
		if (n.getInner() != null) {
			img.setInner((Expression) n.getInner().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * EnumConstantDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(EnumConstantDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EnumConstantDeclaration img = NodeFacade.EnumConstantDeclaration();
		ctx.setNode(img);
		img.setName(n.getName());
		if (n.getArgs() != null) {
			img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
		}
		if (n.getClassBody() != null) {
			img.setClassBody((NodeList<BodyDeclaration>) n.getClassBody()
					.accept(this, ctx));
		}
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * EnumDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(EnumDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		EnumDeclaration img = NodeFacade.EnumDeclaration();
		ctx.setNode(img);
		img.setImplementsList(copyClassOrInterfaceTypes(n.getImplementsList(),
				ctx));
		if (n.getEntries() != null) {
			img.setEntries((NodeList<EnumConstantDeclaration>) n.getEntries()
					.accept(this, ctx));
		}
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setMembers(copyMembers(n.getMembers(), ctx));
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.cutEnclosure();
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ExplicitConstructorInvocationStmt, java.lang.Object)
	 */
	@Override
	public Node visit(ExplicitConstructorInvocationStmt n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		ExplicitConstructorInvocationStmt img = NodeFacade
				.ExplicitConstructorInvocationStmt();
		ctx.setNode(img);
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
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ExpressionStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(ExpressionStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ExpressionStmt img = NodeFacade.ExpressionStmt();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * FieldAccessExpr, java.lang.Object)
	 */
	@Override
	public Node visit(FieldAccessExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		FieldAccessExpr img = NodeFacade.FieldAccessExpr();
		ctx.setNode(img);
		if (n.getScope() != null) {
			ctx.setName(true);
			img.setScope((Expression) n.getScope().accept(this, ctx));
			ctx.setName(false);
		}
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		img.setField(n.getField());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * FieldDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(FieldDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		FieldDeclaration img = NodeFacade.FieldDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getVariables() != null) {
			img.setVariables((NodeList<VariableDeclarator>) n.getVariables()
					.accept(this, ctx));
		}
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ForeachStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(ForeachStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ForeachStmt img = NodeFacade.ForeachStmt();
		ctx.setNode(img);
		if (n.getVariable() != null) {
			img.setVariable((VariableDeclarationExpr) n.getVariable().accept(
					this, ctx));
		}
		if (n.getIterable() != null) {
			img.setIterable((Expression) n.getIterable().accept(this, ctx));
		}
		if (n.getBody() != null) {
			img.setBody((Statement) n.getBody().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ForStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(ForStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ForStmt img = NodeFacade.ForStmt();
		ctx.setNode(img);
		if (n.getInit() != null) {
			img.setInit((NodeList<Expression>) n.getInit().accept(this, ctx));
		}
		if (n.getCompare() != null) {
			img.setCompare((Expression) n.getCompare().accept(this, ctx));
		}
		if (n.getUpdate() != null) {
			img.setUpdate((NodeList<Expression>) n.getUpdate()
					.accept(this, ctx));
		}
		if (n.getBody() != null) {
			img.setBody((Statement) n.getBody().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.IfStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(IfStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		IfStmt img = NodeFacade.IfStmt();
		ctx.setNode(img);
		if (n.getCondition() != null) {
			img.setCondition((Expression) n.getCondition().accept(this, ctx));
		}
		if (n.getThenStmt() != null) {
			img.setThenStmt((Statement) n.getThenStmt().accept(this, ctx));
		}
		if (n.getElseStmt() != null) {
			img.setElseStmt((Statement) n.getElseStmt().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ImportDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(ImportDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ImportDeclaration img = NodeFacade.ImportDeclaration();
		ctx.setNode(img);
		if (n.getName() != null) {
			ctx.setName(true);
			img.setName((NameExpr) n.getName().accept(this, ctx));
			ctx.setName(false);
		}
		img.setStatic(n.isStatic());
		img.setAsterisk(n.isAsterisk());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * InitializerDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(InitializerDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		InitializerDeclaration img = NodeFacade.InitializerDeclaration();
		ctx.setNode(img);
		img.setStatic(n.isStatic());
		if (n.getBlock() != null) {
			img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
		}
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.InstanceOfExpr
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(InstanceOfExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		InstanceOfExpr img = NodeFacade.InstanceOfExpr();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * IntegerLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(IntegerLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		IntegerLiteralExpr img = NodeFacade.IntegerLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * InterfaceDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(InterfaceDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		InterfaceDeclaration img = NodeFacade.InterfaceDeclaration();
		ctx.setNode(img);
		img.setTypeParameters(copeTypeParameters(n.getTypeParameters(), ctx));
		img.setExtendsList(copyClassOrInterfaceTypes(n.getExtendsList(), ctx));
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setMembers(copyMembers(n.getMembers(), ctx));
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.cutEnclosure();
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.JavadocComment
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(JavadocComment n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		JavadocComment img = NodeFacade.JavadocComment();
		ctx.setNode(img);
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LabeledStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(LabeledStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LabeledStmt img = NodeFacade.LabeledStmt();
		ctx.setNode(img);
		img.setLabel(n.getLabel());
		if (n.getStmt() != null) {
			img.setStmt((Statement) n.getStmt().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LambdaBlock
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(LambdaBlock n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LambdaBlock img = NodeFacade.LambdaBlock();
		ctx.setNode(img);
		if (n.getBlockStmt() != null) {
			img.setBlockStmt((BlockStmt) n.getBlockStmt().accept(this, ctx));
		}
		if (n.getParameters() != null) {
			img.setParameters((NodeList<Parameter>) n.getParameters().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LambdaExpr
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(LambdaExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LambdaExpr img = NodeFacade.LambdaExpr();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		if (n.getParameters() != null) {
			img.setParameters((NodeList<Parameter>) n.getParameters().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.LineComment
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(LineComment n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LineComment img = NodeFacade.LineComment();
		ctx.setNode(img);
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * LongLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(LongLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LongLiteralExpr img = NodeFacade.LongLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * MarkerAnnotationExpr, java.lang.Object)
	 */
	@Override
	public Node visit(MarkerAnnotationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MarkerAnnotationExpr img = NodeFacade.MarkerAnnotationExpr();
		ctx.setNode(img);
		if (n.getName() != null) {
			ctx.setName(true);
			img.setName((NameExpr) n.getName().accept(this, ctx));
			ctx.setName(false);
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * MemberValuePair, java.lang.Object)
	 */
	@Override
	public Node visit(MemberValuePair n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MemberValuePair img = NodeFacade.MemberValuePair();
		ctx.setNode(img);
		img.setName(n.getName());
		if (n.getValue() != null) {
			img.setValue((Expression) n.getValue().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.MethodCallExpr
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(MethodCallExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodCallExpr img = NodeFacade.MethodCallExpr();
		ctx.setNode(img);
		if (n.getScope() != null) {
			ctx.setName(true);
			img.setScope((Expression) n.getScope().accept(this, ctx));
			ctx.setName(false);
		}
		if (n.getTypeArgs() != null) {
			ctx.setName(true);
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
			ctx.setName(false);
		}
		img.setName(n.getName());
		if (n.getArgs() != null) {
			img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * MethodDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(MethodDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodDeclaration img = NodeFacade.MethodDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setTypeParameters(copeTypeParameters(n.getTypeParameters(), ctx));
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setName(n.getName());
		if (n.getReceiverType() != null) {
			img.setReceiverType((Type) n.getReceiverType().accept(this, ctx));
		}
		if (n.getReceiverQualifier() != null) {
			img.setReceiverQualifier((NameExpr) n.getReceiverQualifier()
					.accept(this, ctx));
		}
		if (n.getParameters() != null) {
			img.setParameters((NodeList<Parameter>) n.getParameters().accept(
					this, ctx));
		}
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		if (n.getThrowsList() != null) {
			ctx.setName(true);
			img.setThrowsList((NodeList<ClassOrInterfaceType>) n
					.getThrowsList().accept(this, ctx));
			ctx.setName(false);
		}
		if (n.getBlock() != null) {
			img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
		}
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ExpressionMethodReference, java.lang.Object)
	 */
	@Override
	public Node visit(ExpressionMethodReference n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		ExpressionMethodReference img = NodeFacade.ExpressionMethodReference();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setMethodName(n.getMethodName());
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		if (n.getAnnotations() != null) {
			img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations()
					.accept(this, ctx));
		}
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Modifiers,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(Modifiers n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Modifiers img = NodeFacade.Modifiers();
		ctx.setNode(img);
		img.setModifiers(n.getModifiers());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NameExpr,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(NameExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		NameExpr img = NodeFacade.NameExpr();
		ctx.setNode(img);
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.NodeList,
	 * java.lang.Object)
	 */
	@Override
	public <E extends Node> Node visit(NodeList<E> n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		NodeList<E> img = NodeFacade.NodeList();
		ctx.setNode(img);
		if (n.getNodes() != null) {
			List<E> nodes = new ArrayList<E>();
			ctx.setNode(img);
			for (E item : n.getNodes()) {
				if (item != null) {
					nodes.add((E) item.accept(this, ctx));
				}
			}
			img.setNodes(nodes);
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * NormalAnnotationExpr, java.lang.Object)
	 */
	@Override
	public Node visit(NormalAnnotationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		NormalAnnotationExpr img = NodeFacade.NormalAnnotationExpr();
		ctx.setNode(img);
		if (n.getPairs() != null) {
			img.setPairs((NodeList<MemberValuePair>) n.getPairs().accept(this,
					ctx));
		}
		if (n.getName() != null) {
			ctx.setName(true);
			img.setName((NameExpr) n.getName().accept(this, ctx));
			ctx.setName(false);
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * NullLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(NullLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		NullLiteralExpr img = NodeFacade.NullLiteralExpr();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * ObjectCreationExpr, java.lang.Object)
	 */
	@Override
	public Node visit(ObjectCreationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ObjectCreationExpr img = NodeFacade.ObjectCreationExpr();
		ctx.setNode(img);
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
			img.setAnonymousClassBody((NodeList<BodyDeclaration>) n
					.getAnonymousClassBody().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * PackageDeclaration, java.lang.Object)
	 */
	@Override
	public Node visit(PackageDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		PackageDeclaration img = NodeFacade.PackageDeclaration();
		ctx.setNode(img);
		if (n.getName() != null) {
			ctx.setName(true);
			img.setName((NameExpr) n.getName().accept(this, ctx));
			ctx.setName(false);
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Parameter,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(Parameter n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Parameter img = NodeFacade.Parameter();
		ctx.setNode(img);
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
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PrimitiveType
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(PrimitiveType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		PrimitiveType img = NodeFacade.PrimitiveType();
		ctx.setNode(img);
		img.setType(n.getType());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.PrimitiveType
	 * .Primitive, java.lang.Object)
	 */
	@Override
	public Node visit(Primitive n, Context ctx) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.Project,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(Project n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Project img = NodeFacade.Project();
		ctx.setNode(img);
		if (n.getCompilationUnits() != null) {
			List<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
			ctx.setNode(img);
			// one has to set this first because some of visitor methods may
			// want to add some CompilationUnits
			img.setCompilationUnits(NodeFacade.NodeList(compilationUnits));
			for (CompilationUnit item : n.getCompilationUnits()) {
				if (item != null) {
					compilationUnits.add((CompilationUnit) item.accept(this,
							ctx));
				}
			}
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * QualifiedNameExpr, java.lang.Object)
	 */
	@Override
	public Node visit(QualifiedNameExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		QualifiedNameExpr img = NodeFacade.QualifiedNameExpr();
		ctx.setNode(img);
		if (n.getQualifier() != null) {
			img.setQualifier((NameExpr) n.getQualifier().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ReferenceType
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(ReferenceType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ReferenceType img = NodeFacade.ReferenceType();
		ctx.setNode(img);
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ReturnStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(ReturnStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ReturnStmt img = NodeFacade.ReturnStmt();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * SingleMemberAnnotationExpr, java.lang.Object)
	 */
	@Override
	public Node visit(SingleMemberAnnotationExpr n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		SingleMemberAnnotationExpr img = NodeFacade
				.SingleMemberAnnotationExpr();
		ctx.setNode(img);
		if (n.getMemberValue() != null) {
			img.setMemberValue((Expression) n.getMemberValue()
					.accept(this, ctx));
		}
		if (n.getName() != null) {
			ctx.setName(true);
			img.setName((NameExpr) n.getName().accept(this, ctx));
			ctx.setName(false);
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * StringLiteralExpr, java.lang.Object)
	 */
	@Override
	public Node visit(StringLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		StringLiteralExpr img = NodeFacade.StringLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SuperExpr,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(SuperExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SuperExpr img = NodeFacade.SuperExpr();
		ctx.setNode(img);
		if (n.getClassExpression() != null) {
			img.setClassExpression((Expression) n.getClassExpression().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * SuperMethodReference, java.lang.Object)
	 */
	@Override
	public Node visit(SuperMethodReference n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SuperMethodReference img = NodeFacade.SuperMethodReference();
		ctx.setNode(img);
		if (n.getQualifier() != null) {
			img.setQualifier((NameExpr) n.getQualifier().accept(this, ctx));
		}
		img.setMethodName(n.getMethodName());
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		if (n.getAnnotations() != null) {
			img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations()
					.accept(this, ctx));
		}
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * SwitchEntryStmt, java.lang.Object)
	 */
	@Override
	public Node visit(SwitchEntryStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SwitchEntryStmt img = NodeFacade.SwitchEntryStmt();
		ctx.setNode(img);
		if (n.getLabel() != null) {
			img.setLabel((Expression) n.getLabel().accept(this, ctx));
		}
		if (n.getStmts() != null) {
			img.setStmts((NodeList<Statement>) n.getStmts().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.SwitchStmt
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(SwitchStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SwitchStmt img = NodeFacade.SwitchStmt();
		ctx.setNode(img);
		if (n.getSelector() != null) {
			img.setSelector((Expression) n.getSelector().accept(this, ctx));
		}
		if (n.getEntries() != null) {
			img.setEntries((NodeList<SwitchEntryStmt>) n.getEntries().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * SynchronizedStmt, java.lang.Object)
	 */
	@Override
	public Node visit(SynchronizedStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SynchronizedStmt img = NodeFacade.SynchronizedStmt();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		if (n.getBlock() != null) {
			img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ThisExpr,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(ThisExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ThisExpr img = NodeFacade.ThisExpr();
		ctx.setNode(img);
		if (n.getClassExpression() != null) {
			img.setClassExpression((Expression) n.getClassExpression().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.ThrowStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(ThrowStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ThrowStmt img = NodeFacade.ThrowStmt();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TryStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(TryStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TryStmt img = NodeFacade.TryStmt();
		ctx.setNode(img);
		if (n.getResources() != null) {
			img.setResources((NodeList<VariableDeclarationExpr>) n
					.getResources().accept(this, ctx));
		}
		if (n.getTryBlock() != null) {
			img.setTryBlock((BlockStmt) n.getTryBlock().accept(this, ctx));
		}
		if (n.getCatchClauses() != null) {
			img.setCatchClauses((NodeList<CatchClause>) n.getCatchClauses()
					.accept(this, ctx));
		}
		if (n.getFinallyBlock() != null) {
			img.setFinallyBlock((BlockStmt) n.getFinallyBlock().accept(this,
					ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * TypeDeclarationStmt, java.lang.Object)
	 */
	@Override
	public Node visit(TypeDeclarationStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TypeDeclarationStmt img = NodeFacade.TypeDeclarationStmt();
		ctx.setNode(img);
		if (n.getTypeDeclaration() != null) {
			img.setTypeDeclaration((TypeDeclaration) n.getTypeDeclaration()
					.accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * TypeMethodReference, java.lang.Object)
	 */
	@Override
	public Node visit(TypeMethodReference n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TypeMethodReference img = NodeFacade.TypeMethodReference();
		ctx.setNode(img);
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setMethodName(n.getMethodName());
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		if (n.getAnnotations() != null) {
			img.setAnnotations((NodeList<AnnotationExpr>) n.getAnnotations()
					.accept(this, ctx));
		}
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.TypeParameter
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(TypeParameter n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TypeParameter img = NodeFacade.TypeParameter();
		ctx.setNode(img);
		img.setName(n.getName());
		img.setTypeBound(copyClassOrInterfaceTypes(n.getTypeBound(), ctx));
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.UnaryExpr,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(UnaryExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		UnaryExpr img = NodeFacade.UnaryExpr();
		ctx.setNode(img);
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setOperator(n.getOperator());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.UnaryExpr
	 * .UnaryOperator, java.lang.Object)
	 */
	@Override
	public Node visit(UnaryOperator n, Context ctx) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * VariableDeclarationExpr, java.lang.Object)
	 */
	@Override
	public Node visit(VariableDeclarationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VariableDeclarationExpr img = NodeFacade.VariableDeclarationExpr();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getVars() != null) {
			img.setVars((NodeList<VariableDeclarator>) n.getVars().accept(this,
					ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * VariableDeclarator, java.lang.Object)
	 */
	@Override
	public Node visit(VariableDeclarator n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VariableDeclarator img = NodeFacade.VariableDeclarator();
		ctx.setNode(img);
		if (n.getId() != null) {
			img.setId((VariableDeclaratorId) n.getId().accept(this, ctx));
		}
		if (n.getInit() != null) {
			img.setInit((Expression) n.getInit().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.
	 * VariableDeclaratorId, java.lang.Object)
	 */
	@Override
	public Node visit(VariableDeclaratorId n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VariableDeclaratorId img = NodeFacade.VariableDeclaratorId();
		ctx.setNode(img);
		img.setName(n.getName());
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.VoidType,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(VoidType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VoidType img = NodeFacade.VoidType();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.WhileStmt,
	 * java.lang.Object)
	 */
	@Override
	public Node visit(WhileStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		WhileStmt img = NodeFacade.WhileStmt();
		ctx.setNode(img);
		if (n.getCondition() != null) {
			img.setCondition((Expression) n.getCondition().accept(this, ctx));
		}
		if (n.getBody() != null) {
			img.setBody((Statement) n.getBody().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.GenericVisitor#visit(com.digiarea.jse.WildcardType
	 * , java.lang.Object)
	 */
	@Override
	public Node visit(WildcardType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		WildcardType img = NodeFacade.WildcardType();
		ctx.setNode(img);
		if (n.getExtendsType() != null) {
			img.setExtendsType((ReferenceType) n.getExtendsType().accept(this,
					ctx));
		}
		if (n.getSuperType() != null) {
			img.setSuperType((ReferenceType) n.getSuperType().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

}
