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

import com.digiarea.jse.Statement;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.CatchClause;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class TryStmt.
 */
public final class TryStmt extends Statement {

    /** 
     * The resources.
     */
    private NodeList<VariableDeclarationExpr> resources;

    /** 
     * The try block.
     */
    private BlockStmt tryBlock;

    /** 
     * The catch clauses.
     */
    private NodeList<CatchClause> catchClauses;

    /** 
     * The finally block.
     */
    private BlockStmt finallyBlock;

    /**
     * Gets the resources.
     *
     * @return the resources
     */
    public NodeList<VariableDeclarationExpr> getResources() {
        return resources;
    }

    /**
     * Sets the resources.
     *
     * @param resources the new resources
     */
    public void setResources(NodeList<VariableDeclarationExpr> resources) {
        this.resources = resources;
    }

    /**
     * Gets the try block.
     *
     * @return the try block
     */
    public BlockStmt getTryBlock() {
        return tryBlock;
    }

    /**
     * Sets the try block.
     *
     * @param tryBlock the new try block
     */
    public void setTryBlock(BlockStmt tryBlock) {
        this.tryBlock = tryBlock;
    }

    /**
     * Gets the catch clauses.
     *
     * @return the catch clauses
     */
    public NodeList<CatchClause> getCatchClauses() {
        return catchClauses;
    }

    /**
     * Sets the catch clauses.
     *
     * @param catchClauses the new catch clauses
     */
    public void setCatchClauses(NodeList<CatchClause> catchClauses) {
        this.catchClauses = catchClauses;
    }

    /**
     * Gets the finally block.
     *
     * @return the finally block
     */
    public BlockStmt getFinallyBlock() {
        return finallyBlock;
    }

    /**
     * Sets the finally block.
     *
     * @param finallyBlock the new finally block
     */
    public void setFinallyBlock(BlockStmt finallyBlock) {
        this.finallyBlock = finallyBlock;
    }

    /**
     * Instantiates a new try stmt.
     */
    TryStmt() {
        super();
    }

    /**
     * Instantiates a new try stmt.
     *
     * @param resources the resources
     * @param tryBlock the try block
     * @param catchClauses the catch clauses
     * @param finallyBlock the finally block
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    TryStmt(NodeList<VariableDeclarationExpr> resources, BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.resources = resources;
        this.tryBlock = tryBlock;
        this.catchClauses = catchClauses;
        this.finallyBlock = finallyBlock;
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
