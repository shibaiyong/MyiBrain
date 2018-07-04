package com.uec.ibrain.ibrain.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p>
 * <p>Description: 验证码生成类 </p>
 * <p>Author:ftliu/刘芳彤</p>
 */

public class AuthImageUtil {
    private static char randomString[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };//随机产生数字与字母组合的字符串
    private static int width = 95;// 图片宽
    private static int height = 25;// 图片高
    private static int lineSize = 80;// 干扰线数量
    private static int stringNum = 4;// 随机产生字符数量
    private static int fontSize = 20;//字体大小
    private static int xx = 19;//图片字符间隔
    private static int yy = 20;//字符距离下边界间隔
    private static Random random = new Random();
    private static Logger logger = LoggerFactory.getLogger(AuthImageUtil.class);

    public static Map<String, Object> getImage(){
//        logger.info("验证码日志：开始调用生成方法");
        Map<String,Object> returnMap = new HashMap<String, Object>();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
//        logger.info("验证码日志：开始获取图形上下文");
        Graphics g = image.getGraphics();
        // 设定背景色
//        logger.info("验证码日志：开始设定背景色");
//        g.setColor(getRandColor(200, 250));   //随机色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        //设定字体
//        logger.info("验证码日志：开始设定字体");
        g.setFont(new Font("Fixedsys", Font.BOLD, fontSize));
        // 画边框。
//        logger.info("验证码日志：开始画边框");
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
//        logger.info("验证码日志：开始随机产生干扰线");
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < lineSize; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //取随机产生的码
//        logger.info("验证码日志：开始随机产生4位码");
        String strEnsure = "";
        //4代表4位验证码,如果要生成更多位的认证码,则加大数值

        for (int i = 0; i < stringNum; ++i) {
            strEnsure += randomString[(int) (randomString.length * Math.random())];
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //直接生成
            String str = strEnsure.substring(i, i + 1);
            g.drawString(str, (i+1) * xx-xx/2, yy);
        }
//        logger.info("验证码日志：释放图形上下文");
        // 释放图形上下文
        g.dispose();
//        logger.info("验证码日志：结束随机产生4位码"+strEnsure);
        returnMap.put("image",image);
        returnMap.put("codeEnsure",strEnsure);
        return returnMap;
    }
    //给定范围获得随机颜色
    static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);

    }
}
