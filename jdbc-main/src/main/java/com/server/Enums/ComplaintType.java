package com.server.Enums;

public enum ComplaintType {
    BrokenPackage, UndeliveredPackage, LatePackage, LostPackage, Other;

    public static ComplaintType fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> BrokenPackage;
            case 1 -> UndeliveredPackage;
            case 2 -> LatePackage;
            case 3 -> LostPackage;
            case 4 -> Other;
            default -> null;
        };
    }
}
