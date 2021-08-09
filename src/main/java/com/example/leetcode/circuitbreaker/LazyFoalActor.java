package com.example.leetcode.circuitbreaker;

import akka.actor.UntypedActor;

public class LazyFoalActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("LazyFoalActor receive message : " + message);
    }
}
