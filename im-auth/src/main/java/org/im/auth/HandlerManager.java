package org.im.auth;

import io.netty.channel.ChannelHandlerContext;

import org.im.auth.handler.GreetHandler;
import org.im.auth.handler.gate.RegisterHandler;
import org.im.auth.handler.logic.PrivateChatHandler;
import org.im.protocol.analysis.ParseMap;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.bean.PrivateChatBean;
import org.im.protocol.bean.RegisterBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class HandlerManager {
	private static final Logger logger = LoggerFactory.getLogger(HandlerManager.class);

	private static final Map<Integer, Constructor<? extends IMHandler>> _handlers = new HashMap<>();

	public static void register(Class<? extends Message> msg, Class<? extends IMHandler> handler) {

		int order = ParseMap.getOrder(msg);
		try {
			Constructor<? extends IMHandler> constructor = handler.getConstructor(String.class, long.class,
					Message.class, ChannelHandlerContext.class);
			_handlers.put(order, constructor);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public static IMHandler getHandler(int order, String userId, Message msg, ChannelHandlerContext ctx)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<? extends IMHandler> constructor = _handlers.get(order);
		if (constructor == null) {
			logger.error("handler not exist, Message Order: {}", order);
			return null;
		}

		return constructor.newInstance(userId, msg, ctx);
	}

	public static void initHandlers() {

		HandlerManager.register(GreetBean.class, GreetHandler.class);

		HandlerManager.register(RegisterBean.class, RegisterHandler.class);

		HandlerManager.register(PrivateChatBean.class, PrivateChatHandler.class);
	}
}
