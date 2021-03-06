/**
 * Title:LogicServerHandler.java
 * Author:riozenc
 * Datetime:2017年7月17日 下午1:00:02
**/
package org.im.logic.handler;

import org.im.logic.HandlerManager;
import org.im.logic.IMHandler;
import org.im.logic.Worker;
import org.im.protocol.analysis.ParseMap;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogicServerHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(LogicServerHandler.class);
	private static ChannelHandlerContext gateLogicChannelHandlerContext;
	private static ChannelHandlerContext authLogicChannelHandlerContext;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub
		int order = ParseMap.getOrder(msg.getClass());
		IMHandler handler;
		if (msg instanceof GreetBean) {
			handler = HandlerManager.getHandler(order, msg.getUid(), msg, ctx);
		} else {
			handler = HandlerManager.getHandler(order, msg.getUid(), msg, getGateLogicChannelHandlerContext());
		}

		Worker.dispatch(msg.getUid(), handler);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// super.exceptionCaught(ctx, cause);
		logger.error("An Exception Caught");
	}

	public static void setGateLogicChannelHandlerContext(ChannelHandlerContext ctx) {
		gateLogicChannelHandlerContext = ctx;
	}

	public static ChannelHandlerContext getGateLogicChannelHandlerContext() {
		if (gateLogicChannelHandlerContext != null) {
			return gateLogicChannelHandlerContext;
		} else {
			return null;
		}
	}

	public static void setAuthLogicChannelHandlerContext(ChannelHandlerContext ctx) {
		authLogicChannelHandlerContext = ctx;
	}

	public static ChannelHandlerContext getAuthLogicChannelHandlerContext() {
		if (authLogicChannelHandlerContext != null) {
			return authLogicChannelHandlerContext;
		} else {
			return null;
		}
	}
}
