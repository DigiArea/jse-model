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
     * The expression.
     */
    private Expression expression;

    /** 
     * The operator.
     */
    private UnaryExpr.UnaryOperator operator;

    /**
     * Gets the expression.
     *
     * @return the expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the expression.
     *
     * @param expression the new expression
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public UnaryExpr.UnaryOperator getOperator() {
        return operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator the new operator
     */
    public void setOperator(UnaryExpr.UnaryOperator operator) {
        this.operator = operator;
    }

    /**
     * Instantiates a new unary expr.
     */
    UnaryExpr() {
        super();
    }

    /**
     * Instantiates a new unary expr.
     *
     * @param expression the expression
     * @param operator the operator
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    UnaryExpr(Expression expression, UnaryExpr.UnaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.expression = expression;
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
