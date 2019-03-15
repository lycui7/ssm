package albert.cui.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean后处理器实现类
 * @author albert.cui
 * @date 2019/3/15 21:49
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor(Bean后处理器)实现类构造器......");
    }

    /**
     * * 在为bean注入属性后->setBeanName->setBeanFactory后
     *  * 在afterPropertiesSet之前
     * @param o 要处理的bean对象
     * @param s bean对象的name
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改......");
        return o;
    }

    /**
     *在afterPropertiesSet->init-method->之后
     * 在InstantiationAwareBeanPostProcessor postProcessAfterInitialization方法前
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改......");

        return o;
    }
}
