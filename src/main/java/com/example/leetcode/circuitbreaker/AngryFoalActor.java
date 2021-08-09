package com.example.leetcode.circuitbreaker;

import akka.actor.UntypedActor;

public class AngryFoalActor extends UntypedActor {

    public void onReceive(Object message) throws Exception {
        System.out.println("AngryFoalActor receive message : " + message);
        getSender().tell("hello! I am  AngryFoalActor!", getSelf());
    }
}
