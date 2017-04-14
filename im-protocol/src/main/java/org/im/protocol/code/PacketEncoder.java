/**
 * Title:PacketEncoder.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午5:00:11
**/
package org.im.protocol.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
