package com.example.core.service;

public class TreeNode<T> {

    private T data;

    private T leftChild;

    private T rightChild;

    public TreeNode(T data, T leftChild, T rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
