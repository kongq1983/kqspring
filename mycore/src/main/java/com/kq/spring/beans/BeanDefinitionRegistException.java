package com.kq.spring.beans;

/**
 * BeanDefinitionRegistException
 * @author kq
 * @date 2018-12-03
 */
public class BeanDefinitionRegistException extends Exception {

    public BeanDefinitionRegistException(String mess) {
        super(mess);
    }

    public BeanDefinitionRegistException(String mess, Throwable e) {
        super(mess, e);
    }

}
