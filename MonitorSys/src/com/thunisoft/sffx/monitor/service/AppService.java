package com.thunisoft.sffx.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thunisoft.sffx.monitor.Consts;
import com.thunisoft.sffx.monitor.bean.App;
import com.thunisoft.sffx.monitor.cache.ICache;
import com.thunisoft.sffx.monitor.cache.ShareCache;

@Service
public class AppService {

    @SuppressWarnings("unchecked")
    public List<App> getAppList() {

        ICache cache = ShareCache.getInstance();

        List<App> list = (List<App>) cache.getCacheData(Consts.APP_LIST);
        return list;
    }

}
