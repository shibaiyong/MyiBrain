package com.uec.ibrain.ibrain.tenant.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.uec.ibrain.ibrain.common.HttpManager;
import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.common.exception.BusinessException;
import com.uec.ibrain.ibrain.common.exception.RequestParamException;
import com.uec.ibrain.ibrain.common.http.HttpPageResponse;
import com.uec.ibrain.ibrain.common.http.HttpResponseEntity;
import com.uec.ibrain.ibrain.common.util.CommonUtil;
import com.uec.ibrain.ibrain.common.util.ConstantUtil;
import com.uec.ibrain.ibrain.tenant.bean.TenantAccessToken;
import com.uec.ibrain.ibrain.tenant.bean.TenantBean;
import com.uec.ibrain.ibrain.tenant.bean.TenantParam;
import com.uec.ibrain.ibrain.tenant.service.ITenantService;
import com.uec.ibrain.ibrain.user.bean.UserAPIBean;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.concurrent.TimeUnit;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:租户业务逻辑类  </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("tenantService")
public class TenantServiceImpl implements ITenantService {
	
	@Value("${inews.user.server.address}")
	private String inewsUserServer;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * <p>Description: 添加租户</p>
	 * <p>@Author:ygao/高远</p>
	 *  * @param tenantBean
	 * @date 2018/2/26 14:40
	 * @return com.uec.inews.tenant.bean.TenantBean
	 */
	@Override
	public TenantBean addTenant(TenantBean tenantBean) throws BaseException, Exception {
		if(null == tenantBean){
			throw new RequestParamException(new String[]{"tenantBean"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpPost httpPost = new HttpPost(inewsUserServer+"/api/tenant/create");
		httpPost.addHeader("Content-Type","application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(CommonUtil.toJson(tenantBean), Consts.UTF_8));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<TenantBean> resp = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<TenantBean>> typeReference = new TypeReference<HttpResponseEntity<TenantBean>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				return resp.getResultObj();
			}else{
				System.out.println(resp.getErrorMsg());
				throw new BusinessException("007001002");
			}
		}else{
			throw new BusinessException("007001002");
		}
	}
	/**
	 * <p>Description: 分页查询租户</p>
	 * <p>@Author:ygao/高远</p>
	 *  * @param param
	 * @date 2018/2/26 14:40
	 * @return com.uec.inews.common.http.HttpPageResponse<com.uec.inews.user.bean.TenantEntity>
	 */
	@Override
	public HttpPageResponse<TenantBean> pageTenants(TenantParam param) throws BaseException, Exception {
		HttpPageResponse<TenantBean> page = null;
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/page?queryStr="+param.getQueryStr()+"&pageStart="+param.getPageStart()+"&pageSize="+param.getPageSize());

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<HttpPageResponse<TenantBean>> resp = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<HttpPageResponse<TenantBean>>> typeReference = new TypeReference<HttpResponseEntity<HttpPageResponse<TenantBean>>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				page = resp.getResultObj();
			}
		}
		return page;
	}
	/**
	 * <p>Description: 删除租户</p>
	 * <p>@Author:ygao/高远</p>
	 *  * @param id
	 * @date 2018/2/26 15:41
	 * @return void
	 */
	@Override
	public void delete(Integer id) throws BaseException, Exception {
		if(null == id){ throw new RequestParamException(new String[]{"tenantId"}); }
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpDelete httpPost = new HttpDelete(inewsUserServer+"/api/tenant/delete/"+id);
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
				//TODO 删除用户定制
			}else{
				System.out.println(resp.getErrorMsg());
				throw new BusinessException("007001001");
			}
		}else{
			throw new BusinessException("007001001");
		}
	}
	/**
	 * <p>Description:根据用户id查询所属租户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByUserId
	 * @param userId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.tenant.service.ITenantService#findByUserId(Integer)
	 */
	@Override
	public TenantBean findByUserId(Integer userId) throws BaseException,
			Exception {
		if(null == userId){
			throw new RequestParamException(new String[]{"userId"});
		}
//		UserEntity entity = userRepository.findByUserName(userName);
		
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/userid/"+userId);
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<TenantBean> resp = null;
		TenantBean entity = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<TenantBean>> typeReference = new TypeReference<HttpResponseEntity<TenantBean>>(){};
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
	/**
	 * <p>Description: 修改租户信息</p>
	 * <p>@Author:ygao/高远</p>
	 *  * @param tenant
	 * @date 2018/2/26 19:11
	 * @return com.uec.inews.tenant.bean.TenantBean
	 */
	@Override
	public TenantBean update(TenantBean tenant) throws BaseException,
			Exception {
		if(null == tenant){
			throw new RequestParamException(new String[]{"tenant"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpPost httpPost = new HttpPost(inewsUserServer+"/api/tenant/update");
		httpPost.addHeader("Content-Type","application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(CommonUtil.toJson(tenant), Consts.UTF_8));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<TenantBean> resp = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<TenantBean>> typeReference = new TypeReference<HttpResponseEntity<TenantBean>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				return resp.getResultObj();
			}else{
				System.out.println(resp.getErrorMsg());
				throw new BusinessException("007001003");
			}
		}else{
			throw new BusinessException("007001003");
		}
	}
	/**
	 * <p>Description:查询所有租户列表</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listAllTenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public List<TenantBean> listAllTenant() throws BaseException, Exception {
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/list");
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<List<TenantBean>> resp = null;
		List<TenantBean> entity = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<List<TenantBean>>> typeReference = new TypeReference<HttpResponseEntity<List<TenantBean>>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				entity = resp.getResultObj();
			}else{
				throw new BusinessException("004001004");
			}
		}else{
			throw new BusinessException("004001004");
		}
		return entity;
	}

	/**
	 * <br/>Description:根据标识查询租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public TenantBean findByTenantMark(String tenantMark) throws BaseException, Exception {
		if(StringUtils.isBlank(tenantMark)){
			throw new RequestParamException(new String[]{"tenantMark"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/mark/"+tenantMark);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<TenantBean> resp = null;
		TenantBean entity = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<TenantBean>> typeReference = new TypeReference<HttpResponseEntity<TenantBean>>(){};
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

	/**
	 * <br/>Description:根据id查询租户
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public TenantBean findByTenantId(Integer tenantId) throws BaseException, Exception{
		if(null == tenantId){
			throw new RequestParamException(new String[]{"tenantId"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/id/"+tenantId);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<TenantBean> resp = null;
		TenantBean entity = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<TenantBean>> typeReference = new TypeReference<HttpResponseEntity<TenantBean>>(){};
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
	/**
	 * <br/>Description:根据租户标识获取租户获取该租户下所有用户信息
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<UserEntity> listUsersByTenantMark(String tenantMark) throws BaseException,Exception{
		if(null == tenantMark){
			throw new RequestParamException(new String[]{"tenantMark"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/users/"+tenantMark);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<List<UserEntity>> resp = null;
		List<UserEntity> entityList = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<List<UserEntity>>> typeReference = new TypeReference<HttpResponseEntity<List<UserEntity>>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				entityList = resp.getResultObj();
			}
		}
		return entityList;
	}

	/**
	 * <br/>Description:根据租户标识获取accessToken
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public String getAccessTokenByTenantMark(String tenantMark) throws BaseException, Exception{
		if(StringUtils.isBlank(tenantMark)){
			throw new RequestParamException(new String[]{"tenantMark"});
		}
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/tenant/token/"+tenantMark);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpentity = response.getEntity();
		HttpResponseEntity<String> resp = null;
		String accessToken = null;
		if (httpentity != null) {
			String result = EntityUtils.toString(httpentity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<String>> typeReference = new TypeReference<HttpResponseEntity<String>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(httpentity);
		if(null != resp){
			if(resp.getResult()){
				accessToken = resp.getResultObj();
			}
		}
		return accessToken;
	}



	/**
	 * <br/>Description:根据租户id和租户应用密钥获取租户访问token对象
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public TenantAccessToken getAccessTokenByTenantIdAndSecret(String tenantId, String tenantSecret) throws BaseException, Exception{
		if(null == tenantId ){
			throw  new RequestParamException(new String[]{"tenantId"});
		}
		if(null == tenantSecret ){
			throw  new RequestParamException(new String[]{"tenantSecret"});
		}
		//根据tenantid获取租户信息
		TenantBean entity = null;
		try {
			Integer tid = Integer.parseInt(tenantId);
			entity = this.findByTenantId(tid);
		}catch (Exception e){
			throw new RequestParamException(new String[]{"tenantId"});
		}
		TenantAccessToken tenantAccessToken = new TenantAccessToken();
		if(null == entity){ //如果租户信息为空，抛异常
			throw  new BusinessException("10200001"); //错误的tenantId或者tenantSecret
		}else if(!tenantSecret.equals(entity.getTenantSecret())){
			throw  new BusinessException("10200001"); //错误的tenantId或者tenantSecret
		}else{
			//通过租户标识获取访问令牌
			String accessToken = this.getAccessTokenByTenantMark(entity.getTenantMark());
			tenantAccessToken.setTenant(entity);
			tenantAccessToken.setAccessToken(accessToken);
			//将令牌放入redis，并设置默认过期时间2小时
			ValueOperations<String, String> stringOperations = redisTemplate
					.opsForValue();
			stringOperations.set(accessToken, CommonUtil.toJson(tenantAccessToken), ConstantUtil.DEFAULT_REDIS_EXPIRE_TIME, TimeUnit.SECONDS);
			Date t = DateUtils.addSeconds(new Date(), ConstantUtil.DEFAULT_REDIS_EXPIRE_TIME);
			String timeStr = CommonUtil.formatDateToString(t);
			tenantAccessToken.setExpiration(timeStr);
			tenantAccessToken.setCacheSeconds(ConstantUtil.DEFAULT_REDIS_EXPIRE_TIME);
			return  tenantAccessToken;
		}
	}


	/**
	 * <br/>Description:根据租户编码和访问令牌获取该租户的所有用户信息
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public List<UserAPIBean> listUsersByTokenAndMark(String accessToken, String tenantMark) throws BaseException, Exception{
		if(null == accessToken ){
			throw  new RequestParamException(new String[]{"accessToken"});
		}
		if(null == tenantMark ){
			throw  new RequestParamException(new String[]{"tenantMark"});
		}
		ValueOperations<String, String> stringOperations = redisTemplate
				.opsForValue();
		String token = stringOperations.get(accessToken);
		if(null == token){
			throw new BusinessException("10200002");
		}else{
			TenantAccessToken tenantAccessToken = CommonUtil.parseJson(token, TenantAccessToken.class);
			TenantBean tenantBean = tenantAccessToken.getTenant();
			//token 对应的租户同请求租户
			List<UserAPIBean> resultList = new ArrayList<>();
			if(tenantMark.equals(tenantBean.getTenantMark())){
				List<UserEntity> userEntityList = this.listUsersByTenantMark(tenantMark);
				for(UserEntity user: userEntityList){
					UserAPIBean apiBean = new UserAPIBean();
					apiBean.setUsername(user.getName()); //需要进一步确认字段对应关系
					apiBean.setUid(user.getInnerid().toString());
					apiBean.setAccount(user.getUserName());
					resultList.add(apiBean);
				}
			}
			return resultList;
		}


	}


	/**
	 * <br/>Description:判断访问令牌是否失效
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean isValidAccessToken(String accessToken) throws BaseException, Exception{
		if(null == accessToken){
			throw  new RequestParamException(new String[]{"accessToken"});
		}
		ValueOperations<String, String> stringOperations = redisTemplate
				.opsForValue();
		String token = stringOperations.get(accessToken);
		if(null == token){
			return false;
		}else{
			return true;
		}
	}


	/**
	 * <br/>Description:令牌验证登录inews系统
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean loginWithoutPasswd(String accessToken, String signature, String account) throws BaseException, Exception{
		return false;
	}

}
