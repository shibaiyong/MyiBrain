package com.uec.ibrain.ibrain.common.log;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 内部日志Aop拦截 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Component
@Aspect
public class SystemLogAop {
	
	Log log = LogFactory.getLog(SystemLogAop.class);
	
	@Pointcut("@annotation(com.uec.ibrain.ibrain.common.log.SystemLogDesc)")
    public void methodCachePointcut() {}
	
	@Pointcut("execution(* com.uec.ibrain..*.service..*.*(..))")
    public void serviceAspect() {} 
	
	@Before("serviceAspect()")
	public void doBefore(JoinPoint joinPoint){
		String targetName = joinPoint.getTarget().getClass().getName();    //拦截的类
		Log log = LogFactory.getLog(targetName);
		try {
			String desc = getServiceMthodDescription(joinPoint);
			if(StringUtils.isNotBlank(desc)){
				log.info(desc);		//记录info日志
			}
          
	      }catch(Exception e) {
	          log.error(targetName, e);        //记录错误日志
	      }
       
	}
	
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		String targetName = joinPoint.getTarget().getClass().getName();    //拦截的类
		Log log = LogFactory.getLog(targetName);
		log.error(targetName, e);
	}
	
	@SuppressWarnings("rawtypes")
	public String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                    SystemLogDesc annotation = method.getAnnotation(SystemLogDesc.class); 
                    if(null != annotation){
                    	description = annotation.desc();
                    }else{
//                    	log.warn(targetName+" "+methodName+" dose not has annotation @InternalLogDesc");
                    }
                    break;  
                }  
            }  
        }  
        return description;  
    }
}
