package umg.edu.sistema_eventos.exception;

import java.time.LocalDateTime;

// Esta clase define el formato JSON para los errores que devolvemos
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    // Constructor
    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Getters
    public LocalDateTime getTimestamp() {return timestamp;}
    public int getStatus() {return status;}
    public String getError() {return error;}
    public String getMessage() {return message;}
    public String getPath() {return path;}
}
