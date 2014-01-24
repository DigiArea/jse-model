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
import com.digiarea.jse.Parameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;

/** 
 * A node for a lambda expression.
 *
 * For example:
 *
 * <pre>
 * {@code
 *   ()->{}
 *   (List<String> ls)->ls.size()
 *   (x,y)-> { return x + y; }
 * }
 * </pre>
 *
 * Lambda expressions come in two forms: (i) expression lambdas, whose body is
 * an expression, and (ii) statement lambdas, whose body is a block
 *
 */
public abstract class Lambda extends Expression {

    /** 
     * The parameters.
     */
    private NodeList<Parameter> parameters = null;

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public NodeList<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters.
     *
     * @param parameters the new parameters
     */
    public void setParameters(NodeList<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Instantiates a new lambda.
     */
    Lambda() {
        super();
    }

    /**
     * Instantiates a new lambda.
     *
     * @param parameters the parameters
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    Lambda(NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.parameters = parameters;
    }

}
