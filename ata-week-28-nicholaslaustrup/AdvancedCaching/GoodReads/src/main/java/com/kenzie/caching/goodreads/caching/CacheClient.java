package com.kenzie.caching.goodreads.caching;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.inject.Inject;

public class CacheClient {
    private final JedisPool pool;
    @Inject
    public CacheClient(JedisPool jedisPool) {
        this.pool = jedisPool;
    }

    public void setValue(String key, String value) {
        if (key == null){
            throw new IllegalArgumentException("Key cannot be empty!");
        }
        try (Jedis jedis = pool.getResource()){
            jedis.setex(key, 60*60, value);
        }
    }
    public String getValue(String key) {
        if (key == null){
            throw new IllegalArgumentException("Key cannot be empty!");
        }
        try (Jedis jedis = pool.getResource()){
            return jedis.get(key);
        }
    }

    public boolean invalidate(String key){
        if (key == null){
            throw new IllegalArgumentException("Key cannot be empty!");
        }
        try (Jedis jedis = pool.getResource()){
            long isDeleted = jedis.del(key);
            if (isDeleted > 0){
                return true;
            }
        }
        return false;
    }
}
