/**
 * Title:AbstractGate.java
 * Author:riozenc
 * Datetime:2017年4月14日 下午3:33:18
**/
package org.im.gate.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractGate implements IGate {
	protected static Logger logger = LogManager.getLogger();
}
