package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;

public class PersonalReceiptSetter {

    String receiptstatus;

    public PersonalReceiptSetter() {
    }


    public PersonalReceiptSetter(String   receiptstatus) {
        this.receiptstatus = receiptstatus;

    }

    public String getreceiptstatus() {
        return receiptstatus;
    }


    public void setreceiptstatus(String receiptstatus) {
        this.receiptstatus = receiptstatus;
    }


}
