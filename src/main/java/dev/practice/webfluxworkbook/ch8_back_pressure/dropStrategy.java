package dev.practice.webfluxworkbook.ch8_back_pressure;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class dropStrategy {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureDrop(d -> log.info("# dropped: {}", d))
                .publishOn(Schedulers.parallel())
                .subscribe(d -> {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e) {}
                    log.info("# onNext: {}", d);
                }, e -> log.error("# onError, ", e));
        Thread.sleep(2000L);
    }
}
