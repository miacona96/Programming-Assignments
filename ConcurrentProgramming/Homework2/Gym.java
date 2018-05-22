package Assignment2;

/** Michael Iacona
    6/21/2017
 **/

import java.util.concurrent.*;
import java.util.*;


public class Gym implements Runnable {

    int i;
    //various semaphores - declaration omitted
    private final Semaphore checkPlates = new Semaphore(1);
    private final Semaphore Small_3KG = new Semaphore(110);
    private final Semaphore Medium_5KG = new Semaphore(90);
    private final Semaphore Large_10KG = new Semaphore(75);

    private final Semaphore clientMutex = new Semaphore(1);
    private final Semaphore displayMutex = new Semaphore(1);
    private Map<ApparatusType, Semaphore> machineMutex = new HashMap<ApparatusType, Semaphore>();

    private static final int GYM_SIZE = 30;
    private static final int GYM_REGISTERED_CLIENTS = 10000;
    private Map<WeightPlateSize, Integer> noOfWeightPlates;

    private Set<Integer> clients; //for generating fresh client ids
    private ExecutorService executor = Executors.newFixedThreadPool( GYM_SIZE );

    //create gym
    // There is 5 of each machine available
    public Gym() {

        noOfWeightPlates = new HashMap<WeightPlateSize, Integer>();

        noOfWeightPlates.put( WeightPlateSize.SMALL_3KG, 110 );
        noOfWeightPlates.put( WeightPlateSize.MEDIUM_5KG, 90 );
        noOfWeightPlates.put( WeightPlateSize.LARGE_10KG, 75 );

        for ( ApparatusType i : ApparatusType.values() ) {
            machineMutex.put( i, new Semaphore(5) );
        }

        clients = new HashSet< >( GYM_REGISTERED_CLIENTS );

    }
    public void run(){
        for( i = 0; i < GYM_REGISTERED_CLIENTS; i++ ){
            executor.execute(() -> {
                try{
                    clientMutex.acquire();
                }
                catch( InterruptedException e ){
                    e.printStackTrace();
                }

                //Critical Section for Id's
                Client ID = Client.generateRandom( clients.size() );
                clients.add( ID.id );
                clientMutex.release();
                //Critical Section

                for( Exercise x : ID.getRoutine() ){
                    while(true){
                        try{
                            machineMutex.get( x.at ).acquire();
                        }
                        catch( InterruptedException e ){
                            e.printStackTrace();
                        }
                        try{
                            checkPlates.acquire();
                        }
                        catch( InterruptedException e ){
                            e.printStackTrace();
                        }
                        //Critical Section ( Check for plates )
                        if( noOfWeightPlates.get( WeightPlateSize.SMALL_3KG ) < x.weight.get( WeightPlateSize.SMALL_3KG )
                                || noOfWeightPlates.get( WeightPlateSize.MEDIUM_5KG ) < x.weight.get( WeightPlateSize.MEDIUM_5KG )
                                || noOfWeightPlates.get( WeightPlateSize.LARGE_10KG ) < x.weight.get( WeightPlateSize.LARGE_10KG ) ){

                            machineMutex.get( x.at ).release();
                            checkPlates.release();

                        }
                        else{
                            try{
                                Small_3KG.acquire();
                                Medium_5KG.acquire();
                                Large_10KG.acquire();
                            }
                            catch( InterruptedException e ) {
                                e.printStackTrace();
                            }

                            //Critical section ( take available plates )
                            noOfWeightPlates.put( WeightPlateSize.SMALL_3KG, noOfWeightPlates.get( WeightPlateSize.SMALL_3KG ) - x.weight.get( WeightPlateSize.SMALL_3KG ) );
                            noOfWeightPlates.put( WeightPlateSize.MEDIUM_5KG, noOfWeightPlates.get( WeightPlateSize.MEDIUM_5KG ) - x.weight.get( WeightPlateSize.MEDIUM_5KG ) );
                            noOfWeightPlates.put( WeightPlateSize.LARGE_10KG, noOfWeightPlates.get( WeightPlateSize.LARGE_10KG ) - x.weight.get( WeightPlateSize.LARGE_10KG ) );
                            //Critical Section

                            Small_3KG.release();
                            Medium_5KG.release();
                            Large_10KG.release();
                            checkPlates.release();

                            try{
                                Thread.sleep( x.duration );
                            }
                            catch( InterruptedException e ){
                                e.printStackTrace();
                            }

                            machineMutex.get( x.at ).release();

                            try {
                                Small_3KG.acquire();
                                Medium_5KG.acquire();
                                Large_10KG.acquire();
                            }

                            catch( InterruptedException e ) {
                                e.printStackTrace();
                            }

                            noOfWeightPlates.put( WeightPlateSize.SMALL_3KG, noOfWeightPlates.get( WeightPlateSize.SMALL_3KG ) + x.weight.get(WeightPlateSize.SMALL_3KG ));
                            noOfWeightPlates.put( WeightPlateSize.MEDIUM_5KG, noOfWeightPlates.get( WeightPlateSize.MEDIUM_5KG ) + x.weight.get(WeightPlateSize.MEDIUM_5KG ));
                            noOfWeightPlates.put( WeightPlateSize.LARGE_10KG, noOfWeightPlates.get( WeightPlateSize.LARGE_10KG ) + x.weight.get(WeightPlateSize.LARGE_10KG ));

                            Small_3KG.release();
                            Medium_5KG.release();
                            Large_10KG.release();

                            break;
                        }
                    }
                }
                try{
                    displayMutex.acquire();
                }
                catch( InterruptedException e ){
                    e.printStackTrace();
                }
                ID.display();
                displayMutex.release();
            }
            );
        }

        //Stop accepting new tasks
        executor.shutdown();

    }
}