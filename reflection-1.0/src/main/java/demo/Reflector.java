package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Demonstrates reflection capabilities.  This application uses reflection to
 * discover detailed information about a class and print this information,
 * roughly equivalent to the javap utility.
 *
 * @author Russ Moul
 */
public final class Reflector {
    /**
     * Prevent instantiation.
     */
    private Reflector() {
    }

    /**
     * Gets the type name of a class removing any array notation.
     *
     * @param cls the array class
     *
     * @return the class or primitive type name
     */
    private static String pureTypeName(final Class<?> cls) {
        String typeName = "";

        Class<?> clz = cls;
        while (clz.isArray()) {
            clz = clz.getComponentType();
        }

        typeName = clz.getName();

        return typeName;
    }

    /**
     * Gets the array depth of an array class.
     *
     * @param cls the array class
     *
     * @return the array depth
     */
    private static int arrayDepth(final Class<?> cls) {
        String name = cls.getName();
        int ndx = name.lastIndexOf('[');

        return ndx + 1;
    }

    /**
     * Prints the field declarations for a class.
     *
     * @param cls the class
     */
    private static void printFieldDeclarations(final Class<?> cls) {
        Field[] f = cls.getDeclaredFields();

        for (int i = 0; i < f.length; i++) {
            System.out.print("   ");

            // print the modifiers
            int modifiers = f[i].getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");

            // print field type and name
            Class<?> type = f[i].getType();
            System.out.print(pureTypeName(type) + " ");
            System.out.print(f[i].getName());

            // print return type array declarations
            int depth = arrayDepth(f[i].getType());

            for (int j = 0; j < depth; j++) {
                System.out.print("[]");
            }

            System.out.println(";");
        }
    }

    /**
     * Prints the constructor declarations for a class.
     *
     * @param cls the class
     */
    private static void printConstructorDeclarations(final Class<?> cls) {
        Constructor<?>[] c = cls.getDeclaredConstructors();

        for (int i = 0; i < c.length; i++) {
            System.out.print("   ");

            // print the modifiers
            int modifiers = c[i].getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");

            // print the method name
            System.out.print(c[i].getName());

            // print the argument types
            System.out.print("(");

            Class<?>[] paramTypes = c[i].getParameterTypes();

            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }

                System.out.print(pureTypeName(paramTypes[j]));

                int depth = arrayDepth(paramTypes[j]);

                for (int k = 0; k < depth; k++) {
                    System.out.print("[]");
                }
            }

            System.out.print(")");

            // print exceptions thrown
            Class<?>[] exceptionTypes = c[i].getExceptionTypes();

            if (exceptionTypes.length > 0) {
                System.out.print(" throws ");

                for (int j = 0; j < exceptionTypes.length; j++) {
                    if (j > 0) {
                        System.out.print(", ");
                    }

                    System.out.print(exceptionTypes[j].getName());
                }
            }

            System.out.println(";");
        }
    }

    /**
     * Prints the method declarations for a class.
     *
     * @param cls the class
     */
    private static void printMethodDeclarations(final Class<?> cls) {
        Method[] m = cls.getDeclaredMethods();

        for (int i = 0; i < m.length; i++) {
            System.out.print("   ");

            // print the modifiers
            int modifiers = m[i].getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");

            // print return type
            Class<?> returnType = m[i].getReturnType();
            System.out.print(pureTypeName(returnType) + " ");

            // print the method name
            System.out.print(m[i].getName());

            // print the argument types
            System.out.print("(");

            Class<?>[] paramTypes = m[i].getParameterTypes();

            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }

                System.out.print(pureTypeName(paramTypes[j]));

                int depth = arrayDepth(paramTypes[j]);

                for (int k = 0; k < depth; k++) {
                    System.out.print("[]");
                }
            }

            System.out.print(")");

            // print exceptions thrown
            Class<?>[] exceptionTypes = m[i].getExceptionTypes();

            if (exceptionTypes.length > 0) {
                System.out.print(" throws ");

                for (int j = 0; j < exceptionTypes.length; j++) {
                    if (j > 0) {
                        System.out.print(", ");
                    }

                    System.out.print(exceptionTypes[j].getName());
                }
            }

            // print return type array declarations
            int depth = arrayDepth(returnType);

            for (int j = 0; j < depth; j++) {
                System.out.print("[]");
            }

            System.out.println(";");
        }
    }

    /**
     * Prints the declaration for a class.
     *
     * @param cls the class
     */
    public static void dumpClassDeclaration(final Class<?> cls) {
        // print the modifiers
        int modifiers = cls.getModifiers();
        System.out.print(Modifier.toString(modifiers) + " ");

        // class or interface lineage
        Class<?>[] interfaces = cls.getInterfaces();

        if (cls.isInterface()) {
            System.out.print("interface ");
            System.out.print(cls.getName());

            if (interfaces.length > 0) {
                System.out.print(" extends ");
            }
        } else {
            System.out.print("class ");
            System.out.print(cls.getName());

            // print super class name
            System.out.print(" extends " + cls.getSuperclass().getName());

            if (interfaces.length > 0) {
                System.out.print(" implements ");
            }
        }

        // print interfaces
        for (int i = 0; i < interfaces.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(interfaces[i].getName());
        }

        // print members
        System.out.println(" {");
        printFieldDeclarations(cls);
        printConstructorDeclarations(cls);
        printMethodDeclarations(cls);
        System.out.println("}");
    }

    /**
     * Prints the class declaration of a class.
     *
     * @param args arg[0] is the name of the class to process
     */
    public static void main(final String[] args) {
        try {
            Class<?> cls = Class.forName(args.length > 0 ? args[0] : "java.lang.String");
            dumpClassDeclaration(cls);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
