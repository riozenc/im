/**
 * @Project:im-auth
 * @Title:DefaultAuthLogicServer.java
 * @Author:Riozenc
 * @Datetime:2017年7月17日 下午10:54:07
 * 
 */
package org.im.auth.server;

import org.im.auth.handler.logic.AuthLogicHandler;
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

public class DefaultAuthLogicServer {
	private static final Logger logger = LoggerFactory.getLogger(DefaultAuthLogicServer.class);

	public static void startAuthLogicConnection(String ip, int port) {
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						pipeline.addLast("MessageDecoder", new PacketDecoder());
						pipeline.addLast("MessageEncoder", new PacketEncoder());
						pipeline.addLast("AuthLogicHandler", new AuthLogicHandler());
					}
				});

		bootstrap.connect(ip, port);
	}
}
