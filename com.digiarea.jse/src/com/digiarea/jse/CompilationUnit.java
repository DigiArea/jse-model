/*******************************************************************************
 * Copyright (c) 2011 - 2014 DigiArea, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     DigiArea, Inc. - initial API and implementation
 *******************************************************************************/
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

/** 
 * The Class CompilationUnit.
 */
public final class CompilationUnit extends Node {

    /** 
     * The package declaration.
     */
    private PackageDeclaration packageDeclaration;

    /** 
     * The imports.
     */
    private NodeList<ImportDeclaration> imports;

    /** 
     * The types.
     */
    private NodeList<TypeDeclaration> types;

    /** 
     * The comments.
     */
    private NodeList<Comment> comments;

    /** 
     * The name.
     */
    private String name;

    /**
     * Gets the package declaration.
     *
     * @return the package declaration
     */
    public PackageDeclaration getPackageDeclaration() {
        return packageDeclaration;
    }

    /**
     * Sets the package declaration.
     *
     * @param packageDeclaration the new package declaration
     */
    public void setPackageDeclaration(PackageDeclaration packageDeclaration) {
        this.packageDeclaration = packageDeclaration;
    }

    /**
     * Gets the imports.
     *
     * @return the imports
     */
    public NodeList<ImportDeclaration> getImports() {
        return imports;
    }

    /**
     * Sets the imports.
     *
     * @param imports the new imports
     */
    public void setImports(NodeList<ImportDeclaration> imports) {
        this.imports = imports;
    }

    /**
     * Gets the types.
     *
     * @return the types
     */
    public NodeList<TypeDeclaration> getTypes() {
        return types;
    }

    /**
     * Sets the types.
     *
     * @param types the new types
     */
    public void setTypes(NodeList<TypeDeclaration> types) {
        this.types = types;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public NodeList<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(NodeList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new compilation unit.
     */
    CompilationUnit() {
        super();
    }

    /**
     * Instantiates a new compilation unit.
     *
     * @param packageDeclaration the package declaration
     * @param imports the imports
     * @param types the types
     * @param comments the comments
     * @param name the name
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration> types, NodeList<Comment> comments, String name, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.packageDeclaration = packageDeclaration;
        this.imports = imports;
        this.types = types;
        this.comments = comments;
        this.name = name;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
