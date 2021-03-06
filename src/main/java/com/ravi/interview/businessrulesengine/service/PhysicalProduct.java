package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhysicalProduct extends PurchasedProduct {


    public PhysicalProduct() {
        this.productType = ProductType.PHYSICAL_PRODUCT;
        this.labelType = ShippingLabelType.ORIGINAL;
        this.commissionPayment = true;
    }

    public String processPhysicalProductOrder() {
        String returnValue;
        OrderActions orderActions = new OrderActions();

        if (null != this.getShippingAddress()) {
            returnValue = orderActions.printLabel(this);
            if (returnValue.equals("Invalid shipping label type")) {
                returnValue += ". Not processing the order";
            } else {
                returnValue += "\n" + orderActions.sendCommissionToAgent(this);
            }
        } else {
            returnValue = "Invalid/No shipping address received. Not processing the order";
        }
        return returnValue;
    }
}
