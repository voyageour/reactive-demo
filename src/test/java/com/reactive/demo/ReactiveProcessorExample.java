package com.reactive.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;

@SpringBootTest
public class ReactiveProcessorExample {

    @Test
    public void example1() {

        DirectProcessor<Long> data = DirectProcessor.create();
        data.subscribe(t -> System.out.println(t),
                e -> e.printStackTrace(),
                () -> System.out.println("Finished 1"));
        data.onNext(10L);
        data.onComplete();
        data.subscribe(t -> System.out.println(t),
                e -> e.printStackTrace(),
                () -> System.out.println("Finished 2"));
        data.onNext(12L);

    }

    @Test
    public void example2() {
        ReplayProcessor<Long> data = ReplayProcessor.create(3);
        data.subscribe(t -> System.out.println(t));
        FluxSink<Long> sink = data.sink();
        sink.next(10L);
        sink.next(11L);
        sink.next(12L);
        sink.next(13L);
        sink.next(14L);
        data.subscribe(t -> System.out.println(t));

    }
 }
