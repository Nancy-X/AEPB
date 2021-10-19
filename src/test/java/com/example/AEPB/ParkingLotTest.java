package com.example.AEPB;

import com.example.AEPB.exception.DuplicateCarException;
import com.example.AEPB.exception.FullParkingLotException;
import com.example.AEPB.parkingLot.ParkingLot;
import com.example.AEPB.parkingLot.ParkingTicket;
import com.example.AEPB.parkingLot.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    @Test
    void should_get_parking_ticket_when_park_car_given_valid_car() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.parkCar(vehicle);
        Assertions.assertEquals(vehicle, parkingLot.pickUpCar(ticket));
    }

    @Test
    void should_fail_when_park_car_given_null_car_license_number() {
        Vehicle vehicle = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.parkCar(vehicle));
    }

    @Test
    void should_throw_exception_when_park_car_given_parking_lot_is_full() {
        givenFullParkingLot();

        Assertions.assertThrows(FullParkingLotException.class, () -> parkingLot.parkCar(new Vehicle()));
    }

    private void givenFullParkingLot() {
        for(int i = 0; i < 50; i++) {
            parkingLot.parkCar(new Vehicle());
        }
    }

    @Test
    void should_throw_exception_when_parking_car_given_duplicate_car() {
        Vehicle vehicle = new Vehicle();
        parkingLot.parkCar(vehicle);
        Assertions.assertThrows(DuplicateCarException.class, () -> parkingLot.parkCar(vehicle));
    }


    @Test
    void should_success_when_pick_up_car_given_parking_ticket_car_parked_in_the_parking_lot() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.parkCar(vehicle);

        Assertions.assertEquals(vehicle, parkingLot.pickUpCar(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_car_given_parking_ticket_car_not_parked_in_the_parking_lot() {

        ParkingTicket parkingTicket = new ParkingTicket();
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUpCar(parkingTicket));
    }

    @Test
    void should_throw_exception_when_pick_up_car_given_duplicate_parking_ticket() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.parkCar(vehicle);

        parkingLot.pickUpCar(ticket);

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUpCar(ticket));
    }

    @Test
    void should_fail_when_pick_up_car_given_null_parking_ticket() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.parkCar(vehicle);

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUpCar(null));
    }
}
