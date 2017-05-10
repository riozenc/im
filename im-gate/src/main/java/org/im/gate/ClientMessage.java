/**
 * Title:ClientMessage.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午5:18:27
**/
package org.im.gate;

import java.io.IOException;
import java.util.HashMap;

import org.im.gate.connection.ClientConnection;
import org.im.gate.handler.GateAuthConnectionHandler;
import org.im.gate.handler.GateLogicConnectionHandler;
import org.im.protocol.msg.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;

public class ClientMessage {

	private static final Logger logger = LoggerFactory.getLogger(ClientMessage.class);

	public static HashMap<Integer, Transfer> tranferHandlerMap = new HashMap<>();
	public static HashMap<Class<?>, Integer> msg2ptoNum = new HashMap<>();

	@FunctionalInterface
	public interface Transfer {
		void process(Message msg, ClientConnection conn) throws IOException;
	}

	public static void registerTranferHandler(Integer ptoNum, Transfer tranfer, Class<?> cla) {
		if (tranferHandlerMap.get(ptoNum) == null)
			tranferHandlerMap.put(ptoNum, tranfer);
		else {
			logger.error("pto has been registered in transeerHandlerMap, ptoNum: {}", ptoNum);
			return;
		}

		if (msg2ptoNum.get(cla) == null)
			msg2ptoNum.put(cla, ptoNum);
		else {
			logger.error("pto has been registered in msg2ptoNum, ptoNum: {}", ptoNum);
			return;
		}
	}

	public static void processTransferHandler(Message msg, ClientConnection conn) throws IOException {
		logger.info("MessageName {}", msg.getClass());
		int ptoNum = msg2ptoNum.get(msg.getClass());
		Transfer transferHandler = tranferHandlerMap.get(ptoNum);

		if (transferHandler != null) {
			transferHandler.process(msg, conn);
		}
	}

	public static void transfer2Logic(Message msg, ClientConnection conn) {
		ByteBuf byteBuf = null;
		if (conn.getUserId() == null) {
			logger.error("User not login.");
			return;
		}

//		if (msg instanceof Chat.CPrivateChat) {
//			byteBuf = Utils.pack2Server(msg, ParseMap.getPtoNum(msg), conn.getNetId(), Internal.Dest.Logic,
//					conn.getUserId());
//		}

		GateLogicConnectionHandler.getGatelogicConnection().writeAndFlush(byteBuf);
	}

	public static void transfer2Auth(Message msg, ClientConnection conn) {
		ByteBuf byteBuf = null;
//		if (msg instanceof Auth.CLogin) {
//			String userId = ((Auth.CLogin) msg).getUserid();
//			byteBuf = Utils.pack2Server(msg, ParseMap.getPtoNum(msg), conn.getNetId(), Internal.Dest.Auth, userId);
//			ClientConnectionMap.registerUserid(userId, conn.getNetId());
//		} else if (msg instanceof Auth.CRegister) {
//			byteBuf = Utils.pack2Server(msg, ParseMap.getPtoNum(msg), conn.getNetId(), Internal.Dest.Auth,
//					((Auth.CRegister) msg).getUserid());
//		}

		GateAuthConnectionHandler.getGateAuthConnection().writeAndFlush(byteBuf);

	}
}
