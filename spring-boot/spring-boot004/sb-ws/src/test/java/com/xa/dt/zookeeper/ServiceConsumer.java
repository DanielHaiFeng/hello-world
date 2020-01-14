package com.xa.dt.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author DangTing
 * @classname ServiceConsumer
 * @date 2019-04-30 14:07
 * @version: 1.0
 * @description: TODO
 */
public class ServiceConsumer {
    private List<String> serverList = new ArrayList<String>();

    private String serviceName = "service-A";

    private String servicePath = "/servers/" + serviceName;

    private ZkClient zk;

    public void init() {
        String zkServerList = "192.168.5.27:2181";
        zk = new ZkClient(zkServerList);
        if (zk.exists(servicePath)) {
            serverList = zk.getChildren(servicePath);
            System.out.println("当前服务的地址" + serverList);
        } else {
            System.out.println("service is not exist!");
        }
        zk.subscribeChildChanges(servicePath, new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                serverList = currentChilds;
            }
        });
    }

    //消费服务
    public void consume() {
        //通过负责均衡算法，得到一台服务器进行调用
        int index = getRandomNum(0, 1);
        System.out.println("调用" + serverList.get(index) + "提供的服务：" + serviceName);
        Object o = zk.readData(servicePath + "/" + serverList.get(index));
        System.out.println(o.toString());
    }

    public int getRandomNum(int min, int max) {
        Random rdm = new Random();
        return rdm.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) throws Exception {
        ServiceConsumer consumer = new ServiceConsumer();
        consumer.init();
        consumer.consume();
    }
}
