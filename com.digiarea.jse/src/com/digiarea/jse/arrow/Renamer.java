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

import java.util.HashMap;
import java.util.Map;

import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.utils.NodeUtils;

/**
 * The Class Renamer.
 * 
 * <p>
 * Renames the TOP LEVEL classes only!.
 * </p>
 */
public class Renamer extends Quiver {

	/**
	 * The Class RenamerEngine.
	 */
	private static class RenamerEngine extends Identity {

		/** The renames. */
		private Map<String, String> renames = new HashMap<String, String>();

		/**
		 * Instantiates a new renamer.
		 * 
		 * @param renames
		 *            the renames
		 */
		public RenamerEngine(Map<String, String> renames) {
			super();
			if (renames != null) {
				this.renames = renames;
			}
		}

		/**
		 * Put.
		 * 
		 * @param key
		 *            the key
		 * @param value
		 *            the value
		 * @return the name expr
		 */
		public String put(String key, String value) {
			return renames.put(key, value);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.NameExpr,
		 * com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(NameExpr n, Context ctx) throws Exception {
			NameExpr img = (NameExpr) super.visit(n, ctx);
			String key = img.toString();
			if (renames.containsKey(key)) {
				return NodeFacade.NameExpr(renames.get(key));
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.QualifiedNameExpr
		 * , com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(QualifiedNameExpr n, Context ctx) throws Exception {
			QualifiedNameExpr img = (QualifiedNameExpr) super.visit(n, ctx);
			String key = img.toString();
			if (renames.containsKey(key)) {
				return NodeFacade.NameExpr(renames.get(key));
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.PackageDeclaration
		 * , com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(PackageDeclaration n, Context ctx) throws Exception {
			PackageDeclaration img = (PackageDeclaration) super.visit(n, ctx);
			CompilationUnit unit = ctx.getUnit();
			String key = NodeUtils.getQualifiedName(unit).toString();
			if (renames.containsKey(key)) {
				NameExpr nameExpr = NodeFacade.NameExpr(renames.get(key));
				if (nameExpr instanceof QualifiedNameExpr) {
					img.setName(((QualifiedNameExpr) nameExpr).getQualifier());
				}
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.
		 * ConstructorDeclaration, com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(ConstructorDeclaration n, Context ctx)
				throws Exception {
			ConstructorDeclaration img = (ConstructorDeclaration) super.visit(
					n, ctx);
			CompilationUnit unit = ctx.getUnit();
			String key = NodeUtils.getQualifiedName(unit).toString();
			if (renames.containsKey(key)) {
				img.setName(NodeFacade.NameExpr(renames.get(key)).getName());
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.ClassDeclaration
		 * , com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(ClassDeclaration n, Context ctx) throws Exception {
			CompilationUnit unit = ctx.getUnit();
			ClassDeclaration img = (ClassDeclaration) super.visit(n, ctx);
			String key = NodeUtils.getQualifiedName(unit).toString();
			if (renames.containsKey(key)) {
				img.setName(NodeFacade.NameExpr(renames.get(key)).getName());
			}
			return img;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.CompilationUnit
		 * , com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(CompilationUnit n, Context ctx) throws Exception {
			CompilationUnit img = (CompilationUnit) super.visit(n, ctx);
			// System.out.println("1: " + img.getName());
			img.setName(NodeUtils.toString((NameExpr) visit(
					NodeUtils.getQualifiedName(img), ctx)));
			// System.out.println("2: " + img.getName());
			return img;
		}
	}

	/** The engine. */
	private RenamerEngine engine;

	/**
	 * Instantiates a new renamer.
	 */
	public Renamer() {
		this(null);
	}

	/**
	 * Instantiates a new renamer.
	 * 
	 * @param renames
	 *            the renames
	 */
	public Renamer(Map<String, String> renames) {
		super();
		engine = new RenamerEngine(renames);
		addArrow(engine);
	}

	/**
	 * Put.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the name expr
	 */
	public String put(String key, String value) {
		return engine.put(key, value);
	}

}
