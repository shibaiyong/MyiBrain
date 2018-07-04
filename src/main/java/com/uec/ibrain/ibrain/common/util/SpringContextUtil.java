package com.uec.ibrain.ibrain.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  spring上下文配置</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		 logger.info("------SpringContextUtil setApplicationContext-------");
		applicationContext = context;

	}

	 public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	    }
	 
	    /**
	     * <br/>Description:注意 bean name默认 = 类名(首字母小写)
	     * <p>Author:jlchen/陈金梁</p>
	     * @param name
	     * @return
	     * @throws BeansException
	     */
	    public static Object getBean(String name) throws BeansException {
	        return applicationContext.getBean(name);
	    }
	    
	    /**
	     * <br/>Description:根据类名获取到bean
	     * <p>Author:jlchen/陈金梁</p>
	     * @param clazz
	     * @return
	     * @throws BeansException
	     */
	    @SuppressWarnings("unchecked")
	    public static <T> T getBeanByName(Class<T> clazz) throws BeansException {
	            char[] cs=clazz.getSimpleName().toCharArray();
	            cs[0] += 32;//首字母大写到小写
	            return (T) applicationContext.getBean(String.valueOf(cs));
	    }
	    
	    public static boolean containsBean(String name) {
	        return applicationContext.containsBean(name);
	    }

	    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
	        return applicationContext.isSingleton(name);
	    }
	    
//	public static Object getBean(Class classname) {
//		try {
//			Object _restTemplate = applicationContext.getBean(classname);
//			return _restTemplate;
//		} catch (Exception e) {
//			return "";
//		}
//	}
//
//	public static void setApplicationContext1(ApplicationContext context) {
//		applicationContext = context;
//	}
}
