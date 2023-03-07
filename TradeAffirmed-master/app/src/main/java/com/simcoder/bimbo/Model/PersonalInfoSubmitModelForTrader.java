package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;
public class PersonalInfoSubmitModelForTrader {

    String tid;  String name; String phone; String email; String gender; String age; String country; String personalinfoapprovestatus;
    String  image;
    public PersonalInfoSubmitModelForTrader(String name, String phone, String email, String gender, String age, String country, String personalinfoapprovestatus) {
    }




    public PersonalInfoSubmitModelForTrader(String tid, String name, String image, String phone, String email, String gender, String age, String personalinfoapprovestatus) {
        this.tid = tid;
        this.name= name;
        this.image = image;
        this.phone = phone;
        this.email =email;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.personalinfoapprovestatus = personalinfoapprovestatus;

    }



    public String getimage() {
        return image;
    }


    public void setimage(String image) {
        this.image = image;
    }

    public String getTid() {
        return tid;
    }


    public void setTid(String tid) {
        this.tid = tid;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAge() {
        return age;
    }


    public void setAge(String age) {
        this.age = age;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }
    public String getPersonalinfoapprovestatus() {
        return personalinfoapprovestatus;
    }


    public void setPersonalinfoapprovestatus(String personalinfoapprovestatus) {
        this.personalinfoapprovestatus = personalinfoapprovestatus;
    }
}
