package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;

/** 
 * Abstract base class of all node types that represent a method reference
 * expression (added in JLS8 API).
 *
 * <pre>
 * MethodReference:
 *    CreationReference
 *    ExpressionMethodReference
 *    SuperMethodReference
 *    TypeMethodReference
 * </pre>
 * <p>
 * A method reference that is represented by a simple or qualified name,
 * followed by <code>::</code>, followed by a simple name can be represented as
 * {@link ExpressionMethodReference} or as {@link TypeMethodReference}. The
 * ASTParser currently prefers the first form.
 * </p>
 *
 * @see CreationReference
 * @see ExpressionMethodReference
 * @see SuperMethodReference
 * @see TypeMethodReference
 * @since 3.3
 */
public abstract class MethodReference extends Expression {

    /** 
     * The type args.
     */
    private NodeList<Type> typeArgs;

    public NodeList<Type> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(NodeList<Type> typeArgs) {
        this.typeArgs = typeArgs;
    }

    MethodReference() {
        super();
    }

    MethodReference(NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.typeArgs = typeArgs;
    }

}
