package com.uec.ibrain.ibrain.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 实现cache共享 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 * @param <K>
 * @param <V>
 */
@Component
public class ShiroCache<K, V> implements Cache<K, V> {

    private static final String REDIS_SHIRO_CACHE = "redis-shiro-cache:";
    private String cacheKey;
    private RedisTemplate<K, V> redisTemplate;
    
//    @Value("${server.session.timeout}")
//	private long sessionTimeout;         //取不到值，原因未知
    private long sessionTimeout = 1800;

    @SuppressWarnings("rawtypes")
    public ShiroCache(String name, RedisTemplate client) {
        this.cacheKey = REDIS_SHIRO_CACHE + name + ":";
        this.redisTemplate = client;
    }

    @Override
    public V get(K key) throws CacheException {
        redisTemplate.boundValueOps(getCacheKey(key)).expire(sessionTimeout, TimeUnit.SECONDS);
        return redisTemplate.boundValueOps(getCacheKey(key)).get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
//        System.out.println("put session :"+key+"  "+sessionTimeout);
        redisTemplate.boundValueOps(getCacheKey(key)).set(value);
        V old = get(key);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
//    	System.out.println("remove session :"+key);
        V old = get(key);
        redisTemplate.delete(getCacheKey(key));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }
}
