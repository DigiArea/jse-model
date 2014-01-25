package com.digiarea.jse.arrow;

import com.digiarea.common.Arrow;
import com.digiarea.jse.Project;
import com.digiarea.jse.io.Input;

public class Parser extends Input implements Arrow<Project, Project> {

	public Parser(String encoding, boolean recursive, String paths) {
		super(encoding, recursive, paths, null);
	}

	@Override
	public Project arrow(Project project) throws Exception {
		setProject(project);
		run();
		return project;
	}

}
