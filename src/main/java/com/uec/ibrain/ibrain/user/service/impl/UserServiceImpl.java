package com.uec.ibrain.ibrain.user.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uec.ibrain.ibrain.common.HttpManager;
import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.common.exception.BusinessException;
import com.uec.ibrain.ibrain.common.exception.RequestParamException;
import com.uec.ibrain.ibrain.common.http.HttpResponseEntity;
import com.uec.ibrain.ibrain.common.util.CommonUtil;
import com.uec.ibrain.ibrain.user.bean.UserCreateParam;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import com.uec.ibrain.ibrain.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户业务层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("userService")

public class UserServiceImpl implements IUserService {

	@Value("${inews.user.server.address}")
	private String inewsUserServer;
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * <p>Description:根据用户名查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByUserName
	 * @param userName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public UserEntity findByUserName(String userName) throws BaseException,
			Exception {
		if(StringUtils.isBlank(userName)){
			return null;
			//throw new RequestParamException(new String[]{"userName"});
		}
		ResponseEntity<String> response = restTemplate.getForEntity(inewsUserServer+"/api/user/findbyusername/"+userName, String.class);
		ObjectMapper mapper = new ObjectMapper();
		UserEntity entity = null;
		String body = response.getBody();
		Map<String, String> map = mapper.readValue(body, Map.class);
		if (null == map.get("resultObj")) {
			return null;
		}
		entity = mapper.convertValue(map.get("resultObj"), UserEntity.class);
		if(null == entity){
			throw new BusinessException("004001004");
		}

		return entity;
	}


	@Override
	public UserEntity findById(Integer id) throws BaseException, Exception {
		if(null == id){ throw new RequestParamException(new String[]{"id"}); }
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/user/getuser/"+id);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<UserEntity> resp = null;
		UserEntity entity = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<UserEntity>> typeReference = new TypeReference<HttpResponseEntity<UserEntity>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				entity = resp.getResultObj();
			}
		}
		return entity;
	}


	@Override
	public boolean updatePassword(Integer innerid, String oldPassword,
								  String newPassword) throws BaseException, Exception {
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpPost httpPost = new HttpPost(inewsUserServer+"/api/user/updatepassword");
		httpPost.addHeader("Content-Type","application/json;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", innerid);
		map.put("newPassword", newPassword);
		map.put("oldPassword", oldPassword);
		httpPost.setEntity(new StringEntity(CommonUtil.toJson(map), Consts.UTF_8));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<String> resp = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<String>> typeReference = new TypeReference<HttpResponseEntity<String>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				return true;
			}else{
				System.out.println(resp.getErrorMsg());
				throw new BusinessException("004001008");
			}
		}else{
			throw new BusinessException("004001008");
		}
	}


	@Override
	public boolean updateUserInfo(UserEntity param) throws Exception{
		if(null == param){
			throw new RequestParamException(new String[]{"param"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpPost httpPost = new HttpPost(inewsUserServer+"/api/user/update");
		httpPost.addHeader("Content-Type","application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(CommonUtil.toJson(param), Consts.UTF_8));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<UserEntity> resp = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<UserEntity>> typeReference = new TypeReference<HttpResponseEntity<UserEntity>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				return true;
			}else{
				System.out.println(resp.getErrorMsg());
				throw new BusinessException("004001006");
			}
		}else{
			throw new BusinessException("004001006");
		}
	}


}
