package com.digiarea.jse.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.digiarea.common.utils.StringUtils;
import com.digiarea.jse.AnnotationDeclaration;
import com.digiarea.jse.AnnotationExpr;
import com.digiarea.jse.BlockStmt;
import com.digiarea.jse.BodyDeclaration;
import com.digiarea.jse.ClassDeclaration;
import com.digiarea.jse.ClassOrInterfaceType;
import com.digiarea.jse.CompilationUnit;
import com.digiarea.jse.ConstructorDeclaration;
import com.digiarea.jse.EnumConstantDeclaration;
import com.digiarea.jse.EnumDeclaration;
import com.digiarea.jse.Expression;
import com.digiarea.jse.FieldDeclaration;
import com.digiarea.jse.InterfaceDeclaration;
import com.digiarea.jse.MemberValuePair;
import com.digiarea.jse.MethodDeclaration;
import com.digiarea.jse.Modifiers;
import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.PackageDeclaration;
import com.digiarea.jse.Parameter;
import com.digiarea.jse.PrimitiveType;
import com.digiarea.jse.PrimitiveType.Primitive;
import com.digiarea.jse.ReferenceType;
import com.digiarea.jse.Statement;
import com.digiarea.jse.Type;
import com.digiarea.jse.TypeDeclaration;
import com.digiarea.jse.TypeParameter;

public final class ReflectUtils {

	public static CompilationUnit makeCompilationUnit(String qName)
			throws ClassNotFoundException {
		Class<?> clazz = Class.forName(qName);
		Package pack = clazz.getPackage();
		PackageDeclaration pkg = NodeFacade.PackageDeclaration(
				NodeFacade.NameExpr(pack.getName()),
				makeAnnotations(pack.getAnnotations()));
		return NodeFacade.CompilationUnit(pkg, null,
				Arrays.asList(makeTypeDeclaration(clazz)), null,
				clazz.getCanonicalName());
	}

	public static List<TypeDeclaration> makeTypeDeclarations(Class<?> clazz) {
		Class<?>[] clazzes = clazz.getDeclaredClasses();
		List<TypeDeclaration> types = new ArrayList<TypeDeclaration>();
		for (Class<?> clz : clazzes) {
			types.add(makeTypeDeclaration(clz));
		}
		return types;
	}

	public static TypeDeclaration makeTypeDeclaration(Class<?> clazz) {
		if (clazz.isAnnotation()) {
			return makeAnnotationDeclaration(clazz);
		} else if (clazz.isInterface()) {
			return makeInterfaceDeclaration(clazz);
		} else if (clazz.isEnum()) {
			return makeEnumDeclaration(clazz);
		} else {
			return makeClassDeclaration(clazz);
		}
	}

	public static ClassDeclaration makeClassDeclaration(Class<?> clazz) {
		return NodeFacade.ClassDeclaration(
				makeTypeParameters(clazz.getTypeParameters()),
				makeExtendsType(clazz), makeImplements(clazz),
				clazz.getModifiers(), clazz.getSimpleName(),
				makeMembers(clazz), null,
				makeAnnotations(clazz.getAnnotations()));

	}

	public static InterfaceDeclaration makeInterfaceDeclaration(Class<?> clazz) {
		return NodeFacade.InterfaceDeclaration(
				makeTypeParameters(clazz.getTypeParameters()),
				makeExtendsList(clazz), clazz.getModifiers(),
				clazz.getSimpleName(), makeMembers(clazz), null,
				makeAnnotations(clazz.getAnnotations()));
	}

	public static EnumDeclaration makeEnumDeclaration(Class<?> clazz) {
		// TODO try to fix enumerations members
		int modifiers = Modifiers.removeModifier(clazz.getModifiers(),
				Modifiers.FINAL);
		return NodeFacade.EnumDeclaration(makeImplements(clazz),
				makeEntries(clazz), modifiers, clazz.getSimpleName(), null,
				null, makeAnnotations(clazz.getAnnotations()));
	}

	public static AnnotationDeclaration makeAnnotationDeclaration(Class<?> clazz) {
		return NodeFacade.AnnotationDeclaration(clazz.getModifiers(),
				clazz.getSimpleName(), makeMembers(clazz), null,
				makeAnnotations(clazz.getAnnotations()));
	}

	public static List<BodyDeclaration> makeMembers(Class<?> clazz) {
		List<BodyDeclaration> members = new ArrayList<BodyDeclaration>();
		// make type declarations
		members.addAll(makeTypeDeclarations(clazz));
		// make fields
		members.addAll(makeFields(clazz));
		// make constructors
		members.addAll(makeConstructors(clazz));
		// make methods
		members.addAll(makeMethods(clazz));
		return members;
	}

