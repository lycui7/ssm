package albert.cui.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author albert.cui
 * @date 2019/3/15 21:32
 */
public class Person implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean {
    private String name;
    private int age;
    private String email;
    private BeanFactory beanFactory;
    private String beanName;
    public Person() {
        System.out.println("【构造器】调用Person的构造器实例化......");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性name......");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("【注入属性】注入属性age......");
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        System.out.println("【注入属性】注入属性email......");
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()......");
        this.beanFactory = beanFactory;
    }
    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String s) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()......");
        this.beanName = s;
    }
    //这是InitializingBean的接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean接口】调用InitializingBean.afterPropertiesSet()......");
    }
    //这是DisposableBean的接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("【DisposableBean接口】调用DisposableBean.destory()......");
    }
    //自定义init-method
    public void initMethod() {
        System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法......");
    }
    //自定义destroy-method
    public void destroyMethod() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法......");
    }

}
