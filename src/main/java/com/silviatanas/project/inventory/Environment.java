package com.silviatanas.project.inventory;

/**
 * This is a java presentation of the Environments object array
 * from the initial json object presentation:
 * {@link com.silviatanas.project.inventory.Contract}
 *
 * It has the following structure:
 * {
 *      "name": "<>",
 *      "licenseInfo": "{<>}",
 *      "environmentType": "<>",
 *      "usageType": "<>",
 *      "swAGCloud": true/false
 * }
 */
public class Environment {
    String name;
    String licenseInfo;
    LicenseInfo[] licenseInfoObjects;
    String environmentType;
    String usageType;
    boolean swAGCloud;

    public String getName() {
        return name;
    }

    public String getLicenseInfo() {
        return licenseInfo;
    }

    public LicenseInfo[] getLicenseInfoObjects() {
        return licenseInfoObjects;
    }

    public void setLicenseInfoObjects(LicenseInfo[] licenseInfoObjects) {
        this.licenseInfoObjects = licenseInfoObjects;
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
