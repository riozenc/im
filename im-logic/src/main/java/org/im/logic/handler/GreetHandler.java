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
	private static final int AUTH = 0;
	private static final int LOGIC = 1;

	public GreetHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
		super(userid, netid, msg, ctx);
	}

	@Override
	protected void excute(Worker worker) {
		GreetBean msg = (GreetBean) _msg;
		if (msg.getType() == AUTH) {
			LogicServerHandler.setAuthLogicConnection(_ctx);
			logger.info("[Auth-Logic] connection is established");
		} else if (msg.getType() == LOGIC) {
			LogicServerHandler.setGateLogicConnection(_ctx);
			logger.info("[Gate-Logic] connection is established");
		}
	}
}
