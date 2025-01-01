package dev.practice.webfluxworkbook.ch14_operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FlatMapExample {

    public static void main(String[] args) {
        Flux
                .just("Good", "Bad")
                .flatMap(feeling -> Flux
                        .just("Morning", "Afternoon", "Evening")
                        .map(time -> feeling + " " + time))
                .subscribe(log::info);
    }
}
