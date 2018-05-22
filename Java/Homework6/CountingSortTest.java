import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;

import static junit.framework.TestCase.assertEquals;

public class CountingSortTest {
    @Test
    public void countingSortTest() {

        int[] Empty = {};
        CountingSort.sort(Empty);
        assertEquals(Empty.length, 0);

        int[] array1 = {5, 3, 6, 1, 9, 7, 1, 6, 8,};
        CountingSort.sort(array1);
        assertEquals(array1[0], 1);
        assertEquals(array1[1], 1);
        assertEquals(array1[8], 9);
        assertEquals(array1[5], 6);
        assertEquals(array1[3], 5);


        int[] array2 = {6, 2, 3, 5, 8, 3};
        CountingSort.sort(array2);
        assertEquals(array2[0], 2);
        assertEquals(array2[1], 3);
        assertEquals(array2[2], 3);
        assertEquals(array2[3], 5);
        assertEquals(array2[4], 6);
        assertEquals(array2[5], 8);

        int[] array3 = {1,2,3,4};
        CountingSort.sort(array3);
        assertEquals(array3[0], 1);
        assertEquals(array3[3], 4);
        assertEquals(array3[1], 2);
        assertEquals(array3[2], 3);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CountingSortTest.class);

        for(Failure failure : result.getFailures())
            System.out.println(failure.toString());

        System.out.println(result.wasSuccessful());
    }
}
