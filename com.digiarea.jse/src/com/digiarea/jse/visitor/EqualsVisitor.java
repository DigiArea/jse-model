package com.digiarea.jse.visitor;

import com.digiarea.jse.Node;
import com.digiarea.jse.visitor.GenericVisitor;
import java.util.List;
import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.AnnotationMemberDeclaration;
import com.digiarea.jse.ArrayAccessExpr;
import com.digiarea.jse.ArrayCreationExpr;
import com.digiarea.jse.ArrayInitializerExpr;
import com.digiarea.jse.ArraySlot;
import com.digiarea.jse.AssertStmt;
import com.digiarea.jse.AssignExpr;
import com.digiarea.jse.AssignExpr.AssignOperator;
import com.digiarea.jse.BinaryExpr;
import com.digiarea.jse.BinaryExpr.BinaryOperator;
import com.digiarea.jse.BlockComment;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.BooleanLiteralExpr;
import com.digiarea.jse.BreakStmt;
import com.digiarea.jse.CastExpr;
import com.digiarea.jse.CatchClause;
import com.digiarea.jse.CharLiteralExpr;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassExpr;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ConditionalExpr;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.ContinueStmt;
import com.digiarea.jse.DoStmt;
import com.digiarea.jse.DoubleLiteralExpr;
import com.digiarea.jse.Ellipsis;
import com.digiarea.jse.EmptyMemberDeclaration;
import com.digiarea.jse.EmptyStmt;
import com.digiarea.jse.EmptyTypeDeclaration;
import com.digiarea.jse.EnclosedExpr;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.ExplicitConstructorInvocationStmt;
import com.digiarea.jse.ExpressionStmt;
import com.digiarea.jse.FieldAccessExpr;
import com.digiarea.jse.FieldDeclaration;
import com.digiarea.jse.ForeachStmt;
import com.digiarea.jse.ForStmt;
import com.digiarea.jse.IfStmt;
import com.digiarea.jse.ImportDeclaration;
import com.digiarea.jse.InitializerDeclaration;
import com.digiarea.jse.InstanceOfExpr;
import com.digiarea.jse.IntegerLiteralExpr;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.JavadocComment;
import com.digiarea.jse.LabeledStmt;
import com.digiarea.jse.LambdaBlock;
import com.digiarea.jse.LambdaExpr;
import com.digiarea.jse.LineComment;
import com.digiarea.jse.LongLiteralExpr;
import com.digiarea.jse.MarkerAnnotationExpr;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.MethodCallExpr;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.MethodExprRef;
import com.digiarea.jse.MethodRef;
import com.digiarea.jse.MethodTypeRef;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeList;
import com.digiarea.jse.NormalAnnotationExpr;
import com.digiarea.jse.NullLiteralExpr;
import com.digiarea.jse.ObjectCreationExpr;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.PrimitiveType.Primitive;
import com.digiarea.jse.Project;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.ReturnStmt;
import com.digiarea.jse.SingleMemberAnnotationExpr;
import com.digiarea.jse.StringLiteralExpr;
import com.digiarea.jse.SuperExpr;
import com.digiarea.jse.SwitchEntryStmt;
import com.digiarea.jse.SwitchStmt;
import com.digiarea.jse.SynchronizedStmt;
import com.digiarea.jse.ThisExpr;
import com.digiarea.jse.ThrowStmt;
import com.digiarea.jse.TryStmt;
import com.digiarea.jse.TypeDeclarationStmt;
import com.digiarea.jse.TypeParameter;
import com.digiarea.jse.UnaryExpr;
import com.digiarea.jse.UnaryExpr.UnaryOperator;
import com.digiarea.jse.VariableDeclarationExpr;
import com.digiarea.jse.VariableDeclarator;
import com.digiarea.jse.VariableDeclaratorId;
import com.digiarea.jse.VoidType;
import com.digiarea.jse.WhileStmt;
import com.digiarea.jse.WildcardType;

@SuppressWarnings("unchecked")
public class EqualsVisitor implements GenericVisitor<Boolean, Node> {

    private static final EqualsVisitor SINGLETON = new EqualsVisitor();

