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
import java.util.Arrays;
import java.util.List;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.BinaryExpr;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.Expression;
import com.digiarea.jse.FieldDeclaration;
import com.digiarea.jse.ForStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.MethodCallExpr;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.ObjectCreationExpr;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.UnaryExpr;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.utils.NodeUtils;

/**
 * The Class Enumer.
 * 
 * <p>
 * Turns an enum in a project into regular java class.
 * </p>
 * <ul>
 * <li>An enum mapped into regular abstract class with public fields of
 * corresponding instances of the abstract class.</li>
 * <li>All default methods (name, ordinal, etc.) are presented in the abstract
 * class.</li>
 * <li>Full support of enum syntax (constructors in constants definitions,
 * private constructor, user defined methods, etc.)</li>
 * </ul>
 * 
 */
public class Enumer extends Identity {

	/** The Constant VALUES_FIELD_NAME. */
	private static final String VALUES_FIELD_NAME = "$VALUES";

	/** The Constant STRINGS_FIELD_NAME. */
	private static final String STRINGS_FIELD_NAME = "$STRINGS";

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.inetgames.tools.tf.arrow.Identity#visit(biz.inetgames.tools.tf.
	 * EnumConstantDeclaration, biz.inetgames.tools.tf.visitor.Context)
	 */
	@Override
	public Node visit(EnumConstantDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		// get enclosing class
		ClassDeclaration encolsing = (ClassDeclaration) ctx.getNode();
		// System.out.println(encolsing);
		// change EnumConstantDeclaration to FieldDeclaration
		FieldDeclaration img = NodeFacade.FieldDeclaration();
		ctx.setNode(img);
		// make corresponding class type
		ClassOrInterfaceType type = NodeFacade.ClassOrInterfaceType(NodeFacade
				.NameExpr(encolsing.getName()));
		img.setType(type);
		// make corresponding variable id
		VariableDeclaratorId varId = NodeFacade.VariableDeclaratorId(n
				.getName());
		// make variable declarator
		VariableDeclarator varDecl = NodeFacade.VariableDeclarator(varId, null);
		List<VariableDeclarator> decls = new ArrayList<VariableDeclarator>();
		decls.add(varDecl);
		img.setVariables(NodeFacade.NodeList(decls));
		// set modifiers to public static final
		img.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC | Modifiers.STATIC | Modifiers.FINAL));
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		// make NodeFacade.initializer for the field
		ObjectCreationExpr initExpr = NodeFacade.ObjectCreationExpr();
		List<Expression> args = null;
		initExpr.setType(type);
		if (n.getArgs() != null) {
			args = new ArrayList<Expression>();
			for (Expression e : n.getArgs()) {
				args.add((Expression) e.accept(this, ctx));
			}
			initExpr.setArgs(NodeFacade.NodeList(args));
		}
		varDecl.setInit(initExpr);
		// change type for initExpr
		ClassOrInterfaceType newType = NodeFacade
				.ClassOrInterfaceType(NodeFacade.NameExpr(n.getName()));
		initExpr.setType(newType);
		// make NodeFacade.inner class
		ClassDeclaration clazz = NodeFacade.ClassDeclaration();
		clazz.setModifiers(NodeFacade.Modifiers(Modifiers.PRIVATE | Modifiers.STATIC));
		clazz.setName(n.getName());
		clazz.setExtendsType(type);
		List<BodyDeclaration> classBody = new ArrayList<BodyDeclaration>();
		// make NodeFacade.constructors
		List<ConstructorDeclaration> constructors = copyConstructors(encolsing,
				args != null ? args.size() : 0, n.getName(), args, ctx);
		for (ConstructorDeclaration cd : constructors) {
			classBody.add(cd);
		}
		if (n.getClassBody() != null) {
			// add all members from EnumConstantDeclaration
			for (BodyDeclaration member : n.getClassBody()) {
				classBody.add((BodyDeclaration) member.accept(this, ctx));
			}
		}
		classBody.add(makeOrdinalMethod((Integer) ctx.getObject()));
		classBody.add(makeNameMethod(n.getName()));
		clazz.setMembers(NodeFacade.NodeList(classBody));
		NodeUtils.addMember(encolsing, clazz);

		ctx.setNode(node);
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.inetgames.tools.tf.arrow.Identity#visit(biz.inetgames.tools.tf.
	 * EnumDeclaration, biz.inetgames.tools.tf.visitor.Context)
	 */
	@Override
	public Node visit(EnumDeclaration n, Context ctx) throws Exception {
		Node node = ctx.getNode();
		ctx.addEnclosure(n.getName());
		Integer counter = new Integer(0);
		ctx.setObject(counter);
		ClassDeclaration img = NodeFacade.ClassDeclaration();
		img.setName(n.getName());
		// set the abstract modifier for the class (enums does not allow
		// abstract modifier) but we need this for 'ordinal' abstract method
		img.setModifiers(NodeFacade.Modifiers(Modifiers.addModifier(n
				.getModifiers().getModifiers(), Modifiers.ABSTRACT)));
		// every enum type implements comparable
		img.setImplementsList(copyClassOrInterfaceTypes(n.getImplementsList(),
				ctx));
		ClassOrInterfaceType impl = NodeFacade.ClassOrInterfaceType(NodeFacade
				.NameExpr("Comparable"));
		Type typeArg = NodeFacade.ClassOrInterfaceType(NodeFacade.NameExpr(img
				.getName()));
		impl.setTypeArgs(NodeFacade.NodeList(typeArg));
		if (img.getImplementsList() == null) {
			img.setImplementsList(NodeFacade.NodeList(impl));
		} else {
			img.getImplementsList().add(impl);
		}
		// all enums are static by default as an inner class, so we need to
		// reconstruct the static modifier if it is not presented in enum
		// declaration if we are not in compilation unit context
		if (ctx.getNode() instanceof TypeDeclaration
				&& !Modifiers.isStatic(img.getModifiers().getModifiers())) {
			img.setModifiers(NodeFacade.Modifiers(Modifiers.addModifier(img
					.getModifiers().getModifiers(), Modifiers.STATIC)));
		}
		ctx.setNode(img);
		if (n.getJavaDoc() != null) {
			img.setJavaDoc((JavadocComment) n.getJavaDoc().accept(this, ctx));
		}
		img.setAnnotations(copyAnnotations(n.getAnnotations(), n, ctx));
		List<BodyDeclaration> members = new ArrayList<BodyDeclaration>();
		// set members before anything else because members can be inner classes
		// and fields generated in visit(EnumConstantDeclaration n, Context ctx)
		img.setMembers(NodeFacade.NodeList(members));
		// process
		if (n.getMembers() != null) {
			for (BodyDeclaration member : n.getMembers()) {
				members.add((BodyDeclaration) member.accept(this, ctx));
			}
		}
		if (n.getEntries() != null) {
			List<Expression> valExprs = new ArrayList<Expression>();
			List<Expression> strExprs = new ArrayList<Expression>();
			for (EnumConstantDeclaration e : n.getEntries()) {
				FieldDeclaration field = (FieldDeclaration) e.accept(this, ctx);
				valExprs.add(NodeFacade.NameExpr(e.getName()));
				strExprs.add(NodeFacade.StringLiteralExpr(e.getName()));
				members.add(field);
				ctx.setObject(++counter);
			}
			ReferenceType valuesType = NodeFacade.ReferenceType(NodeFacade
					.ClassOrInterfaceType(NodeFacade.NameExpr(img.getName())),
					1);
			members.add(makeReferenceField(valuesType, valExprs,
					VALUES_FIELD_NAME));
			members.add(makeValuesMethod(valuesType, VALUES_FIELD_NAME));
			ReferenceType stringsType = NodeFacade.ReferenceType(NodeFacade
					.ClassOrInterfaceType(NodeFacade.NameExpr("String")), 1);
			members.add(makeReferenceField(stringsType, strExprs,
					STRINGS_FIELD_NAME));
			members.add(makeValueOfMethod(NodeFacade
					.ClassOrInterfaceType(NodeFacade.NameExpr(img.getName()))));
		}
		// add compareTo
		members.add(makeCompareTo(typeArg));
		// add abstract 'ordinal' method
		members.add(makeAbstractOrdinalMethod());
		// add abstract 'name' method
		members.add(makeAbstractNameMethod());
		ctx.cutEnclosure();
		ctx.setNode(node);
		List<AnnotationExpr> annotations = img.getAnnotations();
		if (annotations == null) {
			annotations = new ArrayList<>();
		}
		// make javax.annotation.Generated
		annotations.add(NodeUtils.makeGenerated(new String[] {
				getClass().getName(), ctx.getEnclosure().toString() },
				"Generated from: " + ctx.getEnclosure().toString()));
		return img;
	}

	/**
	 * Copy constructors.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param paramNumber
	 *            the param number
	 * @param newName
	 *            the NodeFacade.name
	 * @param args
	 *            the args
	 * @param ctx
	 *            the ctx
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	private List<ConstructorDeclaration> copyConstructors(
			ClassDeclaration clazz, int paramNumber, String newName,
			List<Expression> args, Context ctx) throws Exception {
		List<ConstructorDeclaration> constructors = new ArrayList<ConstructorDeclaration>();
		for (BodyDeclaration member : clazz.getMembers()) {
			if (member instanceof ConstructorDeclaration) {
				ConstructorDeclaration decl = (ConstructorDeclaration) member;
				List<Parameter> parameters = decl.getParameters();
				if ((parameters != null && parameters.size() == paramNumber)
						|| paramNumber == 0) {
					// change modifier of the enclosing abstract class to
					// package level for private constructors
					if (Modifiers.isPrivate(decl.getModifiers().getModifiers())) {
						decl.setModifiers(NodeFacade.Modifiers(Modifiers
								.removeModifier(decl.getModifiers()
										.getModifiers(), Modifiers.PRIVATE)));
					}
					ConstructorDeclaration cd = (ConstructorDeclaration) decl
							.accept(this, ctx);
					cd.setName(newName);
					BlockStmt blockStmt = NodeFacade.BlockStmt();
					Statement invocation = NodeFacade
							.ExplicitConstructorInvocationStmt(null, false,
									null, args);
					blockStmt.setStatements(NodeFacade.NodeList(invocation));
					cd.setBlock(blockStmt);
					constructors.add(cd);
				}
			}
		}
		return constructors;
	}

	/**
	 * Make reference field.
	 * 
	 * @param valuesType
	 *            the values type
	 * @param exprs
	 *            the exprs
	 * @param name
	 *            the name
	 * @return the field declaration
	 */
	private FieldDeclaration makeReferenceField(ReferenceType valuesType,
			List<Expression> exprs, String name) {
		FieldDeclaration values = NodeFacade.FieldDeclaration();
		values.setModifiers(NodeFacade.Modifiers(Modifiers.PRIVATE
				| Modifiers.STATIC | Modifiers.FINAL));
		values.setType(valuesType);
		values.setVariables(NodeFacade.NodeList(NodeFacade.VariableDeclarator(
				NodeFacade.VariableDeclaratorId(name),
				NodeFacade.ArrayInitializerExpr(exprs))));
		return values;
	}

	/**
	 * Make values method.
	 * 
	 * @param valuesType
	 *            the values type
	 * @param name
	 *            the name
	 * @return the method declaration
	 */
	private MethodDeclaration makeValuesMethod(ReferenceType valuesType,
			String name) {
		// public static EnumType[] values() { return $VALUES.clone(); }
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC
				| Modifiers.STATIC));
		method.setType(valuesType);
		method.setName("values");
		MethodCallExpr expr = NodeFacade.MethodCallExpr(
				NodeFacade.NameExpr(name), null, "clone", null);
		Statement stmt = NodeFacade.ReturnStmt(expr);
		method.setBlock(NodeFacade.BlockStmt(Arrays.asList(stmt)));
		return method;
	}

	/**
	 * Make value of method.
	 * 
	 * @param type
	 *            the type
	 * @return the method declaration
	 */
	private MethodDeclaration makeValueOfMethod(Type type) {
		/*
		 * public static EnumType valueOf(String name) { for (int i = 1; i <
		 * $STRINGS.length; i++) { if ($STRINGS[i].equals(name)) { return
		 * $VALUES[i]; } } return null; }
		 */
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC
				| Modifiers.STATIC));
		method.setType(type);
		method.setName("valueOf");
		Parameter param = NodeFacade.Parameter(
				NodeFacade.ClassOrInterfaceType(NodeFacade.NameExpr("String")),
				"name");
		method.setParameters(NodeFacade.NodeList(param));
		// for statement
		ForStmt forStmt = NodeFacade.ForStmt();
		Expression init = NodeFacade.VariableDeclarationExpr(
				NodeFacade.PrimitiveType(PrimitiveType.Primitive.Int), "i",
				NodeFacade.IntegerLiteralExpr(1));
		// set init
		forStmt.setInit(NodeFacade.NodeList(init));
		// set compare
		forStmt.setCompare(NodeFacade.BinaryExpr(
				NodeFacade.NameExpr("i"),
				NodeFacade.FieldAccessExpr(
						NodeFacade.NameExpr(STRINGS_FIELD_NAME), null, "length"),
				BinaryExpr.BinaryOperator.less));
		Expression update = NodeFacade.UnaryExpr(NodeFacade.NameExpr("i"),
				UnaryExpr.UnaryOperator.posIncrement);
		// set update
		forStmt.setUpdate(NodeFacade.NodeList(update));
		Expression refExpr = NodeFacade.ArrayAccessExpr(
				NodeFacade.NameExpr(STRINGS_FIELD_NAME),
				NodeFacade.NameExpr("i"));
		MethodCallExpr condition = NodeFacade.MethodCallExpr(refExpr, null,
				"equals",
				Arrays.asList((Expression) NodeFacade.NameExpr("name")));
		Statement returnStmt = NodeFacade.ReturnStmt(NodeFacade
				.ArrayAccessExpr(NodeFacade.NameExpr(VALUES_FIELD_NAME),
						NodeFacade.NameExpr("i")));
		// if statement
		Statement ifStmt = NodeFacade.IfStmt(condition,
				NodeFacade.BlockStmt(NodeFacade.NodeList(returnStmt)), null);
		// set body
		forStmt.setBody(NodeFacade.BlockStmt(Arrays.asList(ifStmt)));
		List<Statement> stmts = new ArrayList<Statement>();
		stmts.add(forStmt);
		stmts.add(NodeFacade.ReturnStmt(NodeFacade.NullLiteralExpr()));
		method.setBlock(NodeFacade.BlockStmt(stmts));
		return method;
	}

	/**
	 * Make ordinal method.
	 * 
	 * @param ordinal
	 *            the ordinal
	 * @return the method declaration
	 */
	private MethodDeclaration makeOrdinalMethod(Integer ordinal) {
		// public int ordinal() { return ordinal; }
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC));
		method.setType(NodeFacade.PrimitiveType(PrimitiveType.Primitive.Int));
		method.setName("ordinal");
		Statement stmt = NodeFacade.ReturnStmt(NodeFacade
				.IntegerLiteralExpr(ordinal));
		method.setBlock(NodeFacade.BlockStmt(Arrays.asList(stmt)));
		return method;
	}

	/**
	 * Make abstract ordinal method.
	 * 
	 * @return the method declaration
	 */
	private MethodDeclaration makeAbstractOrdinalMethod() {
		// public abstract int ordinal();
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC
				| Modifiers.ABSTRACT));
		method.setType(NodeFacade.INT_TYPE);
		method.setName("ordinal");
		return method;
	}

	/**
	 * Make name method.
	 * 
	 * @param name
	 *            the name
	 * @return the method declaration
	 */
	private MethodDeclaration makeNameMethod(String name) {
		// public String name() { return name; }
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC));
		method.setType(NodeFacade.ClassOrInterfaceType(NodeFacade
				.NameExpr("String")));
		method.setName("name");
		Statement stmt = NodeFacade.ReturnStmt(NodeFacade
				.StringLiteralExpr(name));
		method.setBlock(NodeFacade.BlockStmt(Arrays.asList(stmt)));
		return method;
	}

	/**
	 * Make abstract name method.
	 * 
	 * @return the method declaration
	 */
	private MethodDeclaration makeAbstractNameMethod() {
		// public abstract String name();
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC
				| Modifiers.ABSTRACT));
		method.setType(NodeFacade.ClassOrInterfaceType(NodeFacade
				.NameExpr("String")));
		method.setName("name");
		return method;
	}

	/**
	 * Make compare to.
	 * 
	 * @param typeArg
	 *            the type arg
	 * @return the method declaration
	 */
	private MethodDeclaration makeCompareTo(Type typeArg) {
		// public int compareTo(EnumType type)
		// { return this.ordinal() - type.ordinal(); }
		MethodDeclaration method = NodeFacade.MethodDeclaration();
		method.setModifiers(NodeFacade.Modifiers(Modifiers.PUBLIC));
		method.setType(NodeFacade.INT_TYPE);
		method.setName("compareTo");
		Parameter param = NodeFacade.Parameter(typeArg, "type");
		method.setParameters(NodeFacade.NodeList(param));
		MethodCallExpr mcLeft = NodeFacade.MethodCallExpr(
				NodeFacade.ThisExpr(), "ordinal");
		MethodCallExpr mcRight = NodeFacade.MethodCallExpr(
				NodeFacade.NameExpr("type"), "ordinal");
		BinaryExpr binaryExpr = NodeFacade.BinaryExpr(mcLeft, mcRight,
				BinaryOperator.minus);
		Statement stmt = NodeFacade.ReturnStmt(binaryExpr);
		method.setBlock(NodeFacade.BlockStmt(Arrays.asList(stmt)));
		// override annotation
		AnnotationExpr a = NodeFacade.MarkerAnnotationExpr(NodeFacade
				.NameExpr("Override"));
		method.setAnnotations(NodeFacade.NodeList(a));
		return method;
	}
}
