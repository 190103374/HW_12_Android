package com.example.loginregisterapplication;

import java.util.ArrayList;

public class DetailPost {
    private int char_id;
    private String name;
    private String img;
    private String birthday;
    private ArrayList<String> occupation;
    private String status;



    public ArrayList<String> getOccupation() {
//        occupation = new ArrayList<>();
//        String all = "";
//        for (String str : occupation) {
//            all += str;
//        }

        return occupation;
    }

    public String getStatus() {
        return status;
    }

    public int getChar_id() {
        return char_id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return birthday;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }
    public void setDate(String birthday) {
        this.birthday = birthday;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }
}
