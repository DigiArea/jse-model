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
import java.util.Iterator;
import java.util.List;

import com.digiarea.common.Arrow;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.FieldAccessExpr;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.MarkerAnnotationExpr;
import com.digiarea.jse.MethodCallExpr;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Project;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.SingleMemberAnnotationExpr;
import com.digiarea.jse.utils.LangUtils;
import com.digiarea.jse.utils.NodeUtils;
import com.digiarea.jse.visitor.ResolverVisitor;
import com.digiarea.jse.visitor.ResolverVisitor.ResolvedData;

/**
 * The Class Resolver.
 */
public class Resolver implements Arrow<Project, Project> {

	/**
	 * The Class ResolveToQualified.
	 */
	private class ResolveToQualified extends Identity {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .ClassOrInterfaceType, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(ClassOrInterfaceType n, Context ctx) throws Exception {
			ClassOrInterfaceType img = (ClassOrInterfaceType) super.visit(n,
					ctx);
			if (img.getScope() == null
					&& !(img.getName() instanceof QualifiedNameExpr)) {
				ClassOrInterfaceType type = NodeFacade
						.ClassOrInterfaceType(resolve(img.getName().getName(),
								ctx));
				type.setTypeArgs(img.getTypeArgs());
				return type;
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .FieldAccessExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(FieldAccessExpr n, Context ctx) throws Exception {
			FieldAccessExpr img = (FieldAccessExpr) super.visit(n, ctx);
			if (img.getScope() instanceof NameExpr) {
				if (!(img.getScope() instanceof QualifiedNameExpr)) {
					String name = ((NameExpr) img.getScope()).getName();
					img.setScope(resolve(name, ctx));
				}
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .MarkerAnnotationExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(MarkerAnnotationExpr n, Context ctx) throws Exception {
			MarkerAnnotationExpr img = (MarkerAnnotationExpr) super.visit(n,
					ctx);
			if (!(img.getName() instanceof QualifiedNameExpr)) {
				img.setName(resolve(img.getName().getName(), ctx));
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .MethodCallExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(MethodCallExpr n, Context ctx) throws Exception {
			MethodCallExpr img = (MethodCallExpr) super.visit(n, ctx);
			if (img.getScope() instanceof NameExpr) {
				if (!(img.getScope() instanceof QualifiedNameExpr)) {
					String name = ((NameExpr) img.getScope()).getName();
					img.setScope(resolve(name, ctx));
				}
			}
			return img;
		}

		@Override
		public Node visit(MethodDeclaration n, Context ctx) throws Exception {
			MethodDeclaration img = (MethodDeclaration) super.visit(n, ctx);
			if (img.getThrowsList() != null) {
				List<ClassOrInterfaceType> throwsList = new ArrayList<>();
				for (ClassOrInterfaceType type : img.getThrowsList()) {
					NameExpr name = type.getName();
					if (!(name instanceof QualifiedNameExpr)) {
						throwsList.add(NodeFacade.ClassOrInterfaceType(resolve(
								name.getName(), ctx)));
					} else {
						throwsList.add(NodeFacade.ClassOrInterfaceType(name));
					}
				}
				img.setThrowsList(NodeFacade.NodeList(throwsList));
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .NormalAnnotationExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(NormalAnnotationExpr n, Context ctx) throws Exception {
			NormalAnnotationExpr img = (NormalAnnotationExpr) super.visit(n,
					ctx);
			if (!(img.getName() instanceof QualifiedNameExpr)) {
				img.setName(resolve(img.getName().getName(), ctx));
			}
			return img;
		}

		@Override
		public Node visit(NameExpr n, Context ctx) throws Exception {
			NameExpr img = (NameExpr) super.visit(n, ctx);
			if (!ctx.isName()) {
				return resolve(img.getName(), ctx);
			}
			return img;
		}

		@Override
		public Node visit(QualifiedNameExpr n, Context ctx) throws Exception {
			QualifiedNameExpr img = (QualifiedNameExpr) super.visit(n, ctx);
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .SingleMemberAnnotationExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(SingleMemberAnnotationExpr n, Context ctx)
				throws Exception {
			SingleMemberAnnotationExpr img = (SingleMemberAnnotationExpr) super
					.visit(n, ctx);
			if (!(img.getName() instanceof QualifiedNameExpr)) {
				img.setName(resolve(img.getName().getName(), ctx));
			}
			return img;
		}

		@Override
		public Project arrow(Project iProject) throws Exception {
			Project img = NodeFacade.Project();
			Context ctx = new Context(img);
			ResolvedData resolve = new ResolverVisitor().resolve(iProject);
			ctx.setObject(resolve);
			if (iProject.getCompilationUnits() != null) {
				for (CompilationUnit unit : iProject.getCompilationUnits()) {
					CompilationUnit cu = (CompilationUnit) unit.accept(this,
							ctx);
					cu.setImports(null);
					NodeUtils.addCompilationUnit(img, cu);
				}
			}
			return img;
		}

		/**
		 * Resolve.
		 * 
		 * @param name
		 *            the name
		 * @param ctx
		 *            the ctx
		 * @return the name expr
		 */
		private NameExpr resolve(String name, Context ctx) {
			NameExpr result;
			NameExpr pkgName = null;
			//
			PackageDeclaration pkgDecl = ctx.getUnit().getPackageDeclaration();
			if (pkgDecl != null) {
				pkgName = pkgDecl.getName();
			}
			//
			String baseType = getSimpleUnitName(ctx);
			// this top level type
			if (baseType.equals(name)) {
				return NodeFacade.NameExpr(pkgName, baseType);
			}
			ResolvedData resolvedData = (ResolvedData) ctx.getObject();
			// this first inner level
			if (resolvedData.has(pkgName,
					NodeFacade.NameExpr(NodeFacade.NameExpr(baseType), name))) {
				return NodeFacade.NameExpr(pkgName, baseType, name);
			}
			List<ImportDeclaration> imports = ctx.getUnit().getImports();
			if (imports != null) {
				// find in imports
				for (Iterator<ImportDeclaration> iterator = imports.iterator(); iterator
						.hasNext();) {
					ImportDeclaration decl = iterator.next();
					if (name.equals(decl.getName().getName())) {
						return decl.getName();
					}
				}
				// find in first inner level of imported types
				for (Iterator<ImportDeclaration> iterator = imports.iterator(); iterator
						.hasNext();) {
					ImportDeclaration decl = iterator.next();
					NameExpr pkg = ((QualifiedNameExpr) decl.getName())
							.getQualifier();
					if (resolvedData.has(pkg,
							NodeFacade.NameExpr(NodeFacade.NameExpr(decl
									.getName().getName()), name))) {
						return NodeFacade.QualifiedNameExpr(decl.getName(),
								name);
					}
				}
			}
			// find in java.lang.*
			if (LangUtils.isSimpleJavaLang(name)) {
				return NodeFacade.QualifiedNameExpr(
						NodeFacade.QualifiedNameExpr(
								NodeFacade.NameExpr("java"), "lang"), name);
			}
			// find in the same package (see #50)
			result = resolvedData.resolve(pkgName, baseType, name);
			if (result != null) {
				return result;
			}
			return NodeFacade.NameExpr(name);
		}
	}

	/**
	 * The Class ResolveToSimple.
	 */
	private class ResolveToSimple extends Identity {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .ClassOrInterfaceType, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(ClassOrInterfaceType n, Context ctx) throws Exception {
			ClassOrInterfaceType img = (ClassOrInterfaceType) super.visit(n,
					ctx);
			if (img.getScope() == null
					&& img.getName() instanceof QualifiedNameExpr) {
				ctx.setName(true);
				img.setName(unresolve(img.getName(), ctx));
				ctx.setName(false);
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .FieldAccessExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(FieldAccessExpr n, Context ctx) throws Exception {
			FieldAccessExpr img = (FieldAccessExpr) super.visit(n, ctx);
			if (img.getScope() instanceof QualifiedNameExpr) {
				QualifiedNameExpr name = (QualifiedNameExpr) img.getScope();
				ctx.setName(true);
				img.setScope(unresolve(name, ctx));
				ctx.setName(false);
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .MarkerAnnotationExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(MarkerAnnotationExpr n, Context ctx) throws Exception {
			MarkerAnnotationExpr img = (MarkerAnnotationExpr) super.visit(n,
					ctx);
			if (img.getName() instanceof QualifiedNameExpr) {
				ctx.setName(true);
				img.setName(unresolve(img.getName(), ctx));
				ctx.setName(false);
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .MethodCallExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(MethodCallExpr n, Context ctx) throws Exception {
			MethodCallExpr img = (MethodCallExpr) super.visit(n, ctx);
			if (img.getScope() instanceof QualifiedNameExpr) {
				QualifiedNameExpr name = (QualifiedNameExpr) img.getScope();
				ctx.setName(true);
				img.setScope(unresolve(name, ctx));
				ctx.setName(false);
			}
			return img;
		}

		@Override
		public Node visit(MethodDeclaration n, Context ctx) throws Exception {
			MethodDeclaration img = (MethodDeclaration) super.visit(n, ctx);
			if (img.getThrowsList() != null) {
				List<ClassOrInterfaceType> throws_ = new ArrayList<>();
				ctx.setName(true);
				for (ClassOrInterfaceType type : img.getThrowsList()) {
					NameExpr name = type.getName();
					throws_.add(NodeFacade.ClassOrInterfaceType(unresolve(name,
							ctx)));
				}
				ctx.setName(false);
				img.setThrowsList(NodeFacade.NodeList(throws_));
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .NormalAnnotationExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(NormalAnnotationExpr n, Context ctx) throws Exception {
			NormalAnnotationExpr img = (NormalAnnotationExpr) super.visit(n,
					ctx);
			if (img.getName() instanceof QualifiedNameExpr) {
				ctx.setName(true);
				img.setName(unresolve(img.getName(), ctx));
				ctx.setName(false);
			}
			return img;
		}

		@Override
		public Node visit(QualifiedNameExpr n, Context ctx) throws Exception {
			QualifiedNameExpr img = (QualifiedNameExpr) super.visit(n, ctx);
			if (!ctx.isName() && !(ctx.getNode() instanceof QualifiedNameExpr)) {
				return unresolve(img, ctx);
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dagxp.lmm.arrow.Identity#visit(com.dagxp.lmm
		 * .SingleMemberAnnotationExpr, com.dagxp.lmm.visitor.Context)
		 */
		@Override
		public Node visit(SingleMemberAnnotationExpr n, Context ctx)
				throws Exception {
			SingleMemberAnnotationExpr img = (SingleMemberAnnotationExpr) super
					.visit(n, ctx);
			if (img.getName() instanceof QualifiedNameExpr) {
				ctx.setName(true);
				img.setName(unresolve(img.getName(), ctx));
				ctx.setName(false);
			}
			return img;
		}

		@Override
		public Project arrow(Project iProject) throws Exception {
			Project img = NodeFacade.Project();
			Context ctx = new Context(img);
			ctx.setObject(new ResolverVisitor().resolve(iProject));
			if (iProject.getCompilationUnits() != null) {
				for (CompilationUnit unit : iProject.getCompilationUnits()) {
					CompilationUnit cu = (CompilationUnit) unit.accept(this,
							ctx);
					if (cu.getImports() != null && cu.getImports().size() == 0) {
						cu.setImports(null);
					}
					NodeUtils.addCompilationUnit(img, cu);
				}
			}
			return img;
		}

		/**
		 * Unresolve.
		 * 
		 * @param expr
		 *            the expr
		 * @param ctx
		 *            the ctx
		 * @return the name expr
		 * @throws Exception
		 *             the exception
		 */
		private NameExpr unresolve(NameExpr expr, Context ctx) throws Exception {
			// java.lang.*
			if (LangUtils.isQualifiedJavaLang(expr)) {
				return NodeFacade.NameExpr(expr.getName());
			}
			// # 73 - the unit name equals the expression name, do not
			// unresolve!
			if (getSimpleUnitName(ctx).equals(expr.getName())) {
				// # 78 - the unit qualified name is exactly the same as the
				// expression name, unresolve to simple name
				if (ctx.getEnclosure().toString().equals(expr.toString())) {
					return NodeFacade.NameExpr(expr.getName());
				} else {
					return expr;
				}

			}
			List<ImportDeclaration> imports = ctx.getUnit().getImports();
			if (imports == null) {
				imports = new ArrayList<ImportDeclaration>();
				ctx.getUnit().setImports(NodeFacade.NodeList(imports));
			}
			for (ImportDeclaration importDeclaration : imports) {
				if (importDeclaration.getName().getName()
						.equals(expr.getName())) {
					if (expr.equals(importDeclaration.getName())) {
						// imports already has such an import
						// no need to add the same twice
						return NodeFacade.NameExpr(expr.getName());
					}
					// imports already has an import with the same name
					// so the qualified name expression can not be unresolved
					return expr;
				}
			}
			// find in the same class or package
			ResolvedData resolvedData = (ResolvedData) ctx.getObject();
			NameExpr pkgName = null;
			PackageDeclaration pkgDecl = ctx.getUnit().getPackageDeclaration();
			if (pkgDecl != null) {
				pkgName = ctx.getUnit().getPackageDeclaration().getName();
			}
			String baseType = getSimpleUnitName(ctx);
			// see #50
			NameExpr qName = resolvedData.unresolve(pkgName, baseType,
					expr.getName());
			if (!ctx.isName()) {
				imports.add(NodeFacade.ImportDeclaration(expr, true, false));
				return NodeFacade.NameExpr(expr.getName());
			}
			if (!expr.equals(qName)) {
				imports.add(NodeFacade.ImportDeclaration(expr, false, false));
				return NodeFacade.NameExpr(expr.getName());
			}
			return NodeFacade.NameExpr(expr.getName());
		}

	}

	private String getSimpleUnitName(Context ctx) {
		return NodeFacade.NameExpr(ctx.getUnit().getName()).getName();
	}

	/**
	 * The Enum NamesTo.
	 */
	public static enum NamesTo {

		/** The QUALIFIED. */
		QUALIFIED,
		/** The SIMPLE. */
		SIMPLE
	}

	/** The resolve to. */
	private Arrow<Project, Project> arrow;

	/**
	 * Instantiates a new resolver.
	 * 
	 * @param namesTo
	 *            the names to
	 */
	public Resolver(NamesTo namesTo) {
		super();
		if (namesTo == NamesTo.QUALIFIED) {
			arrow = new ResolveToQualified();
		} else {
			arrow = new ResolveToSimple();
		}
	}

	@Override
	public Project arrow(Project iProject) throws Exception {
		return arrow.arrow(iProject);
	}

}
