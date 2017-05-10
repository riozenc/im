/**
 * Title:ClientConnection.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午5:43:41
**/
package org.im.gate.connection;


import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class ClientConnection {
	private static final AtomicLong netidGenerator = new AtomicLong(0);

	ClientConnection(ChannelHandlerContext c) {
		_netId = netidGenerator.incrementAndGet();
		_ctx = c;
		_ctx.attr(ClientConnection.NETID).set(_netId);
		
	}

	private static final Logger logger = LoggerFactory.getLogger(ClientConnection.class);

//	public static AttributeKey<ThreeDES> ENCRYPT = AttributeKey.valueOf("encrypt");
	public static AttributeKey<Long> NETID = AttributeKey.valueOf("netid");

	private String _userId;
	private long _netId;
	private ChannelHandlerContext _ctx;

	public long getNetId() {
		return _netId;
	}

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public void readUserIdFromDB() {

	}

	public void setCtx(ChannelHandlerContext ctx) {
		_ctx = ctx;
	}

	public ChannelHandlerContext getCtx() {
		return _ctx;
	}
}