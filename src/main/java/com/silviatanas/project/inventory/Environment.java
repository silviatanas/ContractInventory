package com.silviatanas.project.inventory;

public class Environment {
    String name;
    LicenseInfo[] licenseInfo;
    String environmentType;
    String usageType;
    boolean swAGCloud;

    public String getName() {
        return name;
    }

    public LicenseInfo[] getLicenseInfo() {
        return licenseInfo;
    }

    public String getEnvironmentType() {
        return environmentType;
    }
    public String getUsageType() {
        return usageType;
    }

    public boolean isSwAGCloud() {
        return swAGCloud;
    }
}
