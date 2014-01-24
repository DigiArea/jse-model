package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

/** 
 * The Class Expression.
 */
public abstract class Expression extends Node {

    Expression() {
        super();
    }

    Expression(NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
    }

}
