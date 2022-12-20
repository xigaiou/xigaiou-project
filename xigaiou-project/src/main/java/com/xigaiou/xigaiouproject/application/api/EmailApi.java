package com.xigaiou.xigaiouproject.application.api;

import com.xigaiou.xigaiouproject.application.service.EmailAppService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@Slf4j
@RequestMapping("/mail")
public class EmailApi {
    /**
     * emailAppService
     */
    @Autowired
    private EmailAppService emailAppService;

    /**
     * 发送一封邮件
     * @param subject subject
     * @param text text
     * @param to to
     * @param from from
     * @return String
     */
    @ApiOperation(value = "label", notes = "发送一条消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "标题",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "text", value = "内容",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "to", value = "送达地址",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "from", value = "发送地址",dataTypeClass = Long.class, required = true, dataType = "String")
    })
    @GetMapping("/sent")
    public String contextLoads(
            @RequestParam("subject") String subject,
            @RequestParam("text") String text,
            @RequestParam("to") String to,
            @RequestParam("from") String from){
        emailAppService.contextLoads(subject, text, to, from);
        return "sent success";
    }

    /**
     * 发送一条带文件的消息
     * @param subject subject
     * @param text text
     * @param filePath filePath
     * @param to to
     * @param from from
     * @return String
     * @throws MessagingException MessagingException
     */
    @ApiOperation(value = "label", notes = "发送一条带文件的消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "标题",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "text", value = "内容",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "filePath", value = "文件路径",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "to", value = "送达地址",dataTypeClass = Long.class, required = true, dataType = "String"),
            @ApiImplicitParam(name = "from", value = "发送地址",dataTypeClass = Long.class, required = true, dataType = "String")
    })
    @GetMapping("/sentFile")
    public String contextAndFileLoads(@RequestParam("subject") String subject,
                                      @RequestParam("text") String text,
                                      @RequestParam("filePath") String filePath,
                                      @RequestParam("to") String to,
                                      @RequestParam("from") String from){
        try {
            emailAppService.contextAndFileLoads(subject, text, filePath, to, from);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "sent success";
    }

}
