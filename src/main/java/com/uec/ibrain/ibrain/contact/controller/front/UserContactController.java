package com.uec.ibrain.ibrain.contact.controller.front;

import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Scope("prototype")
@Controller
@RequestMapping(value = "/contact")
public class UserContactController extends BaseController {

	@Value("${inews.sendmail.accout}")
	private String sendEmailAccount;
	
	@Value("${inews.sendmail.password}")
	private String sendEmailPwd;
	
	@Value("${inews.rcvmail.accout}")
	private String rcvAccount;

	@Value("${apply.user.key.base}")
	private String listKey;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * <br/>Description:添加或修改用户联系信息
	 * @param event
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@Resource
    private JavaMailSenderImpl mailSender;

	@RequestMapping("/goregister")
	public String gotoRegister(){
		return "frontend/register/register";
	}

	/**
	 * 申请试用  发送邮件到
	 * @param userName
	 * @param password
	 * @param cellphone
	 * @param wechat
	 * @param email
	 * @param company
	 * @param job
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/applyForUse", method= RequestMethod.POST)
	public ModelMap saveOrUpdateUserContact(String userName, String password, String cellphone, String wechat, String email, String company, String job)  throws BaseException, Exception {

		if (null==userName || null==password || null==cellphone || null==email || null==company || null==job ){
			return super.getModelMap(false,"数据不合法","001","数据不合法");
		}

        Map<String , Object> map = new HashMap<>();
		map.put("name",userName);
		map.put("password", password);
		map.put("email",email );
		map.put("wechat", wechat );
		map.put("phoneNumber", cellphone  );
		map.put("company", company );
		map.put("position", job );
		map.put("createTime", new Date());
        //message.setText(sb.toString());
		//年月日
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			redisTemplate.opsForList().leftPush(listKey+"_"+sdft.format(new Date()), map);
		}catch (Exception e){
			return super.getModelMap(false,"请求异常请重试","002","请求异常请重试");
		}
		//现在不单独发邮件了，弄成一个整个的后一天发今天的所有请求试用的
        //this.mailSender.send(mimeMessage);
		return super.getModelMap("您的申请已经提交，我们将在三个工作日内与您核实个人信息，请保证电话畅通，谢谢！");
	}
}
