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
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ForStmt.
 */
public final class ForStmt extends Statement {

    /** 
     * The init.
     */
    private NodeList<Expression> init;

    /** 
     * The compare.
     */
    private Expression compare;

    /** 
     * The update.
     */
    private NodeList<Expression> update;

    /** 
     * The body.
     */
    private Statement body;

    /**
     * Gets the inits the.
     *
     * @return the inits the
     */
    public NodeList<Expression> getInit() {
        return init;
    }

    /**
     * Sets the inits the.
     *
     * @param init the new inits the
     */
    public void setInit(NodeList<Expression> init) {
        this.init = init;
    }

    /**
     * Gets the compare.
     *
     * @return the compare
     */
    public Expression getCompare() {
        return compare;
    }

    /**
     * Sets the compare.
     *
     * @param compare the new compare
     */
    public void setCompare(Expression compare) {
        this.compare = compare;
    }

    /**
     * Gets the update.
     *
     * @return the update
     */
    public NodeList<Expression> getUpdate() {
        return update;
    }

    /**
     * Sets the update.
     *
     * @param update the new update
     */
    public void setUpdate(NodeList<Expression> update) {
        this.update = update;
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
     * Instantiates a new for stmt.
     */
    ForStmt() {
        super();
    }

    /**
     * Instantiates a new for stmt.
     *
     * @param init the init
     * @param compare the compare
     * @param update the update
     * @param body the body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ForStmt(NodeList<Expression> init, Expression compare, NodeList<Expression> update, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.init = init;
        this.compare = compare;
        this.update = update;
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
