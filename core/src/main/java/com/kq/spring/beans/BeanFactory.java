package com.kq.spring.beans;

/**
 * BeanFactory
 *
 * @author kq
 * @date 2018-12-03
 */
public interface BeanFactory {

    /**
     * 根据名称 得到bean
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;

}
