import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

public class TreapTest {
    @Test
    public void testAdd() {
        Treap<Integer> testTreap = new Treap<>();
        boolean test1 = testTreap.add(6,12);
        boolean test2 = testTreap.add(14,7);
        boolean test3 = testTreap.add(8,4);
        boolean test4 = testTreap.add(14);

        assertEquals(test1,true);
        assertEquals(test2,true);
        assertEquals(test3,true);
        assertEquals(test4,false);
    }

    @Test
    public void testDelete() {
        Treap<Integer> testTreap = new Treap<>();
        testTreap.add(4, 11);
        testTreap.add(11, 10);
        testTreap.add(7, 2);

        boolean delTest1 = testTreap.delete(7);
        boolean delTest2 = testTreap.delete(10);
        boolean delTest3 = testTreap.delete(7);

        assertEquals(del1Test,true);
        assertEquals(delTest2,false);
        assertEquals(delTest3, false);
    }

    @Test
    public void testFind() {
        Treap<Integer> testTreap = new Treap<>();
        testTreap.add(4,9);
        testTreap.add(7,12);
        testTreap.add(3,4);

        assertEquals(testTreap.find(4),(Integer) 4);
        assertEquals(testTreap.find(12),(Integer) 12);
        assertEquals(testTreap.find(32),null);
    }

    @Test
    public void testToString() {
        Treap<Integer> testTreap = new Treap<>();
        assertEquals(testTreap.toString(), "null\n" );
        testTreap.add(4,25);
        assertEquals(testTreap.toString(), "(key=4, priority=25)\n null\n null\n" );
        testTreap.add(10,15);
        assertEquals(testTreap.toString(), "(key=4, priority=25)\n  null\n  (key=10, priority=15)\n null\n null\n" );
    }

    public static void main(String[] args) {
        Result res = JUnitCore.runClasses(TreapTest.class);
        for(Failure f : res.getFailures())
            System.out.println(f.toString());
        System.out.println(res.wasSuccessful());
    }
}
