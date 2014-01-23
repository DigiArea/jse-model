package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.Expression;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class ForStmt extends Statement {

    private NodeList<Expression> init;

    private Expression compare;

    private NodeList<Expression> update;

    private Statement body;

    public NodeList<Expression> getInit() {
        return init;
    }

    public void setInit(NodeList<Expression> init) {
        this.init = init;
    }

    public Expression getCompare() {
        return compare;
    }

    public void setCompare(Expression compare) {
        this.compare = compare;
    }

    public NodeList<Expression> getUpdate() {
        return update;
    }

    public void setUpdate(NodeList<Expression> update) {
        this.update = update;
    }

    public Statement getBody() {
        return body;
    }

    public void setBody(Statement body) {
        this.body = body;
    }

    public ForStmt() {
        super();
    }

    public ForStmt(NodeList<Expression> init, Expression compare, NodeList<Expression> update, Statement body, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.init = init;
        this.compare = compare;
        this.update = update;
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
