/**
 * Title:GateLogicConnectionHandler.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:00:07
**/
package org.im.gate.handler;

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
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub

	}

}