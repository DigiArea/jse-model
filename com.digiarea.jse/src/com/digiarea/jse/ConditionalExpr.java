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

    /**
     * Gets the condition.
     *
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the condition.
     *
     * @param condition the new condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    /**
     * Gets the then expression.
     *
     * @return the then expression
     */
    public Expression getThenExpression() {
        return thenExpression;
    }

    /**
     * Sets the then expression.
     *
     * @param thenExpression the new then expression
     */
    public void setThenExpression(Expression thenExpression) {
        this.thenExpression = thenExpression;
    }

    /**
     * Gets the else expression.
     *
     * @return the else expression
     */
    public Expression getElseExpression() {
        return elseExpression;
    }

    /**
     * Sets the else expression.
     *
     * @param elseExpression the new else expression
     */
    public void setElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
    }

    /**
     * Instantiates a new conditional expr.
     */
    ConditionalExpr() {
        super();
    }

    /**
     * Instantiates a new conditional expr.
     *
     * @param condition the condition
     * @param thenExpression the then expression
     * @param elseExpression the else expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ConditionalExpr(Expression condition, Expression thenExpression, Expression elseExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.condition = condition;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
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
