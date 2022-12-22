package com.server.Enums;

public enum PackageStatus {

   Delivered, InDistribution, InBranch, InTransfer;

    public static PackageStatus fromInteger(int enumNumber) {
        return switch (enumNumber) {
            case 0 -> Delivered;
            case 1 -> InDistribution;
            case 2 -> InBranch;
            case 3 -> InTransfer;
            default -> null;
        };
    }
}
