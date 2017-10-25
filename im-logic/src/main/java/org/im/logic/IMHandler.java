package org.im.logic;

import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;

public abstract class IMHandler {
	protected final String uid;
	protected final Message msg;
	protected ChannelHandlerContext ctx;

	protected IMHandler(String uid, Message msg, ChannelHandlerContext ctx) {
		this.uid = uid;
		this.msg = msg;
		this.ctx = ctx;
	}

	protected abstract void excute(Worker worker);
}
