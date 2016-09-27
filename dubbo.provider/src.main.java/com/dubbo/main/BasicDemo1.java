package com.dubbo.main;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class BasicDemo1 {

    public static void main(String[] args) throws Exception{
    	Watcher watcher = new Watcher(){
            // ������б��������¼�
            public void process(WatchedEvent event) { 
                System.out.println("������" + event.getType() + "�¼���"); 
            }
        };
    	
    	ZooKeeper zk = new ZooKeeper("192.168.2.11:2181", 3000, watcher);
        System.out.println("=========�����ڵ�===========");
        if(zk.exists("/test", false) == null)
        {
        	zk.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("=============�鿴�ڵ��Ƿ�װ�ɹ�===============");
        System.out.println(new String(zk.getData("/test", false, null)));
        
        System.out.println("=========�޸Ľڵ������==========");
        zk.setData("/test", "zNode2".getBytes(), -1);
        System.out.println("========�鿴�޸ĵĽڵ��Ƿ�ɹ�=========");
        System.out.println(new String(zk.getData("/test", false, null)));
        
        System.out.println("=======ɾ���ڵ�==========");
        zk.delete("/test", -1);
        System.out.println("==========�鿴�ڵ��Ƿ�ɾ��============");
        System.out.println("�ڵ�״̬��" + zk.exists("/test", false));
        zk.close();
    }
}
