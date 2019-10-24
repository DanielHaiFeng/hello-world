package com.xa.dt.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname JsonFormatter
 * @date 2019-05-30 11:11
 * @version: 1.0
 * @description: TODO
 */
public class JsonFormatter {

    private static final Logger logger = LoggerFactory.getLogger(JsonFormatter.class);

    public static String format(String json) {
        //缩进
        StringBuilder indent = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                    indent.append(" ");
                    sb.append("{\n").append(indent);
                    break;
                case '}':
                    indent.deleteCharAt(indent.length() - 1);
                    sb.append("\n").append(indent).append("}");
                    break;
                case '[':
                    indent.append(" ");
                    sb.append("[\n").append(indent);
                    break;
                case ']':
                    indent.deleteCharAt(indent.length() - 1);
                    sb.append("\n").append(indent).append("]");
                    break;
                case ',':
                    sb.append(",\n").append(indent);
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("name", "danielt");
        object.put("sex", "female");
        object.put("address", "xi'am");
        JSONArray array = new JSONArray();
        array.add("football");
        array.add("basketball");
        array.add("ping'pong");
        object.put("aihao", array);
        logger.info("JSON对象[{}]", object);
        System.out.println();
        System.out.println(JsonFormatter.format(object.toJSONString()));
    }
}
