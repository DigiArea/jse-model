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
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class CatchClause extends Node {

    private boolean isFinal;

    /** 
     * A union type, T1 | T2 | ... Tn (used in multicatch statements)
     *
     * @since 1.7
     */
    private NodeList<Type> types;

    private String name;

    private BlockStmt catchBlock;

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public NodeList<Type> getTypes() {
        return types;
    }

    public void setTypes(NodeList<Type> types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockStmt getCatchBlock() {
        return catchBlock;
    }

    public void setCatchBlock(BlockStmt catchBlock) {
        this.catchBlock = catchBlock;
    }

    CatchClause() {
        super();
    }

    CatchClause(boolean isFinal, NodeList<Type> types, String name, BlockStmt catchBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.isFinal = isFinal;
        this.types = types;
        this.name = name;
        this.catchBlock = catchBlock;
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
