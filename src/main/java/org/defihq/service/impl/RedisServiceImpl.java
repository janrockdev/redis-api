package org.defihq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.defihq.model.RedisData;
import org.defihq.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    public static Jedis connRedis(String host, String port) {
        return new Jedis(host, Integer.parseInt(port));
    }

    @Override
    public RedisData getRedisRecord(String key) throws Exception {
        try (Jedis redisDb = connRedis("localhost", "6379")) { //TODO: replace from config
            String res = redisDb.get(key.replaceAll("\"", ""));
            return new RedisData("{\"key\":\"" + res + "\"}"); //TODO: json generator
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public RedisData postRedisRecord(String key, String value) {
        try (Jedis redisDb = connRedis("localhost", "6379")) { //TODO: Replace from config
            redisDb.set(key.replaceAll("\"", ""), value.replaceAll("\"", ""));
            return new RedisData("{\"result\":\"success\"}"); //TODO: json generator
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public RedisData deleteRedisRecord(String key) throws Exception {
        try (Jedis redisDb = connRedis("localhost", "6379")) { //TODO: Replace from config
            redisDb.del(key.replaceAll("\"", ""));
            return new RedisData("{\"result\":\"success\"}"); //TODO: json generator
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public RedisData hasRedisRecord(String key) throws Exception {
        try (Jedis redisDb = connRedis("localhost", "6379")) { //TODO: replace from config
            String res = redisDb.get(key.replaceAll("\"", ""));
            if (res != null) {
                return new RedisData("{\"key\":\"true\"}"); //TODO: json generator
            } else {
                return new RedisData("{\"key\":\"false\"}"); //TODO: json generator
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

}
