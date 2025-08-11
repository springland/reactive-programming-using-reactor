package com.learnreactiveprogramming.backpressure;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class BackPressureSubscriber<T> extends BaseSubscriber<T> {

    private int received = 0;

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        request(5); // first 5
    }

    @Override
    protected void hookOnNext(T value) {
        received++;

        if (received == 5) {
            request(3); // next 3
        } else if (received == 8) {
            cancel();   // cancel after 8 total
        }
    }
}
