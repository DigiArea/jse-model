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

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class NormalAnnotationExpr.
 */
public final class NormalAnnotationExpr extends AnnotationExpr {

    /** 
     * The pairs.
     */
    private NodeList<MemberValuePair> pairs;

    /**
     * Gets the pairs.
     *
     * @return the pairs
     */
    public NodeList<MemberValuePair> getPairs() {
        return pairs;
    }

    /**
     * Sets the pairs.
     *
     * @param pairs the new pairs
     */
    public void setPairs(NodeList<MemberValuePair> pairs) {
        this.pairs = pairs;
    }

    /**
     * Instantiates a new normal annotation expr.
     */
    NormalAnnotationExpr() {
        super();
    }

    /**
     * Instantiates a new normal annotation expr.
     *
     * @param pairs the pairs
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    NormalAnnotationExpr(NodeList<MemberValuePair> pairs, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
        this.pairs = pairs;
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
