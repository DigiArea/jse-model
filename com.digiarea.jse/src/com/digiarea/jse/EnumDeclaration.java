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

import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class EnumDeclaration.
 */
public final class EnumDeclaration extends TypeDeclaration {

    /** 
     * The implements list.
     */
    private NodeList<ClassOrInterfaceType> implementsList;

    /** 
     * The entries.
     */
    private NodeList<EnumConstantDeclaration> entries;

    /**
     * Gets the implements list.
     *
     * @return the implements list
     */
    public NodeList<ClassOrInterfaceType> getImplementsList() {
        return implementsList;
    }

    /**
     * Sets the implements list.
     *
     * @param implementsList the new implements list
     */
    public void setImplementsList(NodeList<ClassOrInterfaceType> implementsList) {
        this.implementsList = implementsList;
    }

    /**
     * Gets the entries.
     *
     * @return the entries
     */
    public NodeList<EnumConstantDeclaration> getEntries() {
        return entries;
    }

    /**
     * Sets the entries.
     *
     * @param entries the new entries
     */
    public void setEntries(NodeList<EnumConstantDeclaration> entries) {
        this.entries = entries;
    }

    /**
     * Instantiates a new enum declaration.
     */
    EnumDeclaration() {
        super();
    }

    /**
     * Instantiates a new enum declaration.
     *
     * @param implementsList the implements list
     * @param entries the entries
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    EnumDeclaration(NodeList<ClassOrInterfaceType> implementsList, NodeList<EnumConstantDeclaration> entries, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
        this.implementsList = implementsList;
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
