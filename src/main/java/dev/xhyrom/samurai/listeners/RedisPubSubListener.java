package dev.xhyrom.samurai.listeners;

import redis.clients.jedis.JedisPubSub;

public class RedisPubSubListener extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Message received. Channel: " + channel + ", Msg: " + message);
    }
}
