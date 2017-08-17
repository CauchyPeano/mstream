package de.cauchypeano.mstream.wss;

import org.phoenixframework.channels.Channel;
import org.phoenixframework.channels.Socket;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class MStreamSocket {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setAutoStartup(true);

        String url = "wss://machinestream.herokuapp.com/api/v1/events";
        StompSessionHandler stompSessionHandler = new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {

            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {

            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println(payload);
            }
        };
        ListenableFuture<StompSession> connect = stompClient.connect(url, stompSessionHandler);

        StompSession stompSession = connect.get();

        stompSession.subscribe("/api/v1/events", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println(payload);            }

        });

        Socket socket;
        Channel channel;

        socket = new Socket("ws://machinestream.herokuapp.com/api/v1/events");
        socket.connect();

        channel = socket.chan("events", null);

        channel.join()
                .receive("new", envelope -> System.out.println(envelope));

    }

}
