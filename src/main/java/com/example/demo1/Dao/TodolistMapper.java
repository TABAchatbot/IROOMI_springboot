package com.example.demo1.Dao;

import com.example.demo1.Dto.Task;
import com.example.demo1.Dto.UserDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TodolistMapper {
    //실제 sql문 매핑하는 거 넣기
    @Insert("INSERT INTO TO_DO_LIST (todo_no, mem_id, todo_detail, priority, estimated_week) VALUES (todo_no_seq.NEXTVAL, #{id}, #{task}, #{priority}, #{estimated_week})")

    void InsertTodolist(Task dto);

    @Select("select MEM_ID, TODO_DETAIL, PRIORITY, ESTIMATED_WEEK from TO_DO_LIST where MEM_ID=#{id} and PRIORITY = '1';")
    @Results({
            @Result(property = "id", column = "MEM_ID"),
            @Result(property = "task", column = "TODO_DETAIL"),
            @Result(property = "priority", column = "PRIORITY"),
            @Result(property = "estimated_week", column = "ESTIMATED_WEEK")
    })
    Task SelectTodolist(String Id);
}
