package org.im.client;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Dell on 2016/2/15. 模拟客户端聊天：自己给自己发消息
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
	public static ChannelHandlerContext _gateClientConnection;

	private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	String _userId = "";
	boolean _verify = false;
	private static int count = 0;

	public static AtomicLong increased = new AtomicLong(1);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws IOException {
		_gateClientConnection = ctx;

		_userId = Long.toString(increased.getAndIncrement());

		sendTest(ctx, _userId);
	}

	void sendTest(ChannelHandlerContext ctx, String userId) {
		System.out.println(userId);
		ByteBuf buf = Unpooled.buffer(1024);

		buf.writeBytes("FE FE FE 16 10 我来了 16".getBytes());

		// ctx.writeAndFlush("FE FE FE");

		ctx.writeAndFlush(buf);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message msg) throws Exception {
		logger.info("received message: {}", msg.getClass());

		if (msg != null) {
			_verify = true;
		}

		// 这样设置的原因是，防止两方都阻塞在输入上
		if (_verify) {

			Thread.sleep(Client.frequency);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
