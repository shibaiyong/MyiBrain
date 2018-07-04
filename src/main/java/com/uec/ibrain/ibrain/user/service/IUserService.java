package com.uec.ibrain.ibrain.user.service;


import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.user.bean.OrganizationBean;
import com.uec.ibrain.ibrain.user.bean.UserCreateParam;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import com.uec.ibrain.ibrain.user.bean.UserParam;

import java.util.List;

/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户业务逻辑类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IUserService {

	/**
	 * <br/>Description:根据用户名查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserEntity findByUserName(String userName) throws BaseException, Exception;

	/**
	 * 根据 id查询用户
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserEntity findById(Integer id) throws BaseException, Exception;

	/**
	 * 修改密码
	 * @param innerid 用户ID
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean updatePassword(Integer innerid, String oldPassword, String newPassword) throws BaseException, Exception;

	/**
	 * 修改用户信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(UserEntity param) throws Exception;
}
