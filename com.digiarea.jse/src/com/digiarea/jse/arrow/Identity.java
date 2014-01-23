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
		AnnotationDeclaration img = new AnnotationDeclaration();
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
		return img;
	}

	@Override
	public Node visit(AnnotationMemberDeclaration n, Context ctx)
			throws Exception {
		AnnotationMemberDeclaration img = new AnnotationMemberDeclaration();
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
		return img;
	}

	@Override
	public Node visit(ArrayAccessExpr n, Context ctx) throws Exception {
		ArrayAccessExpr img = new ArrayAccessExpr();
		if (n.getName() != null) {
			img.setName((Expression) n.getName().accept(this, ctx));
		}
		if (n.getIndex() != null) {
			img.setIndex((Expression) n.getIndex().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ArrayCreationExpr n, Context ctx) throws Exception {
		ArrayCreationExpr img = new ArrayCreationExpr();
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
		return img;
	}

	@Override
	public Node visit(ArrayInitializerExpr n, Context ctx) throws Exception {
		ArrayInitializerExpr img = new ArrayInitializerExpr();
		if (n.getValues() != null) {
			img.setValues((NodeList<Expression>) n.getValues()
					.accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ArraySlot n, Context ctx) throws Exception {
		ArraySlot img = new ArraySlot();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(AssertStmt n, Context ctx) throws Exception {
		AssertStmt img = new AssertStmt();
		if (n.getCheck() != null) {
			img.setCheck((Expression) n.getCheck().accept(this, ctx));
		}
		if (n.getMessage() != null) {
			img.setMessage((Expression) n.getMessage().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(AssignExpr n, Context ctx) throws Exception {
		AssignExpr img = new AssignExpr();
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
		return img;
	}

	@Override
	public Node visit(AssignOperator n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(BinaryExpr n, Context ctx) throws Exception {
		BinaryExpr img = new BinaryExpr();
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
		return img;
	}

	@Override
	public Node visit(BinaryOperator n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(BlockComment n, Context ctx) throws Exception {
		BlockComment img = new BlockComment();
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(BlockStmt n, Context ctx) throws Exception {
		BlockStmt img = new BlockStmt();
		if (n.getStatements() != null) {
			img.setStatements((NodeList<Statement>) n.getStatements().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(BooleanLiteralExpr n, Context ctx) throws Exception {
		BooleanLiteralExpr img = new BooleanLiteralExpr();
		img.setValue(n.isValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(BreakStmt n, Context ctx) throws Exception {
		BreakStmt img = new BreakStmt();
		img.setId(n.getId());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(CastExpr n, Context ctx) throws Exception {
		CastExpr img = new CastExpr();
		if (n.getTypes() != null) {
			img.setTypes((NodeList<Type>) n.getTypes().accept(this, ctx));
		}
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(CatchClause n, Context ctx) throws Exception {
		CatchClause img = new CatchClause();
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
		return img;
	}

	@Override
	public Node visit(CharLiteralExpr n, Context ctx) throws Exception {
		CharLiteralExpr img = new CharLiteralExpr();
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ClassDeclaration n, Context ctx) throws Exception {
		ClassDeclaration img = new ClassDeclaration();
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
		return img;
	}

	@Override
	public Node visit(ClassExpr n, Context ctx) throws Exception {
		ClassExpr img = new ClassExpr();
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ClassOrInterfaceType n, Context ctx) throws Exception {
		ClassOrInterfaceType img = new ClassOrInterfaceType();
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
		return img;
	}

	@Override
	public Node visit(CompilationUnit n, Context ctx) throws Exception {
		CompilationUnit img = new CompilationUnit();
		if (n.getPackageDeclaration() != null) {
			img.setPackageDeclaration((PackageDeclaration) n
					.getPackageDeclaration().accept(this, ctx));
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
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ConditionalExpr n, Context ctx) throws Exception {
		ConditionalExpr img = new ConditionalExpr();
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
		return img;
	}

	@Override
	public Node visit(ConstructorDeclaration n, Context ctx) throws Exception {
		ConstructorDeclaration img = new ConstructorDeclaration();
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
		return img;
	}

	@Override
	public Node visit(ContinueStmt n, Context ctx) throws Exception {
		ContinueStmt img = new ContinueStmt();
		img.setId(n.getId());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(DoStmt n, Context ctx) throws Exception {
		DoStmt img = new DoStmt();
		if (n.getBody() != null) {
			img.setBody((Statement) n.getBody().accept(this, ctx));
		}
		if (n.getCondition() != null) {
			img.setCondition((Expression) n.getCondition().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(DoubleLiteralExpr n, Context ctx) throws Exception {
		DoubleLiteralExpr img = new DoubleLiteralExpr();
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(Ellipsis n, Context ctx) throws Exception {
		Ellipsis img = new Ellipsis();
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(EmptyMemberDeclaration n, Context ctx) throws Exception {
		EmptyMemberDeclaration img = new EmptyMemberDeclaration();
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(EmptyStmt n, Context ctx) throws Exception {
		EmptyStmt img = new EmptyStmt();
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(EmptyTypeDeclaration n, Context ctx) throws Exception {
		EmptyTypeDeclaration img = new EmptyTypeDeclaration();
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
		return img;
	}

	@Override
	public Node visit(EnclosedExpr n, Context ctx) throws Exception {
		EnclosedExpr img = new EnclosedExpr();
		if (n.getInner() != null) {
			img.setInner((Expression) n.getInner().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(EnumConstantDeclaration n, Context ctx) throws Exception {
		EnumConstantDeclaration img = new EnumConstantDeclaration();
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
		return img;
	}

	@Override
	public Node visit(EnumDeclaration n, Context ctx) throws Exception {
		EnumDeclaration img = new EnumDeclaration();
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
		return img;
	}

	@Override
	public Node visit(ExplicitConstructorInvocationStmt n, Context ctx)
			throws Exception {
		ExplicitConstructorInvocationStmt img = new ExplicitConstructorInvocationStmt();
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
		return img;
	}

	@Override
	public Node visit(ExpressionStmt n, Context ctx) throws Exception {
		ExpressionStmt img = new ExpressionStmt();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(FieldAccessExpr n, Context ctx) throws Exception {
		FieldAccessExpr img = new FieldAccessExpr();
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
		return img;
	}

	@Override
	public Node visit(FieldDeclaration n, Context ctx) throws Exception {
		FieldDeclaration img = new FieldDeclaration();
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
		return img;
	}

	@Override
	public Node visit(ForeachStmt n, Context ctx) throws Exception {
		ForeachStmt img = new ForeachStmt();
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
		return img;
	}

	@Override
	public Node visit(ForStmt n, Context ctx) throws Exception {
		ForStmt img = new ForStmt();
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
		return img;
	}

	@Override
	public Node visit(IfStmt n, Context ctx) throws Exception {
		IfStmt img = new IfStmt();
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
		return img;
	}

	@Override
	public Node visit(ImportDeclaration n, Context ctx) throws Exception {
		ImportDeclaration img = new ImportDeclaration();
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setStatic(n.isStatic());
		img.setAsterisk(n.isAsterisk());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(InitializerDeclaration n, Context ctx) throws Exception {
		InitializerDeclaration img = new InitializerDeclaration();
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
		return img;
	}

	@Override
	public Node visit(InstanceOfExpr n, Context ctx) throws Exception {
		InstanceOfExpr img = new InstanceOfExpr();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(IntegerLiteralExpr n, Context ctx) throws Exception {
		IntegerLiteralExpr img = new IntegerLiteralExpr();
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(InterfaceDeclaration n, Context ctx) throws Exception {
		InterfaceDeclaration img = new InterfaceDeclaration();
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
		return img;
	}

	@Override
	public Node visit(JavadocComment n, Context ctx) throws Exception {
		JavadocComment img = new JavadocComment();
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(LabeledStmt n, Context ctx) throws Exception {
		LabeledStmt img = new LabeledStmt();
		img.setLabel(n.getLabel());
		if (n.getStmt() != null) {
			img.setStmt((Statement) n.getStmt().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(LambdaBlock n, Context ctx) throws Exception {
		LambdaBlock img = new LambdaBlock();
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
		return img;
	}

	@Override
	public Node visit(LambdaExpr n, Context ctx) throws Exception {
		LambdaExpr img = new LambdaExpr();
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
		return img;
	}

	@Override
	public Node visit(LineComment n, Context ctx) throws Exception {
		LineComment img = new LineComment();
		img.setContent(n.getContent());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(LongLiteralExpr n, Context ctx) throws Exception {
		LongLiteralExpr img = new LongLiteralExpr();
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(MarkerAnnotationExpr n, Context ctx) throws Exception {
		MarkerAnnotationExpr img = new MarkerAnnotationExpr();
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(MemberValuePair n, Context ctx) throws Exception {
		MemberValuePair img = new MemberValuePair();
		img.setName(n.getName());
		if (n.getValue() != null) {
			img.setValue((Expression) n.getValue().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(MethodCallExpr n, Context ctx) throws Exception {
		MethodCallExpr img = new MethodCallExpr();
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
		return img;
	}

	@Override
	public Node visit(MethodDeclaration n, Context ctx) throws Exception {
		MethodDeclaration img = new MethodDeclaration();
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
		return img;
	}

	@Override
	public Node visit(MethodExprRef n, Context ctx) throws Exception {
		MethodExprRef img = new MethodExprRef();
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
		return img;
	}

	@Override
	public Node visit(MethodRef n, Context ctx) throws Exception {
		MethodRef img = new MethodRef();
		if (n.getTypeArgs() != null) {
			img.setTypeArgs((NodeList<Type>) n.getTypeArgs().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(MethodTypeRef n, Context ctx) throws Exception {
		MethodTypeRef img = new MethodTypeRef();
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
		return img;
	}

	@Override
	public Node visit(Modifiers n, Context ctx) throws Exception {
		Modifiers img = new Modifiers();
		img.setModifiers(n.getModifiers());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(NameExpr n, Context ctx) throws Exception {
		NameExpr img = new NameExpr();
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public <E extends Node> Node visit(NodeList<E> n, Context ctx)
			throws Exception {
		NodeList<E> img = new NodeList<E>();
		if (n.getNodes() != null) {
			List<E> nodes = new ArrayList<E>();
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
		return img;
	}

	@Override
	public Node visit(NormalAnnotationExpr n, Context ctx) throws Exception {
		NormalAnnotationExpr img = new NormalAnnotationExpr();
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
		return img;
	}

	@Override
	public Node visit(NullLiteralExpr n, Context ctx) throws Exception {
		NullLiteralExpr img = new NullLiteralExpr();
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ObjectCreationExpr n, Context ctx) throws Exception {
		ObjectCreationExpr img = new ObjectCreationExpr();
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
		return img;
	}

	@Override
	public Node visit(PackageDeclaration n, Context ctx) throws Exception {
		PackageDeclaration img = new PackageDeclaration();
		if (n.getName() != null) {
			img.setName((NameExpr) n.getName().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(Parameter n, Context ctx) throws Exception {
		Parameter img = new Parameter();
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
		return img;
	}

	@Override
	public Node visit(PrimitiveType n, Context ctx) throws Exception {
		PrimitiveType img = new PrimitiveType();
		img.setType(n.getType());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(Primitive n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(Project n, Context ctx) throws Exception {
		Project img = new Project();
		if (n.getCompilationUnits() != null) {
			img.setCompilationUnits((NodeList<CompilationUnit>) n
					.getCompilationUnits().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(QualifiedNameExpr n, Context ctx) throws Exception {
		QualifiedNameExpr img = new QualifiedNameExpr();
		if (n.getQualifier() != null) {
			img.setQualifier((NameExpr) n.getQualifier().accept(this, ctx));
		}
		img.setName(n.getName());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ReferenceType n, Context ctx) throws Exception {
		ReferenceType img = new ReferenceType();
		if (n.getType() != null) {
			img.setType((Type) n.getType().accept(this, ctx));
		}
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ReturnStmt n, Context ctx) throws Exception {
		ReturnStmt img = new ReturnStmt();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(SingleMemberAnnotationExpr n, Context ctx)
			throws Exception {
		SingleMemberAnnotationExpr img = new SingleMemberAnnotationExpr();
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
		return img;
	}

	@Override
	public Node visit(StringLiteralExpr n, Context ctx) throws Exception {
		StringLiteralExpr img = new StringLiteralExpr();
		img.setValue(n.getValue());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(SuperExpr n, Context ctx) throws Exception {
		SuperExpr img = new SuperExpr();
		if (n.getClassExpression() != null) {
			img.setClassExpression((Expression) n.getClassExpression().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(SwitchEntryStmt n, Context ctx) throws Exception {
		SwitchEntryStmt img = new SwitchEntryStmt();
		if (n.getLabel() != null) {
			img.setLabel((Expression) n.getLabel().accept(this, ctx));
		}
		if (n.getStmts() != null) {
			img.setStmts((NodeList<Statement>) n.getStmts().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(SwitchStmt n, Context ctx) throws Exception {
		SwitchStmt img = new SwitchStmt();
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
		return img;
	}

	@Override
	public Node visit(SynchronizedStmt n, Context ctx) throws Exception {
		SynchronizedStmt img = new SynchronizedStmt();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		if (n.getBlock() != null) {
			img.setBlock((BlockStmt) n.getBlock().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ThisExpr n, Context ctx) throws Exception {
		ThisExpr img = new ThisExpr();
		if (n.getClassExpression() != null) {
			img.setClassExpression((Expression) n.getClassExpression().accept(
					this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(ThrowStmt n, Context ctx) throws Exception {
		ThrowStmt img = new ThrowStmt();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(TryStmt n, Context ctx) throws Exception {
		TryStmt img = new TryStmt();
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
		return img;
	}

	@Override
	public Node visit(TypeDeclarationStmt n, Context ctx) throws Exception {
		TypeDeclarationStmt img = new TypeDeclarationStmt();
		if (n.getTypeDeclaration() != null) {
			img.setTypeDeclaration((TypeDeclaration) n.getTypeDeclaration()
					.accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(TypeParameter n, Context ctx) throws Exception {
		TypeParameter img = new TypeParameter();
		img.setName(n.getName());
		img.setTypeBound(copyClassOrInterfaceTypes(n.getTypeBound(), ctx));
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(UnaryExpr n, Context ctx) throws Exception {
		UnaryExpr img = new UnaryExpr();
		if (n.getExpression() != null) {
			img.setExpression((Expression) n.getExpression().accept(this, ctx));
		}
		img.setOperator(n.getOperator());
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(UnaryOperator n, Context ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(VariableDeclarationExpr n, Context ctx) throws Exception {
		VariableDeclarationExpr img = new VariableDeclarationExpr();
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
		return img;
	}

	@Override
	public Node visit(VariableDeclarator n, Context ctx) throws Exception {
		VariableDeclarator img = new VariableDeclarator();
		if (n.getId() != null) {
			img.setId((VariableDeclaratorId) n.getId().accept(this, ctx));
		}
		if (n.getInit() != null) {
			img.setInit((Expression) n.getInit().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(VariableDeclaratorId n, Context ctx) throws Exception {
		VariableDeclaratorId img = new VariableDeclaratorId();
		img.setName(n.getName());
		if (n.getSlots() != null) {
			img.setSlots((NodeList<ArraySlot>) n.getSlots().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(VoidType n, Context ctx) throws Exception {
		VoidType img = new VoidType();
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(WhileStmt n, Context ctx) throws Exception {
		WhileStmt img = new WhileStmt();
		if (n.getCondition() != null) {
			img.setCondition((Expression) n.getCondition().accept(this, ctx));
		}
		if (n.getBody() != null) {
			img.setBody((Statement) n.getBody().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		img.setPosBegin(n.getPosBegin());
		img.setPosEnd(n.getPosEnd());
		return img;
	}

	@Override
	public Node visit(WildcardType n, Context ctx) throws Exception {
		WildcardType img = new WildcardType();
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
		return img;
	}

}
