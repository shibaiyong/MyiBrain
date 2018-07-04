package com.uec.ibrain.ibrain.remote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.remote.service.IRemoteService;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import com.uec.ibrain.ibrain.user.service.IUserService;
import com.uec.ibrain.ibrain.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/18</p>
 */
@Controller
public class RemoteController extends BaseController{

    @Autowired
    private IRemoteService remoteService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/remoteINews")
    @ResponseBody
    public ModelMap remoteINews(Integer innerid){

        Map result = new HashMap(16);

        try {
            UserEntity user = userService.findById(innerid);
            if (null == user){
                return super.getModelMap(false,null,null,null);
            }
            ObjectMapper mapper = new ObjectMapper();
            String str = remoteService.getToken();
            Map map = mapper.readValue(str, Map.class);
            String token = map.get("access_token").toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            String signeture = EncryptUtil.MD5Encyp(token+user.getUserName()+sdf.format(new Date()),"",1);

            result.put("access_token", token);
            result.put("signature", signeture);
            result.put("account", user.getUserName());
            result.put("redirectUrl", "/home");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getModelMap(result);
    }

}
