/**
 * @Project:im-auth
 * @Title:AuthServer.java
 * @Author:Riozenc
 * @Datetime:2017年7月14日 下午12:28:47
 * 
 */
package org.im.auth.server;

import java.net.InetSocketAddress;

import org.im.auth.HandlerManager;
import org.im.auth.handler.AuthServerHandler;
import org.im.protocol.ParseRegistryMap;
import org.im.protocol.code.PacketDecoder;
import org.im.protocol.code.PacketEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class AuthServer {
	private static final Logger logger = LoggerFactory.getLogger(AuthServer.class);

	public static void startAuthServer(int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						pipeline.addLast("MessageDecoder", new PacketDecoder());
						pipeline.addLast("MessageEncoder", new PacketEncoder());
						pipeline.addLast("AuthServerHandler", new AuthServerHandler());
					}
				});

		bindConnectionOptions(bootstrap);

		bootstrap.bind(new InetSocketAddress(port)).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					// init registry
					ParseRegistryMap.initRegistry();
					HandlerManager.initHandlers();
					logger.info("[AuthServer] Started Successed, waiting for other server connect...");
				} else {
					logger.error("[AuthServer] Started Failed");
				}
			}
		});
	}

	protected static void bindConnectionOptions(ServerBootstrap bootstrap) {

		bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		bootstrap.childOption(ChannelOption.SO_LINGER, 0);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

		bootstrap.childOption(ChannelOption.SO_REUSEADDR, true); // 调试用
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); // 心跳机制暂时使用TCP选项，之后再自己实现

	}
}
