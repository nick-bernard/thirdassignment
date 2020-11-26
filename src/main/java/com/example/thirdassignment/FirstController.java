package com.example.thirdassignment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @RequestMapping("/")
    public String hello(){
        return "Hello from FirstController!";
    }
}
