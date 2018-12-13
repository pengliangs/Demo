package com.pl.web.socket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * author pengliang  2018-06-25 23:01
 */
@Component
public class SubscribeListener implements ApplicationListener<SessionSubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        System.out.println("【订阅】SubscribeListener");
    }
}
