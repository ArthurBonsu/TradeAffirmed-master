package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;

public class AllCandidatesApproved {

    String approvalID,  uid,  tid,age, name, image,email,  aid,  status, gender, country, date;
    public AllCandidatesApproved() {

    }



    public AllCandidatesApproved(
            String approvalID, String uid, String tid,String age, String name, String image,String email, String aid, String status, String gender,String country,String date ) {
        this.approvalID = approvalID;
        this.uid= uid;
        this.tid = tid;
        this.age = age;
        this.name = name;
        this.image = image;
        this.email = email;
        this.aid = aid;
        this.status = status;
        this.gender = gender;
        this.country = country;
        this.date = date;
    }

    public String getapprovalID() {
        return approvalID;
    }


    public void setapprovalID(String approvalID) {
        this.approvalID = approvalID;
    }



    public String getuid() {
        return uid;
    }


    public void setuid(String uid) {
        this.uid = uid;
    }


    public String gettid() {
        return tid;
    }


    public void settid(String tid) {
        this.tid = tid;
    }

    public String getage() {
        return age;
    }


    public void setage(String age) {
        this.age = age;
    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    public String getimage() {
        return image;
    }


    public void setimage(String image) {
        this.image = image;
    }


    public String getemail() {
        return email;
    }


    public void setemail(String email) {
        this.email = email;
    }

    public String getaid() {
        return aid;
    }


    public void setaid(String aid) {
        this.aid = aid;
    }
    public String getstatus() {
        return status;
    }


    public void setstatus(String status) {
        this.status = status;
    }

    public String getgender() {
        return gender;
    }


    public void setgender(String gender) {
        this.gender = gender;
    }
    public String getcountry() {
        return country;
    }


    public void setcountry(String country) {
        this.country = country;
          }
    public String getdate() {
        return date;
    }


    public void setdate(String date) {
        this.date = date;
        }

}
