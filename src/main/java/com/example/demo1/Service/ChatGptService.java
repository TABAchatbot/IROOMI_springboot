package com.example.demo1.Service;

import org.springframework.beans.factory.annotation.Value;
import com.example.demo1.Dto.ChatGptRequestDto;
import com.example.demo1.Dto.ChatGptResponseDto;
import com.example.demo1.Dto.QuestionRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptService {
    private static RestTemplate restTemplate = new RestTemplate();

    //***************application.properties 내 설정값 사용*********************

    @Value("${chatgpt.api.url}")
    private String apiUrl;

    @Value("${chatgpt.api.key}")
    private String apiKey;

    @Value("${chatgpt.model}")
    private String model;

    @Value("${chatgpt.max-token}")
    private Integer maxToken;

    @Value("${chatgpt.temperature}")
    private Double temperature;

    @Value("${chatgpt.top-p}")
    private Double topP;

    @Value("${chatgpt.media-type}")
    private String mediaType;


    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mediaType));
        headers.add("Authorization", "Bearer " + apiKey);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                apiUrl,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                model,
                                requestDto.getQuestion(),
                                maxToken,
                                temperature,
                                topP
                        )
                )
        );
    }
}
