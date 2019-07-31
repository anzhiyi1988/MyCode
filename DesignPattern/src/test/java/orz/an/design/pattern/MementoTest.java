package orz.an.design.pattern;

import org.junit.Test;
import orz.an.design.pattern.memento.Caretaker;
import orz.an.design.pattern.memento.Originator;

public class MementoTest {


    @Test
    public void mementoTest() {
        Originator originator = new Originator();
        originator.setState("1");
        originator.print();


        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.setState("2");
        originator.print();

        originator.restoreMemento(caretaker.getMemento());

        originator.print();


    }
}
