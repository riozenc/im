/**
 * Title:GateServerHandler.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:04:45
**/
package org.im.gate.handler;

import org.im.gate.ClientMessage;
import org.im.gate.connection.ClientConnection;
import org.im.gate.connection.ClientConnectionMap;
import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GateServerHandler extends SimpleChannelInboundHandler<Message> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ClientConnectionMap.addClientConnection(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
		// TODO 最好加一个通知客户端收到消息的通知
		ClientConnection connection = ClientConnectionMap.getClientConnection(ctx);
		ClientMessage.processTransferHandler(message, connection);
	}

	/**
	 * 通道失效，强行关闭等
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		ClientConnectionMap.removeClientConnection(ctx);
	}

}
