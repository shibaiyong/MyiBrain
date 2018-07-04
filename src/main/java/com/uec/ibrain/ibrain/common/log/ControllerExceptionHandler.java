package com.uec.ibrain.ibrain.common.log;

import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.common.exception.BaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 异常全局处理类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@ControllerAdvice
public class ControllerExceptionHandler extends BaseController {
	Log log = LogFactory.getLog(ControllerExceptionHandler.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ResponseBody
	@ExceptionHandler(value = BaseException.class)
	public ModelMap baseExceptionHandler(HttpServletRequest req, BaseException e) {
		log.error(this,e);
		return super.getModelMap(e);
	}
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ModelMap defaultExceptionHandler(HttpServletRequest req, Exception e) {
		log.error(this,e);
		return super.getModelMap(e);
	}
	
    
}
