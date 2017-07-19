/**
 * @Project:im-auth
 * @Title:UserCache.java
 * @Author:Riozenc
 * @Datetime:2017年7月14日 下午1:17:32
 * 
 */
package org.im.auth.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.im.auth.cache.bean.AuthenticatedUser;

/**
 * 认证用户列表（可以对等为在线用户列表）
 * 
 * @author riozenc
 *
 */
public class UserCache {
	private static final ConcurrentHashMap<String, AuthenticatedUser> map = new ConcurrentHashMap<String, AuthenticatedUser>();

	public static void addUser(String userId) {
		if (getUser(userId) == null) {
			map.put(userId, new AuthenticatedUser(userId));
		}

	}

	public static void removeUser(String userId) {
		map.remove(userId);
	}

	public static AuthenticatedUser getUser(String userId) {
		return map.get(userId);
	}

}
