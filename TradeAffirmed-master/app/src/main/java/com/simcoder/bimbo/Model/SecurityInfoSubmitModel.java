
package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;
public class SecurityInfoSubmitModel {

    String gpscode , gpsimage, securityinfoapprovestatus, securityapprovalapprove, status, approvalID;

    public SecurityInfoSubmitModel(  String gpsimage,String securityinfoapprovestatus) {
    }


    public SecurityInfoSubmitModel(
            String gpscode, String gpsimage,String approvalID, String  securityinfoapprovestatus, String status
    ) {
        this.gpscode = gpscode;
        this.gpsimage= gpsimage;

        this.securityinfoapprovestatus = securityinfoapprovestatus;
        this.status = status;
        this.approvalID =approvalID;

    }


    public String getsecurityinfoapprovestatus() {
        return securityinfoapprovestatus;
    }


    public void setsecurityinfoapprovestatus(String securityinfoapprovestatus) {
        this.securityinfoapprovestatus = securityinfoapprovestatus;
    }

    public String getstatus() {
        return status;
    }


    public void setstatus(String status) {
        this.status = status;
    }
    public String getapprovalID() {
        return approvalID;
    }


    public void setapprovalID(String approvalID) {
        this.approvalID = approvalID;
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