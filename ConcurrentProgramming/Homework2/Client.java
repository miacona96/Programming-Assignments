package Assignment2;

import java.util.*;

public class Client{

    public int id;
    private List<Exercise> routine;


    public Client(int id){
        this.id = id;
    }

    public void addExercise( Exercise e ){
        routine.add(e);
    }

    public static Client generateRandom(int id){

        Client client = new Client(id);
        client.routine = new Vector<Exercise>();
        Random randomize = new Random();

        //Client will perform 15-20 exercises
        for( int i = 15 + randomize.nextInt(5) + 1; i > 0; i-- ){

            //Number of plates for each exercise between 0 and 10
            Map<WeightPlateSize, Integer> temp = new HashMap<WeightPlateSize, Integer>();

            temp.put( WeightPlateSize.SMALL_3KG, randomize.nextInt(10) );
            temp.put( WeightPlateSize.MEDIUM_5KG, randomize.nextInt(10) );
            temp.put( WeightPlateSize.LARGE_10KG, randomize.nextInt(10) );

            client.addExercise( Exercise.generateRandom( temp ) );

        }
        return client;
    }

    public List<Exercise> getRoutine(){
        return routine;
    }

    public void display(){

        System.out.print( "Client ID Number: " );
        System.out.println( id );

        for( Exercise i : routine ){
            i.display();
        }
    }
}
