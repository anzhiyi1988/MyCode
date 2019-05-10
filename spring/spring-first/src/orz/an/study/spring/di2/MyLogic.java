package orz.an.study.spring.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyLogic {

@Qualifier("chService")
    @Autowired
    private IService service;


    public void process() {
        service.serv();
    }


}
