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
import java.util.*;
import com.digiarea.jse.*;

/**
 * <p>This class was generated automatically by javacc, do not edit.</p>
 * @author norb.beaver@digi-area.com
 */
@SuppressWarnings({"unused", "serial"})
public final class ASTParser implements ASTParserConstants {

  /**
   * The cu name.
   */
  private String cuName = null;

  /**
   * Reset.
   *
   * @param in the in
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void reset(java.io.InputStream in) throws java.io.IOException {
    reset(in, null);
  }

  /**
   * Reset.
   *
   * @param in the in
   * @param encoding the encoding
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void reset(java.io.InputStream in, String encoding) throws java.io.IOException {
    in.reset();
    ReInit(in, encoding);
    token_source.clearComments();
  }

 /**
  * To class or interface type.
  *
  * @param name the name
  * @return the class or interface type
  */
 public static ClassOrInterfaceType toClassOrInterfaceType(NameExpr name) {
                if (name instanceof QualifiedNameExpr) {
                        return toClassOrInterfaceType((QualifiedNameExpr) name);
                } else {
                        return NodeFacade.ClassOrInterfaceType(name);
                }
        }

        /**
         * To class or interface type.
         *
         * @param qName the q name
         * @return the class or interface type
         */
        public static ClassOrInterfaceType toClassOrInterfaceType(
                        QualifiedNameExpr qName) {
                NameExpr qualifier = qName.getQualifier();
                if (Character.isUpperCase(qualifier.getName().charAt(0))) {
                        return NodeFacade.ClassOrInterfaceType(toClassOrInterfaceType(qualifier),
                                        NodeFacade.NameExpr(qName.getName()));
                }
                return NodeFacade.ClassOrInterfaceType(qName);
        }

        /**
         * To string.
         *
         * @param name the name
         * @param dlim the dlim
         * @return the string
         */
        public static String toString(NameExpr name, String dlim) {
                if (name instanceof QualifiedNameExpr) {
                        NameExpr q = ((QualifiedNameExpr) name).getQualifier();
                        if (q != null) {
                                return toString(q, dlim) + dlim + name.getName();
                        }
                }
                return name.getName();
        }

  /**
   * Adds the.
   *
   * @param <T> the generic type
   * @param list the list
   * @param obj the obj
   * @return the list
   */
  private <T> List<T> add(List<T> list, T obj) {
    if (list == null) {
      list = new LinkedList<T>();
    }
    list.add(obj);
    return list;
  }

  /**
   * Adds the.
   *
   * @param <T> the generic type
   * @param pos the pos
   * @param list the list
   * @param obj the obj
   * @return the list
   */
  private <T> List<T> add(int pos, List<T> list, T obj) {
    if (list == null) {
      list = new LinkedList<T>();
    }
    list.add(pos, obj);
    return list;
  }

  /**
   * The Class Modifier.
   */
  private class Modifier {
    
    /**
     * The modifiers.
     */
    final int modifiers;
    
    /**
     * The annotations.
     */
    final List<AnnotationExpr> annotations;

    /**
     * Instantiates a new modifier.
     *
     * @param modifiers the modifiers
     * @param annotations the annotations
     */
    public Modifier(int modifiers, List<AnnotationExpr> annotations) {
      this.modifiers = modifiers;
      this.annotations = annotations;
    }
  }

  /**
   * The Class ArrayDimsAndInits.
   */
  private class ArrayDimsAndInits {
    
    /**
     * The slots.
     */
    final List<ArraySlot> slots;
    
    /**
     * The initializer.
     */
    final ArrayInitializerExpr initializer;

        /**
         * Instantiates a new array dims and inits.
         *
         * @param slots the slots
         * @param initializer the initializer
         */
        public ArrayDimsAndInits(List<ArraySlot> slots, ArrayInitializerExpr initializer) {
          this.slots = slots;
      this.initializer = initializer;
        }

  }

  /**
   * Adds the modifier.
   *
   * @param modifiers the modifiers
   * @param mod the mod
   * @param token the token
   * @return the int
   * @throws ParseException the parse exception
   */
  public int addModifier(int modifiers, int mod, Token token)
        throws ParseException {
    if ((Modifiers.hasModifier(modifiers, mod))) {
      throwParseException(token, "Duplicated modifier");
    }
    return Modifiers.addModifier(modifiers, mod);
  }

  /**
   * Push javadoc.
   */
  private void pushJavadoc() {
    token_source.pushJavadoc();
  }

  /**
   * Pop javadoc.
   *
   * @return the javadoc comment
   */
  private JavadocComment popJavadoc() {
    return token_source.popJavadoc();
  }

  /**
   * Gets the comments.
   *
   * @return the comments
   */
  private List<Comment> getComments() {
    return token_source.getComments();
  }

