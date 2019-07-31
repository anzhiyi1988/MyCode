package orz.an.design.pattern.memento;

import java.util.Map;

public class Memento {


    private String state;


    private Map<String , Object> sateMap;


    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
