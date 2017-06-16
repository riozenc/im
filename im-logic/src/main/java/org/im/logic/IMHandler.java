package org.im.logic;

import org.im.protocol.msg.Message;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Dell on 2016/3/2.
 */
public abstract class IMHandler {
	protected final String _userid;
	protected final long _netid;
	protected final Message _msg;
	protected ChannelHandlerContext _ctx;

	protected IMHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
		_userid = userid;
		_netid = netid;
		_msg = msg;
		_ctx = ctx;
	}

	protected abstract void excute(Worker worker);
}
