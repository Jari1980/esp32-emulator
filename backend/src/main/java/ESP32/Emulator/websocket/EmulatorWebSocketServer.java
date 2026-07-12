package ESP32.Emulator.websocket;

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

    public EmulatorWebSocketServer(int port, Supplier<String> stateSupplier) {
        super(new InetSocketAddress(port));
        this.stateSupplier = stateSupplier;
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
        System.out.println("MESSAGE: " + message);
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
