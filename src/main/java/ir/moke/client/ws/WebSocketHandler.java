package ir.moke.client.ws;

public interface WebSocketHandler {

    void onOpen();

    void onMessage(String message);

    void onError();

    void onClose(CloseEvent event);
}
