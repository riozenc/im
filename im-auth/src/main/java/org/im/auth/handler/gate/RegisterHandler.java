package org.im.auth.handler.gate;

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
public class RegisterHandler extends IMHandler {
	private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);

	public RegisterHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
		super(userid,netid, msg, ctx);
	}

	@Override
	protected void excute(Worker worker) throws Exception {
		RegisterBean registerBean = (RegisterBean) msg;
		String userId = registerBean.getUserId();
		// 这个userId是有效的
		UserCache.addUser(userId);

		// 返回注册成功or失败
		ctx.writeAndFlush(registerBean);//
	}

}
