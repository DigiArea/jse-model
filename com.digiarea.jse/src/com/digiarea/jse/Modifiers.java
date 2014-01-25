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
package com.digiarea.jse;

import com.digiarea.jse.Node;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;

/** 
 * The Class Modifiers.
 */
public class Modifiers extends Node {

    /** 
     * The {@code int} value representing the {@code public} modifier.
     */
    public static final int PUBLIC = 0x00000001;

    /** 
     * The {@code int} value representing the {@code protected} modifier.
     */
    public static final int PRIVATE = 0x00000002;

    /** 
     * The {@code int} value representing the {@code protected} modifier.
     */
    public static final int PROTECTED = 0x00000004;

    /** 
     * The {@code int} value representing the {@code static} modifier.
     */
    public static final int STATIC = 0x00000008;

    /** 
     * The {@code int} value representing the {@code final} modifier.
     */
    public static final int FINAL = 0x00000010;

    /** 
     * The {@code int} value representing the {@code synchronized} modifier.
     */
    public static final int SYNCHRONIZED = 0x00000020;

    /** 
     * The {@code int} value representing the {@code volatile} modifier.
     */
    public static final int VOLATILE = 0x00000040;

    /** 
     * The {@code int} value representing the {@code native} modifier.
     */
    public static final int TRANSIENT = 0x00000080;

    /** 
     * The {@code int} value representing the {@code native} modifier.
     */
    public static final int NATIVE = 0x00000100;

    /** 
     * The {@code int} value representing the {@code abstract} modifier.
     */
    public static final int ABSTRACT = 0x00000400;

    /** 
     * The {@code int} value representing the {@code strictfp} modifier.
     */
    public static final int STRICTFP = 0x00000800;

    /** 
     * The {@code int} value representing the {@code default} modifier.
     */
    public static final int DEFAULT = 0x00001000;

    /** 
     * The modifiers.
     */
    private int modifiers = 0;

    /** 
     * Adds the modifier.
     *
     * @param mod            the mod
     * @return the int
     */
    public int addModifier(int mod) {
        return modifiers |= mod;
    }

    /** 
     * Checks for modifier.
     *
     * @param modifier            the modifier
     * @return true, if successful
     */
    public boolean hasModifier(int modifier) {
        return (modifiers & modifier) != 0;
    }

    /** 
     * Checks if is abstract.
     *
     * @return true, if is abstract
     */
    public boolean isAbstract() {
        return (modifiers & ABSTRACT) != 0;
    }

    /** 
     * Checks if is final.
     *
     * @return true, if is final
     */
    public boolean isFinal() {
        return (modifiers & FINAL) != 0;
    }

    /** 
     * Checks if is native.
     *
     * @return true, if is native
     */
    public boolean isNative() {
        return (modifiers & NATIVE) != 0;
    }

    /** 
     * Checks if is private.
     *
     * @return true, if is private
     */
    public boolean isPrivate() {
        return (modifiers & PRIVATE) != 0;
    }

    /** 
     * Checks if is protected.
     *
     * @return true, if is protected
     */
    public boolean isProtected() {
        return (modifiers & PROTECTED) != 0;
    }

    /** 
     * Checks if is public.
     *
     * @return true, if is public
     */
    public boolean isPublic() {
        return (modifiers & PUBLIC) != 0;
    }

    /** 
     * Checks if is static.
     *
     * @return true, if is static
     */
    public boolean isStatic() {
        return (modifiers & STATIC) != 0;
    }

    /** 
     * Checks if is strictfp.
     *
     * @return true, if is strictfp
     */
    public boolean isStrictfp() {
        return (modifiers & STRICTFP) != 0;
    }

    /** 
     * Checks if is synchronized.
     *
     * @return true, if is synchronized
     */
    public boolean isSynchronized() {
        return (modifiers & SYNCHRONIZED) != 0;
    }

    /** 
     * Checks if is transient.
     *
     * @return true, if is transient
     */
    public boolean isTransient() {
        return (modifiers & TRANSIENT) != 0;
    }

