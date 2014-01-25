package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class SuperExpr.
 */
public final class SuperExpr extends Expression {

    /** 
     * The class expression.
     */
    private Expression classExpression;

    public Expression getClassExpression() {
        return classExpression;
    }

    public void setClassExpression(Expression classExpression) {
        this.classExpression = classExpression;
    }

    SuperExpr() {
        super();
    }

    SuperExpr(Expression classExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.classExpression = classExpression;
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
