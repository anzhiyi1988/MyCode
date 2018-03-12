package com.thunisoft.sffx.monitor.cache;

public interface ICache {

    public void addCacheData(String key, Object data);

    public Object getCacheData(String key);

}
