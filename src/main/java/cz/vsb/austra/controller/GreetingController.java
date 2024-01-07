package cz.vsb.austra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping("/hello")
    String hello(){
        return "Hi folks";
    }

    @RequestMapping("/greeting")
    String greeting(){
        return "Hello Spring world";
    }

}
