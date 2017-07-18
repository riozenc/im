/**
 * @Project:im-auth
 * @Title:AuthLogicHandler.java
 * @Author:Riozenc
 * @Datetime:2017年7月17日 下午10:56:14
 * 
 */
package org.im.auth.handler.logic;

import org.im.auth.HandlerManager;
import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.auth.handler.gate.AuthServerHandler;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class AuthLogicHandler extends SimpleChannelInboundHandler<Message> {

	private static final Logger logger = LoggerFactory.getLogger(AuthLogicHandler.class);

	private static ChannelHandlerContext authLogicChannelHandlerContext;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		setAuthLogicChannelHandlerContext(ctx);
		logger.info("[Auth-Logic] connection is established");
		// 向logic发送Greet协议
		sendGreet2Logic();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub

		IMHandler handler = HandlerManager.getHandler(msg.getOrder(), msg.getUID(), msg,
				AuthServerHandler.getGateAuthChannelHandlerContext());
		if (handler == null) {
			logger.error("Error Messgae Type: {}", msg.getClass());
			return;
		}
		Worker.dispatch(msg.getUID(), handler);
	}

	private void sendGreet2Logic() {
		GreetBean greetBean = new GreetBean();
		greetBean.setType(GreetBean.GREET_AUTH);
		getAuthLogicChannelHandlerContext().writeAndFlush(greetBean.message2Byte());
		logger.info("Auth send Green to Logic.");
	}

	public static ChannelHandlerContext getAuthLogicChannelHandlerContext() {
		return authLogicChannelHandlerContext;
	}

	public static void setAuthLogicChannelHandlerContext(ChannelHandlerContext ctx) {
		authLogicChannelHandlerContext = ctx;
	}
}