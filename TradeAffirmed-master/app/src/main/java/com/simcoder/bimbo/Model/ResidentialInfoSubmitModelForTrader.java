
package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;
public class ResidentialInfoSubmitModelForTrader {

    String address , gpscode, street,residenceinfoapprovestatus;

    public ResidentialInfoSubmitModelForTrader(String gpscode, String  street, String residenceinfoapprovestatus) {
    }


    public ResidentialInfoSubmitModelForTrader(
            String address , String gpscode, String  street,String residenceinfoapprovestatus
    ) {
        this.address = address;
        this.gpscode= gpscode;
        this.street = street;
        this.residenceinfoapprovestatus = residenceinfoapprovestatus;

    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getGpscode() {
        return gpscode;
    }


    public void setGpscode(String gpscode) {
        this.gpscode = gpscode;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public String getResidenceApproveStatus() {
        return residenceinfoapprovestatus;
    }


    public void setResidence(String residenceinfoapprovestatus) {
        this.residenceinfoapprovestatus = residenceinfoapprovestatus;
    }




}