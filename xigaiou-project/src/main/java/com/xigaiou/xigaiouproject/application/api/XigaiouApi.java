package com.xigaiou.xigaiouproject.application.api;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.application.service.XigaiouAppService;
import com.xigaiou.xigaiouproject.domain.entity.Cat;
import com.xigaiou.xigaiouproject.domain.entity.Person;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api")
public class XigaiouApi {
    @Autowired
    private XigaiouAppService appService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/catPrint")
    public Cat catPrint(){
        return appService.catPrint();
    }

    @GetMapping("/personPrint")
    public Person personPrint(){
        return appService.personPrint();
    }

    @GetMapping("/hanziPrint")
    public int hanziPrint(){
        return "你是猪".length();
    }

    @RequestMapping("/getUser")
    public SwaggerConfig.User getUser(){
        return new SwaggerConfig.User();
    }

    @PostMapping("/stringToList")
    public JSONObject dealString(@RequestBody JSONObject info){
        try{
            if(info.containsKey("words") && Objects.nonNull(info.get("words"))){
                List<String> list = Arrays.asList(info.getString("words").split(","));
                info.put("words", list);
            }
        } catch (Exception e){
            log.error("words error!!!!!!!!!!!!!!!!!!!", e);
        }
        return info;
    }
}
