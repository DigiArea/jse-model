package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.Type;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class VariableDeclarationExpr extends Expression {

    private Modifiers modifiers;

    private Type type;

    private NodeList<VariableDeclarator> vars;

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public NodeList<VariableDeclarator> getVars() {
        return vars;
    }

    public void setVars(NodeList<VariableDeclarator> vars) {
        this.vars = vars;
    }

    public VariableDeclarationExpr() {
        super();
    }

    public VariableDeclarationExpr(Modifiers modifiers, Type type, NodeList<VariableDeclarator> vars, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.type = type;
        this.vars = vars;
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
