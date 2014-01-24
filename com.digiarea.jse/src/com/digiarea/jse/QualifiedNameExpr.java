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

import com.digiarea.jse.NameExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class QualifiedNameExpr.
 */
public final class QualifiedNameExpr extends NameExpr {

    /** 
     * The qualifier.
     */
    private NameExpr qualifier;

    /**
     * Gets the qualifier.
     *
     * @return the qualifier
     */
    public NameExpr getQualifier() {
        return qualifier;
    }

    /**
     * Sets the qualifier.
     *
     * @param qualifier the new qualifier
     */
    public void setQualifier(NameExpr qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * Instantiates a new qualified name expr.
     */
    QualifiedNameExpr() {
        super();
    }

    /**
     * Instantiates a new qualified name expr.
     *
     * @param qualifier the qualifier
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    QualifiedNameExpr(NameExpr qualifier, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
        this.qualifier = qualifier;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.NameExpr#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.NameExpr#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
