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

import com.digiarea.jse.Type;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ClassOrInterfaceType.
 */
public final class ClassOrInterfaceType extends Type {

    /** 
     * The scope.
     */
    private ClassOrInterfaceType scope;

    /** 
     * The name.
     */
    private NameExpr name;

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    /**
     * Gets the scope.
     *
     * @return the scope
     */
    public ClassOrInterfaceType getScope() {
        return scope;
    }

    /**
     * Sets the scope.
     *
     * @param scope the new scope
     */
    public void setScope(ClassOrInterfaceType scope) {
        this.scope = scope;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public NameExpr getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(NameExpr name) {
        this.name = name;
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
     * Instantiates a new class or interface type.
     */
    ClassOrInterfaceType() {
        super();
    }

    /**
     * Instantiates a new class or interface type.
     *
     * @param scope the scope
     * @param name the name
     * @param typeArgs the type args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ClassOrInterfaceType(ClassOrInterfaceType scope, NameExpr name, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.name = name;
        this.typeArgs = typeArgs;
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
