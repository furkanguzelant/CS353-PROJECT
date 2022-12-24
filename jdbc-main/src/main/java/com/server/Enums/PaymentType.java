package com.server.Enums;

public enum PaymentType {

    PREPAID,COUNTER_PAID;

    public static PaymentType fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> PREPAID;
            case 1 -> COUNTER_PAID;
            default -> null;
        };

    }
}

