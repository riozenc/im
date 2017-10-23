package org.im.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	public static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {

		logger.error("error");
		logger.debug("debug");
		logger.info("info");
		logger.trace("trace");
		logger.warn("warn");
		logger.error("error {}", "param");
	}
}
