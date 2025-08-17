package org.mrpaulwoods.rsocket.client;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import org.mrpaulwoods.rsocket.dto.ResponseDto;
import org.mrpaulwoods.rsocket.util.ObjectUtil;
import reactor.core.publisher.Mono;

public class CallbackService implements RSocket {

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        System.out.println("Client received: " + ObjectUtil.toObject(payload, ResponseDto.class));
        return Mono.empty();
    }

}
