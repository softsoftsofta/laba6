package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@ToString(value = ToString.Value.YES)
public class CacheUtil {

    public static Object[] cache(Object... objects) {
        if (objects == null || objects.length == 0) {
            return new Object[0];
        }

        Object[] cachedObjects = new Object[objects.length];

        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            if(obj == null) continue;
            Class<?> objClass = obj.getClass();
            if (objClass.isAnnotationPresent(Cache.class)) {
                cachedObjects[i] =  cache(obj);
            }
            else cachedObjects[i] = obj;

        }
        return cachedObjects;
    }

    public static <T> T cache(T obj) {
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new CacheInvocationHandler(obj)
        );
    }

    private static class CacheInvocationHandler implements InvocationHandler {
        private final Object target;
        private final Map<Method, Object> cache = new HashMap<>();
        private String lastState;
        private List<String> cacheMethods = new ArrayList<>(); //3.3 переменная для параметров @Cache

        public CacheInvocationHandler(Object target) {
            this.target = target;
            this.lastState = serializeState(target);
            this.cacheMethods = getCacheMethods(target.getClass()); //3.3 считали параметры с помощью метода getCacheMethods
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean shouldCache = cacheMethods.isEmpty() || cacheMethods.contains(method.getName()); //3.3 дополнительная переменная проверки CacheMethods
            if (shouldCache && (args == null || args.length == 0)) {
                String currentState = serializeState(target);
                if (!cache.containsKey(method) || !currentState.equals(lastState)) {
                    Object result = method.invoke(target, args);
                    cache.put(method, result);
                    lastState = currentState;
                    return result;
                } else {
                    return cache.get(method);
                }
            } else {
                return method.invoke(target, args);
            }
        }

        private String serializeState(Object obj) {
            // Простая сериализация состояния объекта
            return obj.toString();
        }

        // 3.3 Собирает все параметры из @Cache, которые будут кэшироваться
        private List<String> getCacheMethods(Class<?> clazz){
            if(clazz.isAnnotationPresent(Cache.class)){
                Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
                return  Arrays.asList(cacheAnnotation.value());
            }
            return Collections.emptyList();
        }
    }
}