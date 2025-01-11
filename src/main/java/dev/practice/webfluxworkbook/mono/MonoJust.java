package dev.practice.webfluxworkbook.mono;

import reactor.core.publisher.Mono;

public class MonoJust {

    public static void main(String[] args) {
        Mono<Integer> just = Mono.just(1);
        just.subscribe(i -> System.out.println("Received : " + i));
    }
}
