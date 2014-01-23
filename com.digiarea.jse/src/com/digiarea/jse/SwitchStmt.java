package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.Expression;
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class SwitchStmt extends Statement {

    private Expression selector;

    private NodeList<SwitchEntryStmt> entries;

    public Expression getSelector() {
        return selector;
    }

    public void setSelector(Expression selector) {
        this.selector = selector;
    }

    public NodeList<SwitchEntryStmt> getEntries() {
        return entries;
    }

    public void setEntries(NodeList<SwitchEntryStmt> entries) {
        this.entries = entries;
    }

    public SwitchStmt() {
        super();
    }

    public SwitchStmt(Expression selector, NodeList<SwitchEntryStmt> entries, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.selector = selector;
        this.entries = entries;
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
