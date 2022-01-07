package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;


//packaging slip - original
//commission to agent

public class PhysicalProduct extends PurchasedProduct{
    String shippingAddress;

    public PhysicalProduct() {
        this.productType = ProductType.PHYSICAL_PRODUCT;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String processPhysicalProductOrder () {
        String returnValue = null;
        OrderActions orderActions = new OrderActions();
        if (null == this.getShippingAddress()) {
            returnValue = "No shipping address sent - received (\"null\")";
        } else {
            returnValue = orderActions.printLabel(this);
            returnValue = returnValue + "\n" + orderActions.sendCommissionToAgent(this);
        }
        return returnValue;
    }
}
