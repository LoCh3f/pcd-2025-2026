package pcd.lab01.ex01;

import java.util.Arrays;

public class MySortingThread extends Thread{
    private final int[] toSort;
    MySortingThread(int[] v, String myName) {
        super(myName);
        this.toSort = v;
    }

    public void run() {
        Arrays.sort(toSort);
        System.out.println("Sub Array Sorted by" + this.getName());
    }
    public int[] getSorted() {
        return this.toSort;
    }
}
