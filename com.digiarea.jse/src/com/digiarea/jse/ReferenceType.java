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
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ReferenceType.
 */
public final class ReferenceType extends Type {

    /** 
     * The type.
     */
    private Type type;

    /** 
     * The slots.
     */
    private NodeList<ArraySlot> slots;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(Type type) {
        this.type = type;
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
     * Instantiates a new reference type.
     */
    ReferenceType() {
        super();
    }

    /**
     * Instantiates a new reference type.
     *
     * @param type the type
     * @param slots the slots
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ReferenceType(Type type, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.type = type;
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
