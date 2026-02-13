package io.romeosarkar10x.learn.java.reactive_programming.mono_just;

import com.github.javafaker.Faker;
import io.romeosarkar10x.learn.java.reactive_programming.subscriber.SubscriberImpl;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class MonoJustExample {
    public static final Logger log = LoggerFactory.getLogger(MonoJustExample.class);
    public static final Faker faker = new Faker();

    public static void main() {
        // Mono<@NonNull String> monoPub = Mono.just(faker.animal().name().trim().toUpperCase());
        // Mono<@NonNull String> monoPub = Mono.error(new Exception("Test error message"));
        Mono<@NonNull Integer> monoPub = Mono.just(1);// .map(i -> i / 0);
        // SubscriberImpl sub = new SubscriberImpl();
        // monoPub.subscribe(sub);

        // sub.getSubscription().request(5);

        Disposable disposable = monoPub.subscribe(
                (i -> log.info("Received -> '{}'", i)),
                (err -> {
                    log.error("Error -> '{}'", err.getMessage());
                }),
                (() -> {
                    log.info("Completed");
                }),
                ((subscription) -> {
                    subscription.request(2);
                    // subscription.cancel();
                })
        );


    }
}
