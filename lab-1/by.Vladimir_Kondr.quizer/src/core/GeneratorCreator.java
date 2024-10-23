package core;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Abstract class for creating instances of TaskGenerator with specified arguments.
 */
public abstract class GeneratorCreator {

    /**
     * Creates an instance of TaskGenerator using the provided generator class and arguments map.
     *
     * @param generatorClass the class of the TaskGenerator to create
     * @param argsMap        a map of argument names to values
     * @return an instance of TaskGenerator
     * @throws IllegalArgumentException if required arguments are missing or have type mismatches
     * @throws RuntimeException         if the generator instance creation fails
     */
    protected TaskGenerator<? extends Task> createGenerator(Class<? extends TaskGenerator<? extends Task>> generatorClass, Map<String, Object> argsMap) {
        try {
            Constructor<?>[] constructors = generatorClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                String[] parameterNames = getParameterNames(constructor);
                Object[] args = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    String paramName = parameterNames[i];
                    if (!argsMap.containsKey(paramName)) {
                        throw new IllegalArgumentException("Missing argument: " + paramName);
                    }
                    Object arg = argsMap.get(paramName);
                    try {
                        if (parameterTypes[i].isPrimitive()) {
                            args[i] = switch (parameterTypes[i].getName()) {
                                case "int" -> args[i] = ((Number) arg).intValue();
                                case "long" -> args[i] = ((Number) arg).longValue();
                                case "double" -> args[i] = ((Number) arg).doubleValue();
                                case "float" -> args[i] = ((Number) arg).floatValue();
                                case "boolean" -> args[i] = ((Boolean) arg).booleanValue();
                                case "char" -> args[i] = ((Character) arg).charValue();
                                case "byte" -> args[i] = ((Number) arg).byteValue();
                                case "short" -> args[i] = ((Number) arg).shortValue();
                                default -> throw new IllegalStateException("Unexpected value: " + parameterTypes[i].getName());
                            };
                        } else {
                            args[i] = parameterTypes[i].cast(arg);
                        }
                    } catch (ClassCastException e) {
                        throw new IllegalArgumentException("Argument type mismatch for: " + paramName, e);
                    }
                }
                return (TaskGenerator<? extends Task>) constructor.newInstance(args);
            }
            throw new IllegalArgumentException("No suitable constructor found for generator class: " + generatorClass.getName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create generator instance", e);
        }
    }

    /**
     * Retrieves the parameter names for the given constructor.
     *
     * @param constructor the constructor to retrieve parameter names for
     * @return an array of parameter names
     * @throws Exception if an error occurs while retrieving parameter names
     */
    protected String[] getParameterNames(Constructor<?> constructor) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(constructor.getDeclaringClass()));
        CtClass cc = pool.get(constructor.getDeclaringClass().getName());
        CtConstructor ctConstructor = cc.getDeclaredConstructor(toCtClass(pool, constructor.getParameterTypes()));
        MethodInfo methodInfo = ctConstructor.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        String[] paramNames = new String[ctConstructor.getParameterTypes().length];
        int pos = Modifier.isStatic(ctConstructor.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }

    /**
     * Converts an array of Class objects to an array of CtClass objects.
     *
     * @param pool    the ClassPool to use for conversion
     * @param classes the array of Class objects to convert
     * @return an array of CtClass objects
     * @throws NotFoundException if a class cannot be found in the pool
     */
    protected CtClass[] toCtClass(ClassPool pool, Class<?>[] classes) throws NotFoundException {
        CtClass[] ctClasses = new CtClass[classes.length];
        for (int i = 0; i < classes.length; i++) {
            ctClasses[i] = pool.get(classes[i].getName());
        }
        return ctClasses;
    }
}