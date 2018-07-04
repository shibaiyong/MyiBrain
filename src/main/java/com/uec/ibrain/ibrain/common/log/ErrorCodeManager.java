package com.uec.ibrain.ibrain.common.log;

import com.uec.ibrain.ibrain.common.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.MessageFormat;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 错误码管理工具类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class ErrorCodeManager {
	private static final Log log = LogFactory.getLog(ErrorCodeManager.class);

	/**
	 * <br/>Description:获取错误信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param errorCode
	 * @return
	 * @throws IOException 
	 */
	public static String getText(String errorCode) {
		return getText(null,errorCode,null);
	}

	/**
	 * <br/>Description:获取错误信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param errorCode
	 * @param param
	 * @return
	 * @throws IOException 
	 */
	public static String getText(String errorCode, Object[] param) {
		return getText(null,errorCode,param);
	}
	
	/**
	 * <br/>Description:获取错误信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param errorCode
	 * @param param
	 * @return
	 * @throws IOException 
	 */
	public static String getText(String i18n,String errorCode, Object[] param) {
		StringBuffer errorFileNameSb = new StringBuffer("errorcode");
		if(StringUtils.isNoneBlank(i18n)){
			errorFileNameSb.append("_");
			errorFileNameSb.append(i18n);
		}
		errorFileNameSb.append(".properties");
		String msg = null;
		try{
			msg = CommonUtil.getPropertiesValue(errorFileNameSb.toString(),errorCode);
		}catch(IOException e){
			log.error(errorFileNameSb.toString() + " file does not exist");
			log.error(e);
		}
		if(StringUtils.isNoneBlank(msg)){
			msg = MessageFormat.format(msg, param);
		}
		return msg;
	}
}
