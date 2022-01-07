package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;


//packaging slip - original
//commission to agent

@Slf4j
public class PhysicalProduct extends PurchasedProduct {
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

    public String processPhysicalProductOrder() {
        String returnValue;
        OrderActions orderActions = new OrderActions();

        log.info("Setting commission to agent as true");
        this.setCommissionPayment(true);

        log.info("Setting shipping label as Original");
        this.setLabelType(ShippingLabelType.ORIGINAL);

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
