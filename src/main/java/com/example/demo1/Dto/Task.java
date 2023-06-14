package com.example.demo1.Dto;

public class Task {

    private String id; // 추가함.... 고객번호로 바꿀 수도 있음 ...
    private String task;
    private String priority;
    private int estimated_week;


    //Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getEstimated_week() {
        return estimated_week;
    }

    public void setEstimated_week(int estimated_week) {
        this.estimated_week = estimated_week;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
