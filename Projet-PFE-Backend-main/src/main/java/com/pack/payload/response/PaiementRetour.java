package com.pack.payload.response;


public class PaiementRetour {
    private boolean success;
    private String message ;
    public PaiementRetour() {
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
