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
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.digiarea.common.utils.FileExtensions;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.Project;
import com.digiarea.jse.parser.ASTParser;

/**
 * The Class Input.
 */
public class Input implements Runnable {

	/**
	 * The Constant ENCODING.
	 */
	private static final String ENCODING = "UTF-8";

	/**
	 * The Constant EXT.
	 */
	private static final String EXT = FileExtensions.JAVA.END;

	/**
	 * The encoding.
	 */
	private String encoding = null;

	/**
	 * The recursive.
	 */
	private boolean recursive = false;

	/**
	 * The path.
	 */
	private String path = null;

	/**
	 * The project.
	 */
	private Project project = null;

	/**
	 * Instantiates a new input.
	 */
	public Input() {
		super();
	}

	/**
	 * Instantiates a new input.
	 * 
	 * @param encoding
	 *            the encoding
	 * @param recursive
	 *            the recursive
	 * @param path
	 *            the path
	 * @param project
	 *            the project
	 */
	public Input(String encoding, boolean recursive, String path,
			Project project) {
		super();
		if (encoding == null) {
			this.encoding = ENCODING;
		} else {
			this.encoding = encoding;
		}
		this.recursive = recursive;
		this.path = path;
		this.project = project;
	}

	/**
	 * Gets the encoding.
	 * 
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Sets the encoding.
	 * 
	 * @param encoding
	 *            the new encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Checks if is recursive.
	 * 
	 * @return true, if is recursive
	 */
	public boolean isRecursive() {
		return recursive;
	}

	/**
	 * Sets the recursive.
	 * 
	 * @param recursive
	 *            the new recursive
	 */
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 * 
	 * @param path
	 *            the new path
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * Sets the project.
	 * 
	 * @param project
	 *            the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		final List<CompilationUnit> compilationUnits = new ArrayList<>();
		final Path p = Paths.get(path);
		final FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				if (recursive || dir.equals(p)) {
					return FileVisitResult.CONTINUE;
				} else {
					return FileVisitResult.SKIP_SUBTREE;
				}
			}

			@Override
			public FileVisitResult visitFile(Path item,
					BasicFileAttributes attrs) throws IOException {
				File file = item.toFile();
				if (item.toString().endsWith(EXT)) {
					// System.out.println(item);
					try (FileInputStream stream = new FileInputStream(file)) {
						compilationUnits.add(new ASTParser(stream, encoding)
								.CompilationUnit(getUnitName(file)));
						stream.close();
					} catch (Exception e) {
						throw new Error(e.getMessage());
					}
				}
				return FileVisitResult.CONTINUE;
			}

		};
		try {
			Files.walkFileTree(p, fv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		project.setCompilationUnits(NodeFacade.NodeList(compilationUnits, null,
				0, 0));
	}

	/**
	 * Gets the unit name.
	 * 
	 * @param file
	 *            the file
	 * @return the unit name
	 */
	private String getUnitName(File file) {
		String fName = file.getName();
		int offset = EXT.length();
		return fName.substring(0, fName.length() - offset);
	}

}
