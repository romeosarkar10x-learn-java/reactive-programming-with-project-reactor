package io.romeosarkar10x.learn.java.reactive_programming.mono;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MonoFromSupplier {
    public static final Logger log = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main() {
        // List<Integer> list = List.of(1, 2, 3, 4, null);
        List<Integer> list = new ArrayList<>(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(null);

        Mono<@NonNull Integer> pub = Mono.fromSupplier(() -> sum(list));
        pub.subscribe(val -> log.info("{}", val));
    }

    public static int sum(List<Integer> list) {
        log.info("Calculating sum of list: [ {} ]", list.stream().map(val -> {
            if(Objects.nonNull(val)) {
                return val.toString();
            }

            return "null";
        }).collect(Collectors.joining(", ")));

        int sum = 0;

        for (Integer val : list) {
            if (Objects.nonNull(val)) {
                sum += val;
            }
        }

        return sum;
    }
}
