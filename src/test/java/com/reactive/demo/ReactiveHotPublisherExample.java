package com.reactive.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@SpringBootTest
public class ReactiveHotPublisherExample {

    private static Stream<String> getVideo() {
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }

    @Test
    public void example1() throws InterruptedException {
        Flux<String> netFlux = Flux.fromStream(ReactiveHotPublisherExample::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .share(); // turn the cold publisher into a hot publisher

        // First Subscriber
        netFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        // wait 5 seconds before next Subscriber joins
        Thread.sleep(5000);

        // Seconds Subscriber
        netFlux.subscribe(part -> System.out.println("Subscriber 2: " + part));

        Thread.sleep(6000);
    }

    @Test
    public void example2() throws InterruptedException {
        Flux<String> netFlux = Flux.fromStream(ReactiveHotPublisherExample::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .publish()
                .refCount(2); // turn the cold publisher into a hot publisher

        // First Subscriber
        netFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        // wait 5 seconds before next Subscriber joins
        Thread.sleep(5000);

        // Seconds Subscriber
        netFlux.subscribe(part -> System.out.println("Subscriber 2: " + part));

        Thread.sleep(10000);
    }
}
