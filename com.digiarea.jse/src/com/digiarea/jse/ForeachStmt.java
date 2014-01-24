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
package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ForeachStmt.
 */
public final class ForeachStmt extends Statement {

    /** 
     * The variable.
     */
    private VariableDeclarationExpr variable;

    /** 
     * The iterable.
     */
    private Expression iterable;

    /** 
     * The body.
     */
    private Statement body;

    /**
     * Gets the variable.
     *
     * @return the variable
     */
    public VariableDeclarationExpr getVariable() {
        return variable;
    }

    /**
     * Sets the variable.
     *
     * @param variable the new variable
     */
    public void setVariable(VariableDeclarationExpr variable) {
        this.variable = variable;
    }

    /**
     * Gets the iterable.
     *
     * @return the iterable
     */
    public Expression getIterable() {
        return iterable;
    }

    /**
     * Sets the iterable.
     *
     * @param iterable the new iterable
     */
    public void setIterable(Expression iterable) {
        this.iterable = iterable;
    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    public Statement getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param body the new body
     */
    public void setBody(Statement body) {
        this.body = body;
    }

    /**
     * Instantiates a new foreach stmt.
     */
    ForeachStmt() {
        super();
    }

    /**
     * Instantiates a new foreach stmt.
     *
     * @param variable the variable
     * @param iterable the iterable
     * @param body the body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ForeachStmt(VariableDeclarationExpr variable, Expression iterable, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.variable = variable;
        this.iterable = iterable;
        this.body = body;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
