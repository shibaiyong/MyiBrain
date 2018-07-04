package com.uec.ibrain.ibrain.controller;

import com.uec.ibrain.ibrain.auth.UsernamePasswordLoginTypeToken;
import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.common.util.CommonUtil;
import com.uec.ibrain.ibrain.tenant.bean.TenantBean;
import com.uec.ibrain.ibrain.tenant.service.ITenantService;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import com.uec.ibrain.ibrain.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/16</p>
 */
@Controller
@Scope("prototype")
public class PortalController extends BaseController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;
    @Autowired
    @Qualifier("tenantService")
    private ITenantService tenantService;

    @RequestMapping({"/", "/index", "/ibrain"})
    public String index(Model model){
        String ctx = request.getContextPath();
        model.addAttribute("ctx", ctx);
        return "frontend/index/index";
    }

    @RequestMapping("/loginpage")
    public String loginPage(){
        return "frontend/login/login";
    }

    @RequestMapping("/gointroduce")
    public String goIntroduce(){
        return "frontend/introduce/introduce";
    }

    /**
     * 新的登录请求 没有验证码需求
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ModelMap login(UserEntity user)throws Exception{

        Subject currentUser = SecurityUtils.getSubject();
        Map<String,Integer> count = new HashMap<>();

        session.setAttribute("ctx",request.getContextPath());
        UserEntity userEntity = userService.findByUserName(user.getUserName());

        if(null == userEntity){
            return super.getModelMap(false,null,null,"用户名不存在");
        }
        //增加登录类型
        UsernamePasswordLoginTypeToken token = new UsernamePasswordLoginTypeToken(user.getUserName(), CommonUtil.encryptPassword(user.getPassword(),userEntity.getSalt()),1);
        try{
            currentUser.login(token);
        }catch(AuthenticationException e){
            return super.getModelMap(false,count,null,"用户名或密码错误");
        };
        session.setAttribute("userEntity", userEntity);

        if(currentUser.isAuthenticated()){
            Subject subject = SecurityUtils.getSubject();
            String userName = (String) subject.getPrincipal();
            Session session = subject.getSession();
            UserEntity userInfo = userEntity;
            if(null != userInfo.getEndTime() && System.currentTimeMillis() > userInfo.getEndTime().getTime()){
                subject.logout();
                return super.getModelMap(false,null,null,"用户账号已过期");
            }
            session.setAttribute("user",userInfo);
            TenantBean tenant = tenantService.findByUserId(userInfo.getInnerid());
            session.setAttribute("tenant",tenant);

            String tenantMark = "";
            if(null != tenant && null != tenant.getTenantMark()){
                tenantMark = tenant.getTenantMark();
            }

            userEntity.setPassword("");
            return super.getModelMap(userEntity);
        }else{
            return super.getModelMap(false,null,null,"用户名或密码错误");
        }

    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ModelMap logout(){
        session.removeAttribute("userEntity");
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            currentUser.logout();
        }
        return super.getModelMap(true);
    }

    @RequestMapping("/voicetrans")
    public String voicetrans()throws Exception{
        return "frontend/phonetic_transcription/phonetic_transcription";
    }

    @RequestMapping("/iwriting")
    public String wisewrite()throws Exception{
        return "frontend/Intelligent_writing/Intelligent_writing";
    }

    @RequestMapping("/magicdata")
    public String transportspy()throws Exception{
        return "frontend/magicdata/magicdata";
    }
    @RequestMapping("/videosearch")
    public String videosearch()throws Exception{
        return "frontend/kuaishou/kuaishou";
    }

    @RequestMapping(value = "/checksession",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkIsLogin(){
        UserEntity userEntity = (UserEntity)session.getAttribute("user");
        if (null != userEntity){
            return true;
        }
        return false;
    }

}
