package droid.mobile.games.common.bus;

import java.util.Map;
import java.util.Set;

interface HandlerFinder {
    Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object listener);


    HandlerFinder ANNOTATED = new HandlerFinder() {
        @Override
        public Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object listener) {
            return AnnotatedHandlerFinder.findAllSubscribers(listener);
        }
    };
}