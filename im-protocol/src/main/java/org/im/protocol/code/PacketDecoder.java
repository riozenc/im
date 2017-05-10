/**
 * Title:PacketDecoder.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午5:00:01
**/
package org.im.protocol.code;

import java.util.List;

import org.im.protocol.analysis.ParseMap;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {
	private static final Logger logger = LoggerFactory.getLogger(PacketDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub

		in.markReaderIndex();

		if (in.readableBytes() < 4) {
			logger.info("readableBytes length less than 4 bytes, ignored");
			in.resetReaderIndex();
			return;
		}

		int length = in.readInt();

		int ptoNum = in.readInt();

		ByteBuf byteBuf = Unpooled.buffer(length);

		in.readBytes(byteBuf);

		try {
			/*
			 * 解密消息体 ThreeDES des =
			 * ctx.channel().attr(ClientAttr.ENCRYPT).get(); byte[] bareByte =
			 * des.decrypt(inByte);
			 */

			byte[] body = byteBuf.array();

			Message msg = ParseMap.getMessage(ptoNum, body);
			out.add(msg);
			logger.info("GateServer Received Message: content length {}, ptoNum: {}", length, ptoNum);

		} catch (Exception e) {
			logger.error(ctx.channel().remoteAddress() + ",decode failed.", e);
		}
	}

}
