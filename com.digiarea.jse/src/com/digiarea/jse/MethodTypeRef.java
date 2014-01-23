package com.digiarea.jse;

import com.digiarea.jse.MethodRef;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public class MethodTypeRef extends MethodRef {

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public MethodTypeRef() {
        super();
    }

    public MethodTypeRef(Type type, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, name, annotations, posBegin, posEnd);
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
