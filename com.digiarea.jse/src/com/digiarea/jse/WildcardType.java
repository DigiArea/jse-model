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

import com.digiarea.jse.Type;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class WildcardType.
 */
public final class WildcardType extends Type {

    /** 
     * The extends type.
     */
    private ReferenceType extendsType;

    /** 
     * The super type.
     */
    private ReferenceType superType;

    /**
     * Gets the extends type.
     *
     * @return the extends type
     */
    public ReferenceType getExtendsType() {
        return extendsType;
    }

    /**
     * Sets the extends type.
     *
     * @param extendsType the new extends type
     */
    public void setExtendsType(ReferenceType extendsType) {
        this.extendsType = extendsType;
    }

    /**
     * Gets the super type.
     *
     * @return the super type
     */
    public ReferenceType getSuperType() {
        return superType;
    }

    /**
     * Sets the super type.
     *
     * @param superType the new super type
     */
    public void setSuperType(ReferenceType superType) {
        this.superType = superType;
    }

    /**
     * Instantiates a new wildcard type.
     */
    WildcardType() {
        super();
    }

    /**
     * Instantiates a new wildcard type.
     *
     * @param extendsType the extends type
     * @param superType the super type
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    WildcardType(ReferenceType extendsType, ReferenceType superType, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.extendsType = extendsType;
        this.superType = superType;
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
