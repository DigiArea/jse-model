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
package com.digiarea.jse.visitor;

import java.util.ArrayList;
import java.util.List;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeFacade;

/**
 * The Class AnnotatorCleaner.
 */
public class AnnotatorCleaner extends AnnotatorVisitor<List<String>> {

	/* (non-Javadoc)
	 * @see com.digiarea.jse.visitor.AnnotatorVisitor#processAnnotations(com.digiarea.jse.Node, java.lang.Object)
	 */
	@Override
	protected void processAnnotations(Node node, List<String> ctx)
			throws Exception {
		List<AnnotationExpr> annotations = node.getAnnotations();
		if (annotations != null) {
			List<AnnotationExpr> cleaned = new ArrayList<AnnotationExpr>();
			for (AnnotationExpr annotation : annotations) {
				if (!ctx.contains(annotation.getName().toString())) {
					cleaned.add(annotation);
					annotation.accept(this, ctx);
				}
			}
			node.setAnnotations(NodeFacade.NodeList(cleaned));
		}
	}

}
