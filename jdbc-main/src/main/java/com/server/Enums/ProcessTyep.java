package com.server.Enums;

public enum ProcessType {
    Receive, Transfer, Deliver;

    public static ProcessType fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> Receive;
            case 1 -> Transfer;
            case 2 -> Deliver;
            default -> null;
        };

    }
}
