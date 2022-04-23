package cap.workwalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeController {

    @GetMapping("/login/login")
    public String hello(){
        return "react-spring 연결 - 현재 서버시간은 "+new Date() +"입니다. \n";
    }

    @GetMapping("/test")
    public String testcon(){
        return "react-spring 연결 테스트";
    }

}
