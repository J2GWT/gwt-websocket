package ir.moke.client.ws;

public class WebSocket {

    private final String url;
    private final int port;
    private final String path;
    private String sessionId;
    private WebSocketHandler webSocketHandler;

    public WebSocket(String url, int port, String path) {
        this.url = url;
        this.port = port;
        if (path.startsWith("/")) {
            this.path = path.substring(1);
        } else {
            this.path = path;
        }
    }

    public String getUrlConnection() {
        return "ws://" + url + ":" + port + "/" + path;
    }

    public void addHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void connect() {
        sessionId = UtilsJSNI.randomStr();
        WebSocketJSNI.connect(this, sessionId, getUrlConnection());
    }

    public void disconnect() {
        WebSocketJSNI.disconnect(sessionId);
    }

    public short getStatus() {
        return WebSocketJSNI.getStatus(sessionId);
    }

    public void sendMessage(String message) {
        WebSocketJSNI.sendMessage(sessionId, message);
    }

    private void onOpen() {
        webSocketHandler.onOpen();
    }

    private void onMessage(String message) {
        webSocketHandler.onMessage(message);
    }

    private void onClose(short code, String reason) {
        webSocketHandler.onClose(new CloseEvent(code, reason));
    }
}
