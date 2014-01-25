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

import java.util.List;

import com.digiarea.jse.MarkerAnnotationExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.SingleMemberAnnotationExpr;

/**
 * Annotator.
 * 
 * <p>
 * This class removes annotations.
 * </p>
 * 
 * 
 */
public class Annotator extends Quiver {

	/**
	 * The Class AnnotatorEngine.
	 */
	private static class AnnotatorEngine extends Identity {

		/** The names. */
		private List<String> annotations;

		/**
		 * Instantiates a new removes the annotations.
		 * 
		 * <p>
		 * If list names is null or empty then all annotations will be removed,
		 * otherwise only annotations from the list will be removed.
		 * </p>
		 * <p>
		 * Fully qualified name must be used as a name in the list.
		 * </p>
		 * 
		 * @param names
		 *            the names
		 */
		public AnnotatorEngine(List<String> names) {
			super();
			this.annotations = names;
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.MarkerAnnotationExpr, com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(MarkerAnnotationExpr n, Context ctx) throws Exception {
			return isRemovable(n.getName().toString()) ? null : super.visit(n,
					ctx);
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.NormalAnnotationExpr, com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(NormalAnnotationExpr n, Context ctx) throws Exception {
			return isRemovable(n.getName().toString()) ? null : super.visit(n,
					ctx);
		}

		/* (non-Javadoc)
		 * @see com.digiarea.jse.arrow.Identity#visit(com.digiarea.jse.SingleMemberAnnotationExpr, com.digiarea.jse.arrow.Context)
		 */
		@Override
		public Node visit(SingleMemberAnnotationExpr n, Context ctx)
				throws Exception {
			return isRemovable(n.getName().toString()) ? null : super.visit(n,
					ctx);
		}

		/**
		 * Checks if is removable.
		 * 
		 * @param name
		 *            the name
		 * @return true, if is removable
		 */
		private boolean isRemovable(String name) {
			return isEmpty() || annotations.contains(name);
		}

		/**
		 * Checks if is empty.
		 * 
		 * @return true, if is empty
		 */
		private boolean isEmpty() {
			return annotations == null || annotations.size() == 0;
		}
	}

	/**
	 * Instantiates a new annotator.
	 */
	public Annotator() {
		this(null);
	}

	/**
	 * Instantiates a new annotator.
	 * 
	 * @param names
	 *            the names
	 */
	public Annotator(List<String> names) {
		super();
		addArrow(new AnnotatorEngine(names));
		addArrow(new Cleaner());
	}
}
