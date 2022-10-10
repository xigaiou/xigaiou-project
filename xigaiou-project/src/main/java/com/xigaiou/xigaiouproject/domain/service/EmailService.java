package com.xigaiou.xigaiouproject.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void contextLoads(String subject, String text, String to, String from) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(to);
        message.setFrom(from);
        mailSender.send(message);
    }

    public void contextAndFileLoads(String subject, String text, String filePath,
                                    String to, String from) throws MessagingException {
        //邮件设置2：一个复杂的邮件
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject(subject);
            helper.setText(text+"</br>"+"<b style='color:red'>html5红色标签尝试</b>", true);

            //发送附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            //获取附件的文件名
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);

            helper.setTo(to);
            helper.setFrom(from);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
