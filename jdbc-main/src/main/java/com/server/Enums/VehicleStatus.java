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
}
