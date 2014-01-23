package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class PackageDeclaration extends Node {

    private NameExpr name;

    public NameExpr getName() {
        return name;
    }

    public void setName(NameExpr name) {
        this.name = name;
    }

    public PackageDeclaration() {
        super();
    }

    public PackageDeclaration(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
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
