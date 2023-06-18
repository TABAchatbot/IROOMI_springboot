package com.example.demo1.Controller;

import com.example.demo1.Dto.UserDto;
import com.example.demo1.Service.UserService;
import com.example.demo1.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @PostMapping("/")
    @ResponseBody
    public String handleRequest() {
        // 간단한 메시지 작성
        String message = "API is functioning properly.";

        return message;
    }

}
