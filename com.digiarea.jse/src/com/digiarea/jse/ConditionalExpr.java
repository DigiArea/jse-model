package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ConditionalExpr.
 */
public final class ConditionalExpr extends Expression {

    /** 
     * The condition.
     */
    private Expression condition;

    /** 
     * The then expression.
     */
    private Expression thenExpression;

    /** 
     * The else expression.
     */
    private Expression elseExpression;

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getThenExpression() {
        return thenExpression;
    }

    public void setThenExpression(Expression thenExpression) {
        this.thenExpression = thenExpression;
    }

    public Expression getElseExpression() {
        return elseExpression;
    }

    public void setElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
    }

    ConditionalExpr() {
        super();
    }

    ConditionalExpr(Expression condition, Expression thenExpression, Expression elseExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.condition = condition;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
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
