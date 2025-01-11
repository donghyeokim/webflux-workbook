package dev.practice.webfluxworkbook.mono;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoFromXXX {
    static String getName() {
        System.out.println("Generate name..");
        return "Something";
    }

    static Mono<String> getMonoName() {
        System.out.println("Entered getMonoName");
        return Mono.fromSupplier(() -> {
            System.out.println("Generate name...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Something";
        }).map(String::toUpperCase);
    }

    public static void main(String[] args) throws InterruptedException {
        Mono<String> just = Mono.just(getName());
        System.out.println();

        Mono<String> fromSupplier = Mono.fromSupplier(() -> getName());
        fromSupplier.subscribe(i -> System.out.println(i),
                err -> System.out.println(err),
                () -> System.out.println("completed"));
        System.out.println();

        for (int i = 0; i < 5; i++)
            getMonoName();
        System.out.println();

        getMonoName().subscribe(i -> System.out.println(i));
        System.out.println();

        getMonoName().subscribeOn(Schedulers.boundedElastic())
                .subscribe(i -> System.out.println(i));
        System.out.println("quickly already print");
        Thread.sleep(4000);
    }
}
