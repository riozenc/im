package ims.common.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoginSessionCache {
	private static final Map<String, Serializable> map = new ConcurrentHashMap<>();
	private static final Lock lock = new ReentrantLock();

	public static Serializable put(String key, Serializable value) {
		return map.put(key, value);
	}

	public static Serializable get(String key) {
		return map.get(key);
	}

}
