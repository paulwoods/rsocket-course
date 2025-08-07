package org.mrpaulwoods.rsocket.service;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import reactor.core.publisher.Mono;

public class MathService implements RSocket {

    @Override
    public Mono<Void> fireAndForget(Payload payload) {

        // shows that the client exits quickly (because it's fire and forget)
        // and the server waits 5 seconds before accepting the request.
        try {
            //noinspection BlockingMethodInNonBlockingContext
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Receiving: " + payload.getDataUtf8());
        return Mono.empty();
    }

}
