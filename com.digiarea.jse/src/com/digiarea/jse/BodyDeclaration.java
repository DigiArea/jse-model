package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class BodyDeclaration.
 */
public abstract class BodyDeclaration extends Node {

    /** 
     * The java doc.
     */
    private JavadocComment javaDoc;

    public JavadocComment getJavaDoc() {
        return javaDoc;
    }

    public void setJavaDoc(JavadocComment javaDoc) {
        this.javaDoc = javaDoc;
    }

    BodyDeclaration() {
        super();
    }

    BodyDeclaration(JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.javaDoc = javaDoc;
    }

}
