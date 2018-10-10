package orz.anzhy.sffx.monitor.cache;

import java.util.HashMap;
import java.util.Map;

public final class ShareCache implements ICache {

    private final static ICache CACHE = new ShareCache();

    private final static Map<String, Object> CACHEDATA = new HashMap<String, Object>();

    private ShareCache() {

    }

    public static ICache getInstance() {
        return CACHE;
    }

    @Override
    public void addCacheData(String key, Object data) {
        CACHEDATA.put(key, data);
    }

    @Override
    public Object getCacheData(String key) {
        return CACHEDATA.get(key);
    }

}
