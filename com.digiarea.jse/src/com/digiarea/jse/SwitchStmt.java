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
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class SwitchStmt.
 */
public final class SwitchStmt extends Statement {

    /** 
     * The selector.
     */
    private Expression selector;

    /** 
     * The entries.
     */
    private NodeList<SwitchEntryStmt> entries;

    /**
     * Gets the selector.
     *
     * @return the selector
     */
    public Expression getSelector() {
        return selector;
    }

    /**
     * Sets the selector.
     *
     * @param selector the new selector
     */
    public void setSelector(Expression selector) {
        this.selector = selector;
    }

    /**
     * Gets the entries.
     *
     * @return the entries
     */
    public NodeList<SwitchEntryStmt> getEntries() {
        return entries;
    }

    /**
     * Sets the entries.
     *
     * @param entries the new entries
     */
    public void setEntries(NodeList<SwitchEntryStmt> entries) {
        this.entries = entries;
    }

    /**
     * Instantiates a new switch stmt.
     */
    SwitchStmt() {
        super();
    }

    /**
     * Instantiates a new switch stmt.
     *
     * @param selector the selector
     * @param entries the entries
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    SwitchStmt(Expression selector, NodeList<SwitchEntryStmt> entries, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.selector = selector;
        this.entries = entries;
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
