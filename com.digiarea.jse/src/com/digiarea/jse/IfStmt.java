package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class IfStmt extends Statement {

    private Expression condition;

    private Statement thenStmt;

    private Statement elseStmt;

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Statement getThenStmt() {
        return thenStmt;
    }

    public void setThenStmt(Statement thenStmt) {
        this.thenStmt = thenStmt;
    }

    public Statement getElseStmt() {
        return elseStmt;
    }

    public void setElseStmt(Statement elseStmt) {
        this.elseStmt = elseStmt;
    }

    public IfStmt() {
        super();
    }

    public IfStmt(Expression condition, Statement thenStmt, Statement elseStmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
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
