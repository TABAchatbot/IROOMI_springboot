package com.example.demo1.Service;

import com.example.demo1.Dao.TodolistMapper;
import com.example.demo1.Dao.WeeklyplanMapper;
import com.example.demo1.Dto.StudyPlan;
import com.example.demo1.Dto.TodoList;
import com.example.demo1.Dto.Task;
import com.example.demo1.Dto.WeeklyPlan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTResponseService {

    @Autowired //TodolistMapper 매핑
    private TodolistMapper todolistMapper;

    @Autowired //WeeklyplanMapper 매핑
    private WeeklyplanMapper weeklyplanMapper;

    //Weekly_plan 만드는 method
    public boolean createWeeklyPlan(String jsonData){
        System.out.println(jsonData); //출력테스트용

        // 1. JSON 문자열을 WeeklyPlan 객체로 변환
        Type type = new TypeToken<Map<String, List<StudyPlan>>>() {}.getType();
        Map<String, List<StudyPlan>> studyPlanMap = new Gson().fromJson(jsonData, type);

        for (Map.Entry<String, List<StudyPlan>> entry : studyPlanMap.entrySet()) {
            String weekNo = entry.getKey();
            List<StudyPlan> studyPlans = entry.getValue();

            for (StudyPlan studyPlan : studyPlans) {
                String weekDay = studyPlan.getDay();
                String topic = studyPlan.getTopic();
                int estimatedTime = studyPlan.getStudy_hours();

                WeeklyPlan weeklyplan = new WeeklyPlan();
                weeklyplan.setWeekNo(weekNo);
                weeklyplan.setWeekDay(weekDay);
                weeklyplan.setTopic(topic);
                weeklyplan.setEstimatedTime(estimatedTime);

                weeklyplanMapper.InsertWeeklyplan(weeklyplan);
            }
        }

        System.out.println("Data inserted successfully.");

        return true;


    }















    //TODOLIST 만드는 method
    public boolean createTodolist(String input){

        System.out.println(input); //출력테스트용

        //------------실제코드-------------------------------
        // 1. JSON 파싱하여 Java 객체로 변환
        TodoList todoList = parseJsonToTodoList(input);

        // 2. Tibero DB에 Java 객체 저장
        List<Task> tasks = todoList.getTasks();
        for (Task task : tasks) {
            todolistMapper.InsertTodolist(task);
        }


        return true;
    }
    private static TodoList parseJsonToTodoList(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, TodoList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
