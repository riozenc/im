/**
 * Title:ParseMap.java
 * Author:riozenc
 * Datetime:2017年4月20日 下午1:59:20
**/
package org.im.protocol.analysis;

import java.io.IOException;
import java.util.HashMap;

import org.im.protocol.msg.AbstractMessage;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseMap {
	private static final Logger logger = LoggerFactory.getLogger(ParseMap.class);

	@FunctionalInterface
	public interface Parsing {
		Message process(byte[] bytes) throws IOException;
	}

	private static HashMap<Integer, Class<? extends AbstractMessage>> parseMap = new HashMap<>();
	public static HashMap<Class<? extends AbstractMessage>, Integer> msg2Order = new HashMap<>();

	public static void register(int order, Class<? extends AbstractMessage> clazz) {
		if (parseMap.get(order) == null)
			parseMap.put(order, clazz);
		else {
			logger.error(clazz + " has been registered in parseMap, order: {}", order);
			return;
		}

		if (msg2Order.get(clazz) == null)
			msg2Order.put(clazz, order);
		else {
			logger.error(clazz + " has been registered in msg2ptoNum, order: {}", order);
			return;
		}
	}

	public static Message getMessage(int order, byte[] bytes)
			throws IOException, InstantiationException, IllegalAccessException {
		Class<? extends AbstractMessage> clazz = parseMap.get(order);
		if (clazz == null) {
			logger.error("UnKnown Protocol order: {}", order);
		}

		AbstractMessage abstractMessage = clazz.newInstance();

		Message msg = abstractMessage.byte2Message(bytes);

		return msg;
	}

	public static int getOrder(Class<?> clz) {
		return msg2Order.get(clz);
	}
}
