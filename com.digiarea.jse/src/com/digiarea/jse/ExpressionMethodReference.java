package com.digiarea.jse;

import com.digiarea.jse.MethodReference;
import com.digiarea.jse.Expression;
import com.digiarea.jse.Type;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Expression method reference AST node type (added in JLS8 API).
 *
 * <pre>
 * ExpressionMethodReference:
 *     Expression <b>::</b>
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         Identifier
 * </pre>
 *
 * @since 3.3
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class ExpressionMethodReference extends MethodReference {

    /** 
     * The expression; defaults to an unspecified, legal expression.
     */
    private Expression expression = null;

    /** 
     * The method name; defaults to an unspecified, legal Java method name.
     */
    private String methodName = null;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    ExpressionMethodReference() {
        super();
    }

    ExpressionMethodReference(Expression expression, String methodName, NodeList<Type> typeArgs, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(typeArgs, annotations, posBegin, posEnd);
        this.expression = expression;
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
