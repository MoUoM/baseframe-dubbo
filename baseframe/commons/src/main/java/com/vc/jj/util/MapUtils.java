package com.vc.jj.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MapUtils {

	/**
	 * 解析XML
	 * @param xml_obj
	 * @return
	 * @throws ServiceException 
	 */
	public static Map<String, String> parserXml(String xml_obj, List<String> ele) {
		// TODO Auto-generated method stub
		String result = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xml_obj);
			//获取根节点元素对象  
	        Element node = document.getRootElement();  
	        //获取element中的元素节点对象  
	        for (String string : ele) {
	        	Element element = node.element(string);  
				if(element != null){
					result = element.getText();
					map.put(string, result);
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return map;
	}
	
}
