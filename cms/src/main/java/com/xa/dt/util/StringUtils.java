package com.xa.dt.util;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public String convertToStr(Object obj) {
		if (null == obj) {
			return "";
		} else {
			return obj.toString();
		}
	}
}
