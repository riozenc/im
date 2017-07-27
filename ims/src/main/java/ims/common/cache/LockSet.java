/**
 * @Project:crm
 * @Title:LockSet.java
 * @Author:Riozenc
 * @Datetime:2016年9月4日 下午12:55:38
 * 
 */
package ims.common.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSet {

	private static Map<String, Lock> MAP = new HashMap<String, Lock>();
	private static Lock MAP_LOCK = new ReentrantLock(true);
	private static int SIZE = 50;

	private static void clear() {
		MAP_LOCK.lock();
		try {
			Iterator<Entry<String, Lock>> locks = MAP.entrySet().iterator();
			while (locks.hasNext()) {
				Entry<String, Lock> entry = locks.next();
				if (null != entry.getValue()) {
					if (entry.getValue().tryLock()) {
						entry.getValue().unlock();
						locks.remove();
					}
				}
			}
		} finally {
			MAP_LOCK.unlock();
		}
	}

	private static boolean addLock(String id, Lock lock) {
		MAP_LOCK.lock();
		try {
			if (!MAP.containsKey(id)) {
				MAP.put(id, lock);
				return true;
			} else {
				return false;
			}
		} finally {
			if (MAP.size() > SIZE) {
				clear();
			}
			MAP_LOCK.unlock();
		}
	}

	public static Lock getLock(String id) throws RuntimeException {

		MAP_LOCK.lock();
		try {
			if (null != id) {
				Lock lock = MAP.get(id);
				if (null == lock) {
					lock = new ReentrantLock(true);
					addLock(id, lock);
				}
				return lock;
			} else {
				throw new RuntimeException(id + " is null..");
			}
		} finally {
			MAP_LOCK.unlock();
		}

	}

}
