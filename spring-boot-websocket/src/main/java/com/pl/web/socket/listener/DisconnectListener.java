package com.pl.web.socket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * author pengliang  2018-06-25 23:04
 */
@Component
public class DisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        System.out.println("【断开】DisconnectListener");
    }
}
