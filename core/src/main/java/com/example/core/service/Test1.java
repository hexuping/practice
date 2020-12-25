package com.example.core.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Test1 {

    private static String str = "{}";

    public static boolean test(String str) {
        Map<String, String> map = new HashMap<>();
        map.put(")", "(");
        map.put("}", "{");
        map.put("]", "[");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if(map.containsKey(s)) {
                if(!stack.isEmpty()) {
                    String value = map.get(s);
                    String pop = stack.pop();
                    if(!value.equals(pop)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.push(s);
            }
        }

        stack.empty();
        if(stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean test = test(str);
        System.out.println(test);
    }

}
