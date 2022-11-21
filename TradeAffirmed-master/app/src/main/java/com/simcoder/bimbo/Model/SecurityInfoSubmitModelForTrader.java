
package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;
public class SecurityInfoSubmitModelForTrader {

    String gpscode , gpsimage, securityinfoapprovestatus, securityapprovalapprove, status, approvalID, uid, tid, natidimage;



    public SecurityInfoSubmitModelForTrader (
            String  tid, String gpscode,String  gpsimage,String natidimage, String approvalID, String securityinfoapprovestatus
    ) {
        this.tid =tid;
        this.gpscode = gpscode;
        this.gpsimage = gpsimage;
        this.natidimage = natidimage;
        this.approvalID = approvalID;
        this.securityinfoapprovestatus = securityinfoapprovestatus;

    }


    public String gettid() {
        return tid;
    }


    public void settid(String tid) {
        this.tid = tid;
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