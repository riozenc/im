/**
 * Title:DefaultGate.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午3:29:15
**/
package org.im.gate.server;

import java.net.InetSocketAddress;

import org.im.gate.TransferHandlerMap;
import org.im.gate.handler.GateServerHandler;
import org.im.protocol.ParseRegistryMap;
import org.im.protocol.code.PacketDecoder;
import org.im.protocol.code.PacketEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 默认的Gate（网关）
 * 
 * @author riozenc
 *
 */
public class DefaultGate extends AbstractGate {
	static final boolean SSL = System.getProperty("ssl") != null;

	@Override
	public void startGate(int port) {
		// TODO Auto-generated method stub

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						pipeline.addLast("MessageEncoder", new PacketEncoder());
						pipeline.addLast("MessageDecoder", new PacketDecoder());
						pipeline.addLast("ClientMessageHandler", new GateServerHandler());
					}
				});

		bootstrap.bind(new InetSocketAddress(port)).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					// init Registry
					ParseRegistryMap.initRegistry();
					TransferHandlerMap.initRegistry();
					logger.info("[GateServer] Started Successed, registry is complete, waiting for client connect...");
				} else {
					logger.error("[GateServer] Started Failed, registry is incomplete");
				}
			}
		});

	}

}
