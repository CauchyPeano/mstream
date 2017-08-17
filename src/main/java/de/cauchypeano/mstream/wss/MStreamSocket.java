package de.cauchypeano.mstream.wss;

import org.phoenixframework.channels.Channel;
import org.phoenixframework.channels.Socket;

import java.io.IOException;

public class MStreamSocket {

    public static void main(String[] args) throws IOException {

        Socket socket;
        Channel channel;

        socket = new Socket("ws://machinestream.herokuapp.com/api/v1/events");
        socket.connect();

        channel = socket.chan("events", null);

        channel.join()
                .receive("new", envelope -> System.out.println(envelope));

    }

}
