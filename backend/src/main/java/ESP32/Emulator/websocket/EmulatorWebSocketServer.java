package ESP32.Emulator.websocket;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.command.CommandMapper;
import ESP32.Emulator.emulator.Esp32Emulator;
import ESP32.Emulator.message.CommandEnvelope;
import ESP32.Emulator.message.CommandMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;

public class EmulatorWebSocketServer extends WebSocketServer {
    private final Set<WebSocket> clients = new CopyOnWriteArraySet<>();
    private final Supplier<String> stateSupplier;
    private final CommandMapper commandMapper;
    private final Esp32Emulator emulator;
    private final ObjectMapper objectMapper;

    public EmulatorWebSocketServer(int port, Supplier<String> stateSupplier,
                                   CommandMapper commandMapper, Esp32Emulator emulator,
                                   ObjectMapper objectMapper) {
        super(new InetSocketAddress(port));
        this.stateSupplier = stateSupplier;
        this.commandMapper = commandMapper;
        this.emulator = emulator;
        this.objectMapper = objectMapper;
    }

    public void broadcast(String message) {
        for (WebSocket client : clients) {
            client.send(message);
        }
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        clients.add(connection);
        System.out.println("WebSocket client connected");
        connection.send(stateSupplier.get());
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        clients.remove(connection);
        System.out.println("WebSocket client disconnected");
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        try {
            CommandEnvelope envelope = objectMapper.readValue(message, CommandEnvelope.class);
            Command command = commandMapper.map(envelope.payload());
            emulator.execute(command);
            broadcast(stateSupplier.get());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket connection, Exception exception) {
        exception.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started");
    }
}
