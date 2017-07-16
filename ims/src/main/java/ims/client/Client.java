package ims.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.im.protocol.code.PacketDecoder;
import org.im.protocol.code.PacketEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dell on 2016/2/15. Simple client for module test
 */
public class Client {
	static final String HOST = System.getProperty("host", "127.0.0.1");
	static final int PORT = Integer.parseInt(System.getProperty("port", "9090"));
	private static Channel CLIENT_CHANNEL;
	public static final int frequency = 100; // ms

	private static final Logger logger = LoggerFactory.getLogger(Client.class);

	public static void init() throws Exception {
		beginPressTest();
	}

	public static void writeAndFlush(Object object) {
		
		
		ChannelFuture channelFuture = CLIENT_CHANNEL.writeAndFlush(object);

	}

	public static void beginPressTest() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup(1);
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();
						p.addLast("MessageDecoder", new PacketDecoder());
						p.addLast("MessageEncoder", new PacketEncoder());
						p.addLast(new ClientHandler());
					}
				});

		// Start the client.
		startConnection(b);

	}

	private static ChannelFuture startConnection(Bootstrap b) {

		return b.connect(HOST, PORT).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					CLIENT_CHANNEL = future.channel();
					logger.info("Client[{}] connected Gate Successed...");
				} else {
					logger.error("Client[{}] connected Gate Failed");
				}
			}
		});
	}
}
