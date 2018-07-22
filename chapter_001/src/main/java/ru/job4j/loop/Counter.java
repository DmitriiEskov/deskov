package ru.job4j.loop;

/**
 * Counter.
 *
 * @author Dmitrii Eskov (dmitryhope@yahoo.com)
 * @version 1.0
 * @since 19.07.2018
 */
public class Counter {
    private int sum;

    /**
     * Calculating a sum of even numbers from an interval.
     * @param start - start of an interval
     * @param finish - end of an interval
     * @return sum - sum of even numbers
     */
    public int add(int start, int finish) {
        for (int index = start; index <= finish; index++) {
            if (index % 2 == 0) {
                this.sum = this.sum + index;
            }
        }
        return this.sum;
    }
}