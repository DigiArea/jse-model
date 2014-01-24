package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class IfStmt.
 */
public final class IfStmt extends Statement {

    /** 
     * The condition.
     */
    private Expression condition;

    /** 
     * The then stmt.
     */
    private Statement thenStmt;

    /** 
     * The else stmt.
     */
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

    IfStmt() {
        super();
    }

    IfStmt(Expression condition, Statement thenStmt, Statement elseStmt, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
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
