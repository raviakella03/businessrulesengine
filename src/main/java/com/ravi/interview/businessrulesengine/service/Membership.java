package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.MembershipType;
import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Membership extends PurchasedProduct {
    MembershipType membershipLevel;

    public Membership() {
        this.productType = ProductType.MEMBERSHIP;
        this.membershipLevel = MembershipType.NO_MEMBERSHIP;
        this.labelType = ShippingLabelType.EMAIL;
        this.commissionPayment = false;
    }

    /*public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }*/

    public MembershipType getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipType membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String processMembershipOrder(MembershipType membershipType) {
        String returnValue;
        OrderActions orderActions = new OrderActions();

        if (this.getMembershipLevel().equals(MembershipType.NO_MEMBERSHIP)) {
            returnValue = orderActions.activateMembership(this, membershipType);
        } else {
            returnValue = orderActions.updateMembership(this, membershipType);
        }
        if (!((returnValue.startsWith("Activated") || returnValue.startsWith("Changed")) && returnValue.contains("Confirmation"))) {
            returnValue = "Error while activating Membership for " + this.getShippingAddress().split("@")[0];
        }
        return returnValue;
    }
}
