package com.jeecms.common.email;

/**
 * 邮件发送者
 * 
 * @author liufang
 * 
 */
public interface EmailSender {
	/**
	 * 发送服务器IP
	 * 
	 * @return
	 */
	public String getHost();

	/**
	 * 发送服务器端口
	 * 
	 * @return
	 */
	public Integer getPort();

	/**
	 * 发送编码
	 * 
	 * @return
	 */
	public String getEncoding();

	/**
	 * 登录名
	 * 
	 * @return
	 */
	public String getUsername();

	/**
	 * 密码
	 * 
	 * @return
	 */
	public String getPassword();

	/**
	 * 发送人
	 * 
	 * @return
	 */
	public String getPersonal();
	/**
	 * 发送标题
	 * 
	 * @return
	 */
	public String getTitle();
	/**
	 * 发送内容
	 * 
	 * @return
	 */
	public String getContent();
	/**
	 * 回转地址
	 * 
	 * @return
	 */
	public String getReturnUrl();
	/**
	 * 设置发送内容
	 * 
	 * @return
	 */
	public void setContent(String email_text);
}
