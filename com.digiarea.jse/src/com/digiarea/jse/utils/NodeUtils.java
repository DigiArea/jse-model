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
package com.digiarea.jse.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.digiarea.common.utils.Delimiter;
import com.digiarea.common.utils.StringUtils;
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.Expression;
import com.digiarea.jse.FieldAccessExpr;
import com.digiarea.jse.IntegerLiteralExpr;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.Project;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;
import com.digiarea.jse.TypeDeclaration;

public class NodeUtils {

	private static final String JAVAX_ANNOTATION_GENERATED = "javax.annotation.Generated";

	private static final String ADD_ALL = "addAll";

	private static final String SET = "set";

	private static final String IS = "is";

	private static final String GET = "get";

	/**
	 * Equals by last.
	 * 
	 * @param expr
	 *            the expr
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public static boolean equalsByLast(NameExpr expr, NameExpr name) {
		return NodeUtils.getLast(expr).getName().equals(name.getName());
	}

	/**
	 * Equals by last.
	 * 
	 * @param expr
	 *            the expr
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public static boolean equalsByLast(NameExpr expr, String name) {
		return NodeUtils.getLast(expr).getName().equals(name);
	}

	/**
	 * Gets the last.
	 * 
	 * @param expr
	 *            the expr
	 * @return the last
	 */
	public static NameExpr getLast(NameExpr expr) {
		if (expr instanceof QualifiedNameExpr) {
			return getLast(((QualifiedNameExpr) expr).getQualifier());
		} else {
			return expr;
		}
	}

	/**
	 * Merge.
	 * 
	 * @param tail
	 *            the tail
	 * @param head
	 *            the head
	 * @return the name expr
	 */
	public static NameExpr merge(NameExpr tail, NameExpr head) {
		if (tail == null) {
			return head;
		} else if (head == null) {
			return tail;
		} else if (head instanceof QualifiedNameExpr) {
			NameExpr qualifier = ((QualifiedNameExpr) head).getQualifier();
			if (qualifier != null) {
				return NodeFacade.QualifiedNameExpr(merge(tail, qualifier),
						head.getName());
			}
		}
		return NodeFacade.QualifiedNameExpr(tail, head.getName());
	}

	/**
	 * Adds the compilation unit.
	 * 
	 * @param project
	 *            the project
	 * @param unit
	 *            the unit
	 */
	public static void addCompilationUnit(Project project, CompilationUnit unit) {
		List<CompilationUnit> units = project.getCompilationUnits();
		if (units == null) {
			units = new ArrayList<CompilationUnit>();
			project.setCompilationUnits(NodeFacade.NodeList(units));
		}
		units.add(unit);
	}

	/**
	 * Gets the qualified name.
	 * 
	 * @param unit
	 *            the unit
	 * @return the qualified name
	 */
	public static QualifiedNameExpr getQualifiedName(CompilationUnit unit) {
		NameExpr qualifiedName = NodeFacade.NameExpr(unit.getName());
		if (qualifiedName instanceof QualifiedNameExpr) {
			return (QualifiedNameExpr) qualifiedName;
		} else {
			return NodeFacade.QualifiedNameExpr(null, qualifiedName.getName());
		}
	}

	public static String toString(NameExpr name, String dlim) {
		if (name instanceof QualifiedNameExpr) {
			NameExpr q = ((QualifiedNameExpr) name).getQualifier();
			if (q != null) {
				return toString(q, dlim) + dlim + name.getName();
			}
		}
		return name.getName();
	}

	public static String toString(NameExpr name) {
		return toString(name, Delimiter.DOT.toString());
	}

	/**
	 * The Generated annotation is used to mark source code that has been
	 * generated. It can also be used to differentiate user written code from
	 * generated code in a single file. When used, the value element must have
	 * the name of the code generator. The recommended convention is to use the
	 * fully qualified name of the code generator in the value field . For
	 * example: com.company.package.classname. The date element is used to
	 * indicate the date the source was generated. The date element must follow
	 * the ISO 8601 standard. For example the date element would have the
	 * following value 2001-07-04T12:08:56.235-0700 which represents 2001-07-04
	 * 12:08:56 local time in the U.S. Pacific Time time zone. The comment
	 * element is a place holder for any comments that the code generator may
	 * want to include in the generated code.
	 * 
	 * @param value
	 * @param comments
	 * @return
	 */
	public static NormalAnnotationExpr makeGenerated(String[] value,
			String comments) {
		List<Expression> values = new ArrayList<>();
		for (int i = 0; i < value.length; i++) {
			values.add(NodeFacade.StringLiteralExpr(value[i]));
		}
		List<MemberValuePair> pairs = new ArrayList<>();
		pairs.add(NodeFacade.MemberValuePair("value",
				NodeFacade.ArrayInitializerExpr(values)));
		pairs.add(NodeFacade.MemberValuePair("comments",
				NodeFacade.StringLiteralExpr(comments)));
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		df.setTimeZone(tz);
		pairs.add(NodeFacade.MemberValuePair("date",
				NodeFacade.StringLiteralExpr(df.format(new Date()))));
		return NodeFacade.NormalAnnotationExpr(pairs,
				NodeFacade.NameExpr(JAVAX_ANNOTATION_GENERATED));

	}

