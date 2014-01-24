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

/** 
 * Abstract base class of all node types that represent a method reference
 * expression (added in JLS8 API).
 *
 * <pre>
 * MethodReference:
 *    CreationReference
 *    ExpressionMethodReference
 *    SuperMethodReference
 *    TypeMethodReference
 * </pre>
 * <p>
 * A method reference that is represented by a simple or qualified name,
 * followed by <code>::</code>, followed by a simple name can be represented as
 * {@link ExpressionMethodReference} or as {@link TypeMethodReference}. The
 * ASTParser currently prefers the first form.
 * </p>
 *
 * @see CreationReference
 * @see ExpressionMethodReference
 * @see SuperMethodReference
 * @see TypeMethodReference
 * @since 3.3
 */
public abstract class MethodReference extends Expression {

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

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
     * Instantiates a new method reference.
     */
    MethodReference() {
        super();
    }

    /**
     * Instantiates a new method reference.
     *
     * @param typeArgs the type args
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    MethodReference(NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.typeArgs = typeArgs;
    }

}
