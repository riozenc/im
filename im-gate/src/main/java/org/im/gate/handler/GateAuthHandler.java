/**
 * Title:GateAuthConnectionHandler.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:02:28
**/
package org.im.gate.handler;

import org.im.gate.connection.ClientConnectionMap;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理 注册通讯服务器的请求
 * 
 * @author rioze
 *
 */
public class GateAuthHandler extends SimpleChannelInboundHandler<Message> {
	private static final Logger logger = LoggerFactory.getLogger(GateAuthHandler.class);
	private static ChannelHandlerContext gateAuthChannelHandlerContext;

	public static ChannelHandlerContext getGateAuthChannelHandlerContext() {
		return gateAuthChannelHandlerContext;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		gateAuthChannelHandlerContext = ctx;
		logger.info("[Gate-Auth] connection is established");
		sendGreet2Auth();// 发送内部协议
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub
		String uid = msg.getUid();
		ChannelFuture channelFuture = ClientConnectionMap.getClientConnection(uid).getCtx().writeAndFlush(msg);
		System.out.println(channelFuture);
	}

	private void sendGreet2Auth() {
		// 向auth送Greet协议
		GreetBean greetBean = new GreetBean();
		greetBean.setType(GreetBean.GREET_AUTH);
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(greetBean.message2Byte());
		getGateAuthChannelHandlerContext().writeAndFlush(buf);
		logger.info("Gate send Green to Auth.");
	}
}
