package com.example.redis.paging.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class TestRedisUtil {

    @Test
    public void testInitHash() throws Exception {
        for(int i=1;i<=30;i++){
            MyItem item = new MyItem();
            item.setName("商品" + i);
            item.setPrice("10.00");
            RedisUtil.hset("redis:paging:demo:hash:item", String.valueOf(i), MyJsonUtil.pojoToJson(item));
        }
    }

    @Test
    public void testInitZset(){
        for(int i=1;i<=30;i++){
            RedisUtil.zadd("redis:paging:demo:zset:item", i, i+"");
        }
    }

    @Test
    public void testQueryPage(){
        Set<String> set = this.queryPage(2, 5);
        for (String item : set){
            System.out.println(item);
        }
    }

    @Test
    public void testGetTotalRecord(){
        System.out.println(RedisUtil.zcard("redis:paging:demo:zset:item"));
    }

    private Set<String> queryPage(int pageNo, int pageSize) {
        long start = (pageNo-1) * pageSize;
        long stop = start + pageSize -1;
        return RedisUtil.zrange("redis:paging:demo:zset:item", start, stop);
    }
}
