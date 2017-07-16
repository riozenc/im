/**
 * Title:TransferHandlerMap.java
 * Author:riozenc
 * Datetime:2017年5月10日 下午5:17:52
**/
package org.im.gate;

import java.io.IOException;

import org.im.protocol.bean.RegisterBean;

public class TransferHandlerMap {
	public static void initRegistry() throws IOException {
		ClientMessage.registerTranferHandler(900, ClientMessage::transfer2Register, RegisterBean.class);
//		ClientMessage.registerTranferHandler(1001, ClientMessage::transfer2Auth, Auth.CRegister.class);
//		ClientMessage.registerTranferHandler(1003, ClientMessage::transfer2Logic, Chat.CPrivateChat.class);
	}
}
