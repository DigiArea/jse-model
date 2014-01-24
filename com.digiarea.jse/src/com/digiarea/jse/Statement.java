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
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class Statement.
 */
public abstract class Statement extends Node {

    /**
     * Instantiates a new statement.
     */
    Statement() {
        super();
    }

    /**
     * Instantiates a new statement.
     *
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    Statement(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
    }

}
