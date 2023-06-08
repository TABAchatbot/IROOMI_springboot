package com.example.demo1.Service;

import com.example.demo1.Dao.TodolistMapper;
import com.example.demo1.Dao.UseMapper;
import com.example.demo1.Dto.TodolistDto;
import com.example.demo1.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGPTResponseService {

    @Autowired
    private TodolistMapper todolistMapper;

    //아래 메써드에서 input은 chatGPTresponse 값
    public boolean createTodolist(String input){

        System.out.println(input); //출력테스트용
        //todolistMapper.InsertTodolist(dto);
        System.out.println("----------row 분리 시작----------------");

        // 입력된 문자열을 줄바꿈을 기준으로 행(row)으로 분리
        String[] rows = input.split("\n");

        for(int i = 0; i < rows.length; i++){

            System.out.println(rows[i]);

            String[] columns = rows[i].split("\\|");
            for(int j=0; j<columns.length; j++){
                System.out.println(columns[j].trim());
                System.out.println(j);
            }
            System.out.println("=================================");
        }

        /*
        // 행(row)을 처리하여 데이터베이스에 저장
        for (int i = 2; i < rows.length; i++) {
            String[] columns = rows[i].split("|");
            String todo = columns[1].trim();
            String duration = columns[2].trim();
            String priority = columns[3].trim();

            // Todo 객체 생성
            TodolistDto dto = new TodolistDto();
            dto.setTodoNo(1) ; //test값
            dto.setMemNo(1); //test값
            dto.setPriority(Integer.parseInt(priority));
            dto.setTodoDetail(todo);
            dto.setDuration(Integer.parseInt(duration));

            //이런식으로...// 데이터베이스에 입력
            todolistMapper.InsertTodolist(dto);
        }
        */

        return true;
    }

    //밑에는 참고할 코드,,,, 참고하고 삭제하기
    //application 테스트시에는 잠시 주석처리 해놓기
    /*

    @Override
    public boolean join(UserDto dto){
        UserDto user = userMapper.selectOneUser(dto.getId());

        if (user != null)
        {
            return false;
        }
        else
        {
            userMapper.InsertUser(dto);
            return true;
        }
    }

    @Override
    public List<UserDto> getUserList(){
        return userMapper.selectAllUser();
    }

    */


}
