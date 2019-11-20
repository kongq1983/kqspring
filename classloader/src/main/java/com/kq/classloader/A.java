package com.kq.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A
 *
 * @author kq
 * @date 2019-11-19
 */
public class A {

    protected static Logger LOG = LoggerFactory.getLogger(A.class);

    public static void main(String[] args) {
        LOG.trace("Hello World!");
        LOG.debug("How are you today?");
        LOG.info("I am fine.");
        LOG.warn("I love programming.");
        LOG.error("I am programming.");
    }

}
