package com.uec.ibrain.ibrain.user.service;

import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.user.bean.RoleEntity;
import com.uec.ibrain.ibrain.user.bean.UserRoleEntity;

import java.util.List;


public interface IRoleService {
	
	/**
	 * <br/>Description:根据id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RoleEntity findById(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:创建角色
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param role
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RoleEntity createRole(RoleEntity role) throws BaseException, Exception;
	
	/**
	 * <br/>Description:更新角色信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param role
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RoleEntity updateRole(RoleEntity role) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除角色
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 */
	public void deleteRole(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据用户id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<RoleEntity> findByUserId(Integer userId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据用户名查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<RoleEntity> findByUserName(String userName) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加用户角色关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userRole
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserRoleEntity addUserRole(UserRoleEntity userRole) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据用户id删除用户权限关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userId
	 * @throws BaseException
	 * @throws Exception
	 */
	public void deleteUserRoleByUserId(Integer userId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:查询角色列表
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<RoleEntity> listRoles() throws BaseException, Exception;

}
