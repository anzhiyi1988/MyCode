package orz.an.design.pattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListObjects {

    private List<Object> objects = new ArrayList();

    public void add(Object object) {
        objects.add(object);
    }

    public List getObjects() {
        return objects;
    }


    public Iterator iterator() {
        return new ListIterator(objects);
    }

}
