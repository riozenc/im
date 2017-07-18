package org.im.logic;

import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;

public abstract class IMHandler {
	protected final String userid;
	protected final long netid;
	protected final Message msg;
	protected ChannelHandlerContext ctx;

	protected IMHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
		this.userid = userid;
		this.netid = netid;
		this.msg = msg;
		this.ctx = ctx;
	}

	protected abstract void excute(Worker worker);
}
