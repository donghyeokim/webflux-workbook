package dev.practice.webfluxworkbook.flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class FluxSubscribeWith {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Subscription> subscriptionAtomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received subscription: " + subscription);
                        subscriptionAtomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Received next: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Received error: " + throwable);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Received completed");
                    }
                });
        System.out.println("Somthing interrupt");
        Subscription subscription = subscriptionAtomicReference.get();
        subscription.request(2);

        Thread.sleep(2000);
        subscription.cancel();

        Thread.sleep(2000);

        subscription.request(1);
    }
}
