package com.reactive.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootTest
public class ReactiveOperatorsExample {

    @Test
    public void example1() {

        Mono.just(1).subscribe(System.out::println);

        Integer[] intArray = {2,4,5,6,8,10};
        Flux.fromArray(intArray).take(3).subscribe(s -> System.out.print(s+" "));
        System.out.println("");
        Flux.fromIterable(Arrays.asList(intArray)).takeLast(3).subscribe(s -> System.out.print(s+" "));

    }

    @Test
    public void example2() {
        String names[] = {"One" , "Two" , "Three" , "Four" , "Five"};
        Flux.fromArray(names).map(n -> n.length()).subscribe(System.out::println);
    }

    @Test
    public void example3() {
        String names[] = {"One" , "Two" , "Three" , "Four" , "Five"};

        Flux.fromArray(names).map(n -> Flux.just(n.split(""))).subscribe(System.out::println);
//        Flux.fromArray(names).flatMap(n -> Flux.just(n.split(""))).subscribe(System.out::println);
    }

    @Test
    public void example4() {
        String names[] = {"One" , "Two" , "Three" , "Four" , "Five"};
        Integer numbers[] = {1,2,3,4};

        Flux.zip(Flux.fromArray(names),Flux.fromArray(numbers)).subscribe(System.out::println);

        Flux.merge(Flux.fromArray(names),Flux.fromArray(numbers)).subscribe(System.out::println);

        Flux.concat(Flux.fromArray(names),Flux.fromArray(numbers)).subscribe(System.out::println);
    }

    @Test
    public void example5() {
        String names[] = {"One" , "Two" , "Three" , "Four" , "Five"};
        Integer numbers[] = {1,2,3,4,4,3,3,5,1,0};

//        Flux.fromArray(names).filter(n->n.length()%2==0).subscribe(System.out::println);
//
//        Flux.fromArray(numbers).distinct().sort().subscribe(System.out::println);

//        Flux.fromArray(numbers).elementAt(3,-1).subscribe(System.out::println);
//
//        Flux.fromArray(numbers).elementAt(numbers.length,-1).subscribe(System.out::println);


//        Flux.fromArray(numbers).groupBy(i-> i > 3).subscribe(System.out::println);
//
//        Flux.fromArray(numbers).groupBy(i -> i > 3)
//                .flatMap(groupFlux -> groupFlux.key()==Boolean.TRUE ? groupFlux: Mono.just("") )
//                .subscribe(e -> System.out.print(e+" "),
//                        err -> System.out.println(err.getMessage()),
//                        () -> System.out.println() );
//
//
//        Flux.fromArray(numbers).groupBy(i -> i < 3)
//                .flatMap(groupFlux -> groupFlux.key()==Boolean.TRUE ? groupFlux: groupFlux.ignoreElements() )
//                .subscribe(e -> System.out.print(e+" "));

    }
}
