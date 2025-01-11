package dev.practice.webfluxworkbook.mono;

import reactor.core.publisher.Mono;

public class MonoSubscribe {
    public static void main(String[] args) {
        Mono<String> ball = Mono.just("ball");
        ball.subscribe(System.out::println);

        ball.subscribe(item -> System.out.println(item),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("completed"));
        System.out.println();

        Mono<Integer> ball2 = Mono.just("ball2")
                .map(String::length)
                .map(len -> len / 0);
        ball2.subscribe(i -> System.out.println(i),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("completed"));
        System.out.println();

        Mono<String> empty = Mono.empty();
        empty.subscribe(i -> System.out.println(i),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("completed"));
        System.out.println();

        Mono<String> error = Mono.error(new IllegalStateException());
        error.subscribe(i -> System.out.println(i),
                err -> System.out.println(err),
                () -> System.out.println("completed"));
    }
}
