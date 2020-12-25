package com.example.core.service;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {

    private static String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Map<String, ArrayList<String>> map = new HashMap<>();
        Arrays.asList(str).stream().forEach(s -> {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            System.out.println(key);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        });
        System.out.println(new ArrayList<>(map.values()));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
