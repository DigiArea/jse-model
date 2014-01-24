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

import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.Project;
import com.digiarea.jse.utils.NodeUtils;

/**
 * The Class Cleaner.
 * 
 * <p>
 * Removes unused imports.
 * </p>
 * <p>
 * A project must be resolved by Resolver to NamesTo.QUALIFIED before used.
 * </p>
 * 
 */
public class Cleaner extends Identity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.inetgames.tools.tf.arrow.Identity#visit(biz.inetgames.tools.tf.NameExpr
	 * , biz.inetgames.tools.tf.visitor.Context)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Node visit(NameExpr n, Context ctx) throws Exception {
		List<ImportDeclaration> imports = ctx.getUnit().getImports();
		if (imports != null) {
			for (ImportDeclaration importDeclaration : imports) {
				if (importDeclaration.getName().getName().equals(n.getName())) {
					((List<ImportDeclaration>) ctx.getObject())
							.add(importDeclaration);
				}
			}
		}
		return super.visit(n, ctx);
	}

	@Override
	public Project arrow(Project iProject) throws Exception {
		Project img = NodeFacade.Project();
		Context ctx = new Context(img);
		if (iProject.getCompilationUnits() != null) {
			for (CompilationUnit unit : iProject.getCompilationUnits()) {
				List<ImportDeclaration> used = new ArrayList<ImportDeclaration>();
				ctx.setObject(used);
				CompilationUnit cu = (CompilationUnit) unit.accept(this, ctx);
				clear(cu, used);
				NodeUtils.addCompilationUnit(img, cu);
			}
		}
		return img;
	}

	/**
	 * Clear.
	 * 
	 * @param unit
	 *            the unit
	 * @param used
	 *            the used
	 */
	private void clear(CompilationUnit unit, List<ImportDeclaration> used) {
		List<ImportDeclaration> imports = unit.getImports();
		if (imports != null) {
			for (Iterator<ImportDeclaration> iterator = imports.iterator(); iterator
					.hasNext();) {
				ImportDeclaration decl = iterator.next();
				if (!used.contains(decl)) {
					iterator.remove();
				}
			}
		}
	}

}