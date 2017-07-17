/**
 * Title:DefaultLogic.java
 * Author:riozenc
 * Datetime:2017年6月16日 下午4:41:43
**/
package org.im.logic.server;

import java.net.InetSocketAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.im.logic.HandlerManager;
import org.im.logic.handler.LogicServerHandler;
import org.im.protocol.ParseRegistryMap;
import org.im.protocol.code.PacketDecoder;
import org.im.protocol.code.PacketEncoder;

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

/**
 * 默认类
 * 
 * @author riozenc
 *
 */
public class DefaultLogic implements ILogic {
	private static Logger logger = LogManager.getLogger();

	@Override
	public void startLogic(int port) {
		// TODO Auto-generated method stub
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						pipeline.addLast("MessageDecoder", new PacketDecoder());
						pipeline.addLast("MessageEncoder", new PacketEncoder());
						pipeline.addLast("AuthServerHandler", new LogicServerHandler());
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
