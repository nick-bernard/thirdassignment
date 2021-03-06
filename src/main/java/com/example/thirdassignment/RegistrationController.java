package com.example.thirdassignment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Controller
public class RegistrationController {
    // @Value("#{environment.accesskey}")
    @Value("${accessKey}")
    String accesskey;
    @Value("${secretKey}")
    String secretkey;
    @Value("${bucketName}")
    String bucketName;



    @GetMapping("/register")
    public ModelAndView renderRegistrationPage(){

        return new ModelAndView("register");
    }

    /*
    @GetMapping("/greeting")
    public ModelAndView greeting(@RequestParam(name = "name", required = true) String name) {
        ModelAndView mv = new ModelAndView("greeting");
        mv.addObject("name", name+"lol");
        return mv;
    }
    */


    /*
    @GetMapping("/index")
    public ModelAndView renderUploadPage() {
        //System.out.println(accesskey  + bucketName + secretkey);
        return new ModelAndView("uploadFiles");
    }
    */

    @PostMapping(value = "/add")
    public ModelAndView registerNewUser(@RequestParam("Photo") MultipartFile image,
                                        @RequestParam(name = "Name") String name,
                                        @RequestParam(name = "Username") String username,
                                        @RequestParam(name = "Password") String password,
                                        @RequestParam(name = "Bio") String bio
                                        )
    {
        ModelAndView returnPage = new ModelAndView();
        System.out.println("description      " + name);
        System.out.println(image.getOriginalFilename());

        BasicAWSCredentials cred = new BasicAWSCredentials(accesskey, secretkey);
        // AmazonS3Client client=AmazonS3ClientBuilder.standard().withCredentials(new
        // AWSCredentialsProvider(cred)).with
        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
                .withRegion(Regions.US_EAST_1).build();

        try {
            PutObjectRequest put = new PutObjectRequest(bucketName, image.getOriginalFilename(),
                    image.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
            client.putObject(put);

            String imgSrc = "http://" + bucketName + ".s3.amazonaws.com/" + image.getOriginalFilename();

            returnPage.setViewName("showAddedToDB");
            returnPage.addObject("name", name);
            returnPage.addObject("username", username);
            returnPage.addObject("bio", bio);
            returnPage.addObject("imgSrc", imgSrc);

            //Save this in the DB.



            //Save this in the DB.



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            returnPage.setViewName("error");
        }

        return returnPage;
    }

    /*
    @GetMapping("/profile")
    public String renderProfile(){

        // Fetch the image from database
        // add object to the view

        // return the view
    }
     */

}

