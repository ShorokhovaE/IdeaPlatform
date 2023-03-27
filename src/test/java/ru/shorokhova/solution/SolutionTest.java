package ru.shorokhova.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SolutionTest {

    @Test
    public void timeOnWayInMinutesTest1() {
        LocalDateTime departureDateTime = LocalDateTime.of(2023, 2, 1, 12, 15);
        LocalDateTime arrivalDateTime = LocalDateTime.of(2023, 2, 1, 17, 15);
        Assertions.assertEquals(5 * 60, Solution.timeOnWayInMinutes(departureDateTime, arrivalDateTime));
    }

    @Test
    public void timeOnWayInMinutesTest2() {
        LocalDateTime departureDateTime = LocalDateTime.of(2023, 2, 1, 12, 15);
        LocalDateTime arrivalDateTime = LocalDateTime.of(2023, 2, 2, 17, 15);
        Assertions.assertEquals(29 * 60, Solution.timeOnWayInMinutes(departureDateTime, arrivalDateTime));
    }

    @Test
    public void timeOnWayInMinutesTestForException() {
        LocalDateTime departureDateTime = LocalDateTime.of(2023, 2, 2, 12, 15);
        LocalDateTime arrivalDateTime = LocalDateTime.of(2023, 2, 1, 17, 15);
        Assertions.assertThrows(DateTimeException.class, () -> Solution.timeOnWayInMinutes(departureDateTime, arrivalDateTime));
    }

    @Test
    public void averageTimeOnWayInMinutesTest() {
        List<Integer> minutesOnWayLis = new ArrayList<>();
        minutesOnWayLis.add(100);
        minutesOnWayLis.add(200);
        minutesOnWayLis.add(300);
        Assertions.assertEquals(200, Solution.averageTimeOnWayInMinutes(minutesOnWayLis));
    }

    @Test
    public void findPercentileTest() {
        List<Integer> minutesOnWayLis = new ArrayList<>();
        minutesOnWayLis.add(300);
        minutesOnWayLis.add(200);
        minutesOnWayLis.add(300);
        minutesOnWayLis.add(100);
        Assertions.assertEquals(300, Solution.findPercentile(minutesOnWayLis, 90));
    }

}
