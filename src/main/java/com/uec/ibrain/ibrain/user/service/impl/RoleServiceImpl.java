package com.uec.ibrain.ibrain.user.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.uec.ibrain.ibrain.common.HttpManager;
import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.common.exception.RequestParamException;
import com.uec.ibrain.ibrain.common.http.HttpResponseEntity;
import com.uec.ibrain.ibrain.common.util.CommonUtil;
import com.uec.ibrain.ibrain.user.bean.RoleEntity;
import com.uec.ibrain.ibrain.user.bean.UserRoleEntity;
import com.uec.ibrain.ibrain.user.service.IRoleService;
import com.uec.ibrain.ibrain.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 角色业务层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("roleService")
@Transactional(value = "transactionManager")
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	@Value("${inews.user.server.address}")
	private String inewsUserServer;

	/**
	 * <p>Description:根据id查询角色</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public RoleEntity findById(Integer id) throws BaseException, Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		return null;
	}

	/**
	 * <p>Description:创建角色</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: createRole
	 * @param role
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public RoleEntity createRole(RoleEntity role) throws BaseException,
			Exception {
		if(null == role){
			throw new RequestParamException(new String[]{"role"});
		}

		return null;
	}

	/**
	 * <p>Description:更新角色</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: updateRole
	 * @param role
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public RoleEntity updateRole(RoleEntity role) throws BaseException,
			Exception {
		if(null == role){
			throw new RequestParamException(new String[]{"role"});
		}
		return null;
	}

	/**
	 * <p>Description:删除角色</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: deleteRole
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public void deleteRole(Integer id) throws BaseException, Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
	}

	/**
	 * <p>Description:根据用户id查询角色</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByUserId
	 * @param userId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public List<RoleEntity> findByUserId(Integer userId) throws BaseException,
			Exception {
		if(null == userId){
			throw new RequestParamException(new String[]{"userId"});
		}
		return null;
	}

	/**
	 * <p>Description:根据用户名查询角色</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByUserName
	 * @param userName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public List<RoleEntity> findByUserName(String userName)
			throws BaseException, Exception {
		if(StringUtils.isBlank(userName)){
			throw new RequestParamException(new String[]{"userName"});
		}

		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/role/findbyusername/"+userName);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		HttpResponseEntity<List<RoleEntity>> resp = null;
		List<RoleEntity> list = null;
		if (entity != null) {
			String result = EntityUtils.toString(entity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<List<RoleEntity>>> typeReference = new TypeReference<HttpResponseEntity<List<RoleEntity>>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(entity);
		if(null != resp){
			if(resp.getResult()){
				list = (List<RoleEntity>)resp.getResultObj();
			}
		}

		return list;
	}

	/**
	 * <p>Description:添加用户角色关系</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: addUserRole
	 * @param userRole
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public UserRoleEntity addUserRole(UserRoleEntity userRole)
			throws BaseException, Exception {
		if(null == userRole){
			throw new RequestParamException(new String[]{"userRole"});
		}

		return null;
	}

	/**
	 * <p>Description:根据用户id删除用户权限关系</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: deleteUserRoleByUserId
	 * @param userId
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public void deleteUserRoleByUserId(Integer userId) throws BaseException,
			Exception {
		if(null == userId){
			throw new RequestParamException(new String[]{"userId"});
		}
	}

	/**
	 * <p>Description:查询角色列表</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listRoles
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@Override
	public List<RoleEntity> listRoles() throws BaseException, Exception {
		CloseableHttpClient httpClient = HttpManager.getHttpClient();
		HttpGet httpGet = new HttpGet(inewsUserServer+"/api/role/list");
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		HttpResponseEntity<List<RoleEntity>> resp = null;
		List<RoleEntity> list = null;
		if (entity != null) {
			String result = EntityUtils.toString(entity, Consts.UTF_8);
			TypeReference<HttpResponseEntity<List<RoleEntity>>> typeReference = new TypeReference<HttpResponseEntity<List<RoleEntity>>>(){};
			resp = CommonUtil.parseJsonTypeReference(result, typeReference);
		}
		EntityUtils.consume(entity);
		if(null != resp){
			if(resp.getResult()){
				list = (List<RoleEntity>)resp.getResultObj();
			}
		}
		return list;
	}

}
