/** Michael Iacona
    I pledge my honor that I abided by the Steven's honor systems
    6/5/17
 **/

import java.util.List;
import java.util.ArrayList;

public class AssignmentOne {

    public List<Integer> lprimes( List<Integer[]> intervals ) {

        if( intervals == null )
            return null;

        List<PrimeFinder> Finder = new ArrayList<PrimeFinder>();
        List<Integer> Primes = new ArrayList<Integer>();
        List<Thread> Threads = new ArrayList<Thread>();

        for( Integer[] range : intervals ) {

            PrimeFinder interval = new PrimeFinder( range[0], range[1] );
            Finder.add( interval );
            Thread thread = new Thread( interval );
            thread.start();
            Threads.add( thread );
        }

        for( Thread x : Threads ) {

            try {
                //check to see if you can join thread
                x.join();
            }

            catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        for( PrimeFinder primes : Finder ) {

            Primes.addAll( primes.getPrimesList() );

        }
        return Primes;
    }


    public static void main(String[] args) {


        // Check for correct inputs
        if( args.length <= 1 ) {
            System.out.println( "Error: \nUsage: Incorrect Input" );
            return;
        }

        if( (args.length % 2) != 0 ) {
            System.out.println( "Error: Args must be in pairs");
            return;
        }

        List<Integer[]> interval = new ArrayList<>();

        for( int i = 0; i <  args.length -1 ; i++  ) {

            Integer start = Integer.valueOf( args[i] );
            Integer end = Integer.valueOf( args[i +1] );

            Integer[] pair = { start,end };
            interval.add(pair);

        }


        //Prime Numbers found

        AssignmentOne Assignment = new AssignmentOne();

        List<Integer> attributePrimes = Assignment.lprimes( interval );

        System.out.println( "Primes:\n" + attributePrimes );
    }

}