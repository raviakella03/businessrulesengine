package com.ravi.interview.businessrulesengine.utils;

import com.ravi.interview.businessrulesengine.service.Membership;
import com.ravi.interview.businessrulesengine.service.PhysicalProduct;
import com.ravi.interview.businessrulesengine.service.PurchasedProduct;

public class OrderActions {
    public String printLabel(PurchasedProduct purchasedProduct) {
        String returnValue;
        if (purchasedProduct.getLabelType() == (ShippingLabelType.ORIGINAL)) {
//            returnValue = "Original Shipping Label";
            PhysicalProduct physicalProduct = new PhysicalProduct();
            physicalProduct = (PhysicalProduct)purchasedProduct;
            returnValue = printOriginalShippingLabel(physicalProduct.getShippingAddress());
        } else {
            returnValue = "No shipping label needed";
        }
        return returnValue;
    }

    public String printOriginalShippingLabel(String shippingAddress) {
        String returnValue = null;
        if (null != shippingAddress) {
            returnValue = "Original Shipping Label\n" + shippingAddress;
        } else if (null == shippingAddress || "".equals(shippingAddress)){
            return "No Shipping address set for order processing";
        }
        return returnValue;
    }

    public String sendCommissionToAgent(PurchasedProduct purchasedProduct) {
        String returnValue = "";
        if (purchasedProduct.isCommissionPayment()) {
            returnValue = "Agent commission sent";
        } else {
            returnValue = "No commission to agent";
        }
        return returnValue;
    }

    public String activateMembership(Membership membership) {
        //call a setter to set the membership level
        //call a getter to get the updated membership level
        //send email to the customer about the membership activation or upgrade
        return null;
    }

    public String addFreeFirstAidVideo() {
        //call print Label and add first aid video to it
        return null;
    }

}
