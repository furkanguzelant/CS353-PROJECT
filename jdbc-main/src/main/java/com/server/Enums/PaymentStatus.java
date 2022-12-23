package com.server.Enums;

public enum PaymentStatus {
    NOT_PAID,PAID;

    public static PaymentStatus fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> NOT_PAID;
            case 1 -> PAID;
            default -> null;
        };

    }
}
