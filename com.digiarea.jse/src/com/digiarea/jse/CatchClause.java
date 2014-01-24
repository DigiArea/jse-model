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

/** 
 * The Class CatchClause.
 */
public final class CatchClause extends Node {

    /** 
     * The is final.
     */
    private boolean isFinal;

    /** 
     * A union type, T1 | T2 | ... Tn (used in multicatch statements)
     *
     * @since 1.7
     */
    private NodeList<Type> types;

    /** 
     * The name.
     */
    private String name;

    /** 
     * The catch block.
     */
    private BlockStmt catchBlock;

    /**
     * Checks if is final.
     *
     * @return true, if is final
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Sets the final.
     *
     * @param isFinal the new final
     */
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Gets the types.
     *
     * @return the types
     */
    public NodeList<Type> getTypes() {
        return types;
    }

    /**
     * Sets the types.
     *
     * @param types the new types
     */
    public void setTypes(NodeList<Type> types) {
        this.types = types;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the catch block.
     *
     * @return the catch block
     */
    public BlockStmt getCatchBlock() {
        return catchBlock;
    }

    /**
     * Sets the catch block.
     *
     * @param catchBlock the new catch block
     */
    public void setCatchBlock(BlockStmt catchBlock) {
        this.catchBlock = catchBlock;
    }

    /**
     * Instantiates a new catch clause.
     */
    CatchClause() {
        super();
    }

    /**
     * Instantiates a new catch clause.
     *
     * @param isFinal the is final
     * @param types the types
     * @param name the name
     * @param catchBlock the catch block
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    CatchClause(boolean isFinal, NodeList<Type> types, String name, BlockStmt catchBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.isFinal = isFinal;
        this.types = types;
        this.name = name;
        this.catchBlock = catchBlock;
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
