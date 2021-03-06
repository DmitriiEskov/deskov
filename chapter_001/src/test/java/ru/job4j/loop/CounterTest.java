package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Counter class.
 *
 * @author Dmitrii Eskov (dmitryhope@yahoo.com)
 * @version $Id$
 * @since 21.07.2018
 */
public class CounterTest {

    /**
     * Test for calculating a sum of even numbers(1 - 10).
     */
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}