package com.uec.ibrain.ibrain.auth;

import com.uec.ibrain.ibrain.common.exception.BaseException;
import com.uec.ibrain.ibrain.common.util.CommonUtil;
import com.uec.ibrain.ibrain.tenant.service.ITenantService;
import com.uec.ibrain.ibrain.user.bean.RoleEntity;
import com.uec.ibrain.ibrain.user.bean.UserEntity;
import com.uec.ibrain.ibrain.user.service.IRoleService;
import com.uec.ibrain.ibrain.user.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.util.Date;
import java.util.List;


public class MyShiroRealm extends AuthorizingRealm{

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    
    @Autowired
	@Qualifier("userService")
    @Lazy
	private IUserService userService;
	
	@Autowired
	@Qualifier("roleService")
	@Lazy
	private IRoleService roleService;

	@Autowired
	@Qualifier("tenantService")
	private ITenantService tenantService;

	/**
	 * 权限认证，为当前登录的Subject授予角色和权限
	 * 经测试：本例中该方法的调用时机为需授权资源被访问时
	 * 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
	 * 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
	 * @param principalCollection
	 * @return
	 */
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        logger.info("----------------------login name="+loginName+"-----------------------");
        //到数据库查是否有此对象
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        try {
        	List<RoleEntity> roles = roleService.findByUserName(loginName);
			if(roles==null||roles.isEmpty()){
				logger.error("*******************用户角色为空！***************************");
			}else{
				for(RoleEntity role:roles){
					info.addRole(role.getName());
//					logger.info("##为用户----"+loginName+"----获取角色###"+role.getName());
//					List<Permission> perms=roleAdapterService.findPermissionsByRoleId(role.getId()+"");
//					if(perms!=null&&!perms.isEmpty()){
//						for(Permission perm:perms){
//							info.addStringPermission(perm.getCode());
////							logger.info("##为用户----"+loginName+"----获取角色----"+role.getName()+"----下的权限："+perm.getName()+"---"+perm.getCode());
//						}
//					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("获取用户角色权限失败!");
		}
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return info;
    }


	/**
	 * <p>Description:登录认证</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: doGetAuthenticationInfo
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
//		 UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
		UsernamePasswordLoginTypeToken token=(UsernamePasswordLoginTypeToken) authenticationToken;
		logger.info("login = " + CommonUtil.toJson(token));
		Integer  loginType = token.getLoginType(); // 1密码登录；2接口令牌登录3手机验证码登录

		if(1 == loginType){
			try {
				UserEntity user = userService.findByUserName(token.getUsername());
				if(null != user){
					// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
					return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
				}
			} catch (BaseException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}else if(2 == loginType){
			String accessToken = token.getAccessToken();
			if(null != accessToken){
				try {
					boolean flag = tenantService.isValidAccessToken(accessToken);
					logger.info("accessToken = " + accessToken + ", flag = " + flag);
					if(flag){ //当accesstoken在有效期内时，进一步验证
						Date datetime = token.getDatetime();
						if(null == datetime){
							datetime = new Date();
						}
						//signature = md5(name/account+accessToken+当前时间/传入时间（保留至小时数如2017010313）)
						String t = CommonUtil.formatDateToString(datetime);
						String time = t.substring(0,4) + t.substring(5,7) + t.substring(8,10) +  t.substring(11,13);
						logger.info("token验证信息:access_token"+ token.getAccessToken() + ", account =" + token.getUsername() + ",time = " + time);
//						String signature = CommonUtil.encryptByMd5(token.getAccessToken()+token.getUsername()+time);
						String signature = DigestUtils.md5Hex(token.getAccessToken()+token.getUsername()+time);

						// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
						return new SimpleAuthenticationInfo(token.getUsername(), signature, getName());

					}
				} catch (BaseException e) {
					logger.error(e.getMessage());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}

		}else if(3 == loginType){

		}

		return null;
	}
}