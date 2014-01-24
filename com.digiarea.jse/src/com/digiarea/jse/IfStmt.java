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
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class IfStmt.
 */
public final class IfStmt extends Statement {

    /** 
     * The condition.
     */
    private Expression condition;

    /** 
     * The then stmt.
     */
    private Statement thenStmt;

    /** 
     * The else stmt.
     */
    private Statement elseStmt;

    /**
     * Gets the condition.
     *
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the condition.
     *
     * @param condition the new condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    /**
     * Gets the then stmt.
     *
     * @return the then stmt
     */
    public Statement getThenStmt() {
        return thenStmt;
    }

    /**
     * Sets the then stmt.
     *
     * @param thenStmt the new then stmt
     */
    public void setThenStmt(Statement thenStmt) {
        this.thenStmt = thenStmt;
    }

    /**
     * Gets the else stmt.
     *
     * @return the else stmt
     */
    public Statement getElseStmt() {
        return elseStmt;
    }

    /**
     * Sets the else stmt.
     *
     * @param elseStmt the new else stmt
     */
    public void setElseStmt(Statement elseStmt) {
        this.elseStmt = elseStmt;
    }

    /**
     * Instantiates a new if stmt.
     */
    IfStmt() {
        super();
    }

    /**
     * Instantiates a new if stmt.
     *
     * @param condition the condition
     * @param thenStmt the then stmt
     * @param elseStmt the else stmt
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    IfStmt(Expression condition, Statement thenStmt, Statement elseStmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
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
