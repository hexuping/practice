package com.example.core.service;

import java.util.Stack;

public class PracticeTest {

    public void createTree() {
        Integer[] nodes = new Integer[]{1, 2, 3, null, null, 4, 5};
        for(int i = 0; i < nodes.length; i++) {
            Integer nodeVal = nodes[i];


        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("first");
        stack.push("second");

        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
