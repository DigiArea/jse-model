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
import com.digiarea.jse.Parameter;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class MethodDeclaration extends BodyDeclaration {

    private Modifiers modifiers;

    private NodeList<TypeParameter> typeParameters;

    private Type type;

    private String name;

    private NodeList<Parameter> parameters;

    private NodeList<ArraySlot> slots;

    private NodeList<ClassOrInterfaceType> throwsList;

    private BlockStmt block;

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

    public NodeList<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(NodeList<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(NodeList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public NodeList<ArraySlot> getSlots() {
        return slots;
    }

    public void setSlots(NodeList<ArraySlot> slots) {
        this.slots = slots;
    }

    public NodeList<ClassOrInterfaceType> getThrowsList() {
        return throwsList;
    }

    public void setThrowsList(NodeList<ClassOrInterfaceType> throwsList) {
        this.throwsList = throwsList;
    }

    public BlockStmt getBlock() {
        return block;
    }

    public void setBlock(BlockStmt block) {
        this.block = block;
    }

    MethodDeclaration() {
        super();
    }

    MethodDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, Type type, String name, NodeList<Parameter> parameters, NodeList<ArraySlot> slots, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.typeParameters = typeParameters;
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.slots = slots;
        this.throwsList = throwsList;
        this.block = block;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
