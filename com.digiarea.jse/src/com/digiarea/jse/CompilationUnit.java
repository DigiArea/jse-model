package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.Comment;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

public final class CompilationUnit extends Node {

    private PackageDeclaration packageDeclaration;

    private NodeList<ImportDeclaration> imports;

    private NodeList<TypeDeclaration> types;

    private NodeList<Comment> comments;

    private String name;

    public PackageDeclaration getPackageDeclaration() {
        return packageDeclaration;
    }

    public void setPackageDeclaration(PackageDeclaration packageDeclaration) {
        this.packageDeclaration = packageDeclaration;
    }

    public NodeList<ImportDeclaration> getImports() {
        return imports;
    }

    public void setImports(NodeList<ImportDeclaration> imports) {
        this.imports = imports;
    }

    public NodeList<TypeDeclaration> getTypes() {
        return types;
    }

    public void setTypes(NodeList<TypeDeclaration> types) {
        this.types = types;
    }

    public NodeList<Comment> getComments() {
        return comments;
    }

    public void setComments(NodeList<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompilationUnit() {
        super();
    }

    public CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration> types, NodeList<Comment> comments, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.packageDeclaration = packageDeclaration;
        this.imports = imports;
        this.types = types;
        this.comments = comments;
        this.name = name;
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
