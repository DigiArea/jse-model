package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

public abstract class Statement extends Node {

    public Statement() {
        super();
    }

    public Statement(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
    }

}
