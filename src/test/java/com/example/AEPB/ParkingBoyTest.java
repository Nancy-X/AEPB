package com.example.AEPB;

import com.example.AEPB.exception.FullParkingLotException;
import com.example.AEPB.parkingLot.ParkingBoy;
import com.example.AEPB.parkingLot.ParkingLots;
import com.example.AEPB.parkingLot.ParkingTicket;
import com.example.AEPB.parkingLot.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    @BeforeEach
    void setup() {

        this.parkingBoy = new ParkingBoy(new ParkingLots());
    }

    /**
     * test case 1:
     * given: 第一个停车场为空，parking boy 停车
     * when: parking boy 停车
     * then: 将车停在第一个停车场，拿到停车票
     */
    @Test
    void should_get_parking_ticket_and_parking_the_car_in_the_first_parking_lot_when_park_car_given_vehicle_to_parking_boy_and_the_first_parking_lot_is_empty() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingBoy.park(vehicle);

        Assertions.assertEquals(1, ticket.getParingLotNumber());
    }

    /**
     * test case 2:
     * given: 第一个停车场停满，第二个停车场有空位，parking boy 停车
     * when: parking boy 停车
     * then: 将车停在第二个停车场，拿到停车票
     */
    @Test
    void should_get_parking_ticket_and_parking_the_car_in_the_second_parking_lot_when_park_car_given_vehicle_to_parking_boy_and_the_first_parking_lot_is_full() {
        Vehicle vehicle = new Vehicle();
        
        makeTheFirstParkingLotFull(parkingBoy);
        ParkingTicket ticket = parkingBoy.park(vehicle);

        Assertions.assertEquals(2, ticket.getParingLotNumber());
    }

    private void makeTheFirstParkingLotFull(ParkingBoy parkingBoy) {
        for(int i = 0; i < 50; i++) {
            parkingBoy.park(new Vehicle());
        }
    }

    /**
     * test case 3:
     * given: 第一个停车场有空位，第二个停车场有空位，parking boy 停车
     * when: parking boy 停车
     * then: 将车停在第一个停车场，拿到停车票
     */
    @Test
    void should_get_parking_ticket_and_parking_the_car_in_the_first_parking_lot_when_park_car_given_vehicle_to_parking_boy_and_the_first_parking_lot_is_not_full() {
        Vehicle vehicle = new Vehicle();

        makeTheFirstParkingLotNotFull(parkingBoy);
        ParkingTicket ticket = parkingBoy.park(vehicle);

        Assertions.assertEquals(1, ticket.getParingLotNumber());
    }

    private void makeTheFirstParkingLotNotFull(ParkingBoy parkingBoy) {
        for(int i = 0; i < 40; i++) {
            parkingBoy.park(new Vehicle());
        }
    }

    /**
     * test case 4:
     * given: 10个停车场均停满，parking boy 停车
     * when: parking boy 停车
     * then: 无法停车，不能拿到停车票
     */
    @Test
    void should_throw_exception_when_park_car_given_vehicle_to_parking_boy_and_all_parking_lot_is_full() {
        Vehicle vehicle = new Vehicle();

        makeAllParkingLotsFull(parkingBoy);

        Assertions.assertThrows(FullParkingLotException.class, () -> parkingBoy.park(vehicle));
    }

    private void makeAllParkingLotsFull(ParkingBoy parkingBoy) {
        for(int i = 0; i < 500; i++) {
            parkingBoy.park(new Vehicle());
        }
    }

    /**
     * test case 5:
     * given: 提供合规的停车票，parking boy 取车
     * when: parking boy 取车
     * then: 取到车
     */
    @Test
    void should_get_the_car_when_pick_up_car_given_valid_ticket_to_parking_boy() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = parkingBoy.park(vehicle);

        Vehicle pickedUpVehicle = parkingBoy.pickUpVehicle(ticket);

        Assertions.assertEquals(vehicle, pickedUpVehicle);
    }

    /**
     * test case 6:
     * given: 不提供停车票，parking boy 取车
     * when: parking boy 取车
     * then: 不能取到车
     */
    @Test
    void should_throw_exception_when_pick_up_car_given_no_ticket_to_parking_boy() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingBoy.pickUpVehicle(null));
    }

    /**
     * test case 7:
     * given: 提供已经取过车的停车票，parking boy 取车
     * when: parking boy 取车
     * then: 不能取到车
     */
    @Test
    void should_throw_exception_when_pick_up_car_given_valid_ticket_to_parking_boy() {
        ParkingTicket ticket = parkingBoy.park(new Vehicle());

        Vehicle pickedUpVehicle = parkingBoy.pickUpVehicle(ticket);

        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingBoy.pickUpVehicle(ticket));
    }

    /**
     * test case 8:
     * given:提供错误的停车票，parking boy 取车
     * when: parking boy 取车
     * then: 不能取到车
     */
    @Test
    void should_throw_exception_when_pick_up_car_given_forged_ticket_to_parking_boy() {
        ParkingTicket ticket = parkingBoy.park(new Vehicle());

        ParkingTicket forgedTicket = new ParkingTicket(ticket.getParingLotNumber());
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingBoy.pickUpVehicle(forgedTicket));
    }
}
