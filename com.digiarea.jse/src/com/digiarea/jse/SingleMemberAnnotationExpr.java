package com.digiarea.jse;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.Expression;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class SingleMemberAnnotationExpr extends AnnotationExpr {

    private Expression memberValue;

    public Expression getMemberValue() {
        return memberValue;
    }

    public void setMemberValue(Expression memberValue) {
        this.memberValue = memberValue;
    }

    public SingleMemberAnnotationExpr() {
        super();
    }

    public SingleMemberAnnotationExpr(Expression memberValue, NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
        this.memberValue = memberValue;
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
