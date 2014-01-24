package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class Project.
 */
public class Project extends Node {

    /** 
     * The compilation units.
     */
    private NodeList<CompilationUnit> compilationUnits = null;

    public NodeList<CompilationUnit> getCompilationUnits() {
        return compilationUnits;
    }

    public void setCompilationUnits(NodeList<CompilationUnit> compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    Project() {
        super();
    }

    Project(NodeList<CompilationUnit> compilationUnits, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.compilationUnits = compilationUnits;
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
