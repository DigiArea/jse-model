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
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class AnnotationExpr.
 */
public abstract class AnnotationExpr extends Expression {

    /** 
     * The name.
     */
    private NameExpr name;

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
     * Instantiates a new annotation expr.
     */
    AnnotationExpr() {
        super();
    }

    /**
     * Instantiates a new annotation expr.
     *
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    AnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
    }

}
