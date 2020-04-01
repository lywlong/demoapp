package com.example.demoapp.config;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

import java.util.Properties;

public class MyCacheManagerEventListenerFactory extends CacheManagerEventListenerFactory {

    @Override
    public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
        return new MyCacheManagerEventListener(cacheManager);
    }
}
