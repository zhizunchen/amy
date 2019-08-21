package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Resource
    private ExecutorService executorService;


    @Test
    public void test(){
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void testExecutor() {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("===============");
            }
        });

    }

}
