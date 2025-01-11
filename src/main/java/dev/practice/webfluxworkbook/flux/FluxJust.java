package dev.practice.webfluxworkbook.flux;

import reactor.core.publisher.Flux;

public class FluxJust {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C");
        flux.subscribe(i -> System.out.println(i),
                err -> System.out.println(err),
                () -> System.out.println("Done"));
    }
}
