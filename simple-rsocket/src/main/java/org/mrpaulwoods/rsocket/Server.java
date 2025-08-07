package org.mrpaulwoods.rsocket;

import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.mrpaulwoods.rsocket.service.SocketAcceptorImpl;

public class Server {

    public static void main(String[] args) {
        RSocketServer rSocketServer = RSocketServer.create(new SocketAcceptorImpl());
        CloseableChannel closeableChannel = rSocketServer.bindNow(TcpServerTransport.create(6565));


        // keep listening until exit signal
        closeableChannel.onClose().block();
    }

}
