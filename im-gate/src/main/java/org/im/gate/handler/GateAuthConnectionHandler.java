/**
 * Title:GateAuthConnectionHandler.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:02:28
**/
package org.im.gate.handler;

import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GateAuthConnectionHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(GateAuthConnectionHandler.class);
	private static ChannelHandlerContext _gateAuthConnection;

	public static ChannelHandlerContext getGateAuthConnection() {
		return _gateAuthConnection;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub

	}
}
