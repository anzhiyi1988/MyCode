package orz.an.study.spring.di;

public class MyLogic {


    private IService service;

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }


    public void process() {
        service.serv();
    }


}
