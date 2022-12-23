package com.server.Enums;

public enum ComplaintType {
    Broken, Undelivered, LateDelivered, MissingItems, Other;

    public static ComplaintType fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> Broken;
            case 1 -> Undelivered;
            case 2 -> LateDelivered;
            case 3 -> MissingItems;
            case 4 -> Other;
            default -> null;
        };
    }
}
