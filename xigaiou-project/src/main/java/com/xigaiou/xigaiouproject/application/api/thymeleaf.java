package com.xigaiou.xigaiouproject.application.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class thymeleaf {
    @GetMapping("/test")
    public String test1(Map<String,Object> map){
        map.put("msg","<h1>Hello</h1>");
        map.put("users", Arrays.asList("qinjiang","kuangshen"));
        //classpath:/templates/thymeleaf.html
        return "thymeleaf";
    }
}
