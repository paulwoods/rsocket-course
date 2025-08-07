package org.mrpaulwoods.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mrpaulwoods.rsocket.dto.RequestDto;
import org.mrpaulwoods.rsocket.util.ObjectUtil;
import reactor.test.StepVerifier;

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

}
