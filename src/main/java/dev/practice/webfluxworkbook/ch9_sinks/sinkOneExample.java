package dev.practice.webfluxworkbook.ch9_sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
public class sinkOneExample {
    public static void main(String[] args) throws InterruptedException {
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello", Sinks.EmitFailureHandler.FAIL_FAST);
        sinkOne.emitValue("Hi", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> log.info("# subscriber1 {}", data));
        mono.subscribe(data -> log.info("# subscriber2 {}", data));

        Thread.sleep(200L);
    }
}
