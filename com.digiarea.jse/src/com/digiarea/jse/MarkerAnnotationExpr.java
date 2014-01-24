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
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class MarkerAnnotationExpr.
 */
public final class MarkerAnnotationExpr extends AnnotationExpr {

    /**
     * Instantiates a new marker annotation expr.
     */
    MarkerAnnotationExpr() {
        super();
    }

    /**
     * Instantiates a new marker annotation expr.
     *
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    MarkerAnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
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
