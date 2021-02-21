package ir.moke.client.ws;

public class CloseEvent {
    private final short code ;
    private final String reason ;

    public CloseEvent(short code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public short getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
