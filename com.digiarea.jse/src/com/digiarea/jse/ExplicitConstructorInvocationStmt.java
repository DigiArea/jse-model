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

import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ExplicitConstructorInvocationStmt.
 */
public final class ExplicitConstructorInvocationStmt extends Statement {

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    /** 
     * The is this.
     */
    private boolean isThis;

    /** 
     * The expression.
     */
    private Expression expression;

    /** 
     * The args.
     */
    private NodeList<Expression> args;

    /**
     * Gets the type args.
     *
     * @return the type args
     */
    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    /**
     * Sets the type args.
     *
     * @param typeArgs the new type args
     */
    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    /**
     * Checks if is this.
     *
     * @return true, if is this
     */
    public boolean isThis() {
        return isThis;
    }

    /**
     * Sets the this.
     *
     * @param isThis the new this
     */
    public void setThis(boolean isThis) {
        this.isThis = isThis;
    }

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
     * Gets the args.
     *
     * @return the args
     */
    public NodeList<Expression> getArgs() {
        return args;
    }

    /**
     * Sets the args.
     *
     * @param args the new args
     */
    public void setArgs(NodeList<Expression> args) {
        this.args = args;
    }

    /**
     * Instantiates a new explicit constructor invocation stmt.
     */
    ExplicitConstructorInvocationStmt() {
        super();
    }

    /**
     * Instantiates a new explicit constructor invocation stmt.
     *
     * @param typeArgs the type args
     * @param isThis the is this
     * @param expression the expression
     * @param args the args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ExplicitConstructorInvocationStmt(NodeList<Type> typeArgs, boolean isThis, Expression expression, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.typeArgs = typeArgs;
        this.isThis = isThis;
        this.expression = expression;
        this.args = args;
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
