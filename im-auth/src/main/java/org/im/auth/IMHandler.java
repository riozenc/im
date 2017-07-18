package org.im.auth;

import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Dell on 2016/3/2.
 */
public abstract class IMHandler {
	protected final String userid;
	protected final Message msg;
	protected ChannelHandlerContext ctx;

	protected IMHandler(String userid, Message msg, ChannelHandlerContext ctx) {
		this.userid = userid;
		this.msg = msg;
		this.ctx = ctx;
	}

	protected abstract void excute(Worker worker) throws Exception;
}
