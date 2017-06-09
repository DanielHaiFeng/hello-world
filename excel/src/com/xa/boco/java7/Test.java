package com.xa.boco.java7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		//java7新特性
		Map<String, List<String>> anagrams = new HashMap<>();
		
		//可读性更好的字面量
		int one_million = 1_000_000;
		
		//switch支持string
		String s = "";
		switch (s) {
		case "quux":
		case "foo":
		case "bar":
			break;
		case "baz":
		default:
			break;
		}
		
		//二进制
		int binary = 0b1001_1001; 
		
	}

}
