package com.plus.controller;

import com.plus.service.IDanMuService;
import com.plus.service.impl.DanMuServiceImpl;
import com.plus.thread.AliveThread;
import com.plus.thread.CrawlerThread;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Component
@Order(1)
public class DyController implements ApplicationRunner {

    @Resource
    public IDanMuService danMuService;

    public static IDanMuService sDanMuService;

    @PostConstruct
    public void init() {
        sDanMuService = this.danMuService;
    }

    @Override
    public void run(ApplicationArguments args) {
        Thread t1 = new Thread(new CrawlerThread(74960)); //60937
//        Thread t2 = new Thread(new CrawlerThread(88660));
        Thread t3 = new Thread(new AliveThread());
        t1.start();
//        t2.start();
        t3.start();



        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        //client.close();
    }
}
