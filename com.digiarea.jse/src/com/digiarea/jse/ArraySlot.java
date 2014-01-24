package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ArraySlot.
 */
public class ArraySlot extends Node {

    /** 
     * The expression.
     */
    private Expression expression = null;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    ArraySlot() {
        super();
    }

    ArraySlot(Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.expression = expression;
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
