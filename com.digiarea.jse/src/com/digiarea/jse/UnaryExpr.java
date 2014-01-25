package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class UnaryExpr.
 */
public final class UnaryExpr extends Expression {

    /** 
     * The Enum UnaryOperator.
     */
    public static enum UnaryOperator {

        /** The positive.
         */
        positive("+"), 
        /** The negative.
         */
        negative("-"), 
        /** The pre increment.
         */
        preIncrement("++"), 
        /** The pre decrement.
         */
        preDecrement("--"), 
        /** The not.
         */
        not("!"), 
        /** The inverse.
         */
        inverse("~"), 
        /** The pos increment.
         */
        posIncrement("++"), 
        /** The pos decrement.
         */
        posDecrement("--");

        /** 
         * The string.
         */
        private String string;

        /** 
         * Instantiates a new unary operator.
         *
         * @param string the string
         */
        private UnaryOperator(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

        /** 
         * Gets the string.
         *
         * @return the string
         */
        public String getString() {
            return string;
        }

        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    /** 
     * The expression.
     */
    private Expression expression;

    /** 
     * The operator.
     */
    private UnaryExpr.UnaryOperator operator;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public UnaryExpr.UnaryOperator getOperator() {
        return operator;
    }

    public void setOperator(UnaryExpr.UnaryOperator operator) {
        this.operator = operator;
    }

    UnaryExpr() {
        super();
    }

    UnaryExpr(Expression expression, UnaryExpr.UnaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.expression = expression;
        this.operator = operator;
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
