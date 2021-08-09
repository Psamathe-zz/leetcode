package com.example.leetcode.circuitbreaker;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.Future;

import static akka.pattern.Patterns.ask;

public class MyTest {



    /**
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("worker");
        Props props = Props.create(DangerousJavaActor.class );
        ActorRef actor = system.actorOf(props);
        Future sFuture = ask(actor, "ping" , 1000);
        System.out.println("First : " + sFuture);

    }
     */

    public static void main(String[] args) {
        ActorSystemTools.start();
        ActorRef angryFoal = ActorSystemTools.actorOf(AngryFoalActor.class);
        ActorRef lazyFoal = ActorSystemTools.actorOf(LazyFoalActor.class);
        ActorRef circuitbreakerFoal = ActorSystemTools.actorOf(DangerousJavaActor.class);
        angryFoal.tell("is my middle name", lazyFoal);
    }
}
