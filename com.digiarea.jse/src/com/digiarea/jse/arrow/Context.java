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
package com.digiarea.jse.arrow;

import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.Project;
import com.digiarea.jse.utils.Enclosure;

/**
 * The Class Context.
 */
public class Context {

	/** The project. */
	private Project project;

	/** The unit. */
	private CompilationUnit unit;

	/** The enclosure. */
	private Enclosure enclosure;

	/** The node. */
	private Node node;

	/** The object. */
	private Object object;

	/**
	 * The is name.
	 */
	private boolean isName = false;

	/**
	 * Instantiates a new context.
	 * 
	 * @param project
	 *            the project
	 */
	public Context(Project project) {
		super();
		this.project = project;
	}

	/**
	 * Gets the unit.
	 * 
	 * @return the unit
	 */
	public CompilationUnit getUnit() {
		return unit;
	}

	/**
	 * Sets the unit.
	 * 
	 * @param unit
	 *            the new unit
	 */
	public void setUnit(CompilationUnit unit) {
		this.unit = unit;
	}

	/**
	 * Gets the node.
	 * 
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 * 
	 * @param node
	 *            the new node
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * Gets the project.
	 * 
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Gets the enclosure.
	 * 
	 * @return the enclosure
	 */
	public NameExpr getEnclosure() {
		return enclosure.get();
	}

	/**
	 * Sets the enclosure.
	 * 
	 * @param enclosure
	 *            the new enclosure
	 */
	public void setEnclosure(NameExpr enclosure) {
		this.enclosure = new Enclosure(enclosure);
	}

	/**
	 * Adds the enclosure.
	 * 
	 * @param name
	 *            the name
	 */
	public void addEnclosure(String name) {
		this.enclosure.add(name);
	}

	/**
	 * Cut enclosure.
	 */
	public void cutEnclosure() {
		enclosure.cut();
	}

	/**
	 * Gets the object.
	 * 
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * Sets the object.
	 * 
	 * @param object
	 *            the new object
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * Checks if is name.
	 *
	 * @return true, if is name
	 */
	public boolean isName() {
		return isName;
	}

	/**
	 * Sets the name.
	 *
	 * @param isName the new name
	 */
	public void setName(boolean isName) {
		this.isName = isName;
	}

}
