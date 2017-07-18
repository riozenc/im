/**
 * @Project:im-auth
 * @Title:GreetHandler.java
 * @Author:Riozenc
 * @Datetime:2017年7月17日 下午8:59:47
 * 
 */
package org.im.auth.handler;

import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.auth.handler.gate.AuthServerHandler;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class GreetHandler extends IMHandler {
	private static final Logger logger = LoggerFactory.getLogger(GreetHandler.class);

	protected GreetHandler(String userid, Message msg, ChannelHandlerContext ctx) {
		super(userid, msg, ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void excute(Worker worker) throws Exception {
		// TODO Auto-generated method stub
		AuthServerHandler.setGateAuthChannelHandlerContext(ctx);
		logger.info("[Gate-Auth] connection is established");
	}

}
