package com.jianping.demo;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.data.redis.connection.jedis.JedisUtils;

public class RedisTest {
	public static void main(String[] args) {
        //连接本地的 Redis 服务
        System.out.println("连接成功");
        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());
    }
}
