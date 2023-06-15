package com.example.demo1.Dao;

import com.example.demo1.Dto.Task;
import com.example.demo1.Dto.User;
import com.example.demo1.Dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM MEM_INFO WHERE mem_id=#{Id}")
    User findByUserid(String Id);

    @Insert("INSERT INTO MEM_INFO(\n" +
            "  mem_no,\n" +
            "  mem_id,\n" +
            "  pw,\n" +
            "  name,\n" +
            "  gender,\n" +
            "  birth_date\n" +
            ") VALUES (mem_no_seq.NEXTVAL, #{Id}, #{Pw}, #{Username}, #{Gender}, #{Birth_date} )")
    void InsertUser(User user);
    //SELECT TO_DATE('2023-06-15', 'YYYY-MM-DD') AS converted_date;

}
