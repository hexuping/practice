package com.example.core.service;

public class PracticeTest1 {

    static class Animal {
        @Override
        public String toString() {
            return "Animal";
        }
    }

    static class Dog extends Animal {
        @Override
        public String toString() {
            return "Dog";
        }
    }

    static class Fruit {
        @Override
        public String toString() {
            return "Fruit";
        }
    }

    static class TestClass<T> {

        public void show01(T t) {
            System.out.println(t.toString());
        }

        public <T> void show02(T t) {
            System.out.println(t.toString());
        }

        public <K> void show03(K k) {
            System.out.println(k.toString());
        }
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        Fruit fruit = new Fruit();
        TestClass<Animal> testClass = new TestClass<>();
        testClass.show01(dog);

        // 使用时指定变量
        testClass.show02(animal);
        testClass.show02(dog);
        testClass.show03(fruit);

        testClass.show03(animal);
        testClass.show03(dog);
        testClass.show03(fruit);
    }
}
