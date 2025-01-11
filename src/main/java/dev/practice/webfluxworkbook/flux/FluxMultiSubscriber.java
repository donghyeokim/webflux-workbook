package dev.practice.webfluxworkbook.flux;

import reactor.core.publisher.Flux;

public class FluxMultiSubscriber {
    // cold sequence로 출력 (새로운 Flux 리턴)
    public static void main(String[] args) {
        Flux<Integer> just = Flux.just(1,2,3,4,5);
        Flux<Integer> even = just.filter(i -> i % 2 == 0);
        just.subscribe(i -> System.out.println("Sub1:" + i));
        just.subscribe(i -> System.out.println("Sub2:" + i));
        even.subscribe(i -> System.out.println("Sub3:" + i));
    }
}
