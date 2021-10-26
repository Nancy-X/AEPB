package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.FullParkingLotException;

import java.util.Objects;

public class ParkingBoy {

    private final ParkingLots parkingLots;

    public ParkingBoy(ParkingLots parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Vehicle pickUpVehicle(ParkingTicket ticket) {
        if(Objects.isNull(ticket)) {
            throw new IllegalArgumentException("Illegal Parking Ticket!");
        }
        int parkingLotIndex = ticket.getParingLotNumber() - 1;
        return parkingLots.getParkingLots().get(parkingLotIndex).pickUp(ticket);
    }

    public ParkingTicket park(Vehicle vehicle) {
        for(ParkingLot parkingLot : parkingLots.getParkingLots()) {
            if (parkingLot.hasEmptyLot()) {
                return parkingLot.park(vehicle);
            }
        }
        throw new FullParkingLotException("All parking lot is full");
    }
}
