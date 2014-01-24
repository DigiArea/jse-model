package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.Expression;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class SwitchEntryStmt.
 */
public final class SwitchEntryStmt extends Statement {

    /** 
     * The label.
     */
    private Expression label;

    /** 
     * The stmts.
     */
    private NodeList<Statement> stmts;

    public Expression getLabel() {
        return label;
    }

    public void setLabel(Expression label) {
        this.label = label;
    }

    public NodeList<Statement> getStmts() {
        return stmts;
    }

    public void setStmts(NodeList<Statement> stmts) {
        this.stmts = stmts;
    }

    SwitchEntryStmt() {
        super();
    }

    SwitchEntryStmt(Expression label, NodeList<Statement> stmts, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.label = label;
        this.stmts = stmts;
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
