package Assignment2;

import java.util.*;

public class Exercise{

    public ApparatusType at;
    public Map<WeightPlateSize,Integer> weight;
    public long duration;

    static long startTime = System.currentTimeMillis();
    static long endTime = System.currentTimeMillis();

    public Exercise(ApparatusType at, Map<WeightPlateSize, Integer> weight, long duration){

        this.at = at;
        this.weight= weight;
        this.duration = duration;

    }

    public static Exercise generateRandom( Map<WeightPlateSize, Integer> nPlates ){

        Random randomize = new Random();
        Exercise set = new Exercise( ApparatusType.values()
                [ randomize.nextInt( ApparatusType.values().length ) ],
                nPlates , (endTime - startTime) );

        return set;

    }

    public void display(){

        Set<WeightPlateSize> weightKeys =  weight.keySet();
        System.out.print( "(" );
        System.out.print( at );
        System.out.print( ", " );
        System.out.print("[");

        for( WeightPlateSize i : weightKeys ){
            System.out.print( "(");
            System.out.print( i );
            System.out.print( "," );
            System.out.print( weight.get(i) );
            System.out.print( ")");
            System.out.print( ", " );
        }

        System.out.print( "duration:" );
        //Prevent the output of a duration 0 with + 1
        System.out.print( duration + 1 );
        System.out.print("ms");
        System.out.println("]");

    }
}