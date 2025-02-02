package dev.practice.webfluxworkbook.ch9_sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class sinkManyReplayExample {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Integer> unicastSink = Sinks.many().replay().limit(2);
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));
        unicastSink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);
        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
        Thread.sleep(500L);
    }
}
