package com.xa.dt.zookeeper;

import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;

/**
 * @author DangTing
 * @classname ServiceAProvider
 * @date 2019-04-30 14:03
 * @version: 1.0
 */
public class ServiceAProvider {

    private String serviceName = "service-A";

    public void init() {
        //zookeeper服务器地址
        String severList = "192.168.5.27:2181";
        String rootPath = "/servers";
        ZkClient zk = new ZkClient(severList, 5000, 5000);
        if (!zk.exists(rootPath)) {
            zk.createPersistent(rootPath);
        }
        if (!zk.exists(rootPath + "/" + serviceName)) {
            zk.createPersistent(rootPath + "/" + serviceName);
        }

        //获取本服务器IP
        String ip = "127.1.1.2";
        Stat stat = new Stat();
        JSONObject object = new JSONObject();
        object.put("serviceAddr", "service-ip:123");
        //创建永久节点并写入数据
        if (!zk.exists(rootPath + "/" + serviceName + "/" + ip)) {
            zk.createPersistent(rootPath + "/" + serviceName + "/" + ip, object);
        }
        System.out.println("znode:" + rootPath + "/" + serviceName + "/" + ip + "创建完成");
    }

    //提供服务
    public void provide() {
    }

    public static void main(String[] args) throws Exception {
        ServiceAProvider service = new ServiceAProvider();
        service.init();
        Thread.sleep(1000 * 60 * 60 * 24);
    }
}
