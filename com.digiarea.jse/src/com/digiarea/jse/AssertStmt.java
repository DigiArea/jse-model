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
 * The Class AssertStmt.
 */
public final class AssertStmt extends Statement {

    /** 
     * The check.
     */
    private Expression check;

    /** 
     * The message.
     */
    private Expression message;

    /**
     * Gets the check.
     *
     * @return the check
     */
    public Expression getCheck() {
        return check;
    }

    /**
     * Sets the check.
     *
     * @param check the new check
     */
    public void setCheck(Expression check) {
        this.check = check;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public Expression getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(Expression message) {
        this.message = message;
    }

    /**
     * Instantiates a new assert stmt.
     */
    AssertStmt() {
        super();
    }

    /**
     * Instantiates a new assert stmt.
     *
     * @param check the check
     * @param message the message
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    AssertStmt(Expression check, Expression message, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.check = check;
        this.message = message;
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
