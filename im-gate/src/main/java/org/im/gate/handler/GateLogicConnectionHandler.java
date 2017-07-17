/**
 * Title:GateLogicConnectionHandler.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:00:07
**/
package org.im.gate.handler;

import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GateLogicConnectionHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(GateLogicConnectionHandler.class);

	private static ChannelHandlerContext _gateLogicConnection;

	public static ChannelHandlerContext getGatelogicConnection() {
		return _gateLogicConnection;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		_gateLogicConnection = ctx;
		logger.info("[Gate-Logic] connection is established");

		// 向logic发送Greet
		sendGreet2Logic();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub

	}

	private void sendGreet2Logic() {
		GreetBean greetBean = new GreetBean();
		greetBean.setType(GreetBean.GREET_LOGIC);
		getGatelogicConnection().writeAndFlush(greetBean.message2Byte());
		logger.info("Gate send Green to Logic.");
	}
}
