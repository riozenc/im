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
	private static final Logger logger = LoggerFactory.getLogger(ClientConnection.class);
	private static final AtomicLong netidGenerator = new AtomicLong(0);

	ClientConnection(ChannelHandlerContext c) {
		netId = netidGenerator.incrementAndGet();
		ctx = c;
		ctx.channel().attr(ClientConnection.NETID).set(netId);
	}

	public static AttributeKey<Long> NETID = AttributeKey.valueOf("netid");

	private ChannelHandlerContext ctx;
	private long netId;
	private String uid;

	public long getNetId() {
		return netId;
	}

	public String getUid() {
		return uid;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
