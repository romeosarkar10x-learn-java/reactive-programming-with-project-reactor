package io.romeosarkar10x.learn.java.reactive_programming.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DefaultSubscriber<T> implements Subscriber<T> {
    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private final String id;

    public DefaultSubscriber() {
        this.id = UUID.randomUUID().toString();
    }

    public DefaultSubscriber(String id) {
        this.id = id;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("DefaultSubscriber@{} subscribed", this.id);
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("{} received {}", this.id, item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("Error '{}'", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Subscriber '{}' completed", this.id);
    }
}

