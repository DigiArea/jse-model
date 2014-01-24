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

import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class InitializerDeclaration.
 */
public final class InitializerDeclaration extends BodyDeclaration {

    /** 
     * The is static.
     */
    private boolean isStatic;

    /** 
     * The block.
     */
    private BlockStmt block;

    /**
     * Checks if is static.
     *
     * @return true, if is static
     */
    public boolean isStatic() {
        return isStatic;
    }

    /**
     * Sets the static.
     *
     * @param isStatic the new static
     */
    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    /**
     * Gets the block.
     *
     * @return the block
     */
    public BlockStmt getBlock() {
        return block;
    }

    /**
     * Sets the block.
     *
     * @param block the new block
     */
    public void setBlock(BlockStmt block) {
        this.block = block;
    }

    /**
     * Instantiates a new initializer declaration.
     */
    InitializerDeclaration() {
        super();
    }

    /**
     * Instantiates a new initializer declaration.
     *
     * @param isStatic the is static
     * @param block the block
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    InitializerDeclaration(boolean isStatic, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.isStatic = isStatic;
        this.block = block;
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
