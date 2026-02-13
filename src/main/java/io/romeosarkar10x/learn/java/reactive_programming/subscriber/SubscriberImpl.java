package io.romeosarkar10x.learn.java.reactive_programming.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {
    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String next) {
        log.info("Received: '{}'", next);
    }

    @Override
    public void onError(Throwable error) {
        log.error("Error: '{}'", error.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Completed!");
    }

    public Subscription getSubscription() {
        return this.subscription;
    }
}
