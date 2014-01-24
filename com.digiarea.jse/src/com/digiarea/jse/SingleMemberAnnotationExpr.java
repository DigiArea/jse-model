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
import com.digiarea.jse.Expression;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class SingleMemberAnnotationExpr.
 */
public final class SingleMemberAnnotationExpr extends AnnotationExpr {

    /** 
     * The member value.
     */
    private Expression memberValue;

    /**
     * Gets the member value.
     *
     * @return the member value
     */
    public Expression getMemberValue() {
        return memberValue;
    }

    /**
     * Sets the member value.
     *
     * @param memberValue the new member value
     */
    public void setMemberValue(Expression memberValue) {
        this.memberValue = memberValue;
    }

    /**
     * Instantiates a new single member annotation expr.
     */
    SingleMemberAnnotationExpr() {
        super();
    }

    /**
     * Instantiates a new single member annotation expr.
     *
     * @param memberValue the member value
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    SingleMemberAnnotationExpr(Expression memberValue, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
        this.memberValue = memberValue;
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
