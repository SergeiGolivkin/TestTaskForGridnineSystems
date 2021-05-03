package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FlightServiceImpl implements FlightService {

    @Override
    public List<Flight> minusDepartureTimeBeforeCurrentTime(List<Flight> flights) {

        List<Flight> flightList = new ArrayList<>(flights) ;
        ListIterator<Flight> iterator = flightList.listIterator();
        while (iterator.hasNext()) {
            Flight currentFlight = iterator.next();
            for (int i = 0; i < currentFlight.getSegments().size(); i++) {
                LocalDateTime departureTime = currentFlight.getSegments().get(i).getDepartureDate();
                if (departureTime.isBefore(LocalDateTime.now())) {
                    iterator.remove();
                    break;
                }
            }

        }
        return flightList;
    }


    @Override
    public List<Flight> minusFlightArrivalDateBeforeDepartureDate(List<Flight> flights) {

        List<Flight> flightList = new ArrayList<>(flights) ;
        ListIterator<Flight> iterator = flightList.listIterator();
        while(iterator.hasNext()) {
            Flight currentFlight = iterator.next();
            for (int i = 0; i < currentFlight.getSegments().size(); i++) {
                LocalDateTime departureTime = currentFlight.getSegments().get(i).getDepartureDate();
                LocalDateTime arrivalTime = currentFlight.getSegments().get(i).getArrivalDate();
                if (arrivalTime.isBefore(departureTime)) {
                    iterator.remove();
                    break;
                }
            }
        }
        return flightList;
    }


    @Override
    public List<Flight> minusFlightsWithMoreThanTwoHoursOnTheGround(List<Flight> flights) {

        List <Flight> flightList = new ArrayList<>(flights);
        for(Flight flight : flights){
            for (int i = 1; i < flight.getSegments().size(); i++) {
                if (ChronoUnit.HOURS.between(flight.getSegments().get(i-1).getArrivalDate(),
                        flight.getSegments().get(i).getDepartureDate()) >= 2) {
                   flightList.remove(flight);
                }
            }
        }
        return flightList;
    }
}

