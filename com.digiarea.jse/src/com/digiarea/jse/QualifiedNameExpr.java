package com.digiarea.jse;

import com.digiarea.jse.NameExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class QualifiedNameExpr.
 */
public final class QualifiedNameExpr extends NameExpr {

    /** 
     * The qualifier.
     */
    private NameExpr qualifier;

    public NameExpr getQualifier() {
        return qualifier;
    }

    public void setQualifier(NameExpr qualifier) {
        this.qualifier = qualifier;
    }

    QualifiedNameExpr() {
        super();
    }

    QualifiedNameExpr(NameExpr qualifier, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(name, annotations, posBegin, posEnd);
        this.qualifier = qualifier;
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
