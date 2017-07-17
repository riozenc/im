package org.im.auth.handler;

import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.auth.cache.UserCache;
import org.im.protocol.bean.RegisterBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

/**
 * 客户注册处理
 * 
 * @author riozenc
 *
 */
public class CRegisterHandler extends IMHandler {
	private static final Logger logger = LoggerFactory.getLogger(CRegisterHandler.class);

	public CRegisterHandler(String userid, Message msg, ChannelHandlerContext ctx) {
		super(userid, msg, ctx);
	}

	@Override
	protected void excute(Worker worker) throws Exception {
		RegisterBean msg = (RegisterBean) _msg;
		String userId = msg.getUserId();
		// 这个userId是有效的
		UserCache.addUser(userId);
	}

}
