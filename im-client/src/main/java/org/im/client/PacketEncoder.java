package org.im.client;

import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Administrator on 2016/1/29.
 */
public class PacketEncoder extends MessageToByteEncoder<Message> {
	private static final Logger logger = LoggerFactory.getLogger(PacketEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub

		byte[] bytes = msg.message2Byte();// 将对象转换为byte

		// int ptoNum = ParseMap.msg2ptoNum.get(msg);
		int length = bytes.length;

		out.writeBytes(bytes);

		logger.info("GateServer Send Message, remoteAddress: {}, content length {}", ctx.channel().remoteAddress(),
				length);
	}

}
