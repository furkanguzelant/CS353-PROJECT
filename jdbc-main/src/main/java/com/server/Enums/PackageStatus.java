package com.server.Enums;

public enum PackageStatus {

   Delivered, InDistribution, InBranch;

    public static PackageStatus fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> Delivered;
            case 1 -> InDistribution;
            case 2 -> InBranch;
            default -> null;
        };

    }
}
