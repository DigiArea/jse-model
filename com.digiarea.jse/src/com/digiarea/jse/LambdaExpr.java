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

import com.digiarea.jse.Lambda;
import com.digiarea.jse.Expression;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class LambdaExpr.
 */
public final class LambdaExpr extends Lambda {

    /** 
     * The expression.
     */
    private Expression expression = null;

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
     * Instantiates a new lambda expr.
     */
    LambdaExpr() {
        super();
    }

    /**
     * Instantiates a new lambda expr.
     *
     * @param expression the expression
     * @param parameters the parameters
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    LambdaExpr(Expression expression, NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(parameters, annotations, posBegin, posEnd);
        this.expression = expression;
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
