/**
 * @Project:im-auth
 * @Title:UserCache.java
 * @Author:Riozenc
 * @Datetime:2017年7月14日 下午1:17:32
 * 
 */
package org.im.auth.cache;

import java.util.concurrent.ConcurrentHashMap;

public class UserCache {
	private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

	public static void addUser(String userId) {
		map.put(userId, userId);
	}

	public static void removeUser(String userId) {
		map.remove(userId);
	}

}
