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
 * The Class AssignExpr.
 */
public final class AssignExpr extends Expression {

    /** 
     * The Enum AssignOperator.
     */
    public static enum AssignOperator {

        /** The assign.
         */
        assign("="), 
        /** The plus.
         */
        plus("+="), 
        /** The minus.
         */
        minus("-="), 
        /** The star.
         */
        star("*="), 
        /** The slash.
         */
        slash("/="), 
        /** The and.
         */
        and("&="), 
        /** The or.
         */
        or("|="), 
        /** The xor.
         */
        xor("^="), 
        /** The rem.
         */
        rem("%="), 
        /** The l shift.
         */
        lShift("<<="), 
        /** The r signed shift.
         */
        rSignedShift(">>="), 
        /** The r unsigned shift.
         */
        rUnsignedShift(">>>=");

        /** 
         * The string.
         */
        private String string;

        /** 
         * Instantiates a new assign operator.
         *
         * @param string the string
         */
        private AssignOperator(String string) {
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
     * The target.
     */
    private Expression target;

    /** 
     * The value.
     */
    private Expression value;

    /** 
     * The operator.
     */
    private AssignExpr.AssignOperator operator;

    /**
     * Gets the target.
     *
     * @return the target
     */
    public Expression getTarget() {
        return target;
    }

    /**
     * Sets the target.
     *
     * @param target the new target
     */
    public void setTarget(Expression target) {
        this.target = target;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public Expression getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(Expression value) {
        this.value = value;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public AssignExpr.AssignOperator getOperator() {
        return operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator the new operator
     */
    public void setOperator(AssignExpr.AssignOperator operator) {
        this.operator = operator;
    }

    /**
     * Instantiates a new assign expr.
     */
    AssignExpr() {
        super();
    }

    /**
     * Instantiates a new assign expr.
     *
     * @param target the target
     * @param value the value
     * @param operator the operator
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    AssignExpr(Expression target, Expression value, AssignExpr.AssignOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.target = target;
        this.value = value;
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
