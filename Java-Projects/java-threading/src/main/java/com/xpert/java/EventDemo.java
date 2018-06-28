package com.xpert.java;

import java.util.ArrayList;



interface IEvent<TEventArgs extends Object>{
    void invoke(Object source, TEventArgs eventArgs);
}

class EventHandler<TEventArgs>{
    private ArrayList<IEvent<TEventArgs>> eventDelegateArray = new ArrayList<>();

    /**
     * Subscribe to notifications
     * @param methodReference
     */
    public void subscribe(IEvent<TEventArgs> methodReference){
        eventDelegateArray.add(methodReference);
    }

    /**
     * Unsubscribe to notifications
     * @param methodReference
     */
    public void unsubsribe(IEvent<TEventArgs> methodReference){
        eventDelegateArray.remove(methodReference);
    }

    /**
     * notify the subscribers about the event with the src and args
     * @param source
     * @param eventArgs
     */
    public void invoke(Object source, TEventArgs eventArgs){
        if(eventDelegateArray.size() > 0 ){
            eventDelegateArray.forEach(p -> p.invoke(source, eventArgs));
        }
    }

    /**
     * Clear all the subscriptions
     */
    public void clear(){
        if(eventDelegateArray.size() > 0){
            eventDelegateArray.clear();
        }
    }
}

class DummyEventProducer{
    // the event
    public EventHandler<String> myEvent = new EventHandler<>();

    public void raiseMyEvent(String s){
        myEvent.invoke(this, s);
    }
}

class DummyEventSubscriber{

    /**
     * event handler for my event
     * @param src
     * @param eventArgs
     */
    public void onMyEvent(Object src, String eventArgs){
        System.out.println("Event fired with args " + eventArgs);
    }
}

public class EventDemo {

    public static void main(String[] args){
        // dummy producer
        DummyEventProducer producer = new DummyEventProducer();

        // dummy subscriber
        DummyEventSubscriber subscriber1 = new DummyEventSubscriber();
        DummyEventSubscriber subscriber2 = new DummyEventSubscriber();
        DummyEventSubscriber subscriber3 = new DummyEventSubscriber();

        // We create weak references since we need to unsubscribe to events later.
        IEvent<String> sink1 = subscriber1::onMyEvent;
        IEvent<String> sink2 = subscriber2::onMyEvent;
        IEvent<String> sink3 = subscriber3::onMyEvent;

        // subscribe
        producer.myEvent.subscribe(sink1);
        producer.myEvent.subscribe(sink2);
        producer.myEvent.subscribe(sink3);

        // fire the event
        producer.raiseMyEvent("Hello World");

        // unsubscribe
        producer.myEvent.unsubsribe(sink1);
        producer.myEvent.unsubsribe(sink2);
        producer.myEvent.unsubsribe(sink3);

        // fire again
        producer.raiseMyEvent("Hello Again");

        // if we dont really care about unsubscribing then we can
        // directly use strong references during subsribe
        producer.myEvent.subscribe(subscriber1::onMyEvent);

        producer.raiseMyEvent("Only One subscriber with strong reference");
        producer.myEvent.clear();

        producer.raiseMyEvent("Subscriptions are cleared");
    }
}