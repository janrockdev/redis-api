package org.defihq.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.defihq.model.RedisData;
import org.defihq.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    public static final Logger logger = LogManager.getLogger();

    public static String pingRedis(String host, String port) {

        try (Jedis jedis = new Jedis(host, Integer.parseInt(port))) {
            return jedis.ping();
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }

    public static Jedis connRedis(String host, String port) {
        return new Jedis(host, Integer.parseInt(port));
    }

    @Override
    public RedisData postRedisRecord(String key, String value) {
        try (Jedis redisDb = connRedis("localhost", "6379")) {
            redisDb.set(key.replaceAll("\"", ""), value.replaceAll("\"", ""));
            return new RedisData("\"result\":\"success\"");
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
