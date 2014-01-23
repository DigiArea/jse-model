package com.digiarea.jse;

import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class InterfaceDeclaration extends TypeDeclaration {

    private NodeList<TypeParameter> typeParameters;

    private NodeList<ClassOrInterfaceType> extendsList;

    public NodeList<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(NodeList<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    public NodeList<ClassOrInterfaceType> getExtendsList() {
        return extendsList;
    }

    public void setExtendsList(NodeList<ClassOrInterfaceType> extendsList) {
        this.extendsList = extendsList;
    }

    public InterfaceDeclaration() {
        super();
    }

    public InterfaceDeclaration(NodeList<TypeParameter> typeParameters, NodeList<ClassOrInterfaceType> extendsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
        this.typeParameters = typeParameters;
        this.extendsList = extendsList;
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
