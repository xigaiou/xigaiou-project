package com.xigaiou.xigaiouproject.domain.service;

import com.xigaiou.xigaiouproject.domain.entity.Cat;
import com.xigaiou.xigaiouproject.domain.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class XigaiouService {
    @Autowired
    Cat cat;
    @Autowired
    Person person;
    public Cat catPrint(){
        return cat;
    }
    public Person personPrint(){
        return person;
    }
}
