package droid.mobile.games.common.json;

import java.util.Map;

public class Json {
    Map<String,Object> container;

    public final Map<String, Object> getContainer() {
        return container;
    }

    public final <T> T get(T defVal,String... keys){
        try{
            Map<String,Object> map = container;
            Object retn = null;
            for (String key : keys){
                Object object = map.get(key);
                if (object instanceof Map){
                    map = (Map<String, Object>) object;
                }
                retn = object;
            }
            if (retn != null){
                if (retn instanceof Number){
                    Object o = null;
                    if (defVal instanceof Float){
                        o = ((Number)retn).floatValue();
                    }else if (defVal instanceof Integer){
                        o = ((Number)retn).intValue();
                    }else if (defVal instanceof Double){
                        o = ((Number)retn).doubleValue();
                    }else if (defVal instanceof Long){
                        o = ((Number)retn).longValue();
                    }else if (defVal instanceof Byte){
                        o = ((Number)retn).byteValue();
                    }else if (defVal instanceof Short){
                        o = ((Number)retn).shortValue();
                    }
                    return (T)o;
                }else {
                    if (defVal != null) {
                        if (retn.getClass().getName().equals(defVal.getClass().getName())) {
                            return (T) retn;
                        }else{
                            return defVal;
                        }
                    }else {
                        return (T) retn;
                    }
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        return defVal;
    }
}
