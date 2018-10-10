package orz.anzhy.sffx.monitor.dao;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ConnTest {

    @Test
    public void connTest() {
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(config, "192.168.56.101", 6379);

        Jedis jedis = jedisPool.getResource();
        jedis.lpush("testList", "aaaaaaaaaaaa");
    }

}
