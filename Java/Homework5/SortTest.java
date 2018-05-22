import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.Assert.assertEquals;

public class SortTest {
    @Test
    public void testSort(){
        Integer[] arr1 = {50, 20, 2000, 3020, 7, 1234, 123456, 70};
        Sort.sort(arr1);
        assertEquals(arr1, new Integer[] {7, 20, 50, 70 ,1234, 2000, 3020, 123456});

        Integer[] arr2 = {15};
        Sort.sort(arr2);
        assertEquals(arr2, new Integer[] {15} );


        Character[] arr3 = new Character[] {'z', 'r', 'c', 'b', 'e'};
        Sort.sort(arr3);
        assertEquals( array3, new Character[] {'b', 'c', 'e', 'r', 'z'});

        Character[] arr4 = new Character[] {'x'};
        Sort.sort(arr4);
        assertEquals(arr4, new Character[] {'x'});

        Integer[] arr5 = {};
        Sort.sort(arr5);
        assertEquals(arr5 , new Integer[] {});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SortTest.class);
        for(Failure failure : result.getFailures())
            System.out.println(failure.toString());
        System.out.println(result.wasSuccessful());
    }
}
