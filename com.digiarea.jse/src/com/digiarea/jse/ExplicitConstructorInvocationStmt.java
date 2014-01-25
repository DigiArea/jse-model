package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ExplicitConstructorInvocationStmt.
 */
public final class ExplicitConstructorInvocationStmt extends Statement {

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    /** 
     * The is this.
     */
    private boolean isThis;

    /** 
     * The expression.
     */
    private Expression expression;

    /** 
     * The args.
     */
    private NodeList<Expression> args;

    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    public boolean isThis() {
        return isThis;
    }

    public void setThis(boolean isThis) {
        this.isThis = isThis;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public NodeList<Expression> getArgs() {
        return args;
    }

    public void setArgs(NodeList<Expression> args) {
        this.args = args;
    }

    ExplicitConstructorInvocationStmt() {
        super();
    }

    ExplicitConstructorInvocationStmt(NodeList<Type> typeArgs, boolean isThis, Expression expression, NodeList<Expression> args, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.typeArgs = typeArgs;
        this.isThis = isThis;
        this.expression = expression;
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
