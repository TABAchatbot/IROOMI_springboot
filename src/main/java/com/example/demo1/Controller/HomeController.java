package com.example.demo1.Controller;

import com.example.demo1.Dto.UserDto;
import com.example.demo1.Service.UserService;
import com.example.demo1.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/")
    public String register(RedirectAttributes redirect, UserDto user)
    {
        if (userService.join(user))
        {
            redirect.addAttribute("msg","회원가입이 완료되었습니다");
        }
        else
        {
            redirect.addAttribute("msg", "중복된 아이디가 존재합니다");
        }

        return "redirect:result_alarm";
    }

    @RequestMapping(value = "/result_alarm")
    public String result_alarm(@RequestParam("msg") String param, Model model)
    {
        model.addAttribute("msg", param);
        return "result_alarm";
    }



    @RequestMapping("/")
    public String home (Model model)
    {
        List<UserDto> userList = userService.getUserList();
        model.addAttribute("userList", userList);

        return "home";
    }


}
