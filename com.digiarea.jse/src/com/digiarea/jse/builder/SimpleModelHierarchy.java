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
package com.digiarea.jse.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Project;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.builder.ModelBuilder.BuilderType;
import com.digiarea.jse.utils.Enclosure;
import com.digiarea.jse.utils.NodeUtils;
import com.digiarea.jse.utils.ReflectUtils;
import com.digiarea.jse.visitor.VoidVisitorAdapter;

/**
 * The Class SimpleModelHierarchy.
 */
public class SimpleModelHierarchy implements ModelHierarchy {

	/**
	 * The Class ModelScanner.
	 */
	private class ModelScanner extends VoidVisitorAdapter<Enclosure> {

		/* (non-Javadoc)
		 * @see com.digiarea.jse.visitor.VoidVisitorAdapter#visit(com.digiarea.jse.AnnotationDeclaration, java.lang.Object)
		 */
		@Override
		public void visit(AnnotationDeclaration n, Enclosure ctx)
				throws Exception {
			ctx.add(n.getName());
			NameExpr e = ctx.get();
			updaters.put(e.toString(), new ModelUpdater(e, n,
					SimpleModelHierarchy.this));
			super.visit(n, ctx);
			ctx.cut();
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.visitor.VoidVisitorAdapter#visit(com.digiarea.jse.ClassDeclaration, java.lang.Object)
		 */
		@Override
		public void visit(ClassDeclaration n, Enclosure ctx) throws Exception {
			ctx.add(n.getName());
			NameExpr e = ctx.get();
			ModelUpdater modelUpdater = new ModelUpdater(e, n,
					SimpleModelHierarchy.this);
			String name = e.getName();
			// FIXME dirty hack
			if (isRoot(n.getAnnotations())) {
				root = modelUpdater;
			} else if (!modelUpdater.hasExtends()
					&& !name.contains("NodeFacade")
					&& !name.contains("NodeFactory")
					&& !name.contains("Binding")) {
				// FIXME what if a class like com.dagxp.lmm.jse.ModifierSet
				// exists?
				// we have the situation with com.dagxp.lmm.jse.NodeFacade and
				// com.dagxp.lmm.jse.NodeFacade
				root = modelUpdater;
			}
			updaters.put(e.toString(), modelUpdater);
			super.visit(n, ctx);
			ctx.cut();
		}

		/**
		 * Dirty hack.
		 *
		 * @param annotations the annotations
		 * @return true, if is root
		 */
		private boolean isRoot(List<AnnotationExpr> annotations) {
			if (annotations != null) {
				for (AnnotationExpr annotation : annotations) {
					if ((annotation instanceof NormalAnnotationExpr)
							&& annotation.getName().toString()
									.equals("com.dagxp.amm.model.Bean")) {
						NormalAnnotationExpr a = (NormalAnnotationExpr) annotation;
						List<MemberValuePair> pairs = a.getPairs();
						if (pairs != null) {
							for (MemberValuePair pair : pairs) {
								if (pair.getName().equals("isRoot")
										&& pair.getValue().toString()
												.equals("true")) {
									return true;
								}
							}
						}
					}
				}
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.visitor.VoidVisitorAdapter#visit(com.digiarea.jse.EnumDeclaration, java.lang.Object)
		 */
		@Override
		public void visit(EnumDeclaration n, Enclosure ctx) throws Exception {
			ctx.add(n.getName());
			NameExpr e = ctx.get();
			updaters.put(e.toString(), new ModelUpdater(e, n,
					SimpleModelHierarchy.this));
			super.visit(n, ctx);
			ctx.cut();
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.visitor.VoidVisitorAdapter#visit(com.digiarea.jse.InterfaceDeclaration, java.lang.Object)
		 */
		@Override
		public void visit(InterfaceDeclaration n, Enclosure ctx)
				throws Exception {
			ctx.add(n.getName());
			NameExpr e = ctx.get();
			updaters.put(e.toString(), new ModelUpdater(e, n,
					SimpleModelHierarchy.this));
			super.visit(n, ctx);
			ctx.cut();
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.visitor.VoidVisitorAdapter#visit(com.digiarea.jse.PackageDeclaration, java.lang.Object)
		 */
		@Override
		public void visit(PackageDeclaration n, Enclosure ctx) throws Exception {
			ctx.set(n.getName());
			super.visit(n, ctx);
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.visitor.VoidVisitorAdapter#visit(com.digiarea.jse.Project, java.lang.Object)
		 */
		@Override
		public void visit(Project n, Enclosure ctx) throws Exception {
			if (n.getCompilationUnits() != null) {
				for (CompilationUnit cu : n.getCompilationUnits()) {
					super.visit(cu, ctx);
				}
			}
		}

	}

	/**
	 * The updaters.
	 */
	private Map<String, ModelUpdater> updaters = new LinkedHashMap<String, ModelUpdater>();
	
	/**
	 * The external.
	 */
	private Map<String, ModelUpdater> external = new HashMap<String, ModelUpdater>();
	
	/**
	 * The builders.
	 */
	private Map<String, ModelBuilder> builders = new HashMap<String, ModelBuilder>();
	
	/**
	 * The project.
	 */
	private Project project = null;
	
	/**
	 * The visitors.
	 */
	private List<ModelVisitor> visitors;
	
	/**
	 * The root.
	 */
	private ModelUpdater root = null;

	/**
	 * Instantiates a new simple model hierarchy.
	 *
	 * @param project the project
	 * @throws Exception the exception
	 */
	public SimpleModelHierarchy(Project project) throws Exception {
		this(project, null);
	}

	/**
	 * Instantiates a new simple model hierarchy.
	 *
	 * @param project the project
	 * @param visitors the visitors
	 * @throws Exception the exception
	 */
	public SimpleModelHierarchy(Project project, List<ModelVisitor> visitors)
			throws Exception {
		super();
		this.project = project;
		if (visitors != null) {
			this.visitors = visitors;
		} else {
			this.visitors = new ArrayList<ModelVisitor>();
		}
		// scan the model
		new ModelScanner().visit(project, new Enclosure());
		// printUpdaters();
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#update()
	 */
	@Override
	public void update() throws Exception {
		// scan the model
		updaters.clear();
		new ModelScanner().visit(project, new Enclosure());
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#addModelVisitor(com.digiarea.jse.builder.ModelVisitor)
	 */
	@Override
	public boolean addModelVisitor(ModelVisitor visitor) {
		return visitors.add(visitor);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#removeModelVisitor(com.digiarea.jse.builder.ModelVisitor)
	 */
	@Override
	public boolean removeModelVisitor(ModelVisitor visitor) {
		return visitors.remove(visitor);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#clearModelVisitors()
	 */
	@Override
	public void clearModelVisitors() {
		visitors.clear();
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#updaterExists(java.lang.String)
	 */
	@Override
	public boolean updaterExists(String qualifiedName) {
		return updaters.containsKey(qualifiedName);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getUpdater(java.lang.String)
	 */
	@Override
	public ModelUpdater getUpdater(String qualifiedName) {
		if (updaters.containsKey(qualifiedName)) {
			return updaters.get(qualifiedName);
		} else if (external.containsKey(qualifiedName)) {
			return external.get(qualifiedName);
		} else {
			ModelUpdater updater = null;
			try {
				updater = new ModelUpdater(
						ReflectUtils.makeCompilationUnit(qualifiedName), this);
				external.put(updater.getQualifiedName().toString(), updater);
				return updater;
			} catch (Exception e) {
				return null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getUpdaters()
	 */
	@Override
	public Map<String, ModelUpdater> getUpdaters() {
		return updaters;
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#builderExists(java.lang.String)
	 */
	@Override
	public boolean builderExists(String qualifiedName) {
		return builders.containsKey(qualifiedName);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getBuilder(java.lang.String)
	 */
	@Override
	public ModelBuilder getBuilder(String qualifiedName) {
		return builders.get(qualifiedName);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#removeBuilder(java.lang.String)
	 */
	@Override
	public TypeDeclaration removeBuilder(String qualifiedName) {
		if (builders.containsKey(qualifiedName)) {
			TypeDeclaration type = builders.get(qualifiedName)
					.getTypeDeclaration();
			builders.remove(qualifiedName);
			return type;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getBuilder(java.lang.String, com.digiarea.jse.builder.ModelBuilder.BuilderType)
	 */
	@Override
	public ModelBuilder getBuilder(String qualifiedName, BuilderType builderType) {
		if (builders.containsKey(qualifiedName)) {
			return getBuilder(qualifiedName);
		} else {
			ModelBuilder builder = ModelBuilder.getBuilder(qualifiedName,
					builderType);
			putBuilder(qualifiedName, builder);
			return builder;
		}
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#putBuilder(java.lang.String, com.digiarea.jse.builder.ModelBuilder)
	 */
	@Override
	public ModelBuilder putBuilder(String qualifiedName, ModelBuilder value) {
		return builders.put(qualifiedName, value);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getBuilders()
	 */
	@Override
	public Project getBuilders() {
		List<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
		for (Map.Entry<String, ModelBuilder> entry : builders.entrySet()) {
			compilationUnits.add(entry.getValue().build());
		}
		return NodeFacade.Project(compilationUnits);
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getProject()
	 */
	@Override
	public Project getProject() {
		return project;
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getResult()
	 */
	@Override
	public Project getResult() {
		return NodeUtils.mergeProjects(getBuilders(), getProject());
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#process()
	 */
	@Override
	public final void process() throws Exception {
		// visit nodes
		for (Map.Entry<String, ModelUpdater> entry : updaters.entrySet()) {
			ModelUpdater updater = entry.getValue();
			for (ModelVisitor visitor : visitors) {
				visitor.visit(updater, this);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getSubTypes(com.digiarea.jse.builder.ModelUpdater)
	 */
	@Override
	public List<ClassOrInterfaceType> getSubTypes(ModelUpdater u) {
		List<ClassOrInterfaceType> values = new ArrayList<ClassOrInterfaceType>();
		getValues(u.getQualifiedName().toString(), values);
		return values;
	}

	/**
	 * Gets the values.
	 *
	 * @param rootQName the root q name
	 * @param values the values
	 * @return the values
	 */
	private void getValues(String rootQName, List<ClassOrInterfaceType> values) {
		for (Map.Entry<String, ModelUpdater> entry : updaters.entrySet()) {
			ModelUpdater updater = entry.getValue();
			if (updater.hasExtends()
					&& rootQName.equals(updater.getExtendsType().toString())) {
				values.add(NodeFacade.ClassOrInterfaceType(updater
						.getQualifiedName()));
				if (!updater.isAbstract()) {
					getValues(updater.getQualifiedName().toString(), values);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#printUpdaters()
	 */
	@Override
	public void printUpdaters() {
		for (Map.Entry<String, ModelUpdater> entry : updaters.entrySet()) {
			System.out.println(entry.getKey() + " : "
					+ entry.getValue().getExtendsType());
		}
	}

	/* (non-Javadoc)
	 * @see com.digiarea.jse.builder.ModelHierarchy#getRoot()
	 */
	@Override
	public ModelUpdater getRoot() {
		return root;
	}

}
