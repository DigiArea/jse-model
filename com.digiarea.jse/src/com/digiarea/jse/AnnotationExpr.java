package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class AnnotationExpr.
 */
public abstract class AnnotationExpr extends Expression {

    /** 
     * The name.
     */
    private NameExpr name;

    public NameExpr getName() {
        return name;
    }

    public void setName(NameExpr name) {
        this.name = name;
    }

    AnnotationExpr() {
        super();
    }

    AnnotationExpr(NameExpr name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.name = name;
    }

}
