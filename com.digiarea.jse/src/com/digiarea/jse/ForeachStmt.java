package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ForeachStmt.
 */
public final class ForeachStmt extends Statement {

    /** 
     * The variable.
     */
    private VariableDeclarationExpr variable;

    /** 
     * The iterable.
     */
    private Expression iterable;

    /** 
     * The body.
     */
    private Statement body;

    public VariableDeclarationExpr getVariable() {
        return variable;
    }

    public void setVariable(VariableDeclarationExpr variable) {
        this.variable = variable;
    }

    public Expression getIterable() {
        return iterable;
    }

    public void setIterable(Expression iterable) {
        this.iterable = iterable;
    }

    public Statement getBody() {
        return body;
    }

    public void setBody(Statement body) {
        this.body = body;
    }

    ForeachStmt() {
        super();
    }

    ForeachStmt(VariableDeclarationExpr variable, Expression iterable, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.variable = variable;
        this.iterable = iterable;
        this.body = body;
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
