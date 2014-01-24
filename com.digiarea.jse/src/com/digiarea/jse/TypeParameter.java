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
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class TypeParameter.
 */
public final class TypeParameter extends Node {

    /** 
     * The name.
     */
    private String name;

    /** 
     * The type bound.
     */
    private NodeList<ClassOrInterfaceType> typeBound;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type bound.
     *
     * @return the type bound
     */
    public NodeList<ClassOrInterfaceType> getTypeBound() {
        return typeBound;
    }

    /**
     * Sets the type bound.
     *
     * @param typeBound the new type bound
     */
    public void setTypeBound(NodeList<ClassOrInterfaceType> typeBound) {
        this.typeBound = typeBound;
    }

    /**
     * Instantiates a new type parameter.
     */
    TypeParameter() {
        super();
    }

    /**
     * Instantiates a new type parameter.
     *
     * @param name the name
     * @param typeBound the type bound
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    TypeParameter(String name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
        this.typeBound = typeBound;
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
