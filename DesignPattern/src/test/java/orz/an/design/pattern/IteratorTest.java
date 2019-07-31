package orz.an.design.pattern;

import org.junit.Test;
import orz.an.design.pattern.iterator.ArrayObjects;
import orz.an.design.pattern.iterator.ListObjects;

import java.util.Iterator;
import java.util.List;

public class IteratorTest {


    @Test
    public void testNoIterator() {

        ArrayObjects aOobjects = new ArrayObjects(3);
        aOobjects.add("object1");
        aOobjects.add("object2");
        aOobjects.add("object3");


        Object[] objs = aOobjects.getObjects();
        for (int i = 0; i < objs.length; i++) {
            System.out.println(objs[i]);

        }


        ListObjects lObjects = new ListObjects();
        lObjects.add("objectA");
        lObjects.add("objectB");
        lObjects.add("objectC");

        List<Object> list = lObjects.getObjects();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


    }


    @Test
    public void testIterator() {

        ArrayObjects aOobjects = new ArrayObjects(3);
        aOobjects.add("object1");
        aOobjects.add("object2");
        aOobjects.add("object3");


        Iterator iterator = aOobjects.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        ListObjects lObjects = new ListObjects();
        lObjects.add("objectA");
        lObjects.add("objectB");
        lObjects.add("objectC");

        iterator = lObjects.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


}
