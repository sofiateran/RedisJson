package com.lambda.configuration;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisClient {
    private Jedis jedis;

    public RedisClient(String host, int port) {
        jedis = new Jedis(host, port);
    }

    public void set(String key, String value) {
        jedis.set(key, value);
    }

    public Long sadd(String key, String... members) {
        return jedis.sadd(key, members);
    }

    public Long srem(String key, String... members) {
        return jedis.srem(key, members);
    }
    public List<String> smembers(String key) {
        Set<String> set = jedis.smembers(key);
        return new ArrayList<>(set);
    }

    public String get(String key) {
        return jedis.get(key);
    }

    public void close() {
        jedis.close();
    }

}
