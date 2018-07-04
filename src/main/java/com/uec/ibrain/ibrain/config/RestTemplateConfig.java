package com.uec.ibrain.ibrain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/16</p>
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        RestTemplate template = new RestTemplate(factory);
        //字符类型
        template.getMessageConverters().set(0,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return template;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //读取超时  单位ms
        factory.setReadTimeout(5000);
        //连接超时  单位ms
        factory.setConnectTimeout(10000);
        return factory;
    }

}
