/**
 * Title:ClientMessage.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午5:18:27
**/
package org.im.gate;

import java.io.IOException;
import java.util.HashMap;

import org.im.gate.connection.ClientConnection;
import org.im.gate.connection.ClientConnectionMap;
import org.im.gate.handler.GateAuthHandler;
import org.im.gate.handler.GateLogicHandler;
import org.im.protocol.bean.RegisterBean;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;

public class ClientMessage {

	private static final Logger logger = LoggerFactory.getLogger(ClientMessage.class);

	public static HashMap<Integer, Transfer> tranferHandlerMap = new HashMap<>();
	public static HashMap<Class<?>, Integer> msg2Order = new HashMap<>();

	@FunctionalInterface
	public interface Transfer {
		void process(Message msg, ClientConnection conn) throws IOException;
	}

	public static void registerTranferHandler(Integer order, Transfer tranfer, Class<?> cla) {
		if (tranferHandlerMap.get(order) == null)
			tranferHandlerMap.put(order, tranfer);
		else {
			logger.error("pto has been registered in transeerHandlerMap, order: {}", order);
			return;
		}

		if (msg2Order.get(cla) == null)
			msg2Order.put(cla, order);
		else {
			logger.error("pto has been registered in msg2Order, order: {}", order);
			return;
		}
	}

	// new
	public static void processTransferHandler(Message msg, ClientConnection conn) throws IOException {
		logger.info("MessageName {}", msg.getClass());
		int order = msg2Order.get(msg.getClass());
		Transfer transferHandler = tranferHandlerMap.get(order);

		if (transferHandler != null) {
			transferHandler.process(msg, conn);
		}
	}

	/**
	 * 转发logic
	 * 
	 * @param msg
	 * @param conn
	 */
	public static void transfer2Logic(Message msg, ClientConnection conn) {
		ByteBuf byteBuf = null;
		if (conn.getUID() == null) {
			logger.error("User not login.");
			return;
		}

		GateLogicHandler.getGatelogicChannelHandlerContext().writeAndFlush(byteBuf);
	}

	/**
	 * 转发auth
	 * 
	 * @param msg
	 * @param connection
	 */
	public static void transfer2Auth(Message msg, ClientConnection connection) {
		RegisterBean bean = (RegisterBean) msg;
		// 保存客户端连接
		ClientConnectionMap.addClientConnection(connection, bean.getUID());
		GateAuthHandler.getGateAuthChannelHandlerContext().writeAndFlush(bean.message2Byte());
	}
}
