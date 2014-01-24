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
 * The Class ClassDeclaration.
 */
public final class ClassDeclaration extends TypeDeclaration {

    /** 
     * The type parameters.
     */
    private NodeList<TypeParameter> typeParameters;

    /** 
     * The extends type.
     */
    private ClassOrInterfaceType extendsType;

    /** 
     * The implements list.
     */
    private NodeList<ClassOrInterfaceType> implementsList;

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
     * Gets the extends type.
     *
     * @return the extends type
     */
    public ClassOrInterfaceType getExtendsType() {
        return extendsType;
    }

    /**
     * Sets the extends type.
     *
     * @param extendsType the new extends type
     */
    public void setExtendsType(ClassOrInterfaceType extendsType) {
        this.extendsType = extendsType;
    }

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
     * Instantiates a new class declaration.
     */
    ClassDeclaration() {
        super();
    }

    /**
     * Instantiates a new class declaration.
     *
     * @param typeParameters the type parameters
     * @param extendsType the extends type
     * @param implementsList the implements list
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ClassDeclaration(NodeList<TypeParameter> typeParameters, ClassOrInterfaceType extendsType, NodeList<ClassOrInterfaceType> implementsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
        this.typeParameters = typeParameters;
        this.extendsType = extendsType;
        this.implementsList = implementsList;
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
