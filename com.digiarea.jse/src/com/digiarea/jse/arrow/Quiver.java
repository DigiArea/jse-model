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

import java.util.ArrayList;
import java.util.List;

import com.digiarea.common.Arrow;
import com.digiarea.jse.Project;

/**
 * The Quiver Composite.
 */
public class Quiver implements Arrow<Project, Project> {

	/** The arrows. */
	private List<Arrow<Project, Project>> arrows = new ArrayList<>();

	/**
	 * Instantiates a new composite.
	 */
	public Quiver() {
		this(null);
	}

	/**
	 * Instantiates a new composite.
	 * 
	 * @param arrows
	 *            the arrows
	 */
	public Quiver(List<Arrow<Project, Project>> arrows) {
		super();
		if (arrows != null) {
			this.arrows.addAll(arrows);
		}
	}

	/* (non-Javadoc)
	 * @see com.digiarea.common.Arrow#arrow(java.lang.Object)
	 */
	@Override
	public Project arrow(Project iProject) throws Exception {
		Project result = iProject;
		for (Arrow<Project, Project> arrow : arrows) {
			result = arrow.arrow(result);
		}
		return result;
	}

	/**
	 * Adds the arrow.
	 *
	 * @param arrow the arrow
	 */
	public void addArrow(Arrow<Project, Project> arrow) {
		arrows.add(arrow);
	}

}
