package orz.an.design.pattern.iterator;

import java.util.Iterator;

public class ArrayIterator implements Iterator {

    Object[] arr;

    int index = 0;

    public ArrayIterator(Object[] arr) {
        this.arr = arr;
    }


    @Override
    public boolean hasNext() {

        if (index >= arr.length) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return arr[index++];
    }
}
