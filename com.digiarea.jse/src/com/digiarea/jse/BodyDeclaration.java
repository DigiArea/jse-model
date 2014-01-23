package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

public abstract class BodyDeclaration extends Node {

    private JavadocComment javaDoc;

    public JavadocComment getJavaDoc() {
        return javaDoc;
    }

    public void setJavaDoc(JavadocComment javaDoc) {
        this.javaDoc = javaDoc;
    }

    public BodyDeclaration() {
        super();
    }

    public BodyDeclaration(JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.javaDoc = javaDoc;
    }

}
