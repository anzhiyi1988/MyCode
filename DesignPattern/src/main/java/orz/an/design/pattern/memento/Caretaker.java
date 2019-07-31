package orz.an.design.pattern.memento;

import java.util.Map;

public class Caretaker {


    private Memento memento;


    private Map<String , Memento> memMap;



    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }


}
