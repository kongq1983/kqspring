package com.kq.classloader.util;

import com.kq.classloader.bean.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassLoaderUtil
 *
 * @author kq
 * @date 2019-01-19
 */
public class ClassLoaderUtil {

    protected static Logger logger = LoggerFactory.getLogger(ClassLoaderUtil.class);

    public static ClassLoader getClassLoader(Class<?> clazz) {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = clazz.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }

        return cl;
    }

    private static ClassLoader findClassLoader() {
        return getClassLoader(ClassLoaderUtil.class);
    }



    private void loadDirectory(Map<String, Class<?>> extensionClasses, String dir, String type) {
        String fileName = dir + type;
        try {
            Enumeration<URL> urls; // 从类记载器 得到文件  比如： META-INF/dubbo/internal/org.apache.dubbo.rpc.cluster.RouterFactory
            ClassLoader classLoader = findClassLoader();
            if (classLoader != null) {
                urls = classLoader.getResources(fileName);
            } else {
                urls = ClassLoader.getSystemResources(fileName);
            }
            if (urls != null) {
                while (urls.hasMoreElements()) {
                    java.net.URL resourceURL = urls.nextElement();
                    logger.info("resourceURL={}",resourceURL);
                    loadResource(extensionClasses, classLoader, resourceURL);
                }
            }
        } catch (Throwable t) {
            logger.error("Exception occurred when loading extension class (interface: " +
                    type + ", description file: " + fileName + ").", t);
        }
    }
    // 1.读取资源文件中的内容，一行一行读取 2. 得到每行的key value  3. 缓存类  (解析META-INF/dubbo/internal下文件  key   value)
    private void loadResource(Map<String, Class<?>> extensionClasses, ClassLoader classLoader, java.net.URL resourceURL) {
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceURL.openStream(), StandardCharsets.UTF_8))) {
                String line; //比如:   line: adaptive=org.apache.dubbo.common.extension.factory.AdaptiveExtensionFactory
                while ((line = reader.readLine()) != null) { //line: spi=org.apache.dubbo.common.extension.factory.SpiExtensionFactory
                    logger.info("readLine={}",line);
                }
            }
        } catch (Throwable t) {
            logger.error("Exception occurred when loading extension class (interface: " +
                     ", class file: " + resourceURL + ") in " + resourceURL, t);
        }
    }



    public static void printParentClassLoader(Class clazz) {
        ClassLoader cl = clazz.getClassLoader();
        System.out.println("classLoader="+cl);

        while (cl!=null) {
            cl = cl.getParent();
            System.out.println("classLoader="+cl);
        }

    }

    public static void main1(String args[]) {

        Class clazz = Student.class;
        ClassLoader classLoader = getClassLoader(clazz);
        System.out.println("p.classLoader="+classLoader.toString());

        printParentClassLoader(clazz);

        System.out.println("======================================");
        System.out.println("getContextClassLoader="+Thread.currentThread().getContextClassLoader());

    }

    public static void main(String[] args) {
        Map<String, Class<?>> map = new HashMap<>();

        new ClassLoaderUtil().loadDirectory(map,"META-INF/dubbo/internal/","org.apache.dubbo.common.extension.ExtensionFactory");

        System.out.println("map="+map);
    }

}
