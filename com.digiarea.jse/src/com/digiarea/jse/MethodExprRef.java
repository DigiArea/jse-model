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

import com.digiarea.jse.MethodRef;
import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class MethodExprRef.
 */
public class MethodExprRef extends MethodRef {

    /** 
     * The scope.
     */
    private Expression scope;

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
     * Instantiates a new method expr ref.
     */
    MethodExprRef() {
        super();
    }

    /**
     * Instantiates a new method expr ref.
     *
     * @param scope the scope
     * @param typeArgs the type args
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    MethodExprRef(Expression scope, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, name, annotations, posBegin, posEnd);
        this.scope = scope;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.MethodRef#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.MethodRef#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
