package com.reactive.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;


@SpringBootTest
public class ReactiveMonoFluxExamples {

    @Test
    public void example1() {
        Mono.just(1).subscribe(s -> System.out.println(s));
    }

    @Test
    public void example2() {
        Flux.fromStream(Stream.of(1,2,3,4,5,6,7,8,9,10)).subscribe(s -> System.out.println(s));
    }

    @Test
    public void example3() throws IOException {

        Integer intArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Flux<Integer> fluxOfInts = Flux.fromArray(intArray).delayElements(Duration.ofSeconds(1));

        fluxOfInts.subscribe(System.out::println);
        fluxOfInts.subscribe(emit -> System.out.println("Subscriber (2) --> "+emit));
        fluxOfInts.subscribe(emit -> System.out.println("Subscriber (3) --> "+emit));

        Stream<Integer> streamOfInts2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        streamOfInts2.forEach(e-> System.out.println("Functional Programming (1) --> "+e));
        streamOfInts2.forEach(e-> System.out.println("Functional Programming (2) --> "+e));
//        streamOfInts2.forEach(e-> System.out.println("Functional Programming (3) --> "+e));

//        System.out.println("Press a key to exit");
//        System.in.read();
    }


    @Test
    public void example4() {
        Integer num = Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).delayElements(Duration.ofSeconds(1)).blockLast();
        System.out.println(num);
    }

    @Test
    public void example5() {
        Flux.fromStream(Stream.of(1,2,3,4,5))
                .subscribe(emit -> System.out.println(emit),
                        err -> System.out.println("Error "+err.getMessage()),
                        () -> System.out.println("Completed!"));
    }

    @Test
    public void example6() {
        Flux.fromStream(Stream.of(1,2,3,4,5))
                .subscribe(emit -> {
                    if(emit == 5) throw new RuntimeException("Last Element Occured!");
                    System.out.println(emit);
                    },
                        err -> System.out.println("Error "+err.getMessage()),
                        () -> System.out.println("Completed!"));
    }

}
