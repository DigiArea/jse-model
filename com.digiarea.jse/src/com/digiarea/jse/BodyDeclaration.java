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

import com.digiarea.jse.Node;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class BodyDeclaration.
 */
public abstract class BodyDeclaration extends Node {

    /** 
     * The java doc.
     */
    private JavadocComment javaDoc;

    /**
     * Gets the java doc.
     *
     * @return the java doc
     */
    public JavadocComment getJavaDoc() {
        return javaDoc;
    }

    /**
     * Sets the java doc.
     *
     * @param javaDoc the new java doc
     */
    public void setJavaDoc(JavadocComment javaDoc) {
        this.javaDoc = javaDoc;
    }

    /**
     * Instantiates a new body declaration.
     */
    BodyDeclaration() {
        super();
    }

    /**
     * Instantiates a new body declaration.
     *
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    BodyDeclaration(JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.javaDoc = javaDoc;
    }

}
