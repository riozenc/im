/**
 * Title:DefaultLogic.java
 * Author:riozenc
 * Datetime:2017年6月16日 下午4:41:43
**/
package org.im.logic.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * 默认类
 * 
 * @author riozenc
 *
 */
public class DefaultLogic implements ILogic {
	private static Logger logger = LogManager.getLogger();

	@Override
	public void startLogic(int port) throws Exception {
		// TODO Auto-generated method stub
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
	}

}
