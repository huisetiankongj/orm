package org.springframework.http.converter;

public class JsonPack {

    public final static String SUCCESSFUL = "successful";
    public final static String DATA = "data";

    private boolean successful;
    private Object data;
    private String msg;

    public JsonPack(boolean successful, Object data) {
        this.successful = successful;
        this.data = data;
    }

    public JsonPack(boolean successful) {
        this.data = null;
        this.successful = successful;
    }

    public JsonPack() {
        this.successful = true;
        this.data = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
