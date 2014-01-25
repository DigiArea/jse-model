package com.digiarea.jse;

import com.digiarea.jse.Expression;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;

/** 
 * A node for a lambda expression.
 *
 * For example:
 *
 * <pre>
 * {@code
 *   ()->{}
 *   (List<String> ls)->ls.size()
 *   (x,y)-> { return x + y; }
 * }
 * </pre>
 *
 * Lambda expressions come in two forms: (i) expression lambdas, whose body is
 * an expression, and (ii) statement lambdas, whose body is a block
 *
 */
public abstract class Lambda extends Expression {

    /** 
     * The parameters.
     */
    private NodeList<Parameter> parameters = null;

    public NodeList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(NodeList<Parameter> parameters) {
        this.parameters = parameters;
    }

    Lambda() {
        super();
    }

    Lambda(NodeList<Parameter> parameters, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.parameters = parameters;
    }

}
