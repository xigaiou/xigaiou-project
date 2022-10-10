package com.xigaiou.xigaiouproject.application.service;

import com.xigaiou.xigaiouproject.domain.service.EmailService;
import com.xigaiou.xigaiouproject.domain.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class EmailAppService {
    @Autowired
    private EmailService emailService;

    public void contextLoads(String subject, String text, String to, String from){
        emailService.contextLoads(subject, text, to, from);
    }

    public void contextAndFileLoads(String subject, String text, String filePath,
                                    String to, String from) throws MessagingException {
        emailService.contextAndFileLoads(subject, text, filePath, to, from);
    }
}
