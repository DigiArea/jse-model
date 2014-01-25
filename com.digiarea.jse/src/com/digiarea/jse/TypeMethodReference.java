package com.digiarea.jse;

import com.digiarea.jse.MethodReference;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Type method reference expression node type (added in JLS8 API).
 *
 * <pre>
 * TypeMethodReference:
 *     Type <b>::</b>
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         Identifier
 * </pre>
 *
 * @since 3.3
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class TypeMethodReference extends MethodReference {

    /** 
     * The type; defaults to null.
     */
    private Type type = null;

    /** 
     * The method name; defaults to an unspecified, legal Java method name.
     */
    private String methodName = null;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    TypeMethodReference() {
        super();
    }

    TypeMethodReference(Type type, String methodName, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, annotations, posBegin, posEnd);
        this.type = type;
        this.methodName = methodName;
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
