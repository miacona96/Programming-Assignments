package com.company;

import org.junit.runner.Result;
import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;
import com.company.IDLList;


import static org.junit.Assert.assertEquals;

public class IDLListTest {
    @Test

    public void IDLListAddTest() {
        IDLList<String> testList = new IDLList<>();
        testList.append( "i2" );
        testList.add( "i5" );
        testList.add( "i8" );
        testList.add( "i9" );

        testList.add( 0, "i1" );
        testList.add( 7, "i7" );
        testList.add( 18, "i12" );

        assertEquals( testList.toString(), "[ i1, i2, i5, i7 i8, i9, i12 ]" );
    }

    @Test
    public void IDLListGetTest() {
        IDLList<String> testList = new IDLList<>();
        testList.append( "a" );
        testList.append( "b" );
        testList.append( "c" );

        assertEquals( testList.get(3), "b" );
        assertEquals( testList.get(1), "c" );

        testList.append( "x" );
        testList.add( 1, "z" );

        assertEquals( testList.get(1), "z" );
        assertEquals( testList.getLast(), "x" );
    }

    @Test
    public void IDLListRemoveTest() {
        IDLList<String> testList = new IDLList<>();

        testList.append( "0" ); testList.append( "1" ); testList.append( "2" ); testList.append( "3" );
        testList.remove(1);
        assertEquals( testList.toString(), "[0, 2, 3]" );

        testList.remove();
        assertEquals( testList.toString(), "[2, 3]" );

        testList.removeLast();
        assertEquals( testList.toString(), "[2]" );

        testList.append( "3" ); testList.append( "4" ); testList.append( "5" );
        testList.remove( "4" );
        assertEquals( testList.toString(), "[2, 3, 5]" );
    }

    @Test
    public void IDLListSizeTest() {
        IDLList<String> testList = new IDLList<>();
        testList.append("I" );
        testList.append("see");
        testList.append("you");
        testList.append("pee");


        assertEquals(testList.size(),3);
    }

    public static void main(String[] args) {
        Result r= JUnitCore.runClasses( IDLListTest.class );
        for( Failure fail : r.getFailures() ) {
            System.out.println( fail.toString() );
        }System.out.println( r.wasSuccessful() );
    }
}

