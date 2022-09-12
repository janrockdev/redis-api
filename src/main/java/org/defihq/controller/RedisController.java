package org.defihq.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.defihq.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RedisController {

    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/getRecord")
    public CompletableFuture<String> getRecord(@RequestBody JsonNode documentDetails) {
        log.info("Path: getRecord");
        return CompletableFuture.supplyAsync(() -> {
            log.info(documentDetails.toString());
            try {
                return redisService.getRedisRecord(documentDetails.get("key").toString()).getJsonData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    @PostMapping("/postRecord")
    public CompletableFuture<String> postRecord(@RequestBody JsonNode documentDetails) {
        log.info("Path: postRecord");
        return CompletableFuture.supplyAsync(() -> {
            log.info(documentDetails.toString());
            try {
                return redisService.postRedisRecord(documentDetails.get("key").toString(), documentDetails.get("value").toString()).getJsonData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

}