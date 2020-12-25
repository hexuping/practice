package com.example.core.service;

import java.lang.IllegalMonitorStateException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ThreadTest {

    public synchronized void waitTest() {
        System.out.println("----------Start");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------End");
    }


    public static void main(String[] args) {
//        ThreadTest threadTest = new ThreadTest();
//        new Thread(() -> threadTest.waitTest(), "Thread-A").start();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DATE)-1);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
//        calendar.set(Calendar.DATE, -1);
//        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println(format);
    }
}