    public static boolean equals(Node n1, Node n2) throws Exception {
        return SINGLETON.nodeEquals(n1, n2);
    }

    protected <T extends Node> boolean nodeEquals(T n1, T n2) throws Exception {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        if (n1.getClass() != n2.getClass()) {
            return false;
        }
        return n1.accept(this, n2).booleanValue();
    }

    protected <T extends Node> boolean nodesEquals(List<T> nodes1, List<T> nodes2) throws Exception {
        if (nodes1 == null) {
            if (nodes2 == null) {
                return true;
            }
            return false;
        } else if (nodes2 == null) {
            return false;
        }
        if (nodes1.size() != nodes2.size()) {
            return false;
        }
        for (int i = 0; i < nodes1.size(); i++) {
            if (!nodeEquals(nodes1.get(i), nodes2.get(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean objEquals(Object n1, Object n2) {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        return n1.equals(n2);
    }

    @Override
    public Boolean visit(AnnotationDeclaration n, Node ctx) throws Exception {
        AnnotationDeclaration x = (AnnotationDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getMembers(), x.getMembers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(AnnotationMemberDeclaration n, Node ctx) throws Exception {
        AnnotationMemberDeclaration x = (AnnotationMemberDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getDefaultValue(), x.getDefaultValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ArrayAccessExpr n, Node ctx) throws Exception {
        ArrayAccessExpr x = (ArrayAccessExpr) ctx;
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getIndex(), x.getIndex())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ArrayCreationExpr n, Node ctx) throws Exception {
        ArrayCreationExpr x = (ArrayCreationExpr) ctx;
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getSlots(), x.getSlots())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getInitializer(), x.getInitializer())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ArrayInitializerExpr n, Node ctx) throws Exception {
        ArrayInitializerExpr x = (ArrayInitializerExpr) ctx;
        if (!nodeEquals(n.getValues(), x.getValues())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ArraySlot n, Node ctx) throws Exception {
        ArraySlot x = (ArraySlot) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(AssertStmt n, Node ctx) throws Exception {
        AssertStmt x = (AssertStmt) ctx;
        if (!nodeEquals(n.getCheck(), x.getCheck())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getMessage(), x.getMessage())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(AssignExpr n, Node ctx) throws Exception {
        AssignExpr x = (AssignExpr) ctx;
        if (!nodeEquals(n.getTarget(), x.getTarget())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOperator() != x.getOperator()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(AssignOperator n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(BinaryExpr n, Node ctx) throws Exception {
        BinaryExpr x = (BinaryExpr) ctx;
        if (!nodeEquals(n.getLeft(), x.getLeft())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getRight(), x.getRight())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOperator() != x.getOperator()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(BinaryOperator n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(BlockComment n, Node ctx) throws Exception {
        BlockComment x = (BlockComment) ctx;
        if (!objEquals(n.getContent(), x.getContent())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(BlockStmt n, Node ctx) throws Exception {
        BlockStmt x = (BlockStmt) ctx;
        if (!nodeEquals(n.getStatements(), x.getStatements())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(BooleanLiteralExpr n, Node ctx) throws Exception {
        BooleanLiteralExpr x = (BooleanLiteralExpr) ctx;
        if (n.isValue() != x.isValue()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(BreakStmt n, Node ctx) throws Exception {
        BreakStmt x = (BreakStmt) ctx;
        if (!objEquals(n.getId(), x.getId())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(CastExpr n, Node ctx) throws Exception {
        CastExpr x = (CastExpr) ctx;
        if (!nodeEquals(n.getTypes(), x.getTypes())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(CatchClause n, Node ctx) throws Exception {
        CatchClause x = (CatchClause) ctx;
        if (n.isFinal() != x.isFinal()) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypes(), x.getTypes())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getCatchBlock(), x.getCatchBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(CharLiteralExpr n, Node ctx) throws Exception {
        CharLiteralExpr x = (CharLiteralExpr) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClassDeclaration n, Node ctx) throws Exception {
        ClassDeclaration x = (ClassDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeParameters(), x.getTypeParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getExtendsType(), x.getExtendsType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getImplementsList(), x.getImplementsList())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getMembers(), x.getMembers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClassExpr n, Node ctx) throws Exception {
        ClassExpr x = (ClassExpr) ctx;
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClassOrInterfaceType n, Node ctx) throws Exception {
        ClassOrInterfaceType x = (ClassOrInterfaceType) ctx;
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(CompilationUnit n, Node ctx) throws Exception {
        CompilationUnit x = (CompilationUnit) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getPackageDeclaration(), x.getPackageDeclaration())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getImports(), x.getImports())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypes(), x.getTypes())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ConditionalExpr n, Node ctx) throws Exception {
        ConditionalExpr x = (ConditionalExpr) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getThenExpression(), x.getThenExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getElseExpression(), x.getElseExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ConstructorDeclaration n, Node ctx) throws Exception {
        ConstructorDeclaration x = (ConstructorDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeParameters(), x.getTypeParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getParameters(), x.getParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getThrowsList(), x.getThrowsList())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ContinueStmt n, Node ctx) throws Exception {
        ContinueStmt x = (ContinueStmt) ctx;
        if (!objEquals(n.getId(), x.getId())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(DoStmt n, Node ctx) throws Exception {
        DoStmt x = (DoStmt) ctx;
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(DoubleLiteralExpr n, Node ctx) throws Exception {
        DoubleLiteralExpr x = (DoubleLiteralExpr) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Ellipsis n, Node ctx) throws Exception {
        Ellipsis x = (Ellipsis) ctx;
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(EmptyMemberDeclaration n, Node ctx) throws Exception {
        EmptyMemberDeclaration x = (EmptyMemberDeclaration) ctx;
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(EmptyStmt n, Node ctx) throws Exception {
        EmptyStmt x = (EmptyStmt) ctx;
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(EmptyTypeDeclaration n, Node ctx) throws Exception {
        EmptyTypeDeclaration x = (EmptyTypeDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getMembers(), x.getMembers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(EnclosedExpr n, Node ctx) throws Exception {
        EnclosedExpr x = (EnclosedExpr) ctx;
        if (!nodeEquals(n.getInner(), x.getInner())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(EnumConstantDeclaration n, Node ctx) throws Exception {
        EnumConstantDeclaration x = (EnumConstantDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getArgs(), x.getArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getClassBody(), x.getClassBody())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(EnumDeclaration n, Node ctx) throws Exception {
        EnumDeclaration x = (EnumDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getImplementsList(), x.getImplementsList())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getEntries(), x.getEntries())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getMembers(), x.getMembers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ExplicitConstructorInvocationStmt n, Node ctx) throws Exception {
        ExplicitConstructorInvocationStmt x = (ExplicitConstructorInvocationStmt) ctx;
        if (n.isThis() != x.isThis()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getArgs(), x.getArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ExpressionStmt n, Node ctx) throws Exception {
        ExpressionStmt x = (ExpressionStmt) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(FieldAccessExpr n, Node ctx) throws Exception {
        FieldAccessExpr x = (FieldAccessExpr) ctx;
        if (!objEquals(n.getField(), x.getField())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(FieldDeclaration n, Node ctx) throws Exception {
        FieldDeclaration x = (FieldDeclaration) ctx;
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getVariables(), x.getVariables())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ForeachStmt n, Node ctx) throws Exception {
        ForeachStmt x = (ForeachStmt) ctx;
        if (!nodeEquals(n.getVariable(), x.getVariable())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getIterable(), x.getIterable())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ForStmt n, Node ctx) throws Exception {
        ForStmt x = (ForStmt) ctx;
        if (!nodeEquals(n.getInit(), x.getInit())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getCompare(), x.getCompare())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getUpdate(), x.getUpdate())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(IfStmt n, Node ctx) throws Exception {
        IfStmt x = (IfStmt) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getThenStmt(), x.getThenStmt())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getElseStmt(), x.getElseStmt())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ImportDeclaration n, Node ctx) throws Exception {
        ImportDeclaration x = (ImportDeclaration) ctx;
        if (n.isStatic() != x.isStatic()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.isAsterisk() != x.isAsterisk()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(InitializerDeclaration n, Node ctx) throws Exception {
        InitializerDeclaration x = (InitializerDeclaration) ctx;
        if (n.isStatic() != x.isStatic()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(InstanceOfExpr n, Node ctx) throws Exception {
        InstanceOfExpr x = (InstanceOfExpr) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(IntegerLiteralExpr n, Node ctx) throws Exception {
        IntegerLiteralExpr x = (IntegerLiteralExpr) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(InterfaceDeclaration n, Node ctx) throws Exception {
        InterfaceDeclaration x = (InterfaceDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeParameters(), x.getTypeParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getExtendsList(), x.getExtendsList())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getMembers(), x.getMembers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JavadocComment n, Node ctx) throws Exception {
        JavadocComment x = (JavadocComment) ctx;
        if (!objEquals(n.getContent(), x.getContent())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(LabeledStmt n, Node ctx) throws Exception {
        LabeledStmt x = (LabeledStmt) ctx;
        if (!objEquals(n.getLabel(), x.getLabel())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getStmt(), x.getStmt())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(LambdaBlock n, Node ctx) throws Exception {
        LambdaBlock x = (LambdaBlock) ctx;
        if (!nodeEquals(n.getBlockStmt(), x.getBlockStmt())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getParameters(), x.getParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(LambdaExpr n, Node ctx) throws Exception {
        LambdaExpr x = (LambdaExpr) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getParameters(), x.getParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(LineComment n, Node ctx) throws Exception {
        LineComment x = (LineComment) ctx;
        if (!objEquals(n.getContent(), x.getContent())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(LongLiteralExpr n, Node ctx) throws Exception {
        LongLiteralExpr x = (LongLiteralExpr) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MarkerAnnotationExpr n, Node ctx) throws Exception {
        MarkerAnnotationExpr x = (MarkerAnnotationExpr) ctx;
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MemberValuePair n, Node ctx) throws Exception {
        MemberValuePair x = (MemberValuePair) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MethodCallExpr n, Node ctx) throws Exception {
        MethodCallExpr x = (MethodCallExpr) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getArgs(), x.getArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MethodDeclaration n, Node ctx) throws Exception {
        MethodDeclaration x = (MethodDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeParameters(), x.getTypeParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getParameters(), x.getParameters())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getSlots(), x.getSlots())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getThrowsList(), x.getThrowsList())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MethodExprRef n, Node ctx) throws Exception {
        MethodExprRef x = (MethodExprRef) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MethodRef n, Node ctx) throws Exception {
        MethodRef x = (MethodRef) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(MethodTypeRef n, Node ctx) throws Exception {
        MethodTypeRef x = (MethodTypeRef) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Modifiers n, Node ctx) throws Exception {
        Modifiers x = (Modifiers) ctx;
        if (n.getModifiers() != x.getModifiers()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(NameExpr n, Node ctx) throws Exception {
        NameExpr x = (NameExpr) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public <E extends Node> Boolean visit(NodeList<E> n, Node ctx) throws Exception {
        NodeList<E> x = (NodeList<E>) ctx;
        if (!nodesEquals(n.getNodes(), x.getNodes())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(NormalAnnotationExpr n, Node ctx) throws Exception {
        NormalAnnotationExpr x = (NormalAnnotationExpr) ctx;
        if (!nodeEquals(n.getPairs(), x.getPairs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(NullLiteralExpr n, Node ctx) throws Exception {
        NullLiteralExpr x = (NullLiteralExpr) ctx;
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ObjectCreationExpr n, Node ctx) throws Exception {
        ObjectCreationExpr x = (ObjectCreationExpr) ctx;
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeArgs(), x.getTypeArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getArgs(), x.getArgs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnonymousClassBody(), x.getAnonymousClassBody())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(PackageDeclaration n, Node ctx) throws Exception {
        PackageDeclaration x = (PackageDeclaration) ctx;
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Parameter n, Node ctx) throws Exception {
        Parameter x = (Parameter) ctx;
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getEllipsis(), x.getEllipsis())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getId(), x.getId())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(PrimitiveType n, Node ctx) throws Exception {
        PrimitiveType x = (PrimitiveType) ctx;
        if (n.getType() != x.getType()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Primitive n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Project n, Node ctx) throws Exception {
        Project x = (Project) ctx;
        if (!nodeEquals(n.getCompilationUnits(), x.getCompilationUnits())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(QualifiedNameExpr n, Node ctx) throws Exception {
        QualifiedNameExpr x = (QualifiedNameExpr) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getQualifier(), x.getQualifier())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ReferenceType n, Node ctx) throws Exception {
        ReferenceType x = (ReferenceType) ctx;
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getSlots(), x.getSlots())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ReturnStmt n, Node ctx) throws Exception {
        ReturnStmt x = (ReturnStmt) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SingleMemberAnnotationExpr n, Node ctx) throws Exception {
        SingleMemberAnnotationExpr x = (SingleMemberAnnotationExpr) ctx;
        if (!nodeEquals(n.getMemberValue(), x.getMemberValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(StringLiteralExpr n, Node ctx) throws Exception {
        StringLiteralExpr x = (StringLiteralExpr) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SuperExpr n, Node ctx) throws Exception {
        SuperExpr x = (SuperExpr) ctx;
        if (!nodeEquals(n.getClassExpression(), x.getClassExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SwitchEntryStmt n, Node ctx) throws Exception {
        SwitchEntryStmt x = (SwitchEntryStmt) ctx;
        if (!nodeEquals(n.getLabel(), x.getLabel())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getStmts(), x.getStmts())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SwitchStmt n, Node ctx) throws Exception {
        SwitchStmt x = (SwitchStmt) ctx;
        if (!nodeEquals(n.getSelector(), x.getSelector())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getEntries(), x.getEntries())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SynchronizedStmt n, Node ctx) throws Exception {
        SynchronizedStmt x = (SynchronizedStmt) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ThisExpr n, Node ctx) throws Exception {
        ThisExpr x = (ThisExpr) ctx;
        if (!nodeEquals(n.getClassExpression(), x.getClassExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ThrowStmt n, Node ctx) throws Exception {
        ThrowStmt x = (ThrowStmt) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(TryStmt n, Node ctx) throws Exception {
        TryStmt x = (TryStmt) ctx;
        if (!nodeEquals(n.getResources(), x.getResources())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTryBlock(), x.getTryBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getCatchClauses(), x.getCatchClauses())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getFinallyBlock(), x.getFinallyBlock())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(TypeDeclarationStmt n, Node ctx) throws Exception {
        TypeDeclarationStmt x = (TypeDeclarationStmt) ctx;
        if (!nodeEquals(n.getTypeDeclaration(), x.getTypeDeclaration())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(TypeParameter n, Node ctx) throws Exception {
        TypeParameter x = (TypeParameter) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getTypeBound(), x.getTypeBound())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(UnaryExpr n, Node ctx) throws Exception {
        UnaryExpr x = (UnaryExpr) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOperator() != x.getOperator()) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(UnaryOperator n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(VariableDeclarationExpr n, Node ctx) throws Exception {
        VariableDeclarationExpr x = (VariableDeclarationExpr) ctx;
        if (!nodeEquals(n.getModifiers(), x.getModifiers())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getType(), x.getType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getVars(), x.getVars())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(VariableDeclarator n, Node ctx) throws Exception {
        VariableDeclarator x = (VariableDeclarator) ctx;
        if (!nodeEquals(n.getId(), x.getId())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getInit(), x.getInit())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(VariableDeclaratorId n, Node ctx) throws Exception {
        VariableDeclaratorId x = (VariableDeclaratorId) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getSlots(), x.getSlots())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(VoidType n, Node ctx) throws Exception {
        VoidType x = (VoidType) ctx;
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(WhileStmt n, Node ctx) throws Exception {
        WhileStmt x = (WhileStmt) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(WildcardType n, Node ctx) throws Exception {
        WildcardType x = (WildcardType) ctx;
        if (!nodeEquals(n.getExtendsType(), x.getExtendsType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getSuperType(), x.getSuperType())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getAnnotations(), x.getAnnotations())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    public EqualsVisitor() {
        super();
    }

}
