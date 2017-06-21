/**
 * Title:BaseAction.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:08:35
**/
package ims.webapp;

import org.springframework.web.bind.annotation.RequestMapping;

public abstract class BaseAction extends com.riozenc.quicktool.springmvc.webapp.action.BaseAction {
	@RequestMapping(params = "type=index")
	public String index() {
		return getIndex();
	}

	public abstract String getIndex();

}
