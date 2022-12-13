package com.server.Enums;

public enum CourierStatus {

    AVAILABLE, IN_DISTRIBUTION;

    public static CourierStatus fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> AVAILABLE;
            case 1 -> IN_DISTRIBUTION;
            default -> null;
        };
    }
}
