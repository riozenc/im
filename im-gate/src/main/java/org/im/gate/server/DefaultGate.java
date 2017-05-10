/**
 * Title:DefaultGate.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午3:29:15
**/
package org.im.gate.server;

import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import org.im.gate.TransferHandlerMap;
import org.im.protocol.ParseRegistryMap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * 默认的Gate（网关）
 * 
 * @author riozenc
 *
 */
public class DefaultGate extends AbstractGate {
	static final boolean SSL = System.getProperty("ssl") != null;

	@Override
	public void startGate(int port) throws CertificateException, SSLException {
		// TODO Auto-generated method stub
		final SslContext sslCtx;
		if (SSL) {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslCtx = null;
		}
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.handler(new LoggingHandler(LogLevel.INFO));
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline p = ch.pipeline();
				if (sslCtx != null) {
					p.addLast(sslCtx.newHandler(ch.alloc()));
				}
				p.addLast(new LoggingHandler(LogLevel.INFO));
				// p.addLast(new WisdomDecodeHandler());
				// p.addLast(new EchoServerHandler());
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
