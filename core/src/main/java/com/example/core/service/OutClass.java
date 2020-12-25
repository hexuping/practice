package com.example.core.service;

public class OutClass {

    private static int a;

    private int b;

    private void setB(int b) {
        this.b = b;
    }

    static class inner {

        private int c;

        private void test() {
            a = 10;
//            setB(10);
//            c = a + OutClass.this.b;
        }
    }

    public void test() {
        new inner().test();
    }

    public static void main(String[] args) {
        OutClass outClass = new OutClass();
//        inner inner = outClass.new inner();
//        inner.test();
//        System.out.println(String.format("a = %d, b = %d, c = %d", a, outClass.b, inner.c));
    }
}
