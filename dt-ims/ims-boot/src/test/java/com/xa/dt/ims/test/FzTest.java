package com.xa.dt.ims.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FzTest {

    @Test
    public void testFz() {
        String num1 = "99999999999999999999";
        String num2 = "99999999999999999999";
        List<String> num1List = split(num1);
        List<String> num2List = split(num2);
        if (num1List.size() == 0 || num2List.size() == 0) {
            log.info("计算结果[{}]", 0);
            return;
        }
        List<String> tmpList = calcUnit(num1List, num2List);
        calc(tmpList);
        String result = tmpList.get(0);
        log.info("计算结果[{}]", result);
        BigInteger b1 = new BigInteger(num1);
        BigInteger b2 = new BigInteger(num2);
        log.info("验证结果[{}]", b1.multiply(b2));
    }

    private String addStr(String num1, String num2) {
        int[] a1 = new int[num1.length()];
        for (int i = 0; i < num1.length(); i++) {
            a1[i] = Integer.valueOf(String.valueOf(num1.charAt(i)));
        }
        int[] a2 = new int[num2.length()];
        for (int i = 0; i < num2.length(); i++) {
            a2[i] = Integer.valueOf(String.valueOf(num2.charAt(i)));
        }
        //各位相加
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a1[i] + a2[i];
        }
        //进位
        for (int i = a1.length - 1; i > 0; i--) {
            a1[i - 1] += a1[i] / 10;
            a1[i] = a1[i] % 10;
        }
        StringBuilder re = new StringBuilder();
        boolean hasInt = false;
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] == 0) {
                if (hasInt) {
                    re.append(a1[i]);
                }
            } else {
                re.append(a1[i]);
                hasInt = true;
            }
        }
        return re.toString();
    }

    private List<String> split(String num) {
        List<String> list = new ArrayList<String>();
        int size = num.length();
        for (int i = size - 1; i >= 0; i--) {
            char n = num.charAt(i);
            if ('0' == n) {
                continue;
            }
            list.add(n + "-" + (size - 1 - i));
        }
        return list;
    }

    private List<String> calcUnit(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < list1.size(); i++) {
            String numTmp1 = list1.get(i);
            String cs1 = numTmp1.split("[-]")[0];
            String zs1 = numTmp1.split("[-]")[1];
            for (int j = 0; j < list2.size(); j++) {
                String numTmp2 = list2.get(j);
                String cs2 = numTmp2.split("[-]")[0];
                String zs2 = numTmp2.split("[-]")[1];
                int ctmp = Integer.valueOf(cs1) * Integer.valueOf(cs2);
                int ztmp = Integer.valueOf(zs1) + Integer.valueOf(zs2);
                StringBuilder sb = new StringBuilder(String.valueOf(ctmp));
                for (int k = 0; k < ztmp; k++) {
                    sb.append("0");
                }
                result.add(sb.toString());
            }
        }
        return result;
    }

    private void calc(List<String> list) {
        if (list.size() < 2) {
            return;
        } else {
            String num1 = list.get(list.size() - 1);
            String num2 = list.get(list.size() - 2);
            int ws = 0;
            if (num1.length() >= num2.length()) {
                ws = num1.length();
            } else {
                ws = num2.length();
            }
            num1 = buQi(num1, ws + 1);
            num2 = buQi(num2, ws + 1);
            String strTmp = addStr(num1, num2);
            list.set(list.size() - 2, strTmp);
            list.remove(list.size() - 1);
            calc(list);
        }
    }

    private String buQi(String str, int ws) {
        if (str.length() < ws) {
            int bw = ws - str.length();
            for (int i = 0; i < bw; i++) {
                str = "0" + str;
            }
        }
        return str;
    }
}