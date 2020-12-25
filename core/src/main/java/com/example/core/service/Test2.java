package com.example.core.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test2 {

    private static int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};

    public static void main(String[] args) {
        List<Integer> list = Arrays.stream(height).boxed().collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {

        }
    }
}
