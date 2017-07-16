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

		// -2 -2 -2 99 0 0 0 0 0 0 3 84 0 0 0 81 60 82 101 103 105 115 116 101
		// 114 66 101 97 110 62 60 117 115 101 114 73 100 62 99 104 105 122 105
		// 121 117 101 60 47 117 115 101 114 73 100 62 60 112 97 115 115 119 111
		// 114 100 62 49 50 51 49 50 51 60 47 112 97 115 115 119 111 114 100 62
		// 60 47 82 101 103 105 115 116 101 114 66 101 97 110 62 17 7 6 23 10 48
		// 0 0 0 0 -2

		in.markReaderIndex();

		int length = in.readableBytes();
		int size = 0;

		ByteBuf byteBuf = Unpooled.buffer(length);

		if (in.readableBytes() < 3) {
			logger.info("readableBytes length less than 3 bytes, ignored");
			in.resetReaderIndex();
			return;
		}

		in.readBytes(byteBuf);

		byte[] bs = byteBuf.array();// 获取数据

		// 判断fe fe fe
		if (coverByte(bs[0]) != 0xFE || coverByte(bs[1]) != 0xFE || coverByte(bs[2]) != 0xFE) {
			logger.info("head is not FE FE FE, ignored");
			in.resetReaderIndex();
			return;
		}
		size += 3;

		// 判断 版本号 //兼容性判断,功能待定
		if (bs[3] != 99) {
			logger.info("data version is error, ignored");
			in.resetReaderIndex();
			return;
		}
		size += 1;

		// 获取uuid
		String uuid = new String("" + bs[4] + bs[5] + bs[6] + bs[7]);
		size += 4;
		String order = new String("" + bs[8] + bs[9] + bs[10] + bs[11]);
		size += 4;
		String dataLength = new String("" + bs[12] + bs[13] + bs[14] + bs[15]);
		size += 4;
		byte[] body = new byte[Integer.parseInt(dataLength)];
		System.arraycopy(bs, size, body, 0, body.length);
		size += body.length;

		int year = bs[size];
		size += 1;
		int month = bs[size];
		size += 1;
		int day = bs[size];
		size += 1;
		int hour = bs[size];
		size += 1;
		int minute = bs[size];
		size += 1;
		int second = bs[size];
		size += 1;

		String date = 2000 + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

		int encryption = bs[size];
		size += 1;

		String crc = new String("" + bs[size] + bs[size + 1] + bs[size + 2]);
		size += 3;

		if (coverByte(bs[length - 1]) != 0xFE) {
			logger.info("end is not FE, ignored");
			in.resetReaderIndex();
			return;
		}

		try {

			// 消息体转对象

			// 解密消息体
			// ThreeDES des = ctx.channel().attr(ClientAttr.ENCRYPT).get();
			// byte[] bareByte = des.decrypt(inByte);

			// byte[] body = byteBuf.array();
			Message msg = ParseMap.getMessage(Integer.parseInt(order, 16), body);
			out.add(msg);
			logger.info("GateServer Received Message: content length {}, order: {}", length, order);

		} catch (Exception e) {
			logger.error(ctx.channel().remoteAddress() + ",decode failed.", e);
		}
	}

	private int coverByte(byte b) {
		return b & 0xFF;
	}

}
