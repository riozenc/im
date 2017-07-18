/**
 * Title:PrivateChatHandler.java
 * Author:riozenc
 * Datetime:2017年7月18日 上午9:30:08
**/
package org.im.logic.handler;

import org.im.logic.IMHandler;
import org.im.logic.Worker;
import org.im.protocol.bean.PrivateChatBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class PrivateChatHandler extends IMHandler {

	private static final Logger logger = LoggerFactory.getLogger(PrivateChatHandler.class);

	protected PrivateChatHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
		super(userid, netid, msg, ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void excute(Worker worker) {
		// TODO Auto-generated method stub
		PrivateChatBean privateChatBean = (PrivateChatBean) msg;

		// 转发给 auth(logic-->auth)
		LogicServerHandler.getAuthLogicChannelHandlerContext().writeAndFlush(privateChatBean);

		// 回给网关，给发消息者回应
		// ctx.writeAndFlush();//回应bean ： 返回码和接收人。
	}

}
