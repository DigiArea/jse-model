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

import com.digiarea.jse.Expression;
import com.digiarea.jse.visitor.VoidVisitor;
import com.digiarea.jse.visitor.GenericVisitor;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.NodeList;

public final class AssignExpr extends Expression {

    public static enum AssignOperator {

        /** The assign.
         */
        assign("="), 
        /** The plus.
         */
        plus("+="), 
        /** The minus.
         */
        minus("-="), 
        /** The star.
         */
        star("*="), 
        /** The slash.
         */
        slash("/="), 
        /** The and.
         */
        and("&="), 
        /** The or.
         */
        or("|="), 
        /** The xor.
         */
        xor("^="), 
        /** The rem.
         */
        rem("%="), 
        /** The l shift.
         */
        lShift("<<="), 
        /** The r signed shift.
         */
        rSignedShift(">>="), 
        /** The r unsigned shift.
         */
        rUnsignedShift(">>>=");

        private String string;

        private AssignOperator(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

        public String getString() {
            return string;
        }

        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    private Expression target;

    private Expression value;

    private AssignExpr.AssignOperator operator;

    public Expression getTarget() {
        return target;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public AssignExpr.AssignOperator getOperator() {
        return operator;
    }

    public void setOperator(AssignExpr.AssignOperator operator) {
        this.operator = operator;
    }

    AssignExpr() {
        super();
    }

    AssignExpr(Expression target, Expression value, AssignExpr.AssignOperator operator, NodeList<AnnotationExpr> annotations, int posBegin, int posEnd) {
        super(annotations, posBegin, posEnd);
        this.target = target;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
