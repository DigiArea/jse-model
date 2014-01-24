package com.digiarea.jse;

import com.digiarea.jse.MethodReference;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Super method reference node type (added in JLS8 API).
 *
 * <pre>
 * SuperMethodReference:
 *     [ ClassName <b>.</b> ] <b>super</b> <b>::</b>
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         Identifier
 * </pre>
 *
 * @since 3.3
 * @noinstantiate This class is not intended to be instantiated by clients
 */
public class SuperMethodReference extends MethodReference {

    /** 
     * The optional qualifier; <code>null</code> for none; defaults to none.
     */
    private NameExpr qualifier = null;

    /** 
     * The method name; defaults to a unspecified, legal Java method name.
     */
    private String methodName = null;

    public NameExpr getQualifier() {
        return qualifier;
    }

    public void setQualifier(NameExpr qualifier) {
        this.qualifier = qualifier;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    SuperMethodReference() {
        super();
    }

    SuperMethodReference(NameExpr qualifier, String methodName, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, annotations, posBegin, posEnd);
        this.qualifier = qualifier;
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
