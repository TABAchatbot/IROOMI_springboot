package com.example.demo1.Dao;

import com.example.demo1.Dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UseMapper {
    @Insert("INSERT INTO user_1 VALUES (#{Id}, #{Pw}, #{Gender})")
    void InsertUser(UserDto dto);
    @Select("SELECT * FROM user_1 WHERE Id=#{Id}")
    UserDto selectOneUser(String Id);
    @Select("SELECT * FROM user_1")
    List<UserDto> selectAllUser();
}
