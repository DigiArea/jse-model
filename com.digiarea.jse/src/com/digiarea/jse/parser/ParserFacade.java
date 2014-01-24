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
package com.digiarea.jse.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.digiarea.jse.Node;

public class ParserFacade {

	public static final Node parse(String javaCode) throws Exception {
		InputStream is = new ByteArrayInputStream(javaCode.getBytes());
		ASTParser parser = new ASTParser(is);
		try {
			Node node = parser.ArrayInitializer();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.VariableDeclarationExpression();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.Expression();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.Statement();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.Block();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.ClassOrInterfaceBodyDeclaration(false);
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.TypeDeclaration();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.ImportDeclaration();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.PackageDeclaration();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.CompilationUnit(null);
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.ExplicitConstructorInvocation();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.EmptyStatement();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.Annotation();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.Type();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		try {
			Node node = parser.Wildcard();
			if (parser.getNextToken().kind == ASTParserConstants.EOF) {
				return node;
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			parser.reset(is);
		}
		return null;
	}

}
