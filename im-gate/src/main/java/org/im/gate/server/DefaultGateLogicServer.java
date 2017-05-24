/**
 * Title:DefaultGateLogicServer.java
 * Author:riozenc
 * Datetime:2017年5月24日 下午2:56:15
**/
package org.im.gate.server;

import org.im.gate.handler.GateLogicConnectionHandler;
import org.im.protocol.code.PacketDecoder;
import org.im.protocol.code.PacketEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DefaultGateLogicServer {

	private static final Logger logger = LoggerFactory.getLogger(DefaultGateLogicServer.class);

	public static void startGateLogicConnection(String ip, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();

						pipeline.addLast("MessageDecoder", new PacketDecoder());
						pipeline.addLast("MessageEncoder", new PacketEncoder());

						pipeline.addLast("GateLogicConnectionHandler", new GateLogicConnectionHandler()); // logic
																											// ->
																											// gate
					}
				});

		bootstrap.connect(ip, port);

	}

}
