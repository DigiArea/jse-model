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
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.Type;
import com.digiarea.jse.Ellipsis;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class Parameter.
 */
public final class Parameter extends Node {

    /** 
     * The modifiers.
     */
    private Modifiers modifiers;

    /** 
     * The type.
     */
    private Type type;

    /** 
     * The ellipsis.
     */
    private Ellipsis ellipsis;

    /** 
     * The id.
     */
    private VariableDeclaratorId id;

    /**
     * Gets the modifiers.
     *
     * @return the modifiers
     */
    public Modifiers getModifiers() {
        return modifiers;
    }

    /**
     * Sets the modifiers.
     *
     * @param modifiers the new modifiers
     */
    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

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
     * Gets the ellipsis.
     *
     * @return the ellipsis
     */
    public Ellipsis getEllipsis() {
        return ellipsis;
    }

    /**
     * Sets the ellipsis.
     *
     * @param ellipsis the new ellipsis
     */
    public void setEllipsis(Ellipsis ellipsis) {
        this.ellipsis = ellipsis;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public VariableDeclaratorId getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(VariableDeclaratorId id) {
        this.id = id;
    }

    /**
     * Instantiates a new parameter.
     */
    Parameter() {
        super();
    }

    /**
     * Instantiates a new parameter.
     *
     * @param modifiers the modifiers
     * @param type the type
     * @param ellipsis the ellipsis
     * @param id the id
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    Parameter(Modifiers modifiers, Type type, Ellipsis ellipsis, VariableDeclaratorId id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.type = type;
        this.ellipsis = ellipsis;
        this.id = id;
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
