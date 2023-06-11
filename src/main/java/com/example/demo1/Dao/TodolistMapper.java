package com.example.demo1.Dao;

import com.example.demo1.Dto.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodolistMapper {
    //실제 sql문 매핑하는 거 넣기
    @Insert("INSERT INTO TO_DO_LIST (todo_no, mem_no, todo_detail, priority, duration) VALUES (todo_no_seq.NEXTVAL, mem_no_seq.NEXTVAL, #{task}, #{priority}, #{estimated_week})")
    //mem_no_seq.NEXTVAL 이 값은 나중에 Param 받아서 고쳐줘야함
    void InsertTodolist(Task dto);
}
