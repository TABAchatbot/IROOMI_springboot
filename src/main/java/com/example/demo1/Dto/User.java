package com.example.demo1.Dto;

public class User {
    private String Id;
    private String Pw;
    private String Username;

    private int Gender;
    private String Birth_date;
    private String Job;
    private int Prepare_month;


    //Getter & Setter_____________________________
    public String getBirth_date() {
        return Birth_date;
    }

    public void setBirth_date(String birth_date) {
        Birth_date = birth_date;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public int getPrepare_month() {
        return Prepare_month;
    }

    public void setPrepare_month(int prepare_month) {
        Prepare_month = prepare_month;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPw() {
        return Pw;
    }

    public void setPw(String pw) {
        Pw = pw;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

}
