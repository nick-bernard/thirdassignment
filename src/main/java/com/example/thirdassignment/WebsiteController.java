package com.example.thirdassignment;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsiteController {

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(name="name", required=false, defaultValue="world") String name){

        System.out.println("In login() controller");
        ModelAndView returnPage = new ModelAndView();
        String s = new String("Login " + name);
        returnPage.addObject("loginText", s);

        return returnPage;
    }

}
