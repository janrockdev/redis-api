package org.defihq.service;

import org.defihq.model.RedisData;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {

    RedisData postRedisRecord(String key, String value) throws Exception;
}
