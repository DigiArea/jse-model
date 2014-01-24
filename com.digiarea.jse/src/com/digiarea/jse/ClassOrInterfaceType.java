package com.digiarea.jse;

import com.digiarea.jse.Type;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ClassOrInterfaceType.
 */
public final class ClassOrInterfaceType extends Type {

    /** 
     * The scope.
     */
    private ClassOrInterfaceType scope;

    /** 
     * The name.
     */
    private NameExpr name;

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    public ClassOrInterfaceType getScope() {
        return scope;
    }

    public void setScope(ClassOrInterfaceType scope) {
        this.scope = scope;
    }

    public NameExpr getName() {
        return name;
    }

    public void setName(NameExpr name) {
        this.name = name;
    }

    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    ClassOrInterfaceType() {
        super();
    }

    ClassOrInterfaceType(ClassOrInterfaceType scope, NameExpr name, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.scope = scope;
        this.name = name;
        this.typeArgs = typeArgs;
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
