package com.example.demo1.Dto;

public class TodolistDto {
    private int TodoNo;
    private int MemNo;

    private String TodoDetail;

    private int Priority;

    private int Duration;



    //Getter & Setter

    public int getTodoNo() {
        return TodoNo;
    }

    public void setTodoNo(int todoNo) {
        TodoNo = todoNo;
    }

    public int getMemNo() {
        return MemNo;
    }

    public void setMemNo(int memNo) {
        MemNo = memNo;
    }

    public String getTodoDetail() {
        return TodoDetail;
    }

    public void setTodoDetail(String todoDetail) {
        TodoDetail = todoDetail;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

}
