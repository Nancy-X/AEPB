package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.FullParkingLotException;

import java.util.List;
import java.util.Objects;

public class ParkingBoy {

    protected final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Vehicle vehicle) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasEmptyLot()) {
                return parkingLot.park(vehicle);
            }
        }
        throw new FullParkingLotException("All parking lot is full");
    }

    public Vehicle pickup(ParkingTicket ticket) {
        if (Objects.isNull(ticket)) {
            throw new IllegalArgumentException("Illegal Parking Ticket!");
        }
        int parkingLotIndex = ticket.getParingLotNumber() - 1;
        return parkingLots.get(parkingLotIndex).pickUp(ticket);
    }
}
