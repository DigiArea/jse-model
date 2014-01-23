package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class ObjectCreationExpr extends Expression {

    private Expression scope;

    private ClassOrInterfaceType type;

    private NodeList<Type> typeArgs;

    private NodeList<Expression> args;

    private NodeList<BodyDeclaration> anonymousClassBody;

    public Expression getScope() {
        return scope;
    }

    public void setScope(Expression scope) {
        this.scope = scope;
    }

    public ClassOrInterfaceType getType() {
        return type;
    }

    public void setType(ClassOrInterfaceType type) {
        this.type = type;
    }

    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    public NodeList<Expression> getArgs() {
        return args;
    }

    public void setArgs(NodeList<Expression> args) {
        this.args = args;
    }

    public NodeList<BodyDeclaration> getAnonymousClassBody() {
        return anonymousClassBody;
    }

    public void setAnonymousClassBody(NodeList<BodyDeclaration> anonymousClassBody) {
        this.anonymousClassBody = anonymousClassBody;
    }

    public ObjectCreationExpr() {
        super();
    }

    public ObjectCreationExpr(Expression scope, ClassOrInterfaceType type, NodeList<Type> typeArgs, NodeList<Expression> args, NodeList<BodyDeclaration> anonymousClassBody, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.type = type;
        this.typeArgs = typeArgs;
        this.args = args;
        this.anonymousClassBody = anonymousClassBody;
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
