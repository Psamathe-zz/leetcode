package com.example.leetcode.circuitbreaker;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorSystemTools {

    private static ActorSystem actorSystem = null;

    public static void start() {
        System.out.println("start actorSystem...");
        actorSystem = ActorSystem.create();
    }

    @SuppressWarnings("unchecked")
    public static ActorRef actorOf(Class clazz) {
        return actorSystem.actorOf(Props.create(clazz));
    }

}