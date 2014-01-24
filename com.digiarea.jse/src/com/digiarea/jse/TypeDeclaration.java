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
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;

/** 
 * The Class TypeDeclaration.
 */
public abstract class TypeDeclaration extends BodyDeclaration {

    /** 
     * The modifiers.
     */
    private Modifiers modifiers;

    /** 
     * The name.
     */
    private String name;

    /** 
     * The members.
     */
    private NodeList<BodyDeclaration> members;

    /**
     * Gets the modifiers.
     *
     * @return the modifiers
     */
    public Modifiers getModifiers() {
        return modifiers;
    }

    /**
     * Sets the modifiers.
     *
     * @param modifiers the new modifiers
     */
    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
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
     * Gets the members.
     *
     * @return the members
     */
    public NodeList<BodyDeclaration> getMembers() {
        return members;
    }

    /**
     * Sets the members.
     *
     * @param members the new members
     */
    public void setMembers(NodeList<BodyDeclaration> members) {
        this.members = members;
    }

    /**
     * Instantiates a new type declaration.
     */
    TypeDeclaration() {
        super();
    }

    /**
     * Instantiates a new type declaration.
     *
     * @param modifiers the modifiers
     * @param name the name
     * @param members the members
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    TypeDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.name = name;
        this.members = members;
    }

}
