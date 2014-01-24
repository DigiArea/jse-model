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

import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.ArrayInitializerExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class ArrayCreationExpr extends Expression {

    private Type type;

    private NodeList<ArraySlot> slots;

    private ArrayInitializerExpr initializer;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public NodeList<ArraySlot> getSlots() {
        return slots;
    }

    public void setSlots(NodeList<ArraySlot> slots) {
        this.slots = slots;
    }

    public ArrayInitializerExpr getInitializer() {
        return initializer;
    }

    public void setInitializer(ArrayInitializerExpr initializer) {
        this.initializer = initializer;
    }

    ArrayCreationExpr() {
        super();
    }

    ArrayCreationExpr(Type type, NodeList<ArraySlot> slots, ArrayInitializerExpr initializer, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.type = type;
        this.slots = slots;
        this.initializer = initializer;
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
