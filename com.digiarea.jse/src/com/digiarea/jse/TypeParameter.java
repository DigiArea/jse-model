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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeList<ClassOrInterfaceType> getTypeBound() {
        return typeBound;
    }

    public void setTypeBound(NodeList<ClassOrInterfaceType> typeBound) {
        this.typeBound = typeBound;
    }

    TypeParameter() {
        super();
    }

    TypeParameter(String name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
        this.typeBound = typeBound;
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
