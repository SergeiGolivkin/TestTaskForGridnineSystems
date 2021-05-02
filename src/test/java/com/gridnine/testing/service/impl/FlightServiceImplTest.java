package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.FlightService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightServiceImplTest {

    private final FlightService flightService = new FlightServiceImpl();

    @Test
    public void minusDepartureTimeBeforeCurrentTime() {
        List<Flight> testFlights = createTestFlights();
        flightService.minusDepartureTimeBeforeCurrentTime(testFlights);
        Assert.assertFalse(testFlights.isEmpty());
        Assert.assertEquals(2, testFlights.size());
        Assert.assertTrue(testFlights.stream()
                .flatMap(flight -> flight.getSegments().stream())
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())));
    }

    private List<Flight> createTestFlights() {
        List<Flight> testFlights = new ArrayList<>();
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.APRIL, 30, 12, 0),
                LocalDateTime.of(2021, Month.APRIL, 30, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 4, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 4, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 6, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 6, 14, 0)));
        return testFlights;
    }

    @Test
    public void minusFlightArrivalDateBeforeDepartureDate() {
    }

    @Test
    public void minusFlightsWithMoreThanTwoHoursOnTheGround() {

    }
}