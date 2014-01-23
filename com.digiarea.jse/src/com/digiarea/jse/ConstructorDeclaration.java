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
import com.digiarea.jse.Parameter;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class ConstructorDeclaration extends BodyDeclaration {

    private Modifiers modifiers;

    private NodeList<TypeParameter> typeParameters;

    private String name;

    private NodeList<Parameter> parameters;

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

    public ConstructorDeclaration() {
        super();
    }

    public ConstructorDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, String name, NodeList<Parameter> parameters, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.typeParameters = typeParameters;
        this.name = name;
        this.parameters = parameters;
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
