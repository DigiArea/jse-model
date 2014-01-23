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

import com.digiarea.jse.Lambda;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class LambdaBlock extends Lambda {

    private BlockStmt blockStmt = null;

    public BlockStmt getBlockStmt() {
        return blockStmt;
    }

    public void setBlockStmt(BlockStmt blockStmt) {
        this.blockStmt = blockStmt;
    }

    public LambdaBlock() {
        super();
    }

    public LambdaBlock(BlockStmt blockStmt, NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(parameters, annotations, posBegin, posEnd);
        this.blockStmt = blockStmt;
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
