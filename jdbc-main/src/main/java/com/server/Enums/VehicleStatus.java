package com.server.Enums;

public enum VehicleStatus {
    Busy,
    Available;

    public static VehicleStatus fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> Busy;
            case 1 -> Available;
            default -> null;
        };
    }

    public static int toInteger(VehicleStatus status){
        return switch (status){

            case Busy -> 0;
            case Available -> 1;
        };
    }
}
