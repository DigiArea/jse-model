/**
 * Copyright (c) 2009-2011 DigiArea, Inc. All rights reserved.
 * 
 * @author norb <norb.beaver@digi-area.com>
 */
package com.digiarea.jse.utils;

import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.QualifiedNameExpr;

/**
 * The Class Enclosure.
 */
public class Enclosure {

	/** The enclosure. */
	private NameExpr enclosure;

	public Enclosure() {
		super();
	}

	/**
	 * Instantiates a new enclosure.
	 * 
	 * @param enclosure
	 *            the enclosure
	 */
	public Enclosure(NameExpr enclosure) {
		super();
		this.enclosure = enclosure;
	}

	/**
	 * Gets the.
	 * 
	 * @return the name expr
	 */
	public NameExpr get() {
		return enclosure;
	}

	/**
	 * Cut.
	 */
	public void cut() {
		if (this.enclosure instanceof QualifiedNameExpr) {
			this.enclosure = ((QualifiedNameExpr) this.enclosure)
					.getQualifier();
		}
	}

	/**
	 * Adds the.
	 * 
	 * @param name
	 *            the name
	 */
	public void add(String name) {
		this.enclosure = NodeFacade.QualifiedNameExpr(this.enclosure, name);
	}

	public void set(NameExpr enclosure) {
		this.enclosure = enclosure;
	}

}
