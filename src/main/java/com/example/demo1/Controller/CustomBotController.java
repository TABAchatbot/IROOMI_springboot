package com.example.demo1.Controller;

import com.example.demo1.Dto.ChatGPTRequest;
import com.example.demo1.Dto.ChatGPTResponse;
import com.example.demo1.Service.AuthenticationService;
import com.example.demo1.Service.ChatGPTResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.demo1.Dto.Task;

import java.util.Map;

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

    @Autowired
    private AuthenticationService authenticationService;

    /*원래 있던 GetMapping 함수*/



    /*바꾼 PostMapping 함수*/

    @PostMapping("/todo-list")
    public String createTodolist(@RequestBody Map<String, String> requestData) {
        String prompt = requestData.get("prompt");

        //출력테스트용
        System.out.println("prompt: " + prompt);


        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);

        //출력테스트용
        System.out.println("response : "+ chatGPTResponse.getChoices().get(0).getMessage().getContent());

        //DB: chatGPTResponse 값을 데이터베이스에 저장하는 메소드
        String id = "TEST_ID";
        responseService.createTodolist(chatGPTResponse.getChoices().get(0).getMessage().getContent(), id);

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }


    @PostMapping("/weekly-plan")
    public String createWeeklyplan(@RequestBody Map<String, String> requestData){

        String available_time = requestData.get("available_time");

        //출력테스트용
        System.out.println("available_time: " + available_time);

        String id = "TEST_ID"; // ID 세팅... 매개변수에서 가져와야함 ....

        /*id로 TODOLIST 첫번째 항목 가져오기*/
        Task task =  responseService.retrieveFirstTodolistById(id); // TASK 형식으로 가져옴...

        //출력테스트용
        System.out.println("Task Id : " + task.getId());
        System.out.println("Task Priority : " + task.getPriority());
        System.out.println("Task Task : " + task.getTask());
        System.out.println("Task Estimated_week : " + task.getEstimated_week());



        /*chatGPT 에 보낼 prompt 만들기*/
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append(task.getEstimated_week()).append("주 동안 ").append(task.getTask()).append("을 할거야.");
        promptBuilder.append(" ").append(available_time).append("시간씩 할애할 수 있어.");
        promptBuilder.append("매주 5일동안 할거야.");
        promptBuilder.append(task.getEstimated_week()).append("주 동안 할 주간계획표 생성해주되, ");
        promptBuilder.append("day, study_hours, topic 이름의 항목으로 표로 작성해주고, 타입은 json값으로 알려줘. JSON 형식은 아래와 같은 문자열로 시작하게 출력해줘. JSON값만 답으로 줘. 한국어로 알려줘\n" +
                "{\n" +
                "  \"1주차\": [\n" +
                "    {\n" +
                "      \"day\": \"월\",\n" +
                "      \"study_hours\": 2,\n" +
                "      \"topic\": \"\"\n" +
                "    }\n" +
                "  ]\n" +
                "\n" +
                "}");


        String prompt = promptBuilder.toString();

        //출력테스트용
        System.out.println(prompt);

        /*chatGPT 에 보내기*/
        ChatGPTRequest request=new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);

        //DB : chatGPTResponse 값을 데이터베이스에 저장하는 메쏘드
        responseService.createWeeklyPlan(chatGPTResponse.getChoices().get(0).getMessage().getContent());

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();

    }

}
