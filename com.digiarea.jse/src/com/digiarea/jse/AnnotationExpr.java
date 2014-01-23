package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;

public abstract class AnnotationExpr extends Expression {

    private NameExpr name;

    public NameExpr getName() {
        return name;
    }

    public void setName(NameExpr name) {
        this.name = name;
    }

    public AnnotationExpr() {
        super();
    }

    public AnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
    }

}
