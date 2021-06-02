/**
 * @projectName Test
 * @package orz.anzhy.test
 * @className orz.anzhy.test.SerializableTest
 * @copyright anzhy.
 */
package orz.anzhy.test;

import org.junit.Test;
import orz.anzhy.serializable.Student;

import java.io.*;

/**
 * SerializableTest
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/2/23 10:18
 */
public class SerializableTest {

    @Test
    public void writeObjectTest() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("f:/SerializableTest/test.out"));
        Student student = new Student();
        student.setAge(18);
        student.setName("anzhy");
        objectOutputStream.writeObject(student);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Test
    public void readObjectTest() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("f:/SerializableTest/test.out"));
        Student student = (Student) objectInputStream.readObject();
        System.out.println(student.getName());
        System.out.println(student.getAge());
        objectInputStream.close();
    }

}
