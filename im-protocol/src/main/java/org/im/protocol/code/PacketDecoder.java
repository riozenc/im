/**
 * Title:PacketDecoder.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午5:00:01
**/
package org.im.protocol.code;

import java.util.List;

import org.im.protocol.analysis.ParseMap;
import org.im.protocol.msg.AbstractMessage;
import org.im.protocol.msg.DataPackageTool;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.config.Global;

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
		if (length < AbstractMessage.getBaseLength()) {
			logger.info("readableBytes(" + length + ") length less than " + AbstractMessage.getBaseLength()
					+ " bytes, ignored");
			in.resetReaderIndex();
			return;
		}

		// 判断fe fe fe
		if (DataPackageTool.coverByte(in.readByte()) != 0xFE) {

			return;
		}

		if (DataPackageTool.coverByte(in.readByte()) != 0xFE) {

			return;
		}

		if (DataPackageTool.coverByte(in.readByte()) != 0xFE) {

			return;
		}

		// 判断版本号
		if (DataPackageTool.coverByte(in.readByte()) != Integer.parseInt(Global.getConfig("protocol-version"))) {
			in.readerIndex(1);
			return;
		}

		// 跳过8位（个人ID和命令）
		// in.readerIndex(3 + 1 + 8);
		in.readerIndex(in.readerIndex() + 8);

		// 数据长度
		byte[] dataLengthBytes = new byte[4];
		in.readBytes(dataLengthBytes);
		int dataLength = Integer.parseInt(DataPackageTool.coverBytes(dataLengthBytes), 16);
		if (length < dataLength + AbstractMessage.getBaseLength()) {
			logger.info("readableBytes(" + length + ") length less than [" + dataLength + "(" + dataLengthBytes[0] + " "
					+ dataLengthBytes[1] + " " + dataLengthBytes[2] + " " + dataLengthBytes[3] + ")]+["
					+ AbstractMessage.getBaseLength() + "] bytes, wating");
			in.resetReaderIndex();
			return;
		}
		in.readerIndex(in.readerIndex() + dataLength + 6 + 1 + 3);// 数据长度+日期长度+校验方式长度+校验码长度
		// 判断结束符
		if (DataPackageTool.coverByte(in.readByte()) != 0x16) {
			return;
		}
		in.resetReaderIndex();// 重置，读取完整的一条数据
		ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(AbstractMessage.getBaseLength() + dataLength);// 使用内存池分配器创建直接内存缓冲区
		in.readBytes(byteBuf);
		byte[] bs = new byte[AbstractMessage.getBaseLength() + dataLength];
		byteBuf.readBytes(bs);

		byte[] orderBytes = new byte[4];
		System.arraycopy(bs, 3 + 1 + 4, orderBytes, 0, orderBytes.length);
		int order = Integer.parseInt(DataPackageTool.coverBytes(orderBytes), 16);

		try {
			// 消息体转对象
			Message msg = ParseMap.getMessage(order, bs);

			out.add(msg);
			logger.info("GateServer Received Message: content length {}, order: {}", length, order);

		} catch (Exception e) {
			logger.error(ctx.channel().remoteAddress() + ",decode failed.", e);
		}
	}

}
