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
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class BlockStmt.
 */
public final class BlockStmt extends Statement {

    /** 
     * The statements.
     */
    private NodeList<Statement> statements;

    /**
     * Gets the statements.
     *
     * @return the statements
     */
    public NodeList<Statement> getStatements() {
        return statements;
    }

    /**
     * Sets the statements.
     *
     * @param statements the new statements
     */
    public void setStatements(NodeList<Statement> statements) {
        this.statements = statements;
    }

    /**
     * Instantiates a new block stmt.
     */
    BlockStmt() {
        super();
    }

    /**
     * Instantiates a new block stmt.
     *
     * @param statements the statements
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    BlockStmt(NodeList<Statement> statements, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.statements = statements;
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
