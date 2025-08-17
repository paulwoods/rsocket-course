package org.mrpaulwoods.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mrpaulwoods.rsocket.dto.ChartResponseDto;
import org.mrpaulwoods.rsocket.dto.RequestDto;
import org.mrpaulwoods.rsocket.dto.ResponseDto;
import org.mrpaulwoods.rsocket.util.ObjectUtil;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Lec01RSocketTest {

    private RSocket rSocket;

    @BeforeAll
    public void beforeAll() {
        this.rSocket = RSocketConnector
                .create()
                .connect(TcpClientTransport.create("localhost", 6565))
                .block();
    }

    @Test
    public void fireAndForget() {
        Payload payload = ObjectUtil.toPayload(new RequestDto(5));
        this.rSocket.fireAndForget(payload)
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    public void requestResponse() {
        Payload payload = ObjectUtil.toPayload(new RequestDto(5));
        this.rSocket.requestResponse(payload)
                .map(p -> ObjectUtil.toObject(p, ResponseDto.class))
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void requestStream() {
        Payload payload = ObjectUtil.toPayload(new RequestDto(5));
        this.rSocket.requestStream(payload)
                .map(p -> ObjectUtil.toObject(p, ResponseDto.class))
                .doOnNext(System.out::println)
                .take(4)
                .as(StepVerifier::create)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void requestChannel() {

        Flux<Payload> payloadFlux = Flux.range(-10, 21)
                .delayElements(Duration.ofMillis(500))
                .map(RequestDto::new)
                .map(ObjectUtil::toPayload);

        Flux<ChartResponseDto> flux = this.rSocket.requestChannel(payloadFlux)
                .map(p -> ObjectUtil.toObject(p, ChartResponseDto.class))
                .doOnNext(System.out::println);

        StepVerifier.create(flux)
                .expectNextCount(21)
                .verifyComplete();
    }

}
