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
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.Type;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Method declaration node type. A method declaration is the union of a method
 * declaration and a constructor declaration.
 *
 * <pre>
 * MethodDeclaration:
 *    [ Javadoc ] { Modifier } [ <b>&lt;</b> TypeParameter { <b>,</b> TypeParameter } <b>&gt;</b> ] ( Type | <b>void</b> )
 *        Identifier <b>(</b>
 *            [ ReceiverParameter <b>,</b> ] [ FormalParameter { <b>,</b> FormalParameter } ]
 *        <b>)</b> { ArraySlot }
 *        [ <b>throws</b> Type { <b>,</b> Type } ]
 *        ( Block | <b>;</b> )
 * </pre>
 * <p>
 * The ReceiverParameter is represented as:
 * <code>Type [ NameExpr <b>.</b> ] <b>this</b></code><br>
 * </p>
 *
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public final class MethodDeclaration extends BodyDeclaration {

    /** 
     * The modifiers, defaults to null.
     *
     */
    private Modifiers modifiers;

    /** 
     * The type parameters.
     *
     */
    private NodeList<TypeParameter> typeParameters;

    /** 
     * The return type.
     */
    private Type type;

    /** 
     * The method name.
     */
    private String name;

    /** 
     * The explicit receiver type, or <code>null</code> if none. Defaults to
     * none.
     *
     * @since 3.3
     */
    private Type receiverType = null;

    /** 
     * Qualifying name of the explicit </code>this</code> parameter, or
     * <code>null</code> if none. Defaults to null.
     *
     * @since 3.3
     */
    private NameExpr receiverQualifier = null;

    /** 
     * The parameter declarations.
     */
    private NodeList<Parameter> parameters;

    /** 
     * List of extra dimensions this node has with optional annotations (element
     * type: {@link ArraySlot}). Null before JLS8. Added in JLS8; defaults to
     * null.
     *
     * @since 3.3
     */
    private NodeList<ArraySlot> slots;

    /** 
     * The list of thrown exception Types (element type: {@link Type}).
     *
     * @since 3.3
     */
    private NodeList<ClassOrInterfaceType> throwsList;

    /** 
     * The method body, or <code>null</code> if none. Defaults to null.
     */
    private BlockStmt block;

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
     * Gets the receiver type.
     *
     * @return the receiver type
     */
    public Type getReceiverType() {
        return receiverType;
    }

    /**
     * Sets the receiver type.
     *
     * @param receiverType the new receiver type
     */
    public void setReceiverType(Type receiverType) {
        this.receiverType = receiverType;
    }

    /**
     * Gets the receiver qualifier.
     *
     * @return the receiver qualifier
     */
    public NameExpr getReceiverQualifier() {
        return receiverQualifier;
    }

    /**
     * Sets the receiver qualifier.
     *
     * @param receiverQualifier the new receiver qualifier
     */
    public void setReceiverQualifier(NameExpr receiverQualifier) {
        this.receiverQualifier = receiverQualifier;
    }

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public NodeList<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters.
     *
     * @param parameters the new parameters
     */
    public void setParameters(NodeList<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets the slots.
     *
     * @return the slots
     */
    public NodeList<ArraySlot> getSlots() {
        return slots;
    }

    /**
     * Sets the slots.
     *
     * @param slots the new slots
     */
    public void setSlots(NodeList<ArraySlot> slots) {
        this.slots = slots;
    }

    /**
     * Gets the throws list.
     *
     * @return the throws list
     */
    public NodeList<ClassOrInterfaceType> getThrowsList() {
        return throwsList;
    }

    /**
     * Sets the throws list.
     *
     * @param throwsList the new throws list
     */
    public void setThrowsList(NodeList<ClassOrInterfaceType> throwsList) {
        this.throwsList = throwsList;
    }

    /**
     * Gets the block.
     *
     * @return the block
     */
    public BlockStmt getBlock() {
        return block;
    }

    /**
     * Sets the block.
     *
     * @param block the new block
     */
    public void setBlock(BlockStmt block) {
        this.block = block;
    }

    /**
     * Instantiates a new method declaration.
     */
    MethodDeclaration() {
        super();
    }

    /**
     * Instantiates a new method declaration.
     *
     * @param modifiers the modifiers
     * @param typeParameters the type parameters
     * @param type the type
     * @param name the name
     * @param receiverType the receiver type
     * @param receiverQualifier the receiver qualifier
     * @param parameters the parameters
     * @param slots the slots
     * @param throwsList the throws list
     * @param block the block
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    MethodDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, Type type, String name, Type receiverType, NameExpr receiverQualifier, NodeList<Parameter> parameters, NodeList<ArraySlot> slots, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.typeParameters = typeParameters;
        this.type = type;
        this.name = name;
        this.receiverType = receiverType;
        this.receiverQualifier = receiverQualifier;
        this.parameters = parameters;
        this.slots = slots;
        this.throwsList = throwsList;
        this.block = block;
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
