package com.server.Enums;

public enum PaymentType {

    SENDER_PAYS,COUNTER_PAY;

    public static PaymentType fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> SENDER_PAYS;
            case 1 -> COUNTER_PAY;
            default -> null;
        };

    }
}

