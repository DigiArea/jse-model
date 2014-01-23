package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class VariableDeclaratorId extends Node {

    private String name;

    private NodeList<ArraySlot> slots;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeList<ArraySlot> getSlots() {
        return slots;
    }

    public void setSlots(NodeList<ArraySlot> slots) {
        this.slots = slots;
    }

    public VariableDeclaratorId() {
        super();
    }

    public VariableDeclaratorId(String name, NodeList<ArraySlot> slots, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
        this.slots = slots;
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
