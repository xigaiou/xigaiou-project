
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class test {
    @Test
    public void test1(){
        TemplateEngine te=new TemplateEngine();
        String s="<input type='text' th:value='hello'/>";
        Context c1=new Context();
        System.out.println(te.process(s,c1));
    }

}
