package com.uec.ibrain.ibrain.tenant.service;

import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.common.http.HttpPageResponse;
import com.uec.ibrain.ibrain.tenant.bean.TenantAccessToken;
import com.uec.ibrain.ibrain.tenant.bean.TenantBean;
import com.uec.ibrain.ibrain.tenant.bean.TenantParam;
import com.uec.ibrain.ibrain.user.bean.UserAPIBean;
import com.uec.ibrain.ibrain.user.bean.UserEntity;

import java.util.List;

/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户业务逻辑类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface ITenantService {
	/**
	* <p>Description: 修改租户信息</p>
	* <p>@Author:ygao/高远</p>
	*  * @param param
	* @date 2018/2/26 19:11
	* @return
	*/
	public TenantBean update(TenantBean param) throws BaseException, Exception;
	/**
	 * <p>Description: 删除租户</p>
	 * <p>@Author:ygao/高远</p>
	 ** @param id
	 * @date 2018/2/26 15:38
	 * @return
	 */
	public void delete(Integer id) throws BaseException, Exception;
	/**
	* <p>Description: 分页查询租户</p>
	* <p>@Author:ygao/高远</p>
	** @param param
	* @date 2018/2/26 15:38
	* @return
	*/

	public HttpPageResponse<TenantBean> pageTenants(TenantParam param) throws BaseException, Exception;
	/**
	* <p>Description: </p>
	* <p>@Author:ygao/高远</p>
	* @param tenantBean
	* @date 2018/2/26 13:25
	* @return 
	*/
	public TenantBean addTenant(TenantBean tenantBean)throws BaseException,Exception;
	
	/**
	 * <br/>Description:根据用户id查询所属租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantBean findByUserId(Integer userId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:查询所有租户列表
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<TenantBean> listAllTenant() throws BaseException, Exception;

	/**
	 * <br/>Description:根据标识查询租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantBean findByTenantMark(String tenantMark) throws BaseException, Exception;

	/**
	 * <br/>Description:根据id查询租户
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantBean findByTenantId(Integer tenantId) throws BaseException, Exception;

	/**
	 * <br/>Description:根据租户id和租户应用密钥获取租户访问token对象
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public String getAccessTokenByTenantMark(String tenantMark) throws BaseException, Exception;

	/**
	 * <br/>Description:根据租户标识获取租户获取该租户下所有用户信息
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<UserEntity> listUsersByTenantMark(String tenantMark) throws BaseException,Exception;

	/**
	 * <br/>Description:根据租户id和租户应用密钥获取租户访问token对象
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantAccessToken getAccessTokenByTenantIdAndSecret(String tenantId, String tenantSecret) throws BaseException, Exception;

	/**
	 * <br/>Description:根据租户编码和访问令牌获取该租户的所有用户信息
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<UserAPIBean> listUsersByTokenAndMark(String accessToken, String tenantMark) throws BaseException, Exception;


	/**
	 * <br/>Description:判断访问令牌是否失效
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean isValidAccessToken(String accessToken) throws BaseException, Exception;


	/**
	 * <br/>Description:令牌验证登录inews系统
	 * <p>Author:jlchen/陈金梁</p>
	 * @Param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean loginWithoutPasswd(String accessToken, String signature, String account) throws BaseException, Exception;



}
