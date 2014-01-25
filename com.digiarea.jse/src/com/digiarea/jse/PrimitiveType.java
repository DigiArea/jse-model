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
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class PrimitiveType.
 */
public final class PrimitiveType extends Type {

    /** 
     * The Enum Primitive.
     */
    public enum Primitive {

        /** The Boolean.
         */
        Boolean("boolean"), 
        /** The Char.
         */
        Char("char"), 
        /** The Byte.
         */
        Byte("byte"), 
        /** The Short.
         */
        Short("short"), 
        /** The Int.
         */
        Int("int"), 
        /** The Long.
         */
        Long("long"), 
        /** The Float.
         */
        Float("float"), 
        /** The Double.
         */
        Double("double");

        /** 
         * The string.
         */
        private String string;

        /** 
         * Instantiates a new primitive.
         *
         * @param string the string
         */
        private Primitive(String string) {
            this.string = string;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return string;
        }

        /** 
         * Gets the string.
         *
         * @return the string
         */
        public String getString() {
            return string;
        }

        /**
         * Accept.
         *
         * @param <C> the generic type
         * @param v the v
         * @param ctx the ctx
         * @throws Exception the exception
         */
        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        /**
         * Accept.
         *
         * @param <R> the generic type
         * @param <C> the generic type
         * @param v the v
         * @param ctx the ctx
         * @return the r
         * @throws Exception the exception
         */
        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    /** 
     * The type.
     */
    private PrimitiveType.Primitive type;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public PrimitiveType.Primitive getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(PrimitiveType.Primitive type) {
        this.type = type;
    }

    /**
     * Instantiates a new primitive type.
     */
    PrimitiveType() {
        super();
    }

    /**
     * Instantiates a new primitive type.
     *
     * @param type the type
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    PrimitiveType(PrimitiveType.Primitive type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.type = type;
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
