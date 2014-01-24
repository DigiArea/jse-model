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
import com.digiarea.jse.Expression;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class EnumConstantDeclaration.
 */
public final class EnumConstantDeclaration extends BodyDeclaration {

    /** 
     * The name.
     */
    private String name;

    /** 
     * The args.
     */
    private NodeList<Expression> args;

    /** 
     * The class body.
     */
    private NodeList<BodyDeclaration> classBody;

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
     * Gets the class body.
     *
     * @return the class body
     */
    public NodeList<BodyDeclaration> getClassBody() {
        return classBody;
    }

    /**
     * Sets the class body.
     *
     * @param classBody the new class body
     */
    public void setClassBody(NodeList<BodyDeclaration> classBody) {
        this.classBody = classBody;
    }

    /**
     * Instantiates a new enum constant declaration.
     */
    EnumConstantDeclaration() {
        super();
    }

    /**
     * Instantiates a new enum constant declaration.
     *
     * @param name the name
     * @param args the args
     * @param classBody the class body
     * @param javaDoc the java doc
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    EnumConstantDeclaration(String name, NodeList<Expression> args, NodeList<BodyDeclaration> classBody, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.name = name;
        this.args = args;
        this.classBody = classBody;
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
