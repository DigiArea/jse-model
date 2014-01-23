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

@SuppressWarnings("unchecked")
public class Identity implements GenericVisitor<Node, Context>,
		Arrow<Project, Project> {

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

	@Override
	public Project arrow(Project input) throws Exception {
		return (Project) input.accept(this, new Context(new Project()));
	}

	@Override
	public Node visit(AnnotationDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		AnnotationDeclaration img = new AnnotationDeclaration();
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

	@Override
	public Node visit(AnnotationMemberDeclaration n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		AnnotationMemberDeclaration img = new AnnotationMemberDeclaration();
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

	@Override
	public Node visit(ArrayAccessExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArrayAccessExpr img = new ArrayAccessExpr();
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

	@Override
	public Node visit(ArrayCreationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArrayCreationExpr img = new ArrayCreationExpr();
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

	@Override
	public Node visit(ArrayInitializerExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArrayInitializerExpr img = new ArrayInitializerExpr();
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

	@Override
	public Node visit(ArraySlot n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ArraySlot img = new ArraySlot();
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

	@Override
	public Node visit(AssertStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		AssertStmt img = new AssertStmt();
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

	@Override
	public Node visit(AssignExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		AssignExpr img = new AssignExpr();
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

	@Override
	public Node visit(AssignOperator n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(BinaryExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BinaryExpr img = new BinaryExpr();
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

	@Override
	public Node visit(BinaryOperator n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(BlockComment n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BlockComment img = new BlockComment();
		ctx.setNode(img);
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(BlockStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BlockStmt img = new BlockStmt();
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

	@Override
	public Node visit(BooleanLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BooleanLiteralExpr img = new BooleanLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.isValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(BreakStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		BreakStmt img = new BreakStmt();
		ctx.setNode(img);
		img.setId(n.getId());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(CastExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CastExpr img = new CastExpr();
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

	@Override
	public Node visit(CatchClause n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CatchClause img = new CatchClause();
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

	@Override
	public Node visit(CharLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CharLiteralExpr img = new CharLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(ClassDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		ClassDeclaration img = new ClassDeclaration();
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

	@Override
	public Node visit(ClassExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ClassExpr img = new ClassExpr();
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

	@Override
	public Node visit(ClassOrInterfaceType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ClassOrInterfaceType img = new ClassOrInterfaceType();
		ctx.setNode(img);
		if (n.getScope() != null) {
			img.setScope((ClassOrInterfaceType) n.getScope().accept(this, ctx));
		}
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
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

	@Override
	public Node visit(CompilationUnit n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		CompilationUnit img = new CompilationUnit();
		ctx.setNode(img);
		ctx.setUnit(img);
		img.setName(n.getName());
		if (n.getPackageDeclaration() != null) {
			img.setPackageDeclaration((PackageDeclaration) n
					.getPackageDeclaration().accept(this, ctx));
		}
		// as package might be set we can set the new enclosure
		if (img.getPackageDeclaration() != null) {
			ctx.setEnclosure(img.getPackageDeclaration().getName());
		} else {
			ctx.setEnclosure(new NameExpr());
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

	@Override
	public Node visit(ConditionalExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ConditionalExpr img = new ConditionalExpr();
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

	@Override
	public Node visit(ConstructorDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ConstructorDeclaration img = new ConstructorDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setTypeParameters(copeTypeParameters(n.getTypeParameters(), ctx));
		img.setName(n.getName());
		if (n.getParameters() != null) {
			img.setParameters((NodeList<Parameter>) n.getParameters().accept(
					this, ctx));
		}
		if (n.getThrowsList() != null) {
			img.setThrowsList((NodeList<ClassOrInterfaceType>) n
					.getThrowsList().accept(this, ctx));
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

	@Override
	public Node visit(ContinueStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ContinueStmt img = new ContinueStmt();
		ctx.setNode(img);
		img.setId(n.getId());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(DoStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		DoStmt img = new DoStmt();
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

	@Override
	public Node visit(DoubleLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		DoubleLiteralExpr img = new DoubleLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(Ellipsis n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Ellipsis img = new Ellipsis();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(EmptyMemberDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EmptyMemberDeclaration img = new EmptyMemberDeclaration();
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

	@Override
	public Node visit(EmptyStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EmptyStmt img = new EmptyStmt();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(EmptyTypeDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		EmptyTypeDeclaration img = new EmptyTypeDeclaration();
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

	@Override
	public Node visit(EnclosedExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EnclosedExpr img = new EnclosedExpr();
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

	@Override
	public Node visit(EnumConstantDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		EnumConstantDeclaration img = new EnumConstantDeclaration();
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

	@Override
	public Node visit(EnumDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		EnumDeclaration img = new EnumDeclaration();
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

	@Override
	public Node visit(ExplicitConstructorInvocationStmt n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		ExplicitConstructorInvocationStmt img = new ExplicitConstructorInvocationStmt();
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

	@Override
	public Node visit(ExpressionStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ExpressionStmt img = new ExpressionStmt();
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

	@Override
	public Node visit(FieldAccessExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		FieldAccessExpr img = new FieldAccessExpr();
		ctx.setNode(img);
		if (n.getScope() != null) {
			img.setScope((Expression) n.getScope().accept(this, ctx));
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

	@Override
	public Node visit(FieldDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		FieldDeclaration img = new FieldDeclaration();
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

	@Override
	public Node visit(ForeachStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ForeachStmt img = new ForeachStmt();
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

	@Override
	public Node visit(ForStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ForStmt img = new ForStmt();
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

	@Override
	public Node visit(IfStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		IfStmt img = new IfStmt();
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

	@Override
	public Node visit(ImportDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ImportDeclaration img = new ImportDeclaration();
		ctx.setNode(img);
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setStatic(n.isStatic());
		img.setAsterisk(n.isAsterisk());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(InitializerDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		InitializerDeclaration img = new InitializerDeclaration();
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

	@Override
	public Node visit(InstanceOfExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		InstanceOfExpr img = new InstanceOfExpr();
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

	@Override
	public Node visit(IntegerLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		IntegerLiteralExpr img = new IntegerLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(InterfaceDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		InterfaceDeclaration img = new InterfaceDeclaration();
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

	@Override
	public Node visit(JavadocComment n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		JavadocComment img = new JavadocComment();
		ctx.setNode(img);
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(LabeledStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LabeledStmt img = new LabeledStmt();
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

	@Override
	public Node visit(LambdaBlock n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LambdaBlock img = new LambdaBlock();
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

	@Override
	public Node visit(LambdaExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LambdaExpr img = new LambdaExpr();
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

	@Override
	public Node visit(LineComment n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LineComment img = new LineComment();
		ctx.setNode(img);
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(LongLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		LongLiteralExpr img = new LongLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(MarkerAnnotationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MarkerAnnotationExpr img = new MarkerAnnotationExpr();
		ctx.setNode(img);
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(MemberValuePair n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MemberValuePair img = new MemberValuePair();
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

	@Override
	public Node visit(MethodCallExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodCallExpr img = new MethodCallExpr();
		ctx.setNode(img);
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
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(MethodDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodDeclaration img = new MethodDeclaration();
		ctx.setNode(img);
		if (n.getModifiers() != null) {
			img.setModifiers((Modifiers) n.getModifiers().accept(this, ctx));
		}
		img.setTypeParameters(copeTypeParameters(n.getTypeParameters(), ctx));
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setName(n.getName());
		if (n.getParameters() != null) {
			img.setParameters((NodeList<Parameter>) n.getParameters().accept(
					this, ctx));
		}
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		if (n.getThrowsList() != null) {
			img.setThrowsList((NodeList<ClassOrInterfaceType>) n
					.getThrowsList().accept(this, ctx));
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

	@Override
	public Node visit(MethodExprRef n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodExprRef img = new MethodExprRef();
		ctx.setNode(img);
		if (n.getScope() != null) {
			img.setScope((Expression) n.getScope().accept(this, ctx));
		}
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(MethodRef n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodRef img = new MethodRef();
		ctx.setNode(img);
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(MethodTypeRef n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		MethodTypeRef img = new MethodTypeRef();
		ctx.setNode(img);
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(Modifiers n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Modifiers img = new Modifiers();
		ctx.setNode(img);
		img.setModifiers(n.getModifiers());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(NameExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		NameExpr img = new NameExpr();
		ctx.setNode(img);
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public <E extends Node> Node visit(NodeList<E> n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		NodeList<E> img = new NodeList<E>();
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

	@Override
	public Node visit(NormalAnnotationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		NormalAnnotationExpr img = new NormalAnnotationExpr();
		ctx.setNode(img);
		if (n.getPairs() != null) {
			img.setPairs((NodeList<MemberValuePair>) n.getPairs().accept(this,
					ctx));
		}
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(NullLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		NullLiteralExpr img = new NullLiteralExpr();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(ObjectCreationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ObjectCreationExpr img = new ObjectCreationExpr();
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

	@Override
	public Node visit(PackageDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		PackageDeclaration img = new PackageDeclaration();
		ctx.setNode(img);
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(Parameter n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Parameter img = new Parameter();
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

	@Override
	public Node visit(PrimitiveType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		PrimitiveType img = new PrimitiveType();
		ctx.setNode(img);
		img.setType(n.getType());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(Primitive n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(Project n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		Project img = new Project();
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

	@Override
	public Node visit(QualifiedNameExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		QualifiedNameExpr img = new QualifiedNameExpr();
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

	@Override
	public Node visit(ReferenceType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ReferenceType img = new ReferenceType();
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

	@Override
	public Node visit(ReturnStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ReturnStmt img = new ReturnStmt();
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

	@Override
	public Node visit(SingleMemberAnnotationExpr n, Context ctx)
			throws Exception {
		Node node = ctx.getNode();
		SingleMemberAnnotationExpr img = new SingleMemberAnnotationExpr();
		ctx.setNode(img);
		if (n.getMemberValue() != null) {
			img.setMemberValue((Expression) n.getMemberValue()
					.accept(this, ctx));
		}
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(StringLiteralExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		StringLiteralExpr img = new StringLiteralExpr();
		ctx.setNode(img);
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(SuperExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SuperExpr img = new SuperExpr();
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

	@Override
	public Node visit(SwitchEntryStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SwitchEntryStmt img = new SwitchEntryStmt();
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

	@Override
	public Node visit(SwitchStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SwitchStmt img = new SwitchStmt();
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

	@Override
	public Node visit(SynchronizedStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		SynchronizedStmt img = new SynchronizedStmt();
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

	@Override
	public Node visit(ThisExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ThisExpr img = new ThisExpr();
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

	@Override
	public Node visit(ThrowStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ThrowStmt img = new ThrowStmt();
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

	@Override
	public Node visit(TryStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TryStmt img = new TryStmt();
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

	@Override
	public Node visit(TypeDeclarationStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TypeDeclarationStmt img = new TypeDeclarationStmt();
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

	@Override
	public Node visit(TypeParameter n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		TypeParameter img = new TypeParameter();
		ctx.setNode(img);
		img.setName(n.getName());
		img.setTypeBound(copyClassOrInterfaceTypes(n.getTypeBound(), ctx));
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(UnaryExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		UnaryExpr img = new UnaryExpr();
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

	@Override
	public Node visit(UnaryOperator n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(VariableDeclarationExpr n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VariableDeclarationExpr img = new VariableDeclarationExpr();
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

	@Override
	public Node visit(VariableDeclarator n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VariableDeclarator img = new VariableDeclarator();
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

	@Override
	public Node visit(VariableDeclaratorId n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VariableDeclaratorId img = new VariableDeclaratorId();
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

	@Override
	public Node visit(VoidType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		VoidType img = new VoidType();
		ctx.setNode(img);
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		ctx.setNode(node);
		return img;
	}

	@Override
	public Node visit(WhileStmt n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		WhileStmt img = new WhileStmt();
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

	@Override
	public Node visit(WildcardType n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		WildcardType img = new WildcardType();
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
