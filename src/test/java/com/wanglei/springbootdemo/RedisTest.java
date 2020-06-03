package com.wanglei.springbootdemo;

import com.wanglei.springbootdemo.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("wanglei", "nanshen");
    }

    @Test
    public void pushList(){
        List<String> list = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        redisUtils.rightPushAll("number",list);
    }

    @Test
    public void popList(){
        String value = redisUtils.leftPopList("number");
        System.out.println(value);
    }

    @Test
    public void rightPopList(){
        String value = redisUtils.rightPopList("number");
        System.out.println(value);
    }

    @Test
    public void pub() throws InterruptedException{
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            redisUtils.pub("test1","这是我发送的第"+i+"个消息");
        }
    }
}
