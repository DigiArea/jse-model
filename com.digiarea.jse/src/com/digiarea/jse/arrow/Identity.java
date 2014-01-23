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

import com.digiarea.common.Arrow;
import com.digiarea.jse.Project;
import com.digiarea.jse.visitor.CloneVisitor;

public class Identity extends CloneVisitor<Context> implements
		Arrow<Project, Project> {

	@Override
	public Project arrow(Project input) throws Exception {
		return (Project) input.accept(this, new Context(new Project()));
	}

}
