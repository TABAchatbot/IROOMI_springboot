package com.example.demo1.Dao;

import com.example.demo1.Dto.StudyPlan;
import com.example.demo1.Dto.Task;
import com.example.demo1.Dto.WeeklyPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeeklyplanMapper {

    @Insert("INSERT INTO WEEKLY_PLAN (weekly_plan_no, mem_id, week_no, week_day, topic, estimated_time, created_time) VALUES (weekly_plan_no_seq.NEXTVAL, #{id}, #{weekNo}, #{weekDay}, #{topic}, #{estimatedTime}, SYSTIMESTAMP )")



    void InsertWeeklyplan(WeeklyPlan weeklyPlan); // weeklyPlan 에 멤버변수 id값 넣어야 할듯 ....
}