  /**
   * Throw parse exception.
   *
   * @param token the token
   * @param message the message
   * @throws ParseException the parse exception
   */
  private void throwParseException(Token token, String message)
        throws ParseException {
    StringBuilder buf = new StringBuilder();
    buf.append("\u005cn");
    buf.append(message);
    buf.append(": \u005c"");
    buf.append(token.image);
    buf.append("\u005c" at line ");
    buf.append(token.beginLine);
    buf.append(", column ");
    buf.append(token.beginColumn);
    ParseException e = new ParseException(buf.toString());
    e.currentToken = token;
    throw e;
  }

  /**
   * The Class GTToken.
   */
  static final class GTToken extends Token {
    
    /**
     * The real kind.
     */
    int realKind = ASTParserConstants.GT;
    
    /**
     * Instantiates a new GT token.
     *
     * @param kind the kind
     * @param image the image
     */
    GTToken(int kind, String image) {
      this.kind = kind;
      this.image = image;
    }

    /**
     * New token.
     *
     * @param kind the kind
     * @param image the image
     * @return the token
     */
    public static Token newToken(int kind, String image) {
      return new GTToken(kind, image);
    }
  }

/**
 * ***************************************
 * THE JAVA LANGUAGE GRAMMAR STARTS HERE *
 * ***************************************.
 *
 * @param name the name
 * @return the compilation unit
 * @throws ParseException the parse exception
 */
/*
 * Program structuring syntax follows.
 */
  final public CompilationUnit CompilationUnit(String name) throws ParseException {
  PackageDeclaration pakage = null;
  List<ImportDeclaration> imports = null;
  ImportDeclaration in = null;
  List<TypeDeclaration> types = null;
  TypeDeclaration tn = null;
  cuName = name;
    if (jj_2_1(2147483647)) {
      pakage = PackageDeclaration();
    } else {
      ;
    }
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IMPORT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      in = ImportDeclaration();
      imports = add(imports, in);
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABSTRACT:
      case CLASS:
      case ENUM:
      case FINAL:
      case INTERFACE:
      case NATIVE:
      case PRIVATE:
      case PROTECTED:
      case PUBLIC:
      case STATIC:
      case STRICTFP:
      case SYNCHRONIZED:
      case TRANSIENT:
      case VOLATILE:
      case SEMICOLON:
      case AT:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      tn = TypeDeclaration();
      types = add(types, tn);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 0:
      jj_consume_token(0);
      break;
    case 129:
      jj_consume_token(129);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    if (pakage != null)
    {
      cuName = toString(pakage.getName(), ".") + "." + cuName;
    }
    {if (true) return NodeFacade.CompilationUnit(pakage, imports, types, getComments(), cuName);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Package declaration.
   *
   * @return the package declaration
   * @throws ParseException the parse exception
   */
  final public PackageDeclaration PackageDeclaration() throws ParseException {
  List<AnnotationExpr> annotations = null;
  AnnotationExpr ann;
  NameExpr name;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AT:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      ann = Annotation();
      annotations = add(annotations, ann);
    }
    jj_consume_token(PACKAGE);
    name = Name();
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.PackageDeclaration(name, annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Import declaration.
   *
   * @return the import declaration
   * @throws ParseException the parse exception
   */
  final public ImportDeclaration ImportDeclaration() throws ParseException {
  NameExpr name;
  boolean isStatic = false;
  boolean isAsterisk = false;
    jj_consume_token(IMPORT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STATIC:
      jj_consume_token(STATIC);
      isStatic = true;
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    name = Name();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOT:
      jj_consume_token(DOT);
      jj_consume_token(STAR);
      isAsterisk = true;
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.ImportDeclaration(name, isStatic, isAsterisk);}
    throw new Error("Missing return statement in function");
  }

/*
 * Modifiers. We match all modifiers in a single rule to reduce the chances of
 * syntax errors for simple modifier mistakes. It will also enable us to give
 * better error messages.
 */
  /**
 * Modifiers.
 *
 * @return the modifier
 * @throws ParseException the parse exception
 */
final public Modifier Modifiers() throws ParseException {
  int modifiers = 0;
  List<AnnotationExpr> annotations = null;
  AnnotationExpr ann;
    label_4:
    while (true) {
      if (jj_2_2(2)) {
        ;
      } else {
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PUBLIC:
        jj_consume_token(PUBLIC);
        modifiers = addModifier(modifiers, Modifiers.PUBLIC, token);
        break;
      case STATIC:
        jj_consume_token(STATIC);
        modifiers = addModifier(modifiers, Modifiers.STATIC, token);
        break;
      case PROTECTED:
        jj_consume_token(PROTECTED);
        modifiers = addModifier(modifiers, Modifiers.PROTECTED, token);
        break;
      case PRIVATE:
        jj_consume_token(PRIVATE);
        modifiers = addModifier(modifiers, Modifiers.PRIVATE, token);
        break;
      case FINAL:
        jj_consume_token(FINAL);
        modifiers = addModifier(modifiers, Modifiers.FINAL, token);
        break;
      case ABSTRACT:
        jj_consume_token(ABSTRACT);
        modifiers = addModifier(modifiers, Modifiers.ABSTRACT, token);
        break;
      case SYNCHRONIZED:
        jj_consume_token(SYNCHRONIZED);
        modifiers = addModifier(modifiers, Modifiers.SYNCHRONIZED, token);
        break;
      case NATIVE:
        jj_consume_token(NATIVE);
        modifiers = addModifier(modifiers, Modifiers.NATIVE, token);
        break;
      case TRANSIENT:
        jj_consume_token(TRANSIENT);
        modifiers = addModifier(modifiers, Modifiers.TRANSIENT, token);
        break;
      case VOLATILE:
        jj_consume_token(VOLATILE);
        modifiers = addModifier(modifiers, Modifiers.VOLATILE, token);
        break;
      case STRICTFP:
        jj_consume_token(STRICTFP);
        modifiers = addModifier(modifiers, Modifiers.STRICTFP, token);
        break;
      case AT:
        ann = Annotation();
        annotations = add(annotations, ann);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return new Modifier(modifiers, annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type decl modifiers.
   *
   * @return the modifier
   * @throws ParseException the parse exception
   */
  final public Modifier TypeDeclModifiers() throws ParseException {
  int modifiers = 0;
  List<AnnotationExpr> annotations = null;
  AnnotationExpr ann;
    label_5:
    while (true) {
      if (jj_2_3(2)) {
        ;
      } else {
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PUBLIC:
        jj_consume_token(PUBLIC);
        modifiers = addModifier(modifiers, Modifiers.PUBLIC, token);
        break;
      case STATIC:
        jj_consume_token(STATIC);
        modifiers = addModifier(modifiers, Modifiers.STATIC, token);
        break;
      case PROTECTED:
        jj_consume_token(PROTECTED);
        modifiers = addModifier(modifiers, Modifiers.PROTECTED, token);
        break;
      case PRIVATE:
        jj_consume_token(PRIVATE);
        modifiers = addModifier(modifiers, Modifiers.PRIVATE, token);
        break;
      case FINAL:
        jj_consume_token(FINAL);
        modifiers = addModifier(modifiers, Modifiers.FINAL, token);
        break;
      case ABSTRACT:
        jj_consume_token(ABSTRACT);
        modifiers = addModifier(modifiers, Modifiers.ABSTRACT, token);
        break;
      case SYNCHRONIZED:
        jj_consume_token(SYNCHRONIZED);
        modifiers = addModifier(modifiers, Modifiers.SYNCHRONIZED, token);
        break;
      case NATIVE:
        jj_consume_token(NATIVE);
        modifiers = addModifier(modifiers, Modifiers.NATIVE, token);
        break;
      case TRANSIENT:
        jj_consume_token(TRANSIENT);
        modifiers = addModifier(modifiers, Modifiers.TRANSIENT, token);
        break;
      case VOLATILE:
        jj_consume_token(VOLATILE);
        modifiers = addModifier(modifiers, Modifiers.VOLATILE, token);
        break;
      case STRICTFP:
        jj_consume_token(STRICTFP);
        modifiers = addModifier(modifiers, Modifiers.STRICTFP, token);
        break;
      case _DEFAULT:
        jj_consume_token(_DEFAULT);
        modifiers = addModifier(modifiers, Modifiers.DEFAULT, token);
        break;
      case AT:
        ann = Annotation();
        annotations = add(annotations, ann);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return new Modifier(modifiers, annotations);}
    throw new Error("Missing return statement in function");
  }

/*
 * Declaration syntax follows.
 */
  /**
 * Type declaration.
 *
 * @return the type declaration
 * @throws ParseException the parse exception
 */
final public TypeDeclaration TypeDeclaration() throws ParseException {
  Modifier modifier;
  TypeDeclaration ret;
    pushJavadoc();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      ret = NodeFacade.EmptyTypeDeclaration(popJavadoc());
      break;
    case ABSTRACT:
    case CLASS:
    case ENUM:
    case FINAL:
    case INTERFACE:
    case NATIVE:
    case PRIVATE:
    case PROTECTED:
    case PUBLIC:
    case STATIC:
    case STRICTFP:
    case SYNCHRONIZED:
    case TRANSIENT:
    case VOLATILE:
    case AT:
      modifier = Modifiers();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CLASS:
      case INTERFACE:
        ret = ClassOrInterfaceDeclaration(modifier);
        break;
      case ENUM:
        ret = EnumDeclaration(modifier);
        break;
      case AT:
        ret = AnnotationTypeDeclaration(modifier);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Class or interface declaration.
   *
   * @param modifier the modifier
   * @return the type declaration
   * @throws ParseException the parse exception
   */
  final public TypeDeclaration ClassOrInterfaceDeclaration(Modifier modifier) throws ParseException {
  TypeDeclaration typeDecl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CLASS:
      typeDecl = ClassDeclaration(modifier);
      break;
    case INTERFACE:
      typeDecl = InterfaceDeclaration(modifier);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return typeDecl;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Class declaration.
   *
   * @param modifier the modifier
   * @return the class declaration
   * @throws ParseException the parse exception
   */
  final public ClassDeclaration ClassDeclaration(Modifier modifier) throws ParseException {
  String name = null;
  List<TypeParameter> typePar = null;
  List<ClassOrInterfaceType> extList = null;
  List<ClassOrInterfaceType> impList = null;
  List<BodyDeclaration> members = null;
    jj_consume_token(CLASS);
    jj_consume_token(IDENTIFIER);
    name = token.image;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LT:
      typePar = TypeParameters();
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EXTENDS:
      extList = ExtendsList(false);
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IMPLEMENTS:
      impList = ImplementsList(false);
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
    members = ClassOrInterfaceBody(false);
    {if (true) return NodeFacade.ClassDeclaration(typePar, (ClassOrInterfaceType) (extList != null ? extList.get(0) : null),
        impList, modifier.modifiers, name, members, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Interface declaration.
   *
   * @param modifier the modifier
   * @return the interface declaration
   * @throws ParseException the parse exception
   */
  final public InterfaceDeclaration InterfaceDeclaration(Modifier modifier) throws ParseException {
  String name = null;
  List<TypeParameter> typePar = null;
  List<ClassOrInterfaceType> extList = null;
  List<BodyDeclaration> members = null;
    jj_consume_token(INTERFACE);
    jj_consume_token(IDENTIFIER);
    name = token.image;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LT:
      typePar = TypeParameters();
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EXTENDS:
      extList = ExtendsList(true);
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
    members = ClassOrInterfaceBody(true);
    {if (true) return NodeFacade.InterfaceDeclaration(typePar, extList, modifier.modifiers, name, members, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Extends list.
   *
   * @param isInterface the is interface
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<ClassOrInterfaceType> ExtendsList(boolean isInterface) throws ParseException {
  boolean extendsMoreThanOne = false;
  List<ClassOrInterfaceType> ret = null;
  ClassOrInterfaceType cit;
  List<AnnotationExpr> ann = null;
    jj_consume_token(EXTENDS);
    ann = Annotations();
    cit = ClassOrInterfaceType();
    cit.setAnnotations(NodeFacade.NodeList(ann));
    ret = add(ret, cit);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      ann = Annotations();
      cit = ClassOrInterfaceType();
      cit.setAnnotations(NodeFacade.NodeList(ann));
      ret = add(ret, cit);
      extendsMoreThanOne = true;
    }
    if (extendsMoreThanOne && !isInterface) throwParseException(token, "A class cannot extend more than one other class");
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Implements list.
   *
   * @param isInterface the is interface
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<ClassOrInterfaceType> ImplementsList(boolean isInterface) throws ParseException {
  List<ClassOrInterfaceType> ret = null;
  ClassOrInterfaceType cit;
  List<AnnotationExpr> ann = null;
    jj_consume_token(IMPLEMENTS);
    ann = Annotations();
    cit = ClassOrInterfaceType();
    cit.setAnnotations(NodeFacade.NodeList(ann));
    ret = add(ret, cit);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_7;
      }
      jj_consume_token(COMMA);
      ann = Annotations();
      cit = ClassOrInterfaceType();
      cit.setAnnotations(NodeFacade.NodeList(ann));
      ret = add(ret, cit);
    }
    if (isInterface) throwParseException(token, "An interface cannot implement other interfaces");
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Throws list.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<ClassOrInterfaceType> ThrowsList() throws ParseException {
  List<ClassOrInterfaceType> ret = null;
  ClassOrInterfaceType cit;
  List<AnnotationExpr> ann = null;
    jj_consume_token(THROWS);
    ann = Annotations();
    cit = ClassOrInterfaceType();
    cit.setAnnotations(NodeFacade.NodeList(ann));
    ret = add(ret, cit);
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_8;
      }
      jj_consume_token(COMMA);
      ann = Annotations();
      cit = ClassOrInterfaceType();
      cit.setAnnotations(NodeFacade.NodeList(ann));
      ret = add(ret, cit);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Enum declaration.
   *
   * @param modifier the modifier
   * @return the enum declaration
   * @throws ParseException the parse exception
   */
  final public EnumDeclaration EnumDeclaration(Modifier modifier) throws ParseException {
  String name;
  List<ClassOrInterfaceType> impList = null;
  EnumConstantDeclaration entry;
  List<EnumConstantDeclaration> entries = null;
  BodyDeclaration member;
  List<BodyDeclaration> members = null;
    jj_consume_token(ENUM);
    jj_consume_token(IDENTIFIER);
    name = token.image;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IMPLEMENTS:
      impList = ImplementsList(false);
      break;
    default:
      jj_la1[19] = jj_gen;
      ;
    }
    jj_consume_token(LBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
    case AT:
      entries = new LinkedList<EnumConstantDeclaration>();
      entry = EnumConstantDeclaration();
      entries.add(entry);
      label_9:
      while (true) {
        if (jj_2_4(2)) {
          ;
        } else {
          break label_9;
        }
        jj_consume_token(COMMA);
        entry = EnumConstantDeclaration();
        entries.add(entry);
      }
      break;
    default:
      jj_la1[20] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      break;
    default:
      jj_la1[21] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ABSTRACT:
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case CLASS:
        case _DEFAULT:
        case DOUBLE:
        case ENUM:
        case FINAL:
        case FLOAT:
        case INT:
        case INTERFACE:
        case LONG:
        case NATIVE:
        case PRIVATE:
        case PROTECTED:
        case PUBLIC:
        case SHORT:
        case STATIC:
        case STRICTFP:
        case SYNCHRONIZED:
        case TRANSIENT:
        case VOID:
        case VOLATILE:
        case IDENTIFIER:
        case LBRACE:
        case SEMICOLON:
        case AT:
        case LT:
          ;
          break;
        default:
          jj_la1[22] = jj_gen;
          break label_10;
        }
        member = ClassOrInterfaceBodyDeclaration(false);
          members = add(members, member);
      }
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
    jj_consume_token(RBRACE);
    {if (true) return NodeFacade.EnumDeclaration(impList, entries, modifier.modifiers, name, members, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Enum constant declaration.
   *
   * @return the enum constant declaration
   * @throws ParseException the parse exception
   */
  final public EnumConstantDeclaration EnumConstantDeclaration() throws ParseException {
  List<AnnotationExpr> annotations = null;
  AnnotationExpr ann;
  String name;
  List<Expression> args = null;
  List<BodyDeclaration> classBody = null;
    pushJavadoc();
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AT:
        ;
        break;
      default:
        jj_la1[24] = jj_gen;
        break label_11;
      }
      ann = Annotation();
      annotations = add(annotations, ann);
    }
    jj_consume_token(IDENTIFIER);
    name = token.image;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      args = Arguments();
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACE:
      classBody = ClassOrInterfaceBody(false);
      break;
    default:
      jj_la1[26] = jj_gen;
      ;
    }
    {if (true) return NodeFacade.EnumConstantDeclaration(name, args, classBody, popJavadoc(), annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type parameters.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<TypeParameter> TypeParameters() throws ParseException {
  List<TypeParameter> ret = null;
  TypeParameter tp;
    jj_consume_token(LT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
    case AT:
      tp = TypeParameter();
      ret = add(ret, tp);
      label_12:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[27] = jj_gen;
          break label_12;
        }
        jj_consume_token(COMMA);
        tp = TypeParameter();
        ret = add(ret, tp);
      }
      break;
    default:
      jj_la1[28] = jj_gen;
      ;
    }
    jj_consume_token(GT);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type parameter.
   *
   * @return the type parameter
   * @throws ParseException the parse exception
   */
  final public TypeParameter TypeParameter() throws ParseException {
  String name;
  List<ClassOrInterfaceType> typeBound = null;
  List<AnnotationExpr> ann = null;
    ann = Annotations();
    jj_consume_token(IDENTIFIER);
    name = token.image;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EXTENDS:
      typeBound = TypeBound();
      break;
    default:
      jj_la1[29] = jj_gen;
      ;
    }
    {if (true) return NodeFacade.TypeParameter(name, typeBound, ann);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type bound.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<ClassOrInterfaceType> TypeBound() throws ParseException {
  List<ClassOrInterfaceType> ret = null;
  ClassOrInterfaceType cit;
  List<AnnotationExpr> ann = null;
    jj_consume_token(EXTENDS);
    ann = Annotations();
    cit = ClassOrInterfaceType();
    cit.setAnnotations(NodeFacade.NodeList(ann));
    ret = add(ret, cit);
    label_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIT_AND:
        ;
        break;
      default:
        jj_la1[30] = jj_gen;
        break label_13;
      }
      jj_consume_token(BIT_AND);
      ann = Annotations();
      cit = ClassOrInterfaceType();
      cit.setAnnotations(NodeFacade.NodeList(ann));
      ret = add(ret, cit);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Class or interface body.
   *
   * @param isInterface the is interface
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<BodyDeclaration> ClassOrInterfaceBody(boolean isInterface) throws ParseException {
  List<BodyDeclaration> ret = null;
  BodyDeclaration member;
    jj_consume_token(LBRACE);
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABSTRACT:
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case CLASS:
      case _DEFAULT:
      case DOUBLE:
      case ENUM:
      case FINAL:
      case FLOAT:
      case INT:
      case INTERFACE:
      case LONG:
      case NATIVE:
      case PRIVATE:
      case PROTECTED:
      case PUBLIC:
      case SHORT:
      case STATIC:
      case STRICTFP:
      case SYNCHRONIZED:
      case TRANSIENT:
      case VOID:
      case VOLATILE:
      case IDENTIFIER:
      case LBRACE:
      case SEMICOLON:
      case AT:
      case LT:
        ;
        break;
      default:
        jj_la1[31] = jj_gen;
        break label_14;
      }
      member = ClassOrInterfaceBodyDeclaration(isInterface);
      ret = add(ret, member);
    }
    jj_consume_token(RBRACE);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Class or interface body declaration.
   *
   * @param isInterface the is interface
   * @return the body declaration
   * @throws ParseException the parse exception
   */
  final public BodyDeclaration ClassOrInterfaceBodyDeclaration(boolean isInterface) throws ParseException {
  boolean isNestedInterface = false;
  Modifier modifier;
  BodyDeclaration ret;
    pushJavadoc();
    if (jj_2_8(2)) {
      ret = InitializerDeclaration();
      if (isInterface) throwParseException(token, "An interface cannot have initializers");
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABSTRACT:
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case CLASS:
      case _DEFAULT:
      case DOUBLE:
      case ENUM:
      case FINAL:
      case FLOAT:
      case INT:
      case INTERFACE:
      case LONG:
      case NATIVE:
      case PRIVATE:
      case PROTECTED:
      case PUBLIC:
      case SHORT:
      case STATIC:
      case STRICTFP:
      case SYNCHRONIZED:
      case TRANSIENT:
      case VOID:
      case VOLATILE:
      case IDENTIFIER:
      case AT:
      case LT:
        modifier = TypeDeclModifiers();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CLASS:
        case INTERFACE:
          ret = ClassOrInterfaceDeclaration(modifier);
          break;
        case ENUM:
          ret = EnumDeclaration(modifier);
          break;
        default:
          jj_la1[32] = jj_gen;
          if (jj_2_5(2)) {
            ret = AnnotationTypeDeclaration(modifier);
          } else if (jj_2_6(2147483647)) {
            ret = ConstructorDeclaration(modifier);
          } else if (jj_2_7(2147483647)) {
            ret = FieldDeclaration(modifier);
          } else {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case SHORT:
            case VOID:
            case IDENTIFIER:
            case AT:
            case LT:
              ret = MethodDeclaration(modifier);
              break;
            default:
              jj_la1[33] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
          }
        }
        break;
      case SEMICOLON:
        jj_consume_token(SEMICOLON);
      ret = NodeFacade.EmptyMemberDeclaration(popJavadoc());
        break;
      default:
        jj_la1[34] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Field declaration.
   *
   * @param modifier the modifier
   * @return the field declaration
   * @throws ParseException the parse exception
   */
  final public FieldDeclaration FieldDeclaration(Modifier modifier) throws ParseException {
  Type type;
  List<VariableDeclarator> variables = new LinkedList<VariableDeclarator>();
  VariableDeclarator val;
    // Modifiers are already matched in the caller
      type = TypeNoAnnotations();
    val = VariableDeclarator();
    variables.add(val);
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[35] = jj_gen;
        break label_15;
      }
      jj_consume_token(COMMA);
      val = VariableDeclarator();
      variables.add(val);
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.FieldDeclaration(modifier.modifiers, type, variables, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Variable declarator.
   *
   * @return the variable declarator
   * @throws ParseException the parse exception
   */
  final public VariableDeclarator VariableDeclarator() throws ParseException {
  VariableDeclaratorId id;
  Expression init = null;
    id = VariableDeclaratorId();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      init = VariableInitializer();
      break;
    default:
      jj_la1[36] = jj_gen;
      ;
    }
    {if (true) return NodeFacade.VariableDeclarator(id, init);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Variable declarator id.
   *
   * @return the variable declarator id
   * @throws ParseException the parse exception
   */
  final public VariableDeclaratorId VariableDeclaratorId() throws ParseException {
  String name;
  List<AnnotationExpr> ann = null;
  List<ArraySlot> slots = null;
  ArraySlot slot = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      jj_consume_token(IDENTIFIER);
      break;
    case THIS:
      jj_consume_token(THIS);
      break;
    default:
      jj_la1[37] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    name = token.image;
    label_16:
    while (true) {
      if (jj_2_9(2147483647)) {
        ;
      } else {
        break label_16;
      }
      ann = Annotations();
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
     slot = NodeFacade.ArraySlot(ann);
     slots = add(slots, slot);
    }
    {if (true) return NodeFacade.VariableDeclaratorId(name, slots);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Variable initializer.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression VariableInitializer() throws ParseException {
  Expression ret;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACE:
      ret = ArrayInitializer();
      break;
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case AT:
    case BANG:
    case TILDE:
    case INCR:
    case DECR:
    case PLUS:
    case MINUS:
      ret = Expression();
      break;
    default:
      jj_la1[38] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Array initializer.
   *
   * @return the array initializer expr
   * @throws ParseException the parse exception
   */
  final public ArrayInitializerExpr ArrayInitializer() throws ParseException {
  List<Expression> values = null;
  Expression val;
    jj_consume_token(LBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case LBRACE:
    case AT:
    case BANG:
    case TILDE:
    case INCR:
    case DECR:
    case PLUS:
    case MINUS:
      val = VariableInitializer();
      values = add(values, val);
      label_17:
      while (true) {
        if (jj_2_10(2)) {
          ;
        } else {
          break label_17;
        }
        jj_consume_token(COMMA);
        val = VariableInitializer();
        values = add(values, val);
      }
      break;
    default:
      jj_la1[39] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      break;
    default:
      jj_la1[40] = jj_gen;
      ;
    }
    jj_consume_token(RBRACE);
    {if (true) return NodeFacade.ArrayInitializerExpr(values);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Method declaration.
   *
   * @param modifier the modifier
   * @return the method declaration
   * @throws ParseException the parse exception
   */
  final public MethodDeclaration MethodDeclaration(Modifier modifier) throws ParseException {
  List<TypeParameter> typeParameters = null;
  Type type;
  String name;
  List<Parameter> parameters;
  int arrayCount = 0;
  List<ClassOrInterfaceType> throws_ = null;
  BlockStmt block = null;
  List<AnnotationExpr> ann = null;
  List<ArraySlot> slots = null;
  ArraySlot slot = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LT:
      typeParameters = TypeParameters();
      break;
    default:
      jj_la1[41] = jj_gen;
      ;
    }
    type = ResultType();
    jj_consume_token(IDENTIFIER);
    name = token.image;
    parameters = FormalParameters();
    label_18:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACKET:
      case AT:
        ;
        break;
      default:
        jj_la1[42] = jj_gen;
        break label_18;
      }
      ann = Annotations();
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
     slot = NodeFacade.ArraySlot(ann);
     slots = add(slots, slot);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case THROWS:
      throws_ = ThrowsList();
      break;
    default:
      jj_la1[43] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACE:
      block = Block();
      break;
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      break;
    default:
      jj_la1[44] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return NodeFacade.MethodDeclaration(modifier.modifiers, typeParameters, type, name, parameters,
        slots, throws_, block, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Formal parameters.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Parameter> FormalParameters() throws ParseException {
  List<Parameter> ret = null;
  Parameter par;
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ABSTRACT:
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FINAL:
    case FLOAT:
    case INT:
    case LONG:
    case NATIVE:
    case PRIVATE:
    case PROTECTED:
    case PUBLIC:
    case SHORT:
    case STATIC:
    case STRICTFP:
    case SYNCHRONIZED:
    case TRANSIENT:
    case VOLATILE:
    case IDENTIFIER:
    case AT:
      par = FormalParameter();
      ret = add(ret, par);
      label_19:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[45] = jj_gen;
          break label_19;
        }
        jj_consume_token(COMMA);
        par = FormalParameter();
        ret = add(ret, par);
      }
      break;
    default:
      jj_la1[46] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Formal parameter.
   *
   * @return the parameter
   * @throws ParseException the parse exception
   */
  final public Parameter FormalParameter() throws ParseException {
  Modifier modifier;
  Type type;
  VariableDeclaratorId id;
  List<AnnotationExpr> ann = null;
  Ellipsis ellipsis = null;
    modifier = Modifiers();
    type = Type();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AT:
    case ELLIPSIS:
      ann = Annotations();
      jj_consume_token(ELLIPSIS);
      ellipsis = NodeFacade.Ellipsis(ann);
      break;
    default:
      jj_la1[47] = jj_gen;
      ;
    }
    id = VariableDeclaratorId();
    {if (true) return NodeFacade.Parameter(modifier.modifiers, type, ellipsis, id, modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Lambda parameters.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Parameter> LambdaParameters() throws ParseException {
  List<Parameter> ret = null;
  Parameter par;
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      par = LambdaParameter();
      ret = add(ret, par);
      label_20:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[48] = jj_gen;
          break label_20;
        }
        jj_consume_token(COMMA);
        par = LambdaParameter();
        ret = add(ret, par);
      }
      break;
    default:
      jj_la1[49] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Lambda parameter.
   *
   * @return the parameter
   * @throws ParseException the parse exception
   */
  final public Parameter LambdaParameter() throws ParseException {
  String name = null;
    jj_consume_token(IDENTIFIER);
    name = token.image;
    {if (true) return NodeFacade.Parameter(0, null, null, NodeFacade.VariableDeclaratorId(name, null), null);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Constructor declaration.
   *
   * @param modifier the modifier
   * @return the constructor declaration
   * @throws ParseException the parse exception
   */
  final public ConstructorDeclaration ConstructorDeclaration(Modifier modifier) throws ParseException {
  List<TypeParameter> typeParameters = null;
  String name;
  List<Parameter> parameters;
  List<ClassOrInterfaceType> throws_ = null;
  ExplicitConstructorInvocationStmt exConsInv = null;
  List<Statement> stmts;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LT:
      typeParameters = TypeParameters();
      break;
    default:
      jj_la1[50] = jj_gen;
      ;
    }
    jj_consume_token(IDENTIFIER);
    name = token.image;
    parameters = FormalParameters();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case THROWS:
      throws_ = ThrowsList();
      break;
    default:
      jj_la1[51] = jj_gen;
      ;
    }
    jj_consume_token(LBRACE);
    if (jj_2_11(2147483647)) {
      exConsInv = ExplicitConstructorInvocation();
    } else {
      ;
    }
    stmts = Statements();
    jj_consume_token(RBRACE);
    if (exConsInv != null)
    {
      stmts = add(0, stmts, exConsInv);
    }
    {if (true) return NodeFacade.ConstructorDeclaration(modifier.modifiers, typeParameters, name, parameters,
        throws_, NodeFacade.BlockStmt(stmts), popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Explicit constructor invocation.
   *
   * @return the explicit constructor invocation stmt
   * @throws ParseException the parse exception
   */
  final public ExplicitConstructorInvocationStmt ExplicitConstructorInvocation() throws ParseException {
  boolean isThis = false;
  List<Expression> args;
  Expression expr = null;
  List<Type> typeArgs = null;
    if (jj_2_13(2147483647)) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
        typeArgs = TypeArguments();
        break;
      default:
        jj_la1[52] = jj_gen;
        ;
      }
      jj_consume_token(THIS);
      isThis = true;
      args = Arguments();
      jj_consume_token(SEMICOLON);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FALSE:
      case FLOAT:
      case INT:
      case LONG:
      case NEW:
      case NULL:
      case SHORT:
      case SUPER:
      case THIS:
      case TRUE:
      case VOID:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case AT:
      case LT:
        if (jj_2_12(2147483647)) {
          expr = PrimaryExpressionWithoutSuperSuffix();
          jj_consume_token(DOT);
        } else {
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LT:
          typeArgs = TypeArguments();
          break;
        default:
          jj_la1[53] = jj_gen;
          ;
        }
        jj_consume_token(SUPER);
        args = Arguments();
        jj_consume_token(SEMICOLON);
        break;
      default:
        jj_la1[54] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return NodeFacade.ExplicitConstructorInvocationStmt(typeArgs, isThis, expr, args);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Statements.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Statement> Statements() throws ParseException {
  List<Statement> ret = null;
  Statement stmt;
    label_21:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABSTRACT:
      case ASSERT:
      case BOOLEAN:
      case BREAK:
      case BYTE:
      case CHAR:
      case CLASS:
      case CONTINUE:
      case DO:
      case DOUBLE:
      case FALSE:
      case FINAL:
      case FLOAT:
      case FOR:
      case IF:
      case INT:
      case INTERFACE:
      case LONG:
      case NATIVE:
      case NEW:
      case NULL:
      case PRIVATE:
      case PROTECTED:
      case PUBLIC:
      case RETURN:
      case SHORT:
      case STATIC:
      case STRICTFP:
      case SUPER:
      case SWITCH:
      case SYNCHRONIZED:
      case THIS:
      case THROW:
      case TRANSIENT:
      case TRUE:
      case TRY:
      case VOID:
      case VOLATILE:
      case WHILE:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case LBRACE:
      case SEMICOLON:
      case AT:
      case INCR:
      case DECR:
        ;
        break;
      default:
        jj_la1[55] = jj_gen;
        break label_21;
      }
      stmt = BlockStatement();
      ret = add(ret, stmt);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Initializer declaration.
   *
   * @return the initializer declaration
   * @throws ParseException the parse exception
   */
  final public InitializerDeclaration InitializerDeclaration() throws ParseException {
  BlockStmt block;
  boolean isStatic = false;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STATIC:
      jj_consume_token(STATIC);
      isStatic = true;
      break;
    default:
      jj_la1[56] = jj_gen;
      ;
    }
    block = Block();
    {if (true) return NodeFacade.InitializerDeclaration(isStatic, block, popJavadoc());}
    throw new Error("Missing return statement in function");
  }

/*
 * Type, name and expression syntax follows.
 */
  /**
 * Type.
 *
 * @return the type
 * @throws ParseException the parse exception
 */
final public Type Type() throws ParseException {
  Type ret;
  List<AnnotationExpr> annotations = null;
    annotations = Annotations();
    if (jj_2_14(2)) {
      ret = ReferenceType();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FLOAT:
      case INT:
      case LONG:
      case SHORT:
        ret = PrimitiveType();
        break;
      default:
        jj_la1[57] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    ret.setAnnotations(NodeFacade.NodeList(annotations));
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type no annotations.
   *
   * @return the type
   * @throws ParseException the parse exception
   */
  final public Type TypeNoAnnotations() throws ParseException {
  Type ret;
    if (jj_2_15(2)) {
      ret = ReferenceType();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FLOAT:
      case INT:
      case LONG:
      case SHORT:
        ret = PrimitiveType();
        break;
      default:
        jj_la1[58] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Reference type.
   *
   * @return the reference type
   * @throws ParseException the parse exception
   */
  final public ReferenceType ReferenceType() throws ParseException {
  Type type;
  List<ArraySlot> slots = null;
  ArraySlot slot = null;
  List<AnnotationExpr> annotations = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FLOAT:
    case INT:
    case LONG:
    case SHORT:
      type = PrimitiveType();
      label_22:
      while (true) {
        annotations = Annotations();
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
        slot = NodeFacade.ArraySlot(annotations);
        slots = add(slots, slot);
        if (jj_2_16(2147483647)) {
          ;
        } else {
          break label_22;
        }
      }
      break;
    case IDENTIFIER:
      type = ClassOrInterfaceType();
      label_23:
      while (true) {
        if (jj_2_17(2147483647)) {
          ;
        } else {
          break label_23;
        }
        annotations = Annotations();
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
        slot = NodeFacade.ArraySlot(annotations);
        slots = add(slots, slot);
      }
      break;
    default:
      jj_la1[59] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return NodeFacade.ReferenceType(type, slots);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Class or interface type.
   *
   * @return the class or interface type
   * @throws ParseException the parse exception
   */
  final public ClassOrInterfaceType ClassOrInterfaceType() throws ParseException {
  ClassOrInterfaceType ret;
  NameExpr name;
  List<Type> typeArgs = null;
  List<AnnotationExpr> ann = null;
    name = Name();
    if (jj_2_18(2)) {
      typeArgs = TypeArguments();
    } else {
      ;
    }
    ret = toClassOrInterfaceType(name);
    ret.setTypeArgs(NodeFacade.NodeList(typeArgs));
    typeArgs = null;
    label_24:
    while (true) {
      if (jj_2_19(2)) {
        ;
      } else {
        break label_24;
      }
      jj_consume_token(DOT);
      ann = Annotations();
      jj_consume_token(IDENTIFIER);
      name = NodeFacade.NameExpr(token.image);
      if (jj_2_20(2)) {
        typeArgs = TypeArguments();
      } else {
        ;
      }
      ret = NodeFacade.ClassOrInterfaceType(ret, name, typeArgs, ann);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type arguments.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Type> TypeArguments() throws ParseException {
  List<Type> ret = new LinkedList<Type>();
  Type type;
    jj_consume_token(LT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FLOAT:
    case INT:
    case LONG:
    case SHORT:
    case IDENTIFIER:
    case AT:
    case HOOK:
      type = TypeArgument();
      ret.add(type);
      label_25:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[60] = jj_gen;
          break label_25;
        }
        jj_consume_token(COMMA);
        type = TypeArgument();
        ret.add(type);
      }
      break;
    default:
      jj_la1[61] = jj_gen;
      ;
    }
    jj_consume_token(GT);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Type argument.
   *
   * @return the type
   * @throws ParseException the parse exception
   */
  final public Type TypeArgument() throws ParseException {
  Type ret;
  List<AnnotationExpr> annotations = null;
    annotations = Annotations();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FLOAT:
    case INT:
    case LONG:
    case SHORT:
    case IDENTIFIER:
      ret = ReferenceType();
      break;
    case HOOK:
      ret = Wildcard();
      break;
    default:
      jj_la1[62] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    ret.setAnnotations(NodeFacade.NodeList(annotations));
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Wildcard.
   *
   * @return the wildcard type
   * @throws ParseException the parse exception
   */
  final public WildcardType Wildcard() throws ParseException {
  ReferenceType ext = null;
  ReferenceType sup = null;
  List<AnnotationExpr> annotations = null;
    jj_consume_token(HOOK);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EXTENDS:
    case SUPER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EXTENDS:
        jj_consume_token(EXTENDS);
        annotations = Annotations();
        ext = ReferenceType();
      ext.setAnnotations(NodeFacade.NodeList(annotations));
        break;
      case SUPER:
        jj_consume_token(SUPER);
        annotations = Annotations();
        sup = ReferenceType();
      sup.setAnnotations(NodeFacade.NodeList(annotations));
        break;
      default:
        jj_la1[63] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[64] = jj_gen;
      ;
    }
    {if (true) return NodeFacade.WildcardType(ext, sup);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Primitive type.
   *
   * @return the primitive type
   * @throws ParseException the parse exception
   */
  final public PrimitiveType PrimitiveType() throws ParseException {
  PrimitiveType ret;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Boolean);
      break;
    case CHAR:
      jj_consume_token(CHAR);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Char);
      break;
    case BYTE:
      jj_consume_token(BYTE);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Byte);
      break;
    case SHORT:
      jj_consume_token(SHORT);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Short);
      break;
    case INT:
      jj_consume_token(INT);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Int);
      break;
    case LONG:
      jj_consume_token(LONG);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Long);
      break;
    case FLOAT:
      jj_consume_token(FLOAT);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Float);
      break;
    case DOUBLE:
      jj_consume_token(DOUBLE);
      ret = NodeFacade.PrimitiveType(PrimitiveType.Primitive.Double);
      break;
    default:
      jj_la1[65] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Result type.
   *
   * @return the type
   * @throws ParseException the parse exception
   */
  final public Type ResultType() throws ParseException {
  Type ret;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VOID:
      jj_consume_token(VOID);
      ret = NodeFacade.VoidType();
      break;
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FLOAT:
    case INT:
    case LONG:
    case SHORT:
    case IDENTIFIER:
    case AT:
      ret = Type();
      break;
    default:
      jj_la1[66] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Name.
   *
   * @return the name expr
   * @throws ParseException the parse exception
   */
  final public NameExpr Name() throws ParseException {
  NameExpr ret;
    jj_consume_token(IDENTIFIER);
    ret = NodeFacade.NameExpr(token.image);
    label_26:
    while (true) {
      if (jj_2_21(2)) {
        ;
      } else {
        break label_26;
      }
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
      ret = NodeFacade.QualifiedNameExpr(ret, token.image);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Name list.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<NameExpr> NameList() throws ParseException {
  List<NameExpr> ret = new LinkedList<NameExpr>();
  NameExpr name;
  List<AnnotationExpr> ann = null;
    ann = Annotations();
    name = Name();
    name.setAnnotations(NodeFacade.NodeList(ann));
    ret.add(name);
    label_27:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[67] = jj_gen;
        break label_27;
      }
      jj_consume_token(COMMA);
      ann = Annotations();
      name = Name();
      name.setAnnotations(NodeFacade.NodeList(ann));
      ret.add(name);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

/*
 * Expression syntax follows.
 */
  /**
 * Expression.
 *
 * @return the expression
 * @throws ParseException the parse exception
 */
final public Expression Expression() throws ParseException {
  Expression ret;
  AssignExpr.AssignOperator op;
  Expression value;
    ret = ConditionalExpression();
    if (jj_2_22(2)) {
      op = AssignmentOperator();
      value = Expression();
      ret = NodeFacade.AssignExpr(ret, value, op);
    } else {
      ;
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Assignment operator.
   *
   * @return the assign expr. assign operator
   * @throws ParseException the parse exception
   */
  final public AssignExpr.AssignOperator AssignmentOperator() throws ParseException {
  AssignExpr.AssignOperator ret;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      ret = AssignExpr.AssignOperator.assign;
      break;
    case STARASSIGN:
      jj_consume_token(STARASSIGN);
      ret = AssignExpr.AssignOperator.star;
      break;
    case SLASHASSIGN:
      jj_consume_token(SLASHASSIGN);
      ret = AssignExpr.AssignOperator.slash;
      break;
    case REMASSIGN:
      jj_consume_token(REMASSIGN);
      ret = AssignExpr.AssignOperator.rem;
      break;
    case PLUSASSIGN:
      jj_consume_token(PLUSASSIGN);
      ret = AssignExpr.AssignOperator.plus;
      break;
    case MINUSASSIGN:
      jj_consume_token(MINUSASSIGN);
      ret = AssignExpr.AssignOperator.minus;
      break;
    case LSHIFTASSIGN:
      jj_consume_token(LSHIFTASSIGN);
      ret = AssignExpr.AssignOperator.lShift;
      break;
    case RSIGNEDSHIFTASSIGN:
      jj_consume_token(RSIGNEDSHIFTASSIGN);
      ret = AssignExpr.AssignOperator.rSignedShift;
      break;
    case RUNSIGNEDSHIFTASSIGN:
      jj_consume_token(RUNSIGNEDSHIFTASSIGN);
      ret = AssignExpr.AssignOperator.rUnsignedShift;
      break;
    case ANDASSIGN:
      jj_consume_token(ANDASSIGN);
      ret = AssignExpr.AssignOperator.and;
      break;
    case XORASSIGN:
      jj_consume_token(XORASSIGN);
      ret = AssignExpr.AssignOperator.xor;
      break;
    case ORASSIGN:
      jj_consume_token(ORASSIGN);
      ret = AssignExpr.AssignOperator.or;
      break;
    default:
      jj_la1[68] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Conditional expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression ConditionalExpression() throws ParseException {
  Expression ret;
  Expression left;
  Expression right;
    ret = ConditionalOrExpression();
    if (jj_2_23(2)) {
      jj_consume_token(HOOK);
      left = Expression();
      jj_consume_token(COLON);
      right = ConditionalExpression();
      ret = NodeFacade.ConditionalExpr(ret, left, right);
    } else {
      ;
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

//Expression LambdaExpression() ://{//  Expression ret = null;//  Expression left = null;//  Expression right = null;//  BlockStmt blockStmt = null;//  NodeList<Parameter> pars = null;//  CastExpr expr = null;//}//{//  ret = ConditionalOrExpression()//  [//    "->"//    {//      if(ret instanceof NameExpr)//      {//        String name = ((NameExpr) ret).getName();//        pars = NodeFacade.NodeList(NodeFacade.Parameter(0, null, false, NodeFacade.VariableDeclaratorId(name, 0), null));//      } else if(ret instanceof Lambda)//      {//        pars = ((Lambda) ret).getParameters();//      } else if(ret instanceof CastExpr)//      {//        expr = (CastExpr) ret;//        pars = ((Lambda) expr.getExpression()).getParameters();//      } else//      {//        System.err.println(ret.getClass());//        throw new ParseException("LambdaExpression: " + ret.getClass().toString());//      }//    }//    (//      ret = Expression()//      {//        ret = NodeFacade.LambdaExpr(ret, pars);//      }//    | blockStmt = Block()//      {//        ret = NodeFacade.LambdaBlock(blockStmt, pars);//      }//    )//  ]//  {//    if(expr != null)//    {//      expr.setExpression(ret);//      ret = expr;//    }//    return ret;//  }//}  /**
 * Conditional or expression.
 *
 * @return the expression
 * @throws ParseException the parse exception
 */
final public Expression ConditionalOrExpression() throws ParseException {
  Expression ret;
  Expression right;
    ret = ConditionalAndExpression();
    label_28:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SC_OR:
        ;
        break;
      default:
        jj_la1[69] = jj_gen;
        break label_28;
      }
      jj_consume_token(SC_OR);
      right = ConditionalAndExpression();
      ret = NodeFacade.BinaryExpr(ret, right, BinaryExpr.BinaryOperator.or);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Conditional and expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression ConditionalAndExpression() throws ParseException {
  Expression ret;
  Expression right;
    ret = InclusiveOrExpression();
    label_29:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SC_AND:
        ;
        break;
      default:
        jj_la1[70] = jj_gen;
        break label_29;
      }
      jj_consume_token(SC_AND);
      right = InclusiveOrExpression();
      ret = NodeFacade.BinaryExpr(ret, right, BinaryExpr.BinaryOperator.and);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Inclusive or expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression InclusiveOrExpression() throws ParseException {
  Expression ret;
  Expression right;
    ret = ExclusiveOrExpression();
    label_30:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIT_OR:
        ;
        break;
      default:
        jj_la1[71] = jj_gen;
        break label_30;
      }
      jj_consume_token(BIT_OR);
      right = ExclusiveOrExpression();
      ret = NodeFacade.BinaryExpr(ret, right, BinaryExpr.BinaryOperator.binOr);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Exclusive or expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression ExclusiveOrExpression() throws ParseException {
  Expression ret;
  Expression right;
    ret = AndExpression();
    label_31:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case XOR:
        ;
        break;
      default:
        jj_la1[72] = jj_gen;
        break label_31;
      }
      jj_consume_token(XOR);
      right = AndExpression();
      ret = NodeFacade.BinaryExpr(ret, right, BinaryExpr.BinaryOperator.xor);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * And expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression AndExpression() throws ParseException {
  Expression ret;
  Expression right;
    ret = EqualityExpression();
    label_32:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIT_AND:
        ;
        break;
      default:
        jj_la1[73] = jj_gen;
        break label_32;
      }
      jj_consume_token(BIT_AND);
      right = EqualityExpression();
      ret = NodeFacade.BinaryExpr(ret, right, BinaryExpr.BinaryOperator.binAnd);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Equality expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression EqualityExpression() throws ParseException {
  Expression ret;
  Expression right;
  BinaryExpr.BinaryOperator op;
    ret = InstanceOfExpression();
    label_33:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
      case NE:
        ;
        break;
      default:
        jj_la1[74] = jj_gen;
        break label_33;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQ:
        jj_consume_token(EQ);
        op = BinaryExpr.BinaryOperator.equals;
        break;
      case NE:
        jj_consume_token(NE);
        op = BinaryExpr.BinaryOperator.notEquals;
        break;
      default:
        jj_la1[75] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = InstanceOfExpression();
      ret = NodeFacade.BinaryExpr(ret, right, op);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Instance of expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression InstanceOfExpression() throws ParseException {
  Expression ret;
  Type type;
    ret = RelationalExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INSTANCEOF:
      jj_consume_token(INSTANCEOF);
      type = Type();
      ret = NodeFacade.InstanceOfExpr(ret, type);
      break;
    default:
      jj_la1[76] = jj_gen;
      ;
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Relational expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression RelationalExpression() throws ParseException {
  Expression ret;
  Expression right;
  BinaryExpr.BinaryOperator op;
    ret = ShiftExpression();
    label_34:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
      case LE:
      case GE:
      case GT:
        ;
        break;
      default:
        jj_la1[77] = jj_gen;
        break label_34;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
        jj_consume_token(LT);
        op = BinaryExpr.BinaryOperator.less;
        break;
      case GT:
        jj_consume_token(GT);
        op = BinaryExpr.BinaryOperator.greater;
        break;
      case LE:
        jj_consume_token(LE);
        op = BinaryExpr.BinaryOperator.lessEquals;
        break;
      case GE:
        jj_consume_token(GE);
        op = BinaryExpr.BinaryOperator.greaterEquals;
        break;
      default:
        jj_la1[78] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = ShiftExpression();
      ret = NodeFacade.BinaryExpr(ret, right, op);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Shift expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression ShiftExpression() throws ParseException {
  Expression ret;
  Expression right;
  BinaryExpr.BinaryOperator op;
    ret = AdditiveExpression();
    label_35:
    while (true) {
      if (jj_2_24(1)) {
        ;
      } else {
        break label_35;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LSHIFT:
        jj_consume_token(LSHIFT);
        op = BinaryExpr.BinaryOperator.lShift;
        break;
      default:
        jj_la1[79] = jj_gen;
        if (jj_2_25(1)) {
          RSIGNEDSHIFT();
        op = BinaryExpr.BinaryOperator.rSignedShift;
        } else if (jj_2_26(1)) {
          RUNSIGNEDSHIFT();
        op = BinaryExpr.BinaryOperator.rUnsignedShift;
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      right = AdditiveExpression();
      ret = NodeFacade.BinaryExpr(ret, right, op);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Additive expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression AdditiveExpression() throws ParseException {
  Expression ret;
  Expression right;
  BinaryExpr.BinaryOperator op;
    ret = MultiplicativeExpression();
    label_36:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[80] = jj_gen;
        break label_36;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        op = BinaryExpr.BinaryOperator.plus;
        break;
      case MINUS:
        jj_consume_token(MINUS);
        op = BinaryExpr.BinaryOperator.minus;
        break;
      default:
        jj_la1[81] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = MultiplicativeExpression();
      ret = NodeFacade.BinaryExpr(ret, right, op);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Multiplicative expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression MultiplicativeExpression() throws ParseException {
  Expression ret;
  Expression right;
  BinaryExpr.BinaryOperator op;
    ret = UnaryExpression();
    label_37:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
      case SLASH:
      case REM:
        ;
        break;
      default:
        jj_la1[82] = jj_gen;
        break label_37;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
        jj_consume_token(STAR);
        op = BinaryExpr.BinaryOperator.times;
        break;
      case SLASH:
        jj_consume_token(SLASH);
        op = BinaryExpr.BinaryOperator.divide;
        break;
      case REM:
        jj_consume_token(REM);
        op = BinaryExpr.BinaryOperator.remainder;
        break;
      default:
        jj_la1[83] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = UnaryExpression();
      ret = NodeFacade.BinaryExpr(ret, right, op);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Unary expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression UnaryExpression() throws ParseException {
  Expression ret;
  UnaryExpr.UnaryOperator op;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INCR:
      ret = PreIncrementExpression();
      break;
    case DECR:
      ret = PreDecrementExpression();
      break;
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        op = UnaryExpr.UnaryOperator.positive;
        break;
      case MINUS:
        jj_consume_token(MINUS);
        op = UnaryExpr.UnaryOperator.negative;
        break;
      default:
        jj_la1[84] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ret = UnaryExpression();
      ret = NodeFacade.UnaryExpr(ret, op);
      break;
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case AT:
    case BANG:
    case TILDE:
      ret = UnaryExpressionNotPlusMinus();
      break;
    default:
      jj_la1[85] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Pre increment expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PreIncrementExpression() throws ParseException {
  Expression ret;
    jj_consume_token(INCR);
    ret = UnaryExpression();
    ret = NodeFacade.UnaryExpr(ret, UnaryExpr.UnaryOperator.preIncrement);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Pre decrement expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PreDecrementExpression() throws ParseException {
  Expression ret;
    jj_consume_token(DECR);
    ret = UnaryExpression();
    ret = NodeFacade.UnaryExpr(ret, UnaryExpr.UnaryOperator.preDecrement);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Unary expression not plus minus.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression UnaryExpressionNotPlusMinus() throws ParseException {
  Expression ret;
  UnaryExpr.UnaryOperator op;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BANG:
    case TILDE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TILDE:
        jj_consume_token(TILDE);
        op = UnaryExpr.UnaryOperator.inverse;
        break;
      case BANG:
        jj_consume_token(BANG);
        op = UnaryExpr.UnaryOperator.not;
        break;
      default:
        jj_la1[86] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ret = UnaryExpression();
      ret = NodeFacade.UnaryExpr(ret, op);
      break;
    default:
      jj_la1[87] = jj_gen;
      if (jj_2_27(2147483647)) {
        ret = CastExpression();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FALSE:
        case FLOAT:
        case INT:
        case LONG:
        case NEW:
        case NULL:
        case SHORT:
        case SUPER:
        case THIS:
        case TRUE:
        case VOID:
        case LONG_LITERAL:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case AT:
          ret = PostfixExpression();
          break;
        default:
          jj_la1[88] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Postfix expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PostfixExpression() throws ParseException {
  Expression ret;
  UnaryExpr.UnaryOperator op;
    ret = PrimaryExpression();
    if (jj_2_28(2)) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INCR:
        jj_consume_token(INCR);
        op = UnaryExpr.UnaryOperator.posIncrement;
        break;
      case DECR:
        jj_consume_token(DECR);
        op = UnaryExpr.UnaryOperator.posDecrement;
        break;
      default:
        jj_la1[89] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ret = NodeFacade.UnaryExpr(ret, op);
    } else {
      ;
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Cast expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression CastExpression() throws ParseException {
  Expression ret = null;
  Type type = null;
  List<Type> types = null;
  List<AnnotationExpr> ann = null;
    jj_consume_token(LPAREN);
    ann = Annotations();
    if (jj_2_29(2)) {
      type = PrimitiveType();
      type.setAnnotations(NodeFacade.NodeList(ann));
      types = add(types, type);
      label_38:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BIT_AND:
          ;
          break;
        default:
          jj_la1[90] = jj_gen;
          break label_38;
        }
        jj_consume_token(BIT_AND);
        ann = Annotations();
        type = PrimitiveType();
       type.setAnnotations(NodeFacade.NodeList(ann));
       types = add(types, type);
      }
      jj_consume_token(RPAREN);
      ret = UnaryExpression();
      ret = NodeFacade.CastExpr(types, ret);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FLOAT:
      case INT:
      case LONG:
      case SHORT:
      case IDENTIFIER:
        type = ReferenceType();
      type.setAnnotations(NodeFacade.NodeList(ann));
      types = add(types, type);
        label_39:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case BIT_AND:
            ;
            break;
          default:
            jj_la1[91] = jj_gen;
            break label_39;
          }
          jj_consume_token(BIT_AND);
          ann = Annotations();
          type = ReferenceType();
       type.setAnnotations(NodeFacade.NodeList(ann));
       types = add(types, type);
        }
        jj_consume_token(RPAREN);
        ret = UnaryExpressionNotPlusMinus();
      ret = NodeFacade.CastExpr(types, ret);
        break;
      default:
        jj_la1[92] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Primary expression.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PrimaryExpression() throws ParseException {
  Expression ret;
  Expression inner;
    ret = PrimaryPrefix();
    label_40:
    while (true) {
      if (jj_2_30(2)) {
        ;
      } else {
        break label_40;
      }
      ret = PrimarySuffix(ret);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Primary expression without super suffix.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PrimaryExpressionWithoutSuperSuffix() throws ParseException {
  Expression ret;
  Expression inner;
    ret = PrimaryPrefix();
    label_41:
    while (true) {
      if (jj_2_31(2147483647)) {
        ;
      } else {
        break label_41;
      }
      ret = PrimarySuffixWithoutSuper(ret);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Primary prefix.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PrimaryPrefix() throws ParseException {
  Expression ret = null;
  BlockStmt blockStmt = null;
  String name = null;
  List<Type> typeArgs = null;
  List<Expression> args = null;
  List<Parameter> pars = null;
  boolean hasArgs = false;
  boolean isArrow = false;
  Type type = null;
  Parameter par = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
    case NULL:
    case TRUE:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
      ret = Literal();
      break;
    case THIS:
      jj_consume_token(THIS);
      ret = NodeFacade.ThisExpr();
      break;
    case SUPER:
      jj_consume_token(SUPER);
      ret = NodeFacade.SuperExpr();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOT:
        jj_consume_token(DOT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LT:
          typeArgs = TypeArguments();
          break;
        default:
          jj_la1[93] = jj_gen;
          ;
        }
        jj_consume_token(IDENTIFIER);
        name = token.image;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAREN:
          args = Arguments();
          hasArgs = true;
          break;
        default:
          jj_la1[94] = jj_gen;
          ;
        }
        ret = hasArgs ? NodeFacade.MethodCallExpr(ret, typeArgs, name, args) : NodeFacade.FieldAccessExpr(ret, null, name);
        break;
      case 130:
        jj_consume_token(130);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LT:
          typeArgs = TypeArguments();
          break;
        default:
          jj_la1[95] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
          jj_consume_token(IDENTIFIER);
          break;
        case NEW:
          jj_consume_token(NEW);
          break;
        default:
          jj_la1[96] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        ret = NodeFacade.MethodExprRef(ret, typeArgs, token.image);
        break;
      default:
        jj_la1[97] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[105] = jj_gen;
      if (jj_2_32(2147483647)) {
        pars = FormalParameters();
        jj_consume_token(131);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACE:
          blockStmt = Block();
         ret = NodeFacade.LambdaBlock(blockStmt, NodeFacade.NodeList(pars));
          break;
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FALSE:
        case FLOAT:
        case INT:
        case LONG:
        case NEW:
        case NULL:
        case SHORT:
        case SUPER:
        case THIS:
        case TRUE:
        case VOID:
        case LONG_LITERAL:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case AT:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          ret = Expression();
         ret = NodeFacade.LambdaExpr(ret, NodeFacade.NodeList(pars));
          break;
        default:
          jj_la1[98] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } else if (jj_2_33(2147483647)) {
        pars = LambdaParameters();
        jj_consume_token(131);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACE:
          blockStmt = Block();
         ret = NodeFacade.LambdaBlock(blockStmt, NodeFacade.NodeList(pars));
          break;
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FALSE:
        case FLOAT:
        case INT:
        case LONG:
        case NEW:
        case NULL:
        case SHORT:
        case SUPER:
        case THIS:
        case TRUE:
        case VOID:
        case LONG_LITERAL:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case AT:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          ret = Expression();
         ret = NodeFacade.LambdaExpr(ret, NodeFacade.NodeList(pars));
          break;
        default:
          jj_la1[99] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAREN:
          jj_consume_token(LPAREN);
          ret = Expression();
          jj_consume_token(RPAREN);
      ret = NodeFacade.EnclosedExpr(ret);
          break;
        case NEW:
          ret = AllocationExpression(null);
          break;
        default:
          jj_la1[106] = jj_gen;
          if (jj_2_34(2147483647)) {
            type = ResultType();
            jj_consume_token(DOT);
            jj_consume_token(CLASS);
      ret = NodeFacade.ClassExpr(type);
          } else if (jj_2_35(2147483647)) {
            type = ResultType();
            jj_consume_token(130);
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case LT:
              typeArgs = TypeArguments();
              break;
            default:
              jj_la1[100] = jj_gen;
              ;
            }
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case IDENTIFIER:
              jj_consume_token(IDENTIFIER);
              break;
            case NEW:
              jj_consume_token(NEW);
              break;
            default:
              jj_la1[101] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
      ret = NodeFacade.MethodTypeRef(type, typeArgs, token.image);
          } else {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case IDENTIFIER:
              jj_consume_token(IDENTIFIER);
      name = token.image;
              switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
              case LPAREN:
              case 131:
                switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
                case 131:
                  jj_consume_token(131);
        isArrow = true;
        pars = NodeFacade.NodeList(NodeFacade.Parameter(0, null, null, NodeFacade.VariableDeclaratorId(name, null), null));
                  switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
                  case LBRACE:
                    blockStmt = Block();
         ret = NodeFacade.LambdaBlock(blockStmt, NodeFacade.NodeList(pars));
                    break;
                  case BOOLEAN:
                  case BYTE:
                  case CHAR:
                  case DOUBLE:
                  case FALSE:
                  case FLOAT:
                  case INT:
                  case LONG:
                  case NEW:
                  case NULL:
                  case SHORT:
                  case SUPER:
                  case THIS:
                  case TRUE:
                  case VOID:
                  case LONG_LITERAL:
                  case INTEGER_LITERAL:
                  case FLOATING_POINT_LITERAL:
                  case CHARACTER_LITERAL:
                  case STRING_LITERAL:
                  case IDENTIFIER:
                  case LPAREN:
                  case AT:
                  case BANG:
                  case TILDE:
                  case INCR:
                  case DECR:
                  case PLUS:
                  case MINUS:
                    ret = Expression();
         ret = NodeFacade.LambdaExpr(ret, NodeFacade.NodeList(pars));
                    break;
                  default:
                    jj_la1[102] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
                  }
                  break;
                case LPAREN:
                  args = Arguments();
        hasArgs = true;
                  break;
                default:
                  jj_la1[103] = jj_gen;
                  jj_consume_token(-1);
                  throw new ParseException();
                }
                break;
              default:
                jj_la1[104] = jj_gen;
                ;
              }
      if(isArrow)
      {
        {if (true) return ret;}
      } else
      {
        ret = hasArgs ? NodeFacade.MethodCallExpr(null, null, name, args) : NodeFacade.NameExpr(name);
      }
              break;
            default:
              jj_la1[107] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
          }
        }
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Primary suffix.
   *
   * @param scope the scope
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PrimarySuffix(Expression scope) throws ParseException {
  Expression ret;
    if (jj_2_36(2)) {
      ret = PrimarySuffixWithoutSuper(scope);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOT:
        jj_consume_token(DOT);
        jj_consume_token(SUPER);
      ret = NodeFacade.SuperExpr(scope);
        break;
      default:
        jj_la1[108] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Primary suffix without super.
   *
   * @param scope the scope
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression PrimarySuffixWithoutSuper(Expression scope) throws ParseException {
  Expression ret = null;
  List<Type> typeArgs = null;
  NodeList<Parameter> pars = null;
  List<Expression> args = null;
  boolean hasArgs = false;
  String name = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 130:
      jj_consume_token(130);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
        typeArgs = TypeArguments();
        break;
      default:
        jj_la1[109] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        jj_consume_token(IDENTIFIER);
        break;
      case NEW:
        jj_consume_token(NEW);
        break;
      default:
        jj_la1[110] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ret = NodeFacade.MethodExprRef(scope, typeArgs, token.image);
      break;
    case DOT:
      jj_consume_token(DOT);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case THIS:
        jj_consume_token(THIS);
        ret = NodeFacade.ThisExpr(scope);
        break;
      case NEW:
        ret = AllocationExpression(scope);
        break;
      default:
        jj_la1[113] = jj_gen;
        if (jj_2_37(2147483647)) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LT:
            typeArgs = TypeArguments();
            break;
          default:
            jj_la1[111] = jj_gen;
            ;
          }
          jj_consume_token(IDENTIFIER);
        name = token.image;
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LPAREN:
            args = Arguments();
          hasArgs = true;
            break;
          default:
            jj_la1[112] = jj_gen;
            ;
          }
        ret = hasArgs ? NodeFacade.MethodCallExpr(scope, typeArgs, name, args) : NodeFacade.FieldAccessExpr(scope, typeArgs, name);
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    case LBRACKET:
      jj_consume_token(LBRACKET);
      ret = Expression();
      jj_consume_token(RBRACKET);
      ret = NodeFacade.ArrayAccessExpr(scope, ret);
      break;
    default:
      jj_la1[114] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Literal.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression Literal() throws ParseException {
  Expression ret;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      jj_consume_token(INTEGER_LITERAL);
      ret = NodeFacade.IntegerLiteralExpr(token.image);
      break;
    case LONG_LITERAL:
      jj_consume_token(LONG_LITERAL);
      ret = NodeFacade.LongLiteralExpr(token.image);
      break;
    case FLOATING_POINT_LITERAL:
      jj_consume_token(FLOATING_POINT_LITERAL);
      ret = NodeFacade.DoubleLiteralExpr(token.image);
      break;
    case CHARACTER_LITERAL:
      jj_consume_token(CHARACTER_LITERAL);
      ret = NodeFacade.CharLiteralExpr(token.image);
      break;
    case STRING_LITERAL:
      jj_consume_token(STRING_LITERAL);
      ret = NodeFacade.StringLiteralExpr(token.image.substring(1, token.image.length() - 1));
      break;
    case FALSE:
    case TRUE:
      ret = BooleanLiteral();
      break;
    case NULL:
      ret = NullLiteral();
      break;
    default:
      jj_la1[115] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Boolean literal.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression BooleanLiteral() throws ParseException {
  Expression ret;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      jj_consume_token(TRUE);
      ret = NodeFacade.BooleanLiteralExpr(true);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      ret = NodeFacade.BooleanLiteralExpr(false);
      break;
    default:
      jj_la1[116] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Null literal.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression NullLiteral() throws ParseException {
    jj_consume_token(NULL);
    {if (true) return NodeFacade.NullLiteralExpr();}
    throw new Error("Missing return statement in function");
  }

  /**
   * Arguments.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Expression> Arguments() throws ParseException {
  List<Expression> ret = null;
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case AT:
    case BANG:
    case TILDE:
    case INCR:
    case DECR:
    case PLUS:
    case MINUS:
      ret = ArgumentList();
      break;
    default:
      jj_la1[117] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Argument list.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Expression> ArgumentList() throws ParseException {
  List<Expression> ret = new LinkedList<Expression>();
  Expression expr;
    expr = Expression();
    ret.add(expr);
    label_42:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[118] = jj_gen;
        break label_42;
      }
      jj_consume_token(COMMA);
      expr = Expression();
      ret.add(expr);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Allocation expression.
   *
   * @param scope the scope
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression AllocationExpression(Expression scope) throws ParseException {
  Expression ret;
  Type type;
  ArrayDimsAndInits arr = null;
  List<Type> typeArgs = null;
  List<BodyDeclaration> anonymousBody = null;
  List<Expression> args;
  List<AnnotationExpr> ann = null;
    jj_consume_token(NEW);
    ann = Annotations();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FLOAT:
    case INT:
    case LONG:
    case SHORT:
      type = PrimitiveType();
      arr = ArrayDimsAndInits();
      ret = NodeFacade.ArrayCreationExpr(type, arr.slots, arr.initializer);
      break;
    default:
      jj_la1[120] = jj_gen;
      if (jj_2_39(2147483647)) {
        type = ClassOrInterfaceType();
        arr = ArrayDimsAndInits();
      ret = NodeFacade.ArrayCreationExpr(type, arr.slots, arr.initializer);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
        case LT:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LT:
            typeArgs = TypeArguments();
            break;
          default:
            jj_la1[119] = jj_gen;
            ;
          }
          type = ClassOrInterfaceType();
          args = Arguments();
          if (jj_2_38(2)) {
            anonymousBody = ClassOrInterfaceBody(false);
          } else {
            ;
          }
      ret = NodeFacade.ObjectCreationExpr(scope, (ClassOrInterfaceType) type, typeArgs, args, anonymousBody);
          break;
        default:
          jj_la1[121] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    type.setAnnotations(NodeFacade.NodeList(ann));
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

/*
 * The third LOOKAHEAD specification below is to parse to PrimarySuffix
 * if there is an expression between the "[...]".
 */
  /**
 * Array dims and inits.
 *
 * @return the array dims and inits
 * @throws ParseException the parse exception
 */
final public ArrayDimsAndInits ArrayDimsAndInits() throws ParseException {
  ArrayDimsAndInits ret = null;
  ArrayInitializerExpr initializer = null;
  Expression expression = null;
  List<ArraySlot> slots = null;
  ArraySlot slot = null;
  List<AnnotationExpr> ann = null;
    if (jj_2_42(2147483647)) {
      label_43:
      while (true) {
        ann = Annotations();
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      slot = NodeFacade.ArraySlot(ann);
      slots = add(slots, slot);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACKET:
        case AT:
          ;
          break;
        default:
          jj_la1[122] = jj_gen;
          break label_43;
        }
      }
      initializer = ArrayInitializer();
      ret = new ArrayDimsAndInits(slots, initializer);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACKET:
      case AT:
        label_44:
        while (true) {
          ann = Annotations();
          jj_consume_token(LBRACKET);
          expression = Expression();
        slot = NodeFacade.ArraySlot(expression, ann);
        slots = add(slots, slot);
          jj_consume_token(RBRACKET);
          if (jj_2_40(2147483647)) {
            ;
          } else {
            break label_44;
          }
        }
        label_45:
        while (true) {
          if (jj_2_41(2147483647)) {
            ;
          } else {
            break label_45;
          }
          ann = Annotations();
          jj_consume_token(LBRACKET);
          jj_consume_token(RBRACKET);
        slot = NodeFacade.ArraySlot(ann);
        slots = add(slots, slot);
        }
      ret = new ArrayDimsAndInits(slots, initializer);
        break;
      default:
        jj_la1[123] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

/*
 * Statement syntax follows.
 */
  /**
 * Statement.
 *
 * @return the statement
 * @throws ParseException the parse exception
 */
final public Statement Statement() throws ParseException {
  Statement ret = null;
    if (jj_2_43(2)) {
      ret = LabeledStatement();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASSERT:
        ret = AssertStatement();
        break;
      case LBRACE:
        ret = Block();
        break;
      case SEMICOLON:
        ret = EmptyStatement();
        break;
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FALSE:
      case FLOAT:
      case INT:
      case LONG:
      case NEW:
      case NULL:
      case SHORT:
      case SUPER:
      case THIS:
      case TRUE:
      case VOID:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case AT:
      case INCR:
      case DECR:
        ret = StatementExpression();
        break;
      case SWITCH:
        ret = SwitchStatement();
        break;
      case IF:
        ret = IfStatement();
        break;
      case WHILE:
        ret = WhileStatement();
        break;
      case DO:
        ret = DoStatement();
        break;
      case FOR:
        ret = ForStatement();
        break;
      case BREAK:
        ret = BreakStatement();
        break;
      case CONTINUE:
        ret = ContinueStatement();
        break;
      case RETURN:
        ret = ReturnStatement();
        break;
      case THROW:
        ret = ThrowStatement();
        break;
      case SYNCHRONIZED:
        ret = SynchronizedStatement();
        break;
      case TRY:
        ret = TryStatement();
        break;
      default:
        jj_la1[124] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Assert statement.
   *
   * @return the assert stmt
   * @throws ParseException the parse exception
   */
  final public AssertStmt AssertStatement() throws ParseException {
  Expression check;
  Expression msg = null;
    jj_consume_token(ASSERT);
    check = Expression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COLON:
      jj_consume_token(COLON);
      msg = Expression();
      break;
    default:
      jj_la1[125] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.AssertStmt(check, msg);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Labeled statement.
   *
   * @return the labeled stmt
   * @throws ParseException the parse exception
   */
  final public LabeledStmt LabeledStatement() throws ParseException {
  String label;
  Statement stmt;
    jj_consume_token(IDENTIFIER);
    label = token.image;
    jj_consume_token(COLON);
    stmt = Statement();
    {if (true) return NodeFacade.LabeledStmt(label, stmt);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Block.
   *
   * @return the block stmt
   * @throws ParseException the parse exception
   */
  final public BlockStmt Block() throws ParseException {
  List<Statement> stmts;
    jj_consume_token(LBRACE);
    stmts = Statements();
    jj_consume_token(RBRACE);
    {if (true) return NodeFacade.BlockStmt(stmts);}
    throw new Error("Missing return statement in function");
  }

/*
 * Classes inside block stametents can only be abstract or final. The semantic must check it.
 */
  /**
 * Block statement.
 *
 * @return the statement
 * @throws ParseException the parse exception
 */
final public Statement BlockStatement() throws ParseException {
  Statement ret;
  Expression expr;
  TypeDeclaration typeDecl;
  Modifier modifier;
    if (jj_2_44(2147483647)) {
      pushJavadoc();
      modifier = Modifiers();
      typeDecl = ClassOrInterfaceDeclaration(modifier);
      ret = NodeFacade.TypeDeclarationStmt(typeDecl);
    } else if (jj_2_45(2147483647)) {
      expr = VariableDeclarationExpression();
      jj_consume_token(SEMICOLON);
      ret = NodeFacade.ExpressionStmt(expr);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASSERT:
      case BOOLEAN:
      case BREAK:
      case BYTE:
      case CHAR:
      case CONTINUE:
      case DO:
      case DOUBLE:
      case FALSE:
      case FLOAT:
      case FOR:
      case IF:
      case INT:
      case LONG:
      case NEW:
      case NULL:
      case RETURN:
      case SHORT:
      case SUPER:
      case SWITCH:
      case SYNCHRONIZED:
      case THIS:
      case THROW:
      case TRUE:
      case TRY:
      case VOID:
      case WHILE:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case LBRACE:
      case SEMICOLON:
      case AT:
      case INCR:
      case DECR:
        ret = Statement();
        break;
      default:
        jj_la1[126] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Variable declaration expression.
   *
   * @return the variable declaration expr
   * @throws ParseException the parse exception
   */
  final public VariableDeclarationExpr VariableDeclarationExpression() throws ParseException {
  Modifier modifier = null;
  Type type = null;
  List<VariableDeclarator> vars = new LinkedList<VariableDeclarator>();
  VariableDeclarator var = null;
    modifier = Modifiers();
    type = TypeNoAnnotations();
    var = VariableDeclarator();
    vars.add(var);
    label_46:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[127] = jj_gen;
        break label_46;
      }
      jj_consume_token(COMMA);
      var = VariableDeclarator();
      vars.add(var);
    }
    {if (true) return NodeFacade.VariableDeclarationExpr(modifier.modifiers, type, vars, modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Empty statement.
   *
   * @return the empty stmt
   * @throws ParseException the parse exception
   */
  final public EmptyStmt EmptyStatement() throws ParseException {
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.EmptyStmt();}
    throw new Error("Missing return statement in function");
  }

  /**
   * Statement expression.
   *
   * @return the expression stmt
   * @throws ParseException the parse exception
   */
  final public ExpressionStmt StatementExpression() throws ParseException {
  Expression expr;
  AssignExpr.AssignOperator op;
  Expression value;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INCR:
      expr = PreIncrementExpression();
      break;
    case DECR:
      expr = PreDecrementExpression();
      break;
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case AT:
      expr = PrimaryExpression();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASSIGN:
      case INCR:
      case DECR:
      case PLUSASSIGN:
      case MINUSASSIGN:
      case STARASSIGN:
      case SLASHASSIGN:
      case ANDASSIGN:
      case ORASSIGN:
      case XORASSIGN:
      case REMASSIGN:
      case LSHIFTASSIGN:
      case RSIGNEDSHIFTASSIGN:
      case RUNSIGNEDSHIFTASSIGN:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INCR:
          jj_consume_token(INCR);
        expr = NodeFacade.UnaryExpr(expr, UnaryExpr.UnaryOperator.posIncrement);
          break;
        case DECR:
          jj_consume_token(DECR);
        expr = NodeFacade.UnaryExpr(expr, UnaryExpr.UnaryOperator.posDecrement);
          break;
        case ASSIGN:
        case PLUSASSIGN:
        case MINUSASSIGN:
        case STARASSIGN:
        case SLASHASSIGN:
        case ANDASSIGN:
        case ORASSIGN:
        case XORASSIGN:
        case REMASSIGN:
        case LSHIFTASSIGN:
        case RSIGNEDSHIFTASSIGN:
        case RUNSIGNEDSHIFTASSIGN:
          op = AssignmentOperator();
          value = Expression();
        expr = NodeFacade.AssignExpr(expr, value, op);
          break;
        default:
          jj_la1[128] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[129] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[130] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.ExpressionStmt(expr);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Switch statement.
   *
   * @return the switch stmt
   * @throws ParseException the parse exception
   */
  final public SwitchStmt SwitchStatement() throws ParseException {
  Expression selector;
  SwitchEntryStmt entry;
  List<SwitchEntryStmt> entries = null;
    jj_consume_token(SWITCH);
    jj_consume_token(LPAREN);
    selector = Expression();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    label_47:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CASE:
      case _DEFAULT:
        ;
        break;
      default:
        jj_la1[131] = jj_gen;
        break label_47;
      }
      entry = SwitchEntry();
      entries = add(entries, entry);
    }
    jj_consume_token(RBRACE);
    {if (true) return NodeFacade.SwitchStmt(selector, entries);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Switch entry.
   *
   * @return the switch entry stmt
   * @throws ParseException the parse exception
   */
  final public SwitchEntryStmt SwitchEntry() throws ParseException {
  Expression label = null;
  List<Statement> stmts;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CASE:
      jj_consume_token(CASE);
      label = Expression();
      break;
    case _DEFAULT:
      jj_consume_token(_DEFAULT);
      break;
    default:
      jj_la1[132] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(COLON);
    stmts = Statements();
    {if (true) return NodeFacade.SwitchEntryStmt(label, stmts);}
    throw new Error("Missing return statement in function");
  }

  /**
   * If statement.
   *
   * @return the if stmt
   * @throws ParseException the parse exception
   */
  final public IfStmt IfStatement() throws ParseException {
  Expression condition;
  Statement thenStmt;
  Statement elseStmt = null;
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    condition = Expression();
    jj_consume_token(RPAREN);
    thenStmt = Statement();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      elseStmt = Statement();
      break;
    default:
      jj_la1[133] = jj_gen;
      ;
    }
    {if (true) return NodeFacade.IfStmt(condition, thenStmt, elseStmt);}
    throw new Error("Missing return statement in function");
  }

  /**
   * While statement.
   *
   * @return the while stmt
   * @throws ParseException the parse exception
   */
  final public WhileStmt WhileStatement() throws ParseException {
  Expression condition;
  Statement body;
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    condition = Expression();
    jj_consume_token(RPAREN);
    body = Statement();
    {if (true) return NodeFacade.WhileStmt(condition, body);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Do statement.
   *
   * @return the do stmt
   * @throws ParseException the parse exception
   */
  final public DoStmt DoStatement() throws ParseException {
  Expression condition;
  Statement body;
    jj_consume_token(DO);
    body = Statement();
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    condition = Expression();
    jj_consume_token(RPAREN);
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.DoStmt(body, condition);}
    throw new Error("Missing return statement in function");
  }

  /**
   * For statement.
   *
   * @return the statement
   * @throws ParseException the parse exception
   */
  final public Statement ForStatement() throws ParseException {
  String id = null;
  VariableDeclarationExpr varExpr = null;
  Expression expr = null;
  List<Expression> init = null;
  List<Expression> update = null;
  Statement body;
    jj_consume_token(FOR);
    jj_consume_token(LPAREN);
    if (jj_2_46(2147483647)) {
      varExpr = VariableDeclarationExpression();
      jj_consume_token(COLON);
      expr = Expression();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABSTRACT:
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FALSE:
      case FINAL:
      case FLOAT:
      case INT:
      case LONG:
      case NATIVE:
      case NEW:
      case NULL:
      case PRIVATE:
      case PROTECTED:
      case PUBLIC:
      case SHORT:
      case STATIC:
      case STRICTFP:
      case SUPER:
      case SYNCHRONIZED:
      case THIS:
      case TRANSIENT:
      case TRUE:
      case VOID:
      case VOLATILE:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case SEMICOLON:
      case AT:
      case BANG:
      case TILDE:
      case INCR:
      case DECR:
      case PLUS:
      case MINUS:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ABSTRACT:
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FALSE:
        case FINAL:
        case FLOAT:
        case INT:
        case LONG:
        case NATIVE:
        case NEW:
        case NULL:
        case PRIVATE:
        case PROTECTED:
        case PUBLIC:
        case SHORT:
        case STATIC:
        case STRICTFP:
        case SUPER:
        case SYNCHRONIZED:
        case THIS:
        case TRANSIENT:
        case TRUE:
        case VOID:
        case VOLATILE:
        case LONG_LITERAL:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case AT:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          init = ForInit();
          break;
        default:
          jj_la1[134] = jj_gen;
          ;
        }
        jj_consume_token(SEMICOLON);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FALSE:
        case FLOAT:
        case INT:
        case LONG:
        case NEW:
        case NULL:
        case SHORT:
        case SUPER:
        case THIS:
        case TRUE:
        case VOID:
        case LONG_LITERAL:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case AT:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          expr = Expression();
          break;
        default:
          jj_la1[135] = jj_gen;
          ;
        }
        jj_consume_token(SEMICOLON);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FALSE:
        case FLOAT:
        case INT:
        case LONG:
        case NEW:
        case NULL:
        case SHORT:
        case SUPER:
        case THIS:
        case TRUE:
        case VOID:
        case LONG_LITERAL:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case CHARACTER_LITERAL:
        case STRING_LITERAL:
        case IDENTIFIER:
        case LPAREN:
        case AT:
        case BANG:
        case TILDE:
        case INCR:
        case DECR:
        case PLUS:
        case MINUS:
          update = ForUpdate();
          break;
        default:
          jj_la1[136] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[137] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(RPAREN);
    body = Statement();
    if (varExpr != null)
    {
      {if (true) return NodeFacade.ForeachStmt(varExpr, expr, body);}
    }
    {if (true) return NodeFacade.ForStmt(init, expr, update, body);}
    throw new Error("Missing return statement in function");
  }

  /**
   * For init.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Expression> ForInit() throws ParseException {
  List<Expression> ret;
  Expression expr;
    if (jj_2_47(2147483647)) {
      expr = VariableDeclarationExpression();
      ret = new LinkedList<Expression>();
      ret.add(expr);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FALSE:
      case FLOAT:
      case INT:
      case LONG:
      case NEW:
      case NULL:
      case SHORT:
      case SUPER:
      case THIS:
      case TRUE:
      case VOID:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case AT:
      case BANG:
      case TILDE:
      case INCR:
      case DECR:
      case PLUS:
      case MINUS:
        ret = ExpressionList();
        break;
      default:
        jj_la1[138] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Expression list.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Expression> ExpressionList() throws ParseException {
  List<Expression> ret = new LinkedList<Expression>();
  Expression expr;
    expr = Expression();
    ret.add(expr);
    label_48:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[139] = jj_gen;
        break label_48;
      }
      jj_consume_token(COMMA);
      expr = Expression();
      ret.add(expr);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * For update.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<Expression> ForUpdate() throws ParseException {
  List<Expression> ret;
    ret = ExpressionList();
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Break statement.
   *
   * @return the break stmt
   * @throws ParseException the parse exception
   */
  final public BreakStmt BreakStatement() throws ParseException {
  String id = null;
    jj_consume_token(BREAK);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      jj_consume_token(IDENTIFIER);
      id = token.image;
      break;
    default:
      jj_la1[140] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.BreakStmt(id);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Continue statement.
   *
   * @return the continue stmt
   * @throws ParseException the parse exception
   */
  final public ContinueStmt ContinueStatement() throws ParseException {
  String id = null;
    jj_consume_token(CONTINUE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      jj_consume_token(IDENTIFIER);
      id = token.image;
      break;
    default:
      jj_la1[141] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.ContinueStmt(id);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Return statement.
   *
   * @return the return stmt
   * @throws ParseException the parse exception
   */
  final public ReturnStmt ReturnStatement() throws ParseException {
  Expression expr = null;
    jj_consume_token(RETURN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case AT:
    case BANG:
    case TILDE:
    case INCR:
    case DECR:
    case PLUS:
    case MINUS:
      expr = Expression();
      break;
    default:
      jj_la1[142] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.ReturnStmt(expr);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Throw statement.
   *
   * @return the throw stmt
   * @throws ParseException the parse exception
   */
  final public ThrowStmt ThrowStatement() throws ParseException {
  Expression expr;
    jj_consume_token(THROW);
    expr = Expression();
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.ThrowStmt(expr);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Synchronized statement.
   *
   * @return the synchronized stmt
   * @throws ParseException the parse exception
   */
  final public SynchronizedStmt SynchronizedStatement() throws ParseException {
  Expression expr;
  BlockStmt block;
    jj_consume_token(SYNCHRONIZED);
    jj_consume_token(LPAREN);
    expr = Expression();
    jj_consume_token(RPAREN);
    block = Block();
    {if (true) return NodeFacade.SynchronizedStmt(expr, block);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Try statement.
   *
   * @return the try stmt
   * @throws ParseException the parse exception
   */
  final public TryStmt TryStatement() throws ParseException {
  BlockStmt tryBlock;
  BlockStmt finallyBlock = null;
  List<CatchClause> catchs = null;
  Parameter except = null;
  BlockStmt catchBlock = null;
  List<VariableDeclarationExpr> resources = null;
  VariableDeclarationExpr resource = null;
  List<Type> types = null;
  Type type = null;
  String name = null;
  boolean isFinal = false;
    jj_consume_token(TRY);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
      label_49:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ABSTRACT:
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FINAL:
        case FLOAT:
        case INT:
        case LONG:
        case NATIVE:
        case PRIVATE:
        case PROTECTED:
        case PUBLIC:
        case SHORT:
        case STATIC:
        case STRICTFP:
        case SYNCHRONIZED:
        case TRANSIENT:
        case VOLATILE:
        case IDENTIFIER:
        case AT:
          ;
          break;
        default:
          jj_la1[143] = jj_gen;
          break label_49;
        }
        resource = VariableDeclarationExpression();
       resources = add(resources, resource);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEMICOLON:
          jj_consume_token(SEMICOLON);
          break;
        default:
          jj_la1[144] = jj_gen;
          ;
        }
      }
      jj_consume_token(RPAREN);
      break;
    default:
      jj_la1[145] = jj_gen;
      ;
    }
    tryBlock = Block();
    label_50:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CATCH:
        ;
        break;
      default:
        jj_la1[146] = jj_gen;
        break label_50;
      }
      jj_consume_token(CATCH);
      jj_consume_token(LPAREN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FINAL:
        jj_consume_token(FINAL);
         isFinal = true;
        break;
      default:
        jj_la1[147] = jj_gen;
        ;
      }
      type = Type();
        types = add(types, type);
      label_51:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BIT_OR:
          ;
          break;
        default:
          jj_la1[148] = jj_gen;
          break label_51;
        }
        jj_consume_token(BIT_OR);
        type = Type();
          types = add(types, type);
      }
      jj_consume_token(IDENTIFIER);
        name = token.image;
      jj_consume_token(RPAREN);
      catchBlock = Block();
        catchs = add(catchs, NodeFacade.CatchClause(isFinal, types, name, catchBlock));
        types = null;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FINALLY:
      jj_consume_token(FINALLY);
      finallyBlock = Block();
      break;
    default:
      jj_la1[149] = jj_gen;
      ;
    }
    {if (true) return NodeFacade.TryStmt(resources, tryBlock, catchs, finallyBlock);}
    throw new Error("Missing return statement in function");
  }

/* We use productions to match >>>, >> and > so that we can keep the
 * type declaration syntax with generics clean
 */
  /**
 * Runsignedshift.
 *
 * @throws ParseException the parse exception
 */
final public void RUNSIGNEDSHIFT() throws ParseException {
    if (getToken(1).kind == GT && ((GTToken) getToken(1)).realKind == RUNSIGNEDSHIFT) {

    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(GT);
    jj_consume_token(GT);
    jj_consume_token(GT);
  }

  /**
   * Rsignedshift.
   *
   * @throws ParseException the parse exception
   */
  final public void RSIGNEDSHIFT() throws ParseException {
    if (getToken(1).kind == GT && ((GTToken) getToken(1)).realKind == RSIGNEDSHIFT) {

    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(GT);
    jj_consume_token(GT);
  }

/* Annotation syntax follows. */
  /**
 * Annotation.
 *
 * @return the annotation expr
 * @throws ParseException the parse exception
 */
final public AnnotationExpr Annotation() throws ParseException {
  AnnotationExpr ret;
    if (jj_2_48(2147483647)) {
      ret = NormalAnnotation();
    } else if (jj_2_49(2147483647)) {
      ret = SingleMemberAnnotation();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AT:
        ret = MarkerAnnotation();
        break;
      default:
        jj_la1[150] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Annotations.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<AnnotationExpr> Annotations() throws ParseException {
  List<AnnotationExpr> annotations = null;
  AnnotationExpr ann;
    label_52:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AT:
        ;
        break;
      default:
        jj_la1[151] = jj_gen;
        break label_52;
      }
      ann = Annotation();
      annotations = add(annotations, ann);
    }
    {if (true) return annotations;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Normal annotation.
   *
   * @return the normal annotation expr
   * @throws ParseException the parse exception
   */
  final public NormalAnnotationExpr NormalAnnotation() throws ParseException {
  NameExpr name;
  List<MemberValuePair> pairs = null;
    jj_consume_token(AT);
    name = Name();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      pairs = MemberValuePairs();
      break;
    default:
      jj_la1[152] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return NodeFacade.NormalAnnotationExpr(pairs, name);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Marker annotation.
   *
   * @return the marker annotation expr
   * @throws ParseException the parse exception
   */
  final public MarkerAnnotationExpr MarkerAnnotation() throws ParseException {
  NameExpr name;
    jj_consume_token(AT);
    name = Name();
    {if (true) return NodeFacade.MarkerAnnotationExpr(name);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Single member annotation.
   *
   * @return the single member annotation expr
   * @throws ParseException the parse exception
   */
  final public SingleMemberAnnotationExpr SingleMemberAnnotation() throws ParseException {
  NameExpr name;
  Expression memberVal;
    jj_consume_token(AT);
    name = Name();
    jj_consume_token(LPAREN);
    memberVal = MemberValue();
    jj_consume_token(RPAREN);
    {if (true) return NodeFacade.SingleMemberAnnotationExpr(memberVal, name);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Member value pairs.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<MemberValuePair> MemberValuePairs() throws ParseException {
  List<MemberValuePair> ret = new LinkedList<MemberValuePair>();
  MemberValuePair pair;
    pair = MemberValuePair();
    ret.add(pair);
    label_53:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[153] = jj_gen;
        break label_53;
      }
      jj_consume_token(COMMA);
      pair = MemberValuePair();
      ret.add(pair);
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Member value pair.
   *
   * @return the member value pair
   * @throws ParseException the parse exception
   */
  final public MemberValuePair MemberValuePair() throws ParseException {
  String name;
  Expression value;
    jj_consume_token(IDENTIFIER);
    name = token.image;
    jj_consume_token(ASSIGN);
    value = MemberValue();
    {if (true) return NodeFacade.MemberValuePair(name, value);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Member value.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression MemberValue() throws ParseException {
  Expression ret;
    if (jj_2_50(3)) {
      ret = Annotation();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
        ret = MemberValueArrayInitializer();
        break;
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case DOUBLE:
      case FALSE:
      case FLOAT:
      case INT:
      case LONG:
      case NEW:
      case NULL:
      case SHORT:
      case SUPER:
      case THIS:
      case TRUE:
      case VOID:
      case LONG_LITERAL:
      case INTEGER_LITERAL:
      case FLOATING_POINT_LITERAL:
      case CHARACTER_LITERAL:
      case STRING_LITERAL:
      case IDENTIFIER:
      case LPAREN:
      case AT:
      case BANG:
      case TILDE:
      case INCR:
      case DECR:
      case PLUS:
      case MINUS:
        ret = ConditionalExpression();
        break;
      default:
        jj_la1[154] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Member value array initializer.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression MemberValueArrayInitializer() throws ParseException {
  List<Expression> ret = null;
  Expression member;
    jj_consume_token(LBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case DOUBLE:
    case FALSE:
    case FLOAT:
    case INT:
    case LONG:
    case NEW:
    case NULL:
    case SHORT:
    case SUPER:
    case THIS:
    case TRUE:
    case VOID:
    case LONG_LITERAL:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case CHARACTER_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case LBRACE:
    case AT:
    case BANG:
    case TILDE:
    case INCR:
    case DECR:
    case PLUS:
    case MINUS:
      member = MemberValue();
      ret = add(ret, member);
      label_54:
      while (true) {
        if (jj_2_51(2)) {
          ;
        } else {
          break label_54;
        }
        jj_consume_token(COMMA);
        member = MemberValue();
        ret = add(ret, member);
      }
      break;
    default:
      jj_la1[155] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      break;
    default:
      jj_la1[156] = jj_gen;
      ;
    }
    jj_consume_token(RBRACE);
    {if (true) return NodeFacade.ArrayInitializerExpr(ret);}
    throw new Error("Missing return statement in function");
  }

/* Annotation Types. */
  /**
 * Annotation type declaration.
 *
 * @param modifier the modifier
 * @return the annotation declaration
 * @throws ParseException the parse exception
 */
final public AnnotationDeclaration AnnotationTypeDeclaration(Modifier modifier) throws ParseException {
  String name;
  List<BodyDeclaration> members;
    jj_consume_token(AT);
    jj_consume_token(INTERFACE);
    jj_consume_token(IDENTIFIER);
    name = token.image;
    members = AnnotationTypeBody();
    {if (true) return NodeFacade.AnnotationDeclaration(modifier.modifiers, name, members, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Annotation type body.
   *
   * @return the list
   * @throws ParseException the parse exception
   */
  final public List<BodyDeclaration> AnnotationTypeBody() throws ParseException {
  List<BodyDeclaration> ret = null;
  BodyDeclaration member;
    jj_consume_token(LBRACE);
    label_55:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABSTRACT:
      case BOOLEAN:
      case BYTE:
      case CHAR:
      case CLASS:
      case DOUBLE:
      case ENUM:
      case FINAL:
      case FLOAT:
      case INT:
      case INTERFACE:
      case LONG:
      case NATIVE:
      case PRIVATE:
      case PROTECTED:
      case PUBLIC:
      case SHORT:
      case STATIC:
      case STRICTFP:
      case SYNCHRONIZED:
      case TRANSIENT:
      case VOLATILE:
      case IDENTIFIER:
      case SEMICOLON:
      case AT:
        ;
        break;
      default:
        jj_la1[157] = jj_gen;
        break label_55;
      }
      member = AnnotationBodyDeclaration();
      ret = add(ret, member);
    }
    jj_consume_token(RBRACE);
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Annotation body declaration.
   *
   * @return the body declaration
   * @throws ParseException the parse exception
   */
  final public BodyDeclaration AnnotationBodyDeclaration() throws ParseException {
  Modifier modifier;
  BodyDeclaration ret;
    pushJavadoc();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      ret = NodeFacade.EmptyTypeDeclaration(popJavadoc());
      break;
    case ABSTRACT:
    case BOOLEAN:
    case BYTE:
    case CHAR:
    case CLASS:
    case DOUBLE:
    case ENUM:
    case FINAL:
    case FLOAT:
    case INT:
    case INTERFACE:
    case LONG:
    case NATIVE:
    case PRIVATE:
    case PROTECTED:
    case PUBLIC:
    case SHORT:
    case STATIC:
    case STRICTFP:
    case SYNCHRONIZED:
    case TRANSIENT:
    case VOLATILE:
    case IDENTIFIER:
    case AT:
      modifier = Modifiers();
      if (jj_2_52(2147483647)) {
        ret = AnnotationTypeMemberDeclaration(modifier);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CLASS:
        case INTERFACE:
          ret = ClassOrInterfaceDeclaration(modifier);
          break;
        case ENUM:
          ret = EnumDeclaration(modifier);
          break;
        default:
          jj_la1[158] = jj_gen;
          if (jj_2_53(2)) {
            ret = AnnotationTypeDeclaration(modifier);
          } else {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case SHORT:
            case IDENTIFIER:
              ret = FieldDeclaration(modifier);
              break;
            default:
              jj_la1[159] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
          }
        }
      }
      break;
    default:
      jj_la1[160] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Annotation type member declaration.
   *
   * @param modifier the modifier
   * @return the annotation member declaration
   * @throws ParseException the parse exception
   */
  final public AnnotationMemberDeclaration AnnotationTypeMemberDeclaration(Modifier modifier) throws ParseException {
  Type type;
  String name;
  Expression defaultVal = null;
    type = Type();
    jj_consume_token(IDENTIFIER);
    name = token.image;
    jj_consume_token(LPAREN);
    jj_consume_token(RPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case _DEFAULT:
      defaultVal = DefaultValue();
      break;
    default:
      jj_la1[161] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
    {if (true) return NodeFacade.AnnotationMemberDeclaration(modifier.modifiers, type, name, defaultVal, popJavadoc(), modifier.annotations);}
    throw new Error("Missing return statement in function");
  }

  /**
   * Default value.
   *
   * @return the expression
   * @throws ParseException the parse exception
   */
  final public Expression DefaultValue() throws ParseException {
  Expression ret;
    jj_consume_token(_DEFAULT);
    ret = MemberValue();
    {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  /**
   * Jj_2_1.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  /**
   * Jj_2_2.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  /**
   * Jj_2_3.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  /**
   * Jj_2_4.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  /**
   * Jj_2_5.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  /**
   * Jj_2_6.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  /**
   * Jj_2_7.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  /**
   * Jj_2_8.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  /**
   * Jj_2_9.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  /**
   * Jj_2_10.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  /**
   * Jj_2_11.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  /**
   * Jj_2_12.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  /**
   * Jj_2_13.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  /**
   * Jj_2_14.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  /**
   * Jj_2_15.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  /**
   * Jj_2_16.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  /**
   * Jj_2_17.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_17(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(16, xla); }
  }

  /**
   * Jj_2_18.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_18(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(17, xla); }
  }

  /**
   * Jj_2_19.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_19(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(18, xla); }
  }

  /**
   * Jj_2_20.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_20(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_20(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(19, xla); }
  }

  /**
   * Jj_2_21.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_21(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_21(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(20, xla); }
  }

  /**
   * Jj_2_22.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_22(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_22(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(21, xla); }
  }

  /**
   * Jj_2_23.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_23(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_23(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(22, xla); }
  }

  /**
   * Jj_2_24.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_24(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_24(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(23, xla); }
  }

  /**
   * Jj_2_25.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_25(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_25(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(24, xla); }
  }

  /**
   * Jj_2_26.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_26(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_26(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(25, xla); }
  }

  /**
   * Jj_2_27.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_27(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_27(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(26, xla); }
  }

  /**
   * Jj_2_28.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_28(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_28(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(27, xla); }
  }

  /**
   * Jj_2_29.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_29(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_29(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(28, xla); }
  }

  /**
   * Jj_2_30.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_30(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_30(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(29, xla); }
  }

  /**
   * Jj_2_31.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_31(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_31(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(30, xla); }
  }

  /**
   * Jj_2_32.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_32(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_32(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(31, xla); }
  }

  /**
   * Jj_2_33.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_33(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_33(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(32, xla); }
  }

  /**
   * Jj_2_34.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_34(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_34(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(33, xla); }
  }

  /**
   * Jj_2_35.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_35(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_35(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(34, xla); }
  }

  /**
   * Jj_2_36.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_36(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_36(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(35, xla); }
  }

  /**
   * Jj_2_37.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_37(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_37(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(36, xla); }
  }

  /**
   * Jj_2_38.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_38(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_38(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(37, xla); }
  }

  /**
   * Jj_2_39.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_39(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_39(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(38, xla); }
  }

  /**
   * Jj_2_40.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_40(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_40(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(39, xla); }
  }

  /**
   * Jj_2_41.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_41(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_41(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(40, xla); }
  }

  /**
   * Jj_2_42.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_42(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_42(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(41, xla); }
  }

  /**
   * Jj_2_43.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_43(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_43(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(42, xla); }
  }

  /**
   * Jj_2_44.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_44(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_44(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(43, xla); }
  }

  /**
   * Jj_2_45.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_45(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_45(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(44, xla); }
  }

  /**
   * Jj_2_46.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_46(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_46(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(45, xla); }
  }

  /**
   * Jj_2_47.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_47(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_47(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(46, xla); }
  }

  /**
   * Jj_2_48.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_48(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_48(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(47, xla); }
  }

  /**
   * Jj_2_49.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_49(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_49(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(48, xla); }
  }

  /**
   * Jj_2_50.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_50(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_50(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(49, xla); }
  }

  /**
   * Jj_2_51.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_51(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_51(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(50, xla); }
  }

  /**
   * Jj_2_52.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_52(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_52(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(51, xla); }
  }

  /**
   * Jj_2_53.
   *
   * @param xla the xla
   * @return true, if successful
   */
  private boolean jj_2_53(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_53(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(52, xla); }
  }

  /**
   * Jj_3 r_385.
   *
   * @return true, if successful
   */
  private boolean jj_3R_385() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /**
   * Jj_3 r_152.
   *
   * @return true, if successful
   */
  private boolean jj_3R_152() {
    if (jj_3R_93()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_202()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_203()) return true;
    return false;
  }

  /**
   * Jj_3 r_353.
   *
   * @return true, if successful
   */
  private boolean jj_3R_353() {
    if (jj_scan_token(BREAK)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_385()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3_19.
   *
   * @return true, if successful
   */
  private boolean jj_3_19() {
    if (jj_scan_token(DOT)) return true;
    if (jj_3R_88()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_20()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_104.
   *
   * @return true, if successful
   */
  private boolean jj_3R_104() {
    if (jj_scan_token(BIT_AND)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_103()) return true;
    return false;
  }

  /**
   * Jj_3_18.
   *
   * @return true, if successful
   */
  private boolean jj_3_18() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_426.
   *
   * @return true, if successful
   */
  private boolean jj_3R_426() {
    if (jj_3R_436()) return true;
    return false;
  }

  /**
   * Jj_3 r_409.
   *
   * @return true, if successful
   */
  private boolean jj_3R_409() {
    if (jj_3R_426()) return true;
    return false;
  }

  /**
   * Jj_3 r_81.
   *
   * @return true, if successful
   */
  private boolean jj_3R_81() {
    if (jj_3R_119()) return true;
    return false;
  }

  /**
   * Jj_3 r_112.
   *
   * @return true, if successful
   */
  private boolean jj_3R_112() {
    if (jj_3R_117()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_18()) jj_scanpos = xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_19()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_29.
   *
   * @return true, if successful
   */
  private boolean jj_3_29() {
    if (jj_3R_103()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_104()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_201()) return true;
    return false;
  }

  /**
   * Jj_3 r_80.
   *
   * @return true, if successful
   */
  private boolean jj_3R_80() {
    if (jj_scan_token(_DEFAULT)) return true;
    return false;
  }

  /**
   * Jj_3 r_100.
   *
   * @return true, if successful
   */
  private boolean jj_3R_100() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_88()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_29()) {
    jj_scanpos = xsp;
    if (jj_3R_152()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_79.
   *
   * @return true, if successful
   */
  private boolean jj_3R_79() {
    if (jj_scan_token(STRICTFP)) return true;
    return false;
  }

  /**
   * Jj_3_17.
   *
   * @return true, if successful
   */
  private boolean jj_3_17() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_439.
   *
   * @return true, if successful
   */
  private boolean jj_3R_439() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_78.
   *
   * @return true, if successful
   */
  private boolean jj_3R_78() {
    if (jj_scan_token(VOLATILE)) return true;
    return false;
  }

  /**
   * Jj_3 r_77.
   *
   * @return true, if successful
   */
  private boolean jj_3R_77() {
    if (jj_scan_token(TRANSIENT)) return true;
    return false;
  }

  /**
   * Jj_3_16.
   *
   * @return true, if successful
   */
  private boolean jj_3_16() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_436.
   *
   * @return true, if successful
   */
  private boolean jj_3R_436() {
    if (jj_3R_96()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_439()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_198.
   *
   * @return true, if successful
   */
  private boolean jj_3R_198() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_76.
   *
   * @return true, if successful
   */
  private boolean jj_3R_76() {
    if (jj_scan_token(NATIVE)) return true;
    return false;
  }

  /**
   * Jj_3 r_135.
   *
   * @return true, if successful
   */
  private boolean jj_3R_135() {
    if (jj_3R_112()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_198()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_75.
   *
   * @return true, if successful
   */
  private boolean jj_3R_75() {
    if (jj_scan_token(SYNCHRONIZED)) return true;
    return false;
  }

  /**
   * Jj_3 r_102.
   *
   * @return true, if successful
   */
  private boolean jj_3R_102() {
    if (jj_scan_token(DECR)) return true;
    return false;
  }

  /**
   * Jj_3_47.
   *
   * @return true, if successful
   */
  private boolean jj_3_47() {
    if (jj_3R_115()) return true;
    if (jj_3R_85()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /**
   * Jj_3 r_408.
   *
   * @return true, if successful
   */
  private boolean jj_3R_408() {
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_74.
   *
   * @return true, if successful
   */
  private boolean jj_3R_74() {
    if (jj_scan_token(ABSTRACT)) return true;
    return false;
  }

  /**
   * Jj_3 r_197.
   *
   * @return true, if successful
   */
  private boolean jj_3R_197() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_101.
   *
   * @return true, if successful
   */
  private boolean jj_3R_101() {
    if (jj_scan_token(INCR)) return true;
    return false;
  }

  /**
   * Jj_3 r_435.
   *
   * @return true, if successful
   */
  private boolean jj_3R_435() {
    if (jj_3R_436()) return true;
    return false;
  }

  /**
   * Jj_3 r_73.
   *
   * @return true, if successful
   */
  private boolean jj_3R_73() {
    if (jj_scan_token(FINAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_134.
   *
   * @return true, if successful
   */
  private boolean jj_3R_134() {
    if (jj_3R_103()) return true;
    Token xsp;
    if (jj_3R_197()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_197()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_28.
   *
   * @return true, if successful
   */
  private boolean jj_3_28() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_101()) {
    jj_scanpos = xsp;
    if (jj_3R_102()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_93.
   *
   * @return true, if successful
   */
  private boolean jj_3R_93() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_134()) {
    jj_scanpos = xsp;
    if (jj_3R_135()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_434.
   *
   * @return true, if successful
   */
  private boolean jj_3R_434() {
    if (jj_3R_116()) return true;
    return false;
  }

  /**
   * Jj_3 r_72.
   *
   * @return true, if successful
   */
  private boolean jj_3R_72() {
    if (jj_scan_token(PRIVATE)) return true;
    return false;
  }

  /**
   * Jj_3 r_282.
   *
   * @return true, if successful
   */
  private boolean jj_3R_282() {
    if (jj_3R_302()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_28()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_425.
   *
   * @return true, if successful
   */
  private boolean jj_3R_425() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_434()) {
    jj_scanpos = xsp;
    if (jj_3R_435()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_71.
   *
   * @return true, if successful
   */
  private boolean jj_3R_71() {
    if (jj_scan_token(PROTECTED)) return true;
    return false;
  }

  /**
   * Jj_3_27.
   *
   * @return true, if successful
   */
  private boolean jj_3_27() {
    if (jj_3R_100()) return true;
    return false;
  }

  /**
   * Jj_3 r_70.
   *
   * @return true, if successful
   */
  private boolean jj_3R_70() {
    if (jj_scan_token(STATIC)) return true;
    return false;
  }

  /**
   * Jj_3_46.
   *
   * @return true, if successful
   */
  private boolean jj_3_46() {
    if (jj_3R_116()) return true;
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_69.
   *
   * @return true, if successful
   */
  private boolean jj_3R_69() {
    if (jj_scan_token(PUBLIC)) return true;
    return false;
  }

  /**
   * Jj_3 r_216.
   *
   * @return true, if successful
   */
  private boolean jj_3R_216() {
    if (jj_3R_103()) return true;
    return false;
  }

  /**
   * Jj_3 r_247.
   *
   * @return true, if successful
   */
  private boolean jj_3R_247() {
    if (jj_3R_282()) return true;
    return false;
  }

  /**
   * Jj_3_15.
   *
   * @return true, if successful
   */
  private boolean jj_3_15() {
    if (jj_3R_93()) return true;
    return false;
  }

  /**
   * Jj_3 r_246.
   *
   * @return true, if successful
   */
  private boolean jj_3R_246() {
    if (jj_3R_100()) return true;
    return false;
  }

  /**
   * Jj_3_3.
   *
   * @return true, if successful
   */
  private boolean jj_3_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_69()) {
    jj_scanpos = xsp;
    if (jj_3R_70()) {
    jj_scanpos = xsp;
    if (jj_3R_71()) {
    jj_scanpos = xsp;
    if (jj_3R_72()) {
    jj_scanpos = xsp;
    if (jj_3R_73()) {
    jj_scanpos = xsp;
    if (jj_3R_74()) {
    jj_scanpos = xsp;
    if (jj_3R_75()) {
    jj_scanpos = xsp;
    if (jj_3R_76()) {
    jj_scanpos = xsp;
    if (jj_3R_77()) {
    jj_scanpos = xsp;
    if (jj_3R_78()) {
    jj_scanpos = xsp;
    if (jj_3R_79()) {
    jj_scanpos = xsp;
    if (jj_3R_80()) {
    jj_scanpos = xsp;
    if (jj_3R_81()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_407.
   *
   * @return true, if successful
   */
  private boolean jj_3R_407() {
    if (jj_3R_425()) return true;
    return false;
  }

  /**
   * Jj_3 r_172.
   *
   * @return true, if successful
   */
  private boolean jj_3R_172() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_15()) {
    jj_scanpos = xsp;
    if (jj_3R_216()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_384.
   *
   * @return true, if successful
   */
  private boolean jj_3R_384() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_407()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    xsp = jj_scanpos;
    if (jj_3R_408()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    xsp = jj_scanpos;
    if (jj_3R_409()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_283.
   *
   * @return true, if successful
   */
  private boolean jj_3R_283() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_3()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_383.
   *
   * @return true, if successful
   */
  private boolean jj_3R_383() {
    if (jj_3R_116()) return true;
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_281.
   *
   * @return true, if successful
   */
  private boolean jj_3R_281() {
    if (jj_scan_token(BANG)) return true;
    return false;
  }

  /**
   * Jj_3 r_352.
   *
   * @return true, if successful
   */
  private boolean jj_3R_352() {
    if (jj_scan_token(FOR)) return true;
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_383()) {
    jj_scanpos = xsp;
    if (jj_3R_384()) return true;
    }
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_311()) return true;
    return false;
  }

  /**
   * Jj_3 r_280.
   *
   * @return true, if successful
   */
  private boolean jj_3R_280() {
    if (jj_scan_token(TILDE)) return true;
    return false;
  }

  /**
   * Jj_3 r_124.
   *
   * @return true, if successful
   */
  private boolean jj_3R_124() {
    if (jj_3R_103()) return true;
    return false;
  }

  /**
   * Jj_3 r_245.
   *
   * @return true, if successful
   */
  private boolean jj_3R_245() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_280()) {
    jj_scanpos = xsp;
    if (jj_3R_281()) return true;
    }
    if (jj_3R_201()) return true;
    return false;
  }

  /**
   * Jj_3_14.
   *
   * @return true, if successful
   */
  private boolean jj_3_14() {
    if (jj_3R_93()) return true;
    return false;
  }

  /**
   * Jj_3 r_203.
   *
   * @return true, if successful
   */
  private boolean jj_3R_203() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_245()) {
    jj_scanpos = xsp;
    if (jj_3R_246()) {
    jj_scanpos = xsp;
    if (jj_3R_247()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_68.
   *
   * @return true, if successful
   */
  private boolean jj_3R_68() {
    if (jj_3R_119()) return true;
    return false;
  }

  /**
   * Jj_3 r_85.
   *
   * @return true, if successful
   */
  private boolean jj_3R_85() {
    if (jj_3R_88()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_14()) {
    jj_scanpos = xsp;
    if (jj_3R_124()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_67.
   *
   * @return true, if successful
   */
  private boolean jj_3R_67() {
    if (jj_scan_token(STRICTFP)) return true;
    return false;
  }

  /**
   * Jj_3 r_351.
   *
   * @return true, if successful
   */
  private boolean jj_3R_351() {
    if (jj_scan_token(DO)) return true;
    if (jj_3R_311()) return true;
    if (jj_scan_token(WHILE)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RPAREN)) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_66.
   *
   * @return true, if successful
   */
  private boolean jj_3R_66() {
    if (jj_scan_token(VOLATILE)) return true;
    return false;
  }

  /**
   * Jj_3 r_65.
   *
   * @return true, if successful
   */
  private boolean jj_3R_65() {
    if (jj_scan_token(TRANSIENT)) return true;
    return false;
  }

  /**
   * Jj_3 r_277.
   *
   * @return true, if successful
   */
  private boolean jj_3R_277() {
    if (jj_scan_token(DECR)) return true;
    if (jj_3R_201()) return true;
    return false;
  }

  /**
   * Jj_3 r_64.
   *
   * @return true, if successful
   */
  private boolean jj_3R_64() {
    if (jj_scan_token(NATIVE)) return true;
    return false;
  }

  /**
   * Jj_3 r_125.
   *
   * @return true, if successful
   */
  private boolean jj_3R_125() {
    if (jj_scan_token(STATIC)) return true;
    return false;
  }

  /**
   * Jj_3 r_350.
   *
   * @return true, if successful
   */
  private boolean jj_3R_350() {
    if (jj_scan_token(WHILE)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_311()) return true;
    return false;
  }

  /**
   * Jj_3 r_63.
   *
   * @return true, if successful
   */
  private boolean jj_3R_63() {
    if (jj_scan_token(SYNCHRONIZED)) return true;
    return false;
  }

  /**
   * Jj_3 r_87.
   *
   * @return true, if successful
   */
  private boolean jj_3R_87() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_125()) jj_scanpos = xsp;
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_62.
   *
   * @return true, if successful
   */
  private boolean jj_3R_62() {
    if (jj_scan_token(ABSTRACT)) return true;
    return false;
  }

  /**
   * Jj_3 r_276.
   *
   * @return true, if successful
   */
  private boolean jj_3R_276() {
    if (jj_scan_token(INCR)) return true;
    if (jj_3R_201()) return true;
    return false;
  }

  /**
   * Jj_3 r_61.
   *
   * @return true, if successful
   */
  private boolean jj_3R_61() {
    if (jj_scan_token(FINAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_382.
   *
   * @return true, if successful
   */
  private boolean jj_3R_382() {
    if (jj_scan_token(ELSE)) return true;
    if (jj_3R_311()) return true;
    return false;
  }

  /**
   * Jj_3 r_60.
   *
   * @return true, if successful
   */
  private boolean jj_3R_60() {
    if (jj_scan_token(PRIVATE)) return true;
    return false;
  }

  /**
   * Jj_3 r_349.
   *
   * @return true, if successful
   */
  private boolean jj_3R_349() {
    if (jj_scan_token(IF)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_311()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_382()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_59.
   *
   * @return true, if successful
   */
  private boolean jj_3R_59() {
    if (jj_scan_token(PROTECTED)) return true;
    return false;
  }

  /**
   * Jj_3 r_225.
   *
   * @return true, if successful
   */
  private boolean jj_3R_225() {
    if (jj_3R_257()) return true;
    return false;
  }

  /**
   * Jj_3 r_244.
   *
   * @return true, if successful
   */
  private boolean jj_3R_244() {
    if (jj_3R_203()) return true;
    return false;
  }

  /**
   * Jj_3 r_181.
   *
   * @return true, if successful
   */
  private boolean jj_3R_181() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_225()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_58.
   *
   * @return true, if successful
   */
  private boolean jj_3R_58() {
    if (jj_scan_token(STATIC)) return true;
    return false;
  }

  /**
   * Jj_3_12.
   *
   * @return true, if successful
   */
  private boolean jj_3_12() {
    if (jj_3R_91()) return true;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  /**
   * Jj_3 r_57.
   *
   * @return true, if successful
   */
  private boolean jj_3R_57() {
    if (jj_scan_token(PUBLIC)) return true;
    return false;
  }

  /**
   * Jj_3 r_279.
   *
   * @return true, if successful
   */
  private boolean jj_3R_279() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  /**
   * Jj_3_2.
   *
   * @return true, if successful
   */
  private boolean jj_3_2() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_57()) {
    jj_scanpos = xsp;
    if (jj_3R_58()) {
    jj_scanpos = xsp;
    if (jj_3R_59()) {
    jj_scanpos = xsp;
    if (jj_3R_60()) {
    jj_scanpos = xsp;
    if (jj_3R_61()) {
    jj_scanpos = xsp;
    if (jj_3R_62()) {
    jj_scanpos = xsp;
    if (jj_3R_63()) {
    jj_scanpos = xsp;
    if (jj_3R_64()) {
    jj_scanpos = xsp;
    if (jj_3R_65()) {
    jj_scanpos = xsp;
    if (jj_3R_66()) {
    jj_scanpos = xsp;
    if (jj_3R_67()) {
    jj_scanpos = xsp;
    if (jj_3R_68()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_186.
   *
   * @return true, if successful
   */
  private boolean jj_3R_186() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_278.
   *
   * @return true, if successful
   */
  private boolean jj_3R_278() {
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  /**
   * Jj_3 r_92.
   *
   * @return true, if successful
   */
  private boolean jj_3R_92() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_115.
   *
   * @return true, if successful
   */
  private boolean jj_3R_115() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_2()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_185.
   *
   * @return true, if successful
   */
  private boolean jj_3R_185() {
    if (jj_3R_91()) return true;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  /**
   * Jj_3 r_243.
   *
   * @return true, if successful
   */
  private boolean jj_3R_243() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_278()) {
    jj_scanpos = xsp;
    if (jj_3R_279()) return true;
    }
    if (jj_3R_201()) return true;
    return false;
  }

  /**
   * Jj_3_13.
   *
   * @return true, if successful
   */
  private boolean jj_3_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_92()) jj_scanpos = xsp;
    if (jj_scan_token(THIS)) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_242.
   *
   * @return true, if successful
   */
  private boolean jj_3R_242() {
    if (jj_3R_277()) return true;
    return false;
  }

  /**
   * Jj_3 r_131.
   *
   * @return true, if successful
   */
  private boolean jj_3R_131() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_185()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_186()) jj_scanpos = xsp;
    if (jj_scan_token(SUPER)) return true;
    if (jj_3R_184()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_241.
   *
   * @return true, if successful
   */
  private boolean jj_3R_241() {
    if (jj_3R_276()) return true;
    return false;
  }

  /**
   * Jj_3 r_424.
   *
   * @return true, if successful
   */
  private boolean jj_3R_424() {
    if (jj_scan_token(CASE)) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_395.
   *
   * @return true, if successful
   */
  private boolean jj_3R_395() {
    if (jj_3R_415()) return true;
    return false;
  }

  /**
   * Jj_3 r_201.
   *
   * @return true, if successful
   */
  private boolean jj_3R_201() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_241()) {
    jj_scanpos = xsp;
    if (jj_3R_242()) {
    jj_scanpos = xsp;
    if (jj_3R_243()) {
    jj_scanpos = xsp;
    if (jj_3R_244()) return true;
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_406.
   *
   * @return true, if successful
   */
  private boolean jj_3R_406() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_424()) {
    jj_scanpos = xsp;
    if (jj_scan_token(23)) return true;
    }
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_181()) return true;
    return false;
  }

  /**
   * Jj_3 r_183.
   *
   * @return true, if successful
   */
  private boolean jj_3R_183() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_130.
   *
   * @return true, if successful
   */
  private boolean jj_3R_130() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_183()) jj_scanpos = xsp;
    if (jj_scan_token(THIS)) return true;
    if (jj_3R_184()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_90.
   *
   * @return true, if successful
   */
  private boolean jj_3R_90() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_130()) {
    jj_scanpos = xsp;
    if (jj_3R_131()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_381.
   *
   * @return true, if successful
   */
  private boolean jj_3R_381() {
    if (jj_3R_406()) return true;
    return false;
  }

  /**
   * Jj_3 r_433.
   *
   * @return true, if successful
   */
  private boolean jj_3R_433() {
    if (jj_scan_token(REM)) return true;
    return false;
  }

  /**
   * Jj_3_11.
   *
   * @return true, if successful
   */
  private boolean jj_3_11() {
    if (jj_3R_90()) return true;
    return false;
  }

  /**
   * Jj_3 r_348.
   *
   * @return true, if successful
   */
  private boolean jj_3R_348() {
    if (jj_scan_token(SWITCH)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RPAREN)) return true;
    if (jj_scan_token(LBRACE)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_381()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_432.
   *
   * @return true, if successful
   */
  private boolean jj_3R_432() {
    if (jj_scan_token(SLASH)) return true;
    return false;
  }

  /**
   * Jj_3 r_431.
   *
   * @return true, if successful
   */
  private boolean jj_3R_431() {
    if (jj_scan_token(STAR)) return true;
    return false;
  }

  /**
   * Jj_3 r_416.
   *
   * @return true, if successful
   */
  private boolean jj_3R_416() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_431()) {
    jj_scanpos = xsp;
    if (jj_3R_432()) {
    jj_scanpos = xsp;
    if (jj_3R_433()) return true;
    }
    }
    if (jj_3R_201()) return true;
    return false;
  }

  /**
   * Jj_3 r_396.
   *
   * @return true, if successful
   */
  private boolean jj_3R_396() {
    if (jj_3R_90()) return true;
    return false;
  }

  /**
   * Jj_3 r_401.
   *
   * @return true, if successful
   */
  private boolean jj_3R_401() {
    if (jj_3R_201()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_416()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_423.
   *
   * @return true, if successful
   */
  private boolean jj_3R_423() {
    if (jj_3R_95()) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_318.
   *
   * @return true, if successful
   */
  private boolean jj_3R_318() {
    if (jj_3R_123()) return true;
    return false;
  }

  /**
   * Jj_3 r_305.
   *
   * @return true, if successful
   */
  private boolean jj_3R_305() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_318()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_3R_107()) return true;
    xsp = jj_scanpos;
    if (jj_3R_395()) jj_scanpos = xsp;
    if (jj_scan_token(LBRACE)) return true;
    xsp = jj_scanpos;
    if (jj_3R_396()) jj_scanpos = xsp;
    if (jj_3R_181()) return true;
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_422.
   *
   * @return true, if successful
   */
  private boolean jj_3R_422() {
    if (jj_scan_token(DECR)) return true;
    return false;
  }

  /**
   * Jj_3 r_121.
   *
   * @return true, if successful
   */
  private boolean jj_3R_121() {
    if (jj_3R_119()) return true;
    return false;
  }

  /**
   * Jj_3 r_421.
   *
   * @return true, if successful
   */
  private boolean jj_3R_421() {
    if (jj_scan_token(INCR)) return true;
    return false;
  }

  /**
   * Jj_3 r_405.
   *
   * @return true, if successful
   */
  private boolean jj_3R_405() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_421()) {
    jj_scanpos = xsp;
    if (jj_3R_422()) {
    jj_scanpos = xsp;
    if (jj_3R_423()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_56.
   *
   * @return true, if successful
   */
  private boolean jj_3R_56() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_121()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(PACKAGE)) return true;
    if (jj_3R_117()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_363.
   *
   * @return true, if successful
   */
  private boolean jj_3R_363() {
    if (jj_3R_302()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_405()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_362.
   *
   * @return true, if successful
   */
  private boolean jj_3R_362() {
    if (jj_3R_277()) return true;
    return false;
  }

  /**
   * Jj_3 r_361.
   *
   * @return true, if successful
   */
  private boolean jj_3R_361() {
    if (jj_3R_276()) return true;
    return false;
  }

  /**
   * Jj_3 r_418.
   *
   * @return true, if successful
   */
  private boolean jj_3R_418() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  /**
   * Jj_3 r_347.
   *
   * @return true, if successful
   */
  private boolean jj_3R_347() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_361()) {
    jj_scanpos = xsp;
    if (jj_3R_362()) {
    jj_scanpos = xsp;
    if (jj_3R_363()) return true;
    }
    }
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_417.
   *
   * @return true, if successful
   */
  private boolean jj_3R_417() {
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  /**
   * Jj_3 r_210.
   *
   * @return true, if successful
   */
  private boolean jj_3R_210() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /**
   * Jj_3 r_402.
   *
   * @return true, if successful
   */
  private boolean jj_3R_402() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_417()) {
    jj_scanpos = xsp;
    if (jj_3R_418()) return true;
    }
    if (jj_3R_401()) return true;
    return false;
  }

  /**
   * Jj_3 r_370.
   *
   * @return true, if successful
   */
  private boolean jj_3R_370() {
    if (jj_3R_401()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_402()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_1.
   *
   * @return true, if successful
   */
  private boolean jj_3_1() {
    if (jj_3R_56()) return true;
    return false;
  }

  /**
   * Jj_3 r_211.
   *
   * @return true, if successful
   */
  private boolean jj_3R_211() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_210()) return true;
    return false;
  }

  /**
   * Jj_3 r_346.
   *
   * @return true, if successful
   */
  private boolean jj_3R_346() {
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_166.
   *
   * @return true, if successful
   */
  private boolean jj_3R_166() {
    if (jj_3R_210()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_211()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_26.
   *
   * @return true, if successful
   */
  private boolean jj_3_26() {
    if (jj_3R_99()) return true;
    return false;
  }

  /**
   * Jj_3 r_108.
   *
   * @return true, if successful
   */
  private boolean jj_3R_108() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_166()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_174.
   *
   * @return true, if successful
   */
  private boolean jj_3R_174() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_173()) return true;
    return false;
  }

  /**
   * Jj_3_25.
   *
   * @return true, if successful
   */
  private boolean jj_3_25() {
    if (jj_3R_98()) return true;
    return false;
  }

  /**
   * Jj_3 r_97.
   *
   * @return true, if successful
   */
  private boolean jj_3R_97() {
    if (jj_scan_token(LSHIFT)) return true;
    return false;
  }

  /**
   * Jj_3 r_116.
   *
   * @return true, if successful
   */
  private boolean jj_3R_116() {
    if (jj_3R_115()) return true;
    if (jj_3R_172()) return true;
    if (jj_3R_173()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_174()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_24.
   *
   * @return true, if successful
   */
  private boolean jj_3_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_97()) {
    jj_scanpos = xsp;
    if (jj_3_25()) {
    jj_scanpos = xsp;
    if (jj_3_26()) return true;
    }
    }
    if (jj_3R_370()) return true;
    return false;
  }

  /**
   * Jj_3 r_368.
   *
   * @return true, if successful
   */
  private boolean jj_3R_368() {
    if (jj_3R_370()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_24()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_250.
   *
   * @return true, if successful
   */
  private boolean jj_3R_250() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(ELLIPSIS)) return true;
    return false;
  }

  /**
   * Jj_3_45.
   *
   * @return true, if successful
   */
  private boolean jj_3_45() {
    if (jj_3R_116()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_208.
   *
   * @return true, if successful
   */
  private boolean jj_3R_208() {
    if (jj_3R_115()) return true;
    if (jj_3R_85()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_250()) jj_scanpos = xsp;
    if (jj_3R_217()) return true;
    return false;
  }

  /**
   * Jj_3 r_294.
   *
   * @return true, if successful
   */
  private boolean jj_3R_294() {
    if (jj_3R_311()) return true;
    return false;
  }

  /**
   * Jj_3_44.
   *
   * @return true, if successful
   */
  private boolean jj_3_44() {
    if (jj_3R_115()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(20)) {
    jj_scanpos = xsp;
    if (jj_scan_token(40)) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_293.
   *
   * @return true, if successful
   */
  private boolean jj_3R_293() {
    if (jj_3R_116()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_374.
   *
   * @return true, if successful
   */
  private boolean jj_3R_374() {
    if (jj_scan_token(GE)) return true;
    return false;
  }

  /**
   * Jj_3 r_292.
   *
   * @return true, if successful
   */
  private boolean jj_3R_292() {
    if (jj_3R_115()) return true;
    if (jj_3R_303()) return true;
    return false;
  }

  /**
   * Jj_3 r_373.
   *
   * @return true, if successful
   */
  private boolean jj_3R_373() {
    if (jj_scan_token(LE)) return true;
    return false;
  }

  /**
   * Jj_3 r_209.
   *
   * @return true, if successful
   */
  private boolean jj_3R_209() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_208()) return true;
    return false;
  }

  /**
   * Jj_3 r_257.
   *
   * @return true, if successful
   */
  private boolean jj_3R_257() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_292()) {
    jj_scanpos = xsp;
    if (jj_3R_293()) {
    jj_scanpos = xsp;
    if (jj_3R_294()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_372.
   *
   * @return true, if successful
   */
  private boolean jj_3R_372() {
    if (jj_scan_token(GT)) return true;
    return false;
  }

  /**
   * Jj_3 r_165.
   *
   * @return true, if successful
   */
  private boolean jj_3R_165() {
    if (jj_3R_208()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_209()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_371.
   *
   * @return true, if successful
   */
  private boolean jj_3R_371() {
    if (jj_scan_token(LT)) return true;
    return false;
  }

  /**
   * Jj_3 r_369.
   *
   * @return true, if successful
   */
  private boolean jj_3R_369() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_371()) {
    jj_scanpos = xsp;
    if (jj_3R_372()) {
    jj_scanpos = xsp;
    if (jj_3R_373()) {
    jj_scanpos = xsp;
    if (jj_3R_374()) return true;
    }
    }
    }
    if (jj_3R_368()) return true;
    return false;
  }

  /**
   * Jj_3 r_107.
   *
   * @return true, if successful
   */
  private boolean jj_3R_107() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_165()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_380.
   *
   * @return true, if successful
   */
  private boolean jj_3R_380() {
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_364.
   *
   * @return true, if successful
   */
  private boolean jj_3R_364() {
    if (jj_3R_368()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_369()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_126.
   *
   * @return true, if successful
   */
  private boolean jj_3R_126() {
    if (jj_scan_token(LBRACE)) return true;
    if (jj_3R_181()) return true;
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_400.
   *
   * @return true, if successful
   */
  private boolean jj_3R_400() {
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_399.
   *
   * @return true, if successful
   */
  private boolean jj_3R_399() {
    if (jj_3R_415()) return true;
    return false;
  }

  /**
   * Jj_3 r_365.
   *
   * @return true, if successful
   */
  private boolean jj_3R_365() {
    if (jj_scan_token(INSTANCEOF)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  /**
   * Jj_3 r_398.
   *
   * @return true, if successful
   */
  private boolean jj_3R_398() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_359.
   *
   * @return true, if successful
   */
  private boolean jj_3R_359() {
    if (jj_3R_364()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_365()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_114.
   *
   * @return true, if successful
   */
  private boolean jj_3R_114() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_311()) return true;
    return false;
  }

  /**
   * Jj_3 r_319.
   *
   * @return true, if successful
   */
  private boolean jj_3R_319() {
    if (jj_3R_123()) return true;
    return false;
  }

  /**
   * Jj_3 r_307.
   *
   * @return true, if successful
   */
  private boolean jj_3R_307() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_319()) jj_scanpos = xsp;
    if (jj_3R_109()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_3R_107()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_398()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_3R_399()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_400()) {
    jj_scanpos = xsp;
    if (jj_scan_token(87)) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_345.
   *
   * @return true, if successful
   */
  private boolean jj_3R_345() {
    if (jj_scan_token(ASSERT)) return true;
    if (jj_3R_96()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_380()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_367.
   *
   * @return true, if successful
   */
  private boolean jj_3R_367() {
    if (jj_scan_token(NE)) return true;
    return false;
  }

  /**
   * Jj_3 r_366.
   *
   * @return true, if successful
   */
  private boolean jj_3R_366() {
    if (jj_scan_token(EQ)) return true;
    return false;
  }

  /**
   * Jj_3 r_337.
   *
   * @return true, if successful
   */
  private boolean jj_3R_337() {
    if (jj_3R_358()) return true;
    return false;
  }

  /**
   * Jj_3 r_360.
   *
   * @return true, if successful
   */
  private boolean jj_3R_360() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_366()) {
    jj_scanpos = xsp;
    if (jj_3R_367()) return true;
    }
    if (jj_3R_359()) return true;
    return false;
  }

  /**
   * Jj_3 r_336.
   *
   * @return true, if successful
   */
  private boolean jj_3R_336() {
    if (jj_3R_357()) return true;
    return false;
  }

  /**
   * Jj_3 r_335.
   *
   * @return true, if successful
   */
  private boolean jj_3R_335() {
    if (jj_3R_356()) return true;
    return false;
  }

  /**
   * Jj_3 r_334.
   *
   * @return true, if successful
   */
  private boolean jj_3R_334() {
    if (jj_3R_355()) return true;
    return false;
  }

  /**
   * Jj_3 r_333.
   *
   * @return true, if successful
   */
  private boolean jj_3R_333() {
    if (jj_3R_354()) return true;
    return false;
  }

  /**
   * Jj_3 r_340.
   *
   * @return true, if successful
   */
  private boolean jj_3R_340() {
    if (jj_3R_359()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_360()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_332.
   *
   * @return true, if successful
   */
  private boolean jj_3R_332() {
    if (jj_3R_353()) return true;
    return false;
  }

  /**
   * Jj_3 r_331.
   *
   * @return true, if successful
   */
  private boolean jj_3R_331() {
    if (jj_3R_352()) return true;
    return false;
  }

  /**
   * Jj_3_10.
   *
   * @return true, if successful
   */
  private boolean jj_3_10() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_89()) return true;
    return false;
  }

  /**
   * Jj_3 r_330.
   *
   * @return true, if successful
   */
  private boolean jj_3R_330() {
    if (jj_3R_351()) return true;
    return false;
  }

  /**
   * Jj_3 r_329.
   *
   * @return true, if successful
   */
  private boolean jj_3R_329() {
    if (jj_3R_350()) return true;
    return false;
  }

  /**
   * Jj_3 r_328.
   *
   * @return true, if successful
   */
  private boolean jj_3R_328() {
    if (jj_3R_349()) return true;
    return false;
  }

  /**
   * Jj_3 r_327.
   *
   * @return true, if successful
   */
  private boolean jj_3R_327() {
    if (jj_3R_348()) return true;
    return false;
  }

  /**
   * Jj_3 r_326.
   *
   * @return true, if successful
   */
  private boolean jj_3R_326() {
    if (jj_3R_347()) return true;
    return false;
  }

  /**
   * Jj_3 r_325.
   *
   * @return true, if successful
   */
  private boolean jj_3R_325() {
    if (jj_3R_346()) return true;
    return false;
  }

  /**
   * Jj_3 r_324.
   *
   * @return true, if successful
   */
  private boolean jj_3R_324() {
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_253.
   *
   * @return true, if successful
   */
  private boolean jj_3R_253() {
    if (jj_3R_89()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_10()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_323.
   *
   * @return true, if successful
   */
  private boolean jj_3R_323() {
    if (jj_3R_345()) return true;
    return false;
  }

  /**
   * Jj_3_43.
   *
   * @return true, if successful
   */
  private boolean jj_3_43() {
    if (jj_3R_114()) return true;
    return false;
  }

  /**
   * Jj_3 r_182.
   *
   * @return true, if successful
   */
  private boolean jj_3R_182() {
    if (jj_scan_token(LBRACE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_253()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(88)) jj_scanpos = xsp;
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_311.
   *
   * @return true, if successful
   */
  private boolean jj_3R_311() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_43()) {
    jj_scanpos = xsp;
    if (jj_3R_323()) {
    jj_scanpos = xsp;
    if (jj_3R_324()) {
    jj_scanpos = xsp;
    if (jj_3R_325()) {
    jj_scanpos = xsp;
    if (jj_3R_326()) {
    jj_scanpos = xsp;
    if (jj_3R_327()) {
    jj_scanpos = xsp;
    if (jj_3R_328()) {
    jj_scanpos = xsp;
    if (jj_3R_329()) {
    jj_scanpos = xsp;
    if (jj_3R_330()) {
    jj_scanpos = xsp;
    if (jj_3R_331()) {
    jj_scanpos = xsp;
    if (jj_3R_332()) {
    jj_scanpos = xsp;
    if (jj_3R_333()) {
    jj_scanpos = xsp;
    if (jj_3R_334()) {
    jj_scanpos = xsp;
    if (jj_3R_335()) {
    jj_scanpos = xsp;
    if (jj_3R_336()) {
    jj_scanpos = xsp;
    if (jj_3R_337()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_343.
   *
   * @return true, if successful
   */
  private boolean jj_3R_343() {
    if (jj_scan_token(BIT_AND)) return true;
    if (jj_3R_340()) return true;
    return false;
  }

  /**
   * Jj_3 r_315.
   *
   * @return true, if successful
   */
  private boolean jj_3R_315() {
    if (jj_3R_340()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_343()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_129.
   *
   * @return true, if successful
   */
  private boolean jj_3R_129() {
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_128.
   *
   * @return true, if successful
   */
  private boolean jj_3R_128() {
    if (jj_3R_182()) return true;
    return false;
  }

  /**
   * Jj_3_41.
   *
   * @return true, if successful
   */
  private boolean jj_3_41() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_89.
   *
   * @return true, if successful
   */
  private boolean jj_3R_89() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_128()) {
    jj_scanpos = xsp;
    if (jj_3R_129()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_218.
   *
   * @return true, if successful
   */
  private boolean jj_3R_218() {
    if (jj_scan_token(ASSIGN)) return true;
    if (jj_3R_89()) return true;
    return false;
  }

  /**
   * Jj_3 r_320.
   *
   * @return true, if successful
   */
  private boolean jj_3R_320() {
    if (jj_scan_token(XOR)) return true;
    if (jj_3R_315()) return true;
    return false;
  }

  /**
   * Jj_3_9.
   *
   * @return true, if successful
   */
  private boolean jj_3_9() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3_40.
   *
   * @return true, if successful
   */
  private boolean jj_3_40() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_215.
   *
   * @return true, if successful
   */
  private boolean jj_3R_215() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_301.
   *
   * @return true, if successful
   */
  private boolean jj_3R_301() {
    if (jj_3R_315()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_320()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_255.
   *
   * @return true, if successful
   */
  private boolean jj_3R_255() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_214.
   *
   * @return true, if successful
   */
  private boolean jj_3R_214() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_171.
   *
   * @return true, if successful
   */
  private boolean jj_3R_171() {
    Token xsp;
    if (jj_3R_214()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_214()) { jj_scanpos = xsp; break; }
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_215()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_42.
   *
   * @return true, if successful
   */
  private boolean jj_3_42() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_217.
   *
   * @return true, if successful
   */
  private boolean jj_3R_217() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(78)) {
    jj_scanpos = xsp;
    if (jj_scan_token(56)) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_255()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_308.
   *
   * @return true, if successful
   */
  private boolean jj_3R_308() {
    if (jj_scan_token(BIT_OR)) return true;
    if (jj_3R_301()) return true;
    return false;
  }

  /**
   * Jj_3 r_275.
   *
   * @return true, if successful
   */
  private boolean jj_3R_275() {
    if (jj_3R_301()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_308()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_213.
   *
   * @return true, if successful
   */
  private boolean jj_3R_213() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_170.
   *
   * @return true, if successful
   */
  private boolean jj_3R_170() {
    Token xsp;
    if (jj_3R_213()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_213()) { jj_scanpos = xsp; break; }
    }
    if (jj_3R_182()) return true;
    return false;
  }

  /**
   * Jj_3 r_113.
   *
   * @return true, if successful
   */
  private boolean jj_3R_113() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_170()) {
    jj_scanpos = xsp;
    if (jj_3R_171()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_446.
   *
   * @return true, if successful
   */
  private boolean jj_3R_446() {
    if (jj_scan_token(_DEFAULT)) return true;
    if (jj_3R_120()) return true;
    return false;
  }

  /**
   * Jj_3 r_173.
   *
   * @return true, if successful
   */
  private boolean jj_3R_173() {
    if (jj_3R_217()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_218()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_445.
   *
   * @return true, if successful
   */
  private boolean jj_3R_445() {
    if (jj_3R_446()) return true;
    return false;
  }

  /**
   * Jj_3 r_289.
   *
   * @return true, if successful
   */
  private boolean jj_3R_289() {
    if (jj_scan_token(SC_AND)) return true;
    if (jj_3R_275()) return true;
    return false;
  }

  /**
   * Jj_3 r_86.
   *
   * @return true, if successful
   */
  private boolean jj_3R_86() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_240.
   *
   * @return true, if successful
   */
  private boolean jj_3R_240() {
    if (jj_3R_275()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_289()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_444.
   *
   * @return true, if successful
   */
  private boolean jj_3R_444() {
    if (jj_3R_85()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_scan_token(RPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_445()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_397.
   *
   * @return true, if successful
   */
  private boolean jj_3R_397() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_173()) return true;
    return false;
  }

  /**
   * Jj_3_39.
   *
   * @return true, if successful
   */
  private boolean jj_3_39() {
    if (jj_3R_112()) return true;
    if (jj_3R_113()) return true;
    return false;
  }

  /**
   * Jj_3_38.
   *
   * @return true, if successful
   */
  private boolean jj_3_38() {
    if (jj_3R_111()) return true;
    return false;
  }

  /**
   * Jj_3_52.
   *
   * @return true, if successful
   */
  private boolean jj_3_52() {
    if (jj_3R_85()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_306.
   *
   * @return true, if successful
   */
  private boolean jj_3R_306() {
    if (jj_3R_172()) return true;
    if (jj_3R_173()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_397()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_298.
   *
   * @return true, if successful
   */
  private boolean jj_3R_298() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_254.
   *
   * @return true, if successful
   */
  private boolean jj_3R_254() {
    if (jj_scan_token(SC_OR)) return true;
    if (jj_3R_240()) return true;
    return false;
  }

  /**
   * Jj_3 r_271.
   *
   * @return true, if successful
   */
  private boolean jj_3R_271() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_298()) jj_scanpos = xsp;
    if (jj_3R_112()) return true;
    if (jj_3R_184()) return true;
    xsp = jj_scanpos;
    if (jj_3_38()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_443.
   *
   * @return true, if successful
   */
  private boolean jj_3R_443() {
    if (jj_3R_306()) return true;
    return false;
  }

  /**
   * Jj_3_53.
   *
   * @return true, if successful
   */
  private boolean jj_3_53() {
    if (jj_3R_83()) return true;
    return false;
  }

  /**
   * Jj_3 r_442.
   *
   * @return true, if successful
   */
  private boolean jj_3R_442() {
    if (jj_3R_304()) return true;
    return false;
  }

  /**
   * Jj_3 r_441.
   *
   * @return true, if successful
   */
  private boolean jj_3R_441() {
    if (jj_3R_303()) return true;
    return false;
  }

  /**
   * Jj_3 r_200.
   *
   * @return true, if successful
   */
  private boolean jj_3R_200() {
    if (jj_3R_240()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_254()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_270.
   *
   * @return true, if successful
   */
  private boolean jj_3R_270() {
    if (jj_3R_112()) return true;
    if (jj_3R_113()) return true;
    return false;
  }

  /**
   * Jj_3 r_440.
   *
   * @return true, if successful
   */
  private boolean jj_3R_440() {
    if (jj_3R_444()) return true;
    return false;
  }

  /**
   * Jj_3 r_84.
   *
   * @return true, if successful
   */
  private boolean jj_3R_84() {
    if (jj_3R_123()) return true;
    return false;
  }

  /**
   * Jj_3_7.
   *
   * @return true, if successful
   */
  private boolean jj_3_7() {
    if (jj_3R_85()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_86()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_scan_token(88)) {
    jj_scanpos = xsp;
    if (jj_scan_token(91)) {
    jj_scanpos = xsp;
    if (jj_scan_token(87)) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3_6.
   *
   * @return true, if successful
   */
  private boolean jj_3_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_84()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_269.
   *
   * @return true, if successful
   */
  private boolean jj_3R_269() {
    if (jj_3R_103()) return true;
    if (jj_3R_113()) return true;
    return false;
  }

  /**
   * Jj_3 r_438.
   *
   * @return true, if successful
   */
  private boolean jj_3R_438() {
    if (jj_3R_115()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_440()) {
    jj_scanpos = xsp;
    if (jj_3R_441()) {
    jj_scanpos = xsp;
    if (jj_3R_442()) {
    jj_scanpos = xsp;
    if (jj_3_53()) {
    jj_scanpos = xsp;
    if (jj_3R_443()) return true;
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_252.
   *
   * @return true, if successful
   */
  private boolean jj_3R_252() {
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_288.
   *
   * @return true, if successful
   */
  private boolean jj_3R_288() {
    if (jj_3R_307()) return true;
    return false;
  }

  /**
   * Jj_3 r_437.
   *
   * @return true, if successful
   */
  private boolean jj_3R_437() {
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_234.
   *
   * @return true, if successful
   */
  private boolean jj_3R_234() {
    if (jj_scan_token(NEW)) return true;
    if (jj_3R_88()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_269()) {
    jj_scanpos = xsp;
    if (jj_3R_270()) {
    jj_scanpos = xsp;
    if (jj_3R_271()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_287.
   *
   * @return true, if successful
   */
  private boolean jj_3R_287() {
    if (jj_3R_306()) return true;
    return false;
  }

  /**
   * Jj_3 r_286.
   *
   * @return true, if successful
   */
  private boolean jj_3R_286() {
    if (jj_3R_305()) return true;
    return false;
  }

  /**
   * Jj_3 r_429.
   *
   * @return true, if successful
   */
  private boolean jj_3R_429() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_437()) {
    jj_scanpos = xsp;
    if (jj_3R_438()) return true;
    }
    return false;
  }

  /**
   * Jj_3_5.
   *
   * @return true, if successful
   */
  private boolean jj_3_5() {
    if (jj_3R_83()) return true;
    return false;
  }

  /**
   * Jj_3 r_285.
   *
   * @return true, if successful
   */
  private boolean jj_3R_285() {
    if (jj_3R_304()) return true;
    return false;
  }

  /**
   * Jj_3 r_284.
   *
   * @return true, if successful
   */
  private boolean jj_3R_284() {
    if (jj_3R_303()) return true;
    return false;
  }

  /**
   * Jj_3 r_251.
   *
   * @return true, if successful
   */
  private boolean jj_3R_251() {
    if (jj_3R_283()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_284()) {
    jj_scanpos = xsp;
    if (jj_3R_285()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_286()) {
    jj_scanpos = xsp;
    if (jj_3R_287()) {
    jj_scanpos = xsp;
    if (jj_3R_288()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3_8.
   *
   * @return true, if successful
   */
  private boolean jj_3_8() {
    if (jj_3R_87()) return true;
    return false;
  }

  /**
   * Jj_3 r_295.
   *
   * @return true, if successful
   */
  private boolean jj_3R_295() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_414.
   *
   * @return true, if successful
   */
  private boolean jj_3R_414() {
    if (jj_3R_429()) return true;
    return false;
  }

  /**
   * Jj_3 r_212.
   *
   * @return true, if successful
   */
  private boolean jj_3R_212() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3R_251()) {
    jj_scanpos = xsp;
    if (jj_3R_252()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_394.
   *
   * @return true, if successful
   */
  private boolean jj_3R_394() {
    if (jj_scan_token(LBRACE)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_414()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_258.
   *
   * @return true, if successful
   */
  private boolean jj_3R_258() {
    if (jj_3R_96()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_295()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_226.
   *
   * @return true, if successful
   */
  private boolean jj_3R_226() {
    if (jj_3R_258()) return true;
    return false;
  }

  /**
   * Jj_3 r_184.
   *
   * @return true, if successful
   */
  private boolean jj_3R_184() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_226()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_169.
   *
   * @return true, if successful
   */
  private boolean jj_3R_169() {
    if (jj_3R_212()) return true;
    return false;
  }

  /**
   * Jj_3 r_83.
   *
   * @return true, if successful
   */
  private boolean jj_3R_83() {
    if (jj_scan_token(AT)) return true;
    if (jj_scan_token(INTERFACE)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_3R_394()) return true;
    return false;
  }

  /**
   * Jj_3 r_111.
   *
   * @return true, if successful
   */
  private boolean jj_3R_111() {
    if (jj_scan_token(LBRACE)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_169()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_297.
   *
   * @return true, if successful
   */
  private boolean jj_3R_297() {
    if (jj_scan_token(NULL)) return true;
    return false;
  }

  /**
   * Jj_3_23.
   *
   * @return true, if successful
   */
  private boolean jj_3_23() {
    if (jj_scan_token(HOOK)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(COLON)) return true;
    if (jj_3R_149()) return true;
    return false;
  }

  /**
   * Jj_3_51.
   *
   * @return true, if successful
   */
  private boolean jj_3_51() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_120()) return true;
    return false;
  }

  /**
   * Jj_3 r_310.
   *
   * @return true, if successful
   */
  private boolean jj_3R_310() {
    if (jj_scan_token(BIT_AND)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    return false;
  }

  /**
   * Jj_3 r_313.
   *
   * @return true, if successful
   */
  private boolean jj_3R_313() {
    if (jj_scan_token(FALSE)) return true;
    return false;
  }

  /**
   * Jj_3 r_149.
   *
   * @return true, if successful
   */
  private boolean jj_3R_149() {
    if (jj_3R_200()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_23()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_344.
   *
   * @return true, if successful
   */
  private boolean jj_3R_344() {
    if (jj_3R_120()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_51()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_312.
   *
   * @return true, if successful
   */
  private boolean jj_3R_312() {
    if (jj_scan_token(TRUE)) return true;
    return false;
  }

  /**
   * Jj_3 r_296.
   *
   * @return true, if successful
   */
  private boolean jj_3R_296() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_312()) {
    jj_scanpos = xsp;
    if (jj_3R_313()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_291.
   *
   * @return true, if successful
   */
  private boolean jj_3R_291() {
    if (jj_scan_token(EXTENDS)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_310()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_222.
   *
   * @return true, if successful
   */
  private boolean jj_3R_222() {
    if (jj_scan_token(LBRACE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_344()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(88)) jj_scanpos = xsp;
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_148.
   *
   * @return true, if successful
   */
  private boolean jj_3R_148() {
    if (jj_scan_token(ORASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_265.
   *
   * @return true, if successful
   */
  private boolean jj_3R_265() {
    if (jj_3R_297()) return true;
    return false;
  }

  /**
   * Jj_3 r_256.
   *
   * @return true, if successful
   */
  private boolean jj_3R_256() {
    if (jj_3R_291()) return true;
    return false;
  }

  /**
   * Jj_3 r_264.
   *
   * @return true, if successful
   */
  private boolean jj_3R_264() {
    if (jj_3R_296()) return true;
    return false;
  }

  /**
   * Jj_3 r_179.
   *
   * @return true, if successful
   */
  private boolean jj_3R_179() {
    if (jj_3R_149()) return true;
    return false;
  }

  /**
   * Jj_3 r_147.
   *
   * @return true, if successful
   */
  private boolean jj_3R_147() {
    if (jj_scan_token(XORASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_178.
   *
   * @return true, if successful
   */
  private boolean jj_3R_178() {
    if (jj_3R_222()) return true;
    return false;
  }

  /**
   * Jj_3 r_263.
   *
   * @return true, if successful
   */
  private boolean jj_3R_263() {
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  /**
   * Jj_3_50.
   *
   * @return true, if successful
   */
  private boolean jj_3_50() {
    if (jj_3R_119()) return true;
    return false;
  }

  /**
   * Jj_3 r_146.
   *
   * @return true, if successful
   */
  private boolean jj_3R_146() {
    if (jj_scan_token(ANDASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_223.
   *
   * @return true, if successful
   */
  private boolean jj_3R_223() {
    if (jj_3R_88()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_256()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_120.
   *
   * @return true, if successful
   */
  private boolean jj_3R_120() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_50()) {
    jj_scanpos = xsp;
    if (jj_3R_178()) {
    jj_scanpos = xsp;
    if (jj_3R_179()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_262.
   *
   * @return true, if successful
   */
  private boolean jj_3R_262() {
    if (jj_scan_token(CHARACTER_LITERAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_145.
   *
   * @return true, if successful
   */
  private boolean jj_3R_145() {
    if (jj_scan_token(RUNSIGNEDSHIFTASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_261.
   *
   * @return true, if successful
   */
  private boolean jj_3R_261() {
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_144.
   *
   * @return true, if successful
   */
  private boolean jj_3R_144() {
    if (jj_scan_token(RSIGNEDSHIFTASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_260.
   *
   * @return true, if successful
   */
  private boolean jj_3R_260() {
    if (jj_scan_token(LONG_LITERAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_143.
   *
   * @return true, if successful
   */
  private boolean jj_3R_143() {
    if (jj_scan_token(LSHIFTASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_428.
   *
   * @return true, if successful
   */
  private boolean jj_3R_428() {
    if (jj_3R_111()) return true;
    return false;
  }

  /**
   * Jj_3 r_259.
   *
   * @return true, if successful
   */
  private boolean jj_3R_259() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_321.
   *
   * @return true, if successful
   */
  private boolean jj_3R_321() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(ASSIGN)) return true;
    if (jj_3R_120()) return true;
    return false;
  }

  /**
   * Jj_3 r_224.
   *
   * @return true, if successful
   */
  private boolean jj_3R_224() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_223()) return true;
    return false;
  }

  /**
   * Jj_3 r_142.
   *
   * @return true, if successful
   */
  private boolean jj_3R_142() {
    if (jj_scan_token(MINUSASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_227.
   *
   * @return true, if successful
   */
  private boolean jj_3R_227() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_259()) {
    jj_scanpos = xsp;
    if (jj_3R_260()) {
    jj_scanpos = xsp;
    if (jj_3R_261()) {
    jj_scanpos = xsp;
    if (jj_3R_262()) {
    jj_scanpos = xsp;
    if (jj_3R_263()) {
    jj_scanpos = xsp;
    if (jj_3R_264()) {
    jj_scanpos = xsp;
    if (jj_3R_265()) return true;
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_141.
   *
   * @return true, if successful
   */
  private boolean jj_3R_141() {
    if (jj_scan_token(PLUSASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_180.
   *
   * @return true, if successful
   */
  private boolean jj_3R_180() {
    if (jj_3R_223()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_224()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_140.
   *
   * @return true, if successful
   */
  private boolean jj_3R_140() {
    if (jj_scan_token(REMASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_123.
   *
   * @return true, if successful
   */
  private boolean jj_3R_123() {
    if (jj_scan_token(LT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_180()) jj_scanpos = xsp;
    if (jj_scan_token(GT)) return true;
    return false;
  }

  /**
   * Jj_3 r_139.
   *
   * @return true, if successful
   */
  private boolean jj_3R_139() {
    if (jj_scan_token(SLASHASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_322.
   *
   * @return true, if successful
   */
  private boolean jj_3R_322() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_321()) return true;
    return false;
  }

  /**
   * Jj_3 r_164.
   *
   * @return true, if successful
   */
  private boolean jj_3R_164() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  /**
   * Jj_3 r_110.
   *
   * @return true, if successful
   */
  private boolean jj_3R_110() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_138.
   *
   * @return true, if successful
   */
  private boolean jj_3R_138() {
    if (jj_scan_token(STARASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3_37.
   *
   * @return true, if successful
   */
  private boolean jj_3_37() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_110()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /**
   * Jj_3 r_249.
   *
   * @return true, if successful
   */
  private boolean jj_3R_249() {
    if (jj_3R_184()) return true;
    return false;
  }

  /**
   * Jj_3 r_137.
   *
   * @return true, if successful
   */
  private boolean jj_3R_137() {
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3 r_427.
   *
   * @return true, if successful
   */
  private boolean jj_3R_427() {
    if (jj_3R_184()) return true;
    return false;
  }

  /**
   * Jj_3 r_309.
   *
   * @return true, if successful
   */
  private boolean jj_3R_309() {
    if (jj_3R_321()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_322()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_95.
   *
   * @return true, if successful
   */
  private boolean jj_3R_95() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_137()) {
    jj_scanpos = xsp;
    if (jj_3R_138()) {
    jj_scanpos = xsp;
    if (jj_3R_139()) {
    jj_scanpos = xsp;
    if (jj_3R_140()) {
    jj_scanpos = xsp;
    if (jj_3R_141()) {
    jj_scanpos = xsp;
    if (jj_3R_142()) {
    jj_scanpos = xsp;
    if (jj_3R_143()) {
    jj_scanpos = xsp;
    if (jj_3R_144()) {
    jj_scanpos = xsp;
    if (jj_3R_145()) {
    jj_scanpos = xsp;
    if (jj_3R_146()) {
    jj_scanpos = xsp;
    if (jj_3R_147()) {
    jj_scanpos = xsp;
    if (jj_3R_148()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_248.
   *
   * @return true, if successful
   */
  private boolean jj_3R_248() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_207.
   *
   * @return true, if successful
   */
  private boolean jj_3R_207() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_248()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    xsp = jj_scanpos;
    if (jj_3R_249()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_206.
   *
   * @return true, if successful
   */
  private boolean jj_3R_206() {
    if (jj_3R_234()) return true;
    return false;
  }

  /**
   * Jj_3 r_122.
   *
   * @return true, if successful
   */
  private boolean jj_3R_122() {
    if (jj_3R_119()) return true;
    return false;
  }

  /**
   * Jj_3 r_290.
   *
   * @return true, if successful
   */
  private boolean jj_3R_290() {
    if (jj_3R_309()) return true;
    return false;
  }

  /**
   * Jj_3 r_205.
   *
   * @return true, if successful
   */
  private boolean jj_3R_205() {
    if (jj_scan_token(THIS)) return true;
    return false;
  }

  /**
   * Jj_3 r_220.
   *
   * @return true, if successful
   */
  private boolean jj_3R_220() {
    if (jj_scan_token(AT)) return true;
    if (jj_3R_117()) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_120()) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_82.
   *
   * @return true, if successful
   */
  private boolean jj_3R_82() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_122()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(IDENTIFIER)) return true;
    xsp = jj_scanpos;
    if (jj_3R_427()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_428()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_204.
   *
   * @return true, if successful
   */
  private boolean jj_3R_204() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3_22.
   *
   * @return true, if successful
   */
  private boolean jj_3_22() {
    if (jj_3R_95()) return true;
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_163.
   *
   * @return true, if successful
   */
  private boolean jj_3R_163() {
    if (jj_scan_token(DOT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_205()) {
    jj_scanpos = xsp;
    if (jj_3R_206()) {
    jj_scanpos = xsp;
    if (jj_3R_207()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_96.
   *
   * @return true, if successful
   */
  private boolean jj_3R_96() {
    if (jj_3R_149()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_22()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_162.
   *
   * @return true, if successful
   */
  private boolean jj_3R_162() {
    if (jj_scan_token(130)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_204()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(78)) {
    jj_scanpos = xsp;
    if (jj_scan_token(43)) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_106.
   *
   * @return true, if successful
   */
  private boolean jj_3R_106() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_162()) {
    jj_scanpos = xsp;
    if (jj_3R_163()) {
    jj_scanpos = xsp;
    if (jj_3R_164()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3 r_221.
   *
   * @return true, if successful
   */
  private boolean jj_3R_221() {
    if (jj_scan_token(AT)) return true;
    if (jj_3R_117()) return true;
    return false;
  }

  /**
   * Jj_3 r_413.
   *
   * @return true, if successful
   */
  private boolean jj_3R_413() {
    if (jj_3R_212()) return true;
    return false;
  }

  /**
   * Jj_3 r_219.
   *
   * @return true, if successful
   */
  private boolean jj_3R_219() {
    if (jj_scan_token(AT)) return true;
    if (jj_3R_117()) return true;
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_290()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_393.
   *
   * @return true, if successful
   */
  private boolean jj_3R_393() {
    if (jj_scan_token(SEMICOLON)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_413()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_161.
   *
   * @return true, if successful
   */
  private boolean jj_3R_161() {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(SUPER)) return true;
    return false;
  }

  /**
   * Jj_3 r_118.
   *
   * @return true, if successful
   */
  private boolean jj_3R_118() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  /**
   * Jj_3_36.
   *
   * @return true, if successful
   */
  private boolean jj_3_36() {
    if (jj_3R_106()) return true;
    return false;
  }

  /**
   * Jj_3_4.
   *
   * @return true, if successful
   */
  private boolean jj_3_4() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_82()) return true;
    return false;
  }

  /**
   * Jj_3 r_105.
   *
   * @return true, if successful
   */
  private boolean jj_3R_105() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_36()) {
    jj_scanpos = xsp;
    if (jj_3R_161()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_127.
   *
   * @return true, if successful
   */
  private boolean jj_3R_127() {
    if (jj_3R_119()) return true;
    return false;
  }

  /**
   * Jj_3 r_88.
   *
   * @return true, if successful
   */
  private boolean jj_3R_88() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_127()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_392.
   *
   * @return true, if successful
   */
  private boolean jj_3R_392() {
    if (jj_3R_82()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_4()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_49.
   *
   * @return true, if successful
   */
  private boolean jj_3_49() {
    if (jj_scan_token(AT)) return true;
    if (jj_3R_117()) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_391.
   *
   * @return true, if successful
   */
  private boolean jj_3R_391() {
    if (jj_3R_404()) return true;
    return false;
  }

  /**
   * Jj_3_48.
   *
   * @return true, if successful
   */
  private boolean jj_3_48() {
    if (jj_scan_token(AT)) return true;
    if (jj_3R_117()) return true;
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_118()) {
    jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_304.
   *
   * @return true, if successful
   */
  private boolean jj_3R_304() {
    if (jj_scan_token(ENUM)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_391()) jj_scanpos = xsp;
    if (jj_scan_token(LBRACE)) return true;
    xsp = jj_scanpos;
    if (jj_3R_392()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(88)) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_393()) jj_scanpos = xsp;
    if (jj_scan_token(RBRACE)) return true;
    return false;
  }

  /**
   * Jj_3 r_177.
   *
   * @return true, if successful
   */
  private boolean jj_3R_177() {
    if (jj_3R_221()) return true;
    return false;
  }

  /**
   * Jj_3 r_176.
   *
   * @return true, if successful
   */
  private boolean jj_3R_176() {
    if (jj_3R_220()) return true;
    return false;
  }

  /**
   * Jj_3 r_273.
   *
   * @return true, if successful
   */
  private boolean jj_3R_273() {
    if (jj_3R_184()) return true;
    return false;
  }

  /**
   * Jj_3 r_175.
   *
   * @return true, if successful
   */
  private boolean jj_3R_175() {
    if (jj_3R_219()) return true;
    return false;
  }

  /**
   * Jj_3 r_235.
   *
   * @return true, if successful
   */
  private boolean jj_3R_235() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_150.
   *
   * @return true, if successful
   */
  private boolean jj_3R_150() {
    return false;
  }

  /**
   * Jj_3 r_119.
   *
   * @return true, if successful
   */
  private boolean jj_3R_119() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_175()) {
    jj_scanpos = xsp;
    if (jj_3R_176()) {
    jj_scanpos = xsp;
    if (jj_3R_177()) return true;
    }
    }
    return false;
  }

  /**
   * Jj_3_21.
   *
   * @return true, if successful
   */
  private boolean jj_3_21() {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /**
   * Jj_3 r_300.
   *
   * @return true, if successful
   */
  private boolean jj_3R_300() {
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_299.
   *
   * @return true, if successful
   */
  private boolean jj_3R_299() {
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_117.
   *
   * @return true, if successful
   */
  private boolean jj_3R_117() {
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_21()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_151.
   *
   * @return true, if successful
   */
  private boolean jj_3R_151() {
    return false;
  }

  /**
   * Jj_3 r_430.
   *
   * @return true, if successful
   */
  private boolean jj_3R_430() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    return false;
  }

  /**
   * Jj_3 r_98.
   *
   * @return true, if successful
   */
  private boolean jj_3R_98() {
    jj_lookingAhead = true;
    jj_semLA = getToken(1).kind == GT && ((GTToken) getToken(1)).realKind == RSIGNEDSHIFT;
    jj_lookingAhead = false;
    if (!jj_semLA || jj_3R_150()) return true;
    if (jj_scan_token(GT)) return true;
    if (jj_scan_token(GT)) return true;
    return false;
  }

  /**
   * Jj_3 r_236.
   *
   * @return true, if successful
   */
  private boolean jj_3R_236() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_272()) {
    jj_scanpos = xsp;
    if (jj_3R_273()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_272.
   *
   * @return true, if successful
   */
  private boolean jj_3R_272() {
    if (jj_scan_token(131)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_299()) {
    jj_scanpos = xsp;
    if (jj_3R_300()) return true;
    }
    return false;
  }

  /**
   * Jj_3_35.
   *
   * @return true, if successful
   */
  private boolean jj_3_35() {
    if (jj_3R_109()) return true;
    if (jj_scan_token(130)) return true;
    return false;
  }

  /**
   * Jj_3 r_415.
   *
   * @return true, if successful
   */
  private boolean jj_3R_415() {
    if (jj_scan_token(THROWS)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_430()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3_34.
   *
   * @return true, if successful
   */
  private boolean jj_3_34() {
    if (jj_3R_109()) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(CLASS)) return true;
    return false;
  }

  /**
   * Jj_3 r_196.
   *
   * @return true, if successful
   */
  private boolean jj_3R_196() {
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_236()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_99.
   *
   * @return true, if successful
   */
  private boolean jj_3R_99() {
    jj_lookingAhead = true;
    jj_semLA = getToken(1).kind == GT && ((GTToken) getToken(1)).realKind == RUNSIGNEDSHIFT;
    jj_lookingAhead = false;
    if (!jj_semLA || jj_3R_151()) return true;
    if (jj_scan_token(GT)) return true;
    if (jj_scan_token(GT)) return true;
    if (jj_scan_token(GT)) return true;
    return false;
  }

  /**
   * Jj_3 r_168.
   *
   * @return true, if successful
   */
  private boolean jj_3R_168() {
    if (jj_3R_85()) return true;
    return false;
  }

  /**
   * Jj_3 r_195.
   *
   * @return true, if successful
   */
  private boolean jj_3R_195() {
    if (jj_3R_109()) return true;
    if (jj_scan_token(130)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_235()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(78)) {
    jj_scanpos = xsp;
    if (jj_scan_token(43)) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_167.
   *
   * @return true, if successful
   */
  private boolean jj_3R_167() {
    if (jj_scan_token(VOID)) return true;
    return false;
  }

  /**
   * Jj_3 r_109.
   *
   * @return true, if successful
   */
  private boolean jj_3R_109() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_167()) {
    jj_scanpos = xsp;
    if (jj_3R_168()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_194.
   *
   * @return true, if successful
   */
  private boolean jj_3R_194() {
    if (jj_3R_109()) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(CLASS)) return true;
    return false;
  }

  /**
   * Jj_3 r_390.
   *
   * @return true, if successful
   */
  private boolean jj_3R_390() {
    if (jj_scan_token(FINALLY)) return true;
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_193.
   *
   * @return true, if successful
   */
  private boolean jj_3R_193() {
    if (jj_3R_234()) return true;
    return false;
  }

  /**
   * Jj_3 r_192.
   *
   * @return true, if successful
   */
  private boolean jj_3R_192() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3_33.
   *
   * @return true, if successful
   */
  private boolean jj_3_33() {
    if (jj_3R_108()) return true;
    if (jj_scan_token(131)) return true;
    return false;
  }

  /**
   * Jj_3 r_233.
   *
   * @return true, if successful
   */
  private boolean jj_3R_233() {
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_420.
   *
   * @return true, if successful
   */
  private boolean jj_3R_420() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    return false;
  }

  /**
   * Jj_3 r_377.
   *
   * @return true, if successful
   */
  private boolean jj_3R_377() {
    if (jj_3R_404()) return true;
    return false;
  }

  /**
   * Jj_3 r_232.
   *
   * @return true, if successful
   */
  private boolean jj_3R_232() {
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_160.
   *
   * @return true, if successful
   */
  private boolean jj_3R_160() {
    if (jj_scan_token(DOUBLE)) return true;
    return false;
  }

  /**
   * Jj_3 r_412.
   *
   * @return true, if successful
   */
  private boolean jj_3R_412() {
    if (jj_scan_token(BIT_OR)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  /**
   * Jj_3 r_404.
   *
   * @return true, if successful
   */
  private boolean jj_3R_404() {
    if (jj_scan_token(IMPLEMENTS)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_420()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_159.
   *
   * @return true, if successful
   */
  private boolean jj_3R_159() {
    if (jj_scan_token(FLOAT)) return true;
    return false;
  }

  /**
   * Jj_3 r_191.
   *
   * @return true, if successful
   */
  private boolean jj_3R_191() {
    if (jj_3R_108()) return true;
    if (jj_scan_token(131)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_232()) {
    jj_scanpos = xsp;
    if (jj_3R_233()) return true;
    }
    return false;
  }

  /**
   * Jj_3_32.
   *
   * @return true, if successful
   */
  private boolean jj_3_32() {
    if (jj_3R_107()) return true;
    if (jj_scan_token(131)) return true;
    return false;
  }

  /**
   * Jj_3 r_231.
   *
   * @return true, if successful
   */
  private boolean jj_3R_231() {
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_158.
   *
   * @return true, if successful
   */
  private boolean jj_3R_158() {
    if (jj_scan_token(LONG)) return true;
    return false;
  }

  /**
   * Jj_3 r_230.
   *
   * @return true, if successful
   */
  private boolean jj_3R_230() {
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_411.
   *
   * @return true, if successful
   */
  private boolean jj_3R_411() {
    if (jj_scan_token(FINAL)) return true;
    return false;
  }

  /**
   * Jj_3 r_157.
   *
   * @return true, if successful
   */
  private boolean jj_3R_157() {
    if (jj_scan_token(INT)) return true;
    return false;
  }

  /**
   * Jj_3 r_379.
   *
   * @return true, if successful
   */
  private boolean jj_3R_379() {
    if (jj_3R_403()) return true;
    return false;
  }

  /**
   * Jj_3 r_268.
   *
   * @return true, if successful
   */
  private boolean jj_3R_268() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_389.
   *
   * @return true, if successful
   */
  private boolean jj_3R_389() {
    if (jj_scan_token(CATCH)) return true;
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_411()) jj_scanpos = xsp;
    if (jj_3R_85()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_412()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3 r_156.
   *
   * @return true, if successful
   */
  private boolean jj_3R_156() {
    if (jj_scan_token(SHORT)) return true;
    return false;
  }

  /**
   * Jj_3 r_190.
   *
   * @return true, if successful
   */
  private boolean jj_3R_190() {
    if (jj_3R_107()) return true;
    if (jj_scan_token(131)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_230()) {
    jj_scanpos = xsp;
    if (jj_3R_231()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_155.
   *
   * @return true, if successful
   */
  private boolean jj_3R_155() {
    if (jj_scan_token(BYTE)) return true;
    return false;
  }

  /**
   * Jj_3 r_229.
   *
   * @return true, if successful
   */
  private boolean jj_3R_229() {
    if (jj_scan_token(130)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_268()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(78)) {
    jj_scanpos = xsp;
    if (jj_scan_token(43)) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_419.
   *
   * @return true, if successful
   */
  private boolean jj_3R_419() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    return false;
  }

  /**
   * Jj_3 r_154.
   *
   * @return true, if successful
   */
  private boolean jj_3R_154() {
    if (jj_scan_token(CHAR)) return true;
    return false;
  }

  /**
   * Jj_3 r_410.
   *
   * @return true, if successful
   */
  private boolean jj_3R_410() {
    if (jj_3R_116()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(87)) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_267.
   *
   * @return true, if successful
   */
  private boolean jj_3R_267() {
    if (jj_3R_184()) return true;
    return false;
  }

  /**
   * Jj_3 r_153.
   *
   * @return true, if successful
   */
  private boolean jj_3R_153() {
    if (jj_scan_token(BOOLEAN)) return true;
    return false;
  }

  /**
   * Jj_3 r_266.
   *
   * @return true, if successful
   */
  private boolean jj_3R_266() {
    if (jj_3R_94()) return true;
    return false;
  }

  /**
   * Jj_3 r_388.
   *
   * @return true, if successful
   */
  private boolean jj_3R_388() {
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_410()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /**
   * Jj_3 r_103.
   *
   * @return true, if successful
   */
  private boolean jj_3R_103() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_153()) {
    jj_scanpos = xsp;
    if (jj_3R_154()) {
    jj_scanpos = xsp;
    if (jj_3R_155()) {
    jj_scanpos = xsp;
    if (jj_3R_156()) {
    jj_scanpos = xsp;
    if (jj_3R_157()) {
    jj_scanpos = xsp;
    if (jj_3R_158()) {
    jj_scanpos = xsp;
    if (jj_3R_159()) {
    jj_scanpos = xsp;
    if (jj_3R_160()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_403.
   *
   * @return true, if successful
   */
  private boolean jj_3R_403() {
    if (jj_scan_token(EXTENDS)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_112()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_419()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_376.
   *
   * @return true, if successful
   */
  private boolean jj_3R_376() {
    if (jj_3R_403()) return true;
    return false;
  }

  /**
   * Jj_3 r_358.
   *
   * @return true, if successful
   */
  private boolean jj_3R_358() {
    if (jj_scan_token(TRY)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_388()) jj_scanpos = xsp;
    if (jj_3R_126()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_389()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_3R_390()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_228.
   *
   * @return true, if successful
   */
  private boolean jj_3R_228() {
    if (jj_scan_token(DOT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_266()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    xsp = jj_scanpos;
    if (jj_3R_267()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_189.
   *
   * @return true, if successful
   */
  private boolean jj_3R_189() {
    if (jj_scan_token(SUPER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_228()) {
    jj_scanpos = xsp;
    if (jj_3R_229()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_378.
   *
   * @return true, if successful
   */
  private boolean jj_3R_378() {
    if (jj_3R_123()) return true;
    return false;
  }

  /**
   * Jj_3 r_339.
   *
   * @return true, if successful
   */
  private boolean jj_3R_339() {
    if (jj_scan_token(SUPER)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_93()) return true;
    return false;
  }

  /**
   * Jj_3 r_188.
   *
   * @return true, if successful
   */
  private boolean jj_3R_188() {
    if (jj_scan_token(THIS)) return true;
    return false;
  }

  /**
   * Jj_3 r_187.
   *
   * @return true, if successful
   */
  private boolean jj_3R_187() {
    if (jj_3R_227()) return true;
    return false;
  }

  /**
   * Jj_3 r_338.
   *
   * @return true, if successful
   */
  private boolean jj_3R_338() {
    if (jj_scan_token(EXTENDS)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_93()) return true;
    return false;
  }

  /**
   * Jj_3 r_314.
   *
   * @return true, if successful
   */
  private boolean jj_3R_314() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_338()) {
    jj_scanpos = xsp;
    if (jj_3R_339()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_132.
   *
   * @return true, if successful
   */
  private boolean jj_3R_132() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_187()) {
    jj_scanpos = xsp;
    if (jj_3R_188()) {
    jj_scanpos = xsp;
    if (jj_3R_189()) {
    jj_scanpos = xsp;
    if (jj_3R_190()) {
    jj_scanpos = xsp;
    if (jj_3R_191()) {
    jj_scanpos = xsp;
    if (jj_3R_192()) {
    jj_scanpos = xsp;
    if (jj_3R_193()) {
    jj_scanpos = xsp;
    if (jj_3R_194()) {
    jj_scanpos = xsp;
    if (jj_3R_195()) {
    jj_scanpos = xsp;
    if (jj_3R_196()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  /**
   * Jj_3 r_342.
   *
   * @return true, if successful
   */
  private boolean jj_3R_342() {
    if (jj_scan_token(INTERFACE)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_378()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_379()) jj_scanpos = xsp;
    if (jj_3R_111()) return true;
    return false;
  }

  /**
   * Jj_3 r_274.
   *
   * @return true, if successful
   */
  private boolean jj_3R_274() {
    if (jj_scan_token(HOOK)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_314()) jj_scanpos = xsp;
    return false;
  }

  /**
   * Jj_3 r_357.
   *
   * @return true, if successful
   */
  private boolean jj_3R_357() {
    if (jj_scan_token(SYNCHRONIZED)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(RPAREN)) return true;
    if (jj_3R_126()) return true;
    return false;
  }

  /**
   * Jj_3_31.
   *
   * @return true, if successful
   */
  private boolean jj_3_31() {
    if (jj_3R_106()) return true;
    return false;
  }

  /**
   * Jj_3 r_375.
   *
   * @return true, if successful
   */
  private boolean jj_3R_375() {
    if (jj_3R_123()) return true;
    return false;
  }

  /**
   * Jj_3 r_239.
   *
   * @return true, if successful
   */
  private boolean jj_3R_239() {
    if (jj_3R_274()) return true;
    return false;
  }

  /**
   * Jj_3 r_238.
   *
   * @return true, if successful
   */
  private boolean jj_3R_238() {
    if (jj_3R_93()) return true;
    return false;
  }

  /**
   * Jj_3 r_387.
   *
   * @return true, if successful
   */
  private boolean jj_3R_387() {
    if (jj_3R_96()) return true;
    return false;
  }

  /**
   * Jj_3 r_356.
   *
   * @return true, if successful
   */
  private boolean jj_3R_356() {
    if (jj_scan_token(THROW)) return true;
    if (jj_3R_96()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_341.
   *
   * @return true, if successful
   */
  private boolean jj_3R_341() {
    if (jj_scan_token(CLASS)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_375()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_376()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_377()) jj_scanpos = xsp;
    if (jj_3R_111()) return true;
    return false;
  }

  /**
   * Jj_3 r_133.
   *
   * @return true, if successful
   */
  private boolean jj_3R_133() {
    if (jj_3R_106()) return true;
    return false;
  }

  /**
   * Jj_3 r_199.
   *
   * @return true, if successful
   */
  private boolean jj_3R_199() {
    if (jj_3R_88()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_238()) {
    jj_scanpos = xsp;
    if (jj_3R_239()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_91.
   *
   * @return true, if successful
   */
  private boolean jj_3R_91() {
    if (jj_3R_132()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_133()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_355.
   *
   * @return true, if successful
   */
  private boolean jj_3R_355() {
    if (jj_scan_token(RETURN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_387()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_317.
   *
   * @return true, if successful
   */
  private boolean jj_3R_317() {
    if (jj_3R_342()) return true;
    return false;
  }

  /**
   * Jj_3 r_316.
   *
   * @return true, if successful
   */
  private boolean jj_3R_316() {
    if (jj_3R_341()) return true;
    return false;
  }

  /**
   * Jj_3 r_237.
   *
   * @return true, if successful
   */
  private boolean jj_3R_237() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_199()) return true;
    return false;
  }

  /**
   * Jj_3_30.
   *
   * @return true, if successful
   */
  private boolean jj_3_30() {
    if (jj_3R_105()) return true;
    return false;
  }

  /**
   * Jj_3 r_303.
   *
   * @return true, if successful
   */
  private boolean jj_3R_303() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_316()) {
    jj_scanpos = xsp;
    if (jj_3R_317()) return true;
    }
    return false;
  }

  /**
   * Jj_3 r_302.
   *
   * @return true, if successful
   */
  private boolean jj_3R_302() {
    if (jj_3R_132()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_30()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_136.
   *
   * @return true, if successful
   */
  private boolean jj_3R_136() {
    if (jj_3R_199()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_237()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /**
   * Jj_3 r_386.
   *
   * @return true, if successful
   */
  private boolean jj_3R_386() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /**
   * Jj_3 r_94.
   *
   * @return true, if successful
   */
  private boolean jj_3R_94() {
    if (jj_scan_token(LT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_136()) jj_scanpos = xsp;
    if (jj_scan_token(GT)) return true;
    return false;
  }

  /**
   * Jj_3 r_354.
   *
   * @return true, if successful
   */
  private boolean jj_3R_354() {
    if (jj_scan_token(CONTINUE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_386()) jj_scanpos = xsp;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /**
   * Jj_3 r_202.
   *
   * @return true, if successful
   */
  private boolean jj_3R_202() {
    if (jj_scan_token(BIT_AND)) return true;
    if (jj_3R_88()) return true;
    if (jj_3R_93()) return true;
    return false;
  }

  /**
   * Jj_3_20.
   *
   * @return true, if successful
   */
  private boolean jj_3_20() {
    if (jj_3R_94()) return true;
    return false;
  }

  /** Generated Token Manager. */
  public ASTParserTokenManager token_source;
  
  /**
   * The jj_input_stream.
   */
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  
  /**
   * The jj_ntk.
   */
  private int jj_ntk;
  
  /**
   * The jj_lastpos.
   */
  private Token jj_scanpos, jj_lastpos;
  
  /**
   * The jj_la.
   */
  private int jj_la;
  /** Whether we are looking ahead. */
  private boolean jj_lookingAhead = false;
  
  /**
   * The jj_sem la.
   */
  private boolean jj_semLA;
  
  /**
   * The jj_gen.
   */
  private int jj_gen;
  
  /**
   * The jj_la1.
   */
  final private int[] jj_la1 = new int[162];
  
  /**
   * The jj_la1_0.
   */
  static private int[] jj_la1_0;
  
  /**
   * The jj_la1_1.
   */
  static private int[] jj_la1_1;
  
  /**
   * The jj_la1_2.
   */
  static private int[] jj_la1_2;
  
  /**
   * The jj_la1_3.
   */
  static private int[] jj_la1_3;
  
  /**
   * The jj_la1_4.
   */
  static private int[] jj_la1_4;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
      jj_la1_init_3();
      jj_la1_init_4();
   }
   
   /**
    * Jj_la1_init_0.
    */
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x48101000,0x1,0x0,0x0,0x0,0x40001000,0x40801000,0x8100000,0x48101000,0x100000,0x0,0x10000000,0x0,0x0,0x10000000,0x0,0x0,0x0,0x0,0x0,0x0,0x4a995000,0x0,0x0,0x0,0x0,0x0,0x0,0x10000000,0x0,0x4a995000,0x8100000,0x2094000,0x4a995000,0x0,0x0,0x0,0x22094000,0x22094000,0x0,0x0,0x0,0x0,0x0,0x0,0x42095000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x22094000,0x6359f000,0x0,0x2094000,0x2094000,0x2094000,0x0,0x2094000,0x2094000,0x10000000,0x10000000,0x2094000,0x2094000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x22094000,0x0,0x0,0x22094000,0x0,0x0,0x0,0x2094000,0x0,0x0,0x0,0x0,0x0,0x22094000,0x22094000,0x0,0x0,0x22094000,0x0,0x0,0x20000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x20000000,0x20000000,0x22094000,0x0,0x0,0x2094000,0x0,0x0,0x0,0x2349e000,0x0,0x2349e000,0x0,0x0,0x0,0x22094000,0x820000,0x820000,0x4000000,0x62095000,0x22094000,0x22094000,0x62095000,0x22094000,0x0,0x0,0x0,0x22094000,0x42095000,0x0,0x0,0x40000,0x40000000,0x0,0x80000000,0x0,0x0,0x0,0x0,0x22094000,0x22094000,0x0,0x4a195000,0x8100000,0x2094000,0x4a195000,0x800000,};
   }
   
   /**
    * Jj_la1_init_1.
    */
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x20,0x8899c500,0x0,0x0,0x80000,0x0,0x8899c400,0x8899c400,0x100,0x8899c500,0x100,0x0,0x0,0x10,0x0,0x0,0x0,0x0,0x0,0x10,0x0,0x0,0xc89dc781,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xc89dc781,0x100,0x40040281,0xc89dc781,0x0,0x0,0x1000000,0x51241a81,0x51241a81,0x0,0x0,0x0,0x4000000,0x0,0x0,0x889dc681,0x0,0x0,0x0,0x0,0x4000000,0x0,0x0,0x51241a81,0xfbffdf8b,0x80000,0x40281,0x40281,0x40281,0x0,0x40281,0x40281,0x200000,0x200000,0x40281,0x40040281,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x40,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x51241a81,0x0,0x0,0x51241a81,0x0,0x0,0x0,0x40281,0x0,0x0,0x0,0x800,0x0,0x51241a81,0x51241a81,0x0,0x800,0x51241a81,0x0,0x0,0x11201000,0x800,0x0,0x0,0x0,0x800,0x0,0x0,0x1000800,0x0,0x10001000,0x10000000,0x51241a81,0x0,0x0,0x40281,0x0,0x0,0x0,0x73e61a8b,0x0,0x73e61a8b,0x0,0x0,0x0,0x51241a81,0x0,0x0,0x0,0xd9bdde81,0x51241a81,0x51241a81,0xd9bdde81,0x51241a81,0x0,0x0,0x0,0x51241a81,0x889dc681,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x51241a81,0x51241a81,0x0,0x889dc781,0x100,0x40281,0x889dc781,0x0,};
   }
   
   /**
    * Jj_la1_init_2.
    */
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x0,0x4800000,0x0,0x4000000,0x0,0x2000000,0x4000000,0x4000000,0x4000000,0x4800000,0x0,0x10000000,0x0,0x0,0x10000000,0x0,0x1000000,0x1000000,0x1000000,0x0,0x4004000,0x1000000,0x14884000,0x800000,0x4000000,0x20000,0x80000,0x1000000,0x4004000,0x0,0x0,0x14884000,0x0,0x14004000,0x14804000,0x1000000,0x8000000,0x4000,0x640a7086,0x640a7086,0x1000000,0x10000000,0x4200000,0x0,0x880000,0x1000000,0x4004000,0x4000000,0x1000000,0x4000,0x10000000,0x0,0x10000000,0x10000000,0x14027086,0x48a7087,0x0,0x0,0x0,0x4000,0x1000000,0x84004000,0x80004000,0x0,0x0,0x0,0x4004000,0x1000000,0x8000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x10000000,0x10000000,0x0,0x0,0x0,0x0,0x0,0x0,0x64027086,0x60000000,0x60000000,0x4027086,0x0,0x0,0x0,0x4000,0x10000000,0x20000,0x10000000,0x4000,0x2000000,0x640a7086,0x640a7086,0x10000000,0x4000,0x640a7086,0x20000,0x20000,0x3086,0x20000,0x4000,0x2000000,0x10000000,0x4000,0x10000000,0x20000,0x0,0x2200000,0x3086,0x0,0x64027086,0x1000000,0x10000000,0x0,0x10004000,0x4200000,0x4200000,0x48a7087,0x0,0x48a7087,0x1000000,0x8000000,0x8000000,0x4027086,0x0,0x0,0x0,0x64027086,0x64027086,0x64027086,0x64827086,0x64027086,0x1000000,0x4000,0x4000,0x64027086,0x4004000,0x800000,0x20000,0x0,0x0,0x0,0x0,0x4000000,0x4000000,0x4000,0x1000000,0x640a7086,0x640a7086,0x1000000,0x4804000,0x0,0x4000,0x4804000,0x0,};
   }
   
   /**
    * Jj_la1_init_3.
    */
   private static void jj_la1_init_3() {
      jj_la1_3 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x780,0x780,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x20000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x180,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1ffc0000,0x20,0x40,0x4000,0x8000,0x2000,0x12,0x12,0x0,0xc,0xc,0x20000,0x600,0x600,0x11800,0x11800,0x600,0x780,0x0,0x0,0x0,0x180,0x2000,0x2000,0x0,0x0,0x0,0x0,0x0,0x0,0x780,0x780,0x0,0x0,0x780,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x780,0x0,0x0,0x0,0x0,0x0,0x0,0x180,0x1,0x180,0x0,0x1ffc0180,0x1ffc0180,0x180,0x0,0x0,0x0,0x780,0x780,0x780,0x780,0x780,0x0,0x0,0x0,0x780,0x0,0x0,0x0,0x0,0x0,0x4000,0x0,0x0,0x0,0x0,0x0,0x780,0x780,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
   
   /**
    * Jj_la1_init_4.
    */
   private static void jj_la1_init_4() {
      jj_la1_4 = new int[] {0x0,0x0,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4,0x0,0x0,0x0,0x0,0x0,0x8,0x8,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  
  /**
   * The jj_2_rtns.
   */
  final private JJCalls[] jj_2_rtns = new JJCalls[53];
  
  /**
   * The jj_rescan.
   */
  private boolean jj_rescan = false;
  
  /**
   * The jj_gc.
   */
  private int jj_gc = 0;

  /**
   *  Constructor with InputStream.
   *
   * @param stream the stream
   */
  public ASTParser(java.io.InputStream stream) {
     this(stream, null);
  }
  
  /**
   *  Constructor with InputStream and supplied encoding.
   *
   * @param stream the stream
   * @param encoding the encoding
   */
  public ASTParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ASTParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /**
   *  Reinitialise.
   *
   * @param stream the stream
   */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  
  /**
   *  Reinitialise.
   *
   * @param stream the stream
   * @param encoding the encoding
   */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /**
   *  Constructor.
   *
   * @param stream the stream
   */
  public ASTParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ASTParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /**
   *  Reinitialise.
   *
   * @param stream the stream
   */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /**
   *  Constructor with generated Token Manager.
   *
   * @param tm the tm
   */
  public ASTParser(ASTParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /**
   *  Reinitialise.
   *
   * @param tm the tm
   */
  public void ReInit(ASTParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /**
   * Jj_consume_token.
   *
   * @param kind the kind
   * @return the token
   * @throws ParseException the parse exception
   */
  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  /**
   * The Class LookaheadSuccess.
   */
  static private final class LookaheadSuccess extends java.lang.Error { }
  
  /**
   * The jj_ls.
   */
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  
  /**
   * Jj_scan_token.
   *
   * @param kind the kind
   * @return true, if successful
   */
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/**
 *  Get the next Token.
 *
 * @return the next token
 */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/**
 *  Get the specific Token.
 *
 * @param index the index
 * @return the token
 */
  final public Token getToken(int index) {
    Token t = jj_lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  /**
   * Jj_ntk.
   *
   * @return the int
   */
  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  /**
   * The jj_expentries.
   */
  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  
  /**
   * The jj_expentry.
   */
  private int[] jj_expentry;
  
  /**
   * The jj_kind.
   */
  private int jj_kind = -1;
  
  /**
   * The jj_lasttokens.
   */
  private int[] jj_lasttokens = new int[100];
  
  /**
   * The jj_endpos.
   */
  private int jj_endpos;

  /**
   * Jj_add_error_token.
   *
   * @param kind the kind
   * @param pos the pos
   */
  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /**
   *  Generate ParseException.
   *
   * @return the parses the exception
   */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[132];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 162; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
          if ((jj_la1_3[i] & (1<<j)) != 0) {
            la1tokens[96+j] = true;
          }
          if ((jj_la1_4[i] & (1<<j)) != 0) {
            la1tokens[128+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 132; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  /**
   * Jj_rescan_token.
   */
  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 53; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
            case 15: jj_3_16(); break;
            case 16: jj_3_17(); break;
            case 17: jj_3_18(); break;
            case 18: jj_3_19(); break;
            case 19: jj_3_20(); break;
            case 20: jj_3_21(); break;
            case 21: jj_3_22(); break;
            case 22: jj_3_23(); break;
            case 23: jj_3_24(); break;
            case 24: jj_3_25(); break;
            case 25: jj_3_26(); break;
            case 26: jj_3_27(); break;
            case 27: jj_3_28(); break;
            case 28: jj_3_29(); break;
            case 29: jj_3_30(); break;
            case 30: jj_3_31(); break;
            case 31: jj_3_32(); break;
            case 32: jj_3_33(); break;
            case 33: jj_3_34(); break;
            case 34: jj_3_35(); break;
            case 35: jj_3_36(); break;
            case 36: jj_3_37(); break;
            case 37: jj_3_38(); break;
            case 38: jj_3_39(); break;
            case 39: jj_3_40(); break;
            case 40: jj_3_41(); break;
            case 41: jj_3_42(); break;
            case 42: jj_3_43(); break;
            case 43: jj_3_44(); break;
            case 44: jj_3_45(); break;
            case 45: jj_3_46(); break;
            case 46: jj_3_47(); break;
            case 47: jj_3_48(); break;
            case 48: jj_3_49(); break;
            case 49: jj_3_50(); break;
            case 50: jj_3_51(); break;
            case 51: jj_3_52(); break;
            case 52: jj_3_53(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  /**
   * Jj_save.
   *
   * @param index the index
   * @param xla the xla
   */
  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  /**
   * The Class JJCalls.
   */
  static final class JJCalls {
    
    /**
     * The gen.
     */
    int gen;
    
    /**
     * The first.
     */
    Token first;
    
    /**
     * The arg.
     */
    int arg;
    
    /**
     * The next.
     */
    JJCalls next;
  }

}
