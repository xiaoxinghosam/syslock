package com.fzz.cloud.fzzcloudlockingSystem.entity;
import java.util.Date;
/**
 * <p>
 * 
 * </p>
 *
 * @author sky
 * @since 2018-03-20
 */

public class SysUser  {

	private String id;
    /**
     * 用户昵称
     */
	private String nickname;
    /**
     * 邮箱|登录帐号
     */
	private String weixin;
    /**
     * 密码
     */
	private String pswd;
    /**
     * 最后登录时间
     */

	private Date lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
	private String status;
	/**
     * 创建时间
     */

	private Date createTime;
	
	public SysUser(){}
	
	public SysUser(SysUser user) {
		super();
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.weixin = user.getWeixin();
		this.pswd = user.getPswd();
		this.createTime = user.getCreateTime();
		this.lastLoginTime = user.getLastLoginTime();
		this.status = user.getStatus();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
