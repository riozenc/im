/**
 * Title:PacketEncoder.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午5:00:11
**/
package org.im.protocol.code;

import org.im.protocol.analysis.ParseMap;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Message> {
	private static final Logger logger = LoggerFactory.getLogger(PacketEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub

		byte[] bytes = msg.toByteArray();// 将对象转换为byte
		int ptoNum = ParseMap.msg2ptoNum.get(msg);
		int length = bytes.length;

		ByteBuf buf = Unpooled.buffer(8 + length);
		buf.writeInt(length);
		buf.writeInt(ptoNum);
		buf.writeBytes(bytes);
		out.writeBytes(buf);

		logger.info("GateServer Send Message, remoteAddress: {}, content length {}, ptoNum: {}",
				ctx.channel().remoteAddress(), length, ptoNum);
	}

}
