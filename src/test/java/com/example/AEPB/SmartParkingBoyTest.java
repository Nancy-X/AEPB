package com.example.AEPB;

import com.example.AEPB.exception.FullParkingLotException;
import com.example.AEPB.parkingLot.ParkingLot;
import com.example.AEPB.parkingLot.ParkingTicket;
import com.example.AEPB.parkingLot.SmartParkingBoy;
import com.example.AEPB.parkingLot.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.AEPB.ParkingLotParkUtil.parkCars;

public class SmartParkingBoyTest {
    SmartParkingBoy smartParkingBoy;
    List<ParkingLot> parkingLots;

    @BeforeEach
    void setup() {
        parkingLots = new ArrayList<>(10);
        for(int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(i + 1, 50));
        }
        smartParkingBoy = new SmartParkingBoy(parkingLots);
    }

    /**
     * test case 1:
     * given: 所有停车场为空，smart parking boy 停车
     * when: smart parking boy 停车
     * then: 将车停入一号停车场，并且拿到停车票
     */
    @Test
    void should_park_in_parking_lot_1_when_park_given_all_parking_lot_empty_and_parked_by_smart_parking_boy() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = smartParkingBoy.park(vehicle);
        Assertions.assertEquals(1, ticket.getParingLotNumber());
        Assertions.assertEquals(vehicle, smartParkingBoy.pickup(ticket));
    }

    /**
     * test case 2:
     * given: 1-10号停车场的空位分别为1，2，..., 10,smart parking boy 停车
     * when: smart parking boy 停车
     * then: 将车停入10号停车场，并且拿到停车票
     */
    @Test
    void should_park_in_parking_lot_10_when_park_given_parking_lot_10_has_most_empty_positions_and_parked_by_smart_parking_boy() {
        parkCars(parkingLots.get(0), 49);
        parkCars(parkingLots.get(1), 48);
        parkCars(parkingLots.get(2), 47);
        parkCars(parkingLots.get(3), 46);
        parkCars(parkingLots.get(4), 45);
        parkCars(parkingLots.get(5), 44);
        parkCars(parkingLots.get(6), 43);
        parkCars(parkingLots.get(7), 42);
        parkCars(parkingLots.get(8), 41);
        parkCars(parkingLots.get(9), 40);

        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = smartParkingBoy.park(vehicle);
        Assertions.assertEquals(10, ticket.getParingLotNumber());
        Assertions.assertEquals(vehicle, smartParkingBoy.pickup(ticket));
    }

    /**
     * test case 3:
     * given: 1-10号停车场的空位分别为1，2，..., 9, 9，smart parking boy 停车
     * when: smart parking boy 停车
     * then: 将车停入9号停车场，并且拿到停车票
     */
    @Test
    void should_park_in_parking_lot_9_when_park_given_parking_lot_9_and_parking_lot_10_has_same_empty_positions_and_parked_by_smart_parking_boy() {
        parkCars(parkingLots.get(0), 49);
        parkCars(parkingLots.get(1), 48);
        parkCars(parkingLots.get(2), 47);
        parkCars(parkingLots.get(3), 46);
        parkCars(parkingLots.get(4), 45);
        parkCars(parkingLots.get(5), 44);
        parkCars(parkingLots.get(6), 43);
        parkCars(parkingLots.get(7), 42);
        parkCars(parkingLots.get(8), 41);
        parkCars(parkingLots.get(9), 41);

        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = smartParkingBoy.park(vehicle);
        Assertions.assertEquals(9, ticket.getParingLotNumber());
        Assertions.assertEquals(vehicle, smartParkingBoy.pickup(ticket));
    }

    /**
     * test case 4:
     * given: 所有停车场已满，smart parking boy 停车
     * when: smart parking boy 停车
     * then: 不能停入，抛出异常
     */
    @Test
    void should_throw_exception_when_park_given_all_parking_lot_are_full_and_parked_by_smart_parking_boy() {
        makeAllParkingLotsFull(smartParkingBoy);

        Vehicle vehicle = new Vehicle();
        Assertions.assertThrows(FullParkingLotException.class, () -> smartParkingBoy.park(vehicle));
    }

    private void makeAllParkingLotsFull(SmartParkingBoy smartParkingBoy) {
        for(int i = 0; i < 500; i++) {
            smartParkingBoy.park(new Vehicle());
        }
    }
    /**
     * test case 5:
     * given: 提供合规的停车票，parking boy 取车
     * when: smart parking boy 取车
     * then: 取到车
     */
    @Test
    void should_get_car_when_pick_up_given_valid_parking_ticket() {
        Vehicle vehicle = new Vehicle();
        ParkingTicket ticket = smartParkingBoy.park(vehicle);

        Assertions.assertEquals(vehicle, smartParkingBoy.pickup(ticket));
    }

    /**
     * test case 6:
     * given: 不提供停车票，parking boy 取车
     * when: smart parking boy 取车
     * then: 不能取到车
     */
    @Test
    void should_throw_exception_when_pick_up_given_no_parking_ticket() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> smartParkingBoy.pickup(null));

    }

    /**
     * test case 7:
     * given: 提供已经取过车的停车票，parking boy 取车
     * when: smart parking boy 取车
     * then: 不能取到车
     */
    @Test
    void should_throw_exception_when_pick_up_given_parking_ticket_which_picked_before() {
        ParkingTicket ticket = smartParkingBoy.park(new Vehicle());
        smartParkingBoy.pickup(ticket);

        Assertions.assertThrows(IllegalArgumentException.class, () -> smartParkingBoy.pickup(ticket));
    }

    /**
     * test case 8:
     * given:提供错误的停车票，parking boy 取车
     * when: smart parking boy 取车
     * then: 不能取到车
     */
    @Test
    void should_throw_exception_when_pick_up_given_forged_ticket_to_smart_parking_boy() {
        ParkingTicket ticket = smartParkingBoy.park(new Vehicle());

        ParkingTicket forgedTicket = new ParkingTicket(ticket.getParingLotNumber());
        Assertions.assertThrows(IllegalArgumentException.class, () -> smartParkingBoy.pickup(forgedTicket));
    }
}
