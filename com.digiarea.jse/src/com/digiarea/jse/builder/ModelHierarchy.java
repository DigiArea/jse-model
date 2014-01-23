package com.digiarea.jse.builder;

import java.util.List;
import java.util.Map;

import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Project;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.builder.ModelBuilder.BuilderType;

public interface ModelHierarchy {

	public void update() throws Exception;

	public boolean addModelVisitor(ModelVisitor visitor);

	public boolean removeModelVisitor(ModelVisitor visitor);

	public void clearModelVisitors();

	public boolean updaterExists(String qualifiedName);

	public ModelUpdater getUpdater(String qualifiedName);

	public Map<String, ModelUpdater> getUpdaters();

	public boolean builderExists(String qualifiedName);

	public ModelBuilder getBuilder(String qualifiedName);

	public TypeDeclaration removeBuilder(String qualifiedName);

	public ModelBuilder getBuilder(String qualifiedName, BuilderType builderType);

	public ModelBuilder putBuilder(String qualifiedName, ModelBuilder value);

	public Project getBuilders();

	public Project getProject();

	public Project getResult();

	public void process() throws Exception;

	public List<ClassOrInterfaceType> getSubTypes(ModelUpdater u);

	public void printUpdaters();

	public ModelUpdater getRoot();

}