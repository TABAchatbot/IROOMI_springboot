package com.example.demo1.Controller;

import com.example.demo1.Dto.ChatGPTRequest;
import com.example.demo1.Dto.ChatGPTResponse;
import com.example.demo1.Service.ChatGPTResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class CustomBotController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    //DB : 만들어진 Todolist 저장위한 ChatGPTResponseService 주입
    @Autowired
    private ChatGPTResponseService responseService;



    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGPTRequest request=new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);

        //DB : chatGPTResponse 값을 데이터베이스에 저장
        //responseService.createTodolist(chatGPTResponse.getChoices().get(0).getMessage().getContent());
        responseService.createWeeklyPlan(chatGPTResponse.getChoices().get(0).getMessage().getContent());

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();

    }

}
