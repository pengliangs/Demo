package com.pl.web.socket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @author pengliang  2018-06-25 22:53
 */
@Component
public class ConnectionListener implements ApplicationListener<SessionConnectEvent> {

    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
        System.out.println("【连接】ConnectionListener"+sessionConnectEvent.getTimestamp());
    }
}
