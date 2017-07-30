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
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {
	private static final Logger logger = LoggerFactory.getLogger(PacketDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub

		in.markReaderIndex();

		int length = in.readableBytes();
		int size = 0;

//		ByteBuf byteBuf = Unpooled.buffer(length);
//		ByteBuf byteBuf = Unpooled.directBuffer(length);
		ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(length);//使用内存池分配器创建直接内存缓冲区

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
		String UID = new String("" + bs[4] + bs[5] + bs[6] + bs[7]);
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

			Message msg = ParseMap.getMessage(Integer.parseInt(order, 16), body);
			msg.setOrder(Integer.parseInt(order, 16));
			msg.setUID(UID);

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
