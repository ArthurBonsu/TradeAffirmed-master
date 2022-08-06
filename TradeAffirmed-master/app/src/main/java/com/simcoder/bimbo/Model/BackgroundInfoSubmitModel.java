package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;

public class BackgroundInfoSubmitModel {

        String emcountry, ememail, empersionid, emphone, empidtype, uid, backgroundinfostatus;

        public BackgroundInfoSubmitModel( String emcountry, String ememail, String empersionid, String emphone, String empidtype) {
        }


        public BackgroundInfoSubmitModel(
                String uid, String emcountry, String ememail, String empersionid, String emphone, String empidtype,String backgroundinfostatus
        ) {
           this.uid = uid;
           this.emcountry= emcountry;
           this.ememail = ememail;
           this.empersionid = empersionid;
           this.emphone = emphone;
           this.empidtype = empidtype;
           this.backgroundinfostatus = backgroundinfostatus;
        }

        public String getUid() {
            return uid;
        }


        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getEmecountry() {
            return emcountry;
        }


        public void setEmcountry(String emcountry) {
            this.emcountry = emcountry;
        }


        public String getEmemail() {
            return ememail;
        }


        public void setEmemail(String ememail) {
            this.ememail = ememail;
        }

        public String getEmpersionid() {
            return empersionid;
        }


        public void setEmpersionid(String empersionid) {
            this.empersionid = empersionid;
        }


        public String getEmphone() {
            return emphone;
        }

        public void setEmphone(String emphone) {
            this.emphone = emphone;
        }


        public String getEmpidtype() {
            return empidtype;
        }


        public void setEmpidtype(String empidtype) {
            this.empidtype = empidtype;
        }


    public String getBackgroundinfostatus() {
        return backgroundinfostatus;
    }


    public void setBackgroundinfostatus(String backgroundinfostatus) {
        this.backgroundinfostatus = backgroundinfostatus;
    }



}
