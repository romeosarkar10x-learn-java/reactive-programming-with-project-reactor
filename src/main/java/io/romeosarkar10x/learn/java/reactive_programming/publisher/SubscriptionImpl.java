package io.romeosarkar10x.learn.java.reactive_programming.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SubscriptionImpl implements Subscription {
    private static final int MAX_ITEMS = 13;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);

    private final Faker faker = Faker.instance();
    private final Subscriber<? super String> subscriber;
    private int count = 0;
    private boolean cancelled;
    private boolean complete;

    SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        if (cancelled) {
            log.info("Subscriber has requested {} items, but subscription has been cancelled", n);
            return;
        }

        if (complete) {
            log.info("Subscriber has requested {} items, but publisher status is completed", n);
            return;
        }

        log.info("Subscriber has requested {} items...", n);

        for (int i = 0; i < n && count < MAX_ITEMS; i++, this.count++) {
            try {
                Thread.sleep(Duration.ofMillis(400));
            } catch(InterruptedException e) {
                // Do nothing...
            }

            this.subscriber.onNext(faker.animal().name());
        }

        if (count == MAX_ITEMS) {
            this.complete = true;
            this.subscriber.onComplete();
        }
    }

    @Override
    public void cancel() {
        log.info("Subscriber has cancelled the subscription");
        this.cancelled = true;
    }
}
