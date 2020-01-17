package com.xa.dt.test;

import java.math.BigInteger;

public class TestDzsxc {

    public static void main(String[] args) {
        String s1 = "99999999999999999999";
        String s2 = "99999999999999999999";
        String re = multiply(s1, s2);
        System.out.println(re);
        BigInteger b1 = new BigInteger(s1);
        BigInteger b2 = new BigInteger(s2);
        BigInteger b3 = b1.multiply(b2);
        System.out.println(b3.toString());
    }

    public static String multiply(String s1, String s2) {
        if ("0".equals(s1) || "0".equals(s2)) {
            return "0";
        }
        StringBuilder sb1 = new StringBuilder(s1);
        StringBuilder sb2 = new StringBuilder(s2);
        sb1.reverse();
        sb2.reverse();
        int[] a = new int[sb1.length() + sb2.length()];
        for (int i = 0; i < sb1.length(); i++) {
            for (int j = 0; j < sb2.length(); j++) {
                a[i + j] += (sb1.charAt(i) - '0') * (sb2.charAt(j) - '0');
            }
        }
        for (int i = 0; i < a.length - 1; i++) {
            a[i + 1] += a[i] / 10;
            a[i] %= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length - 1; i++) {
            sb.append(a[i]);
        }
        if (a[a.length - 1] != 0) {
            sb.append(a[a.length - 1]);
        }
        return sb.reverse().toString();
    }
}
