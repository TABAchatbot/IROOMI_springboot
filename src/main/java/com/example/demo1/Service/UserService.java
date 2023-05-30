package com.example.demo1.Service;

import com.example.demo1.Dto.UserDto;

import java.util.List;

public interface UserService {
    //로그인 메쏘드 선언
    public boolean join(UserDto user);
    //회원출력 메쏘드 선언
    public List<UserDto> getUserList();

    //**** 인터페이스는 구현해주는 implement 코드가 필요-> 다른파일에서 정의해준다
}
