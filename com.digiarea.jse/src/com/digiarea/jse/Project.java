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
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class Project.
 */
public class Project extends Node {

    /** 
     * The compilation units.
     */
    private NodeList<CompilationUnit> compilationUnits = null;

    /**
     * Gets the compilation units.
     *
     * @return the compilation units
     */
    public NodeList<CompilationUnit> getCompilationUnits() {
        return compilationUnits;
    }

    /**
     * Sets the compilation units.
     *
     * @param compilationUnits the new compilation units
     */
    public void setCompilationUnits(NodeList<CompilationUnit> compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    /**
     * Instantiates a new project.
     */
    Project() {
        super();
    }

    /**
     * Instantiates a new project.
     *
     * @param compilationUnits the compilation units
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    Project(NodeList<CompilationUnit> compilationUnits, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.compilationUnits = compilationUnits;
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
