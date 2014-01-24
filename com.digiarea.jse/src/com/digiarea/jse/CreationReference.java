package com.digiarea.jse;

import com.digiarea.jse.MethodReference;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Creation reference expression node type (added in JLS8 API).
 *
 * <pre>
 * CreationReference:
 *     Type <b>::</b>
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         <b>new</b>
 * </pre>
 *
 * @since 3.3
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CreationReference extends MethodReference {

    /** 
     * The type; defaults to an unspecified type.
     */
    private Type type = null;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    CreationReference() {
        super();
    }

    CreationReference(Type type, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, annotations, posBegin, posEnd);
        this.type = type;
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
