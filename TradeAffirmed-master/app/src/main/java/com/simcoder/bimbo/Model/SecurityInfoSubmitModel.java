
package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;
public class SecurityInfoSubmitModel {

    String gpscode , gpsimage, securityinfoapprovestatus;

    public SecurityInfoSubmitModel(  String gpsimage,String securityinfoapprovestatus) {
    }


    public SecurityInfoSubmitModel(
            String   gpscode , String gpsimage,String securityinfoapprovestatus
    ) {
        this.gpscode = gpscode;
        this.gpsimage= gpsimage;
        this.securityinfoapprovestatus = securityinfoapprovestatus;


    }

    public String getGpsCode() {
        return gpscode;
    }


    public void setGpsCode(String gpscode) {
        this.gpscode = gpscode;
    }


    public String getGpsImage() {
        return gpsimage;
    }


    public void setGpsImage(String gpsimage) {
        this.gpsimage = gpsimage;
    }


    public String getSecurityinfoapprovestatus() {
        return securityinfoapprovestatus;
    }


    public void setSecurityinfoapprovestatus(String securityinfoapprovestatus) {
        this.securityinfoapprovestatus = securityinfoapprovestatus;
    }





}