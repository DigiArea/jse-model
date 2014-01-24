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

/**
 * The Interface ModelVisitor.
 */
public interface ModelVisitor {
	
	/**
	 * The Enum Pattern.
	 */
	public enum Pattern {
		
		/**
		 * The element iterator.
		 */
		ELEMENT_ITERATOR,
		
		/**
		 * The visitor iterator.
		 */
		VISITOR_ITERATOR
	}

	/**
	 * Visit.
	 *
	 * @param u the u
	 * @param ctx the ctx
	 * @throws Exception the exception
	 */
	public void visit(ModelUpdater u, ModelHierarchy ctx) throws Exception;

}
