package com.xigaiou.xigaiouproject.application.service;

import com.xigaiou.xigaiouproject.domain.entity.Cat;
import com.xigaiou.xigaiouproject.domain.entity.Person;
import com.xigaiou.xigaiouproject.domain.service.XigaiouService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class XigaiouAppService {
    @Autowired
    private XigaiouService xigaiouService;

    public Cat catPrint(){
        return xigaiouService.catPrint();
    }

    public Person personPrint(){
        return xigaiouService.personPrint();
    }
}
