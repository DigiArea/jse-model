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
import com.digiarea.jse.Type;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class FieldDeclaration.
 */
public final class FieldDeclaration extends BodyDeclaration {

    /** 
     * The modifiers.
     */
    private Modifiers modifiers;

    /** 
     * The type.
     */
    private Type type;

    /** 
     * The variables.
     */
    private NodeList<VariableDeclarator> variables;

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
     * Gets the type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the variables.
     *
     * @return the variables
     */
    public NodeList<VariableDeclarator> getVariables() {
        return variables;
    }

    /**
     * Sets the variables.
     *
     * @param variables the new variables
     */
    public void setVariables(NodeList<VariableDeclarator> variables) {
        this.variables = variables;
    }

    /**
     * Instantiates a new field declaration.
     */
    FieldDeclaration() {
        super();
    }

    /**
     * Instantiates a new field declaration.
     *
     * @param modifiers the modifiers
     * @param type the type
     * @param variables the variables
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    FieldDeclaration(Modifiers modifiers, Type type, NodeList<VariableDeclarator> variables, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.type = type;
        this.variables = variables;
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
