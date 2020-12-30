package com.example.model.single;

public class HungrySingle {

    private HungrySingle() {}

    private static HungrySingle obj = new HungrySingle();

    public static HungrySingle getInstance() {
        return obj;
    }

}
