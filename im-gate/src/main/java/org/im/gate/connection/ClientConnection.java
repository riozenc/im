/**
 * Title:ClientConnection.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午5:43:41
**/
package org.im.gate.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class ClientConnection {
	private static final Logger logger = LoggerFactory.getLogger(ClientConnection.class);

	ClientConnection(ChannelHandlerContext c, String UID) {
		this.ctx = c;
		ctx.channel().attr(ClientConnection.UID).set(UID);
	}

	public static AttributeKey<String> UID = AttributeKey.valueOf("UID");

	private ChannelHandlerContext ctx;

	public String getUID() {
		return ctx.channel().attr(ClientConnection.UID).get();
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}
}
