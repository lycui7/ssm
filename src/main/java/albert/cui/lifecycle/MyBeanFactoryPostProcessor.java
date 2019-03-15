package albert.cui.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author albert.cui
 * @date 2019/3/15 22:16
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("(工厂后处理器接口实现类)这是BeanFactoryPostProcessor实现类构造器......");
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法......");
        BeanDefinition definition = configurableListableBeanFactory.getBeanDefinition("person");
        definition.getPropertyValues().addPropertyValue("email","albert.cui@goertek.com");
    }
}
