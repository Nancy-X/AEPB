package com.example.AEPB;

import com.example.AEPB.parkingLot.ParkingLot;
import com.example.AEPB.parkingLot.Vehicle;

public class ParkingLotParkUtil {
    public static void parkCars(ParkingLot parkingLot, int parkCarCount) {
        for(int i = 0; i < parkCarCount; i++){
            parkingLot.park(new Vehicle());
        }
    }
}
