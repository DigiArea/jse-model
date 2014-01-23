/**
 * Copyright (c) 2009-2011 DigiArea, Inc. All rights reserved.
 * 
 * @author norb <norb.beaver@digi-area.com>
 */
package com.digiarea.jse.utils;

import com.digiarea.jse.NameExpr;
import com.digiarea.jse.NodeFacade;
import com.digiarea.jse.QualifiedNameExpr;
import com.digiarea.jse.Type;

/**
 * The Class LangUtils.
 */
public class LangUtils {

	/** The Constant BOOLEAN_TYPE. */
	public static final Type BOOLEAN_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Boolean");

	public static final Type BOOLEAN_PROPERTY_TYPE = NodeFacade
			.ClassOrInterfaceType("javafx.beans.property.BooleanProperty");

	/** The Constant CHARACTER_TYPE. */
	public static final Type CHARACTER_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Character");

	/** The Constant BYTE_TYPE. */
	public static final Type BYTE_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Byte");

	/** The Constant SHORT_TYPE. */
	public static final Type SHORT_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Short");

	/** The Constant INTEGER_TYPE. */
	public static final Type INTEGER_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Integer");

	/** The Constant LONG_TYPE. */
	public static final Type LONG_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Long");

	/** The Constant FLOAT_TYPE. */
	public static final Type FLOAT_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Float");

	/** The Constant DOUBLE_TYPE. */
	public static final Type DOUBLE_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Double");

	/** The Constant STRING_TYPE. */
	public static final Type STRING_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.String");

	/** The Constant STRING_TYPE. */
	public static final Type VOID_TYPE = NodeFacade
			.ClassOrInterfaceType("java.lang.Void");

