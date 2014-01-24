package com.digiarea.jse;

import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class InitializerDeclaration.
 */
public final class InitializerDeclaration extends BodyDeclaration {

    /** 
     * The is static.
     */
    private boolean isStatic;

    /** 
     * The block.
     */
    private BlockStmt block;

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public BlockStmt getBlock() {
        return block;
    }

    public void setBlock(BlockStmt block) {
        this.block = block;
    }

    InitializerDeclaration() {
        super();
    }

    InitializerDeclaration(boolean isStatic, BlockStmt block, JavadocComment javaDoc, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(javaDoc, annotations, posBegin, posEnd);
        this.isStatic = isStatic;
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
