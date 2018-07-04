package com.uec.ibrain.ibrain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/6/12</p>
 */

@Component
public class SendEmailTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${apply.user.key.base}")
    private String listKey;

    @Value("${inews.rcvmail.accout}")
    private String rcvaccount;

    @Resource
    private JavaMailSenderImpl mailSender;
    //每天9点
    @Scheduled(cron = "0 0 9 * * ? ")
    public void sendEmail(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list =  redisTemplate.opsForList().range(listKey+"_"+sdft.format(cal.getTime()),0,-1 );

        if (list.size() > 0){
            try {
                final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

                message.setFrom("inews@ronglian.com");
                message.setTo(rcvaccount);
                message.setSubject("用户使用申请");
                message.setText("及时处理");
                File file = new File("userApply.csv");
                //FileWriter writer = new FileWriter(file);
                Writer writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
                StringBuffer sb = new StringBuffer("");
                sb.append("申请用户名 ,");
                sb.append("密码 ,");
                sb.append("邮箱 ,");
                sb.append("微信号 ,");
                sb.append("手机号 ,");
                sb.append("公司 ,");
                sb.append("职位 ,");
                sb.append("申请时间 \n");
                for (Map map : list){
                    sb.append(map.get("name")+" ,");
                    sb.append(map.get("password")+" ,");
                    sb.append(map.get("email")+" ,");
                    sb.append(map.get("wechat")+" ,");
                    sb.append(map.get("phoneNumber")+" ,");
                    sb.append(map.get("company")+" ,");
                    sb.append(map.get("position")+" ,");
                    sb.append(map.get("createTime")+"\n");
                }
                //下面这行是，因为csv文件默认是anci码 加bom的
                writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
                writer.write( new String(sb.toString().getBytes(),"UTF-8") );
                writer.flush();
                writer.close();
                FileSystemResource resource =new FileSystemResource(file);
                //FileSystemResource fe = new FileSystemResource("E:\\log.txt");
                //message.addAttachment(fe.getFilename(), fe);
                message.addAttachment( MimeUtility.encodeText("userApply.csv"), resource);
                this.mailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("完事了，去邮箱看去吧！");
    }

}
