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

        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    /** 
     * The type.
     */
    private PrimitiveType.Primitive type;

    public PrimitiveType.Primitive getType() {
        return type;
    }

    public void setType(PrimitiveType.Primitive type) {
        this.type = type;
    }

    PrimitiveType() {
        super();
    }

    PrimitiveType(PrimitiveType.Primitive type, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.type = type;
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
