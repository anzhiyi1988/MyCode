package orz.anzhy.sffx.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import orz.anzhy.sffx.monitor.Consts;
import orz.anzhy.sffx.monitor.bean.Server;
import orz.anzhy.sffx.monitor.cache.ICache;
import orz.anzhy.sffx.monitor.cache.ShareCache;

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
