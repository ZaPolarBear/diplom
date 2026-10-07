package org.university.diplom.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheEvictService {

    @CacheEvict(value = "waves", allEntries = true)
    @Scheduled(fixedRateString = "${spring.cache.time-to-live}", timeUnit = TimeUnit.MINUTES)
    public void evictCache() {
        log.info("Evicting cache");
    }
}
