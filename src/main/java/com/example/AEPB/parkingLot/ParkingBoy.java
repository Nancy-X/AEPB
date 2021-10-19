package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.FullParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {

    private final List<ParkingLot> parkingLots;
    static final int PARKING_LOT_COUNT = 10;

    public ParkingBoy() {
        parkingLots = new ArrayList<>(PARKING_LOT_COUNT);
        for(int i = 0; i < PARKING_LOT_COUNT; i++) {
            parkingLots.add(new ParkingLot(i + 1));
        }
    }

    public Vehicle pickUpVehicle(ParkingTicket ticket) {
        if(Objects.isNull(ticket)) {
            throw new IllegalArgumentException("Illegal Parking Ticket!");
        }
        int parkingLotIndex = ticket.getParingLotNumber() - 1;
        return parkingLots.get(parkingLotIndex).pickUpCar(ticket);
    }

    public ParkingTicket park(Vehicle vehicle) {
        for(ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkingRecord().size() < ParkingLot.MAX_CAPACITY) {
                return parkingLot.parkCar(vehicle);
            }
        }
        throw new FullParkingLotException("All parking lot is full");
    }
}
