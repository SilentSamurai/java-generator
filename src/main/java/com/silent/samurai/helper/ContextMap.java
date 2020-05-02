package com.silent.samurai.helper;

import java.util.HashMap;

public class ContextMap extends HashMap<String, Object> {


    public static ContextMap newContext() {
        return new ContextMap();
    }
}
