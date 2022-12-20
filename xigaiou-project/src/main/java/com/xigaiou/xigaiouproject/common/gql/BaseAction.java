package com.xigaiou.xigaiouproject.common.gql;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * BaseAction作为底层的一个核心，实现了众多的通用性高的接口
 * @author 西盖欧
 * @date 2022-12-01
 */
@Data
@Slf4j//开启日志输出
public abstract class BaseAction<T> implements Action<T>{
    //下面这句是用作开启日志输出的，一般会在项目每个类的开头加入该注解，在这里我用@Slf4j平替
    //private static final Logger log = LoggerFactory.getLogger(BaseAction.class);
    private String ns;
    private String name;
    private String desc;
    //这里省略了 ActionAcl，它是作为业务判断逻辑，理解为权限控制，起到了验证的功能

    /**
     * 专用于Action的拦截器，只写了Before和 After
     */
    private ActionInterceptor actionInterceptor;

    public BaseAction(String name){
        this("", name, "");
    }

    public BaseAction(String ns, String name){
        this(ns, name, "");
    }

    public BaseAction(String ns, String name, String desc){
        this.ns = ns;
        this.name = name;
        this.desc = desc;
    }

    /**
     * 当desc为空时返回name
     * @return name或desc
     */
    public String getDataDesc(){
        String dataDesc = getDesc();
        return StringUtils.isEmpty(dataDesc) ? getName() : dataDesc;
    }

    public String getDataId(){
        return concatDataId(getNs(), getName());
    }

    public static String concatDataId(String ns, String name, String... exts){
        StringBuilder dataId = new StringBuilder(StringUtils.isEmpty(ns) ? name : String.format("%s.%s", ns, name));

        //单独写出来
        String[] extsTemp = exts;
        int extsLength = exts.length;

        for(int i=0; i < extsLength; i++){
            String ext = extsTemp[i];
            if(StringUtils.isNoneEmpty(ext)){
                dataId.append(".").append(ext);
            }
        }
        return dataId.toString();
    }

    public final T execute(ActionEnv actionEnv){
        //actionEnv非空判断
        if(!checkInput(actionEnv)){
            String msg = String.format("输入参数校验不通过！[%s]", getDataDesc());
            throw new IllegalArgumentException(msg);
        }
        else {
            //前置拦截器
            if (null != actionInterceptor) {
                actionInterceptor.executeBefore(actionEnv);
            }
            //核心执行语句
            T result = onExecute(actionEnv);

            //result非空判断
            result = handleResult(actionEnv, result);

            //后置拦截器
            if (null != actionInterceptor) {
                result = (T) actionInterceptor.executeAfter(result, actionEnv);
            }

            return result;
        }
    }

    protected boolean checkInput(ActionEnv actionEnv){
        if (null == actionEnv) {
            throw new IllegalArgumentException("actionEnv is null");
        }
        return true;
    }

    protected T handleResult(ActionEnv actionEnv, T result){
        if (null == actionEnv) {
            throw new IllegalArgumentException("actionEnv is null");
        }
        return result;
    }

    protected abstract T onExecute(ActionEnv actionEnv);

    /**
     * 这里的T2实际上是Fetcher，
     * 因为在定义下层的时候不知道上层是什么东西，
     * 所以只能说是一个东西继承了Action，而不能指名道姓的说是Fetcher
     * @param interceptor interceptor
     * @return Fetcher
     */
    public <T2 extends Action> T2 setActionInterceptor(ActionInterceptor interceptor){
        actionInterceptor = interceptor;
        return (T2) this;
    }

    public ActionInterceptor getActionInterceptor() {
        return actionInterceptor;
    }
}
