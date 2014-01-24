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

import com.digiarea.jse.StringLiteralExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class IntegerLiteralExpr.
 */
public class IntegerLiteralExpr extends StringLiteralExpr {

    /** 
     * The Constant UNSIGNED_MIN_VALUE.
     */
    private static final String UNSIGNED_MIN_VALUE = "2147483648";

    /** 
     * The Constant MIN_VALUE.
     */
    protected static final String MIN_VALUE = "-" + UNSIGNED_MIN_VALUE;

    /**
     * Instantiates a new integer literal expr.
     */
    IntegerLiteralExpr() {
        super();
    }

    /**
     * Instantiates a new integer literal expr.
     *
     * @param value the value
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    IntegerLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(value, annotations, posBegin, posEnd);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.StringLiteralExpr#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.StringLiteralExpr#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
