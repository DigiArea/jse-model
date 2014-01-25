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
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class InterfaceDeclaration.
 */
public final class InterfaceDeclaration extends TypeDeclaration {

    /** 
     * The type parameters.
     */
    private NodeList<TypeParameter> typeParameters;

    /** 
     * The extends list.
     */
    private NodeList<ClassOrInterfaceType> extendsList;

    /**
     * Gets the type parameters.
     *
     * @return the type parameters
     */
    public NodeList<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    /**
     * Sets the type parameters.
     *
     * @param typeParameters the new type parameters
     */
    public void setTypeParameters(NodeList<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    /**
     * Gets the extends list.
     *
     * @return the extends list
     */
    public NodeList<ClassOrInterfaceType> getExtendsList() {
        return extendsList;
    }

    /**
     * Sets the extends list.
     *
     * @param extendsList the new extends list
     */
    public void setExtendsList(NodeList<ClassOrInterfaceType> extendsList) {
        this.extendsList = extendsList;
    }

    /**
     * Instantiates a new interface declaration.
     */
    InterfaceDeclaration() {
        super();
    }

    /**
     * Instantiates a new interface declaration.
     *
     * @param typeParameters the type parameters
     * @param extendsList the extends list
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    InterfaceDeclaration(NodeList<TypeParameter> typeParameters, NodeList<ClassOrInterfaceType> extendsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
        this.typeParameters = typeParameters;
        this.extendsList = extendsList;
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
