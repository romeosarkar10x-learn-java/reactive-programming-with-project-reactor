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

    public enum EnumSubscriptionStatus {
        OKAY,
        CANCELLED,
        COMPLETED,
        ERROR
    };

    private final Faker faker = Faker.instance();
    private final Subscriber<? super String> subscriber;
    private int count = 0;

    private EnumSubscriptionStatus subscriptionStatus = EnumSubscriptionStatus.OKAY;

    SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        switch(subscriptionStatus) {
            case EnumSubscriptionStatus.CANCELLED:
                log.info("Subscriber has requested {} items, but 'SubscriptionStatus' is 'CANCELLED'", n);
                return;
            case EnumSubscriptionStatus.COMPLETED:
                log.info("Subscriber has requested {} items, but 'SubscriptionStatus' is 'COMPLETED'", n);
                return;
            case EnumSubscriptionStatus.ERROR:
                log.info("Subscriber has requested {} items, but 'SubscriptionStatus' is 'ERROR'", n);
                return;
        }

        log.info("Subscriber has requested {} items...", n);

        for (int i = 0; i < n && count < MAX_ITEMS; i++, this.count++) {
            try {
                Thread.sleep(Duration.ofMillis(400));
            } catch(InterruptedException e) {
                // Do nothing...
            }

            this.subscriber.onNext(faker.animal().name().toUpperCase());
        }

        if (count == MAX_ITEMS) {
            this.subscriptionStatus = EnumSubscriptionStatus.COMPLETED;
            this.subscriber.onComplete();
        }
    }

    @Override
    public void cancel() {
        log.info("Subscriber has cancelled the subscription");
        this.subscriptionStatus = EnumSubscriptionStatus.COMPLETED;
    }
}
