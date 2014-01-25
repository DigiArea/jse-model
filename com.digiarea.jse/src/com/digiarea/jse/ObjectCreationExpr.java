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
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ObjectCreationExpr.
 */
public final class ObjectCreationExpr extends Expression {

    /** 
     * The scope.
     */
    private Expression scope;

    /** 
     * The type.
     */
    private ClassOrInterfaceType type;

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    /** 
     * The args.
     */
    private NodeList<Expression> args;

    /** 
     * The anonymous class body.
     */
    private NodeList<BodyDeclaration> anonymousClassBody;

    /**
     * Gets the scope.
     *
     * @return the scope
     */
    public Expression getScope() {
        return scope;
    }

    /**
     * Sets the scope.
     *
     * @param scope the new scope
     */
    public void setScope(Expression scope) {
        this.scope = scope;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public ClassOrInterfaceType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(ClassOrInterfaceType type) {
        this.type = type;
    }

    /**
     * Gets the type args.
     *
     * @return the type args
     */
    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    /**
     * Sets the type args.
     *
     * @param typeArgs the new type args
     */
    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    /**
     * Gets the args.
     *
     * @return the args
     */
    public NodeList<Expression> getArgs() {
        return args;
    }

    /**
     * Sets the args.
     *
     * @param args the new args
     */
    public void setArgs(NodeList<Expression> args) {
        this.args = args;
    }

    /**
     * Gets the anonymous class body.
     *
     * @return the anonymous class body
     */
    public NodeList<BodyDeclaration> getAnonymousClassBody() {
        return anonymousClassBody;
    }

    /**
     * Sets the anonymous class body.
     *
     * @param anonymousClassBody the new anonymous class body
     */
    public void setAnonymousClassBody(NodeList<BodyDeclaration> anonymousClassBody) {
        this.anonymousClassBody = anonymousClassBody;
    }

    /**
     * Instantiates a new object creation expr.
     */
    ObjectCreationExpr() {
        super();
    }

    /**
     * Instantiates a new object creation expr.
     *
     * @param scope the scope
     * @param type the type
     * @param typeArgs the type args
     * @param args the args
     * @param anonymousClassBody the anonymous class body
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    ObjectCreationExpr(Expression scope, ClassOrInterfaceType type, NodeList<Type> typeArgs, NodeList<Expression> args, NodeList<BodyDeclaration> anonymousClassBody, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.type = type;
        this.typeArgs = typeArgs;
        this.args = args;
        this.anonymousClassBody = anonymousClassBody;
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
