package io.romeosarkar10x.learn.java.reactive_programming;

import io.romeosarkar10x.learn.java.reactive_programming.publisher.PublisherImpl;
import io.romeosarkar10x.learn.java.reactive_programming.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;

import java.time.Duration;

public class Main {
    static void main() throws InterruptedException {
        Publisher<String> pub = new PublisherImpl();
        SubscriberImpl sub = new SubscriberImpl();

        pub.subscribe(sub);

        sub.getSubscription().request(4);
        Thread.sleep(Duration.ofSeconds(2));

        sub.getSubscription().request(5);
        Thread.sleep(Duration.ofSeconds(2));

        sub.getSubscription().request(7);
        Thread.sleep(Duration.ofSeconds(2));

        sub.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}
