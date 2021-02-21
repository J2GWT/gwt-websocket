package ir.moke.client.ws;

public class UtilsJSNI {

    public static native String randomStr() /*-{
        return Math.random().toString(36).substring(7);
    }-*/;
}
