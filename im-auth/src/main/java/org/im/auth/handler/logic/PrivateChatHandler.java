/**
 * Title:PrivateChatHandler.java
 * Author:riozenc
 * Datetime:2017年7月18日 下午3:49:08
**/
package org.im.auth.handler.logic;

import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.protocol.bean.PrivateChatBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class PrivateChatHandler extends IMHandler {

	private static final Logger logger = LoggerFactory.getLogger(PrivateChatHandler.class);

	public PrivateChatHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
		super(userid, netid, msg, ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void excute(Worker worker) throws Exception {
		// TODO Auto-generated method stub

		PrivateChatBean privateChatBean = (PrivateChatBean) msg;

		logger.info("PrivateChat from {} to {} : content {}", privateChatBean.getFrom(), privateChatBean.getTo(),
				privateChatBean.getContent());

		privateChatBean.setUID(privateChatBean.getTo());
		// 是否认证，是否在线等特殊处理
		ctx.writeAndFlush(privateChatBean);
	}

}
