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
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class FieldAccessExpr.
 */
public final class FieldAccessExpr extends Expression {

    /** 
     * The scope.
     */
    private Expression scope;

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    /** 
     * The field.
     */
    private String field;

    /**
     * Gets the scope.
     *
     * @return the scope
     */
    public Expression getScope() {
        return scope;
    }

    /**
     * Sets the scope.
     *
     * @param scope the new scope
     */
    public void setScope(Expression scope) {
        this.scope = scope;
    }

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
     * Gets the field.
     *
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the field.
     *
     * @param field the new field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Instantiates a new field access expr.
     */
    FieldAccessExpr() {
        super();
    }

    /**
     * Instantiates a new field access expr.
     *
     * @param scope the scope
     * @param typeArgs the type args
     * @param field the field
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    FieldAccessExpr(Expression scope, NodeList<Type> typeArgs, String field, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.typeArgs = typeArgs;
        this.field = field;
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
