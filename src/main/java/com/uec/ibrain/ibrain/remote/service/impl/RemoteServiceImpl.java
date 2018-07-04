package com.uec.ibrain.ibrain.remote.service.impl;

import com.uec.ibrain.ibrain.remote.service.IRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/18</p>
 */
@Service("remoteService")
public class RemoteServiceImpl implements IRemoteService {

    @Value("${remote.token.url}")
    private String tokenUrl;

    @Value("${client_id}")
    private String client_id;
    @Value("${client_secret}")
    private String client_secret;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getToken(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> ss = restTemplate.postForEntity(tokenUrl,request,String.class);

        return ss.getBody();
    }



}
