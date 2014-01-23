package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

public abstract class Comment extends Node {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment() {
        super();
    }

    public Comment(String content, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.content = content;
    }

}
