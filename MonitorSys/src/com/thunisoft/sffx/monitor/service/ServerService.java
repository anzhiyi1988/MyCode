package com.thunisoft.sffx.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thunisoft.sffx.monitor.Consts;
import com.thunisoft.sffx.monitor.bean.Server;
import com.thunisoft.sffx.monitor.cache.ICache;
import com.thunisoft.sffx.monitor.cache.ShareCache;

@Service
public class ServerService {

    @SuppressWarnings("unchecked")
    public List<Server> getServList() {

        ICache cache = ShareCache.getInstance();
        List<Server> list = (List<Server>) cache
                .getCacheData(Consts.SERVER_LIST);
        return list;
    }

}
