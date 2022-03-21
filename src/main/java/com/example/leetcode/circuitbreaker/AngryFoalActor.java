package com.example.leetcode.circuitbreaker;


import akka.actor.AbstractActor;

public class AngryFoalActor extends AbstractActor {

    public void onReceive(Object message) throws Exception {
        System.out.println("AngryFoalActor receive message : " + message);
        getSender().tell("hello! I am  AngryFoalActor!", getSelf());
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
