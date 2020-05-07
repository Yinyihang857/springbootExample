package com.sx.springbootexample.baen;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String, String> localHost = new HashMap<>();
    private static Cache cache = new Cache();

    public static Cache CacheObj() {
        return cache;
    }

    public String get(String key) {
        return localHost.get(key);
    }

    public void set(String key, String value) {
        localHost.put(key, value);
    }
}
