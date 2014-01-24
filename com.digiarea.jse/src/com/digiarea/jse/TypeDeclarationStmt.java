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
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class TypeDeclarationStmt.
 */
public final class TypeDeclarationStmt extends Statement {

    /** 
     * The type declaration.
     */
    private TypeDeclaration typeDeclaration;

    /**
     * Gets the type declaration.
     *
     * @return the type declaration
     */
    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    /**
     * Sets the type declaration.
     *
     * @param typeDeclaration the new type declaration
     */
    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    /**
     * Instantiates a new type declaration stmt.
     */
    TypeDeclarationStmt() {
        super();
    }

    /**
     * Instantiates a new type declaration stmt.
     *
     * @param typeDeclaration the type declaration
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    TypeDeclarationStmt(TypeDeclaration typeDeclaration, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.typeDeclaration = typeDeclaration;
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
