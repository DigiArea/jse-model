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

import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class MethodCallExpr extends Expression {

    private Expression scope;

    private NodeList<Type> typeArgs;

    private String name;

    private NodeList<Expression> args;

    public Expression getScope() {
        return scope;
    }

    public void setScope(Expression scope) {
        this.scope = scope;
    }

    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeList<Expression> getArgs() {
        return args;
    }

    public void setArgs(NodeList<Expression> args) {
        this.args = args;
    }

    public MethodCallExpr() {
        super();
    }

    public MethodCallExpr(Expression scope, NodeList<Type> typeArgs, String name, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.typeArgs = typeArgs;
        this.name = name;
        this.args = args;
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
