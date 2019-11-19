package com.kq.spring.beans;



import com.kq.spring.beans.BeanDefinition;
import com.kq.spring.beans.BeanDefinitionRegistException;
import com.kq.spring.beans.DefaultBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * PreBuildBeanFactory
 *
 * @author kq
 * @date 2018-12-03
 */
public class PreBuildBeanFactory extends DefaultBeanFactory {

    private List<String> beanNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionRegistException {
        super.registerBeanDefinition(beanName, beanDefinition);
        synchronized (beanNames) {
            beanNames.add(beanName);
        }
    }

    public void preInstantiateSingletons() throws Exception {
        synchronized (beanNames) {
            for (String name : beanNames) {
                BeanDefinition bd = this.getBeanDefinition(name);
                if (bd.isSingleton()) {
                    this.doGetBean(name);
                    System.out.println("preInstantiate: name=" + name + " " + bd);
                }
            }
        }
    }
}