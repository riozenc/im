package org.im.auth;

import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Dell on 2016/3/2.
 */
public abstract class IMHandler {
	protected final String _userid;
	protected final Message _msg;
	protected ChannelHandlerContext _ctx;

	protected IMHandler(String userid, Message msg, ChannelHandlerContext ctx) {
		_userid = userid;
		_msg = msg;
		_ctx = ctx;
	}

	protected abstract void excute(Worker worker) throws Exception;
}
