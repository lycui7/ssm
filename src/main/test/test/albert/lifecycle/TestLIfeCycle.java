package albert.lifecycle;

import albert.cui.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 探索Spring中bean的生命周期
 * @author albert.cui
 * @date 2019/3/15 21:25
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-test.xml"})*/
public class TestLIfeCycle {
    @Test
    public void test() {
        System.out.println("现在开始初始化容器......");
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-test.xml");
        System.out.println("容器初始化成功......");
        //得到Person实例，并使用
        Person person = ac.getBean("person", Person.class);
        System.out.println(person);
        System.out.println("现在开始关闭容器......");
        //关闭容器使用的是AbstractApplicationContext的钩子方法
        ((ClassPathXmlApplicationContext) ac).registerShutdownHook();
    }
}
