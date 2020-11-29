package com.example.thirdassignment;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsiteController {

    @GetMapping("/login2")
    public ModelAndView login(@RequestParam(name="name", required=false, defaultValue="world") String name){

        System.out.println("In login() controller");
        ModelAndView returnPage = new ModelAndView();
        String s = new String("Login " + name);
        returnPage.addObject("loginText", s);

        return returnPage;
    }

    // Takes and saves info from the form
    @PostMapping("/saveInfo")
    public String saveUserInfo(
        @RequestParam(name="fname", required=true) String fname,
        @RequestParam(name="lname", required=true) String lname,
        Model view)
    {
        System.out.println(fname + " " + lname);
        ////////////////////////
        // Save to database!! //
        ////////////////////////
        view.addAttribute("name", fname + " " + lname);

        return "saveConfirmation";
    }

    @GetMapping("/artworks")
    public ModelAndView renderArtworksPage(){
        return new ModelAndView("artworks");
    }

    @GetMapping("/register")
    public ModelAndView renderRegistrationPage(){
        return new ModelAndView("register");
    }

}
