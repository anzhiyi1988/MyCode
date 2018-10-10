package orz.anzhy.sffx.monitor.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    @Autowired
    @Qualifier("jedisPool")
    protected JedisPool jedisPool;

    private Jedis getClient() {
        try {
            Jedis jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            logger.error("getRedisClent error", e);
        }
        return null;

    }

    /**
     * @param listName 要向哪个list加入值，如果不存在则建立。如果名字存在但不是list类型，则报错
     * @param value  装换成json string
     * @return  
     * 返回增加后list中的数量，如果错误返回null
     */
    protected Long listAdd(String listName, String value) {
        Long result = null;
        Jedis jedis = getClient();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.rpush(listName, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            jedis.close();
        }
        return result;
    }

    protected List<String> listAll(String key) {
        List<String> result = null;
        Jedis jedis = getClient();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.lrange(key, 0, -1);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            jedis.close();
        }
        return result;
    }

}
