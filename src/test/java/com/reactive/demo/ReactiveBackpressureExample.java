package com.reactive.demo;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;

@SpringJUnitConfig
public class ReactiveBackpressureExample {

    @Test
    public void example1() throws IOException {
        Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).delayElements(Duration.ofSeconds(1))
                .log()
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("onSubscribe");
                        this.subscription = subscription;
                        subscription.request(2);
                    }

                    @Override
                    public void onNext(Integer number) {
                        System.out.println("onNext " + number);
                        if (number % 2 == 0)  subscription.request(2);
//                        if (number == 9) subscription.cancel();
//                        else subscription.request(11);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError " + throwable);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        System.out.println("Press a key to exit");
        System.in.read();
    }

}
