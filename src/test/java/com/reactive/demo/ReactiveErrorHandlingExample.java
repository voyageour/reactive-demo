package com.reactive.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;

@SpringBootTest
public class ReactiveErrorHandlingExample {

    @Test
    public void example1() throws IOException {
        Flux.fromStream(Stream.of(1,2,3,4,5,6,7,8,9,10))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(n -> {
                    if(n==5) throw new RuntimeException("Unknown Error Occured");
                }).subscribe(System.out::println);

        System.out.println("Press a key to continue");
        System.in.read();
    }

    @Test
    public void example2() throws IOException {
        Flux.fromStream(Stream.of(1,2,3,4,5,6,7,8,9,10))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(n -> {
                    if(n==5) throw new RuntimeException("Unknown Error Occured");
                }).subscribe(System.out::println,err -> System.out.println("Error message "+err.getMessage()));

        System.out.println("Press a key to continue");
        System.in.read();
    }

    @Test
    public void example3() throws IOException {
        Flux.fromStream(Stream.of(1,2,3,4,5,6,7,8,9,10))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(n -> {
                    if(n==5) throw new RuntimeException("Unknown Error Occured");
                }).doOnError(err -> System.out.println("Error message "+err.getMessage()))
                .subscribe(System.out::println);

        System.out.println("Press a key to continue");
        System.in.read();
    }

    @Test
    public void example4() throws IOException {
        Flux.fromStream(Stream.of(1,2,3,4,5,6,7,8,9,10))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(n -> {
                    if(n==5) throw new RuntimeException("Unknown Error Occured");
                }).onErrorContinue((err,item) -> System.out.println("Error message "+err.getMessage()))
                .subscribe(System.out::println);

        System.out.println("Press a key to continue");
        System.in.read();
    }
}
