package com.xa.boco.util;

import java.util.UUID;

public final class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static void main(String[] args) {
    	System.out.println(UUIDUtil.getUUID());
	}
}