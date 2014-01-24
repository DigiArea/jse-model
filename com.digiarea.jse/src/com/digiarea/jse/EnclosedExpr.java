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

import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class EnclosedExpr.
 */
public final class EnclosedExpr extends Expression {

    /** 
     * The inner.
     */
    private Expression inner;

    /**
     * Gets the inner.
     *
     * @return the inner
     */
    public Expression getInner() {
        return inner;
    }

    /**
     * Sets the inner.
     *
     * @param inner the new inner
     */
    public void setInner(Expression inner) {
        this.inner = inner;
    }

    /**
     * Instantiates a new enclosed expr.
     */
    EnclosedExpr() {
        super();
    }

    /**
     * Instantiates a new enclosed expr.
     *
     * @param inner the inner
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    EnclosedExpr(Expression inner, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.inner = inner;
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
