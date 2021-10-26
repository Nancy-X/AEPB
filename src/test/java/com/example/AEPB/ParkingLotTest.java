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
        parkingLot = new ParkingLot(1, 50);
    }

    @Test
    void should_get_parking_ticket_when_park_car_given_valid_car() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.park(vehicle);
        Assertions.assertEquals(vehicle, parkingLot.pickUp(ticket));
    }

    @Test
    void should_fail_when_park_car_given_null_car() {
        Vehicle vehicle = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void should_throw_exception_when_park_car_given_parking_lot_is_full() {
        givenFullParkingLot();

        Assertions.assertThrows(FullParkingLotException.class, () -> parkingLot.park(new Vehicle()));
    }

    private void givenFullParkingLot() {
        for(int i = 0; i < 50; i++) {
            parkingLot.park(new Vehicle());
        }
    }

    @Test
    void should_throw_exception_when_parking_car_given_duplicate_car() {
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        Assertions.assertThrows(DuplicateCarException.class, () -> parkingLot.park(vehicle));
    }


    @Test
    void should_success_when_pick_up_car_given_valid_parking_ticket() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.park(vehicle);

        Assertions.assertEquals(vehicle, parkingLot.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_car_given_forged_ticket() {

        ParkingTicket forgedTicket = new ParkingTicket(0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUp(forgedTicket));
    }

    @Test
    void should_throw_exception_when_pick_up_car_given_duplicate_parking_ticket() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.park(vehicle);

        parkingLot.pickUp(ticket);

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_fail_when_pick_up_car_given_null_parking_ticket() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingLot.park(vehicle);

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUp(null));
    }
}
