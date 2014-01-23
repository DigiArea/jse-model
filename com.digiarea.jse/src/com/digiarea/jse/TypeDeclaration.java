package com.digiarea.jse;

import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;

public abstract class TypeDeclaration extends BodyDeclaration {

    private Modifiers modifiers;

    private String name;

    private NodeList<BodyDeclaration> members;

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeList<BodyDeclaration> getMembers() {
        return members;
    }

    public void setMembers(NodeList<BodyDeclaration> members) {
        this.members = members;
    }

    public TypeDeclaration() {
        super();
    }

    public TypeDeclaration(Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.name = name;
        this.members = members;
    }

}
