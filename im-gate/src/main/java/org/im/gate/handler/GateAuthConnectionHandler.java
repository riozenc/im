/**
 * Title:GateAuthConnectionHandler.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:02:28
**/
package org.im.gate.handler;

import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理 注册通讯服务器的请求
 * 
 * @author rioze
 *
 */
public class GateAuthConnectionHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(GateAuthConnectionHandler.class);
	private static ChannelHandlerContext _gateAuthConnection;

	public static ChannelHandlerContext getGateAuthConnection() {
		return _gateAuthConnection;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		_gateAuthConnection = ctx;
		logger.info("[Gate-Auth] connection is established");

		sendGreet2Auth();// 发送内部协议

	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub

	}

	private void sendGreet2Auth() {
		// 向auth送Greet协议
		GreetBean greetBean = new GreetBean();
		greetBean.setType(GreetBean.GREET_AUTH);
		getGateAuthConnection().writeAndFlush(greetBean.message2Byte());
		logger.info("Gate send Green to Auth.");
	}
}
