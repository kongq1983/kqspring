package com.kq.spring.beans;

/**
 * StudentBean
 * @author kq
 * @date 2018-12-03
 */
public class StudentBean {

    public void call() {
        System.out.println(System.currentTimeMillis() + " " + this);
    }

    public void init() {
        System.out.println("StudentBean.init() 执行了");
    }

    public void destroy() {
        System.out.println("StudentBean.destroy() 执行了");
    }

}