	public static List<FieldDeclaration> makeFields(Class<?> clazz) {
		List<FieldDeclaration> result = new ArrayList<FieldDeclaration>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isEnumConstant()) {
				result.add(NodeFacade.FieldDeclaration(field.getModifiers(),
						makeType(field.getGenericType()), Arrays
								.asList(NodeFacade.VariableDeclarator(
										NodeFacade.VariableDeclaratorId(field
												.getName()), null)), null,
						makeAnnotations(field.getDeclaredAnnotations())));
			}
		}
		return result;
	}

	public static List<ConstructorDeclaration> makeConstructors(Class<?> clazz) {
		List<ConstructorDeclaration> result = new ArrayList<ConstructorDeclaration>();
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			result.add(NodeFacade.ConstructorDeclaration(
					constructor.getModifiers(),
					makeTypeParameters(constructor.getTypeParameters()),
					clazz.getSimpleName(),
					makeParameters(constructor.getGenericParameterTypes(),
							constructor.getParameterAnnotations(),
							constructor.isVarArgs()),
					makeThrowsList(constructor.getExceptionTypes()), NodeFacade
							.BlockStmt(), null, makeAnnotations(constructor
							.getDeclaredAnnotations())));
		}
		return result;
	}

	public static List<MethodDeclaration> makeMethods(Class<?> clazz) {
		List<MethodDeclaration> result = new ArrayList<MethodDeclaration>();
		Method[] methids = clazz.getDeclaredMethods();
		for (Method method : methids) {
			if (!method.isSynthetic()) {
				Type type = makeType(method.getGenericReturnType());
				BlockStmt stmt = null;
				if (!Modifiers.isAbstract(method.getModifiers())) {
					Expression expr = makeReturn(type);
					if (expr != null) {
						List<Statement> stmts = new ArrayList<Statement>();
						stmts.add(NodeFacade.ReturnStmt(expr));
						stmt = NodeFacade.BlockStmt(stmts);
					} else {
						stmt = NodeFacade.BlockStmt();
					}
				}
				result.add(NodeFacade.MethodDeclaration(
						method.getModifiers(),
						makeTypeParameters(method.getTypeParameters()),
						type,
						method.getName(),
						makeParameters(method.getGenericParameterTypes(),
								method.getParameterAnnotations(),
								method.isVarArgs()), null,
						makeThrowsList(method.getExceptionTypes()), stmt, null,
						makeAnnotations(method.getDeclaredAnnotations())));
			}

		}
		return result;
	}

	public static List<ClassOrInterfaceType> makeThrowsList(
			Class<?>[] exceptionTypes) {
		List<ClassOrInterfaceType> result = new ArrayList<>();
		for (Class<?> clazz : exceptionTypes) {
			result.add(NodeFacade.ClassOrInterfaceType(clazz.getName()));
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static List<Parameter> makeParameters(
			java.lang.reflect.Type[] parameterTypes,
			Annotation[][] annotations, boolean isVarArgs) {
		List<Parameter> result = new ArrayList<Parameter>();
		List<String> taken = new ArrayList<String>();
		boolean varArgs = false;
		for (int i = 0; i < parameterTypes.length; i++) {
			if (i == parameterTypes.length - 1) {
				varArgs = isVarArgs;
			}
			Type type = makeType(parameterTypes[i]);
			result.add(NodeFacade.Parameter(0, type,
					varArgs ? NodeFacade.Ellipsis() : null, NodeFacade
							.VariableDeclaratorId(makeUniqueName(type, taken)),
					makeAnnotations(annotations[i])));
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static List<ClassOrInterfaceType> makeExtendsList(Class<?> clazz) {
		return makeImplements(clazz);
	}

	public static ClassOrInterfaceType makeExtendsType(Class<?> clazz) {
		java.lang.reflect.Type type = clazz.getGenericSuperclass();
		if (!type.equals(Object.class)) {
			return (ClassOrInterfaceType) makeType(type);
		} else {
			return null;
		}
	}

	public static List<TypeParameter> makeTypeParameters(
			TypeVariable<?>[] typeVariables) {
		List<TypeParameter> result = new ArrayList<TypeParameter>();
		for (TypeVariable<?> typeVariable : typeVariables) {
			result.add(NodeFacade.TypeParameter(typeVariable.getName(),
					makeTypes(typeVariable.getBounds()), null));
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static List<ClassOrInterfaceType> makeTypes(
			java.lang.reflect.Type[] bounds) {
		List<ClassOrInterfaceType> result = new ArrayList<ClassOrInterfaceType>();
		for (java.lang.reflect.Type type : bounds) {
			if (!(type instanceof Class)) {
				result.add(NodeFacade.ClassOrInterfaceType(type.getClass()
						.getName()));
			}
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static List<EnumConstantDeclaration> makeEntries(Class<?> clazz) {
		List<EnumConstantDeclaration> result = new ArrayList<EnumConstantDeclaration>();
		Object[] enumConstants = clazz.getEnumConstants();
		for (Object constant : enumConstants) {
			Enum<?> enumConstant = (Enum<?>) constant;
			// TODO try to fix full entries
			result.add(NodeFacade.EnumConstantDeclaration(enumConstant.name()));
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static List<ClassOrInterfaceType> makeImplements(Class<?> clazz) {
		List<ClassOrInterfaceType> result = new ArrayList<ClassOrInterfaceType>();
		java.lang.reflect.Type[] types = clazz.getGenericInterfaces();
		for (java.lang.reflect.Type type : types) {
			result.add((ClassOrInterfaceType) makeType(type));
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static List<AnnotationExpr> makeAnnotations(Annotation[] annotations) {
		List<AnnotationExpr> result = new ArrayList<AnnotationExpr>();
		for (Annotation annotation : annotations) {
			Class<?> clazz = annotation.annotationType();
			NameExpr name = NodeFacade.NameExpr(clazz.getName());
			Method[] methods = clazz.getDeclaredMethods();
			if (methods.length == 0) {
				result.add(NodeFacade.MarkerAnnotationExpr(name));
			} else if (methods.length == 1
					&& methods[0].getName().equals("value")) {
				// TODO value
				Expression value = null;
				result.add(NodeFacade.SingleMemberAnnotationExpr(value, name));
			} else {
				List<MemberValuePair> pairs = new ArrayList<MemberValuePair>();
				for (Method method : methods) {
					// TODO value
					Expression value = null;
					pairs.add(NodeFacade.MemberValuePair(method.getName(),
							value));
				}
				result.add(NodeFacade.NormalAnnotationExpr(pairs, name));
			}
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

	public static Type makeType(java.lang.reflect.Type genericType) {
		List<Type> typeArgs = new ArrayList<Type>();
		String name = "UNKNOWN";
		if (genericType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericType;
			java.lang.reflect.Type[] typeArguments = type
					.getActualTypeArguments();
			for (java.lang.reflect.Type typeArgument : typeArguments) {
				if (typeArgument instanceof Class) {
					Class<?> typeArgClass = (Class<?>) typeArgument;
					typeArgs.add(NodeFacade.ClassOrInterfaceType(typeArgClass
							.getName()));
				} else if (typeArgument instanceof TypeVariable) {
					TypeVariable<?> var = (TypeVariable<?>) typeArgument;
					typeArgs.add(NodeFacade.ClassOrInterfaceType(var.getName()));
				}
			}
			name = ((Class<?>) type.getRawType()).getName();
			ClassOrInterfaceType clazz = NodeFacade.ClassOrInterfaceType(name);
			if (!typeArgs.isEmpty()) {
				clazz.setTypeArgs(NodeFacade.NodeList(typeArgs));
			}
			return clazz;
		} else if (genericType instanceof TypeVariable) {
			TypeVariable<?> type = (TypeVariable<?>) genericType;
			name = type.getName();
			ClassOrInterfaceType clazz = NodeFacade.ClassOrInterfaceType(name);
			return clazz;
		} else if (genericType instanceof GenericArrayType) {
			// TODO GenericArrayType
			name = "Generic-Array-Type";
		} else if (genericType instanceof WildcardType) {
			// TODO WildcardType
			name = "Wildcard-Type";
		} else if (genericType instanceof Class) {
			Class<?> clz = (Class<?>) genericType;
			name = clz.getCanonicalName();
			if (clz.isPrimitive()) {
				name = StringUtils.firstToUpper(name);
				if ("Void".equals(name)) {
					return NodeFacade.VoidType();
				} else {
					return NodeFacade.PrimitiveType(Primitive.valueOf(name));
				}
			} else if (clz.isArray()) {
				Class<?> component = clz.getComponentType();
				int arrayCount = 1;
				while (component.isArray()) {
					arrayCount += 1;
					component = component.getComponentType();
				}
				name = component.getCanonicalName();
				return NodeFacade.ReferenceType(
						NodeFacade.ClassOrInterfaceType(name), arrayCount);
			} else {
				return NodeFacade.ClassOrInterfaceType(name);
			}
		}
		return null;
	}

	private static String makeUniqueName(Type type, List<String> taken) {
		String name = makeName(type);
		int i = 1;
		while (taken.contains(name)) {
			name += i;
		}
		taken.add(name);
		return name;
	}

	private static String makeName(Type type) {
		Type inner = type;
		boolean isUnique = true;
		if (NodeUtils.isList(type)) {
			isUnique = false;
			inner = NodeUtils.getTypeFromList(inner);
		}
		if (inner instanceof ReferenceType) {
			ReferenceType rType = (ReferenceType) type;
			if (isUnique) {
				isUnique = rType.getSlots() == null
						&& rType.getSlots().size() == 0;
			}
			inner = rType.getType();
		}
		if (inner instanceof ClassOrInterfaceType) {
			String name = ((ClassOrInterfaceType) inner).getName().getName();
			name = StringUtils.firstToLower(name);
			return isUnique ? name : name + "s";
		}
		if (inner instanceof PrimitiveType) {
			String name = ((PrimitiveType) inner).getType().toString()
					+ "Value";
			return isUnique ? name : name + "s";
		}
		return "NAME";
	}

	private static Expression makeReturn(Type type) {
		if (type instanceof ReferenceType
				|| type instanceof ClassOrInterfaceType) {
			return NodeFacade.NullLiteralExpr();
		} else if (type instanceof PrimitiveType) {
			Primitive primitive = ((PrimitiveType) type).getType();
			if (primitive == Primitive.Boolean) {
				return NodeFacade.BooleanLiteralExpr(false);
			} else {
				return NodeFacade.IntegerLiteralExpr(0);
			}
		}
		return null;
	}

}
