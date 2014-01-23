package com.digiarea.jse;

import com.digiarea.jse.Statement;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.CatchClause;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class TryStmt extends Statement {

    private NodeList<VariableDeclarationExpr> resources;

    private BlockStmt tryBlock;

    private NodeList<CatchClause> catchClauses;

    private BlockStmt finallyBlock;

    public NodeList<VariableDeclarationExpr> getResources() {
        return resources;
    }

    public void setResources(NodeList<VariableDeclarationExpr> resources) {
        this.resources = resources;
    }

    public BlockStmt getTryBlock() {
        return tryBlock;
    }

    public void setTryBlock(BlockStmt tryBlock) {
        this.tryBlock = tryBlock;
    }

    public NodeList<CatchClause> getCatchClauses() {
        return catchClauses;
    }

    public void setCatchClauses(NodeList<CatchClause> catchClauses) {
        this.catchClauses = catchClauses;
    }

    public BlockStmt getFinallyBlock() {
        return finallyBlock;
    }

    public void setFinallyBlock(BlockStmt finallyBlock) {
        this.finallyBlock = finallyBlock;
    }

    public TryStmt() {
        super();
    }

    public TryStmt(NodeList<VariableDeclarationExpr> resources, BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.resources = resources;
        this.tryBlock = tryBlock;
        this.catchClauses = catchClauses;
        this.finallyBlock = finallyBlock;
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
