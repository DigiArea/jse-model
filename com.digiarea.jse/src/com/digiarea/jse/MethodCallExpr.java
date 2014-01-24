package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class MethodCallExpr.
 */
public final class MethodCallExpr extends Expression {

    /** 
     * The scope.
     */
    private Expression scope;

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    /** 
     * The name.
     */
    private String name;

    /** 
     * The args.
     */
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

    MethodCallExpr() {
        super();
    }

    MethodCallExpr(Expression scope, NodeList<Type> typeArgs, String name, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
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
