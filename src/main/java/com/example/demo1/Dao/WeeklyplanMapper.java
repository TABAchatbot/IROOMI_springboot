package com.example.demo1.Dao;

import com.example.demo1.Dto.StudyPlan;
import com.example.demo1.Dto.Task;
import com.example.demo1.Dto.WeeklyPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeeklyplanMapper {

    @Insert("INSERT INTO WEEKLY_PLAN (weekly_plan_no, mem_no, week_no, week_day, topic, estimated_time) VALUES (weekly_plan_no_seq.NEXTVAL, mem_no_seq.NEXTVAL, #{weekNo}, #{weekDay}, #{topic}, #{estimatedTime})")
    //mem_no 값은 고쳐야함... 아직 test 값

    void InsertWeeklyplan(WeeklyPlan weeklyPlan);
}
