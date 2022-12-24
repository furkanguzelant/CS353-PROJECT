package com.server.DTO;

public class PackageStatisticsInfo {

    private String maxPackageCity;
    private String maxLogisticUnit;
    private Integer totalPackages;
    private Integer numberOfPackageInDistribution;
    private Integer numberOfPackageInBranch;

    public PackageStatisticsInfo() {
    }

    public PackageStatisticsInfo(String maxPackageCity, Integer totalPackages, Integer numberOfPackageInDistribution, Integer numberOfPackageInBranch) {
        this.maxPackageCity = maxPackageCity;
        this.totalPackages = totalPackages;
        this.numberOfPackageInDistribution = numberOfPackageInDistribution;
        this.numberOfPackageInBranch = numberOfPackageInBranch;
    }

    public String getMaxPackageCity() {
        return maxPackageCity;
    }

    public void setMaxPackageCity(String maxPackageCity) {
        this.maxPackageCity = maxPackageCity;
    }

    public Integer getTotalPackages() {
        return totalPackages;
    }

    public void setTotalPackages(Integer totalPackages) {
        this.totalPackages = totalPackages;
    }

    public Integer getNumberOfPackageInDistribution() {
        return numberOfPackageInDistribution;
    }

    public void setNumberOfPackageInDistribution(Integer numberOfPackageInDistribution) {
        this.numberOfPackageInDistribution = numberOfPackageInDistribution;
    }

    public Integer getNumberOfPackageInBranch() {
        return numberOfPackageInBranch;
    }

    public void setNumberOfPackageInBranch(Integer numberOfPackageInBranch) {
        this.numberOfPackageInBranch = numberOfPackageInBranch;
    }

    public String getMaxLogisticUnit() {
        return maxLogisticUnit;
    }

    public void setMaxLogisticUnit(String maxLogisticUnit) {
        this.maxLogisticUnit = maxLogisticUnit;
    }
}
