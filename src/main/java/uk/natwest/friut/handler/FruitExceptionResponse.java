package uk.natwest.friut.handler;

public class FruitExceptionResponse {
    private String message;

    public FruitExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
