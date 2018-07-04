package com.uec.ibrain.ibrain.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.Date;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p>
 * <p>Description: 自定义用户权限验证和授权，增加登录类型：1密码登录；2接口令牌登录3手机验证码登录 </p>
 * <p>参考org.apache.shiro.authcUsernamePasswordToken，增加了用户类型参数 </p>
 * <p>Author:jlchen/陈金梁</p>
 */

public class UsernamePasswordLoginTypeToken  extends UsernamePasswordToken {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码, in char[] format
     */
    private char[] password;

    /**
     * 是否记住我
     * 默认值：<code>false</code>
     */
    private boolean rememberMe = false;

    /**
     * 主机名称或ip
     */
    private String host;

    /**
     * 用户、登录类型 1密码登录；2接口令牌登录3手机验证码登录
     */
    private Integer loginType;
    /**
     * token秘钥
     */
    private String accessToken; //
    /**
     * 时间
     */
    private Date datetime;

    public UsernamePasswordLoginTypeToken () {
    }

    /**
     * 构造方法
     * @param username 用户名
     * @param password 密码（char[]）
     * @param rememberMe 是否记住我
     * @param host 主机或ip
     * @param loginType 用户类型
     */
    public UsernamePasswordLoginTypeToken (final String username, final char[] password,
                                         final boolean rememberMe, final String host, final Integer loginType,
                                           final String accessToken,final Date  datetime) {

        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
        this.loginType = loginType;
        this.accessToken = accessToken;
        this.datetime = datetime;
    }

    /**
     * 构造方法
     * @param username 用户名
     * @param password 密码（char[]）
     */
    public UsernamePasswordLoginTypeToken (final String username, final char[] password,
                                            final Integer loginType) {
        this(username,password, false, null,loginType,null, null);

    }
    /**
     * 构造方法
     * @param username 用户名
     * @param password 密码（String）
     * @param rememberMe 是否记住我
     * @param host 主机或ip
     * @param LoginType 用户类型
     */
    public UsernamePasswordLoginTypeToken (final String username, final String password,
                                         final boolean rememberMe, final String host, final Integer LoginType,
                                           final String accessToken, final Date datetime) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host, LoginType,accessToken,datetime);
    }

    /**
     * 构造方法
     * @param username 用户名
     * @param password 密码（String）
     * @param LoginType 用户类型
     */
    public UsernamePasswordLoginTypeToken (final String username, final String password,
                                            final Integer LoginType) {
        this(username, password, false, null, LoginType,null,null);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Simply returns {@link #getUsername() getUsername()}.
     *
     * @return the {@link #getUsername() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    public Object getPrincipal() {
        return getUsername();
    }

    /**
     * Returns the {@link #getPassword() password} char array.
     *
     * @return the {@link #getPassword() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    public Object getCredentials() {
        return getPassword();
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * 清除数据
     * 密码如果不为空，设置成0x00
     */
    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        this.loginType = null;
        this.datetime = null;
        this.accessToken = null;

        if (this.password != null) {
            for (int i = 0; i < password.length; i++) {
                this.password[i] = 0x00;
            }
            this.password = null;
        }

    }

    /**
     * 重写toString方法
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(username);
        sb.append(", loginType=").append(loginType);
        sb.append(", rememberMe=").append(rememberMe);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", datetime=").append(datetime);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }


}