package orz.an.design.pattern.memento;

public class Originator {


    private String state;

    public Memento createMemento() {
        return new Memento(this.state);
    }

    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void print() {

        System.out.println(this.state);
    }
}
