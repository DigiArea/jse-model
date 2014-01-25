package com.digiarea.jse.arrow;

import com.digiarea.common.Arrow;
import com.digiarea.jse.Project;
import com.digiarea.jse.io.Output;

public class Printer extends Output implements Arrow<Project, Project> {

	public Printer(String encoding, String path) {
		super(encoding, path, null);
	}

	@Override
	public Project arrow(Project project) throws Exception {
		setProject(project);
		run();
		return project;
	}

}
