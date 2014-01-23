package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

public final class BinaryExpr extends Expression {

    public static enum BinaryOperator {

        /** The or.
         */
        or("||"), 
        /** The and.
         */
        and("&&"), 
        /** The bin or.
         */
        binOr("|"), 
        /** The bin and.
         */
        binAnd("&"), 
        /** The xor.
         */
        xor("^"), 
        /** The equals.
         */
        equals("=="), 
        /** The not equals.
         */
        notEquals("!="), 
        /** The less.
         */
        less("<"), 
        /** The greater.
         */
        greater(">"), 
        /** The less equals.
         */
        lessEquals("<="), 
        /** The greater equals.
         */
        greaterEquals(">="), 
        /** The l shift.
         */
        lShift("<<"), 
        /** The r signed shift.
         */
        rSignedShift(">>"), 
        /** The r unsigned shift.
         */
        rUnsignedShift(">>>"), 
        /** The plus.
         */
        plus("+"), 
        /** The minus.
         */
        minus("-"), 
        /** The times.
         */
        times("*"), 
        /** The divide.
         */
        divide("/"), 
        /** The remainder.
         */
        remainder("%");

        private String string;

        private BinaryOperator(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

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

    private Expression left;

    private Expression right;

    private BinaryExpr.BinaryOperator operator;

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public BinaryExpr.BinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(BinaryExpr.BinaryOperator operator) {
        this.operator = operator;
    }

    public BinaryExpr() {
        super();
    }

    public BinaryExpr(Expression left, Expression right, BinaryExpr.BinaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.left = left;
        this.right = right;
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
