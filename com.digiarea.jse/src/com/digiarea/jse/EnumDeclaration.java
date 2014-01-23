package com.digiarea.jse;

import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class EnumDeclaration extends TypeDeclaration {

    private NodeList<ClassOrInterfaceType> implementsList;

    private NodeList<EnumConstantDeclaration> entries;

    public NodeList<ClassOrInterfaceType> getImplementsList() {
        return implementsList;
    }

    public void setImplementsList(NodeList<ClassOrInterfaceType> implementsList) {
        this.implementsList = implementsList;
    }

    public NodeList<EnumConstantDeclaration> getEntries() {
        return entries;
    }

    public void setEntries(NodeList<EnumConstantDeclaration> entries) {
        this.entries = entries;
    }

    public EnumDeclaration() {
        super();
    }

    public EnumDeclaration(NodeList<ClassOrInterfaceType> implementsList, NodeList<EnumConstantDeclaration> entries, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
        this.implementsList = implementsList;
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