	/**
	 * Adds the member.
	 * 
	 * @param type
	 *            the type
	 * @param decl
	 *            the decl
	 */
	public static void addMember(TypeDeclaration type, BodyDeclaration decl) {
		if (type != null && decl != null) {
			List<BodyDeclaration> members = type.getMembers();
			if (members == null) {
				members = new ArrayList<BodyDeclaration>();
				type.setMembers(NodeFacade.NodeList(members));
			}
			members.add(decl);
		}
	}

	/**
	 * Checks if is string.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is string
	 */
	public static boolean isString(Type type) {
		if (type instanceof ReferenceType) {
			return isString(((ReferenceType) type).getType());
		} else if (type instanceof ClassOrInterfaceType) {
			String name = NodeUtils.toNameExpr((ClassOrInterfaceType) type)
					.toString();
			return name.equals("java.lang.String");
		}
		return false;
	}

	/**
	 * To name expr.
	 * 
	 * @param type
	 *            the type
	 * @return the name expr
	 */
	public static NameExpr toNameExpr(ClassOrInterfaceType type) {
		if (type.getScope() != null) {
			return NodeFacade.QualifiedNameExpr(toNameExpr(type.getScope()),
					type.getName().getName());
		}
		return type.getName();
	}

	/**
	 * Checks if is wrapped.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is wrapped
	 */
	public static boolean isWrapped(Type type) {
		return LangUtils.isQualifiedWrappedType(NodeFacade.NameExpr(type
				.toString()));
	}

	public static boolean isBoolean(Type type, boolean isNative) {
		if (type instanceof PrimitiveType) {
			return type.equals(NodeFacade.BOOLEAN_TYPE);
		} else if (type instanceof ReferenceType) {
			return isBoolean(((ReferenceType) type).getType(), isNative);
		} else if (!isNative && type instanceof ClassOrInterfaceType) {
			return type.equals(LangUtils.BOOLEAN_TYPE);
		} else {
			return false;
		}
	}

	public static boolean isBoolean(Type type) {
		return isBoolean(type, false);
	}

	/**
	 * Merge projects.
	 * 
	 * @param projects
	 *            the projects
	 * @return the project
	 */
	public static Project mergeProjects(Project... projects) {
		Project p = NodeFacade.Project();
		List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		p.setCompilationUnits(NodeFacade.NodeList(units));
		for (Project project : projects) {
			List<CompilationUnit> compilationUnits = project
					.getCompilationUnits();
			if (compilationUnits != null) {
				units.addAll(compilationUnits);
			}
		}
		return p;
	}

	/**
	 * Gets the main type.
	 * 
	 * @param unit
	 *            the unit
	 * @return the main type
	 */
	public static TypeDeclaration getMainType(CompilationUnit unit) {
		if (unit.getTypes() != null) {
			for (TypeDeclaration type : unit.getTypes()) {
				if (type.getName().equals(
						NodeFacade.NameExpr(unit.getName()).getName())) {
					return type;
				}
			}
		}
		return null;
	}

	public static int getModifiers(Expression modifiers) {
		if (modifiers instanceof IntegerLiteralExpr) {
			return Integer.valueOf(((IntegerLiteralExpr) modifiers).getValue());
		} else if (modifiers instanceof FieldAccessExpr) {
			FieldAccessExpr field = (FieldAccessExpr) modifiers;
			return NodeUtils.getModifiersValue(field.getField());
		} else if (modifiers instanceof BinaryExpr) {
			BinaryExpr expr = (BinaryExpr) modifiers;
			if (expr.getOperator() == BinaryExpr.BinaryOperator.binOr) {
				return getModifiers(expr.getLeft())
						| getModifiers(expr.getRight());
			}
		}
		return 0;
	}

	public static int getModifiersValue(String name) {
		if ("ABSTRACT".equals(name))
			return Modifiers.ABSTRACT;
		else if ("FINAL".equals(name))
			return Modifiers.FINAL;
		else if ("NATIVE".equals(name))
			return Modifiers.NATIVE;
		else if ("PRIVATE".equals(name))
			return Modifiers.PRIVATE;
		else if ("PROTECTED".equals(name))
			return Modifiers.PROTECTED;
		else if ("PUBLIC".equals(name))
			return Modifiers.PUBLIC;
		else if ("STATIC".equals(name))
			return Modifiers.STATIC;
		else if ("STRICTFP".equals(name))
			return Modifiers.STRICTFP;
		else if ("SYNCHRONIZED".equals(name))
			return Modifiers.SYNCHRONIZED;
		else if ("TRANSIENT".equals(name))
			return Modifiers.TRANSIENT;
		else if ("VOLATILE".equals(name))
			return Modifiers.VOLATILE;
		else
			return 0;
	}

