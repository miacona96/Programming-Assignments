//Would like to use this assignment for my homework grade

public class CountingSort {

    /** Counting Sort for Array of int
     * @param A input array
     * @param B output array
     * @param k largest int in A */
    private static void COUNTING_SORT(int[] A, int[] B, int k) {
        int[] C = new int[k+1];

        for(int i = 0; i < A.length; i++) {
            C[A[i]]++;
        }
        for(int i = 1; i <= k; i++){
            C[i] += C[ i-1 ];
        }
        for(int i = A.length - 1; i >= 0; i--) {
            B[C[ A[ i ]] - 1 ] = A[i];
            C[A[ i ]]--;
        }
    }

    /**Counting sort
     * @param A array */
    public static void sort(int[] A) {
        if(A.length == 0 || A.length == 1) return;
        int k = -1;
        for(int z : A){
            if(z > k) k = z;
        }
        COUNTING_SORT(A.clone(), A, k);
    }
}
