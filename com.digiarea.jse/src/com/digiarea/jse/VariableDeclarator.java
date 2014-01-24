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

import com.digiarea.jse.Node;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class VariableDeclarator.
 */
public final class VariableDeclarator extends Node {

    /** 
     * The id.
     */
    private VariableDeclaratorId id;

    /** 
     * The init.
     */
    private Expression init;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public VariableDeclaratorId getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(VariableDeclaratorId id) {
        this.id = id;
    }

    /**
     * Gets the inits the.
     *
     * @return the inits the
     */
    public Expression getInit() {
        return init;
    }

    /**
     * Sets the inits the.
     *
     * @param init the new inits the
     */
    public void setInit(Expression init) {
        this.init = init;
    }

    /**
     * Instantiates a new variable declarator.
     */
    VariableDeclarator() {
        super();
    }

    /**
     * Instantiates a new variable declarator.
     *
     * @param id the id
     * @param init the init
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    VariableDeclarator(VariableDeclaratorId id, Expression init, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.id = id;
        this.init = init;
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
