package dev.practice.webfluxworkbook.ch14_operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class DeferExample {

    public static void main(String[] args) throws InterruptedException {
        Mono
                .just("Hello")
                .delayElement(Duration.ofSeconds(3))
//                .switchIfEmpty(sayDefault())
                .switchIfEmpty(Mono.defer(() -> sayDefault()))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3500);
    }

    public static Mono<String> sayDefault() {
        log.info("# Say Hi");
        return Mono.just("Hi");
    }
}
