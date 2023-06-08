package com.example.demo1.Dao;

import com.example.demo1.Dto.TodolistDto;
import com.example.demo1.Dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodolistMapper {
    //실제 sql문 매핑하는 거 넣기
    @Insert("INSERT INTO TO_DO_LIST (todo_no, mem_no, todo_detail, priority, duration) VALUES (todo_no_seq.NEXTVAL, 1111, #{TodoDetail}, #{Priority}, #{Duration})")

    void InsertTodolist(TodolistDto dto);
}
