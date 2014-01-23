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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.EmptyTypeDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Project;
import com.digiarea.jse.arrow.Context;

/**
 * The Class ResolverVisitor.
 */
public class ResolverVisitor extends VoidVisitorAdapter<Context> {

	/**
	 * The Interface ResolvedData.
	 */
	public interface ResolvedData {

		/**
		 * Gets the types.
		 * 
		 * @return the types
		 */
		List<String> getTypes();

		/**
		 * Gets the.
		 * 
		 * @param pkgName
		 *            the pkg name
		 * @param baseType
		 *            the base type
		 * @param typeName
		 *            the type name
		 * @return the name expr
		 */
		NameExpr resolve(NameExpr pkgName, String baseType, String typeName);

		/**
		 * Unresolve.
		 * 
		 * @param pkgName
		 *            the pkg name
		 * @param baseType
		 *            the base type
		 * @param typeName
		 *            the type name
		 * @return the name expr
		 */
		NameExpr unresolve(NameExpr pkgName, String baseType, String typeName);

		/**
		 * Checks for.
		 * 
		 * @param pkgName
		 *            the pkg name
		 * @param typeName
		 *            the type name
		 * @return true, if successful
		 */
		public boolean has(NameExpr pkgName, NameExpr typeName);
	}

	/** The resolved. */
	private Map<NameExpr, List<NameExpr>> resolved = new HashMap<NameExpr, List<NameExpr>>();

	/** The upper types. */
	private List<String> upperTypes = new ArrayList<String>();

	/** The current package name. */
	private NameExpr currentPackageName = null;

	/** The current type name. */
	private NameExpr currentTypeName = null;

	/**
	 * Resolve.
	 * 
	 * @param project
	 *            the project
	 * @return the resolved data
	 * @throws Exception
	 *             the exception
	 */
	public ResolvedData resolve(Project project) throws Exception {
		this.visit(project, null);
		return new ResolvedData() {

			private Map<NameExpr, List<NameExpr>> result = Collections
					.unmodifiableMap(resolved);
			private List<String> types = Collections
					.unmodifiableList(upperTypes);

			@Override
			public List<String> getTypes() {
				return types;
			}

			@Override
			public boolean has(NameExpr pkgName, NameExpr typeName) {
				if (result.containsKey(pkgName)) {
					for (NameExpr nameExpr : result.get(pkgName)) {
						if (nameExpr.equals(typeName)) {
							return true;
						}
					}
				}
				return false;
			}

			@Override
			public NameExpr resolve(NameExpr pkgName, String baseType,
					String typeName) {
				return process(pkgName, baseType, typeName, true);
			}

			@Override
			public NameExpr unresolve(NameExpr pkgName, String baseType,
					String typeName) {
				return process(pkgName, baseType, typeName, false);
			}

			private NameExpr process(NameExpr pkgName, String baseType,
					String typeName, boolean isResolving) {
				if (pkgName != null && result.containsKey(pkgName)) {
					List<NameExpr> names = result.get(pkgName);
					NameExpr result = null;
					for (NameExpr nameExpr : names) {
						String name = nameExpr.getName();
						if (name != null && name.equals(typeName)) {
							if (NodeUtils.equalsByLast(nameExpr, baseType)) {
								return NodeUtils.merge(pkgName, nameExpr);
							} else if (isResolving) {
								result = nameExpr;
							}
						}
					}
					return result != null ? NodeUtils.merge(pkgName, result)
							: null;
				}
				return null;
			}

			@Override
			public String toString() {
				StringBuilder builder = new StringBuilder();
				for (Map.Entry<NameExpr, List<NameExpr>> entry : resolved
						.entrySet()) {
					builder.append(entry.getKey().toString());
					builder.append("\n");
					if (entry.getValue() != null) {
						for (NameExpr e : entry.getValue()) {
							builder.append("   ");
							builder.append(e);
							builder.append("\n");
						}
					}

				}
				return builder.toString();
			}

		};
	}

	@Override
	public void visit(AnnotationDeclaration n, Context ctx) throws Exception {
		NameExpr current = currentTypeName;
		put(n.getName());
		super.visit(n, ctx);
		currentTypeName = current;
	}

	@Override
	public void visit(ClassDeclaration n, Context ctx) throws Exception {
		NameExpr current = currentTypeName;
		put(n.getName());
		super.visit(n, ctx);
		currentTypeName = current;
	}

	@Override
	public void visit(InterfaceDeclaration n, Context ctx) throws Exception {
		NameExpr current = currentTypeName;
		put(n.getName());
		super.visit(n, ctx);
		currentTypeName = current;
	}

	@Override
	public void visit(EmptyTypeDeclaration n, Context ctx) throws Exception {
		NameExpr current = currentTypeName;
		put(n.getName());
		super.visit(n, ctx);
		currentTypeName = current;
	}

	@Override
	public void visit(EnumDeclaration n, Context ctx) throws Exception {
		NameExpr current = currentTypeName;
		put(n.getName());
		super.visit(n, ctx);
		currentTypeName = current;
	}

	@Override
	public void visit(CompilationUnit n, Context ctx) throws Exception {
		PackageDeclaration pkg = n.getPackageDeclaration();
		if (pkg != null) {
			currentPackageName = pkg.getName();
		} else {
			currentPackageName = null;
		}
		upperTypes.add(n.getName());
		super.visit(n, ctx);
		currentPackageName = null;
	}

	@Override
	public void visit(Project n, Context ctx) throws Exception {
		if (n.getCompilationUnits() != null) {
			for (CompilationUnit unit : n.getCompilationUnits()) {
				unit.accept(this, null);
			}
		}
	}

	/**
	 * Put.
	 * 
	 * @param name
	 *            the name
	 * @throws CloneNotSupportedException
	 *             the clone not supported exception
	 */
	private void put(String name) throws CloneNotSupportedException {
		if (currentTypeName != null) {
			currentTypeName = NodeFacade.QualifiedNameExpr(currentTypeName,
					name);
		} else {
			currentTypeName = NodeFacade.NameExpr(name);
		}
		if (resolved.containsKey(currentPackageName)) {
			resolved.get(currentPackageName).add(currentTypeName);
		} else {
			List<NameExpr> eq = new ArrayList<NameExpr>();
			eq.add(currentTypeName);
			resolved.put(currentPackageName, eq);
		}
	}

}
