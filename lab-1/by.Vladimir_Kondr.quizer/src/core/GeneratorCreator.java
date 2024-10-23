package core;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Constructor;
import java.util.Map;

public abstract class GeneratorCreator {
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

    protected CtClass[] toCtClass(ClassPool pool, Class<?>[] classes) throws NotFoundException {
        CtClass[] ctClasses = new CtClass[classes.length];
        for (int i = 0; i < classes.length; i++) {
            ctClasses[i] = pool.get(classes[i].getName());
        }
        return ctClasses;
    }
}