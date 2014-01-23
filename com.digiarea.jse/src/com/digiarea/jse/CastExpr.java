package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class CastExpr extends Expression {

    /** 
     * An intersection type, T1 & T2 & ... Tn (used in cast expressions)
     *
     * @since 1.8
     */
    private NodeList<Type> types = null;

    private Expression expression = null;

    public NodeList<Type> getTypes() {
        return types;
    }

    public void setTypes(NodeList<Type> types) {
        this.types = types;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public CastExpr() {
        super();
    }

    public CastExpr(NodeList<Type> types, Expression expression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.types = types;
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
