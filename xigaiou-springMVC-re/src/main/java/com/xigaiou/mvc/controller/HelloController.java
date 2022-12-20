package com.xigaiou.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.xigaiou.mvc.bean.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class HelloController {
    // @RequestMapping注解：处理请求和控制器方法之间的映射关系
    // @RequestMapping注解的value属性可以通过请求地址匹配请求，/表示的当前工程的上下文路径
    // localhost:8080/springMVC/
    @RequestMapping("/")
    public String index() {
        //设置视图名称
        return "index";
    }

    @RequestMapping(
            value = {"/hello","/test"}
    )
    public String HelloWorld() {
        return "hello";
    }

    //使用ServletAPI向request域对象共享数据
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request){
        request.setAttribute("testScope", "hello,servletAPI");
        return "success";
    }

    //使用ModelAndView向request域对象共享数据
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //
        //ModelAndView有Model和View的功能
        //Model主要用于向请求域共享数据
        //View主要用于设置视图，实现页面跳转
        //
        ModelAndView mav = new ModelAndView();
        //向请求域共享数据
        mav.addObject("testScope", "hello,ModelAndView");
        //设置视图，实现页面跳转
        mav.setViewName("success");
        return mav;
    }

    //使用Model向request域对象共享数据
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testScope", "hello,Model");
        return "success";
    }

    //使用map向request域对象共享数据
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        map.put("testScope", "hello,Map");
        return "success";
    }

    //使用ModelMap向request域对象共享数据
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testScope", "hello,ModelMap");
        return "success";
    }

    //向session域共享数据
    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope", "hello,session");
        return "success";
    }

    //向application域共享数据
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope", "hello,application");
        return "success";
    }

    //转发视图(加上forward则实际转到的不经过视图转发器，直接就是最终路径)
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/success";
    }

    //重定向视图(同样不会被SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转)
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testHello";
    }

    @RequestMapping("/testResponseUser")
    @ResponseBody
    public String testResponseUser(){
        User user = new User("1001","admin","123456","23","男");

        return JSON.toJSONString(user);
    }

    /**
     * 文件上传
     * @param session session
     * @return ResponseEntity
     * @throws IOException IOException
     */
    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("WEB-INF/static/images/00001.png");
        //创建输入流
        InputStream is = Files.newInputStream(Paths.get(realPath));
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    /**
     * 上传文件
     * @param photo photo
     * @param session session
     * @return String
     * @throws IOException IOException
     */
    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("WEB-INF/static/images/");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        photo.transferTo(new File(finalPath));
        return "success";
    }

    @RequestMapping("/testIntercepter")
    public String testIntercepter(){
        return "success";
    }
}
