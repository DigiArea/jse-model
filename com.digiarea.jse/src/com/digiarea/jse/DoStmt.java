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
 * The Class DoStmt.
 */
public final class DoStmt extends Statement {

    /** 
     * The body.
     */
    private Statement body;

    /** 
     * The condition.
     */
    private Expression condition;

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
     * Instantiates a new do stmt.
     */
    DoStmt() {
        super();
    }

    /**
     * Instantiates a new do stmt.
     *
     * @param body the body
     * @param condition the condition
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    DoStmt(Statement body, Expression condition, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.body = body;
        this.condition = condition;
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
