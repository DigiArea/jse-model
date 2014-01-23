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

import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.BooleanLiteralExpr;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.Expression;
import com.digiarea.jse.IntegerLiteralExpr;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.Node;
import com.digiarea.jse.NullLiteralExpr;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.ReturnStmt;
import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;

/**
 * The Class Abstractor.
 * 
 * <p>
 * Turns each abstract class from a project into regular adapter class.
 * </p>
 * <ul>
 * <li>Removes abstract modifier from an abstract class definition.</li>
 * <li>Removes abstract modifier from corresponding method definitions.</li>
 * <li>Adds default bodies to abstract methods.</li>
 * <li>Keeps all other entities unchanged.</li>
 * </ul>
 * 
 */
public class Abstractor extends Identity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dagxp.lmm.jse.arrow.Identity#visit(com.dagxp.lmm.jse.EnumDeclaration,
	 * com.dagxp.lmm.jse.visitor.Context)
	 */
	@Override
	public Node visit(EnumDeclaration n, Context ctx) throws Exception {
		ctx.setObject(new Boolean(false));
		EnumDeclaration img = (EnumDeclaration) super.visit(n, ctx);
		int modifiers = img.getModifiers();
		if (ModifierSet.isAbstract(modifiers)) {
			// remove abstract modifier
			modifiers = ModifierSet.removeModifier(modifiers,
					ModifierSet.ABSTRACT);
			img.setModifiers(modifiers);
		}
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dagxp.lmm.jse.arrow.Identity#visit(com.dagxp.lmm.jse.ClassDeclaration
	 * , com.dagxp.lmm.jse.visitor.Context)
	 */
	@Override
	public Node visit(ClassDeclaration n, Context ctx) throws Exception {
		ctx.setObject(new Boolean(false));
		ClassDeclaration img = (ClassDeclaration) super.visit(n, ctx);
		int modifiers = img.getModifiers();
		if (ModifierSet.isAbstract(modifiers)) {
			// remove abstract modifier
			modifiers = ModifierSet.removeModifier(modifiers,
					ModifierSet.ABSTRACT);
			img.setModifiers(modifiers);
		}
		return img;
	}

	@Override
	public Node visit(InterfaceDeclaration n, Context ctx) throws Exception {
		ctx.setObject(new Boolean(true));
		InterfaceDeclaration img = (InterfaceDeclaration) super.visit(n, ctx);
		int modifiers = img.getModifiers();
		if (ModifierSet.isAbstract(modifiers)) {
			// remove abstract modifier
			modifiers = ModifierSet.removeModifier(modifiers,
					ModifierSet.ABSTRACT);
			img.setModifiers(modifiers);
		}
		return img;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dagxp.lmm.jse.arrow.Identity#visit(com.dagxp.lmm.jse.MethodDeclaration
	 * , com.dagxp.lmm.jse.visitor.Context)
	 */
	@Override
	public Node visit(MethodDeclaration n, Context ctx) throws Exception {
		MethodDeclaration img = (MethodDeclaration) super.visit(n, ctx);
		int modifiers = img.getModifiers();
		if (ModifierSet.isAbstract(modifiers)) {
			// remove abstract modifier
			modifiers = ModifierSet.removeModifier(modifiers,
					ModifierSet.ABSTRACT);
			img.setModifiers(modifiers);
			Object obj = ctx.getObject();
			if (!(obj != null && obj instanceof Boolean && (Boolean) obj)) {
				// not an interface - add body
				List<Statement> stmts = new ArrayList<Statement>();
				img.setBody(new BlockStmt(stmts, null));
				Type type = img.getType();
				if (type != NodeUtils.VOID_TYPE) {
					Expression expr = null;
					if (type instanceof PrimitiveType) {
						if (type == NodeUtils.BOOLEAN_TYPE) {
							expr = new BooleanLiteralExpr(false, null);
						} else {
							expr = new IntegerLiteralExpr(0, null);
						}
					} else {
						expr = new NullLiteralExpr();
					}
					stmts.add(new ReturnStmt(expr, null));
				}
			}
		}
		return img;
	}

}
