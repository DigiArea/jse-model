package com.digiarea.jse;

import com.digiarea.jse.MethodRef;
import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public class MethodExprRef extends MethodRef {

    private Expression scope;

    public Expression getScope() {
        return scope;
    }

    public void setScope(Expression scope) {
        this.scope = scope;
    }

    public MethodExprRef() {
        super();
    }

    public MethodExprRef(Expression scope, NodeList<Type> typeArgs, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, name, annotations, posBegin, posEnd);
        this.scope = scope;
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
