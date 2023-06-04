package com.reactive.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class ReactiveCommonMistakesExample {

    @Test
    public void example1() {
        Flux.fromStream(Stream.of("AAA","BB","CCCCC","A","DDDD"))
                .map(s -> s.length())
                .log();
    }

    @Test
    public void example2() {
        Flux<String> fluxStr = Flux.fromStream(Stream.of("AAA","BB","CCCCC","A","DDDD"));
        fluxStr.map(s -> s.length());
        fluxStr.subscribe(System.out::println);
    }

    @Test
    public void example3() {
        List<Integer> strLen = new ArrayList<>();

        Flux.fromStream(Stream.of("AAA","BB","CCCCC","A","DDDD"))
        .map(s -> s.length())
        .subscribe(l -> strLen.add(l));

        System.out.println("Flux String length "+strLen);
    }
}
