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

public final class UnaryExpr extends Expression {

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

        private String string;

        private UnaryOperator(String string) {
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

    private Expression expression;

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

    public UnaryExpr() {
        super();
    }

    public UnaryExpr(Expression expression, UnaryExpr.UnaryOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
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
