package com.digiarea.jse;

import com.digiarea.jse.Type;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class WildcardType.
 */
public final class WildcardType extends Type {

    /** 
     * The extends type.
     */
    private ReferenceType extendsType;

    /** 
     * The super type.
     */
    private ReferenceType superType;

    public ReferenceType getExtendsType() {
        return extendsType;
    }

    public void setExtendsType(ReferenceType extendsType) {
        this.extendsType = extendsType;
    }

    public ReferenceType getSuperType() {
        return superType;
    }

    public void setSuperType(ReferenceType superType) {
        this.superType = superType;
    }

    WildcardType() {
        super();
    }

    WildcardType(ReferenceType extendsType, ReferenceType superType, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.extendsType = extendsType;
        this.superType = superType;
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
