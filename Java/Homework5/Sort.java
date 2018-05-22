import java.util.*;

public class Sort{
    /**represents the interval */
    private static class Interval{
        private int upper;
        private int lower;

        public Interval(int lower, int upper){
            this.lower = lower;
            this.upper = upper;
        }
        public int getUpper() {
            return upper;
        }
        public int getLower(){
            return lower;
        }
        public int hashCode(){
            return getUpper() * getUpper()+getLower();
        }
        public boolean equals(Object o){
            return o instanceof Interval && ((Interval) o).getLower() == this.getLower() && ((Interval) o).getUpper() == this.getUpper();
        }
    }
    /** Lamport iterative version of quicksort
     * @param array array to sort
     */
    public static <T extends Comparable<T>> void sort(T[] array){
        ArrayList<Interval> bounds = new ArrayList<>();
        bounds.add new Interval(0,array.length-1));

        while(bounds.size() > 0){
            Interval value = bounds.remove(0);
            if( value.upper - value.lower >= 1) {
                int piv = partition(array, value.lower, value.upper);
                bounds.add(new Interval(piv+1, value.upper));
                bounds.add(new Interval(value.lower, piv-1));
            }
        }
    }
    /** swaps two elements in the array
     * @param the second index of the second element
     * @param the array that gets swapped in
     * @param the first index of the first element */
    private static <T extends Comparable<T>> void swap(T[] array, int first, int second){
        if(first < 0 || second < 0 || first >= array.length || second >= array.length) return;
        T temp = array[ first ];
        array[first] = array[second];
        array[second] = temp;
    }
    /** Median-of-three partition
     * @param array to partition
     * @param upper bound of the range
     * @param lower bound of the range
     * @return position */
    private static <T extends Comparable<T>> int partition(T[] array, int lowEnd, int highEnd){
        int piv = (int) Math.floor((lowEnd+highEnd+1)/2);
        int up = -1;
        int down = -1;

        if(array[lowEnd].compareTo(array[piv]) > 0)
            swap(array, lowEnd, piv);
        if(array[lowEnd].compareTo(array[piv]) > 0)
            swap(array, lowEnd, piv);
        if(array[piv].compareTo(array[highEnd]) > 0)
            swap(array, piv, highEnd);

        while(true){
            for(int i = lowEnd; i < highEnd+1; i++){
                if(i == piv || array[ i ].compareTo(array[piv]) > 0){
                    up = i; break;
                }
            }
            for(int i = highEnd; i >= lowEnd; i--){
                if(i == piv || array[i].compareTo(array[piv]) <= 0){
                    down = i; break;
                }
            }
            if(up >= down || up == -1 || down == -1)
                break;
            if(up == piv)
                piv = down;
            else if(down == piv)
                piv = up;
            swap(array, up, down);
        }
        return piv;
    }
}
