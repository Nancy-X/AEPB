package com.example.AEPB;

import com.example.AEPB.exception.DuplicateCarException;
import com.example.AEPB.parkingLot.ParkingLot;
import com.example.AEPB.parkingLot.ParkingTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    @Test
    void should_get_parking_ticket_when_park_car_given_valid_car_license_number() {
        String licensePlateNumber = "京A88888";
        Assertions.assertEquals(parkingLot.parkCar(licensePlateNumber).getLicensePlateNumber(), licensePlateNumber);
    }

    @Test
    void should_fail_when_park_car_given_null_car_license_number() {
        String licensePlateNumber = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.parkCar(licensePlateNumber));
    }

    @Test
    void should_fail_when_park_car_given_empty_car_license_number() {
        String licensePlateNumber = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.parkCar(licensePlateNumber));
    }

    @Test
    void should_fail_when_park_car_given_parking_lot_is_full() {
        givenFullParkingLot();

        String licensePlateNumber = "京A88888";
        Assertions.assertNull(parkingLot.parkCar(licensePlateNumber));
    }

    private void givenFullParkingLot() {
        for(int i = 0; i < 50; i++) {
            String licensePlateNumber = String.valueOf(i);
            parkingLot.parkCar(licensePlateNumber);
        }
    }

    @Test
    void should_throw_exception_when_parking_car_given_duplicate_car() {
        String licensePlateNumber = "京A88888";
        parkingLot.parkCar(licensePlateNumber);
        Assertions.assertThrows(DuplicateCarException.class, () -> parkingLot.parkCar(licensePlateNumber));
    }


    @Test
    void should_success_when_pick_up_car_given_parking_ticket_car_parked_in_the_parking_lot() {
        parkingLot.parkCar("京A66666");

        ParkingTicket parkingTicket = new ParkingTicket("京A66666");
        Assertions.assertTrue(parkingLot.pickUpCar(parkingTicket));
    }

    @Test
    void should_throw_exception_when_pick_up_car_given_parking_ticket_car_not_parked_in_the_parking_lot() {
        parkingLot.parkCar("京A66666");

        ParkingTicket parkingTicket = new ParkingTicket("京A88888");
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUpCar(parkingTicket));
    }

    @Test
    void should_throw_exception_when_pick_up_car_given_duplicate_parking_ticket() {
        parkingLot.parkCar("京A66666");

        ParkingTicket parkingTicket = new ParkingTicket("京A66666");
        parkingLot.pickUpCar(parkingTicket);

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUpCar(parkingTicket));
    }

    @Test
    void should_fail_when_pick_up_car_given_null_parking_ticket() {
        parkingLot.parkCar("京A66666");

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUpCar(null));
    }
}
