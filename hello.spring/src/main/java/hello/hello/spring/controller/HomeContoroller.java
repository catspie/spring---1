package hello.hello.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoroller {

    /**
     *  메인 
     */
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