	public static Expression modifiersToExpression(int modifiers) {
		Expression expression = null;
		Expression scope = NodeFacade.NameExpr("com.digiarea.jse.Modifiers");
		if (Modifiers.isPrivate(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "PRIVATE"));
		}
		if (Modifiers.isProtected(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "PROTECTED"));
		}
		if (Modifiers.isPublic(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "PUBLIC"));
		}
		if (Modifiers.isAbstract(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "ABSTRACT"));
		}
		if (Modifiers.isStatic(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "STATIC"));
		}
		if (Modifiers.isFinal(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "FINAL"));
		}
		if (Modifiers.isNative(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "NATIVE"));
		}
		if (Modifiers.isStrictfp(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "STRICTFP"));
		}
		if (Modifiers.isSynchronized(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "SYNCHRONIZE"));
		}
		if (Modifiers.isTransient(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "TRANSIENT"));
		}
		if (Modifiers.isVolatile(modifiers)) {
			expression = addModifiers(expression,
					NodeFacade.FieldAccessExpr(scope, null, "VOLATIVE"));
		}
		return expression;
	}

	private static final Expression addModifiers(Expression left,
			Expression right) {
		if (left == null) {
			return right;
		} else if (right == null) {
			return left;
		} else {
			return NodeFacade.BinaryExpr(left, right, BinaryOperator.binOr);
		}
	}

	/**
	 * Creates the getter declaration.
	 * 
	 * @param type
	 *            the type
	 * @param string
	 *            the string
	 * @return the method declaration
	 */
	public static MethodDeclaration createGetterDeclaration(Type type,
			String string, boolean isProperty) {
		boolean isBoolean = type.equals(NodeFacade.BOOLEAN_TYPE);
		String methodName = null;
		if (isBoolean) {
			if (string.length() > 2 && string.substring(0, 2).equals(IS)) {
				methodName = string;
			} else {
				methodName = IS + StringUtils.firstToUpper(string);
			}
		} else {
			methodName = GET + StringUtils.firstToUpper(string);
		}
		MethodDeclaration method = NodeFacade.MethodDeclaration(type,
				methodName);
		Expression result = NodeFacade.NameExpr(string);
		if (isProperty) {
			result = NodeFacade.MethodCallExpr(result, GET);
		}
		Statement stmt = NodeFacade.ReturnStmt(result);
		method.setBlock(NodeFacade.BlockStmt(Arrays.asList(stmt)));
		return method;
	}

	public static MethodDeclaration createGetterDeclaration(Type type,
			String string) {
		return createGetterDeclaration(type, string, false);
	}

	/**
	 * Creates the setter declaration.
	 * 
	 * @param type
	 *            the type
	 * @param string
	 *            the string
	 * @return the method declaration
	 */
	public static MethodDeclaration createSetterDeclaration(Type type,
			String string, boolean isProperty) {
		Parameter param = NodeFacade.Parameter(type, string);
		boolean isBoolean = type.equals(NodeFacade.BOOLEAN_TYPE);
		String methodName = null;
		if (isBoolean && string.length() > 2
				&& string.substring(0, 2).equals(IS)) {
			methodName = SET + StringUtils.firstToUpper(string.substring(2));
		} else {
			methodName = SET + StringUtils.firstToUpper(string);
		}
		MethodDeclaration method = NodeFacade.MethodDeclaration(
				Modifiers.PUBLIC, null, NodeFacade.VOID_TYPE, methodName,
				Arrays.asList(param), null, null, null, null, null);
		Expression expr = NodeFacade.NameExpr(string);
		if (isProperty) {
			String name = isList(type) ? ADD_ALL : SET;
			expr = NodeFacade.MethodCallExpr(NodeFacade.FieldAccessExpr(
					NodeFacade.ThisExpr(), null, string), null, name, Arrays
					.asList(expr));
		} else {
			expr = NodeFacade.AssignExpr(NodeFacade.FieldAccessExpr(
					NodeFacade.ThisExpr(), null, string), expr,
					AssignOperator.assign);
		}
		Statement stmt = NodeFacade.ExpressionStmt(expr);
		method.setBlock(NodeFacade.BlockStmt(Arrays.asList(stmt)));
		return method;
	}

	public static MethodDeclaration createSetterDeclaration(Type type,
			String string) {
		return createSetterDeclaration(type, string, false);
	}

	/**
	 * Checks if is list.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is list
	 */
	public static boolean isList(Type type) {
		if (type instanceof ReferenceType) {
			return isList(((ReferenceType) type).getType());
		} else if (type instanceof ClassOrInterfaceType) {
			String name = toNameExpr((ClassOrInterfaceType) type).toString();
			return name.equals("java.util.List")
					|| name.equals("java.util.ArrayList");
		}
		return false;
	}

	/**
	 * Gets the type from list.
	 * 
	 * @param type
	 *            the type
	 * @return the type from list
	 */
	public static Type getTypeFromList(Type type) {
		if (type instanceof ReferenceType) {
			return getTypeFromList(((ReferenceType) type).getType());
		} else if (type instanceof ClassOrInterfaceType) {
			ClassOrInterfaceType cType = (ClassOrInterfaceType) type;
			List<Type> types = cType.getTypeArgs();
			if (types != null && !types.isEmpty()) {
				return types.get(0);
			}
		}
		return null;
	}

}
