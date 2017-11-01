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

	public static ConcurrentHashMap<Long, ClientConnection> allClientMap = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<String, ClientConnection> registerClientMap = new ConcurrentHashMap<>(1024);
	private static ConcurrentHashMap<Long, String> uid2netidMap = new ConcurrentHashMap<>();

	/**
	 * 从注册列表中获取ClientConnection
	 * 
	 * @param ctx
	 * @return
	 */
	public static ClientConnection getClientConnection(ChannelHandlerContext ctx) {
		Long netId = ctx.channel().attr(ClientConnection.NETID).get();
		ClientConnection conn = allClientMap.get(netId);
		if (conn != null)
			return conn;
		else {
			// 线程隐患
			if (uid2netidMap.get(netId) != null) {
				conn = registerClientMap.get(uid2netidMap.get(netId));
				return conn;
			}
			logger.error("ClientConenction not found in allClientMap, netId: {}", netId);
		}
		return null;
	}

	public static ClientConnection getClientConnection(long netId) {
		ClientConnection conn = allClientMap.get(netId);
		if (conn != null)
			return conn;
		else {
			logger.error("ClientConenction not found in allClientMap, netId: {}", netId);
		}
		return null;
	}

	public static ClientConnection getClientConnection(String uid) {
		ClientConnection conn = registerClientMap.get(uid);
		if (conn != null)
			return conn;
		else {
			logger.error("ClientConenction not found in registerClientMap, uid: {}", uid);
		}
		return null;
	}

	public static void addClientConnection(ChannelHandlerContext c) {
		// fixme 之后重复登录需要踢掉原来的连接
		ClientConnection conn = new ClientConnection(c);
		if (ClientConnectionMap.allClientMap.putIfAbsent(conn.getNetId(), conn) != null) {
			ClientConnection old = ClientConnectionMap.allClientMap.put(conn.getNetId(), conn);
			old.getCtx().close();
			logger.error("Duplicated netid");
		}
		logger.info("{} 号 连接增加.",c.channel().attr(ClientConnection.NETID).get());
	}

	public static void registerUid(String uid, long netId) {
		if (uid2netidMap.putIfAbsent(netId, uid) == null) {
			ClientConnection conn = ClientConnectionMap.getClientConnection(netId);
			if (conn != null) {
				conn.setUid(uid);
				allClientMap.remove(conn.getNetId());
				ClientConnection oldConn = ClientConnectionMap.registerClientMap.putIfAbsent(conn.getUid(), conn);
				if (oldConn == null) {
					// 不处理
				} else {
					removeClientConnection(oldConn.getCtx());
					registerClientMap.put(conn.getUid(), conn);
					uid2netidMap.put(conn.getNetId(), uid);
					logger.error("UID: {}  reconnection.", oldConn.getUid());
				}

			} else {
				logger.error("{} ClientConnection is null", uid);
				// 重连
				return;
			}
		} else {
			logger.error("uid: {} has registered in uid2netidMap", uid);
		}

	}

	public static void removeClientConnection(ChannelHandlerContext c) {
		ClientConnection conn = getClientConnection(c);
		if (conn == null)
			return;
		Long netid = conn.getNetId();
		allClientMap.remove(netid);
		if (ClientConnectionMap.registerClientMap.remove(conn.getUid()) != null) {
			logger.error("uid: {} is remove allClientMap", conn.getUid());
		} else {
			logger.error("uid: {} is not exist in allClientMap", conn.getUid());
		}
		uid2netidMap.remove(netid);
		conn.getCtx().close();
		logger.info("Client disconnected, netid: {},uid:{}", netid, conn.getUid());
	}

}
