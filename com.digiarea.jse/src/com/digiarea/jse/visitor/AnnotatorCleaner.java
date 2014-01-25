package com.digiarea.jse.visitor;

import java.util.ArrayList;
import java.util.List;

import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.Node;
import com.digiarea.jse.NodeFacade;

public class AnnotatorCleaner extends AnnotatorVisitor<List<String>> {

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
