package com.example.demoapp.config;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

@Slf4j
public class MyCacheManagerEventListener implements CacheManagerEventListener {

    private final CacheManager cacheManager;

    public MyCacheManagerEventListener(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void init() throws CacheException {
        log.info("..cache init");
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void dispose() throws CacheException {
        log.info("ehcache dispose");
    }

    @Override
    public void notifyCacheAdded(String s) {
        log.info("notify cache added ..{}"+s);
        log.info(cacheManager.getCache(s).toString());
    }

    @Override
    public void notifyCacheRemoved(String s) {

    }
}
