package org.defihq.service;

import org.defihq.model.RedisData;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {

    RedisData getRedisRecord(String key) throws Exception;
    RedisData postRedisRecord(String key, String value) throws Exception;
    RedisData deleteRedisRecord(String key) throws Exception;
    //RedisData updateRedisRecord(String key, String value) throws Exception;
    RedisData hasRedisRecord(String key) throws Exception;
}
