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
 * The Class SuperExpr.
 */
public final class SuperExpr extends Expression {

    /** 
     * The class expression.
     */
    private Expression classExpression;

    /**
     * Gets the class expression.
     *
     * @return the class expression
     */
    public Expression getClassExpression() {
        return classExpression;
    }

    /**
     * Sets the class expression.
     *
     * @param classExpression the new class expression
     */
    public void setClassExpression(Expression classExpression) {
        this.classExpression = classExpression;
    }

    /**
     * Instantiates a new super expr.
     */
    SuperExpr() {
        super();
    }

    /**
     * Instantiates a new super expr.
     *
     * @param classExpression the class expression
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    SuperExpr(Expression classExpression, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.classExpression = classExpression;
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
