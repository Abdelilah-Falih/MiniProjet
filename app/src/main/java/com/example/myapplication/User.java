package com.example.myapplication;

public class User {
    private String firstname;
    private String lasttname;
    private String gender;
    private String city;

    public String getImage_path() {
        return image_path;
    }

    private String image_path;

    //region getters
    public String getFirstname() {
        return firstname;
    }

    public String getLasttname() {
        return lasttname;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }
    //endregion


    public User(String firstname, String lasttname, String gender, String city, String image_path) {
        this.firstname = firstname;
        this.lasttname = lasttname;
        this.gender = gender;
        this.city = city;
        this.image_path = image_path;
    }

    String getFullname(){
        return String.format("%s %s", getLasttname(), getFirstname());
    }
}
