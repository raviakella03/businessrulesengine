package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Book extends PurchasedProduct {
//    String shippingAddress;
    int quantity;

    public Book() {
        this.productType = ProductType.BOOK;
        this.labelType = ShippingLabelType.ORIGINAL_DUPLICATE;
        this.commissionPayment = true;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }*/

    public String processBookOrder() {
        String returnValue;
        OrderActions orderActions = new OrderActions();

        if (this.getQuantity() == 0) {
            returnValue = "No quantity received to send to royalty dept. Not processing the order.";
        } else {
            returnValue = orderActions.printLabel(this);
            if (returnValue.equals("Invalid shipping label type") || returnValue.contains("Error while printing Original shipping label") ||
                    returnValue.contains("No Shipping address received for order processing")) {
                returnValue += ". Not processing the order.";
            } else {
                returnValue += "\n" + orderActions.sendCommissionToAgent(this);
            }
        }
        return returnValue;
    }
}
