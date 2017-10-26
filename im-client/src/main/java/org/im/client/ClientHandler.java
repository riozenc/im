package org.im.client;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.im.protocol.bean.PrivateChatBean;
import org.im.protocol.bean.RegisterBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.common.util.date.DateUtil;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Dell on 2016/2/15. 模拟客户端聊天：自己给自己发消息
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
	public static ChannelHandlerContext _gateClientConnection;

	private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	boolean _verify = false;

	public static AtomicLong increased = new AtomicLong(1);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws IOException {
		_gateClientConnection = ctx;
		sendTest(ctx);
	}

	void sendTest(ChannelHandlerContext ctx) {
		RegisterBean registerBean = new RegisterBean();
		registerBean.setUserId("chiziyue");
		registerBean.setUid("17142");
		ChannelFuture cf = ctx.writeAndFlush(registerBean);
		System.out.println(cf);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message msg) throws Exception {
		logger.info("received message: {}", msg.getClass());
		System.out.println("received message: " + msg.getClass());

		if (msg.getClass() == PrivateChatBean.class) {
			PrivateChatBean p = (PrivateChatBean) msg;
			if (p.getContent().equals("9")) {
				PrivateChatBean bean = new PrivateChatBean();
				bean.setContent("今天天气不错！");
				bean.setFrom("17142");
				bean.setTo(p.getFrom());
				bean.setUid("17142");
				bean.setDateTime(DateUtil.formatDateTime(new Date()));
				channelHandlerContext.writeAndFlush(bean);
			}
		}
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
