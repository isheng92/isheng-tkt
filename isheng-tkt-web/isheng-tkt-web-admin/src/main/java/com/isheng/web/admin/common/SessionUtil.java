package com.isheng.web.admin.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.isheng.common.constant.SysConfig;
import com.isheng.model.auth.domain.SessionUser;

public class SessionUtil {

	/**
	 * 获取存放在会话中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionAttr(String key) {
		Session session = SecurityUtils.getSubject().getSession();
		if (null != session) {
			return session.getAttribute(key);
		}
		return null;
	}
	
	/**
	 * 获取存放在会话中的已知类型的对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttr(String key, Class<T> clazz) {
		return (T) getSessionAttr(key);
	}
	
	/**
	 * 设置会话属性
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSessionAttr(String key, Object value) {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(key, value);
	}
	
	/**
	 * 移除会话属性
	 * 
	 * @param key
	 */
	public static void removeSessionAttr(String key) {
		Session session = SecurityUtils.getSubject().getSession(false);
		if (null != session) {
			session.removeAttribute(key);
		}
	}
	
	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	public static SessionUser getCurrentUser() {
		return getSessionAttr(SysConfig.SESSION_USER_KEY, SessionUser.class);
	}
}
