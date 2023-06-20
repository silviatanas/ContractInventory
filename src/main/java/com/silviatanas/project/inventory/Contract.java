package com.silviatanas.project.inventory;

public class Contract {
    String contractNumber;
    long startDate;
    long endDate;
    String customerId;
    String customerName;
    String billingSystemId;
    Environment[] environments;
    String[] relatedContracts;

    public String getContractNumber() {
        return contractNumber;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBillingSystemId() {
        return billingSystemId;
    }

    public Environment[] getEnvironments() {
        return environments;
    }

    public String[] getRelatedContracts() {
        return relatedContracts;
    }
}
