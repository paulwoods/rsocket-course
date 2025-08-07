package org.mrpaulwoods.rsocket.service;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import org.mrpaulwoods.rsocket.dto.RequestDto;
import org.mrpaulwoods.rsocket.util.ObjectUtil;
import reactor.core.publisher.Mono;

public class MathService implements RSocket {

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        System.out.println("Receiving: " + ObjectUtil.toObject(payload, RequestDto.class));
        return Mono.empty();
    }

}
