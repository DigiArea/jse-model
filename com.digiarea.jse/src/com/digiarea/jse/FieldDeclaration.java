package com.digiarea.jse;

import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.Type;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class FieldDeclaration extends BodyDeclaration {

    private Modifiers modifiers;

    private Type type;

    private NodeList<VariableDeclarator> variables;

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

    public NodeList<VariableDeclarator> getVariables() {
        return variables;
    }

    public void setVariables(NodeList<VariableDeclarator> variables) {
        this.variables = variables;
    }

    public FieldDeclaration() {
        super();
    }

    public FieldDeclaration(Modifiers modifiers, Type type, NodeList<VariableDeclarator> variables, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.type = type;
        this.variables = variables;
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
