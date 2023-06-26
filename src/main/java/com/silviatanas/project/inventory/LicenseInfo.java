package com.silviatanas.project.inventory;

/**
 * This is a java presentation of the LicenseInfo object array
 * from the previous json object presentation:
 * {@link com.silviatanas.project.inventory.Environment}
 *
 * It has the following structure:
 * {\"row\": <>, \"unit\": \"<>\", \"productCodes\": [{\"<>\": [\"<>\"]}]}
 */
public class LicenseInfo {
    int row;
    String unit;
    Object[] productCodes;

    public int getRow() {
        return row;
    }

    public String getUnit() {
        return unit;
    }

    public Object[] getProductCodes() {
        return productCodes;
    }
}
