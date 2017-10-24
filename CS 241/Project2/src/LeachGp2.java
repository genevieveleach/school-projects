/**
 * Created by Genevieve on 11/16/2016.
 */

import java.util.Arrays;

public class LeachGp2 {

    private static int swapAvg;
    private static int swapCount;
    private static boolean print;
    private static boolean avg;
    private static int[] orig;

    public static void main(String[] args) {
        print = true;
        avg = false;
        int[] oneInput = {12, 19, 26, 13, 20, 27, 14, 21, 28, 15, 22, 29, 16, 23, 10, 17, 24, 11, 18, 25};
        orig = Arrays.copyOf(oneInput, oneInput.length);
        System.out.println("Part 1:");
        System.out.println();
        heapSort(oneInput,oneInput.length);
        System.out.println();
        System.out.println("Original Array: \n" + Arrays.toString(orig));
        System.out.println("Sorted Array: \n" + Arrays.toString(oneInput));
        if(avg) {
            System.out.println("Number of Swaps: " + swapCount);
        }
        System.out.println();

        print = false;
        avg = true;
        int[] twoInput = new int[100];
        System.out.println("Part 2:");
        System.out.println();
        for(int i = 0; i < 20; i++) {
            System.out.println("Sort #" + (i+1) + ": ");
            genTwoInput(twoInput);
            swapCount = 0;
            heapSort(twoInput,twoInput.length);
            System.out.println("Number of swaps: " + swapCount);
        }
        System.out.println();
        System.out.println("Average number of swaps: " + swapAvg/20);
    }

    private static void genTwoInput(int[] twoInput) {
        for (int i = 0; i < 100; i++) {
            twoInput[i] = 100 + (int) (Math.random() * 800);
        }
    }

    public static void reheap(int[]in, int i, int max) {
        boolean done = false;
        int orphan = in[i];
        int left = 2*i+1;
        while(!done && (left<=max)) {
            int larger = left;
            int right = left+1;
            if(right<=max && in[right] > in[larger]) {
                larger = right;
            }
            if(orphan < in[larger]) {
                in[i] = in[larger];
                i = larger;
                left = 2*i+1;
            } else {
                done = true;
            }
            in[i] = orphan;
        }
    }

    public static void heapSort(int[] in, int n) {
        for(int i = n/2 -1; i>=0; i--) {
            reheap(in, i, n-1);
        }
        swap(in, 0, n-1);
        for(int max = n-2; max>0; max--) {
            reheap(in, 0, max);
            swap(in, 0, max);
        }
    }

    public static void swap(int in[], int i, int j) {
        int temp = in[i];
        in[i] = in[j];
        in[j] = temp;
        if(avg) {
            swapAvg++;
            swapCount++;
        }
        if(print) {
            System.out.println("Original Array: \n" + Arrays.toString(orig));
            System.out.println("After Swap: \n" + Arrays.toString(in));
        }
    }
}
