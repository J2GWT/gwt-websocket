package ir.moke.client.ws;

public class WebSocketJSNI {

    public static native void connect(WebSocket ws, String sessionId, String url) /*-{
        $wnd[sessionId] =  new WebSocket(url);
        $wnd[sessionId].onopen =  function() {ws.@ir.moke.client.ws.WebSocket::onOpen()();};
        $wnd[sessionId].onmessage =  function(msg) {ws.@ir.moke.client.ws.WebSocket::onMessage(Ljava/lang/String;)(msg.data);};
        $wnd[sessionId].onclose =  function(evt) {ws.@ir.moke.client.ws.WebSocket::onClose(SLjava/lang/String;)(evt.code,evt.reason);};
    }-*/;

    public static native void disconnect(String sessionId) /*-{
        if ($wnd[sessionId] !== undefined) $wnd[sessionId].close();
    }-*/;

    public static native void sendMessage(String sessionId, String msg) /*-{
        if ($wnd[sessionId] !== undefined) {
            $wnd[sessionId].send(msg);
        }
    }-*/;

    public static native short getStatus(String sessionId) /*-{
        if ($wnd[sessionId] == undefined) {
            return 3;
        } else {
            return $wnd[sessionId].readyState;
        }
    }-*/;
}
