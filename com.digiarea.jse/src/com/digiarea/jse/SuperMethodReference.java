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

import com.digiarea.jse.MethodReference;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Super method reference node type (added in JLS8 API).
 *
 * <pre>
 * SuperMethodReference:
 *     [ ClassName <b>.</b> ] <b>super</b> <b>::</b>
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         Identifier
 * </pre>
 *
 * @since 3.3
 * @noinstantiate This class is not intended to be instantiated by clients
 */
public class SuperMethodReference extends MethodReference {

    /** 
     * The optional qualifier; <code>null</code> for none; defaults to none.
     */
    private NameExpr qualifier = null;

    /** 
     * The method name; defaults to a unspecified, legal Java method name.
     */
    private String methodName = null;

    /**
     * Gets the qualifier.
     *
     * @return the qualifier
     */
    public NameExpr getQualifier() {
        return qualifier;
    }

    /**
     * Sets the qualifier.
     *
     * @param qualifier the new qualifier
     */
    public void setQualifier(NameExpr qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * Gets the method name.
     *
     * @return the method name
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the method name.
     *
     * @param methodName the new method name
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Instantiates a new super method reference.
     */
    SuperMethodReference() {
        super();
    }

    /**
     * Instantiates a new super method reference.
     *
     * @param qualifier the qualifier
     * @param methodName the method name
     * @param typeArgs the type args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    SuperMethodReference(NameExpr qualifier, String methodName, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, annotations, posBegin, posEnd);
        this.qualifier = qualifier;
        this.methodName = methodName;
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
