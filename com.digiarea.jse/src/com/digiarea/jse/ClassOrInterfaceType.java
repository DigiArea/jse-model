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

import com.digiarea.jse.Type;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class ClassOrInterfaceType extends Type {

    private ClassOrInterfaceType scope;

    private NameExpr name;

    private NodeList<Type> typeArgs;

    public ClassOrInterfaceType getScope() {
        return scope;
    }

    public void setScope(ClassOrInterfaceType scope) {
        this.scope = scope;
    }

    public NameExpr getName() {
        return name;
    }

    public void setName(NameExpr name) {
        this.name = name;
    }

    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    public ClassOrInterfaceType() {
        super();
    }

    public ClassOrInterfaceType(ClassOrInterfaceType scope, NameExpr name, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.name = name;
        this.typeArgs = typeArgs;
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
