package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class VariableDeclarator.
 */
public final class VariableDeclarator extends Node {

    /** 
     * The id.
     */
    private VariableDeclaratorId id;

    /** 
     * The init.
     */
    private Expression init;

    public VariableDeclaratorId getId() {
        return id;
    }

    public void setId(VariableDeclaratorId id) {
        this.id = id;
    }

    public Expression getInit() {
        return init;
    }

    public void setInit(Expression init) {
        this.init = init;
    }

    VariableDeclarator() {
        super();
    }

    VariableDeclarator(VariableDeclaratorId id, Expression init, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.id = id;
        this.init = init;
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
