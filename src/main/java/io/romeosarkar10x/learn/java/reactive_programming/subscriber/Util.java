package io.romeosarkar10x.learn.java.reactive_programming.subscriber;


import io.romeosarkar10x.learn.java.reactive_programming.common.DefaultSubscriber;
import org.jspecify.annotations.NonNull;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {
    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>();
    }

    public static <T> Subscriber<T> subscriber(String id) {
        return new DefaultSubscriber<>(id);
    }

    public static void main() {
        // Mono<@NonNull Integer> monoPub = Mono.just(2);
        Mono<@NonNull Integer> monoPub = Mono.empty();

        monoPub.subscribe(subscriber());
        monoPub.subscribe(subscriber());
    }
}
