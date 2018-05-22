// Michael Iacona

import java.util.List;
import java.util.ArrayList;

public class PrimeFinder implements Runnable {

    //private thread runner;

    private Integer start;
    private Integer end;
    private List<Integer> primes;

    //long Start;
    //long End;

    public PrimeFinder(Integer startNum, Integer endNum) {

        //  Constructs a PrimeFinder

        this.start = startNum;
        this.end = endNum;
        this.primes = new ArrayList< >();

    }

    public List<Integer> getPrimesList() {

        // / Returns the value of the attribute primes

        return this.primes;
    }

    public boolean isPrime(long n) {

        //Determines whether its arg is prime or not

        if (n > 2 && (n & 1) == 0){
            return false;}
        for (int i = 3; i * i <= n; i += 2){
            if (n % i == 0)
                return false;}
        return true;
    }

    public void run()
    {
        for( int i = this.start; i <= this.end; ++i ) {
            if( isPrime(i) ) {
                primes.add(i);
            }
        }
    }
}