package com.xigaiou.xigaiouproject.application.api;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.application.service.XigaiouAppService;
import com.xigaiou.xigaiouproject.common.Handle;
import com.xigaiou.xigaiouproject.domain.entity.ArticleRequest;
import com.xigaiou.xigaiouproject.domain.entity.Cat;
import com.xigaiou.xigaiouproject.domain.entity.Employees;
import com.xigaiou.xigaiouproject.domain.entity.MyClass;
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
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * 测试lambda
     */
    @GetMapping("/test-lambda")
    public void testLambda(){
        Runnable r1 = () -> System.out.println("testing Lambda!");
        r1.run();

        System.out.println("---------------------------");

        //在不修改实体类的情况下，实现对类的排序
        ArticleRequest articleRequest1 = new ArticleRequest("a","b","c");
        ArticleRequest articleRequest2 = new ArticleRequest("aa","bb","cc");
        ArticleRequest articleRequest3 = new ArticleRequest("aaa","bbb","ccc");

        ArticleRequest[] articleRequests = {articleRequest2,articleRequest1,articleRequest3};

        Arrays.sort(articleRequests,(o1, o2)->Integer.compare(o1.getTitle().length(), o2.getTitle().length()));
        System.out.println(Arrays.toString(articleRequests));

        System.out.println("---------------------------");

        System.out.println(Arrays.toString(sethandler("12345 abcde あいうえお", (str) -> str.split(" "))));

    }

    private String[] sethandler(String str, Handle func){
        return func.handle(str);
    }

    @GetMapping("/test-method-reference")
    public void testMethodReference(){
        Runnable r1 = () -> System.out.println("123123");
        r1.run();

        /*
        * 四大内置函数式接口
        * Consumer<T> 消费型接口  特点：有输入无输出
        *       void accept(T t);
        *
        * Supplier<T> 供给型接口  特点：有输出无输入
        *       T get();
        *
        * Function<T, R> 函数型接口 特点：有输入有输出
        *       R apply(T t);
        *
        * Predicate<T> 断言型接口 特点：有输入输出Boolean
        *       Boolean test(T t);
        *
        * */

        //可转为方法引用
        Consumer<String> fun = System.out::println;
        fun.accept("123");

        //构造器引用
        Function <String, MyClass> fun2 = (n) -> new MyClass(n);
        Function <String, MyClass> fun3 = MyClass::new;
        System.out.println(fun2.apply("fun2-test").toString());
        System.out.println(fun3.apply("fun3-test").toString());

        //数组引用
        Function<Integer, Integer[]> fun4 = (n) -> new Integer[n];
        Function<Integer, Integer[]> fun5 = Integer[]::new;
        System.out.println(Arrays.toString(fun4.apply(3)));
        System.out.println(Arrays.toString(fun5.apply(5)));

    }

    /**
     * 创建 Stream
     */
    @GetMapping("/test-stream")
    public void testStream(){
        //1.通过Collection系列集合提供的stream()创建流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过 Arrays 中的方法stream()获取流
        ArticleRequest[] articleRequests = new ArticleRequest[10];
        Stream<ArticleRequest> steam2 = Arrays.stream(articleRequests);

        //3.通过stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.无限流
        //迭代法
        Stream<Integer> stream = Stream.iterate(0, (x) -> x+2);
        stream.limit(10).forEach(System.out::println);

        System.out.println("--------------------");

        //生成法
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }

    @GetMapping("test-map")
    public void testMap(){
        //映射
        List<String> list1 = Arrays.asList("aaa", "bb", "cc");
        list1.stream()
                .map(String::toUpperCase)//作用在每个上面
                .forEach(System.out::println);

        System.out.println("-------------------");

        Stream<Stream<Character>> stream = list1.stream()
                .map(this::filterArticleRequest);//{{a,a,a},{b,b},{c,c}}

        stream.forEach((sm) ->{
            sm.forEach(System.out::println);
        });

        System.out.println("-------------------");
        //flatMap相当于addAll()
        Stream<Character> stream1 = list1.stream()
                .flatMap(this::filterArticleRequest);//{a,a,a,b,b,c,c}
    }

    public Stream<Character> filterArticleRequest(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    @GetMapping("test-middle-handle")
    public void testMidddleHandle(){
        //在不修改实体类的情况下，实现对类的排序
        ArticleRequest articleRequest1 = new ArticleRequest("a","b","c");
        ArticleRequest articleRequest2 = new ArticleRequest("aa","bb","cc");
        ArticleRequest articleRequest3 = new ArticleRequest("aaa","bbb","ccc");
        List<ArticleRequest> list = Arrays.asList(articleRequest1, articleRequest2, articleRequest3);
        //中间操作只是描述操作内容，不执行，终止操作时一次性全部执行，这被称为“惰性求值”
        list.stream().filter((oneArticle) -> oneArticle.getAuthor().length() >= 2).forEach(System.out::println);

    }

    @GetMapping("test-reduce")
    public void testReduce(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x,y)-> x + y );
        System.out.println(sum);

    }
}

