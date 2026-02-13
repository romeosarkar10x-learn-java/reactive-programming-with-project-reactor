package io.romeosarkar10x.learn.java.reactive_programming.publisher;

import io.romeosarkar10x.learn.java.reactive_programming.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublisherImpl implements Publisher<String> {
    private static Logger log = LoggerFactory.getLogger(PublisherImpl.class);

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        log.info("New subscriber...");

        Subscription subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
