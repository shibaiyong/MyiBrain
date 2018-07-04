package com.uec.ibrain.ibrain.common.log;


import java.lang.annotation.*;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 内部日志注解 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Target({ ElementType.METHOD, ElementType.TYPE })  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface SystemLogDesc {
	String operType() default "0";   
    String desc() default "";
}
