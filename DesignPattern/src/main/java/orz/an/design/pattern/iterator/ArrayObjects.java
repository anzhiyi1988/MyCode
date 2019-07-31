package orz.an.design.pattern.iterator;

import java.util.Iterator;

public class ArrayObjects {

    int MAX_NUM = 0;
    private Object[] objects;
    int index = 0;

    public ArrayObjects(int num) {
        MAX_NUM = num;
        objects = new Object[num];
    }


    public void add(Object object) {
        if (index >= MAX_NUM) {
            System.out.println("array is full");
        } else {
            objects[index] = object;
            index++;
        }


    }

    public Object[] getObjects() {
        return objects;
    }

    public Iterator iterator() {
        return new ArrayIterator(objects);
    }

}
