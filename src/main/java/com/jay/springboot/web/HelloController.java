package com.jay.springboot.web;

import com.jay.springboot.web.dto.HelloControllerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloControllerDto helloDto(@RequestParam("name") String name,
                                       @RequestParam("amount") int amount) {
        return new HelloControllerDto(name, amount);
    }
}
