/**
 * @Project:im-auth
 * @Title:AuthServerHandler.java
 * @Author:Riozenc
 * @Datetime:2017年7月14日 下午12:30:06
 * 
 */
package org.im.auth.handler.gate;

import java.util.HashMap;

import org.im.auth.HandlerManager;
import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.protocol.analysis.ParseMap;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class AuthServerHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(AuthServerHandler.class);
	private static ChannelHandlerContext gateAuthChannelHandlerContext;

	public static void setGateAuthChannelHandlerContext(ChannelHandlerContext ctx) {
		gateAuthChannelHandlerContext = ctx;
	}

	public static ChannelHandlerContext getGateAuthChannelHandlerContext() {
		if (gateAuthChannelHandlerContext != null) {
			return gateAuthChannelHandlerContext;
		} else {
			return null;
		}
	}

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
			handler = HandlerManager.getHandler(order, msg.getUid(), msg, ctx);
		} else {
			handler = HandlerManager.getHandler(order, msg.getUid(), msg, getGateAuthChannelHandlerContext());
		}
		Worker.dispatch(msg.getUid(), handler);
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
}
