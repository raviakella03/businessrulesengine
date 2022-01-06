package com.ravi.interview.businessrulesengine.utils;

import com.ravi.interview.businessrulesengine.service.Membership;
import com.ravi.interview.businessrulesengine.service.PurchasedProduct;

public class OrderActions {
    public String printLabel(PurchasedProduct purchasedProduct) {
        return null;
    }

    public void sendCommissionToAgent(String productDetails) {
    }

    public String activateMembership(Membership membership) {
        //call a setter to set the membership level
        //call a getter to return the updated membership level
        //send email to the customer about the membership activation or upgrade
        return null;
    }

    public String addFreeFirstAidVideo() {
        //call print Label and add first aid video to it
        return null;
    }

}
