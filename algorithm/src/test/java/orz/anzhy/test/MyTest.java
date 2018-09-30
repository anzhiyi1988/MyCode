package orz.anzhy.test;

import org.junit.Test;
import orz.anzhy.sort.Merge;

import java.util.Arrays;

public class MyTest {


    @Test
    public void testMerge() {
        int arr[] = {8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Merge merge = new Merge();
        merge.sort(arr, 0, 7);
        System.out.println(Arrays.toString(arr));
    }
}
