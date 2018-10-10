package orz.anzhy.sffx.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import orz.anzhy.sffx.monitor.Consts;
import orz.anzhy.sffx.monitor.bean.App;
import orz.anzhy.sffx.monitor.cache.ICache;
import orz.anzhy.sffx.monitor.cache.ShareCache;

@Service
public class AppService {

    @SuppressWarnings("unchecked")
    public List<App> getAppList() {

        ICache cache = ShareCache.getInstance();

        List<App> list = (List<App>) cache.getCacheData(Consts.APP_LIST);
        return list;
    }

}
