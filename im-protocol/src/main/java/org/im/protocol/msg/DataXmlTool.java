/**
 * @Project:im-protocol
 * @Title:DataXmlTool.java
 * @Author:Riozenc
 * @Datetime:2017年7月7日 上午11:42:11
 * 
 */
package org.im.protocol.msg;

import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.common.util.reflect.ReflectUtil;

public class DataXmlTool {
	private static final Logger logger = LoggerFactory.getLogger(DataXmlTool.class);

	public static Message xml2Bean(byte[] bs, Message message) throws DocumentException {
		String data = new String(bs);
		// 数据变对象
		Document document = DocumentHelper.parseText(data);
		Element element = document.getRootElement();

		Class<?> clazz = message.getClass();
		String className = clazz.getSimpleName().toLowerCase();
		if (!className.equals(element.getName())) {
			logger.error("{} is not equals : {}", className, element.getName());
			return null;
		}
		Field[] fields = clazz.getDeclaredFields();// 不需要上级属性
		for (Field field : fields) {
			// 得到字段的属性名
			String name = field.getName();
			Element fieldElement = element.element(name);

			if (fieldElement.getText() != null && !"".equals(fieldElement.getText())) {
				// 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
				try {
					ReflectUtil.setFieldValue(message, name,
							ReflectUtil.typeFormat(field.getType(), fieldElement.getText()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		}
		return message;
	}
}
