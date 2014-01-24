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

public final class Parameter extends Node {

    private Modifiers modifiers;

    private Type type;

    private Ellipsis ellipsis;

    private VariableDeclaratorId id;

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ellipsis getEllipsis() {
        return ellipsis;
    }

    public void setEllipsis(Ellipsis ellipsis) {
        this.ellipsis = ellipsis;
    }

    public VariableDeclaratorId getId() {
        return id;
    }

    public void setId(VariableDeclaratorId id) {
        this.id = id;
    }

    Parameter() {
        super();
    }

    Parameter(Modifiers modifiers, Type type, Ellipsis ellipsis, VariableDeclaratorId id, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.type = type;
        this.ellipsis = ellipsis;
        this.id = id;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
