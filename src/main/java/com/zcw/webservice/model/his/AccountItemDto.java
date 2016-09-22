package com.zcw.webservice.model.his;

import java.util.Date;
import java.util.List;

/**
 * Created by zcw on 2016/9/21.
 */
public class AccountItemDto extends  AccountItem{
    private double price;
    private String feeDetailItemId;
    private String feeDetailItemName;

    public AccountItemDto() {
        super();
    }
    public AccountItemDto(double price, String feeDetailItemId, String feeDetailItemName) {
        this.price = price;
        this.feeDetailItemId = feeDetailItemId;
        this.feeDetailItemName = feeDetailItemName;
    }

    public AccountItemDto(String patientCode, String patientId, String patientType, String patientName, String testPurposesCode, String testPurposes, Date dateTime, int quantity, double price, String feeItemCode, String feeItemName, String billingDoctorNo, String billingDeptNo, String testDoctorNo, String testDoctorDeptNo, String operatorNo, Long accountId, double price1, String feeDetailItemId, String feeDetailItemName) {
        super(patientCode, patientId, patientType, patientName, testPurposesCode, testPurposes, dateTime, quantity, price, feeItemCode, feeItemName, billingDoctorNo, billingDeptNo, testDoctorNo, testDoctorDeptNo, operatorNo, accountId);
        this.price = price1;
        this.feeDetailItemId = feeDetailItemId;
        this.feeDetailItemName = feeDetailItemName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public String getFeeDetailItemId() {
        return feeDetailItemId;
    }

    public void setFeeDetailItemId(String feeDetailItemId) {
        this.feeDetailItemId = feeDetailItemId;
    }


    public String getFeeDetailItemName() {
        return feeDetailItemName;
    }


    public void setFeeDetailItemName(String feeDetailItemName) {
        this.feeDetailItemName = feeDetailItemName;
    }
}
