package com.example.core;

import com.example.model.single.LazySingle;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
class CoreApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;



    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        String context = "此消息在默认的交换机模式队列下, 有helloReceiver可以收到";
        String routeKey = "hello";
        context = "routeKey:" + routeKey + ",context:" + context;
        System.out.println("sendHelloTest:" + context);
        amqpTemplate.convertAndSend(routeKey, context);
    }

    @Test
    void test2() {
        IntStream.range(0, 20).forEach(i -> {
            new Thread(() -> {
                LazySingle instance = LazySingle.getInstance();
                System.out.println(Thread.currentThread().getName() + "====" + instance);
            }, "Thread" + String.valueOf(i)).start();
        });
    }

}
