package orz.anzhy.test;

import org.junit.Test;
import orz.anzhy.cut.DynamicBottomUp;
import orz.anzhy.cut.DynamicBottomUpExtended;
import orz.anzhy.cut.Recursion;
import orz.anzhy.search.BinarySearch;
import orz.anzhy.selector.Greedy;
import orz.anzhy.sort.*;

import java.util.Arrays;

public class MyTest {


    @Test
    public void testMerge() {
        int arr[] = {8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Merge sort = new Merge();
        sort.sort(arr, 0, 7);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testBubble() {
        int arr[] = {8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Bubble sort = new Bubble();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSelection() {
        int arr[] = {8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Selection sort = new Selection();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testInsertion() {
        int arr[] = {5, 2, 9, 4, 7, 6, 1, 3, 8};
        System.out.println(Arrays.toString(arr));
        Insertion sort = new Insertion();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testHeap() {
        int arr[] = {5, 2, 9, 4, 7, 6, 1, 3, 8};
        System.out.println(Arrays.toString(arr));
        Heap sort = new Heap();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testQuick() {
        int arr[] = {5, 2, 9, 4, 7, 6, 1, 3, 8};
        System.out.println(Arrays.toString(arr));
        Quick sort = new Quick();
        sort.sort(arr, 0, 8);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testCutRecursion() {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        Recursion recursion = new Recursion();
        System.out.println(recursion.cut(price, 5));

    }

    @Test
    public void testCutDynamicBottomUp() {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        DynamicBottomUp dynamicBottomUp = new DynamicBottomUp();
        System.out.println(dynamicBottomUp.cut(price, 5));

    }


    @Test
    public void testCutDynamicBottomUpEx() {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        DynamicBottomUpExtended dynamicBottomUp = new DynamicBottomUpExtended();
        System.out.println(dynamicBottomUp.cut(price, 5));

    }


    @Test
    public void testSelectorGreedy() {
        int[] s = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {4, 5, 6, 7, 9, 9, 10, 11, 14, 16};
        Greedy greedy = new Greedy();
        System.out.println(greedy.greedy(s, f));

    }


    @Test
    public void testBinarySearch() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(BinarySearch.search(arr, 0));
    }
}
