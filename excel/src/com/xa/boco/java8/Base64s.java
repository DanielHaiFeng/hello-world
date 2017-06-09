package com.xa.boco.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64s {
	
	/*
	 * Base64类同时还提供了对URL、MIME友好的编码器与解码器
	 * （Base64.getUrlEncoder() / Base64.getUrlDecoder(), Base64.getMimeEncoder() / Base64.getMimeDecoder()）。
	 */

	public static void main(String[] args) {
		final String text = "Base64 finally in Java 8!";

		final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);

		final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
		System.out.println(decoded);
	}
}
