## GWT WebSocket Implementation

Simple project on gwt framework for websocket communication .

#### build and run : 
`mvn clean compile package gwt:devmode`

***Example Usage :***   
Only try this : 
```java
WebSocket webSocket = new WebSocket("server_ip_address", 8080, "/PATH");
webSocket.addHandler(new WebSocketHandler() {
    @Override
    public void onOpen() {}

    @Override
    public void onMessage(String message) {}

    @Override
    public void onError() {}

    @Override
    public void onClose(CloseEvent event) {}
});
webSocket.connect();
```

***test :***
You can use `https://www.websocket.org/echo.html` or linux websocat command .
```shell script
websocat -s 1234 
```

then send a simple json like this:

```json
[{"name": "mahdi","age": 22},{"name": "Ali","age": 35},{"name": "Zeynab","age": 18}]
```