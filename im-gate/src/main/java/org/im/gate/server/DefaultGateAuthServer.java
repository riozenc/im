/**
 * Title:DefaultGateAuthServer.java
 * Author:riozenc
 * Datetime:2017年5月24日 下午2:53:17
**/
package org.im.gate.server;

import org.im.gate.handler.GateAuthHandler;
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

public class DefaultGateAuthServer {
	private static final Logger logger = LoggerFactory.getLogger(DefaultGateAuthServer.class);

	public static void startGateAuthConnection(String ip, int port) {
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();

						pipeline.addLast("MessageDecoder", new PacketDecoder());
						pipeline.addLast("MessageEncoder", new PacketEncoder());
						pipeline.addLast("GateAuthConnectionHandler", new GateAuthHandler()); // Auth
																										// ->
																										// gate
					}
				});

		bootstrap.connect(ip, port);
	}
}
