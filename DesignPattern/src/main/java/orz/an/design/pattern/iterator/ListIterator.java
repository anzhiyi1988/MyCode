package orz.an.design.pattern.iterator;

import java.util.Iterator;
import java.util.List;

public class ListIterator implements Iterator {


    List list;
    int index = 0;


    public ListIterator(List list) {
        this.list = list;
    }


    @Override
    public boolean hasNext() {
        if (index >= list.size()) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return list.get(index++);
    }
}
