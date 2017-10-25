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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GateLogicHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(GateLogicHandler.class);

	private static ChannelHandlerContext gateLogicChannelHandlerContext;

	public static ChannelHandlerContext getGatelogicChannelHandlerContext() {
		return gateLogicChannelHandlerContext;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		gateLogicChannelHandlerContext = ctx;
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
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(greetBean.message2Byte());
		getGatelogicChannelHandlerContext().writeAndFlush(buf);
		logger.info("Gate send Green to Logic.");
	}
}
