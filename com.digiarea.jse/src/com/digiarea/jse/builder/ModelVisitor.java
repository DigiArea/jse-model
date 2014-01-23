package com.digiarea.jse.builder;

public interface ModelVisitor {
	
	public enum Pattern {
		ELEMENT_ITERATOR,
		VISITOR_ITERATOR
	}

	public void visit(ModelUpdater u, ModelHierarchy ctx) throws Exception;

}
