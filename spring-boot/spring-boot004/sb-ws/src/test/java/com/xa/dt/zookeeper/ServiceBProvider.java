package com.xa.dt.zookeeper;

import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname ServiceBProvider
 * @date 2019-04-30 14:06
 * @version: 1.0
 * @description: TODO
 */
public class ServiceBProvider {

    private String serviceName="service-A";

    public void init() {
        String severList = "120.131.2.119:30011";
        String rootPath = "/servers";
        ZkClient zk = new ZkClient(severList, 5000, 5000);
        if (!zk.exists(rootPath)) {
            zk.createPersistent(rootPath);
        }
        if (!zk.exists(rootPath + "/" + serviceName)) {
            zk.createPersistent(rootPath + "/" + serviceName);
        }

        //获取本服务器IP
        String ip = "127.1.1.3";
        JSONObject object = new JSONObject();
        object.put("serviceAddr", "service-ip:456");
        zk.createPersistent(rootPath + "/" + serviceName + "/" + ip, object);
        System.out.println("znode:" + rootPath + "/" + serviceName + "/" + ip + "创建完成");
    }

    //提供服务
    public void provide(){
    }

    public static void main(String[]args) throws Exception {
        ServiceBProvider service = new ServiceBProvider();
        service.init();
        Thread.sleep(1000*60*60*24);
    }
}
