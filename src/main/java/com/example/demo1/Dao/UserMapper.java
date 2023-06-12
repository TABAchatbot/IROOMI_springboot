package com.example.demo1.Dao;

import com.example.demo1.Dto.User;
import com.example.demo1.Dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM MEM_INFO WHERE user_id=#{Id}")
    User findByUserid(String Id);

}
