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

public class SimpleModelHierarchy implements ModelHierarchy {

	private class ModelScanner extends VoidVisitorAdapter<Enclosure> {

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
		 * Dirty hack
		 * 
		 * @param annotations
		 * @return
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

		@Override
		public void visit(EnumDeclaration n, Enclosure ctx) throws Exception {
			ctx.add(n.getName());
			NameExpr e = ctx.get();
			updaters.put(e.toString(), new ModelUpdater(e, n,
					SimpleModelHierarchy.this));
			super.visit(n, ctx);
			ctx.cut();
		}

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

		@Override
		public void visit(PackageDeclaration n, Enclosure ctx) throws Exception {
			ctx.set(n.getName());
			super.visit(n, ctx);
		}

		@Override
		public void visit(Project n, Enclosure ctx) throws Exception {
			if (n.getCompilationUnits() != null) {
				for (CompilationUnit cu : n.getCompilationUnits()) {
					super.visit(cu, ctx);
				}
			}
		}

	}

	private Map<String, ModelUpdater> updaters = new LinkedHashMap<String, ModelUpdater>();
	private Map<String, ModelUpdater> external = new HashMap<String, ModelUpdater>();
	private Map<String, ModelBuilder> builders = new HashMap<String, ModelBuilder>();
	private Project project = null;
	private List<ModelVisitor> visitors;
	private ModelUpdater root = null;

	public SimpleModelHierarchy(Project project) throws Exception {
		this(project, null);
	}

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

	public void update() throws Exception {
		// scan the model
		updaters.clear();
		new ModelScanner().visit(project, new Enclosure());
	}

	public boolean addModelVisitor(ModelVisitor visitor) {
		return visitors.add(visitor);
	}

	public boolean removeModelVisitor(ModelVisitor visitor) {
		return visitors.remove(visitor);
	}

	public void clearModelVisitors() {
		visitors.clear();
	}

	public boolean updaterExists(String qualifiedName) {
		return updaters.containsKey(qualifiedName);
	}

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

	public Map<String, ModelUpdater> getUpdaters() {
		return updaters;
	}

	public boolean builderExists(String qualifiedName) {
		return builders.containsKey(qualifiedName);
	}

	public ModelBuilder getBuilder(String qualifiedName) {
		return builders.get(qualifiedName);
	}

	public TypeDeclaration removeBuilder(String qualifiedName) {
		if (builders.containsKey(qualifiedName)) {
			TypeDeclaration type = builders.get(qualifiedName)
					.getTypeDeclaration();
			builders.remove(qualifiedName);
			return type;
		}
		return null;
	}

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

	public ModelBuilder putBuilder(String qualifiedName, ModelBuilder value) {
		return builders.put(qualifiedName, value);
	}

	public Project getBuilders() {
		List<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
		for (Map.Entry<String, ModelBuilder> entry : builders.entrySet()) {
			compilationUnits.add(entry.getValue().build());
		}
		return NodeFacade.Project(compilationUnits);
	}

	public Project getProject() {
		return project;
	}

	public Project getResult() {
		return NodeUtils.mergeProjects(getBuilders(), getProject());
	}

	public final void process() throws Exception {
		// visit nodes
		for (Map.Entry<String, ModelUpdater> entry : updaters.entrySet()) {
			ModelUpdater updater = entry.getValue();
			for (ModelVisitor visitor : visitors) {
				visitor.visit(updater, this);
			}
		}
	}

	public List<ClassOrInterfaceType> getSubTypes(ModelUpdater u) {
		List<ClassOrInterfaceType> values = new ArrayList<ClassOrInterfaceType>();
		getValues(u.getQualifiedName().toString(), values);
		return values;
	}

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

	public void printUpdaters() {
		for (Map.Entry<String, ModelUpdater> entry : updaters.entrySet()) {
			System.out.println(entry.getKey() + " : "
					+ entry.getValue().getExtendsType());
		}
	}

	public ModelUpdater getRoot() {
		return root;
	}

}
