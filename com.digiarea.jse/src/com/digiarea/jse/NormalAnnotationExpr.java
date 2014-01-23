package com.digiarea.jse;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class NormalAnnotationExpr extends AnnotationExpr {

    private NodeList<MemberValuePair> pairs;

    public NodeList<MemberValuePair> getPairs() {
        return pairs;
    }

    public void setPairs(NodeList<MemberValuePair> pairs) {
        this.pairs = pairs;
    }

    public NormalAnnotationExpr() {
        super();
    }

    public NormalAnnotationExpr(NodeList<MemberValuePair> pairs, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
        this.pairs = pairs;
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
