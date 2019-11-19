package com.kq.spring.beans;

import com.kq.spring.beans.BeanDefinition;
import com.kq.spring.beans.BeanDefinitionRegistException;

/**
 * BeanDefinitionRegistry
 *
 * @author kq
 * @date 2018-12-03
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException;

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

}
