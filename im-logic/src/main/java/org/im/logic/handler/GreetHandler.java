package org.im.logic.handler;

import org.im.logic.IMHandler;
import org.im.logic.Worker;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class GreetHandler extends IMHandler {
	private static final Logger logger = LoggerFactory.getLogger(GreetHandler.class);

	public GreetHandler(String uid, Message msg, ChannelHandlerContext ctx) {
		super(uid, msg, ctx);
	}

	@Override
	protected void excute(Worker worker) {
		GreetBean greetBean = (GreetBean) msg;

		if (greetBean.getType() == GreetBean.GREET_AUTH) {
			LogicServerHandler.setAuthLogicChannelHandlerContext(ctx);
			logger.info("[Auth-Logic] connection is established");
		} else if (greetBean.getType() == GreetBean.GREET_LOGIC) {
			LogicServerHandler.setGateLogicChannelHandlerContext(ctx);
			logger.info("[Gate-Logic] connection is established");
		}
	}
}
