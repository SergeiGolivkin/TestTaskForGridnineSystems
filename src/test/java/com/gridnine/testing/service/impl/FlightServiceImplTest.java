package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FlightServiceImplTest {

    private final FlightService flightService = new FlightServiceImpl();

    @Test
    public void minusDepartureTimeBeforeCurrentTime() {

        List<Flight> testFlights = createTestFlights();
        List<Flight> result = flightService.minusDepartureTimeBeforeCurrentTime(testFlights);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.stream()
                .flatMap(flight -> flight.getSegments().stream())
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())));
    }

    private List<Flight> createTestFlights() {

        List<Flight> testFlights = new ArrayList<>();
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.AUGUST, 30, 12, 0),
                LocalDateTime.of(2021, Month.AUGUST, 30, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.APRIL, 30, 12, 0),
                LocalDateTime.of(2021, Month.APRIL, 30, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 4, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 4, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 6, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 6, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MARCH, 30, 12, 0),
                LocalDateTime.of(2021, Month.MARCH, 30, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 3, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 3, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 3, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 2, 14, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 15, 12, 0),
                LocalDateTime.of(2021, Month.MAY, 15, 14, 0), LocalDateTime.of(2021,
                        Month.MAY, 15, 18, 0),
                LocalDateTime.of(2021, Month.MAY, 15, 20, 0)));
        testFlights.add(FlightBuilder.createFlight(LocalDateTime.of(2021, Month.MAY, 15, 11, 0),
                LocalDateTime.of(2021, Month.MAY, 15, 14, 0), LocalDateTime.of(2021,
                        Month.MAY, 15, 20, 0),
                LocalDateTime.of(2021, Month.MAY, 15, 22, 0)));
        return testFlights;
    }

    @Test
    public void minusFlightArrivalDateBeforeDepartureDate() {

        List<Flight> testFlights = createTestFlights();
        List<Flight> result = flightService.minusFlightArrivalDateBeforeDepartureDate(testFlights);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(8, result.size());
        Assert.assertTrue(result.stream()
                .flatMap(flight -> flight.getSegments().stream())
                .allMatch(segment -> segment.getArrivalDate().isAfter( segment.getDepartureDate())));
    }

    @Test
    public void minusFlightsWithMoreThanTwoHoursOnTheGround() {

        List<Flight> testFlights = createTestFlights();
        List<Flight> result = flightService.minusFlightArrivalDateBeforeDepartureDate(testFlights);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(8, result.size());
        boolean b = true;

        for (Flight flight : result) {
            for (int i = 1; i < flight.getSegments().size(); i++) {
                if (ChronoUnit.HOURS.between(flight.getSegments().get(i-1).getArrivalDate(),
                    flight.getSegments().get(i).getDepartureDate()) < 2) {
                    b = false;
                    break;
                }
            }
        }
        Assert.assertTrue(b);
    }
}