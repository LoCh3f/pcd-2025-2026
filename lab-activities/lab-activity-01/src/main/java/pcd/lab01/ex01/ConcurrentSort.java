package pcd.lab01.ex01;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class ConcurrentSort {

    static final int VECTOR_SIZE = 400_000_000;

    public static void main(String[] args) throws Exception{

        log("Generating array...");
        var v = genArray(VECTOR_SIZE);

        log("Array generated.");
        log("Sorting  sub arrays (" + VECTOR_SIZE + " elements)...");
        var sorter1 = new MySortingThread(Arrays.copyOfRange(v,0,v.length/2),"Sorter 1");
        var sorter2 = new MySortingThread(Arrays.copyOfRange(v,v.length/2,v.length),"Sorter 2");

        long t0 = System.nanoTime();
        sorter1.start();
        sorter2.start();

        sorter1.join(); // wait for this
        sorter2.join(); // wait for this
        long t1 = System.nanoTime();
        log("Done. Time elapsed: " + ((t1 - t0) / 1000000) + " ms");


        System.arraycopy(sorter1.getSorted(), 0, v, 0, VECTOR_SIZE/2);
        System.arraycopy(sorter2.getSorted(), 0, v, VECTOR_SIZE/2, VECTOR_SIZE/2);
        Arrays.sort(v);
        long t3 = System.nanoTime();
        log("Completed Task in:" + ((t3 - t0) / 1000000) + " ms");
        //dumpArray(v);
    }


    private static int[] genArray(int n) {
        Random gen = new Random(System.currentTimeMillis());
        var v = new int[n];
        for (int i = 0; i < v.length; i++) {
            v[i] = gen.nextInt();
        }
        return v;
    }

    private static void dumpArray(int[] v) {
        for (var l:  v) {
            System.out.print(l + " ");
        }
        System.out.println();
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}
