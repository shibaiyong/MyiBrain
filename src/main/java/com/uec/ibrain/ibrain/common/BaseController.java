package com.uec.ibrain.ibrain.common;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/16</p>
 */
public abstract class BaseController {

    public HttpServletRequest request;

    public HttpServletResponse response;

    public HttpSession session;

    private final String ERROR_MSG = "未知异常，请联系管理员！";

    /**
     *绑定求参数到命令对象
     */
    @ModelAttribute
    public void setRequestParams(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        this.request = request;
        this.response = response;
        this.session = session;
    }

    public ModelMap getModelMap(Object resultObj){
        return this.getModelMap(true,resultObj,null ,null);
    }

    /**
     * <br/>Description:获取异常的响应结果，返回给页面
     * <p>Author:xpguo/郭晓鹏</p>
     * @param e
     * @return
     */
    public ModelMap getModelMap(Exception e){
        return this.getModelMap(false,null,null,ERROR_MSG);
    }

    public ModelMap getModelMap(boolean result,Object resultObj,String errorCode,String errorMsg){
        ModelMap map = new ModelMap();
        map.put("result",result);
        map.put("resultObj",resultObj);
        map.put("errorCode",errorCode);
        map.put("errorMsg",errorMsg);
        return map;
    }

}
