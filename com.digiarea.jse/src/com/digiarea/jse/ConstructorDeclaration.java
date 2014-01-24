package com.digiarea.jse;

import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.Type;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * Constructor declaration node type. A method declaration is the union of a
 * method declaration and a constructor declaration.
 *
 * <pre>
 * ConstructorDeclaration:
 *    [ Javadoc ] { Modifier } [ <b>&lt;</b> TypeParameter { <b>,</b> TypeParameter } <b>&gt;</b> ]
 *        Identifier <b>(</b>
 *            [ ReceiverParameter <b>,</b> ] [ FormalParameter { <b>,</b> FormalParameter } ]
 *        <b>)</b> { Dimension }
 *        [ <b>throws</b> Type { <b>,</b> Type } ]
 *        ( Block | <b>;</b> )
 * </pre>
 * <p>
 * The ReceiverParameter is represented as:
 * <code>Type [ NameExpr <b>.</b> ] <b>this</b></code><br>
 * </p>
 *
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public final class ConstructorDeclaration extends BodyDeclaration {

    /** 
     * The modifiers, defaults to null.
     *
     */
    private Modifiers modifiers;

    /** 
     * The type parameters.
     *
     */
    private NodeList<TypeParameter> typeParameters;

    /** 
     * The constructor name.
     */
    private String name;

    /** 
     * The explicit receiver type, or <code>null</code> if none. Defaults to
     * none.
     *
     * @since 3.3
     */
    private Type receiverType = null;

    /** 
     * Qualifying name of the explicit </code>this</code> parameter, or
     * <code>null</code> if none. Defaults to null.
     *
     * @since 3.3
     */
    private NameExpr receiverQualifier = null;

    /** 
     * The parameter declarations.
     */
    private NodeList<Parameter> parameters;

    /** 
     * The list of thrown exception Types (element type: {@link Type}).
     *
     * @since 3.3
     */
    private NodeList<ClassOrInterfaceType> throwsList;

    /** 
     * The method body, or <code>null</code> if none. Defaults to null.
     */
    private BlockStmt block;

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

    public NodeList<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(NodeList<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(Type receiverType) {
        this.receiverType = receiverType;
    }

    public NameExpr getReceiverQualifier() {
        return receiverQualifier;
    }

    public void setReceiverQualifier(NameExpr receiverQualifier) {
        this.receiverQualifier = receiverQualifier;
    }

    public NodeList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(NodeList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public NodeList<ClassOrInterfaceType> getThrowsList() {
        return throwsList;
    }

    public void setThrowsList(NodeList<ClassOrInterfaceType> throwsList) {
        this.throwsList = throwsList;
    }

    public BlockStmt getBlock() {
        return block;
    }

    public void setBlock(BlockStmt block) {
        this.block = block;
    }

    ConstructorDeclaration() {
        super();
    }

    ConstructorDeclaration(Modifiers modifiers, NodeList<TypeParameter> typeParameters, String name, Type receiverType, NameExpr receiverQualifier, NodeList<Parameter> parameters, NodeList<ClassOrInterfaceType> throwsList, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.modifiers = modifiers;
        this.typeParameters = typeParameters;
        this.name = name;
        this.receiverType = receiverType;
        this.receiverQualifier = receiverQualifier;
        this.parameters = parameters;
        this.throwsList = throwsList;
        this.block = block;
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
