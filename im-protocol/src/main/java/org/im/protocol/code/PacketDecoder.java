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

		int length = in.readableBytes();

//		if (in.readableBytes() < 6) {
//			logger.info("readableBytes length less than 6 bytes, ignored");
//			in.resetReaderIndex();
//			return;
//		}

		ByteBuf byteBuf = Unpooled.buffer(length);

		in.readBytes(byteBuf);

		byte[] bs = byteBuf.array();// 获取数据
		
		System.out.println(new String(bs));

		if (in.readableBytes() < 4) {
			logger.info("readableBytes length less than 4 bytes, ignored");
			in.resetReaderIndex();
			return;
		}

		// 判断fe fe fe
		if (bs[0] != 0xFE || bs[1] != 0xFE || bs[2] != 0xFE) {
			logger.info("head is not FE FE FE, ignored");
			in.resetReaderIndex();
			return;
		}

		// 判断 开始标识
		if (bs[3] != 0x16) {
			logger.info("data flag is error, ignored");
			in.resetReaderIndex();
			return;
		}

		// 长度判断

		int ptoNum = in.readInt();

		try {

			// 解密消息体
			// ThreeDES des = ctx.channel().attr(ClientAttr.ENCRYPT).get();
			// byte[] bareByte = des.decrypt(inByte);

			byte[] body = byteBuf.array();

			Message msg = ParseMap.getMessage(ptoNum, body);
			out.add(msg);
			logger.info("GateServer Received Message: content length {}, ptoNum: {}", length, ptoNum);

		} catch (Exception e) {
			logger.error(ctx.channel().remoteAddress() + ",decode failed.", e);
		}
	}

	public static void main(String[] args) {
		System.out.println(0x16);
		System.out.println(0x10);
		System.out.println(0xFE);
		System.out.println(0x0221);
		int i = 0x0221;

		String s = Integer.toHexString(i);

		System.out.println(s);

		System.out.println(s.getBytes());
		byte v = Integer.valueOf(s, 16).byteValue();
		System.out.println(v);
	}

}
