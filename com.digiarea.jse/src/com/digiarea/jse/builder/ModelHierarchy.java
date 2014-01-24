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

import java.util.List;
import java.util.Map;

import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.Project;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.builder.ModelBuilder.BuilderType;

/**
 * The Interface ModelHierarchy.
 */
public interface ModelHierarchy {

	/**
	 * Update.
	 *
	 * @throws Exception the exception
	 */
	public void update() throws Exception;

	/**
	 * Adds the model visitor.
	 *
	 * @param visitor the visitor
	 * @return true, if successful
	 */
	public boolean addModelVisitor(ModelVisitor visitor);

	/**
	 * Removes the model visitor.
	 *
	 * @param visitor the visitor
	 * @return true, if successful
	 */
	public boolean removeModelVisitor(ModelVisitor visitor);

	/**
	 * Clear model visitors.
	 */
	public void clearModelVisitors();

	/**
	 * Updater exists.
	 *
	 * @param qualifiedName the qualified name
	 * @return true, if successful
	 */
	public boolean updaterExists(String qualifiedName);

	/**
	 * Gets the updater.
	 *
	 * @param qualifiedName the qualified name
	 * @return the updater
	 */
	public ModelUpdater getUpdater(String qualifiedName);

	/**
	 * Gets the updaters.
	 *
	 * @return the updaters
	 */
	public Map<String, ModelUpdater> getUpdaters();

	/**
	 * Builder exists.
	 *
	 * @param qualifiedName the qualified name
	 * @return true, if successful
	 */
	public boolean builderExists(String qualifiedName);

	/**
	 * Gets the builder.
	 *
	 * @param qualifiedName the qualified name
	 * @return the builder
	 */
	public ModelBuilder getBuilder(String qualifiedName);

	/**
	 * Removes the builder.
	 *
	 * @param qualifiedName the qualified name
	 * @return the type declaration
	 */
	public TypeDeclaration removeBuilder(String qualifiedName);

	/**
	 * Gets the builder.
	 *
	 * @param qualifiedName the qualified name
	 * @param builderType the builder type
	 * @return the builder
	 */
	public ModelBuilder getBuilder(String qualifiedName, BuilderType builderType);

	/**
	 * Put builder.
	 *
	 * @param qualifiedName the qualified name
	 * @param value the value
	 * @return the model builder
	 */
	public ModelBuilder putBuilder(String qualifiedName, ModelBuilder value);

	/**
	 * Gets the builders.
	 *
	 * @return the builders
	 */
	public Project getBuilders();

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject();

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Project getResult();

	/**
	 * Process.
	 *
	 * @throws Exception the exception
	 */
	public void process() throws Exception;

	/**
	 * Gets the sub types.
	 *
	 * @param u the u
	 * @return the sub types
	 */
	public List<ClassOrInterfaceType> getSubTypes(ModelUpdater u);

	/**
	 * Prints the updaters.
	 */
	public void printUpdaters();

	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public ModelUpdater getRoot();

}