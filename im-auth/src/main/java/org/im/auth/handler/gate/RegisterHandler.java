package org.im.auth.handler.gate;

import java.util.Date;

import org.im.auth.IMHandler;
import org.im.auth.Worker;
import org.im.protocol.bean.RegisterBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.common.util.date.DateUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * 客户注册处理
 * 
 * @author riozenc
 *
 */
public class RegisterHandler extends IMHandler {
	private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);

	public RegisterHandler(String uid, Message msg, ChannelHandlerContext ctx) {
		super(uid, msg, ctx);
	}

	@Override
	protected void excute(Worker worker) throws Exception {
		RegisterBean registerBean = (RegisterBean) msg;
		String userId = registerBean.getUserId();
		// 这个userId是有效的
//		UserCache.addUser(userId);
		// 返回注册成功or失败
		
		registerBean.setDateTime(DateUtil.formatDateTime(new Date()));

		logger.info("{} 注册成功 {}.", userId,registerBean.getDateTime());
		ctx.writeAndFlush(registerBean);//
	}

}
