package com.example.thirdassignment;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public String renderHomePage(){
        return "index";
    }




    @GetMapping("/artworks")
    public ModelAndView renderArtworksPage(){

        return new ModelAndView("artworks");
    }



}
