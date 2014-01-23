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

public class Project extends Node {

    private NodeList<CompilationUnit> compilationUnits = null;

    public NodeList<CompilationUnit> getCompilationUnits() {
        return compilationUnits;
    }

    public void setCompilationUnits(NodeList<CompilationUnit> compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    public Project() {
        super();
    }

    public Project(NodeList<CompilationUnit> compilationUnits, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.compilationUnits = compilationUnits;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
