package droid.mobile.games.common.bus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class AnnotatedHandlerFinder {

    /** Cache event bus subscriber methods for each class. */
    private static final ConcurrentMap<Class<?>, Map<Class<?>, Set<Method>>> SUBSCRIBERS_CACHE =
            new ConcurrentHashMap<Class<?>, Map<Class<?>, Set<Method>>>();


    private static void loadAnnotatedSubscriberMethods(Class<?> listenerClass,
                                                       Map<Class<?>, Set<Method>> subscriberMethods) {
        Map<Class<?>, Method> producerMethods = new HashMap<Class<?>, Method>();
        loadAnnotatedMethods(listenerClass, subscriberMethods);
    }

    /**
     * Load all methods annotated with {@link Subscribe} into their respective caches for the
     * specified class.
     */

    private static void loadAnnotatedMethods(Class<?> listenerClass,Map<Class<?>, Set<Method>> subscriberMethods) {
        for (Method method : listenerClass.getDeclaredMethods()) {
            // The compiler sometimes creates synthetic bridge methods as part of the
            // type erasure process. As of JDK8 these methods now include the same
            // annotations as the original declarations. They should be ignored for
            // subscribe/produce.
            if (method.isBridge()) {
                continue;
            }
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires "
                            + parameterTypes.length + " arguments.  Methods must require a single argument.");
                }

                Class<?> eventType = parameterTypes[0];
                if (eventType.isInterface()) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + eventType
                            + " which is an interface.  Subscription must be on a concrete class type.");
                }

                if ((method.getModifiers() & Modifier.PUBLIC) == 0) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + eventType
                            + " but is not 'public'.");
                }

                Set<Method> methods = subscriberMethods.get(eventType);
                if (methods == null) {
                    methods = new HashSet<Method>();
                    subscriberMethods.put(eventType, methods);
                }
                methods.add(method);
            }
        }

        //PRODUCERS_CACHE.put(listenerClass, producerMethods);
        SUBSCRIBERS_CACHE.put(listenerClass, subscriberMethods);
    }

    /** This implementation finds all methods marked with a {@link Subscribe} annotation. */
    static Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object listener) {
        Class<?> listenerClass = listener.getClass();
        Map<Class<?>, Set<EventHandler>> handlersInMethod = new HashMap<Class<?>, Set<EventHandler>>();

        Map<Class<?>, Set<Method>> methods = SUBSCRIBERS_CACHE.get(listenerClass);
        if (null == methods) {
            methods = new HashMap<Class<?>, Set<Method>>();
            loadAnnotatedSubscriberMethods(listenerClass, methods);
        }
        if (!methods.isEmpty()) {
            for (Map.Entry<Class<?>, Set<Method>> e : methods.entrySet()) {
                Set<EventHandler> handlers = new HashSet<EventHandler>();
                for (Method m : e.getValue()) {
                    handlers.add(new EventHandler(listener, m));
                }
                handlersInMethod.put(e.getKey(), handlers);
            }
        }

        return handlersInMethod;
    }

    private AnnotatedHandlerFinder() {
        // No instances.
    }

}