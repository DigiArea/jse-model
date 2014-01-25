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
import com.digiarea.jse.Expression;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class SwitchEntryStmt.
 */
public final class SwitchEntryStmt extends Statement {

    /** 
     * The label.
     */
    private Expression label;

    /** 
     * The stmts.
     */
    private NodeList<Statement> stmts;

    /**
     * Gets the label.
     *
     * @return the label
     */
    public Expression getLabel() {
        return label;
    }

    /**
     * Sets the label.
     *
     * @param label the new label
     */
    public void setLabel(Expression label) {
        this.label = label;
    }

    /**
     * Gets the stmts.
     *
     * @return the stmts
     */
    public NodeList<Statement> getStmts() {
        return stmts;
    }

    /**
     * Sets the stmts.
     *
     * @param stmts the new stmts
     */
    public void setStmts(NodeList<Statement> stmts) {
        this.stmts = stmts;
    }

    /**
     * Instantiates a new switch entry stmt.
     */
    SwitchEntryStmt() {
        super();
    }

    /**
     * Instantiates a new switch entry stmt.
     *
     * @param label the label
     * @param stmts the stmts
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    SwitchEntryStmt(Expression label, NodeList<Statement> stmts, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.label = label;
        this.stmts = stmts;
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
