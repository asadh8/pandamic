package com.entrue.pandamic.view.response;

import java.io.Serializable;

/**
 * @author asadhsheriff
 */
public class GenericResponse implements Serializable {

    private boolean callSucceeded;

    private String message;

    public GenericResponse() {

    }

    public boolean isCallSucceeded() {
        return callSucceeded;
    }

    public void setCallSucceeded(boolean callSucceeded) {
        this.callSucceeded = callSucceeded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "callSucceeded=" + callSucceeded +
                ", message='" + message + '\'' +
                '}';
    }
}
