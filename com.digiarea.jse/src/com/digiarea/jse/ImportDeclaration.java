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
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ImportDeclaration.
 */
public final class ImportDeclaration extends Node {

    /** 
     * The name.
     */
    private NameExpr name;

    /** 
     * The is static.
     */
    private boolean isStatic;

    /** 
     * The is asterisk.
     */
    private boolean isAsterisk;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public NameExpr getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(NameExpr name) {
        this.name = name;
    }

    /**
     * Checks if is static.
     *
     * @return true, if is static
     */
    public boolean isStatic() {
        return isStatic;
    }

    /**
     * Sets the static.
     *
     * @param isStatic the new static
     */
    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    /**
     * Checks if is asterisk.
     *
     * @return true, if is asterisk
     */
    public boolean isAsterisk() {
        return isAsterisk;
    }

    /**
     * Sets the asterisk.
     *
     * @param isAsterisk the new asterisk
     */
    public void setAsterisk(boolean isAsterisk) {
        this.isAsterisk = isAsterisk;
    }

    /**
     * Instantiates a new import declaration.
     */
    ImportDeclaration() {
        super();
    }

    /**
     * Instantiates a new import declaration.
     *
     * @param name the name
     * @param isStatic the is static
     * @param isAsterisk the is asterisk
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ImportDeclaration(NameExpr name, boolean isStatic, boolean isAsterisk, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
        this.isStatic = isStatic;
        this.isAsterisk = isAsterisk;
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
