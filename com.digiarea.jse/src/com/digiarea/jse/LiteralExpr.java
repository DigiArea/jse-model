package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class LiteralExpr.
 */
public abstract class LiteralExpr extends Expression {

    LiteralExpr() {
        super();
    }

    LiteralExpr(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
    }

}
