package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class ImportDeclaration extends Node {

    private NameExpr name;

    private boolean isStatic;

    private boolean isAsterisk;

    public NameExpr getName() {
        return name;
    }

    public void setName(NameExpr name) {
        this.name = name;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public boolean isAsterisk() {
        return isAsterisk;
    }

    public void setAsterisk(boolean isAsterisk) {
        this.isAsterisk = isAsterisk;
    }

    public ImportDeclaration() {
        super();
    }

    public ImportDeclaration(NameExpr name, boolean isStatic, boolean isAsterisk, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
        this.isStatic = isStatic;
        this.isAsterisk = isAsterisk;
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
