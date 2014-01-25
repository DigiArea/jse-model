package com.digiarea.jse;

import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class ClassDeclaration.
 */
public final class ClassDeclaration extends TypeDeclaration {

    /** 
     * The type parameters.
     */
    private NodeList<TypeParameter> typeParameters;

    /** 
     * The extends type.
     */
    private ClassOrInterfaceType extendsType;

    /** 
     * The implements list.
     */
    private NodeList<ClassOrInterfaceType> implementsList;

    public NodeList<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(NodeList<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    public ClassOrInterfaceType getExtendsType() {
        return extendsType;
    }

    public void setExtendsType(ClassOrInterfaceType extendsType) {
        this.extendsType = extendsType;
    }

    public NodeList<ClassOrInterfaceType> getImplementsList() {
        return implementsList;
    }

    public void setImplementsList(NodeList<ClassOrInterfaceType> implementsList) {
        this.implementsList = implementsList;
    }

    ClassDeclaration() {
        super();
    }

    ClassDeclaration(NodeList<TypeParameter> typeParameters, ClassOrInterfaceType extendsType, NodeList<ClassOrInterfaceType> implementsList, Modifiers modifiers, String name, NodeList<BodyDeclaration> members, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(modifiers, name, members, javaDoc, annotations, posBegin, posEnd);
        this.typeParameters = typeParameters;
        this.extendsType = extendsType;
        this.implementsList = implementsList;
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
