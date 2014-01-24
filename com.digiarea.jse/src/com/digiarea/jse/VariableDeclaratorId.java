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
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class VariableDeclaratorId.
 */
public final class VariableDeclaratorId extends Node {

    /** 
     * The name.
     */
    private String name;

    /** 
     * The slots.
     */
    private NodeList<ArraySlot> slots;

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
     * Gets the slots.
     *
     * @return the slots
     */
    public NodeList<ArraySlot> getSlots() {
        return slots;
    }

    /**
     * Sets the slots.
     *
     * @param slots the new slots
     */
    public void setSlots(NodeList<ArraySlot> slots) {
        this.slots = slots;
    }

    /**
     * Instantiates a new variable declarator id.
     */
    VariableDeclaratorId() {
        super();
    }

    /**
     * Instantiates a new variable declarator id.
     *
     * @param name the name
     * @param slots the slots
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    VariableDeclaratorId(String name, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
        this.slots = slots;
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
