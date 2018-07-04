package com.uec.ibrain.ibrain.auth;

import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.common.util.AuthImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Map;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p>
 * <p>Description: 图片验证码生成和验证 </p>
 * <p>Author:ftliu/刘芳彤</p>
 */

@RestController
@RequestMapping(value = "/api/login/auth",method = RequestMethod.GET)
public class AuthImageCotroller extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * <br/>Description:生成验证码
     * <p>Author:ftliu/刘芳彤</p>
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getcode")
    public String getImageCode() throws Exception {
        logger.info("验证码接受请求");
        Map<String,Object> map = AuthImageUtil.getImage();

        // 禁止图像缓存。
        response.reset();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 将四位数字的验证码保存到Session中。

        session.setAttribute("codeEnsure",map.get("codeEnsure").toString().toLowerCase());
        session.setAttribute("codeTime",System.currentTimeMillis());
        // debug 日志
        if(map.get("codeEnsure") == null){
            logger.error("生成验证码为空");    // 不是这个问题
        }else {
            logger.info("生成的验证码为："+map.get("codeEnsure").toString());
            logger.info("生成的验证码小写为："+map.get("codeEnsure").toString().toLowerCase());
            logger.info("验证码存入session:"+ session.getAttribute("codeEnsure"));
        }
        // 第二次添加
        if(session.getAttribute("codeEnsure")== null){
            session.setAttribute("codeEnsure",map.get("codeEnsure").toString().toLowerCase());
            logger.info("验证码第二次存入session:"+ session.getAttribute("codeEnsure"));
        }

        if(session.getAttribute("codeTime")!= null){
            logger.info("验证码存入session:"+ session.getAttribute("codeTime"));
        }else{
            logger.error("生成验证码时间为空，进行第二次添加");
            session.setAttribute("codeTime",new Date().getTime());
        }
        try {
            // 将图像输出到Servlet输出流中。
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
            os.close();
//            logger.info("返回前端");
        }catch (Exception e){
            return null;
        }
        return "";

    }

    /**
     * <br/>Description:检验验证码
     * <p>Author:ftliu/刘芳彤</p>
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/checkcode",method = RequestMethod.GET)
    public ModelMap checkCode(String checkCode) throws Exception {
//        String checkCode = request.getParameter("checkCode");
//        logger.info("验证码验证请求");
        Object cko = session.getAttribute("codeEnsure"); //验证码对象
        Object codeTimeObj = session.getAttribute("codeTime"); //验证码生成时间

        if (cko == null || codeTimeObj == null) {
            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return getModelMap(false,null,null,"验证码已失效，请重新输入！");
        }
        logger.info("验证码比对，应为："+cko+",input:"+checkCode);
        String captcha = cko.toString();
        Date now = new Date();
        Long codeTime = Long.valueOf(codeTimeObj.toString());
        if (StringUtils.isEmpty(checkCode) || captcha == null || !(checkCode.equalsIgnoreCase(captcha))) {
//            request.setAttribute("errorMsg", "验证码错误！");
            return getModelMap(false,null,null,"验证码错误");
        } else if ((now.getTime() - codeTime) / 1000 / 60 > 1) {
            //验证码有效时长为1分钟
//            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return getModelMap(false,null,null,"验证码已失效，请重新输入！");
        }// else {

        session.removeAttribute("codeEnsure");
        session.removeAttribute("codeTime");
        return getModelMap(true,"验证成功",null,null);
        //}
    }
}
