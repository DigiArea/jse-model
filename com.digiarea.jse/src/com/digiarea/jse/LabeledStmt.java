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
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class LabeledStmt.
 */
public final class LabeledStmt extends Statement {

    /** 
     * The label.
     */
    private String label;

    /** 
     * The stmt.
     */
    private Statement stmt;

    /**
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label.
     *
     * @param label the new label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets the stmt.
     *
     * @return the stmt
     */
    public Statement getStmt() {
        return stmt;
    }

    /**
     * Sets the stmt.
     *
     * @param stmt the new stmt
     */
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    /**
     * Instantiates a new labeled stmt.
     */
    LabeledStmt() {
        super();
    }

    /**
     * Instantiates a new labeled stmt.
     *
     * @param label the label
     * @param stmt the stmt
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    LabeledStmt(String label, Statement stmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.label = label;
        this.stmt = stmt;
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
