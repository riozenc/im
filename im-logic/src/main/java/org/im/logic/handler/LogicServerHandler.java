/**
 * Title:LogicServerHandler.java
 * Author:riozenc
 * Datetime:2017年7月17日 下午1:00:02
**/
package org.im.logic.handler;

import java.util.HashMap;

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
	private static ChannelHandlerContext _gateLogicConnection;
	private static ChannelHandlerContext _authLogicConnection;

	// 激活用户Map
	private static HashMap<String, Long> userid2netidMap = new HashMap<>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub
		int order = ParseMap.getOrder(msg.getClass());
		IMHandler handler;
		if (msg instanceof GreetBean) {
			handler = HandlerManager.getHandler(order, registerBean.getUserId(), msg, ctx);
		} else {
			handler = HandlerManager.getHandler(order, registerBean.getUserId(), msg, getGateLogicConnection());
		}

		Worker.dispatch(gt.getUserId(), handler);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// super.exceptionCaught(ctx, cause);
		logger.error("An Exception Caught");
	}

	public static void putInUseridMap(String userid, Long netId) {
		userid2netidMap.put(userid, netId);
	}

	public static Long getNetidByUserid(String userid) {
		Long netid = userid2netidMap.get(userid);
		if (netid != null) {
			return netid;
		} else {
			return null;
		}
	}

	public static void setGateLogicConnection(ChannelHandlerContext ctx) {
		_gateLogicConnection = ctx;
	}

	public static ChannelHandlerContext getGateLogicConnection() {
		if (_gateLogicConnection != null) {
			return _gateLogicConnection;
		} else {
			return null;
		}
	}

	public static void setAuthLogicConnection(ChannelHandlerContext ctx) {
		_authLogicConnection = ctx;
	}

	public static ChannelHandlerContext getAuthLogicConnection() {
		if (_authLogicConnection != null) {
			return _authLogicConnection;
		} else {
			return null;
		}
	}
}
