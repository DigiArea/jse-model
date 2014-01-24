/*******************************************************************************
 * Copyright (c) 2011 - 2014 DigiArea, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     DigiArea, Inc. - initial API and implementation
 *******************************************************************************/
package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class BinaryExpr.
 */
public final class BinaryExpr extends Expression {

    /** 
     * The Enum BinaryOperator.
     */
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

        /** 
         * The string.
         */
        private String string;

        /** 
         * Instantiates a new binary operator.
         *
         * @param string the string
         */
        private BinaryOperator(String string) {
            this.string = string;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
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

        /**
         * Accept.
         *
         * @param <C> the generic type
         * @param v the v
         * @param ctx the ctx
         * @throws Exception the exception
         */
        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        /**
         * Accept.
         *
         * @param <R> the generic type
         * @param <C> the generic type
         * @param v the v
         * @param ctx the ctx
         * @return the r
         * @throws Exception the exception
         */
        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    /** 
     * The left.
     */
    private Expression left;

    /** 
     * The right.
     */
    private Expression right;

    /** 
     * The operator.
     */
    private BinaryExpr.BinaryOperator operator;

    /**
     * Gets the left.
     *
     * @return the left
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Sets the left.
     *
     * @param left the new left
     */
    public void setLeft(Expression left) {
        this.left = left;
    }

    /**
     * Gets the right.
     *
     * @return the right
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Sets the right.
     *
     * @param right the new right
     */
    public void setRight(Expression right) {
        this.right = right;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public BinaryExpr.BinaryOperator getOperator() {
        return operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator the new operator
     */
    public void setOperator(BinaryExpr.BinaryOperator operator) {
        this.operator = operator;
    }

    /**
     * Instantiates a new binary expr.
     */
    BinaryExpr() {
        super();
    }

    /**
     * Instantiates a new binary expr.
     *
     * @param left the left
     * @param right the right
     * @param operator the operator
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    BinaryExpr(Expression left, Expression right, BinaryExpr.BinaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
