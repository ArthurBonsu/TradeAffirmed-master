package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

        import java.util.List;

public class ResidenceReceiptSetter {

    String receiptstatus;

    public ResidenceReceiptSetter() {
    }


    public ResidenceReceiptSetter(String   receiptstatus) {
        this.receiptstatus = receiptstatus;

    }

    public String getreceiptstatus() {
        return receiptstatus;
    }


    public void setreceiptstatus(String receiptstatus) {
        this.receiptstatus = receiptstatus;
    }


}