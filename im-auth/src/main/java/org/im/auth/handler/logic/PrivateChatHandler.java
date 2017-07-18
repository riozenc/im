/**
 * Title:PrivateChatHandler.java
 * Author:riozenc
 * Datetime:2017年7月18日 下午3:49:08
**/
package org.im.auth.handler.logic;

import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.auth.handler.gate.AuthServerHandler;
import org.im.protocol.bean.PrivateChatBean;
import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;

public class PrivateChatHandler extends IMHandler {

	protected PrivateChatHandler(String userid, Message msg, ChannelHandlerContext ctx) {
		super(userid, msg, ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void excute(Worker worker) throws Exception {
		// TODO Auto-generated method stub

		PrivateChatBean privateChatBean = (PrivateChatBean) msg;
		
		String toUser = privateChatBean.getTo();

		AuthServerHandler.getNetidByUserid(userid);
	}

}
