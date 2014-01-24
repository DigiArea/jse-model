package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class TypeDeclarationStmt.
 */
public final class TypeDeclarationStmt extends Statement {

    /** 
     * The type declaration.
     */
    private TypeDeclaration typeDeclaration;

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    TypeDeclarationStmt() {
        super();
    }

    TypeDeclarationStmt(TypeDeclaration typeDeclaration, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.typeDeclaration = typeDeclaration;
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
