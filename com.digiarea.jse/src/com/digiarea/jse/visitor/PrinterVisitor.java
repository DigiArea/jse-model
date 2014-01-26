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

import java.util.Iterator;

import com.digiarea.common.utils.SourcePrinter;
import com.digiarea.jse.*;
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.PrimitiveType.Primitive;
import com.digiarea.jse.UnaryExpr.UnaryOperator;

/**
 * The Class PrinterVisitor.
 */

public class PrinterVisitor implements VoidVisitor<SourcePrinter> {

	/**
	 * Instantiates a new printer visitor.
	 */
	public PrinterVisitor() {
		super();
	}

	/**
	 * Prints the members.
	 * 
	 * @param members
	 *            the members
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	protected void printMembers(NodeList<BodyDeclaration> members,
			SourcePrinter printer) throws Exception {
		for (BodyDeclaration member : members) {
			printer.printLn();
			member.accept(this, printer);
			printer.printLn();
		}
	}

	/**
	 * Prints the member annotations.
	 * 
	 * @param annotations
	 *            the annotations
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	public void printMemberAnnotations(NodeList<AnnotationExpr> annotations,
			SourcePrinter printer) throws Exception {
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				a.accept(this, printer);
				printer.printLn();
			}
		}
	}

	/**
	 * Prints the type annotations.
	 * 
	 * @param annotations
	 *            the annotations
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	public void printTypeAnnotations(NodeList<AnnotationExpr> annotations,
			SourcePrinter printer) throws Exception {
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				a.accept(this, printer);
				printer.print(" ");
			}
		}
	}

	/**
	 * Prints the annotations.
	 * 
	 * @param annotations
	 *            the annotations
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	protected void printAnnotations(NodeList<AnnotationExpr> annotations,
			SourcePrinter printer) throws Exception {
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				a.accept(this, printer);
				printer.print(" ");
			}
		}
	}

	/**
	 * Prints the type args.
	 * 
	 * @param args
	 *            the args
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	protected void printTypeArgs(NodeList<Type> args, SourcePrinter printer)
			throws Exception {
		if (args != null) {
			printer.print("<");
			for (Iterator<Type> i = args.iterator(); i.hasNext();) {
				Type t = i.next();
				t.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
			printer.print(">");
		}
	}

	/**
	 * Prints the type parameters.
	 * 
	 * @param args
	 *            the args
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	protected void printTypeParameters(NodeList<TypeParameter> args,
			SourcePrinter printer) throws Exception {
		if (args != null && args.size() > 0) {
			printer.print("<");
			for (Iterator<TypeParameter> i = args.iterator(); i.hasNext();) {
				TypeParameter t = i.next();
				t.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
			printer.print(">");
		}
	}

	/**
	 * Prints the arguments.
	 * 
	 * @param args
	 *            the args
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	protected void printArguments(NodeList<Expression> args,
			SourcePrinter printer) throws Exception {
		printer.print("(");
		if (args != null) {
			for (Iterator<Expression> i = args.iterator(); i.hasNext();) {
				Expression e = i.next();
				e.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
	}

	/**
	 * Prints the javadoc.
	 * 
	 * @param javadoc
	 *            the javadoc
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 */
	protected void printJavadoc(JavadocComment javadoc, SourcePrinter printer)
			throws Exception {
		if (javadoc != null) {
			javadoc.accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.CompilationUnit, java.lang.Object)
	 */
	@Override
	public void visit(CompilationUnit n, SourcePrinter printer)
			throws Exception {
		if (n.getPackageDeclaration() != null) {
			n.getPackageDeclaration().accept(this, printer);
		}
		if (n.getImports() != null) {
			for (ImportDeclaration i : n.getImports()) {
				i.accept(this, printer);
			}
			printer.printLn();
		}
		if (n.getTypes() != null) {
			for (Iterator<TypeDeclaration> i = n.getTypes().iterator(); i
					.hasNext();) {
				i.next().accept(this, printer);
				printer.printLn();
				if (i.hasNext()) {
					printer.printLn();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.PackageDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(PackageDeclaration n, SourcePrinter printer)
			throws Exception {
		printAnnotations(n.getAnnotations(), printer);
		printer.print("package ");
		n.getName().accept(this, printer);
		printer.printLn(";");
		printer.printLn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.NameExpr, java.lang.Object)
	 */
	@Override
	public void visit(NameExpr n, SourcePrinter printer) throws Exception {
		printer.print(n.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.QualifiedNameExpr
	 * , java.lang.Object)
	 */
	@Override
	public void visit(QualifiedNameExpr n, SourcePrinter printer)
			throws Exception {
		if (n.getQualifier() != null) {
			n.getQualifier().accept(this, printer);
			printer.print(".");
		}
		printer.print(n.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ImportDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(ImportDeclaration n, SourcePrinter printer)
			throws Exception {
		printer.print("import ");
		if (n.isStatic()) {
			printer.print("static ");
		}
		n.getName().accept(this, printer);
		if (n.isAsterisk()) {
			printer.print(".*");
		}
		printer.printLn(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ClassOrInterfaceDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(ClassDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		printer.print("class ");
		printer.print(n.getName());
		printTypeParameters(n.getTypeParameters(), printer);
		if (n.getExtendsType() != null) {
			printer.print(" extends ");
			n.getExtendsType().accept(this, printer);
		}
		if (n.getImplementsList() != null && n.getImplementsList().size() > 0) {
			printer.print(" implements ");
			for (Iterator<ClassOrInterfaceType> i = n.getImplementsList()
					.iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.printLn(" {");
		printer.indent();
		if (n.getMembers() != null) {
			printMembers(n.getMembers(), printer);
		}
		printer.unindent();
		printer.printLn();
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * InterfaceDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(InterfaceDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		printer.print("interface ");
		printer.print(n.getName());
		printTypeParameters(n.getTypeParameters(), printer);
		if (n.getExtendsList() != null && n.getExtendsList().size() > 0) {
			printer.print(" extends ");
			for (Iterator<ClassOrInterfaceType> i = n.getExtendsList()
					.iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.printLn(" {");
		printer.indent();
		if (n.getMembers() != null) {
			printMembers(n.getMembers(), printer);
		}
		printer.unindent();
		printer.printLn();
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.EmptyTypeDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EmptyTypeDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.JavadocComment, java.lang.Object)
	 */
	@Override
	public void visit(JavadocComment n, SourcePrinter printer) throws Exception {
		printer.print("/**");
		printer.print(n.getContent().replaceAll("\t", SourcePrinter.INDENT));
		printer.printLn("*/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ClassOrInterfaceType, java.lang.Object)
	 */
	@Override
	public void visit(ClassOrInterfaceType n, SourcePrinter printer)
			throws Exception {
		if (n.getScope() != null) {
			n.getScope().accept(this, printer);
			printer.print(".");
		}
		printTypeAnnotations(n.getAnnotations(), printer);
		n.getName().accept(this, printer);
		printTypeArgs(n.getTypeArgs(), printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.TypeParameter, java.lang.Object)
	 */
	@Override
	public void visit(TypeParameter n, SourcePrinter printer) throws Exception {
		printTypeAnnotations(n.getAnnotations(), printer);
		printer.print(n.getName());
		if (n.getTypeBound() != null && n.getTypeBound().size() > 0) {
			printer.print(" extends ");
			for (Iterator<ClassOrInterfaceType> i = n.getTypeBound().iterator(); i
					.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, printer);
				if (i.hasNext()) {
					printer.print(" & ");
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.PrimitiveType, java.lang.Object)
	 */
	@Override
	public void visit(PrimitiveType n, SourcePrinter printer) throws Exception {
		printTypeAnnotations(n.getAnnotations(), printer);
		n.getType().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ReferenceType, java.lang.Object)
	 */
	@Override
	public void visit(ReferenceType n, SourcePrinter printer) throws Exception {
		printTypeAnnotations(n.getAnnotations(), printer);
		n.getType().accept(this, printer);
		if (n.getSlots() != null) {
			for (ArraySlot slot : n.getSlots()) {
				slot.accept(this, printer);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.WildcardType, java.lang.Object)
	 */
	@Override
	public void visit(WildcardType n, SourcePrinter printer) throws Exception {
		printTypeAnnotations(n.getAnnotations(), printer);
		printer.print("?");
		if (n.getExtendsType() != null) {
			printer.print(" extends ");
			n.getExtendsType().accept(this, printer);
		}
		if (n.getSuperType() != null) {
			printer.print(" super ");
			n.getSuperType().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.FieldDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(FieldDeclaration n, SourcePrinter printer)
			throws Exception {
		// System.out.println(n.getType() + " " + n.getVariables().get(0));
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		n.getType().accept(this, printer);

		printer.print(" ");
		for (Iterator<VariableDeclarator> i = n.getVariables().iterator(); i
				.hasNext();) {
			VariableDeclarator var = i.next();
			var.accept(this, printer);
			if (i.hasNext()) {
				printer.print(", ");
			}
		}

		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.VariableDeclarator, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclarator n, SourcePrinter printer)
			throws Exception {
		n.getId().accept(this, printer);
		if (n.getInit() != null) {
			printer.print(" = ");
			n.getInit().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.VariableDeclaratorId, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclaratorId n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getName());
		if (n.getSlots() != null) {
			for (ArraySlot slot : n.getSlots()) {
				slot.accept(this, printer);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ArrayInitializerExpr, java.lang.Object)
	 */
	@Override
	public void visit(ArrayInitializerExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("{");
		if (n.getValues() != null && n.getValues().iterator() != null) {
			printer.print(" ");
			for (Iterator<Expression> i = n.getValues().iterator(); i.hasNext();) {
				Expression expr = i.next();
				expr.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
			printer.print(" ");
		}
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.VoidType, java.lang.Object)
	 */
	@Override
	public void visit(VoidType n, SourcePrinter printer) throws Exception {
		printTypeAnnotations(n.getAnnotations(), printer);
		printer.print("void");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ArrayAccessExpr, java.lang.Object)
	 */
	@Override
	public void visit(ArrayAccessExpr n, SourcePrinter printer)
			throws Exception {
		n.getName().accept(this, printer);
		printer.print("[");
		n.getIndex().accept(this, printer);
		printer.print("]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ArrayCreationExpr, java.lang.Object)
	 */
	@Override
	public void visit(ArrayCreationExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("new ");
		n.getType().accept(this, printer);
		if (n.getSlots() != null) {
			for (ArraySlot slot : n.getSlots()) {
				slot.accept(this, printer);
			}
		}
		if (n.getInitializer() != null) {
			printer.print(" ");
			n.getInitializer().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.AssignExpr, java.lang.Object)
	 */
	@Override
	public void visit(AssignExpr n, SourcePrinter printer) throws Exception {
		n.getTarget().accept(this, printer);
		printer.print(" ");
		n.getOperator().accept(this, printer);
		printer.print(" ");
		n.getValue().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.BinaryExpr, java.lang.Object)
	 */
	@Override
	public void visit(BinaryExpr n, SourcePrinter printer) throws Exception {
		n.getLeft().accept(this, printer);
		printer.print(" ");
		n.getOperator().accept(this, printer);
		printer.print(" ");
		n.getRight().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.CastExpr, java.lang.Object)
	 */
	@Override
	public void visit(CastExpr n, SourcePrinter printer) throws Exception {
		printer.print("(");
		if (n.getTypes() != null) {
			for (Iterator<Type> i = n.getTypes().iterator(); i.hasNext();) {
				Type t = i.next();
				t.accept(this, printer);
				if (i.hasNext()) {
					printer.print(" & ");
				}
			}
		}
		printer.print(") ");
		n.getExpression().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ClassExpr, java.lang.Object)
	 */
	@Override
	public void visit(ClassExpr n, SourcePrinter printer) throws Exception {
		n.getType().accept(this, printer);
		printer.print(".class");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ConditionalExpr, java.lang.Object)
	 */
	@Override
	public void visit(ConditionalExpr n, SourcePrinter printer)
			throws Exception {
		n.getCondition().accept(this, printer);
		printer.print(" ? ");
		n.getThenExpression().accept(this, printer);
		printer.print(" : ");
		n.getElseExpression().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.EnclosedExpr, java.lang.Object)
	 */
	@Override
	public void visit(EnclosedExpr n, SourcePrinter printer) throws Exception {
		printer.print("(");
		n.getInner().accept(this, printer);
		printer.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.FieldAccessExpr, java.lang.Object)
	 */
	@Override
	public void visit(FieldAccessExpr n, SourcePrinter printer)
			throws Exception {
		n.getScope().accept(this, printer);
		printer.print(".");
		printTypeArgs(n.getTypeArgs(), printer);
		printer.print(n.getField());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.InstanceOfExpr, java.lang.Object)
	 */
	@Override
	public void visit(InstanceOfExpr n, SourcePrinter printer) throws Exception {
		n.getExpression().accept(this, printer);
		printer.print(" instanceof ");
		n.getType().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.CharLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(CharLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.DoubleLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(DoubleLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.IntegerLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(IntegerLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.LongLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(LongLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.StringLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(StringLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("\"");
		if (n.getValue() != null) {
			printer.print(n.getValue());
		}
		printer.print("\"");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.BooleanLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(BooleanLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print(String.valueOf(n.isValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.NullLiteralExpr, java.lang.Object)
	 */
	@Override
	public void visit(NullLiteralExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("null");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ThisExpr, java.lang.Object)
	 */
	@Override
	public void visit(ThisExpr n, SourcePrinter printer) throws Exception {
		if (n.getClassExpression() != null) {
			n.getClassExpression().accept(this, printer);
			printer.print(".");
		}
		printer.print("this");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.SuperExpr, java.lang.Object)
	 */
	@Override
	public void visit(SuperExpr n, SourcePrinter printer) throws Exception {
		if (n.getClassExpression() != null) {
			n.getClassExpression().accept(this, printer);
			printer.print(".");
		}
		printer.print("super");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.MethodCallExpr, java.lang.Object)
	 */
	@Override
	public void visit(MethodCallExpr n, SourcePrinter printer) throws Exception {
		if (n.getScope() != null) {
			n.getScope().accept(this, printer);
			printer.print(".");
		}
		printTypeArgs(n.getTypeArgs(), printer);
		printer.print(n.getName());
		printArguments(n.getArgs(), printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ObjectCreationExpr, java.lang.Object)
	 */
	@Override
	public void visit(ObjectCreationExpr n, SourcePrinter printer)
			throws Exception {
		if (n.getScope() != null) {
			n.getScope().accept(this, printer);
			printer.print(".");
		}
		printer.print("new ");
		printTypeAnnotations(n.getAnnotations(), printer);
		printTypeArgs(n.getTypeArgs(), printer);
		n.getType().accept(this, printer);
		printArguments(n.getArgs(), printer);
		if (n.getAnonymousClassBody() != null) {
			printer.printLn(" {");
			printer.indent();
			printMembers(n.getAnonymousClassBody(), printer);
			printer.unindent();
			printer.print("}");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.UnaryExpr, java.lang.Object)
	 */
	@Override
	public void visit(UnaryExpr n, SourcePrinter printer) throws Exception {
		switch (n.getOperator()) {
		case positive:
			printer.print(" +");
			break;
		case negative:
			printer.print(" -");
			break;
		case inverse:
			printer.print(" ~");
			break;
		case not:
			printer.print(" !");
			break;
		case preIncrement:
			printer.print(" ++");
			break;
		case preDecrement:
			printer.print(" --");
			break;
		default:
		}

		n.getExpression().accept(this, printer);

		switch (n.getOperator()) {
		case posIncrement:
			printer.print("++ ");
			break;
		case posDecrement:
			printer.print("-- ");
			break;
		default:
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ConstructorDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(ConstructorDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		printTypeParameters(n.getTypeParameters(), printer);
		if (n.getTypeParameters() != null) {
			printer.print(" ");
		}
		printer.print(n.getName());
		printer.print("(");
		boolean reciever = false;
		if (n.getReceiverType() != null) {
			reciever = true;
			n.getReceiverType().accept(this, printer);
			printer.print(" ");
			if (n.getReceiverQualifier() != null) {
				n.getReceiverQualifier().accept(this, printer);
				printer.print(".");
			}
			printer.print("this");
		}
		if (n.getParameters() != null) {
			if (reciever) {
				printer.print(", ");
			}
			for (Iterator<Parameter> i = n.getParameters().iterator(); i
					.hasNext();) {
				Parameter p = i.next();
				p.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
		if (n.getThrowsList() != null && n.getThrowsList().size() > 0) {
			printer.print(" throws ");
			for (Iterator<ClassOrInterfaceType> i = n.getThrowsList()
					.iterator(); i.hasNext();) {
				ClassOrInterfaceType name = i.next();
				name.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(" ");
		n.getBlock().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.MethodDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(MethodDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		printTypeParameters(n.getTypeParameters(), printer);
		if (n.getTypeParameters() != null) {
			printer.print(" ");
		}
		n.getType().accept(this, printer);
		printer.print(" ");
		printer.print(n.getName());
		printer.print("(");
		boolean reciever = false;
		if (n.getReceiverType() != null) {
			reciever = true;
			n.getReceiverType().accept(this, printer);
			printer.print(" ");
			if (n.getReceiverQualifier() != null) {
				n.getReceiverQualifier().accept(this, printer);
				printer.print(".");
			}
			printer.print("this");
		}
		if (n.getParameters() != null) {
			if (reciever) {
				printer.print(", ");
			}
			for (Iterator<Parameter> i = n.getParameters().iterator(); i
					.hasNext();) {
				Parameter p = i.next();
				p.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
		if (n.getSlots() != null) {
			for (ArraySlot slot : n.getSlots()) {
				slot.accept(this, printer);
			}
		}
		if (n.getThrowsList() != null && n.getThrowsList().size() > 0) {
			printer.print(" throws ");
			for (Iterator<ClassOrInterfaceType> i = n.getThrowsList()
					.iterator(); i.hasNext();) {
				ClassOrInterfaceType name = i.next();
				name.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		if (n.getBlock() == null) {
			printer.print(";");
		} else {
			printer.print(" ");
			n.getBlock().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.Parameter, java.lang.Object)
	 */
	@Override
	public void visit(Parameter n, SourcePrinter printer) throws Exception {
		printAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			if (n.getModifiers() != null) {
				n.getModifiers().accept(this, printer);
			}
		}
		if (n.getType() != null) {
			n.getType().accept(this, printer);
			if (n.getEllipsis() != null) {
				n.getEllipsis().accept(this, printer);
			}
			printer.print(" ");
		}
		n.getId().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ExplicitConstructorInvocationStmt, java.lang.Object)
	 */
	@Override
	public void visit(ExplicitConstructorInvocationStmt n, SourcePrinter printer)
			throws Exception {
		if (n.isThis()) {
			printTypeArgs(n.getTypeArgs(), printer);
			printer.print("this");
		} else {
			if (n.getExpression() != null) {
				n.getExpression().accept(this, printer);
				printer.print(".");
			}
			printTypeArgs(n.getTypeArgs(), printer);
			printer.print("super");
		}
		printArguments(n.getArgs(), printer);
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.VariableDeclarationExpr, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclarationExpr n, SourcePrinter printer)
			throws Exception {
		printAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}

		n.getType().accept(this, printer);
		printer.print(" ");

		for (Iterator<VariableDeclarator> i = n.getVars().iterator(); i
				.hasNext();) {
			VariableDeclarator v = i.next();
			v.accept(this, printer);
			if (i.hasNext()) {
				printer.print(", ");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.TypeDeclarationStmt, java.lang.Object)
	 */
	@Override
	public void visit(TypeDeclarationStmt n, SourcePrinter printer)
			throws Exception {
		n.getTypeDeclaration().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.AssertStmt, java.lang.Object)
	 */
	@Override
	public void visit(AssertStmt n, SourcePrinter printer) throws Exception {
		printer.print("assert ");
		n.getCheck().accept(this, printer);
		if (n.getMessage() != null) {
			printer.print(" : ");
			n.getMessage().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.BlockStmt, java.lang.Object)
	 */
	@Override
	public void visit(BlockStmt n, SourcePrinter printer) throws Exception {
		printer.printLn("{");
		if (n.getStatements() != null) {
			printer.indent();
			for (Statement s : n.getStatements()) {
				s.accept(this, printer);
				printer.printLn();
			}
			printer.unindent();
		}
		printer.print("}");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.LabeledStmt, java.lang.Object)
	 */
	@Override
	public void visit(LabeledStmt n, SourcePrinter printer) throws Exception {
		printer.print(n.getLabel());
		printer.print(": ");
		n.getStmt().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.EmptyStmt, java.lang.Object)
	 */
	@Override
	public void visit(EmptyStmt n, SourcePrinter printer) throws Exception {
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ExpressionStmt, java.lang.Object)
	 */
	@Override
	public void visit(ExpressionStmt n, SourcePrinter printer) throws Exception {
		n.getExpression().accept(this, printer);
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.SwitchStmt, java.lang.Object)
	 */
	@Override
	public void visit(SwitchStmt n, SourcePrinter printer) throws Exception {
		printer.print("switch(");
		n.getSelector().accept(this, printer);
		printer.printLn(") {");
		if (n.getEntries() != null) {
			printer.indent();
			for (SwitchEntryStmt e : n.getEntries()) {
				e.accept(this, printer);
			}
			printer.unindent();
		}
		printer.print("}");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.SwitchEntryStmt, java.lang.Object)
	 */
	@Override
	public void visit(SwitchEntryStmt n, SourcePrinter printer)
			throws Exception {
		if (n.getLabel() != null) {
			printer.print("case ");
			n.getLabel().accept(this, printer);
			printer.print(":");
		} else {
			printer.print("default:");
		}
		printer.printLn();
		printer.indent();
		if (n.getStmts() != null) {
			for (Statement s : n.getStmts()) {
				s.accept(this, printer);
				printer.printLn();
			}
		}
		printer.unindent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.BreakStmt, java.lang.Object)
	 */
	@Override
	public void visit(BreakStmt n, SourcePrinter printer) throws Exception {
		printer.print("break");
		if (n.getId() != null) {
			printer.print(" ");
			printer.print(n.getId());
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ReturnStmt, java.lang.Object)
	 */
	@Override
	public void visit(ReturnStmt n, SourcePrinter printer) throws Exception {
		printer.print("return");
		if (n.getExpression() != null) {
			printer.print(" ");
			n.getExpression().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.EnumDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EnumDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		printer.print("enum ");
		printer.print(n.getName());
		if (n.getImplementsList() != null && n.getImplementsList().size() > 0) {
			printer.print(" implements ");
			for (Iterator<ClassOrInterfaceType> i = n.getImplementsList()
					.iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}

		printer.printLn(" {");
		printer.indent();
		if (n.getEntries() != null && n.getEntries().size() > 0) {
			printer.printLn();
			for (Iterator<EnumConstantDeclaration> i = n.getEntries()
					.iterator(); i.hasNext();) {
				EnumConstantDeclaration e = i.next();
				e.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
					printer.printLn();
				}
			}
		}
		if (n.getMembers() != null) {
			printer.printLn(";");
			printMembers(n.getMembers(), printer);
		} else {
			if (n.getEntries() != null) {
				printer.printLn();
			}
		}
		printer.unindent();
		printer.printLn();
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.EnumConstantDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EnumConstantDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		printer.print(n.getName());
		if (n.getArgs() != null && n.getArgs().size() > 0) {
			printArguments(n.getArgs(), printer);
		}
		if (n.getClassBody() != null) {
			printer.printLn(" {");
			printer.indent();
			printMembers(n.getClassBody(), printer);
			printer.unindent();
			printer.printLn("}");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.EmptyMemberDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(EmptyMemberDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.InitializerDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(InitializerDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		if (n.isStatic()) {
			printer.print("static ");
		}
		n.getBlock().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.IfStmt, java.lang.Object)
	 */
	@Override
	public void visit(IfStmt n, SourcePrinter printer) throws Exception {
		printer.print("if (");
		n.getCondition().accept(this, printer);
		printer.print(") ");
		n.getThenStmt().accept(this, printer);
		if (n.getElseStmt() != null) {
			printer.print(" else ");
			n.getElseStmt().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.WhileStmt, java.lang.Object)
	 */
	@Override
	public void visit(WhileStmt n, SourcePrinter printer) throws Exception {
		printer.print("while (");
		n.getCondition().accept(this, printer);
		printer.print(") ");
		n.getBody().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ContinueStmt, java.lang.Object)
	 */
	@Override
	public void visit(ContinueStmt n, SourcePrinter printer) throws Exception {
		printer.print("continue");
		if (n.getId() != null) {
			printer.print(" ");
			printer.print(n.getId());
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.DoStmt, java.lang.Object)
	 */
	@Override
	public void visit(DoStmt n, SourcePrinter printer) throws Exception {
		printer.print("do ");
		n.getBody().accept(this, printer);
		printer.print(" while (");
		n.getCondition().accept(this, printer);
		printer.print(");");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ForeachStmt, java.lang.Object)
	 */
	@Override
	public void visit(ForeachStmt n, SourcePrinter printer) throws Exception {
		printer.print("for (");
		n.getVariable().accept(this, printer);
		printer.print(" : ");
		n.getIterable().accept(this, printer);
		printer.print(") ");
		n.getBody().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ForStmt, java.lang.Object)
	 */
	@Override
	public void visit(ForStmt n, SourcePrinter printer) throws Exception {
		printer.print("for (");
		if (n.getInit() != null) {
			for (Iterator<Expression> i = n.getInit().iterator(); i.hasNext();) {
				Expression e = i.next();
				e.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print("; ");
		if (n.getCompare() != null) {
			n.getCompare().accept(this, printer);
		}
		printer.print("; ");
		if (n.getUpdate() != null) {
			for (Iterator<Expression> i = n.getUpdate().iterator(); i.hasNext();) {
				Expression e = i.next();
				e.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(") ");
		n.getBody().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.ThrowStmt, java.lang.Object)
	 */
	@Override
	public void visit(ThrowStmt n, SourcePrinter printer) throws Exception {
		printer.print("throw ");
		n.getExpression().accept(this, printer);
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.SynchronizedStmt, java.lang.Object)
	 */
	@Override
	public void visit(SynchronizedStmt n, SourcePrinter printer)
			throws Exception {
		printer.print("synchronized (");
		n.getExpression().accept(this, printer);
		printer.print(") ");
		n.getBlock().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.TryStmt, java.lang.Object)
	 */
	@Override
	public void visit(TryStmt n, SourcePrinter printer) throws Exception {
		printer.print("try ");
		if (n.getResources() != null) {
			printer.print("(");
			for (Iterator<VariableDeclarationExpr> i = n.getResources()
					.iterator(); i.hasNext();) {
				VariableDeclarationExpr e = i.next();
				e.accept(this, printer);
				if (i.hasNext()) {
					printer.printLn(";");
				}
			}
			printer.print(")");
		}
		n.getTryBlock().accept(this, printer);
		if (n.getCatchClauses() != null) {
			for (CatchClause c : n.getCatchClauses()) {
				c.accept(this, printer);
			}
		}
		if (n.getFinallyBlock() != null) {
			printer.print(" finally ");
			n.getFinallyBlock().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.CatchClause, java.lang.Object)
	 */
	@Override
	public void visit(CatchClause n, SourcePrinter printer) throws Exception {
		printer.print(" catch (");
		if (n.isFinal()) {
			printer.print("final ");
		}
		for (Iterator<Type> i = n.getTypes().iterator(); i.hasNext();) {
			Type e = i.next();
			e.accept(this, printer);
			if (i.hasNext()) {
				printer.print(" | ");
			}
		}
		printer.print(" ");
		printer.print(n.getName());
		printer.print(") ");
		n.getCatchBlock().accept(this, printer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.AnnotationDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(AnnotationDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		printer.print("@interface ");
		printer.print(n.getName());
		printer.printLn(" {");
		printer.indent();
		if (n.getMembers() != null) {
			printMembers(n.getMembers(), printer);
		}
		printer.unindent();
		printer.printLn();
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.AnnotationMemberDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(AnnotationMemberDeclaration n, SourcePrinter printer)
			throws Exception {
		printJavadoc(n.getJavaDoc(), printer);
		printMemberAnnotations(n.getAnnotations(), printer);
		if (n.getModifiers() != null) {
			n.getModifiers().accept(this, printer);
		}
		n.getType().accept(this, printer);
		printer.print(" ");
		printer.print(n.getName());
		printer.print("()");
		if (n.getDefaultValue() != null) {
			printer.print(" default ");
			n.getDefaultValue().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.MarkerAnnotationExpr, java.lang.Object)
	 */
	@Override
	public void visit(MarkerAnnotationExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("@");
		n.getName().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.SingleMemberAnnotationExpr, java.lang.Object)
	 */
	@Override
	public void visit(SingleMemberAnnotationExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("@");
		n.getName().accept(this, printer);
		printer.print("(");
		n.getMemberValue().accept(this, printer);
		printer.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.NormalAnnotationExpr, java.lang.Object)
	 */
	@Override
	public void visit(NormalAnnotationExpr n, SourcePrinter printer)
			throws Exception {
		printer.print("@");
		n.getName().accept(this, printer);
		printer.print("(");
		if (n.getPairs() != null) {
			for (Iterator<MemberValuePair> i = n.getPairs().iterator(); i
					.hasNext();) {
				MemberValuePair m = i.next();
				m.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.MemberValuePair, java.lang.Object)
	 */
	@Override
	public void visit(MemberValuePair n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getName());
		printer.print(" = ");
		n.getValue().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.LineComment, java.lang.Object)
	 */
	@Override
	public void visit(LineComment n, SourcePrinter printer) throws Exception {
		printer.print("//");
		printer.printLn(n.getContent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.BlockComment, java.lang.Object)
	 */
	@Override
	public void visit(BlockComment n, SourcePrinter printer) throws Exception {
		printer.print("/*");
		printer.print(n.getContent().replaceAll("\t", SourcePrinter.INDENT));
		printer.printLn("*/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.ast.visitor.VoidVisitor#visit(biz.inetgames.tools
	 * .ast.Project, java.lang.Object)
	 */
	@Override
	public void visit(Project n, SourcePrinter printer) throws Exception {
		NodeList<CompilationUnit> units = n.getCompilationUnits();
		if (units != null) {
			for (CompilationUnit unit : units) {
				printer.printLn();
				unit.accept(this, printer);
			}
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
	public void visit(UnaryOperator n, SourcePrinter printer) throws Exception {
		printer.print(n.getString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.BinaryExpr
	 * .BinaryOperator, java.lang.Object)
	 */
	@Override
	public void visit(BinaryOperator n, SourcePrinter printer) throws Exception {
		printer.print(n.getString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.Modifiers,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Modifiers n, SourcePrinter printer) throws Exception {
		if (n.isPrivate()) {
			printer.print("private ");
		}
		if (n.isProtected()) {
			printer.print("protected ");
		}
		if (n.isPublic()) {
			printer.print("public ");
		}
		if (n.isAbstract()) {
			printer.print("abstract ");
		}
		if (n.isStatic()) {
			printer.print("static ");
		}
		if (n.isFinal()) {
			printer.print("final ");
		}
		if (n.isNative()) {
			printer.print("native ");
		}
		if (n.isStrictfp()) {
			printer.print("strictfp ");
		}
		if (n.isSynchronized()) {
			printer.print("synchronized ");
		}
		if (n.isTransient()) {
			printer.print("transient ");
		}
		if (n.isVolatile()) {
			printer.print("volatile ");
		}
		if (n.isDefault()) {
			printer.print("default ");
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
	public void visit(AssignOperator n, SourcePrinter printer) throws Exception {
		printer.print(n.getString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.PrimitiveType
	 * .Primitive, java.lang.Object)
	 */
	@Override
	public void visit(Primitive n, SourcePrinter printer) throws Exception {
		printer.print(n.getString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LambdaBlock,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LambdaBlock n, SourcePrinter printer) throws Exception {
		printer.print("(");
		if (n.getParameters() != null) {
			for (Iterator<Parameter> i = n.getParameters().iterator(); i
					.hasNext();) {
				Parameter p = i.next();
				p.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
		printer.print(" -> ");
		n.getBlockStmt().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.LambdaExpr,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LambdaExpr n, SourcePrinter printer) throws Exception {
		printer.print("(");
		if (n.getParameters() != null) {
			for (Iterator<Parameter> i = n.getParameters().iterator(); i
					.hasNext();) {
				Parameter p = i.next();
				p.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
		printer.print(" -> ");
		n.getExpression().accept(this, printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * TypeMethodReference, java.lang.Object)
	 */
	@Override
	public void visit(TypeMethodReference n, SourcePrinter printer)
			throws Exception {
		n.getType().accept(this, printer);
		printer.print("::");
		if (n.getTypeArgs() != null) {
			printTypeArgs(n.getTypeArgs(), printer);
		}
		printer.print(n.getMethodName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * ExpressionMethodReference, java.lang.Object)
	 */
	@Override
	public void visit(ExpressionMethodReference n, SourcePrinter printer)
			throws Exception {
		n.getExpression().accept(this, printer);
		printer.print("::");
		if (n.getTypeArgs() != null) {
			printTypeArgs(n.getTypeArgs(), printer);
		}
		printer.print(n.getMethodName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.CreationReference
	 * , java.lang.Object)
	 */
	@Override
	public void visit(CreationReference n, SourcePrinter printer)
			throws Exception {
		if (n.getType() != null) {
			n.getType().accept(this, printer);
		}
		printer.print("::");
		if (n.getTypeArgs() != null) {
			printTypeArgs(n.getTypeArgs(), printer);
		}
		printer.print("new");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.
	 * SuperMethodReference, java.lang.Object)
	 */
	@Override
	public void visit(SuperMethodReference n, SourcePrinter printer)
			throws Exception {
		if (n.getQualifier() != null) {
			n.getQualifier().accept(this, printer);
			printer.print(".");
		}
		printer.print("super");
		printer.print("::");
		if (n.getTypeArgs() != null) {
			printTypeArgs(n.getTypeArgs(), printer);
		}
		printer.print(n.getMethodName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.ArraySlot,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ArraySlot n, SourcePrinter printer) throws Exception {
		if (n.getAnnotations() != null) {
			printer.print(" ");
			printTypeAnnotations(n.getAnnotations(), printer);
		}
		printer.print("[");
		if (n.getExpression() != null) {
			n.getExpression().accept(this, printer);
		}
		printer.print("]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.Ellipsis,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Ellipsis n, SourcePrinter printer) throws Exception {
		if (n.getAnnotations() != null) {
			printer.print(" ");
			printTypeAnnotations(n.getAnnotations(), printer);
		}
		printer.print("...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digiarea.jse.visitor.VoidVisitor#visit(com.digiarea.jse.NodeList,
	 * java.lang.Object)
	 */
	@Override
	public <E extends Node> void visit(NodeList<E> n, SourcePrinter printer)
			throws Exception {
		// Make literal presentation for this node
		throw new Exception("No literal presentation for this node");
	}

}
