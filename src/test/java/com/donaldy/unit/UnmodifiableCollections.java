package com.donaldy.unit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableCollections {

    public static void main(String[] args) {

        Map<String, String> modifiableMap = new HashMap<>();
        modifiableMap.put("test1", "test1");
        modifiableMap.put("test2", "test2");

        modifiableMap = Collections.unmodifiableMap(modifiableMap);

        System.out.println(modifiableMap.toString());

        modifiableMap.put("test3", "test3");

        System.out.println(modifiableMap.toString());
    }
}