    /** 
     * Checks if is volatile.
     *
     * @return true, if is volatile
     */
    public boolean isVolatile() {
        return (modifiers & VOLATILE) != 0;
    }

    /** 
     * Checks if is dafault.
     *
     * @return true, if is volatile
     */
    public boolean isDefault() {
        return (modifiers & DEFAULT) != 0;
    }

    /** 
     * Removes the modifier.
     *
     * @param mod            the mod
     * @return the int
     */
    public int removeModifier(int mod) {
        return modifiers &= ~mod;
    }

    /** 
     * Adds the modifier.
     *
     * @param modifiers
     *            the modifiers
     * @param mod
     *            the mod
     * @return the int
     */
    public static int addModifier(int modifiers, int mod) {
        return modifiers | mod;
    }

    /** 
     * Checks for modifier.
     *
     * @param modifiers
     *            the modifiers
     * @param modifier
     *            the modifier
     * @return true, if successful
     */
    public static boolean hasModifier(int modifiers, int modifier) {
        return (modifiers & modifier) != 0;
    }

    /** 
     * Checks if is abstract.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is abstract
     */
    public static boolean isAbstract(int modifiers) {
        return (modifiers & ABSTRACT) != 0;
    }

    /** 
     * Checks if is final.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is final
     */
    public static boolean isFinal(int modifiers) {
        return (modifiers & FINAL) != 0;
    }

    /** 
     * Checks if is native.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is native
     */
    public static boolean isNative(int modifiers) {
        return (modifiers & NATIVE) != 0;
    }

    /** 
     * Checks if is private.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is private
     */
    public static boolean isPrivate(int modifiers) {
        return (modifiers & PRIVATE) != 0;
    }

    /** 
     * Checks if is protected.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is protected
     */
    public static boolean isProtected(int modifiers) {
        return (modifiers & PROTECTED) != 0;
    }

    /** 
     * Checks if is public.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is public
     */
    public static boolean isPublic(int modifiers) {
        return (modifiers & PUBLIC) != 0;
    }

    /** 
     * Checks if is static.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is static
     */
    public static boolean isStatic(int modifiers) {
        return (modifiers & STATIC) != 0;
    }

    /** 
     * Checks if is strictfp.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is strictfp
     */
    public static boolean isStrictfp(int modifiers) {
        return (modifiers & STRICTFP) != 0;
    }

    /** 
     * Checks if is synchronized.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is synchronized
     */
    public static boolean isSynchronized(int modifiers) {
        return (modifiers & SYNCHRONIZED) != 0;
    }

    /** 
     * Checks if is transient.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is transient
     */
    public static boolean isTransient(int modifiers) {
        return (modifiers & TRANSIENT) != 0;
    }

    /** 
     * Checks if is volatile.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is volatile
     */
    public static boolean isVolatile(int modifiers) {
        return (modifiers & VOLATILE) != 0;
    }

    /** 
     * Checks if is default.
     *
     * @param modifiers
     *            the modifiers
     * @return true, if is volatile
     */
    public static boolean isDefault(int modifiers) {
        return (modifiers & DEFAULT) != 0;
    }

    /** 
     * Removes the modifier.
     *
     * @param modifiers
     *            the modifiers
     * @param mod
     *            the mod
     * @return the int
     */
    public static int removeModifier(int modifiers, int mod) {
        return modifiers & ~mod;
    }

    /**
     * Gets the modifiers.
     *
     * @return the modifiers
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Sets the modifiers.
     *
     * @param modifiers the new modifiers
     */
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    /**
     * Instantiates a new modifiers.
     */
    Modifiers() {
        super();
    }

    /**
     * Instantiates a new modifiers.
     *
     * @param modifiers the modifiers
     * @param annotations the annotations
     * @param posBegin the pos begin
     * @param posEnd the pos end
     */
    Modifiers(int modifiers, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.modifiers = modifiers;
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.VoidVisitor, java.lang.Object)
     */
    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    /* (non-Javadoc)
     * @see com.digiarea.jse.Node#accept(com.digiarea.jse.visitor.GenericVisitor, java.lang.Object)
     */
    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
