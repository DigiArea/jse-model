package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ArrayInitializerExpr.
 */
public final class ArrayInitializerExpr extends Expression {

    /** 
     * The values.
     */
    private NodeList<Expression> values;

    public NodeList<Expression> getValues() {
        return values;
    }

    public void setValues(NodeList<Expression> values) {
        this.values = values;
    }

    ArrayInitializerExpr() {
        super();
    }

    ArrayInitializerExpr(NodeList<Expression> values, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.values = values;
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
