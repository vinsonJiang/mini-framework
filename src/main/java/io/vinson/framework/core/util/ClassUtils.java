package io.vinson.framework.core.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * 类处理相关工具方法
 *
 * @author: jiangweixin
 * @date: 2018/12/28
 */
public class ClassUtils {

    /** 数组类型的后缀 */
    public final static String ARRAY_SUFFIX = "[]";

    /**
     * 缓存原始类型的map，example：Integer.class -> int.class
     */
    public final static Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap<>(8);
    /**
     * 缓存原始类型的map，example：int.class -> Integer.class
     */
    public final static Map<Class<?>, Class<?>> primitiveTypeWrapperMap = new HashMap<>(8);
    /**
     * 缓存原始类型的map，example：'int' -> int.class
     */
    public final static Map<String, Class<?>> primitiveStringTypeMap = new HashMap<>(8);

    public final static Map<String, Class<?>> commonCacheMap = new HashMap<>(128);


    /**
     * 初始化工具类
     */
    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);

        primitiveWrapperTypeMap.forEach((key, value) -> {
            primitiveTypeWrapperMap.put(value, key);
            primitiveStringTypeMap.put(value.getName(), value);
        });

    }

    public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException {
        Assert.notNull(name, "Name must not be null");
        Class<?> clazz = null;
        // 解析原始数据类型
        if(name.length() <= 8) {
            clazz = primitiveStringTypeMap.get(name);
        }
        if(clazz == null) {
            clazz = commonCacheMap.get(name);
        }
        if(clazz != null) {
            return clazz;
        }
        // java.lang.String[]
        if(name.endsWith(ARRAY_SUFFIX)) {
            String realClassName = name.substring(0, name.indexOf(ARRAY_SUFFIX));
            clazz = forName(realClassName, classLoader);
            return Array.newInstance(clazz, 0).getClass();
        }
        // 其他类型暂不处理
        // TODO:

        if(classLoader == null) {
            classLoader = getDefaultClassLoader();
        }
        try {
            if(classLoader != null) {
                return classLoader.loadClass(name);
            } else {
                return Class.forName(name);
            }
        } catch (ClassNotFoundException e) {

            throw e;
        }
    }

    /**
     * 获取一个默认的classLoader
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
            if(classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

}
