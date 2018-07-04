package com.uec.ibrain.ibrain.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: Redis实现shiro的SessionDao存取session </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO {
	
	Log logger = LogFactory.getLog(RedisSessionDao.class);

	@Value("${server.session.timeout}")
	private long sessionTimeout;

    private static String prefix = "redis-shiro-session:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.debug("创建session:"+session.getId());
        redisTemplate.opsForValue().set(prefix + sessionId.toString(), session, sessionTimeout, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String key = prefix + session.getId().toString();
            redisTemplate.opsForValue().set(key, session);
        redisTemplate.expire(key, sessionTimeout, TimeUnit.SECONDS);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        logger.debug("删除session:"+session.getId());
        super.doDelete(session);
        redisTemplate.delete(prefix + session.getId().toString());
    }
	
}
