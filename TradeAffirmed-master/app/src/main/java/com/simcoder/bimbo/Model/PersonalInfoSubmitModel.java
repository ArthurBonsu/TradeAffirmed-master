package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;
public class PersonalInfoSubmitModel {

    String uid;  String name; String phone; String email; String gender; String age; String country; String personalinfoapprovestatus;

    public PersonalInfoSubmitModel(  String name, String phone, String email, String gender, String age, String country, String personalinfoapprovestatus) {
    }


    public PersonalInfoSubmitModel(
           String uid,  String name, String phone, String email, String gender, String age, String country, String personalinfoapprovestatus
    ) {
        this.uid = uid;
        this.name= name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.personalinfoapprovestatus = personalinfoapprovestatus;
    }

    public String getUid() {
        return uid;
    }


    public void setUid(String uid) {
        this.uid = uid;
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
