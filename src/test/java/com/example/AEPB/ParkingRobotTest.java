package com.example.AEPB;

import com.example.AEPB.exception.FullParkingLotException;
import com.example.AEPB.parkingLot.ParkingLot;
import com.example.AEPB.parkingLot.ParkingRobot;
import com.example.AEPB.parkingLot.ParkingTicket;
import com.example.AEPB.parkingLot.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingRobotTest {

    private ParkingRobot parkingRobot;
    private List<ParkingLot> parkingLots;
    private final int parkingLotCount = 7;
    private List<Integer> parkingLotCapacity;


    @BeforeEach
    void setup() {
        parkingLotCapacity = Arrays.asList(70, 50, 100, 10, 40, 100, 80);
        parkingLots = new ArrayList<>(parkingLotCount);
        for (int i = 0; i < parkingLotCount; i++) {
            parkingLots.add(new ParkingLot(i + 1, parkingLotCapacity.get(i)));
        }
        parkingRobot = new ParkingRobot(parkingLots);
    }

    @Test
    void should_park_in_first_parking_lot_when_park_given_all_parking_lot_empty_and_parked_by_parking_robot() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingRobot.park(vehicle);

        Assertions.assertEquals(1, parkingTicket.getParingLotNumber());
        Assertions.assertEquals(vehicle, parkingLots.get(0).pickUp(parkingTicket));
    }

    @Test
    void should_park_in_parking_lot_3_when_park_given_parking_lot_3_has_most_vacancy_rate() {
        makeParkingLot3HasMostVacancyRate(parkingLots);

        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingRobot.park(vehicle);

        Assertions.assertEquals(3, parkingTicket.getParingLotNumber());
        Assertions.assertEquals(vehicle, parkingLots.get(2).pickUp(parkingTicket));
    }

    private void makeParkingLot3HasMostVacancyRate(List<ParkingLot> parkingLots) {
        List<Integer> parkCarCount = Arrays.asList(60, 40, 8, 4, 5, 12, 10);
        for(int i = 0; i < parkingLotCount; i++) {
            ParkingLotParkUtil.parkCars(parkingLots.get(i), parkCarCount.get(i));
        }
    }

    @Test
    void should_park_in_parking_lot_4_when_park_given_parking_lot_4_and_5_has_same_vacancy_rate_and_more_than_others() {
        makeParkingLot4And5HasSameVacancyRateAndMoreThanOthers(parkingLots);

        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingRobot.park(vehicle);

        Assertions.assertEquals(4, parkingTicket.getParingLotNumber());
        Assertions.assertEquals(vehicle, parkingLots.get(3).pickUp(parkingTicket));
    }

    private void makeParkingLot4And5HasSameVacancyRateAndMoreThanOthers(List<ParkingLot> parkingLots) {
        List<Integer> parkCarCount = Arrays.asList(60, 40, 20, 1, 4, 12, 10);
        for(int i = 0; i < parkingLotCount; i++) {
            ParkingLotParkUtil.parkCars(parkingLots.get(i), parkCarCount.get(i));
        }
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lot_full_and_parked_by_parking_robot() {
        makeAllParkingLotFull(parkingLots);

        Vehicle vehicle = new Vehicle();
        Assertions.assertThrows(FullParkingLotException.class, () -> parkingRobot.park(vehicle));
    }

    private void makeAllParkingLotFull(List<ParkingLot> parkingLots) {
        for(int i = 0; i < parkingLotCount; i++) {
            ParkingLotParkUtil.parkCars(parkingLots.get(i), parkingLotCapacity.get(i));
        }
    }
}