	/**
	 * Checks if is qualified java lang.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is qualified java lang
	 */
	public static boolean isSimpleJavaLang(String name) {
		for (int i = 0; i < JAVA_LANG.length; i++) {
			if (JAVA_LANG[i].equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is qualified java lang.
	 * 
	 * @param expr
	 *            the expr
	 * @return true, if is qualified java lang
	 */
	public static boolean isQualifiedJavaLang(NameExpr expr) {
		if (expr instanceof QualifiedNameExpr) {
			QualifiedNameExpr name = (QualifiedNameExpr) expr;
			for (int i = 0; i < JAVA_LANG.length; i++) {
				if (JAVA_LANG[i].equals(name.getName())
						&& name.getQualifier().toString().equals("java.lang")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if is simple java lang io.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is simple java lang io
	 */
	public static boolean isSimpleJavaLangIo(String name) {
		for (int i = 0; i < JAVA_LANG_IO.length; i++) {
			if (JAVA_LANG_IO[i].equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is qualified java lang io.
	 * 
	 * @param expr
	 *            the expr
	 * @return true, if is qualified java lang io
	 */
	public static boolean isQualifiedJavaLangIo(NameExpr expr) {
		if (expr instanceof QualifiedNameExpr) {
			QualifiedNameExpr name = (QualifiedNameExpr) expr;
			for (int i = 0; i < JAVA_LANG_IO.length; i++) {
				if (JAVA_LANG_IO[i].equals(name.getName())
						&& name.getQualifier().toString()
								.equals("java.lang.io")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if is simple wrapped type.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is simple wrapped type
	 */
	public static boolean isSimpleWrappedType(String name) {
		for (int i = 0; i < WRAPPED_TYPES.length; i++) {
			if (name.equals(NodeFacade.NameExpr(WRAPPED_TYPES[i]).getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is qualified wrapped type.
	 * 
	 * @param expr
	 *            the expr
	 * @return true, if is qualified wrapped type
	 */
	public static boolean isQualifiedWrappedType(NameExpr expr) {
		for (int i = 0; i < WRAPPED_TYPES.length; i++) {
			if (WRAPPED_TYPES[i].equals(expr.toString())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isQualifiedWrappedType(String name) {
		for (int i = 0; i < WRAPPED_TYPES.length; i++) {
			if (WRAPPED_TYPES[i].equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isQualifiedPropertyType(NameExpr expr) {
		for (int i = 0; i < PROPERTY_TYPES.length; i++) {
			if (PROPERTY_TYPES[i].equals(expr.toString())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isQualifiedPropertyType(String name) {
		for (int i = 0; i < PROPERTY_TYPES.length; i++) {
			if (PROPERTY_TYPES[i].equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isByte(Type type) {
		return type.toString().equals(BYTE_TYPE.toString());
	}

	public static boolean isFloat(Type type) {
		return type.toString().equals(FLOAT_TYPE.toString());
	}

	public static boolean isShort(Type type) {
		return type.toString().equals(SHORT_TYPE.toString());
	}

	/**
	 * Gets the wrapped types.
	 * 
	 * @return the wrapped types
	 */
	public static String[] getWrappedTypes() {
		return WRAPPED_TYPES;
	}

	/** The Constant WRAPPED_TYPES. */
	private static final String[] WRAPPED_TYPES = {
			// Wrapped types
			"java.lang.Boolean", "java.lang.Byte", "java.lang.Character",
			"java.lang.Double", "java.lang.Float", "java.lang.Integer",
			"java.lang.Long", "java.lang.Short" };

	private static final String[] PROPERTY_TYPES = {
			// Wrapped types
			"javafx.beans.property.BooleanProperty",
			"javafx.beans.property.DoubleProperty",
			"javafx.beans.property.FloatProperty",
			"javafx.beans.property.IntegerProperty",
			"javafx.beans.property.LongProperty" };

	/** The Constant JAVA_LANG. */
	private static final String[] JAVA_LANG = {
			// Interfaces
			"Appendable",
			"CharSequence",
			"Cloneable",
			"Comparable",
			"Iterable",
			"Readable",
			"Runnable",
			"Thread.UncaughtExceptionHandler",
			// Classes
			"Boolean",
			"Byte",
			"Character",
			"Character.Subset",
			"Character.UnicodeBlock",
			"Class",
			"ClassLoader",
			"Compiler",
			"Double",
			"Enum",
			"Float",
			"InheritableThreadLocal",
			"Integer",
			"Long",
			"Math",
			"Number",
			"Object",
			"Package",
			"Process",
			"ProcessBuilder",
			"Runtime",
			"RuntimePermission",
			"SecurityManager",
			"Short",
			"StackTraceElement",
			"StrictMath",
			"String",
			"StringBuffer",
			"StringBuilder",
			"System",
			"Thread",
			"ThreadGroup",
			"ThreadLocal",
			"Throwable",
			"Void",
			// Enums
			"Thread.State",
			// Exceptions
			"ArithmeticException", "ArrayIndexOutOfBoundsException",
			"ArrayStoreException", "ClassCastException",
			"ClassNotFoundException", "CloneNotSupportedException",
			"EnumConstantNotPresentException", "Exception",
			"IllegalAccessException", "IllegalArgumentException",
			"IllegalMonitorStateException", "IllegalStateException",
			"IllegalThreadStateException",
			"IndexOutOfBoundsException",
			"InstantiationException",
			"InterruptedException",
			"NegativeArraySizeException",
			"NoSuchFieldException",
			"NoSuchMethodException",
			"NullPointerException",
			"NumberFormatException",
			"RuntimeException",
			"SecurityException",
			"StringIndexOutOfBoundsException",
			"TypeNotPresentException",
			"UnsupportedOperationException",
			// Errors
			"AbstractMethodError", "AssertionError", "ClassCircularityError",
			"ClassFormatError", "Error", "ExceptionInInitializerError",
			"IllegalAccessError", "IncompatibleClassChangeError",
			"InstantiationError", "InternalError", "LinkageError",
			"NoClassDefFoundError", "NoSuchFieldError", "NoSuchMethodError",
			"OutOfMemoryError", "StackOverflowError", "ThreadDeath",
			"UnknownError", "UnsatisfiedLinkError",
			"UnsupportedClassVersionError", "VerifyError",
			"VirtualMachineError",
			// Annotation Types
			"Deprecated", "Override", "SuppressWarnings" };

	/** The Constant JAVA_LANG_IO. */
	private static final String[] JAVA_LANG_IO = {
			// Interfaces
			"Closeable",
			"DataInput",
			"DataOutput",
			"Externalizable",
			"FileFilter",
			"FilenameFilter",
			"Flushable",
			"ObjectInput",
			"ObjectInputValidation",
			"ObjectOutput",
			"ObjectStreamConstants",
			"Serializable",
			// Classes
			"BufferedInputStream", "BufferedOutputStream", "BufferedReader",
			"BufferedWriter", "ByteArrayInputStream", "ByteArrayOutputStream",
			"CharArrayReader", "CharArrayWriter", "Console", "DataInputStream",
			"DataOutputStream", "File", "FileDescriptor", "FileInputStream",
			"FileOutputStream", "FilePermission", "FileReader", "FileWriter",
			"FilterInputStream", "FilterOutputStream", "FilterReader",
			"FilterWriter", "InputStream", "InputStreamReader",
			"LineNumberInputStream", "LineNumberReader", "ObjectInputStream",
			"ObjectInputStream.GetField", "ObjectOutputStream",
			"ObjectOutputStream.PutField", "ObjectStreamClass",
			"ObjectStreamField", "OutputStream", "OutputStreamWriter",
			"PipedInputStream", "PipedOutputStream", "PipedReader",
			"PipedWriter", "PrintStream", "PrintWriter", "PushbackInputStream",
			"PushbackReader", "RandomAccessFile",
			"Reader",
			"SequenceInputStream",
			"SerializablePermission",
			"StreamTokenizer",
			"StringBufferInputStream",
			"StringReader",
			"StringWriter",
			"Writer",
			// Exceptions
			"CharConversionException", "EOFException", "FileNotFoundException",
			"InterruptedIOException", "InvalidClassException",
			"InvalidObjectException", "IOException", "NotActiveException",
			"NotSerializableException", "ObjectStreamException",
			"OptionalDataException", "StreamCorruptedException",
			"SyncFailedException", "UnsupportedEncodingException",
			"UTFDataFormatException", "WriteAbortedException",
			// Errors
			"IOError" };

}
