package com.xa.dt.test;

import java.util.Scanner;

public class TestNkqs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num < 1) {
            num = scanner.nextInt();
        }
        System.out.println(calc(num));
    }

    /**
     * 验证尼科彻斯定理，即：任何一个整数m的立方都可以写成m个连续奇数之和
     * @param er
     * @return
     */
    public static String calc(int er) {
        int total = er * er * er;
        int itmp = er;
        if (itmp % 2 == 0) {
            itmp += 1;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.delete(0, sb.length());
            int tmpTotal = 0;
            for (int i = 0; i < er; i++) {
                int tmpZs = itmp + 2 * i;
                tmpTotal += tmpZs;
                if (i == er - 1) {
                    sb.append(tmpZs);
                } else {
                    sb.append(tmpZs + "+");
                }
            }
            if (total == tmpTotal) {
                return sb.toString();
            }
            itmp += 2;
        }
    }
}
