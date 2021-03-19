package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;

public class Settings {

    String desc, image, title;

   public Settings(String title, String image, String desc) {

        this.desc = desc;
        this.image = image;
        this.title = title;
    }

    public String getdesc() {
        return desc;
    }
    public String getimage(String image) {
        return image;
    }
    public String gettitle(String title) {
        return title;
    }

    public void setdesc(String desc) {
        this.desc = desc;
    }
    public void setimage(String image) {
        this.image = image;
    }
    public void settitle(String title) {
        this.title = title;
    }

}
