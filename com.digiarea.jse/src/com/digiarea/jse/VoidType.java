package com.digiarea.jse;

import com.digiarea.jse.Type;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class VoidType extends Type {

    public VoidType() {
        super();
    }

    public VoidType(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
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
