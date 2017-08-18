package de.cauchypeano.mstream.wss;

import de.cauchypeano.mstream.data.Event;
import de.cauchypeano.mstream.data.EventRepository;
import de.cauchypeano.mstream.data.EventStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@Component
public class MStreamSocket implements InitializingBean{

    private static final Logger LOG = Logger.getLogger(MStreamSocket.class.getName());

    @Autowired
    private EventRepository eventRepository;

    public MStreamSocket() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(eventRepository);
    }

    @Scheduled(fixedRate = 1_000)
    public void consumeEvent() {
        Event randomEvent = createRandomEvent();
        System.out.println("Produced event:" + randomEvent.getId());
        eventRepository.save(randomEvent);
    }

    private Event createRandomEvent() {
        Event event = new Event();
        event.setId(newRandomUUID());
        event.setMaschineId(newRandomUUID());
        event.setStatus(randomEventStatus());
        event.setTimestamp(new Date());
        return event;
    }

    private EventStatus randomEventStatus() {
        Random rnd = new Random();
        int i = rnd.nextInt(EventStatus.values().length);
        return EventStatus.values()[i];
    }

    private String newRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

//        WebSocketClient webSocketClient = new StandardWebSocketClient();
//        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
//        stompClient.setAutoStartup(true);
//
//        String url = "wss://machinestream.herokuapp.com/api/v1/events";
//        StompSessionHandler stompSessionHandler = new StompSessionHandler() {
//            @Override
//            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//
//            }
//
//            @Override
//            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
//
//            }
//
//            @Override
//            public void handleTransportError(StompSession session, Throwable exception) {
//
//            }
//
//            @Override
//            public Type getPayloadType(StompHeaders headers) {
//                return null;
//            }
//
//            @Override
//            public void handleFrame(StompHeaders headers, Object payload) {
//                System.out.println(payload);
//            }
//        };
//        ListenableFuture<StompSession> connect = stompClient.connect(url, stompSessionHandler);
//
//        StompSession stompSession = connect.get();
//
//        stompSession.subscribe("/api/v1/events", new StompFrameHandler() {
//
//            @Override
//            public Type getPayloadType(StompHeaders headers) {
//                return String.class;
//            }
//
//            @Override
//            public void handleFrame(StompHeaders headers, Object payload) {
//                System.out.println(payload);            }
//
//        });

//        Socket socket;
//        Channel channel;
//
//        String url = "wss://machinestream.herokuapp.com/v1/api/events";
//        socket = new Socket(url);
//        socket.connect();
//        channel = socket.chan("events", null);
//
//        channel.join()
//                .receive("new", System.out::println);

//        String httpUrl = this.endpointUri.replaceFirst("^ws:", "http:").replaceFirst("^wss:", "https:");
//        Request request = (new Request.Builder()).url(url).build();
//        OkHttpClient httpClient = new OkHttpClient();
//        WebSocketCall wsCall = WebSocketCall.create(httpClient, request);
//        WebSocketListener webSocketListener = new WebSocketListener() {
//            @Override
//            public void onOpen(WebSocket webSocket, Response response) {
//
//            }
//
//            @Override
//            public void onFailure(IOException e, Response response) {
//                LOG.log(Level.WARNING, "WebSocket connection error", e);
//            }
//
//            @Override
//            public void onMessage(ResponseBody message) throws IOException {
//
//            }
//
//            @Override
//            public void onPong(Buffer payload) {
//
//            }
//
//            @Override
//            public void onClose(int code, String reason) {
//
//            }
//        };
//        wsCall.enqueue(webSocketListener);
//
//


    }
}
