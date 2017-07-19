/**
 * Title:ClientConnectionMap.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午6:05:47
**/
package org.im.gate.connection;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class ClientConnectionMap {
	private static final Logger logger = LoggerFactory.getLogger(ClientConnectionMap.class);

	public static ConcurrentHashMap<String, ClientConnection> registerClientMap = new ConcurrentHashMap<>(1024);

	/**
	 * 从注册列表中获取ClientConnection，不存在则新建
	 * 
	 * @param ctx
	 * @return
	 */
	public static ClientConnection getClientConnection(ChannelHandlerContext ctx) {
		String UID = ctx.channel().attr(ClientConnection.UID).get();
		if (UID == null)
			return new ClientConnection(ctx, UID);
		ClientConnection conn = registerClientMap.get(UID);
		if (conn != null)
			return conn;
		else {
			logger.error("ClientConenction not found in allClientMap, UID: {}", UID);
		}
		return null;
	}

	public static ClientConnection getClientConnection(String UID) {
		ClientConnection conn = registerClientMap.get(UID);
		if (conn != null)
			return conn;
		else {
			logger.error("ClientConenction not found in allClientMap, UID: {}", UID);
		}
		return null;
	}

	public static void addClientConnection(ClientConnection c, String UID) {

		ClientConnection conn = new ClientConnection(c.getCtx(), UID);

		ClientConnection oldConn = ClientConnectionMap.registerClientMap.putIfAbsent(conn.getUID(), conn);
		if (oldConn != null) {
			// 踢掉原来的连接
			removeClientConnection(oldConn.getCtx());
			logger.error("UID: {} remove old connection.", oldConn.getUID());
			registerClientMap.put(UID, conn);
		}
	}

	public static void removeClientConnection(ChannelHandlerContext c) {
		ClientConnection conn = getClientConnection(c);
		String UID = conn.getUID();

		if (ClientConnectionMap.registerClientMap.remove(UID) != null) {
			logger.error("UID: {} is remove allClientMap", UID);
		} else {
			logger.error("UID: {} is not exist in allClientMap", UID);
		}
		conn.getCtx().close();
		logger.info("Client disconnected, UID: {}", UID);
	}

}
