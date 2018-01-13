package net.kasler.webserver.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class HelloWorldController {


    @RequestMapping(
            value = "/**",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json"
    )
    HelloWoldObject helloWoldObject(){
        return new HelloWoldObject("hello world", ThreadLocalRandom.current().nextInt());
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class HelloWoldObject{
        String message;
        int errorCode;
    }
}
