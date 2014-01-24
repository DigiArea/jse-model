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
package com.digiarea.jse.io;

import java.io.File;
import java.io.FileOutputStream;

import com.digiarea.common.utils.FileExtensions;
import com.digiarea.common.utils.SourcePrinter;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.Project;
import com.digiarea.jse.visitor.PrinterVisitor;

public class Output implements Runnable {

	private static final String ENCODING = "UTF-8";

	private static final String EXT = FileExtensions.JAVA.END;

	private String encoding = null;
	private String path = null;
	private Project project = null;

	public Output() {
		super();
	}

	public Output(String encoding, String path, Project project) {
		super();
		if (encoding == null) {
			this.encoding = ENCODING;
		} else {
			this.encoding = encoding;
		}
		this.path = path;
		this.project = project;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public void run() {
		NodeList<CompilationUnit> compilationUnits = project
				.getCompilationUnits();
		if (compilationUnits != null) {
			for (CompilationUnit unit : compilationUnits) {
				// System.out.println("OUT: " + getName(unit));
				String dir = getPath(path, getName(unit));
				File distFile = new File(dir);
				distFile.getParentFile().mkdirs();
				try (FileOutputStream out = new FileOutputStream(distFile)) {
					unit.accept(new PrinterVisitor(), new SourcePrinter(out,
							encoding));
				} catch (Exception e) {
					throw new Error(e.getMessage());
				}
			}
		}
	}

	private String getName(CompilationUnit unit) {
		return unit.getName();
	}

	private String getPath(String path, String name) {
		return path + createPathFromQName(name) + "." + EXT;
	}

	private static String createPathFromQName(String name) {
		String[] split = name.split("\\.");
		String ret = File.separatorChar + split[0];
		for (int i = 1; i < split.length; i++) {
			ret += File.separatorChar + split[i];
		}
		return ret;
	}

}
