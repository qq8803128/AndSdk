package droid.mobile.games.common.parameter;

import java.io.Serializable;
import java.util.Map;

public interface Parameter extends Serializable {
    <T extends Parameter> T to(Class<T> clazz);
    Parameter setFunction(String function);
    String getFunction();
    Map<String,Object> container();
    Map<String,String> map();
    <T> T get(String key,T defVal);
    Object get(String key);
    boolean contains(String key);
    boolean isEmpty();
    void remove(String key);
    Parameter put(String key,Object value);
    Parameter putAll(Map<String,Object> map);
    Parameter putAll(Parameter parameter);
}
