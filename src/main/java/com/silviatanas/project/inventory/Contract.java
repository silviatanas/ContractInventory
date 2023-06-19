package com.silviatanas.project.inventory;

public class Contract {
    String contractNumber;
    long startDate;
    long endDate;
    String customerId;
    String customerName;
    String billingSystemId;
    Environment[] environment;
    String[] relatedContracts;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBillingSystemId() {
        return billingSystemId;
    }

    public void setBillingSystemId(String billingSystemId) {
        this.billingSystemId = billingSystemId;
    }

    public Environment[] getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment[] environment) {
        this.environment = environment;
    }

    public String[] getRelatedContracts() {
        return relatedContracts;
    }

    public void setRelatedContracts(String[] relatedContracts) {
        this.relatedContracts = relatedContracts;
    }
}
