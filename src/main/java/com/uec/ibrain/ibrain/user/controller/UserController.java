package com.uec.ibrain.ibrain.user.controller;

import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.common.exception.BaseException;

import com.uec.ibrain.ibrain.tenant.service.ITenantService;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import com.uec.ibrain.ibrain.user.service.IRoleService;
import com.uec.ibrain.ibrain.user.service.IUserService;
import com.uec.ibrain.ibrain.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/18</p>
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {



    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;

    @Autowired
    @Qualifier("tenantService")
    private ITenantService tenantService;
    /**
     * <br/>Description:获取用户信息
     * <p>Author:xpguo/郭晓鹏</p>
     * @param id
     * @param model
     * @return
     * @throws BaseException
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getUser/{id}",method=RequestMethod.GET)
    public ModelMap getUser(@PathVariable("id") int id,Model model) throws BaseException, Exception {
        UserEntity user = userService.findById(id);
        if (null != user){
            return super.getModelMap(user);
        }else{
            return super.getModelMap(true,null,null,null);
        }
    }

    /**
     * <br/>Description:修改密码
     * <p>Author:xpguo/郭晓鹏</p>
     * @param innerid
     * @param newpassword
     * @return
     * @throws BaseException
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/updatePassword",method=RequestMethod.POST)
    public ModelMap updatePassword(Integer innerid, String oldpassword, String newpassword)  throws BaseException, Exception{

        //自己比对密码
        UserEntity user = userService.findById(innerid);
        if (null == user){
            return super.getModelMap(false,"用户id错误",null,null);
        }
        String str = EncryptUtil.MD5Encyp(oldpassword,user.getSalt(),2);
        String pass = EncryptUtil.SHA256Encyp(str,user.getSalt(),3);
        if (!pass.equals(user.getPassword())){
            return super.getModelMap(false,"原密码错误",null,null);
        }

        boolean flag = userService.updatePassword(innerid, null, newpassword);
        if (flag){
            return super.getModelMap("修改密码成功");
        }else {
            return super.getModelMap(false,"修改密码失败",null,null);
        }

    }

    @RequestMapping("/goupdatepwd")
    public String goModifyPass(){
        return "frontend/modify_password/modify_password";
    }

    @RequestMapping("/myinfo")
    public String gotoMyInfo(){
        return "frontend/personalInformation/personal_information";
    }

    @RequestMapping("/goupdateInfo")
    public String goupdateInfo(){
        return "frontend/personalInformation/personal_information_update";
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public ModelMap updateInfo(UserEntity user){

        try {
            if (userService.updateUserInfo(user)){
                return super.getModelMap(true,null,null,null);
            }else{
                return super.getModelMap(false,"修改未成功！",null,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
