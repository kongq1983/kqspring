package com.kq.spring.beans;

import org.junit.Test;

/**
 * DefaultBeanFactoryTest
 *
 * @author kq
 * @date 2018-12-03
 */
public class DefaultBeanFactoryTest {

    static DefaultBeanFactory bf = new DefaultBeanFactory();

    @Test
    public void testRegist() throws Exception {

        GenericBeanDefinition bd = new GenericBeanDefinition();

        bd.setBeanClass(StudentBean.class);
        bd.setScope(BeanDefinition.SCOPE_SINGLETION);
        // bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        bd.setInitMethodName("init");
        bd.setDestroyMethodName("destroy");

        bf.registerBeanDefinition("studentBean", bd);

    }

}
