package com.digiarea.jse;

import com.digiarea.jse.StringLiteralExpr;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class LongLiteralExpr.
 */
public class LongLiteralExpr extends StringLiteralExpr {

    /** 
     * The Constant UNSIGNED_MIN_VALUE.
     */
    private static final String UNSIGNED_MIN_VALUE = "9223372036854775808";

    /** 
     * The Constant MIN_VALUE.
     */
    protected static final String MIN_VALUE = "-" + UNSIGNED_MIN_VALUE + "L";

    LongLiteralExpr() {
        super();
    }

    LongLiteralExpr(String value, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(value, annotations, posBegin, posEnd);
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
